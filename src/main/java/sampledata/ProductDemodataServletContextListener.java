package sampledata;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.PriceRecord;
import com.es.phoneshop.model.product.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.math.BigDecimal;
import java.util.*;

public class ProductDemodataServletContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private static volatile ArrayListProductDao arrayListProductDao;

    public void contextInitialized(ServletContextEvent sce) {
        arrayListProductDao = arrayListProductDao.getInstance();
        Currency usd = Currency.getInstance("USD");
        arrayListProductDao.save(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        arrayListProductDao.save(new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        arrayListProductDao.save(new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(300), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg"));
        arrayListProductDao.save(new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"));
        arrayListProductDao.save(new Product("5L", "Apple iPhone 6", new BigDecimal(1000), usd, 30, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(1000), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(1100), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(1500), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg"));
        arrayListProductDao.save(new Product("6L", "HTC EVO Shift 4G", new BigDecimal(320), usd, 3, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(320), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(330), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(370), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg"));
        arrayListProductDao.save(new Product("7L", "Sony Ericsson C901", new BigDecimal(420), usd, 30, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(420), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(430), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(470), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg"));
        arrayListProductDao.save(new Product("8L", "Sony Xperia XZ", new BigDecimal(120), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(120), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(130), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(170), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg"));
        arrayListProductDao.save(new Product("9L", "Nokia 3310", new BigDecimal(70), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(70), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(80), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(120), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg"));
        arrayListProductDao.save(new Product("10L", "Palm Pixi", new BigDecimal(170), usd, 30, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(170), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(180), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(230), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        arrayListProductDao.save(new Product("11L", "Siemens C56", new BigDecimal(70), usd, 20, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(70), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(80), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(120), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg"));
        arrayListProductDao.save(new Product("12L", "Siemens C61", new BigDecimal(80), usd, 30, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(80), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(90), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(130), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg"));
        arrayListProductDao.save(new Product("13L", "Siemens SXG75", new BigDecimal(150), usd, 40, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(150), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(160), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(200), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}
