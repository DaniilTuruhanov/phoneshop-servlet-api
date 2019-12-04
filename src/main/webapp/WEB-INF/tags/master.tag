<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body class="product-list" >
<header >
    <a href="${pageContext.servletContext.contextPath}/products">
        <img src="${pageContext.servletContext.contextPath}/images/logo.svg"/>PhoneShop
    </a>
    <jsp:include page="/minicart"></jsp:include>
    <form class="cart-button" action="${pageContext.servletContext.contextPath}/checkout">
        <button style="margin-bottom: 10px" class="btn btn-primary btn-lg">
            Checkout page
        </button>
    </form>
</header>
<main>
    <jsp:doBody/>
</main>
</body>
</html>