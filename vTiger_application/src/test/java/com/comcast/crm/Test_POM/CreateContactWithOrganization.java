package com.comcast.crm.Test_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.ContactInfoPage;
import com.comcast.crm.objectRepository_utility.Contactpage;
import com.comcast.crm.objectRepository_utility.CreateContactPage;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws Throwable {
		
		//Read common data from property file
		FileUtility flib=new FileUtility();
		
		//webdriver utility for switch window
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excel file
		
		ExcelUtility elib=new ExcelUtility();
		String orgName = elib.getDatafromExcelfle("contact", 7, 2)+jlib.getRandomNumber();
		String ContactLastNumber = elib.getDatafromExcelfle("contact", 7, 3).toString();
		
		WebDriver driver=null;
		if(BROWSER.equals("edge"));
		{
			driver=new EdgeDriver();
			}
		if(BROWSER.equals("firefox"))
		{
			driver= new FirefoxDriver();
			}
		else
		{
			driver=new ChromeDriver();
			}

		//implicitly wait
		wlib.waitPageLoad(driver);
		
		driver.get(URL);
		
		LoginPage LOGINPAGE = new LoginPage(driver);
		LOGINPAGE.loginToapp(USERNAME, PASSWORD);
		
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();
		
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);
		
		ORGNAME.getSave().click();
		
		//verifcation 
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);
		HEADERMSG.headermsg(orgName);
		
		//create contact
		HOMEPAGE.getContactlnk().click();
		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(ContactLastNumber);
		
		ccp.getOrgIcon().click();
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		OrganizationWindowPage ow = new OrganizationWindowPage(driver);
		ow.getSearchtxt().sendKeys(orgName);
		ow.getSearchbtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		wlib.switchToTabOnURL(driver, "Contacts&action");
		
        ccp.getSavebtn().click();
		
	    HOMEPAGE.logout();
		driver.quit();
		
		//verification
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actpheader = cip.getPheadermsg().getText();
		if(actpheader.contains(ContactLastNumber))
		{
			System.out.println(ContactLastNumber+" is verified!!!");
		}
		else
		{
			System.out.println(ContactLastNumber+" is not verified!!!");
		}
		
		String actproductmsg = cip.getProductmsg().getText();
		if(actproductmsg.contains(ContactLastNumber))
		{
			System.out.println(ContactLastNumber+" is verified!!!");
		}
		else
		{
			System.out.println(ContactLastNumber+" is not verified!!!");
		}
		
		
		driver.quit();
		System.out.println("=====EXECUTION DONE SUCCESSFULLY WITH EXPECTED DATA!!!=====");
		
	}

}
