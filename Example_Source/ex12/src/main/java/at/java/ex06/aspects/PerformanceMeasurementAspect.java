package at.java.ex06.aspects;


import at.java.ex06.monitoring.CompassHealthIndicator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Profile("performance")
public class PerformanceMeasurementAspect {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PerformanceMeasurementAspect.class);
    @Value("${performance.threshold.ms}")
    private long performanceThreshold;

    @Autowired
    private CompassHealthIndicator healthIndicator;

    @Around("execution(* at.java.ex06.service..*.*(..)) || " +
            "execution(* at.java.ex06.repository..*.*(..))")

    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long duration = System.currentTimeMillis() - start;
        healthIndicator.reportDuration(duration);
        if (duration >= performanceThreshold){
            String methodName = pjp.getSignature().toLongString();
            String args = Arrays.toString(pjp.getArgs());
            String classname = pjp.getTarget().getClass().getName();
            String msg = String.format("PERFORMANCE ALERT duration %d>%d ms, %s(%s)",
                                duration, performanceThreshold, methodName, args);
            log.warn(msg);
        }
        return result;
    }
}
