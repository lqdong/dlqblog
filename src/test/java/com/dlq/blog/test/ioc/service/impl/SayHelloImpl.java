package com.dlq.blog.test.ioc.service.impl;

import com.dlq.blog.test.ioc.service.ISayHello;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author donglq
 * @date 2017/10/6 14:41
 */
@Component(value = "sayHello")
public class SayHelloImpl implements ISayHello {

    @PostConstruct
    public void init() {
        Thread hook = new Thread() {
            @Override
            public void run() {
                System.err.println("stop the jvm");
            }
        };
        Runtime.getRuntime().addShutdownHook(hook);
    }

    @Override
    public void sayHello() {
        System.out.println("hello donglq");
    }
}
