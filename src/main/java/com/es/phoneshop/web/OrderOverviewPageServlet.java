package com.es.phoneshop.web;

import com.es.phoneshop.order.DefailtOrderService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderNotFoundException;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.cart.ParseString.getId;

public class OrderOverviewPageServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        orderService = DefailtOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = getId(req);
            Order order = orderService.getOrder(id);
            req.setAttribute("order",order);
            req.getRequestDispatcher("/WEB-INF/pages/orderOverviewPage.jsp").forward(req, resp);
        } catch (OrderNotFoundException e) {
            req.setAttribute("errorId", getId(req));
            req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, resp);
        }
    }
}

