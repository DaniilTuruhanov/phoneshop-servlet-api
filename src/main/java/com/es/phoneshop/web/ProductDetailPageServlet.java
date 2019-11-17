package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.RecentlyViewedProducts;
import com.es.phoneshop.cart.Validator;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

public class ProductDetailPageServlet extends HttpServlet {
    private ProductService productService;
    private HttpSessionCartService cartService;
    private RecentlyViewedProducts recentlyViewedProducts;

    @Override
    public void init() {
        recentlyViewedProducts = new RecentlyViewedProducts();
        productService = productService.getInstance();
        cartService = cartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduct = getIdProduct(request);
        showDetailPage(idProduct, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new QuantityValidator();
        String idProduct = getIdProduct(request);
        Cart cart = cartService.getCart(request);
        try {
            addProductToCart(validator, cart, idProduct, request, response);
        } catch (ProductNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void showDetailPage(String idProduct, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = productService.getProduct(idProduct);
            HttpSession session = request.getSession();

            request.setAttribute("product", product);
            setupRecentlyViewedProducts(request);
            request.getRequestDispatcher("/WEB-INF/pages/productPage.jsp").forward(request, response);
            recentlyViewedProducts.addProductInRecentlyViewes(product);
            session.setAttribute("objectRecentlyViewed", recentlyViewedProducts);
        } catch (ProductNotFoundException e) {
            request.setAttribute("errorId", idProduct);
            request.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(request, response);
        }
    }

    private void addProductToCart(Validator validator, Cart cart, String idProduct, HttpServletRequest request, HttpServletResponse response) throws ProductNotFoundException, ServletException, IOException, ParseException {
        String stringQuantity = request.getParameter("quantity");
        Locale locale = request.getLocale();
        Map errorMap = validator.validate(locale, cart, idProduct, stringQuantity);
        HttpSession session = request.getSession();

        if (errorMap.isEmpty()) {
            int intQuantity = getQuantity(locale, stringQuantity);

            cartService.addCartItem(cart, idProduct, intQuantity);

            session.setAttribute("cart", cart);
            response.sendRedirect(request.getRequestURI() + "?success=true");
        } else {
            request.setAttribute("error", errorMap.get("error"));
            session.setAttribute("cart", cart);

            showDetailPage(idProduct, request, response);
        }
    }

    public void setupRecentlyViewedProducts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        recentlyViewedProducts = session.getAttribute("objectRecentlyViewed") == null ? new RecentlyViewedProducts() : (RecentlyViewedProducts) request.getSession().getAttribute("objectRecentlyViewed");

        Queue<Product> productLinkedList = recentlyViewedProducts.getProductQueue();
        session.setAttribute("recentlyViewed", productLinkedList);
    }

    public int getQuantity(Locale locale, String stringQuantity) throws ParseException {
        return NumberFormat.getInstance(locale).parse(stringQuantity).intValue();
    }

    private String getIdProduct(HttpServletRequest request) {
        String idProduct = request.getRequestURI();
        return idProduct.substring(idProduct.lastIndexOf("/") + 1);
    }

    public ProductService getProductService() {
        return productService;
    }

    public HttpSessionCartService getCartService() {
        return cartService;
    }

    public void setCartService(HttpSessionCartService cartService) {
        this.cartService = cartService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
