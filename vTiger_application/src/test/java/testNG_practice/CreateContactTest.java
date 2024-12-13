package testNG_practice;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.HomePage;

import listeners_Practice.Listeners_retryAnalyzer;

public class CreateContactTest extends BaseClass {
	@Test(groups = "smokeTest")
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
		
		//verifcation header msg with actual header msg
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		if(actheader.contains(contactLastName))
		{
			System.out.println(contactLastName+ " verified with actual msg!!!!");
		}
		else
		{
			System.out.println(contactLastName+" is not verified with actual msg!!!");
		}
		
	}

}
