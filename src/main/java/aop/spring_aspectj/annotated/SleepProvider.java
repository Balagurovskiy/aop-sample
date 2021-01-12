package aop.spring_aspectj.annotated;

import org.springframework.stereotype.Component;

@Component
public class SleepProvider {
	
	@TimerWithMessages(
			startMsg = "Start sleeping.",  
			closeMsg = "Rest comes to the end."
			)
	public void rest(int value) {
		try {
			System.out.println("\tOh that pillow is awesome!");
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@TimerWithMessages(
			startMsg = "Short period of sleep, typically taken during daytime.",  
			closeMsg = "Have to end about after 500ms."
			)
	public void nap() {
		try {
			System.out.println("\tQuick nap");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
