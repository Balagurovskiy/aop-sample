package aop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import aop.spring_aspectj.App;
import aop.spring_aspectj.annotated.SleepProvider;
import aop.spring_aspectj.named.MessageProvider;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = App.class)
//@ContextConfiguration(locations = "/applicationContext.xml")
@ContextConfiguration(classes = {App.class})
public class SuperTest {
	
	
    @Autowired
    private MessageProvider _messageProvider;
    @Autowired
    private SleepProvider _sleepProvider;

    @Test
    public void messageProvider_TimerOnEveryMethodTest() {
    	System.out.println("\n\n * * * * * * * * * * * * * * * ");
    	System.out.println(" * MessageProvider Aspects Test");
    	System.out.println(" * Pointcut On Every Class Method");
    	System.out.println(" * 1) Print 'Start' Message");
    	System.out.println(" * 2) Proceede Method 'anotherMethod()' And Get A message From It");
    	System.out.println(" * 3) Print 'Close' Message And Time Taken On Execution");
    	_messageProvider.anotherMethod();
    	assertThat(_messageProvider).isNotNull();
    }
    /*
     * Catch exception from aspect
     */
    @Test(expected = IllegalArgumentException.class)
    public void messageProvider_NullValidationTest() {
    	System.out.println("\n\n * * * * * * * * * * * * * * * ");
    	System.out.println(" * MessageProvider Aspects Test");
    	System.out.println(" * Pointcut On 'printCollection(..)' Method");
    	System.out.println(" * 1) No 'Start' Message");
    	System.out.println(" * 2) Proceede Method 'printCollection()' With NULL Argument");
    	System.out.println(" * 3) Receive Message From Validation Advice About FAIL");
    	System.out.println(" * 4) No 'Close' Message And Time Taken On Execution");
    	_messageProvider.printCollection( null );
    }
    @Test
    public void messageProvider_EmptyValidationTest() {
    	System.out.println("\n\n * * * * * * * * * * * * * * * ");
    	System.out.println(" * MessageProvider Aspects Test");
    	System.out.println(" * Pointcut On 'printCollection(..)' Method");
    	System.out.println(" * 1) No 'Start' Message");
    	System.out.println(" * 2) Proceede Method 'printCollection()' With Empty Argument");
    	System.out.println(" * 3) Receive Message From Validation Advice About WARN");
    	System.out.println(" * 4) No 'Close' Message And Time Taken On Execution");
    	_messageProvider.printCollection( new ArrayList<String>() );
    }
    @Test
    public void messageProvider_ValidTest() {
    	System.out.println("\n\n * * * * * * * * * * * * * * * ");
    	System.out.println(" * MessageProvider Aspects Test");
    	System.out.println(" * Pointcut On 'printCollection(..)' Method");
    	System.out.println(" * 1) Print 'Start' Message");
    	System.out.println(" * 2) Proceede Method 'printCollection()' With Valid Argument");
    	System.out.println(" * 3) Receive Message From Validation Advice About OK");
    	System.out.println(" * 4) Print 'Close' Message And Time Taken On Execution");
    	_messageProvider.printCollection( Stream.generate(String::new)
    													.limit(5)
    													.map(s -> s = "some strings")
    													.collect(Collectors.toList()) 
    									);
    	assertThat(_messageProvider).isNotNull();
    }
    @Test
    public void sleepProvider_Test() {
    	System.out.println("\n\n * * * * * * * * * * * * * * * ");
    	System.out.println(" * SleepProvider Aspects Test");
    	System.out.println(" * Pointcut On 'TimerWithMessages' Annotation");
    	System.out.println(" * 1) Print 'Start' Message");
    	System.out.println(" * 2) Proceede Method");
    	System.out.println(" * 3) Print Time Taken On Execution");
    	System.out.println(" * 4) Print 'Close' Message");
    	_sleepProvider.rest(1);
    	System.out.println("\n\n");
    	_sleepProvider.nap();
    	assertThat(_sleepProvider).isNotNull();
    }
}
