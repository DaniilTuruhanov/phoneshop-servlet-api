package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class ProductListPageServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = productService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String field = request.getParameter("order");
        String upOrDown = request.getParameter("sort");
        String query = request.getParameter("query");
        request.setAttribute("products", productService.findProductsFromDao(query, field, upOrDown));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String field = req.getParameter("order");
        String upOrDown = req.getParameter("sort");
        String query = req.getParameter("query");
        List<Product> findProducts = productService.findProductsFromDao(query, field, upOrDown);
        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(findProducts);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
