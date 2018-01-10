package com.centling.config;


import com.centling.utils.DateUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Order(1)
public class GlobalLogAspectConfiguration {

    @Around("execution (* com.centling.service.*.*(..))")
    public Object globalLogBack(ProceedingJoinPoint pjp) throws Throwable {
        Object obj = new Object();
        Throwable ex = null;
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        msig = (MethodSignature) sig;

        long start = 0L;
        long end = 0L;
        try{
            start = System.currentTimeMillis();
            obj = pjp.proceed();
            end = System.currentTimeMillis();

            System.out.println(DateUtil.sdf_datetime_format.format(new Date())+"调用的类名称为：" + pjp.getTarget().getClass()
                    + "," + "调用的方法名为：" + msig.getMethod().getName() +
                    ",耗时为" + (end-start) + "ms");
        }catch (Throwable e) {
            ex = e;
            System.out.println(e);
        }finally{
            if(ex == null) {
                return obj;
            }else {

                System.out.println("调用的类名称为：" + pjp.getTarget().getClass()
                        + "," + "调用的方法名为：" + msig.getMethod().getName() +
                        ",耗时为" + (end-start) + "ms");

                throw ex;
            }
        }
    }
}
