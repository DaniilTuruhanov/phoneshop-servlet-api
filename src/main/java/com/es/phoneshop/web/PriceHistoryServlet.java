package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PriceHistoryServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = productService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = req.getParameter("id");
        try {
           Product product=productService.getProductFromDao(idProduct);
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(product);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        } catch (ProductNotFoundException e) {
            req.setAttribute("errorId", idProduct.substring(idProduct.lastIndexOf("/") + 1));
            req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, resp);
        }
    }
}
