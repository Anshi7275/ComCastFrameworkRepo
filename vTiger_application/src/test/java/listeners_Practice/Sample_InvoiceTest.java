package listeners_Practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)

public class Sample_InvoiceTest extends BaseClass{
	@Test
	
	public void createInvoiceTest() {
		System.out.println("execute create Invoice Test");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Login");
		
		System.out.println("==step-1===");
		System.out.println("==step-2===");
		System.out.println("==step-3===");
		System.out.println("==step-4===");
		
	}
	
	@Test
	public void createInvoiceWithContact() {
		System.out.println("execute create invoice with contact");
		System.out.println("execute create Invoice Test");
		System.out.println("==step-1===");
		System.out.println("==step-2===");
		System.out.println("==step-3===");
		System.out.println("==step-4===");
		
	}

}
