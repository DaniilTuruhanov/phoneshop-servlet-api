<%@ page import="com.es.phoneshop.cart.ErrorMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product Page">
    <div class="errors">
        <c:if test="${not empty errorMapForPDP.getErrorMap()}">
            <p style="color: red">Error!!!</p>
        </c:if>
        <c:if test="${param.success}">
            <p style="color: green">Success!!!</p>
        </c:if>
    </div>
    <form method="post" action="${pageContext.servletContext.contextPath}/products/${product.id}"
          class="form-inline">
        <div class="form-group mx-sm-3 mb-2">
            <input name="quantity" class="form-control" value="${not empty param.quantity ? param.quantity : 1}">
        </div>
        <button class="btn btn-primary mb-2">Add to cart</button>
    </form>
    <div class="errors">
        <c:if test="${not empty errorMapForPDP.getErrorMap()}">
            <c:forEach var="error" items="${errorMapForPDP.getErrorMap().get('quantity-'.concat(product.id))}">
                <p style="color: red">${error}</p>
            </c:forEach>
        </c:if>
    </div>
    <div class="card" style="width: 18rem;">
        <img class="card-img-top"
             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}"
             alt="Card image cap">
        <div class="card-body">
            <p>${product.description}</p>
            <p>Price : <fmt:formatNumber value="${product.price}" type="currency"
                                         currencySymbol="${product.currency.symbol}"/></p>
            <p>Quantity in stock : ${product.stock} </p>
        </div>
    </div>

    <div class="rec-products">
        <c:if test="${not empty sessionScope.recentlyViewedProducts}">
            <h4>Recently viewed</h4>
            <table class="button-action">
                <c:forEach var="recentlyViewedProduct"
                           items="${sessionScope.recentlyViewedProducts.getProductQueue()}">
                    <td>
                        <img style="display: block; margin-left: auto; margin-right: auto" class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${recentlyViewedProduct.imageUrl}">
                        <p><a href="products/${recentlyViewedProduct.id}">${recentlyViewedProduct.description}</a>
                        </p>
                        <p style="text-align: center"><fmt:formatNumber value="${recentlyViewedProduct.price}"
                                                                        type="currency"
                                                                        currencySymbol="${recentlyViewedProduct.currency.symbol}"/></p>
                    </td>
                </c:forEach>
            </table>
        </c:if>
    </div>
</tags:master>