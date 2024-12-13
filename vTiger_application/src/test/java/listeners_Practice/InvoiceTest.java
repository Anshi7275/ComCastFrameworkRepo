package listeners_Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void activateTest() {
		System.out.println("execute===");
		
		Assert.assertEquals("", "Home");
		
		System.out.println("===step:1===");
		System.out.println("===step:2===");
		System.out.println("===step:3===");
		
		//it will try 5 times and after on 6th time it will through error 
	}

}
