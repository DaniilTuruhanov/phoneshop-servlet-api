<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="sort" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>

<tags:master pageTitle="Product Page">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <form>
        <input name="query" value="${param.query}">
        <button>Search</button>
    </form>
    <div style="display: flex; ">
        <table class="button-action">
            <thead>
            <tr>
                <td>Image
                </td>
                <td>
                    Description
                    <span id="DescribeUP" href="products?query=${param.query}&sort=UP&order=DESCRIPTION">↑</span>
                    <span id="DescribeDOWN" href="products?query=${param.query}&sort=DOWN&order=DESCRIPTION">↓</span>

                </td>
                <td class="price">
                    Price
                    <span id="PriceUP" href="products?query=${param.query}&sort=UP&order=PRICE">↑</span>
                    <span id="PriceDOWN" href="products?query=${param.query}&sort=DOWN&order=PRICE">↓</span>
                </td>
            </tr>
            </thead>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                    </td>
                    <td><a href="products/${product.id}">${product.description}</a></td>
                    <td class="price">
                        <a href="" onclick="priceHistory();
                            function priceHistory() {
                            let a='<h1>PriceHistory</h1>'+
                            '<h2>${product.description}</h2>'+
                            '<c:forEach var="priceRecords" items="${product.priceHistory}">'+
                            '<p><fmt:formatDate pattern = "dd MMM yyyy" value="${priceRecords.data.getTime()}"/>- <fmt:formatNumber value="${priceRecords.price}" type="currency" currencySymbol="${priceRecords.currency.symbol}"/> </p>'+
                            '</c:forEach>';
                            return document.querySelector('.price-history').innerHTML!==a?
                            document.querySelector('.price-history').innerHTML=a:
                            document.querySelector('.price-history').innerHTML='';
                            }; return false">${product.price}
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="price-history" id="info-pricehistory" style="margin-left: 5%"></div>
    </div>
    <script>
        function getProduct(product) {
            return '<td>' +
                '<img class="product-tile"' +
                ' src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/' + product.imageUrl + '">' +
                '</td>' +
                '<td><a href=products/' + product.id + '>' + product.description + '</a></td>' +
                '<td class="price">'+
                '<span class=showPriceHistory href=products/price?id=' + product.id + '>' + product.price + '</span></td>';
               }
        function sortProducts(url) {
            fetch('/phoneshop_servlet_api_war_exploded/' + url, {
                method: 'POST'
            }).then(function (res) {
                return res.json();
            }).then(function (res) {
                let tbody = document.getElementsByTagName('tbody')[0];
                tbody.innerHTML = '';
                res.forEach(product => {
                    let node = document.createElement('tr');
                    node.innerHTML = getProduct(product);
                    tbody.appendChild(node);
                })
            })
            let a =document.getElementsByClassName('abc');
            console.log(a);
            return a;
        }
        document.getElementById('DescribeUP').addEventListener('click', (e) => {
             sortProducts(e.target.getAttribute('href'));
        });

        document.getElementById('DescribeDOWN').addEventListener('click', (e) => {
            sortProducts(e.target.getAttribute('href'));
        });
        document.getElementById('PriceUP').addEventListener('click', (e) => {
             sortProducts(e.target.getAttribute('href'));
        });
        document.getElementById('PriceDOWN').addEventListener('click', (e) => {
            sortProducts(e.target.getAttribute('href'));
        });
    </script>
</tags:master>