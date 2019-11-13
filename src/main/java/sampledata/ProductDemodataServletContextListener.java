package sampledata;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProductDemodataServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ArrayListProductDao.getInstance();
        ProductService.getInstance();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}
