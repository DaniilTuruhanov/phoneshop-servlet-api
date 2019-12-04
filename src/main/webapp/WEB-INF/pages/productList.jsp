<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sort" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Product List Page">
    <p class="welcome">
        Welcome to Expert-Soft training!
    </p>
    <form class="form-inline">
        <div class="form-group mx-sm-3 mb-2">
            <input class="form-control" name="query" value="${param.query}">
        </div>
        <button class="btn btn-primary mb-2">Search</button>
    </form>
    <div style="display: flex;">
        <table class="table table-striped table-hover table-sm">
            <thead>
            <tr>
                <th>Image
                </th>
                <th>
                    Description
                    <span id="DescribeUP" href="products/sort?query=${param.query}&sort=UP&order=DESCRIPTION">↑</span>
                    <span id="DescribeDOWN"
                          href="products/sort?query=${param.query}&sort=DOWN&order=DESCRIPTION">↓</span>

                </th>
                <th class="price">
                    Price
                    <span id="PriceUP" href="products/sort?query=${param.query}&sort=UP&order=PRICE">↑</span>
                    <span id="PriceDOWN" href="products/sort?query=${param.query}&sort=DOWN&order=PRICE">↓</span>
                </th>
            </tr>
            </thead>
            <c:forEach var="product" items="${products}">
                <tr>
                    <th>
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                    </th>
                    <th><a href="products/${product.id}">${product.description}</a></th>
                    <th class="price">
                        <a href="" onclick="priceHistory();
                                function priceHistory() {
                                let a='<p>Price History</p>'+
                                '<p>${product.description}</p>'+'<c:forEach var="priceRecords" items="${product.priceHistory}">'+'<p> ${priceRecords.data}-<fmt:formatNumber value="${priceRecords.price}" type="currency" currencySymbol="${priceRecords.currency.getSymbol()}"/> </p>'+'</c:forEach>';
                                console.log(a);
                                return document.querySelector('.price-history').innerHTML!==a?
                                document.querySelector('.price-history').innerHTML=a:
                                document.querySelector('.price-history').innerHTML='';
                                }; return false"> <fmt:formatNumber value="${product.price}" type="currency"
                                                                    currencySymbol="${product.currency.getSymbol()}"/>
                        </a>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <div class="price-history" id="info-pricehistory" style="margin-left: 5%"></div>
    </div>
    <script>
        function getProduct(product) {
            return '<th>' +
                '<img class="product-tile"' +
                ' src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/' + product.imageUrl + '">' +
                '</th>' +
                '<th><a href=products/' + product.id + '>' + product.description + '</a></th>' +
                '<th class="price">' +
                '<span class=showPriceHistory href=products/price?id=' + product.id + '>' + product.price + " " + product.currency + '</span></th>';
        }

        function sortProducts(url) {
            fetch('/phoneshop_servlet_api_war_exploded/' + url, {
                method: 'GET'
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
            let a = document.getElementsByClassName('abc');
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