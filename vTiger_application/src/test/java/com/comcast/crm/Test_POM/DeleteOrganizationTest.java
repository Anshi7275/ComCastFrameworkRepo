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
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class DeleteOrganizationTest {
public static void main(String[] args) throws Throwable {
		
		//create fileutility object
		FileUtility flib=new FileUtility();
		
		//create excelutility object
		ExcelUtility elib=new ExcelUtility();
				
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		// create object for wait 
		WebDriverUtility wlib=new WebDriverUtility();
		
		//read data from fileutility
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excelutility 
		String orgName = elib.getDatafromExcelfle("Org", 10, 2)+jlib.getRandomNumber();
		
		WebDriver driver=null;
		if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		driver.get(URL);
		
		wlib.waitPageLoad(driver);
		
		//login to page
		
		LoginPage LOGINPAGE = new LoginPage(driver);
		LOGINPAGE.loginToapp( USERNAME, PASSWORD);
		
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();
		
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);
		
		ORGNAME.getSave().click();
		
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);
		HEADERMSG.headermsg(orgName);
		
		//go back to organization page
		HOMEPAGE.getOrganization().click();
		
		//search for organization 
		ORGNAME.getSearchtxt().sendKeys(orgName);
		wlib.select(ORGNAME.getSearchDD(), "Organization Name");
		ORGNAME.getSearch().click();
		
		//handle dynamic element by xpath
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
		wlib.switchToAlertAndAccept(driver);
		
		HOMEPAGE.logout();
		
		driver.close();
	}

}
