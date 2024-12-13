package com.comcast.crm.Extent_Report;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateOrgTest extends BaseClass {
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOrgNameTest() throws Throwable {
		
		//read testScript data from Excelutility 
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String orgName = elib.getDatafromExcelfle("Org", 1, 2)+jlib.getRandomNumber();
			
		//navigate to organization
		UtilityClassObject.getTest().log(Status.INFO, "Login----> navigate to organization");
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		// click on create org button
		UtilityClassObject.getTest().log(Status.INFO, "create new organization-----> Enter all details");
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();

		// create new orgname and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ORGNAME.getSave().click();
		
		jlib.wait(2000);

		// verification of orgnameHeader by hardAssert
		UtilityClassObject.getTest().log(Status.INFO, "Verification of organization started!!!!");
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean header = actHeader.contains(orgName);

		Assert.assertEquals(header, true);
		System.out.println("===Org Name header is verified!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "Header msg got verified");
	}
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOrgWithIndustryTest() throws Throwable {
			
		//read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String orgName=elib.getDatafromExcelfle("Org",4 , 2)+jlib.getRandomNumber();
		String industry=elib.getDatafromExcelfle("Org",4 , 3).toString();
		String type=elib.getDatafromExcelfle("Org",4 , 4).toString();

		//navigate to organization
		UtilityClassObject.getTest().log(Status.INFO, "Login----> navigate to organization");
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		//click on "create organization" button
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();
		
		//create new org name with industry and type and save
		UtilityClassObject.getTest().log(Status.INFO, "Create new Org-----> Enter all details alog with industry and type");
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);
	
		WebElement industrydropDown = ORGNAME.getIndustry();
		wlib.select(industrydropDown, industry);
		
		WebElement typedropDown = ORGNAME.getType();
		wlib.select(typedropDown, type);
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ORGNAME.getSave().click();
		
		jlib.wait(2000);
		
		//verification of industry and type
		UtilityClassObject.getTest().log(Status.INFO, "Verification of organization started!!!");
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "OrgName header msg got verified!!!");
		
		//verification of industry and type
		String actIndustry = oi.getIndustryMag().getText();
		boolean Industrymsg = actIndustry.contains(industry);
		
		Assert.assertEquals(Industrymsg, true);
		UtilityClassObject.getTest().log(Status.INFO, "OrgName with industry verified!!!");
		
		String actType = oi.getTypemsg().getText();
		boolean Typemsg = actType.contains(type);
		
		Assert.assertEquals(Typemsg, true);
		UtilityClassObject.getTest().log(Status.INFO, "OrgName with type verified!!!");
		
	}
	
	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOrgnameWithPhone() throws Throwable
	{
		//read data from excel
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String orgName=elib.getDatafromExcelfle("Org",7 , 2)+jlib.getRandomNumber();
		String phoneNumber = elib.getDatafromExcelfle("Org", 7, 3)+jlib.getRandomNumber();
		
		UtilityClassObject.getTest().log(Status.INFO, "Login------> navigate to Orgnization");
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization------>Enter all details along with phone number");
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.getContacttxt().sendKeys(phoneNumber);
		cno.getOrgName().sendKeys(orgName);
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		cno.getSave().click();
		
		jlib.wait(2000);
		
		// verification
		UtilityClassObject.getTest().log(Status.INFO, "Verification of Organization started!!!!");
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actheader = oip.getHeadermsg().getText();
		boolean headerMsg = actheader.contains(orgName);

		Assert.assertEquals(headerMsg, true);
		UtilityClassObject.getTest().log(Status.INFO, "header msg got verified!!!");

		String actphone = oip.getPhonemsg().getText();
		boolean phoneMsg = actphone.contains(phoneNumber);

		Assert.assertEquals(phoneMsg, true);
		UtilityClassObject.getTest().log(Status.INFO, "Orgname with Phone number verified!!!");
	}
	

}
