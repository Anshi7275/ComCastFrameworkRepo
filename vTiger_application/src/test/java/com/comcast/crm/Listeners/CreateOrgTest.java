package com.comcast.crm.Listeners;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateOrgTest extends BaseClass {
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
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

		// verification of orgnameHeader by hardAssert
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean header = actHeader.contains(orgName);

		Assert.assertEquals(header, true);
		System.out.println("===Org Name header is verified!!!===");
	}
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
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
		
		//verification of industry and type
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		
		//verification of industry and type
		String actIndustry = oi.getIndustryMag().getText();
		boolean Industrymsg = actIndustry.contains(industry);
		
		Assert.assertEquals(Industrymsg, true);
		
		String actType = oi.getTypemsg().getText();
		boolean Typemsg = actType.contains(type);
		
		Assert.assertEquals(Typemsg, true);
		
	}
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
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
		
		// verification
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actheader = oip.getHeadermsg().getText();
		boolean headerMsg = actheader.contains(orgName);

		Assert.assertEquals(headerMsg, true);

		String actphone = oip.getPhonemsg().getText();
		boolean phoneMsg = actphone.contains(phoneNumber);

		Assert.assertEquals(phoneMsg, true);
	}
	

}
