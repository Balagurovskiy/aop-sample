package aop.dinamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static Object createProxy(Class c,  InvocationHandler h) {
		return   Proxy.newProxyInstance(
						c.getClassLoader(),
		                new Class[] { c },
		                h
		            );
	}
	public static void main(String[] args) {
		
	    Clock clock = new Clock(new Target());
	    Clock clockAnother = new Clock(new AnotherTarget());
	    
	    CustomProxy target = (CustomProxy) createProxy(CustomProxy.class, clock);
	    target.make(1000);
	    target.rest(2000);
	    
	    CustomProxy targetAnother = (CustomProxy) createProxy(CustomProxy.class, clockAnother);
	    targetAnother.make(666);
	}

}
