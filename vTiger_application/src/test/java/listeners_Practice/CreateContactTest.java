package listeners_Practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.HomePage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateContactTest extends BaseClass 
{
	@Test(groups = "smokeTest", retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createContactTest() throws Throwable {
		
		//read the data from excel
		String contactLastName = elib.getDatafromExcelfle("contact", 1, 2)+jlib.getRandomNumber();
		
		//navigate to contact module
		HomePage hp=new HomePage(driver);
		hp.getContactlnk().click();
		
		//click on "create contact" button
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		//Enter all the details and create new contact
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastName);
		ccp.getSavebtn().click();
		
		//verifcation header msg with actual header msg by hardAssert
		ContactInfoPage ci = new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastName);

		Assert.assertEquals(headermsg, true);
		System.out.println("==header verified!!!===");

		String actLastName = ci.getProductmsg().getText();
		boolean lastNameMsg = actLastName.contains(contactLastName);

		Assert.assertEquals(lastNameMsg, true);
		System.out.println("==LastName textFeild verified!!!===");	
	}

}
