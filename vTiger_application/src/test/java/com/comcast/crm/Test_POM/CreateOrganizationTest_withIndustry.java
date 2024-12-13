package com.comcast.crm.Test_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrganizationTest_withIndustry {

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
		String orgName=elib.getDatafromExcelfle("Org",4 , 2)+jlib.getRandomNumber();
		String industry=elib.getDatafromExcelfle("Org",4 , 3).toString();
		String type=elib.getDatafromExcelfle("Org",4 , 4).toString();
		
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
		LOGINPAGE.loginToapp(USERNAME, PASSWORD);
		
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();
		
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();
		
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);
	
		WebElement industrydropDown = ORGNAME.getIndustry();
		wlib.select(industrydropDown, industry);
		
		WebElement typedropDown = ORGNAME.getType();
		wlib.select(typedropDown, type);
		
		ORGNAME.getSave().click();
		
		//verification 
		OrganizationInformationPage headermsg = new OrganizationInformationPage(driver);
		headermsg.headermsg(orgName);
		
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
		
		HOMEPAGE.logout();
		
		driver.close();
		
	}

}
