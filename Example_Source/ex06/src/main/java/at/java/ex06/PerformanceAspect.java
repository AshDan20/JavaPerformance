package at.java.ex06;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(PerformanceAspect.class);

	@Around("execution(* at.java..*.*(..))")
	public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();

		long duration = System.currentTimeMillis() - start;
		if (duration > 100) {
			String targetClass = pjp.getTarget().getClass().getName();
			String method = pjp.getSignature().getName();
			String msg =
					String.format("PERFORMANCE ALERT: duration: %d Target: %s.%s", duration, targetClass, method);
			log.warn(msg);
		}

		return result;
	} 
}
