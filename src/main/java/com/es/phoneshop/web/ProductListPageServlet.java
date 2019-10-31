package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Service;
import sortResources.Comparing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() {
        service = new Service();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Comparing comparing = new Comparing();
        comparing.field = request.getParameter("order");
        comparing.upOrDown = request.getParameter("sort");
        String query = request.getParameter("query");
        request.setAttribute("products", service.productListFromDao(query, comparing.field, comparing.upOrDown));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
