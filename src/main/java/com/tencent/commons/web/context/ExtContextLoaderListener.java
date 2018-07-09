package com.tencent.commons.web.context;


import com.tencent.commons.utils.IOTHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 15-6-22
 *
 * @author bobzbfeng
 */
public class ExtContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        final ServletContext servletContext = event.getServletContext();

        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        BeanProvider.initialize(applicationContext);

        contextSystemAttributes(servletContext);
    }

    private void contextSystemAttributes(ServletContext servletContext) {
        servletContext.setAttribute("iotVersion", IOTHolder.VERSION);
    }
}
