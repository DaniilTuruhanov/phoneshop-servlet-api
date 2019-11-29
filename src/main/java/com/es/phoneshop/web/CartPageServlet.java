package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.cart.OutOfStockException;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.model.product.ProductNotFoundException;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.es.phoneshop.cart.ParseString.getQuantity;

public class CartPageServlet extends HttpServlet {
    private CartService cartService;
    private QuantityValidator validator;

    @Override
    public void init() {
        validator = QuantityValidator.getInstance();
        cartService = HttpSessionCartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/cartPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorMap errorMap = new ErrorMap();
        String[] productIdStrings = req.getParameterValues("productId");
        String[] productQuantityStrings = req.getParameterValues("productQuantity");
        for (int i = 0; i < productIdStrings.length; i++) {
            try {
                updateProductInCart(productIdStrings[i], productQuantityStrings[i], errorMap, req, resp);
            } catch (ProductNotFoundException e) {
                req.setAttribute("errorId", productIdStrings[i]);
                req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, resp);
                return;
            }
        }
        if (errorMap.getErrorMap().isEmpty()) {
            resp.sendRedirect(req.getRequestURI() + "?successUpdate=true");
        } else {
            req.setAttribute("errorMap", errorMap.getErrorMap());
            req.getRequestDispatcher("/WEB-INF/pages/cartPage.jsp").forward(req, resp);
        }
    }

    private void updateProductInCart(String idProduct, String quantity, ErrorMap errorMap, HttpServletRequest request, HttpServletResponse response)
            throws ProductNotFoundException {
        HttpSession session = request.getSession();
        Cart cart = cartService.getCart(session);
        Pair<String, String> pair = new Pair<>(quantity, idProduct);
        validator.validate(pair, errorMap);
        if (errorMap.getErrorMap().get("quantity-" + idProduct) == null) {
            int intQuantity = getQuantity(quantity);
            try {
                cartService.updateCartItem(cart, idProduct, intQuantity);
            } catch (OutOfStockException e) {
                errorMap.addError("quantity-" + idProduct, "Not enough stock. Stock: " + e.getTotalQuantity());
            }
        }
    }
}
