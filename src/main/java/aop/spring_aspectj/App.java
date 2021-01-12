package aop.spring_aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"aop"})
@EnableAspectJAutoProxy
public class App{
	   
}


//@SpringBootApplication
//public class App{
//	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
//	}
//	   
//}
//public class App{
//  public static void main(String[] args) {
//  	 
//      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//      MessageProvider provider = context.getBean(MessageProvider.class);
//  }
//}