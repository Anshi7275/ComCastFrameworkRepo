package extentReport_practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.HomePage;

public class CreateContactWithDateTest extends BaseClass{
	
	@Test
	public void createContactWithDate() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel file");
		String contactLastname = elib.getDatafromExcelfle("contact", 4, 2) + jlib.getRandomNumber();

		wlib.waitPageLoad(driver);

		// navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "create contact");
		// click on "create contact" button
		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "enter all details");
		// Enter all the details and create new contact
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastname);

		String startDate = jlib.getSystemdate();
		String endDate = jlib.getRequiredDate(30);

		ccp.getStartdatetext().clear();
		ccp.getStartdatetext().sendKeys(startDate);

		ccp.getEnddatetxt().clear();
		ccp.getEnddatetxt().sendKeys(endDate);
		ccp.getSavebtn().click();

		// verifcation header msg with actual header msg via hard assert
		ContactInfoPage ci = new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastname);

		Assert.assertEquals(headermsg, true);
		System.out.println("===Contact Header verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Contact header verified!!!");

		// verifcation StartDate msg with actual StartDate msg via hard assert
		String actStartdate = ci.getStartDate().getText();
		boolean StartDateMsg = actStartdate.contains(startDate);
		Assert.assertTrue(StartDateMsg);
		System.out.println("===Start date verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Start date verified!!!");

		// verifcation EndDate msg with actual EndDate msg via hard assert
		String actendDate = ci.getEnddate().getText();
		boolean EndDateMsg = actendDate.contains(endDate);

		Assert.assertEquals(EndDateMsg, true);
		System.out.println("===End date verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "End date verified!!!");
	}

}
