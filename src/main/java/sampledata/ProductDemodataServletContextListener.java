package sampledata;

import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.RecentlyViewedProductsService;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductService;
import com.es.phoneshop.order.ArrayListOrderService;
import com.es.phoneshop.order.OrderFieldsValidator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProductDemodataServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ArrayListProductDao.getInstance();
        ProductService.getInstance();
        QuantityValidator.getInstance();
        RecentlyViewedProductsService.getInstance();
        ArrayListProductDao.getInstance();
        ArrayListOrderService.getInstance();
        OrderFieldsValidator.getInstance();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
