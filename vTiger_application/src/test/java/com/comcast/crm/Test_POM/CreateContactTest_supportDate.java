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

public class CreateContactTest_supportDate {

	public static void main(String[] args) throws Throwable {

		// create object for property file
		FileUtility flib = new FileUtility();

		// create object for excel file
		ExcelUtility elib = new ExcelUtility();

		// create object for wait and date
		WebDriverUtility wlib = new WebDriverUtility();

		// create javautility of random number
		JavaUtility jlib = new JavaUtility();
		jlib.getRandomNumber();

		// read the common data from fileutility class
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read testscript from excelutility
		String lastName = elib.getDatafromExcelfle("contact", 4, 2) + jlib.getRandomNumber();

		WebDriver driver = null;
		if (BROWSER.equals("edge"))
			;
		{
			driver = new EdgeDriver();
		}
		if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);

		wlib.waitPageLoad(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);

		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		Contactpage cp = new Contactpage(driver);
		cp.getCreateContIcon().click();

		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnametxt().sendKeys(lastName);

		String startdate = jlib.getSystemdate();
		String endDate = jlib.getRequiredDate(30);

		ccp.getStartdatetext().clear();
		ccp.getStartdatetext().sendKeys(startdate);

		ccp.getEnddatetxt().clear();
		ccp.getEnddatetxt().sendKeys(endDate);

		ccp.getSavebtn().click();
		// verifcation header msg with actual header msg

		ContactInfoPage ci = new ContactInfoPage(driver);
		String actheader = ci.getPheadermsg().getText();
		if (actheader.contains(lastName)) {
			System.out.println(lastName + " verified with actual msg!!!!");
		} else {
			System.out.println(lastName + " is not verified with actual msg!!!");
		}

		String actStartdate = ci.getStartDate().getText();
		if (actStartdate.contains(startdate)) {
			System.out.println(startdate + " is verified msg!!!");
		} else {
			System.out.println(startdate + " is not verified msg!!");
		}

		String actendDate = ci.getEnddate().getText();
		if (actendDate.contains(endDate)) {
			System.out.println(endDate + " is verified msg!!!");
		} else {
			System.out.println(endDate + " is not verified msg!!");
		}

		hp.logout();
		driver.quit();
	}

}
