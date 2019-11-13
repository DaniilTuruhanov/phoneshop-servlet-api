<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product Page">
    <h3>Information about product</h3>
    <table border="5px">
        <tr>
            <td>
                <h1>${product.description}</h1>
                <img class="productPage-tile"
                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                <h3>Price : <fmt:formatNumber value="${product.price}" type="currency"
                                              currencySymbol="${product.currency.symbol}"/></h3>
                <h3>Quantity in stock : ${product.stock} </h3>
            </td>
        </tr>
    </table>
            <a href="../">Back to Product List</a>
</tags:master>