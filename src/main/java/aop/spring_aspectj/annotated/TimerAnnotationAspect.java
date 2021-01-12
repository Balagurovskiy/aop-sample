package aop.spring_aspectj.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAnnotationAspect {
    @Pointcut("@annotation(TimerWithMessages)")
    public void callTimerAnnotation() { }

    @Around("callTimerAnnotation()")
    public Object aroundCallAt(ProceedingJoinPoint proceedingJoinPoint) {
    	String _start = "";
    	String _end = "";
    	
    	Class currentClass = proceedingJoinPoint.getTarget().getClass();
    	String currentMethodName = proceedingJoinPoint.getSignature().getName();
    	
    	Optional<TimerWithMessages> _timer = getTimerAnnotation(currentClass , currentMethodName);
    	if (_timer.isPresent()) {
    		_start = _timer.get().startMsg();
    		_end = _timer.get().closeMsg();
    	}
    	try {
    		
    		System.out.println(_start);
    		long _currentMills = System.currentTimeMillis();
			proceedingJoinPoint.proceed();
			printTimeStamp(
						currentClass.getName(), 
						currentMethodName, 
						(System.currentTimeMillis() - _currentMills) + " ms"
						);
			System.out.println(_end);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
    	return null;
    }
    private void printTimeStamp(String className, String methodName, String time) {
    	StringBuilder builder = new StringBuilder();
    	builder.append("{ Source : ");
    	builder.append(className);
    	builder.append(".");
    	builder.append(methodName);
    	builder.append(" ; ");
    	builder.append("time : ");
    	builder.append(time);
    	builder.append(" }");
        System.out.println(builder.toString());
    }
    private Optional<TimerWithMessages> getTimerAnnotation(Class classWithTimer, String aroundMethodName) {
    	
    	Annotation annotation = null;
    	TimerWithMessages timer = null;
    	for (Method method : classWithTimer.getMethods()) {
    		if (method.getName().equals(aroundMethodName)) {
        		annotation = method.getAnnotation( TimerWithMessages.class );
    			if(annotation instanceof TimerWithMessages){
    	    		timer = (TimerWithMessages) annotation;
    	    	}
    			break ;
    		}
    	}
    	return Optional.ofNullable(timer);
    }
}
