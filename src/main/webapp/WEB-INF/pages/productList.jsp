<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>
    <form>
        <input name="query" value=${param.query}>
        <button>Search</button>
    </form>
    <table>
        <thead>
        <tr>
            <td>Image
            </td>
            <td>
                Description
                    <tags:sort sort="UP" order="DESCRIPTION" name="↑"></tags:sort></b>
                    <tags:sort sort="DOWN" order="DESCRIPTION" name="↓"></tags:sort>
            </td>
            <td class="price" >
                Price
                <tags:sort sort="UP" order="PRICE" name="↑"></tags:sort>
                <tags:sort sort="DOWN" order="PRICE" name="↓"></tags:sort>

            </td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                </td>
                <td>${product.description}</td>
                <td class="price">
                    <fmt:formatNumber value="${product.price}" type="currency"
                                      currencySymbol="${product.currency.symbol}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</tags:master>