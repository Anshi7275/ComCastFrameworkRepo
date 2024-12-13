package testNG_practice;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.HomePage;

public class CreateContactWithDateTest extends BaseClass{
	
	@Test(groups = "regressionTest")
	public void createContactWithDate() throws Throwable {
		String contactLastname = elib.getDatafromExcelfle("contact", 4, 2) + jlib.getRandomNumber();

		wlib.waitPageLoad(driver);

		// navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// click on "create contact" button
		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();

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

		// verifcation header msg with actual header msg
		ContactInfoPage ci = new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		if (actheader.contains(contactLastname)) {
			System.out.println(contactLastname + " verified with actual msg!!!!");
		} else {
			System.out.println(contactLastname + " is not verified with actual msg!!!");
		}

		String actStartdate = ci.getStartDate().getText();
		if (actStartdate.contains(startDate)) {
			System.out.println(startDate + " is verified msg!!!");
		} else {
			System.out.println(startDate + " is not verified msg!!");
		}

		String actendDate = ci.getEnddate().getText();
		if (actendDate.contains(endDate)) {
			System.out.println(endDate + " is verified msg!!!");
		} else {
			System.out.println(endDate + " is not verified msg!!");
		}
	}

}
