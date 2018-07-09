package com.tencent;


import com.tencent.commons.web.context.BeanProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author bobzbfeng
 */
@ContextConfiguration(locations = {"classpath:/spring/*.xml"}, initializers = {TestApplicationContextInitializer.class})
public abstract class ContextTest extends AbstractTestNGSpringContextTests {


    protected void initBeanProvider() {
        BeanProvider.initialize(this.applicationContext);
    }


}