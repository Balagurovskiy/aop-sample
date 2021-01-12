package aop.spring_aspectj.named;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class MessageProvider {
	public MessageProvider() {
	}
	
	public void printCollection(Collection<?> data) {
		System.out.println("Messages :");
		data.parallelStream().forEach(System.out::println);
	}
	
	public void anotherMethod() {
		System.out.println("Another method was executed .");
	}
}
