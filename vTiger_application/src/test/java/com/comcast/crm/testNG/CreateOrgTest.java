package com.comcast.crm.testNG;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrgTest extends BaseClass {
	
	@Test(groups = "smokeTest")
	public void createOrgNameTest() throws Throwable {
		
		//read testScript data from Excelutility 
		String orgName = elib.getDatafromExcelfle("Org", 1, 2)+jlib.getRandomNumber();
			
		//navigate to organization
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		// click on create org button
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();

		// create new orgname and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);

		ORGNAME.getSave().click();
		
		jlib.wait(2000);

		// verifcation of header msg
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);

		HEADERMSG.organizationName(orgName);
	}
	
	@Test(groups = "regressionTest")
	public void createOrgWithIndustryTest() throws Throwable {
			
		//read data from excel file
		String orgName=elib.getDatafromExcelfle("Org",4 , 2)+jlib.getRandomNumber();
		String industry=elib.getDatafromExcelfle("Org",4 , 3).toString();
		String type=elib.getDatafromExcelfle("Org",4 , 4).toString();

		//navigate to organization
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		//click on "create organization" button
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();
		
		//create new org name with industry and type and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);
	
		WebElement industrydropDown = ORGNAME.getIndustry();
		wlib.select(industrydropDown, industry);
		
		WebElement typedropDown = ORGNAME.getType();
		wlib.select(typedropDown, type);
		
		ORGNAME.getSave().click();
		
		jlib.wait(2000);
		
		//verification of header msg
		OrganizationInformationPage headermsg = new OrganizationInformationPage(driver);
		String actheader = headermsg.getHeadermsg().getText();
		if(actheader.contains(orgName))
		{
			System.out.println(orgName+" is created!!!!");
		}
		else
		{
			System.out.println(orgName+" is not created!!!!");
		}
		
		//verification of industry and type
		String actIndustry = headermsg.getIndustryMag().getText();
		if(actIndustry.contains(industry))
		{
			System.out.println(industry +" is verified!!!");
		}
		else
		{
			System.out.println(industry +" is not verified!!!");
		}
		
		String actType = headermsg.getTypemsg().getText();
		if(actType.contains(type))
		{
			System.out.println(type +" is verified!!!");
		}
		else
		{
			System.out.println(type +" is not verified!!!");
		}
		
	}
	
	@Test(groups = "regressionTest")
	public void createOrgnameWithPhone() throws Throwable
	{
		//read data from excel
		String orgName=elib.getDatafromExcelfle("Org",7 , 2)+jlib.getRandomNumber();
		String phoneNumber = elib.getDatafromExcelfle("Org", 7, 3)+jlib.getRandomNumber();
		
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();
		
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.getContacttxt().sendKeys(phoneNumber);
		cno.getOrgName().sendKeys(orgName);
		
		cno.getSave().click();
		
		jlib.wait(2000);
		
		//verification
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actheader = oip.getHeadermsg().getText();
		if(actheader.contains(orgName))
		{
			System.out.println(orgName+" is created!!!!");
		}
		else
		{
			System.out.println(orgName+" is not created!!!!");
		}
		
		String actphone = oip.getPhonemsg().getText();
		if(actphone.contains(phoneNumber))
		{
			System.out.println(phoneNumber+" is verified!!!");
		}
		else {
			System.out.println(phoneNumber+" is not verified!!!");
		}
	}
	

}
