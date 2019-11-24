<form style="margin: auto" action="${pageContext.servletContext.contextPath}/cart">
    <button class="cartButton">
        Cart: ${sessionScope.cart.totalCost}
    </button>
</form>