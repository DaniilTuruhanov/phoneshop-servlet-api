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
<jsp:useBean id="order" type="com.es.phoneshop.order.Order" scope="request"/>
<tags:master pageTitle="Order Page">
    <c:if test="${not empty order.listCartItems}">
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
    <form action="${pageContext.servletContext.contextPath}/checkout" method="post">
        <table>
            <tags:field name="First name" errorMap="${errorMap}"></tags:field>
            <tags:field name="Last name" errorMap="${errorMap}"></tags:field>
            <tags:field name="Phone" errorMap="${errorMap}"></tags:field>
            <tags:field name="Address" errorMap="${errorMap}"></tags:field>

            <tr>
                <th>Date:</th>
                <th>
                    <input name="Date" type="date">
                    <c:if test="${not empty errorMap.get('Date')}">
                        <c:forEach var="error" items="${errorMap.get('Date')}">
                            <span style="color: red">${error}</span>
                        </c:forEach>
                    </c:if>
                </th>
            </tr>
            <tr>
                <th>Payment method:</th>
                <th>
                    <select name="Payment method">
                        <option>Credit card</option>
                        <option>Money</option>
                    </select>
                </th>
            </tr>
        </table>
        </br>
        <button style="margin-left: 20px" class="btn btn-primary mb-2">Place order</button>
    </form>
    </c:if>
    <c:if test="${empty order.listCartItems}">
        <h1>Order is empty</h1>
    </c:if>
</tags:master>