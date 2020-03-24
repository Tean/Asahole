package com.netteans.examples.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DefaultReturnAspect {
    private static final Logger logger = LoggerFactory.getLogger(DefaultReturnAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void controller() {
        logger.info("sub controller: {}", this);
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
        logger.info("sub requestmapping: {}", this);
    }

    @Pointcut("execution(* com.netteans.*.controller.*.*(..))")
    public void methodPointcut() {
        logger.info("sub aop: {}", this);
    }

    @Before("requestMapping() && methodPointcut()")
    public void before(JoinPoint joinPoint) {
        logger.debug("before aop: {}", joinPoint);
    }

    @Around("requestMapping() && methodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("around aop: {}", proceedingJoinPoint);
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After("requestMapping() && methodPointcut()")
    public void after(JoinPoint joinPoint) {
        logger.debug("after aop: {}", joinPoint);
    }

    @AfterReturning("requestMapping() && methodPointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        logger.debug("afterReturning aop: {}", joinPoint);
    }

    @AfterThrowing(value = "requestMapping() && methodPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        logger.debug("after throwing aop: {}", joinPoint, ex);
    }
}
