package com.netteans.log.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DefaultOperator {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOperator.class);

    //    @Pointcut("execution(** com.netteans..*.*(..))")
    //    @Pointcut("@within(org.springframework.stereotype.Controller)")
    @Pointcut("@annotation(com.netteans.log.annotation.RequestLog)")
    //    @Pointcut("execution(** com.netteans..*.*(..)) && @within(org.springframework.stereotype.Controller) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void injectpointcut() {
        logger.info("init pointcut {}", this);
    }

//    /**
//     * 目标方法执行之前调用
//     */
//    @Before("injectpointcut()")
//    public void before(JoinPoint joinPoint) {
//        logger.error("aspectj before with param {}", joinPoint);
//    }
//
//    /**
//     * 目标方法执行完后调用
//     */
//    @After("injectpointcut()")
//    public void after(JoinPoint joinPoint) {
//        logger.error("aspectj after with param {}", joinPoint);
//    }

    /**
     * 目标方法发生异常时调用
     */
    @AfterThrowing("injectpointcut()")
    public void thrown(JoinPoint joinPoint) {
        logger.error("aspectj thrown with param {}", joinPoint);
    }

    @Around("injectpointcut()")
    public void around(ProceedingJoinPoint joinPoint) {
        logger.error("aspectj around with param {}", joinPoint);
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
