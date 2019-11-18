package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.cart.OutOfStockException;
import com.es.phoneshop.cart.ParseIdClass;
import com.es.phoneshop.cart.ParseQuantityClass;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.RecentlyViewedProductsService;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductDetailPageServlet extends HttpServlet {
    private ProductService productService;
    private HttpSessionCartService cartService;
    private RecentlyViewedProductsService recentlyViewedProductsService;
    private ParseIdClass parseIdClass;
    private ParseQuantityClass parseQuantityClass;

    @Override
    public void init() {
        parseIdClass = new ParseIdClass();
        recentlyViewedProductsService = RecentlyViewedProductsService.getInstance();
        productService = productService.getInstance();
        cartService = cartService.getInstance();
        parseIdClass = new ParseIdClass();
        parseQuantityClass = new ParseQuantityClass();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduct = parseIdClass.getId(request);
        showDetailPage(idProduct, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new QuantityValidator();
        HttpSession session = request.getSession();
        String idProduct = parseIdClass.getId(request);
        Cart cart = cartService.getCart(session);
        addProductToCart(validator, cart, idProduct, request, response);
    }

    public void showDetailPage(String idProduct, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = productService.getProduct(idProduct);
            HttpSession session = request.getSession();
            recentlyViewedProductsService.setRecentlyViewedProductInSession(session);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/productPage.jsp").forward(request, response);
            recentlyViewedProductsService.addProductInRecentlyViewes(product, session);
        } catch (ProductNotFoundException e) {
            request.setAttribute("errorId", idProduct);
            request.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(request, response);
        }
    }

    private void addProductToCart(Validator validator, Cart cart, String idProduct, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringQuantity = request.getParameter("quantity");
        Locale locale = request.getLocale();
        Map<String, String> errorMap = new HashMap<>();
        validator.validate(request, response, errorMap);
        HttpSession session = request.getSession();

        if (errorMap.isEmpty()) {
            int intQuantity = parseQuantityClass.getQuantity(locale, stringQuantity);
            try {
                cartService.addCartItem(cart, idProduct, intQuantity);
            } catch (OutOfStockException e) {
                errorMap.put("quantity", "Not enough stock. Stock: " + e.getTotalQuantity());
                request.setAttribute("errorMap", errorMap);
                session.setAttribute("cart", cart);
                showDetailPage(idProduct, request, response);
                return;
            }
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getRequestURI() + "?success=true");
        } else {
            request.setAttribute("errorMap", errorMap);
            session.setAttribute("cart", cart);
            showDetailPage(idProduct, request, response);
        }
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
