package aop.spring_aspectj.named;

import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageProviderAspect {

	private long time;
	
	private boolean opened;
	
    private String whoIsIt(JoinPoint joinPoint) {
    	return joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
    }
    
    @Before("execution(* MessageProvider.*(..))")
    public void beforeAnyMethod(JoinPoint joinPoint) 
    {
    	time = System.currentTimeMillis();
    	opened = true;
    	
    	StringBuilder builder = new StringBuilder();
    	builder.append("\n");
    	builder.append("< Starting ... ");
    	builder.append( whoIsIt(joinPoint));
    	builder.append(" >\n");
        System.out.println(builder.toString());
    }
    
    @After("execution(* MessageProvider.*(..))")
    public void afterAnyMethod(JoinPoint joinPoint) 
    {
    	/*
    	 * Bad part ! Link after and before advises
    	 * @before don't work if method fails
    	 * but
    	 * @after works ((
    	 * 
    	 */
    	if (opened == false) {
    		return ;
    	}
    	opened = false;
    	
    	StringBuilder builder = new StringBuilder();
    	builder.append("\n");
    	builder.append("< Closing ... ");
    	builder.append( whoIsIt(joinPoint));
    	builder.append(" | ");
    	builder.append(" Time taken: ");
    	builder.append( (System.currentTimeMillis() - time) );
    	builder.append(" ms >\n");
        System.out.println(builder.toString());
    }
    
    
    @Pointcut("execution(* MessageProvider.printCollection(..)) && args(data,..))")
    public <T> void callPrintCollectionMethod(Collection<T> data) { }

    @Around("callPrintCollectionMethod(data)")
    public void throwOnNull(ProceedingJoinPoint proceedingJoinPoint, Collection<?> data) throws Throwable {
    	if (data == null) {
    		System.out.println("FAIL ! NULL ARGUMENT IN " + whoIsIt(proceedingJoinPoint));
			throw new IllegalArgumentException();
		} else if (data.size() == 0){
			System.out.println("WARN ! EMPTY ARGUMENT IN " + whoIsIt(proceedingJoinPoint));
		} else {
			proceedingJoinPoint.proceed();
			System.out.println("OK ! " + whoIsIt(proceedingJoinPoint));
		}
    }	
}
