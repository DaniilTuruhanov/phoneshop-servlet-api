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
<tags:master pageTitle="Order Overview Page">
    <h1>Thank you for your order</h1>
    <table class="table table-striped table-hover table-sm">
        <thead>
        <tr>
            <th>Image</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <c:forEach var="orderItem" items="${order.listCartItems}" varStatus="status">
            <tr>
                <th>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${orderItem.product.imageUrl}">
                </th>
                <th>${orderItem.product.description}</th>
                <th>
                        ${orderItem.product.price} ${orderItem.product.currency}
                </th>
                <th>
                        ${orderItem.quantity}
                </th>
            </tr>
        </c:forEach>

        </br>
        <tr>
            <th colspan="2">Subtotal</th>
            <th>${order.subtotal}</th>
        </tr>
        <tr>
            <th colspan="2">Delivery cost</th>
            <th>${order.deliveryCost}</th>
        </tr>
        <tr>
            <th colspan="2">Total cost</th>
            <th>${order.totalCost}</th>
        </tr>
    </table>
    <table class="table table-sm">
        <tr>
            <th>First Name:</th>
            <th>${order.firstName}</th>
        </tr>

        <tr>
            <th>Last Name:</th>
            <th>${order.lastName}</th>
        </tr>

        <tr>
            <th>Phone:</th>
            <th>${order.phone}</th>
        </tr>

        <tr>
            <th>Address:</th>
            <th>${order.address}</th>
        </tr>

        <tr>
            <th>Payment method:</th>
            <th> ${order.paymentMethod}</th>
        </tr>

        <tr>
            <th>Date:</th>
            <th>${order.date}</th>
        </tr>

    </table>
    <form id="deleteCartItem" action="${pageContext.servletContext.contextPath}/cart/deleteCartItem"
          method="post"></form>

</tags:master>