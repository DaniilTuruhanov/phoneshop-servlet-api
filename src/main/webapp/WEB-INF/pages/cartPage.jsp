<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<tags:master pageTitle="Product Page">
    <c:if test="${param.successUpdate}">
        <p style="color: green">Update successfully!!!</p>
    </c:if>

    <c:if test="${param.successDelete}">
        <p style="color: green">Delete successfully!!!</p>
    </c:if>
    <c:if test="${not empty sessionScope.cart.listCartItems}">
    <form method="post" action="${pageContext.servletContext.contextPath}/cart">
        <table class="button-action">
            <thead>
            <tr>
                <td>Image</td>
                <td>Description</td>
                <td>Price</td>
                <td>Quantity</td>
                <td>Actions</td>
            </tr>
            </thead>
            <c:forEach var="cartProduct" items="${sessionScope.cart.listCartItems}" varStatus="status">
                <tr>
                    <td>
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartProduct.product.imageUrl}">
                    </td>
                    <td>${cartProduct.product.description}</td>
                    </td>
                    <td>
                            ${cartProduct.product.price} ${cartProduct.product.currency}
                    </td>
                    <td>
                        <input name="productQuantity" value="${not empty errorMap.errorMap.get(cartProduct.product.id) ? paramValues.productQuantity[status.index]: cartProduct.quantity}">
                           <c:if test="${not empty errorMap.errorMap.get(cartProduct.product.id)}">
                               <c:forEach var="error" items="${errorMap.errorMap.get(cartProduct.product.id)}">
                                   <p style="color: red">${error}</p>
                               </c:forEach>
                        </c:if>
                        <input type="hidden" name="productId" value="${cartProduct.product.id}">

                    </td>
                    <td>
                        <button form="deleteCartItem" name="productId" value="${cartProduct.product.id}">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </br>
        <button>Update</button>
    </form>
    </c:if>
    <c:if test="${empty sessionScope.cart.listCartItems}">
        <h1>Cart is empty</h1>
    </c:if>
    <form id="deleteCartItem" action="${pageContext.servletContext.contextPath}/cart/deleteCartItem" method="post"></form>

</tags:master>