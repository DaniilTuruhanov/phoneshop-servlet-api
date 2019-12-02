package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.model.product.ProductService;
import com.es.phoneshop.order.DefailtOrderService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderCreateForm;
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
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductService.getInstance();
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
        OrderCreateForm orderCreateForm = new OrderCreateForm();

        orderCreateForm.setFirstName(req.getParameter("First name"));
        orderCreateForm.setLastName(req.getParameter("Last name"));
        orderCreateForm.setDate(req.getParameter("Date"));
        orderCreateForm.setPaymentMethod(req.getParameter("Payment method"));
        orderCreateForm.setPhone(req.getParameter("Phone"));
        orderCreateForm.setAddress(req.getParameter("Address"));

        orderFieldsValidator.validate(orderCreateForm, errorMap);

        if (!errorMap.getErrorMap().isEmpty()) {
            req.setAttribute("order", order);
            req.setAttribute("errorMap", errorMap.getErrorMap());
            req.getRequestDispatcher("/WEB-INF/pages/orderPage.jsp").forward(req, resp);
            return;
        }
        order.setOrderCreateForm(orderCreateForm);
        String secureId = orderService.placeOrder(order);
        cartService.updateProductsInProductService(cart);
        cartService.clearCart(cart);
        resp.sendRedirect(req.getContextPath() + "/overview/" + secureId);
    }
}

