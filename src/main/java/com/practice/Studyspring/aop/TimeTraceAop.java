package com.practice.Studyspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop  {

    @Around("execution(* com.practice.Studyspring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        System.out.println("Start:" +startTime);
        try {
            return joinPoint.proceed();

        }finally {
            long finishTime = System.currentTimeMillis();
            long timeMs = finishTime - startTime;
            System.out.println("END:" +joinPoint.toShortString()+" "+timeMs+"ms");
        }
    }

}
