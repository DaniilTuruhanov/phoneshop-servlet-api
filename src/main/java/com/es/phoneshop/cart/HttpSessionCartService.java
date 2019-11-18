package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class HttpSessionCartService implements CartService {
    private static HttpSessionCartService cartService;
    private static ProductService productService;

    private HttpSessionCartService() {
        productService = ProductService.getInstance();
    }

    public static HttpSessionCartService getInstance() {
        if (cartService == null) {
            cartService = new HttpSessionCartService();
        }
        return cartService;
    }

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public void addCartItem(Cart cart, String idProduct, int quantity) throws OutOfStockException, ProductNotFoundException {
        Product product = productService.getProduct(idProduct);
        cart.addToListCartItems(product, quantity);

        int totalQuantity = cart.getTotalQuantity();
        BigDecimal totalCost = cart.getTotalCost();
        BigDecimal totalProductPrice = productService.getProduct(idProduct).getPrice();
        BigDecimal totalResultProductPrice = totalProductPrice.multiply(new BigDecimal(quantity));

        cart.setTotalQuantity(totalQuantity + quantity);
        BigDecimal result = totalCost.add(totalResultProductPrice);
        cart.setTotalCost(result);
    }
}


