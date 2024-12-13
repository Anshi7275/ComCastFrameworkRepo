package com.comcast.crm.Test_POM;

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
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		
		//create object for property file
		FileUtility flib=new FileUtility();
				
		//create object for excel file
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		//read the common data from fileutility class
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from ExcelUtility class
		String lastName = elib.getDatafromExcelfle("contact", 1, 2)+jlib.getRandomNumber();
		
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
		hp.getContactlnk().click();
		
		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();
		
		CreateContactPage cnc = new CreateContactPage(driver);
		cnc.getLastnametxt().sendKeys(lastName);
		cnc.getSavebtn().click();
		
		//verification
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actpheader = cip.getPheadermsg().getText();
		if(actpheader.contains(lastName))
		{
			System.out.println(lastName+" is verified!!!");
		}
		else
		{
			System.out.println(lastName+" is not verified!!!");
		}
		
		String actproductmsg = cip.getProductmsg().getText();
		if(actproductmsg.contains(lastName))
		{
			System.out.println(lastName+" is verified!!!");
		}
		else
		{
			System.out.println(lastName+" is not verified!!!");
		}
		
	}

}
