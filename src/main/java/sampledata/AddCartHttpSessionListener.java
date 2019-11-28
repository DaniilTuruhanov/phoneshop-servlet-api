package sampledata;

import com.es.phoneshop.cart.Cart;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AddCartHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("cart", new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}
