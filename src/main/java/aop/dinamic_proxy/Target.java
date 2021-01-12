package aop.dinamic_proxy;

public class Target implements CustomProxy{

	public void make(int value) {
		System.out.println("\tDoing some work... ");
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void rest(int value) {
		System.out.println("\tZzzz ");
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
