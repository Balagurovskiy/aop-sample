package aop.dinamic_proxy;

public class AnotherTarget implements CustomProxy {

	@Override
	public void make(int value) {
		System.out.println("\twow Im another");
	}

	@Override
	public void rest(int value) {
	}

}
