<%@ page import="com.es.phoneshop.cart.ErrorMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<tags:master pageTitle="Cart Page">
    <div class="errors">
        <c:if test="${param.successUpdate}">
            <p style="color: green">Update successfully!!!</p>
        </c:if>

        <c:if test="${param.successDelete}">
            <p style="color: green">Delete successfully!!!</p>
        </c:if>
    </div>
    <c:if test="${not empty sessionScope.cart.listCartItems}">
        <form method="post" action="${pageContext.servletContext.contextPath}/cart">
            <table class="table table-striped table-hover table-sm">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <c:forEach var="cartProduct" items="${sessionScope.cart.listCartItems}" varStatus="status">
                    <tr>
                        <th>
                            <img class="product-tile"
                                 src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartProduct.product.imageUrl}">
                        </th>
                        <th>${cartProduct.product.description}</th>
                        <th>
                                ${cartProduct.product.price} ${cartProduct.product.currency}
                        </th>
                        <th>
                            <input name="productQuantity"
                                   value="${not empty errorMap.get(('quantity-').concat(cartProduct.product.id)) ? paramValues.productQuantity[status.index]: cartProduct.quantity}">
                            <c:if test="${not empty errorMap.get(('quantity-').concat(cartProduct.product.id))}">
                                <c:forEach var="error"
                                           items="${errorMap.get(('quantity-').concat(cartProduct.product.id))}">
                                    <p style="color: red">${error}</p>
                                </c:forEach>
                            </c:if>
                            <input class="form-control" type="hidden" name="productId"
                                   value="${cartProduct.product.id}">
                        </th>
                        <th>
                            <button class="btn btn-primary mb-2" form="deleteCartItem" name="productId"
                                    value="${cartProduct.product.id}">Delete
                            </button>
                        </th>
                    </tr>
                </c:forEach>
            </table>
            </br>
            <button style="margin-left: 20px" class="btn btn-primary mb-2">Update</button>
        </form>
    </c:if>
    <c:if test="${empty sessionScope.cart.listCartItems}">
        <h1>Cart is empty</h1>
    </c:if>
    <form id="deleteCartItem" action="${pageContext.servletContext.contextPath}/cart/deleteCartItem"
          method="post"></form>

</tags:master>