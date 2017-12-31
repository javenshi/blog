package com.centling.controller.aop;

import com.centling.annotation.DataFilterFlag;

import com.centling.utils.Constants;
import com.centling.utils.mybatis.DataFilterHelper;
import com.centling.utils.mybatis.FilterEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Aspect
@Component
public class DataFilterAop {
    private final static Logger logger = LoggerFactory.getLogger(DataFilterAop.class);
    //@Autowired
   // private DataFilterRuleService dataFilterRuleService;


    @Pointcut(value = "execution(* com.centling.controller..*.*(..)) && @annotation(com.centling.annotation.DataFilterFlag)")
    private void pointcut() {
    }

   /* @Around("pointcut() && args(request,..) && @annotation(dataFilterFlag)")*/
   /* public Object before(ProceedingJoinPoint joinPoint, DataFilterFlag dataFilterFlag, HttpServletRequest request) throws Throwable {
       //获取方法注解key值
        String name = dataFilterFlag.key();
        List<DataFilterRule> rules = dataFilterRuleService.listByMenuName(name);
        List<FilterEntity> filterEntities = rules
                .stream()
                .map(temp->{
                    FilterEntity fileEntity = new FilterEntity();
                    fileEntity.setColumn(temp.getRuleColumn());
                    fileEntity.setValue(temp.getRuleValue());
                    fileEntity.setExpression(temp.getRuleConditions());
                    return fileEntity;
                })
                .collect(toList());

        request.setAttribute(Constants.DataFilter.DATA_FILTER_WHERE, filterEntities);

        if(logger.isInfoEnabled()){
            logger.info("数据过滤->[执行方法:{},过滤条件:{}]",getFullMethodName(joinPoint),filterEntities);
        }
        Object object = joinPoint.proceed();
        logger.info("数据过滤->[执行方法:{},清除过滤参数]",getFullMethodName(joinPoint));
        DataFilterHelper.clearFilterEntity();
        return object;
    }
*/
    private String getFullMethodName(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getDeclaringClass().getName() + "." +method.getName();
        return methodName;
    }
}
