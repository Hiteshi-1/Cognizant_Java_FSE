package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect – Cross-cutting logging concern for the Library Management application.
 *
 * Exercise 3 : Logs method execution time using @Around advice
 * Exercise 8 : Adds @Before and @After advice for comprehensive AOP demonstration
 */
@Aspect
@Component
public class LoggingAspect {

    // Pointcut: every method in com.library package (service, repository, etc.)
    @Pointcut("execution(* com.library.*.*(..))")
    public void libraryPointcut() {}

    // -------------------------------------------------------
    // Exercise 8 – @Before Advice
    // -------------------------------------------------------

    /**
     * Runs BEFORE the matched method executes.
     */
    @Before("libraryPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP - BEFORE] Entering method: "
                + joinPoint.getSignature().toShortString());
    }

    // -------------------------------------------------------
    // Exercise 8 – @After Advice
    // -------------------------------------------------------

    /**
     * Runs AFTER the matched method executes (whether or not it throws).
     */
    @After("libraryPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP - AFTER]  Exiting method: "
                + joinPoint.getSignature().toShortString());
    }

    // -------------------------------------------------------
    // Exercise 3 – @Around Advice (execution time)
    // -------------------------------------------------------

    /**
     * Wraps the matched method to measure and log execution time.
     */
    @Around("libraryPointcut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();   // execute the actual method

        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("[AOP - AROUND] Method: "
                + proceedingJoinPoint.getSignature().toShortString()
                + " executed in " + timeTaken + " ms");

        return result;
    }

    // -------------------------------------------------------
    // Exercise 8 – @AfterReturning Advice
    // -------------------------------------------------------

    /**
     * Runs only when the matched method returns successfully.
     */
    @AfterReturning(pointcut = "libraryPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP - AFTER RETURNING] Method "
                + joinPoint.getSignature().toShortString()
                + " returned: " + result);
    }
}
