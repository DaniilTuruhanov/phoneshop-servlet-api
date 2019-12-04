package com.es.phoneshop.web;

import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.model.product.Comment;
import com.es.phoneshop.model.product.CommentValidator;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.cart.ParseString.getId;

public class CommentServlet extends HttpServlet {

    private CommentValidator commentValidator;
    private ProductService productService;

    @Override
    public void init() {
        productService = ProductService.getInstance();
        commentValidator = CommentValidator.getInstance();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProduct = getId(req);
        ErrorMap errorMap = new ErrorMap();
        Comment commentItem = new Comment();
        String name = req.getParameter("Name");
        String rating = req.getParameter("Rating");
        String comment = req.getParameter("Comment");

        commentItem.setName(name);
        commentItem.setRating(Integer.parseInt(rating));
        commentItem.setComment(comment);
        commentValidator.validate(commentItem, errorMap);
        Product product = null;
        try {
            product = productService.getProduct(idProduct);
            if (errorMap.getErrorMap().isEmpty()) {
                product.addComment(commentItem);
            }
            req.setAttribute("errorMap", errorMap.getErrorMap());
            req.setAttribute("product", product);
            req.getRequestDispatcher("/WEB-INF/pages/productPage.jsp").forward(req, resp);
        } catch (ProductNotFoundException e) {
            req.setAttribute("errorId", idProduct);
            req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, resp);
        }
    }

    public CommentValidator getCommentValidator() {
        return commentValidator;
    }

    public void setCommentValidator(CommentValidator commentValidator) {
        this.commentValidator = commentValidator;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}