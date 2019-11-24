package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.cart.OutOfStockException;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.RecentlyViewedProductsService;
import com.es.phoneshop.cart.Validator;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.es.phoneshop.cart.ParseIdAndQuantity.getId;
import static com.es.phoneshop.cart.ParseIdAndQuantity.getQuantity;

public class ProductDetailPageServlet extends HttpServlet {
    private ProductService productService;
    private HttpSessionCartService cartService;
    private RecentlyViewedProductsService recentlyViewedProductsService;
    private QuantityValidator quantityValidator;

    @Override
    public void init() {
        quantityValidator = QuantityValidator.getInstance();
        recentlyViewedProductsService = RecentlyViewedProductsService.getInstance();
        productService = productService.getInstance();
        cartService = cartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduct = getId(request);

        showDetailPage(idProduct, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idProduct = getId(request);
        Cart cart = cartService.getCart(session);

        addProductToCart(quantityValidator, cart, idProduct, request, response);
    }

    public void showDetailPage(String idProduct, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = productService.getProduct(idProduct);
            HttpSession session = request.getSession();

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
        ErrorMap errorMap = new ErrorMap();
        HttpSession session = request.getSession();
        Pair<String, String> pair = new Pair<>(stringQuantity, idProduct);
        validator.validate(pair, errorMap);
        if (errorMap.getErrorMap().isEmpty()) {
            int intQuantity = getQuantity(stringQuantity);
            try {
                try {
                    cartService.addCartItem(cart, idProduct, intQuantity);
                } catch (ProductNotFoundException e) {
                    request.setAttribute("errorId", idProduct);
                    request.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(request, response);
                }
            } catch (OutOfStockException e) {
                errorMap.addError(idProduct, "Not enough stock. Stock: " + e.getTotalQuantity());
                request.setAttribute("errorMapForPDP", errorMap);
                session.setAttribute("cart", cart);
                showDetailPage(idProduct, request, response);
                return;
            }
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getRequestURI() + "?success=true");
        } else {
            request.setAttribute("errorMapForPDP", errorMap);
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

    public RecentlyViewedProductsService getRecentlyViewedProductsService() {
        return recentlyViewedProductsService;
    }

    public void setRecentlyViewedProductsService(RecentlyViewedProductsService recentlyViewedProductsService) {
        this.recentlyViewedProductsService = recentlyViewedProductsService;
    }

    public QuantityValidator getQuantityValidator() {
        return quantityValidator;
    }

    public void setQuantityValidator(QuantityValidator quantityValidator) {
        this.quantityValidator = quantityValidator;
    }

    public void setCartService(HttpSessionCartService cartService) {
        this.cartService = cartService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
