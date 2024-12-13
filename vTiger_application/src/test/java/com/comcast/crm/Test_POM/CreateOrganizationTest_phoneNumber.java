package com.comcast.crm.Test_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrganizationTest_phoneNumber {

	public static void main(String[] args) throws Throwable {
		
		//create object for property file
		FileUtility flib=new FileUtility();
				
		//create object for excel file
		ExcelUtility elib=new ExcelUtility();
		
		// create object for wait 
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excel file
		String orgName=elib.getDatafromExcelfle("Org",7 , 2)+jlib.getRandomNumber();
		String phoneNumber = elib.getDatafromExcelfle("Org", 7, 3)+jlib.getRandomNumber();
		
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
		
		driver.get(URL);
		
		wlib.waitPageLoad(driver);
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();
		
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.getContacttxt().sendKeys(phoneNumber);
		cno.getOrgName().sendKeys(orgName);
		
		cno.getSave().click();
		
		//verification
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		oip.headermsg(orgName);
		
		String actphone = oip.getPhonemsg().getText();
		if(actphone.contains(phoneNumber))
		{
			System.out.println(phoneNumber+" is verified!!!");
		}
		else {
			System.out.println(phoneNumber+" is not verified!!!");
		}

		
		//logOut
		hp.logout();
		
		driver.quit();

	}

}
