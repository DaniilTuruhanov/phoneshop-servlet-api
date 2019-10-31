
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" %>
<%@ attribute name="sort" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="query" %>

<a href="products?query=${param.query}&sort=${sort}&order=${order}" font-weigth:>${name}</a>