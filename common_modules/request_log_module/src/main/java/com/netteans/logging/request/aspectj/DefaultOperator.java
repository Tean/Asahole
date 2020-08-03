package com.netteans.logging.request.aspectj;

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

    /**
     *   @Pointcut("execution(** com.netteans..*.*(..))") //切入com.netteans及其子包任意参数以及返回值的方法
     *   @Pointcut("@within(org.springframework.stereotype.Controller)") //切入包含org.springframework.stereotype.Controller注解的类的方法
     *   @Pointcut("@annotation(com.netteans.logging.request.annotation.RequestLog)") //切入带有com.netteans.logging.request.annotation.RequestLog注解的方法
     *   @Pointcut("execution(** com.netteans..*.*(..)) && @within(org.springframework.stereotype.Controller) && @annotation(org.springframework.web.bind.annotation.RequestMapping)") //&& || 逻辑操作
     */
    @Pointcut("@annotation(com.netteans.logging.request.annotation.RequestLog) || @within(com.netteans.logging.request.annotation.RequestLog)")
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
    public Object around(ProceedingJoinPoint joinPoint) {
        logger.error("aspectj around with param {}", joinPoint);
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("{}", throwable);
            return null;
        }
    }
}
