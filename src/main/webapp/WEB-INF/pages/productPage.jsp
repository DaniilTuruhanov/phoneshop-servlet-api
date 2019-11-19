<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<tags:master pageTitle="Product Page">
    <c:if test="${not empty errorMap}">
        <p style="color: red">Error!!!</p>
    </c:if>
    <c:if test="${param.success}">
        <p style="color: green">Success!!!</p>
    </c:if>
    <h3>Information about product</h3>
    <form method="post" action="${pageContext.servletContext.contextPath}/products/${product.id}"
          style="display: flex;">
        <input name="quantity" value="${not empty param.quantity ? param.quantity : 1}">
        <button>Add to cart</button>
    </form>
    <script>
    </script>
    <c:if test="${not empty errorMap}">
        <c:forEach var="error" items="${errorMap.get('quantity')}">
            <p style="color: red">${error}</p>
        </c:forEach>
    </c:if>
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
    <br>
    <br>
    <c:if test="${not empty sessionScope.recentlyViewedProducts.getProductQueue()}">
        <h3>Recently viewed</h3>
        <table class="button-action">
            <c:forEach var="recentlyViewedProduct" items="${sessionScope.recentlyViewedProducts.getProductQueue()}">
                <td>
                    <img style="display: block; margin-left: auto; margin-right: auto" class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${recentlyViewedProduct.imageUrl}">
                    <p><a href="products/${recentlyViewedProduct.id}">${recentlyViewedProduct.description}</a></p>
                    <p style="text-align: center"><fmt:formatNumber value="${recentlyViewedProduct.price}"
                                                                    type="currency"
                                                                    currencySymbol="${recentlyViewedProduct.currency.symbol}"/></p>
                </td>
            </c:forEach>
        </table>
    </c:if>
</tags:master>