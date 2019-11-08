package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailPageServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = productService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = req.getRequestURI();
        try {
            req.setAttribute("product", productService.getProductFromDao(idProduct.substring(idProduct.lastIndexOf("/") + 1)));
        } catch (ProductNotFoundException e) {
            req.setAttribute("errorId", idProduct.substring(idProduct.lastIndexOf("/") + 1));
            req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/pages/productPage.jsp").forward(req, resp);
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
