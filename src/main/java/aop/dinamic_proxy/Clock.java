package aop.dinamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Clock implements InvocationHandler{
    private final Object _target;
    public Clock(Object target) {
    	_target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	long _time = System.currentTimeMillis();
    	
    	String argsStr = convertToStrLine(args);
    	
    	String currentTargetStr = getTargetInfo(method);
    	
    	System.out.println("\n<" +  currentTargetStr + " | args :" + argsStr + ">");
        method.invoke(_target, args);
        System.out.println("</" + currentTargetStr + " | Time : " + (System.currentTimeMillis() - _time) + ">\n");
        return proxy;
    }
    
    private String getTargetInfo(Method method) {
    	return method.getClass().getSimpleName() + "." + method.getName() + "(..)";
    }
    
    private String convertToStrLine(Object[] args) {
    	String argsStr = "";
    	int i = 0;
    	for (Object a : args) {
    		
    		argsStr += a.toString();
    		i++;
    		if (i != args.length) {
    			argsStr += ", ";
    		}
    	}
    	return argsStr;
    }
}
