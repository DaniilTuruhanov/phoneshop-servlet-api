package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.http.HttpSession;

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
        }
        return cart;
    }

    @Override
    public void addCartItem(Cart cart, String idProduct, int quantity)
            throws OutOfStockException, ProductNotFoundException {
        Product product = productService.getProduct(idProduct);
        cart.add(product, quantity);
    }

    @Override
    public void updateCartItem(Cart cart, String idProduct, int quantity)
            throws ProductNotFoundException, OutOfStockException {
        Product product = productService.getProduct(idProduct);
        cart.update(product, quantity);
    }

    @Override
    public void deleteCartItem(Cart cart, String idProduct) {
        cart.delete(idProduct);
    }

    @Override
    public void clearCart(Cart cart) {
        cart.clear();
    }

    @Override
    public void updateProductsInProductService(Cart cart) {
        cart.getListCartItems().stream().forEach(cartItem -> {
            try {
                Product product = productService.getProduct(cartItem.getProduct().getId());
                product.setStock(product.getStock() - cartItem.getQuantity());
            } catch (ProductNotFoundException e) {
            }
        });
    }
}


