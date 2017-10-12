package com.dlq.blog.test.ioc;

import com.dlq.blog.test.ioc.service.ISayHello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc测试入口
 * @author donglq
 * @date 2017/10/6 14:38
 */
public class SpringIoCMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ioc/ioc-context.xml");
        ISayHello sayHello = ctx.getBean("sayHello", ISayHello.class);
        sayHello.sayHello();
    }

}
