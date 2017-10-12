package com.dlq.blog.db;

import com.dlq.blog.db.annotation.DBWR;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 读写分离路由
 * @author donglq
 * @date 2017/10/5 1:57
 */
@Aspect
@Service
public class DBWRRoute {

    @Pointcut("@annotation(com.dlq.blog.db.annotation.DBWR)")
    public void aopPoint() {
    }

    @Before("aopPoint()")
    public Object doRoute(JoinPoint jp) throws Throwable {
        //根据JoinPoint jp 获取方法名称和参数
        Method method = getMethod(jp);
        //获取注解
        DBWR dbwr = method.getAnnotation(DBWR.class);
        DBContext.setDbKey(dbwr.value().name);
        return null;
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

}
