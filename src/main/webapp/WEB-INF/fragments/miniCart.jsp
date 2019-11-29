<form class="cart-button" action="${pageContext.servletContext.contextPath}/cart">
    <button style="margin-bottom: 10px" class="btn btn-primary btn-lg">
        Cart: ${sessionScope.cart.totalCost}
    </button>
</form>