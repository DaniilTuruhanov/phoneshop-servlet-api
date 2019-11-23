package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;

public class RecentlyViewedProductsService {
    private static RecentlyViewedProductsService recentlyViewedProductsService;

    private RecentlyViewedProductsService() {
    }

    public static RecentlyViewedProductsService getInstance() {
        if (recentlyViewedProductsService == null) {
            recentlyViewedProductsService = new RecentlyViewedProductsService();
        }
        return recentlyViewedProductsService;
    }

    public void addProductInRecentlyViewes(Product product, HttpSession session) {
        RecentlyViewedProducts recentlyViewedProducts = (RecentlyViewedProducts) session.getAttribute("recentlyViewedProducts");
        if (recentlyViewedProducts == null) {
            recentlyViewedProducts = new RecentlyViewedProducts();
        }
        recentlyViewedProducts.addProductInProductQueue(product);
        session.setAttribute("recentlyViewedProducts", recentlyViewedProducts);
    }
}
