package com.dlq.blog.test.ioc.process;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 后置处理
 * @author donglq
 * @date 2017/10/6 21:07
 */
@Component
public class DlqBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beforeInitialization: " + bean + " = " + JSON.toJSONString(bean));
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("afterInitialization: " + bean + " = " + JSON.toJSONString(bean));
        return bean;
    }
}
