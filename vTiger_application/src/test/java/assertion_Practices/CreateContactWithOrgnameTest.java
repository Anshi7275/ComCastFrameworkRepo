package assertion_Practices;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

public class CreateContactWithOrgnameTest extends BaseClass {

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {

		wlib.waitPageLoad(driver);

		String orgName = elib.getDatafromExcelfle("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDatafromExcelfle("contact", 7, 3) + jlib.getRandomNumber();

		// navigate to orgnization
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();

		// click on "create org" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();

		// enter all the details and save
		CreateNewOrganizationPage co = new CreateNewOrganizationPage(driver);
		co.getOrgName().sendKeys(orgName);
		co.getSave().click();

		//verification of orgnameHeader by hardAssert
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		
		// navigate to contact
		hp.getContactlnk().click();

		// click on create contact button
		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();

		// enter all the details and save
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastName);

		ccp.getOrgIcon().click();

		wlib.switchToTabOnURL(driver, "Accounts&action");

		// switch to org page
		OrganizationWindowPage ow = new OrganizationWindowPage(driver);
		ow.getSearchtxt().sendKeys(orgName);
		ow.getSearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wlib.switchToTabOnURL(driver, "Contacts&action");

		ccp.getSavebtn().click();

		//verification of contact header using hard assert
		ContactInfoPage ci = new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean Header = actheader.contains(contactLastName);

		assertEquals(Header, true);
		System.out.println("===Contact header verified!!!===");

		// verification of org name via hard assert
		String actOrgname = ci.getOrgName().getText();
		boolean orgnameMsg = actOrgname.contains(orgName);

		assertTrue(orgnameMsg);
		System.out.println("===Org Name textFeild verified!!!===");
		

	}

}
