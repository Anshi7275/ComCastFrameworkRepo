package com.crmVTiger.moduleTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
/**
 * Test script for Create contact module
 * @author Anshika
 */
public class CreateContactTest extends BaseClass {
	
	/**
	 * Scenarios 1: Login()--> navigate to contact-->create new contact-->verify
	 * Scenarios 2: Login()--> navigate to contact-->create new contact with system date and required date-->verify
	 * Scenarios 3: Login()--> navigate to contact-->create new contact with OrgName-->verify
	 *     precondition: atleast one orgName should be created
	 * @throws Throwable
	 */
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {
		
		/*read the data from excel*/
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel file");
		String contactLastName = elib.getDatafromExcelfle("contact", 1, 2)+jlib.getRandomNumber();
		
		/*navigate to contact module*/
		UtilityClassObject.getTest().log(Status.INFO, "login--->navigate to contact page!!!");
		HomePage hp=new HomePage(driver);
		hp.getContactlnk().click();
		
		/*click on "create contact" button*/
		UtilityClassObject.getTest().log(Status.INFO, "create contact!!!");
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		/*Enter all the details and create new contact*/
		UtilityClassObject.getTest().log(Status.INFO, "craete new contact!!!");
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastName);
		
		/* save all the details*/
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ccp.getSavebtn().click();
		
		/*verifcation header msg with actual header msg by hardAssert*/
		UtilityClassObject.getTest().log(Status.INFO, "Start verification of Contact!!!");
		ContactInfoPage ci=new ContactInfoPage(driver);
		
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastName);
		
		Assert.assertEquals(headermsg, true);
		UtilityClassObject.getTest().log(Status.PASS, "header verified!!!");
		
		/*verification of lastname*/
		String actLastName = ci.getProductmsg().getText();
		boolean lastNameMsg = actLastName.contains(contactLastName);
		
		Assert.assertEquals(lastNameMsg, true);
		UtilityClassObject.getTest().log(Status.PASS, "LastName textFeild verified!!!");
		
		UtilityClassObject.getTest().log(Status.INFO, "New contact created!!!");
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithDate() throws Throwable
	{
		/*Read data from excel*/
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel file");
		String contactLastname = elib.getDatafromExcelfle("contact", 4, 2)+jlib.getRandomNumber();
		
		wlib.waitPageLoad(driver);
		
		/*navigate to contact module*/
		HomePage hp=new HomePage(driver);
		hp.getContactlnk().click();
		
		/*click on "create contact" button*/
		UtilityClassObject.getTest().log(Status.INFO, "create new contact");
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		/*Enter all the details and create new contact*/
		UtilityClassObject.getTest().log(Status.INFO, "Enter all the details");
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastname);
		
		/*Enter start date and end date*/
		String startDate = jlib.getSystemdate();
		String endDate = jlib.getRequiredDate(30);
		
		ccp.getStartdatetext().clear();
		ccp.getStartdatetext().sendKeys(startDate);
		
		ccp.getEnddatetxt().clear();
		ccp.getEnddatetxt().sendKeys(endDate);
		
		/*Save all the details*/
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ccp.getSavebtn().click();
		
		/*verifcation header msg with actual header msg via hard assert*/
		
		UtilityClassObject.getTest().log(Status.INFO, "Start verification of Contact!!!");
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastname);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Contact Header verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Contact Header verified!!!");
		
		/*verifcation StartDate msg with actual StartDate msg via hard assert*/
		String actStartdate = ci.getStartDate().getText();
		boolean StartDateMsg = actStartdate.contains(startDate);
		Assert.assertTrue(StartDateMsg);
		System.out.println("===Start date verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Start date verified!!!");
		
		/*verifcation EndDate msg with actual EndDate msg via hard assert*/
		String actendDate = ci.getEnddate().getText();
		boolean EndDateMsg = actendDate.contains(endDate);
		
		Assert.assertEquals(EndDateMsg, true);
		System.out.println("===End date verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "End date verified!!!");
		
		UtilityClassObject.getTest().log(Status.INFO, "New contact with date created!!!");
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		
		wlib.waitPageLoad(driver);
		
		/*Read data from excel*/
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel file");
		String orgName = elib.getDatafromExcelfle("contact", 7, 2)+jlib.getRandomNumber();
		String contactLastName = elib.getDatafromExcelfle("contact", 7, 3)+jlib.getRandomNumber();
		
		/*navigate to orgnization*/
		UtilityClassObject.getTest().log(Status.INFO, "Login----> navigate to OrgName");
		HomePage hp=new HomePage(driver);
		hp.getOrganization().click();
		
		/*click on "create org" button*/
		UtilityClassObject.getTest().log(Status.INFO, "create new orgName");
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreate_org().click();
		
		/*enter all the details and save*/
		UtilityClassObject.getTest().log(Status.INFO, "enter all details of org");
		CreateNewOrganizationPage co=new CreateNewOrganizationPage(driver);
		co.getOrgName().sendKeys(orgName);
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		co.getSave().click();
		
		/*verification of orgnameHeader by hardAssert*/
		UtilityClassObject.getTest().log(Status.INFO, "OrgName verification started!!!");
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "orgName verified!!!");
		
		/*navigate to contact */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact");
		hp.getContactlnk().click();
		
		/*click on create contact button*/
		UtilityClassObject.getTest().log(Status.INFO, "create new contact");
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		/*enter all the details and save*/
		UtilityClassObject.getTest().log(Status.INFO, "enter all details of contact");
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastName);
		
		/*click on add org option*/
		ccp.getOrgIcon().click();
		
		/*Switch to orgname page*/
		UtilityClassObject.getTest().log(Status.INFO, "Switch to OrgName page");
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		OrganizationWindowPage ow = new OrganizationWindowPage(driver);
		ow.getSearchtxt().sendKeys(orgName);
		ow.getSearchbtn().click();
		
		/*Select created orgname*/
		UtilityClassObject.getTest().log(Status.INFO, "select the created OrgName");
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		/*Switch back to contact page*/
		wlib.switchToTabOnURL(driver, "Contacts&action");
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ccp.getSavebtn().click();	
		
		/*verification of contact header using hard assert*/
		
		UtilityClassObject.getTest().log(Status.INFO, "Start verification of Contact!!!");
		ContactInfoPage ci=new ContactInfoPage(driver);
		
		String actheader = ci.getPheadermsg().getText();
		boolean Header = actheader.contains(contactLastName);
		
		Assert.assertEquals(Header, true);
		System.out.println("===Contact header verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Contact header verified!!!");
		
		/*verification of org name via hard assert*/
		String actOrgname = ci.getOrgName().getText();
		boolean orgnameMsg = actOrgname.contains(orgName);
		
		Assert.assertTrue(orgnameMsg);
		System.out.println("===Org Name textFeild verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Org Name textFeild verified!!!");
		
		UtilityClassObject.getTest().log(Status.INFO, "New contact with OrgName created!!!");
	}

}
