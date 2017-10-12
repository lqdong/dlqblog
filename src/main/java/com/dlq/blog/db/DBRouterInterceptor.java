package com.dlq.blog.db;

import com.dlq.blog.db.annotation.Router;
import com.dlq.blog.db.interfaces.DBRouter;
import com.dlq.blog.db.interfaces.DBRule;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * AOP拦截器：根据方法参数中的某个字段来判断这条记录是存在几库几表
 * @author donglq
 * @date 2017/10/3 23:37
 */
@Aspect
@Service
public class DBRouterInterceptor {

    @Autowired
    private DBRouter dBRouter;

    @Autowired
    private DBApplicationContext dbApplicationContext;

    @Pointcut("@annotation(com.dlq.blog.db.annotation.Router)")
    public void aopPoint() {
    }

    @Before("aopPoint()")
    public Object doRoute(JoinPoint jp) throws Throwable {
        System.out.println("aop before");
        boolean result = true;
        //根据JoinPoint jp 获取方法名称和参数
        Method method = getMethod(jp);
        //获取注解
        Router router = method.getAnnotation(Router.class);
        //做路由的字段
        String routeField = router.routerField();
        //规则bean名称
        String ruleBeanName = router.ruleBeanName();
        DBRule dbRule = dbApplicationContext.getApplicationContext().getBean(ruleBeanName, DBRule.class);
        Object[] args = jp.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                //通过反射得到对象args[i] 的 routeField 字段的值
                String routeFieldValue = BeanUtils.getProperty(args[i], routeField);
                if (StringUtils.isNotEmpty(routeFieldValue)) {
                    dBRouter.doRouteByResource(dbRule ,routeFieldValue);
                    break;
                }
            }
        }
        return result;
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

}
