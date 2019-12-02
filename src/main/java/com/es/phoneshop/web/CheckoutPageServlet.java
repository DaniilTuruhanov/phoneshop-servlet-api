package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.order.DefailtOrderService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderFieldsValidator;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javafx.util.Pair;

public class CheckoutPageServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;
    private OrderFieldsValidator orderFieldsValidator;

    @Override
    public void init() throws ServletException {
        orderFieldsValidator = OrderFieldsValidator.getInstance();
        cartService = HttpSessionCartService.getInstance();
        orderService = DefailtOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = cartService.getCart(session);
        Order order = orderService.getOrder(cart);

        req.setAttribute("order", order);
        req.getRequestDispatcher("/WEB-INF/pages/orderPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorMap errorMap = new ErrorMap();
        HttpSession session = req.getSession();
        Cart cart = cartService.getCart(session);
        Order order = orderService.getOrder(cart);
        String firstName = req.getParameter("First name");
        String lastName = req.getParameter("Last name");
        String date = req.getParameter("Date");
        String paymentMethod = req.getParameter("Payment method");
        String phone = req.getParameter("Phone");
        String address = req.getParameter("Address");
        orderFieldsValidator.validate(new Pair(firstName,"First name"),errorMap);
        orderFieldsValidator.validate(new Pair(lastName,"Last name"),errorMap);
        orderFieldsValidator.validate(new Pair(date,"Date"),errorMap);
        orderFieldsValidator.validate(new Pair(phone,"Phone"),errorMap);
        orderFieldsValidator.validate(new Pair(address,"Address"),errorMap);
        orderFieldsValidator.validate(new Pair(paymentMethod,"Payment Method"),errorMap);

        if (!errorMap.getErrorMap().isEmpty()) {
            req.setAttribute("order", order);
            req.setAttribute("errorMap", errorMap.getErrorMap());
            req.getRequestDispatcher("/WEB-INF/pages/orderPage.jsp").forward(req, resp);
            return;
        }
        order.setAddress(address);
        order.setDate(date);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setPhone(phone);
        order.setPaymentMethod(paymentMethod);
        String secureId = orderService.placeOrder(order);
        cartService.clearCart(cart);
        resp.sendRedirect(req.getContextPath() + "/overview/" + secureId);
    }
}

