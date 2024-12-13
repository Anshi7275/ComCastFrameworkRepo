package com.comcast.crm.testNG;

import org.openqa.selenium.By;
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
		
		//verifcation header msg with actual header msg
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		if(actheader.contains(contactLastname))
		{
			System.out.println(contactLastname+ " verified with actual msg!!!!");
		}
		else
		{
			System.out.println(contactLastname+" is not verified with actual msg!!!");
		}
		
		String actStartdate = ci.getStartDate().getText();
		if(actStartdate.contains(startDate))
		{
			System.out.println(startDate+" is verified msg!!!");
		}
		else
		{
			System.out.println(startDate+" is not verified msg!!");
		}
		
		String actendDate = ci.getEnddate().getText();
		if(actendDate.contains(endDate))
		{
			System.out.println(endDate+" is verified msg!!!");
		}
		else
		{
			System.out.println(endDate+" is not verified msg!!");
		}
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
		
		//verification of orgname
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		oi.headermsg(orgName);
		
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
		
		//verification
		ContactInfoPage ci=new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		if(actheader.contains(contactLastName))
		{
			System.out.println(contactLastName+ " verified with actual!!!!");
		}
		else
		{
			System.out.println(contactLastName+" is not verified with actual!!!");
		}
		
		String actOrgname = ci.getOrgName().getText();
		if(actOrgname.contains(orgName))
		{
			System.out.println(orgName+" is verified with actual msg!!!");
		}
		else
		{
			System.out.println(orgName+" is not verified with actual msg!!!");
		}
		
	}

}
