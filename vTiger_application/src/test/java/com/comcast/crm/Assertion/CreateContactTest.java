package com.comcast.crm.Assertion;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

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
		
		//verifcation header msg with actual header msg by hardAssert
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("==header verified!!!===");
		
		String actLastName = ci.getProductmsg().getText();
		boolean lastNameMsg = actLastName.contains(contactLastName);
		
		Assert.assertEquals(lastNameMsg, true);
		System.out.println("==LastName textFeild verified!!!===");
		
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithDate() throws Throwable
	{
		String contactLastname = elib.getDatafromExcelfle("contact", 4, 2)+jlib.getRandomNumber();
		
		wlib.waitPageLoad(driver);
		
		//navigate to contact module
		HomePage hp=new HomePage(driver);
		hp.getContactlnk().click();
		
		//click on "create contact" button
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		//Enter all the details and create new contact
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastname);
		
		String startDate = jlib.getSystemdate();
		String endDate = jlib.getRequiredDate(30);
		
		ccp.getStartdatetext().clear();
		ccp.getStartdatetext().sendKeys(startDate);
		
		ccp.getEnddatetxt().clear();
		ccp.getEnddatetxt().sendKeys(endDate);
		ccp.getSavebtn().click();
		
		//verifcation header msg with actual header msg via hard assert
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean headermsg = actheader.contains(contactLastname);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Contact Header verified!!!===");
		
		//verifcation StartDate msg with actual StartDate msg via hard assert
		String actStartdate = ci.getStartDate().getText();
		boolean StartDateMsg = actStartdate.contains(startDate);
		Assert.assertTrue(StartDateMsg);
		System.out.println("===Start date verified!!!===");
		
		//verifcation EndDate msg with actual EndDate msg via hard assert
		String actendDate = ci.getEnddate().getText();
		boolean EndDateMsg = actendDate.contains(endDate);
		
		Assert.assertEquals(EndDateMsg, true);
		System.out.println("===End date verified!!!===");
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		
		wlib.waitPageLoad(driver);
		
		String orgName = elib.getDatafromExcelfle("contact", 7, 2)+jlib.getRandomNumber();
		String contactLastName = elib.getDatafromExcelfle("contact", 7, 3)+jlib.getRandomNumber();
		
		//navigate to orgnization
		HomePage hp=new HomePage(driver);
		hp.getOrganization().click();
		
		//click on "create org" button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreate_org().click();
		
		//enter all the details and save
		CreateNewOrganizationPage co=new CreateNewOrganizationPage(driver);
		co.getOrgName().sendKeys(orgName);
		co.getSave().click();
		
		//verification of orgnameHeader by hardAssert
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		
		//navigate to contact 
		hp.getContactlnk().click();
		
		//click on create contact button
		Contactpage cp=new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		//enter all the details and save
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(contactLastName);
		
		ccp.getOrgIcon().click();
		
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		//switch to org page
		OrganizationWindowPage ow = new OrganizationWindowPage(driver);
		ow.getSearchtxt().sendKeys(orgName);
		ow.getSearchbtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		wlib.switchToTabOnURL(driver, "Contacts&action");
		
		ccp.getSavebtn().click();	
		
		//verification of contact header using hard assert
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		boolean Header = actheader.contains(contactLastName);
		
		Assert.assertEquals(Header, true);
		System.out.println("===Contact header verified!!!===");
		
		//verification of org name via hard assert
		String actOrgname = ci.getOrgName().getText();
		boolean orgnameMsg = actOrgname.contains(orgName);
		
		Assert.assertTrue(orgnameMsg);
		System.out.println("===Org Name textFeild verified!!!===");
		
	}

}
