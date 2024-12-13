package com.comcast.crm.generic.baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;

public class BaseClass {
	
	//create object
	public DataBaseUtility dblib=new DataBaseUtility();
	public FileUtility flib=new FileUtility(); 
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver;
	
	@BeforeSuite (alwaysRun =  true)
	public void configBS() {
		System.out.println("====Connect to DB and Report config====");
		dblib.getDBconection();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		
	}
	
	@Parameters("BROWSER")
	@BeforeClass (alwaysRun =  true)
	public void configBC(/*String BROWSER*/) throws Throwable {
		System.out.println("====Launch the browser====");
	    String BROWSER = flib.getDataFromPropertiesFile("browser");
		//String BROWSER=BROWSER;
		
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else 
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);    //sharing the static variable for parallel execution
	}
	
	@BeforeMethod(alwaysRun =  true)
	public void configBM() throws Throwable {
		System.out.println("===Login to Browser====");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		driver.get(URL);
		wlib.maximizeWindow(driver);
		wlib.waitPageLoad(driver);
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("====Execution begins====");
	}
	
	@AfterMethod(alwaysRun =  true)
	public void configAM() throws Throwable {
		System.out.println("====Logout the browser====");
		
		HomePage hp=new HomePage(driver);
		jlib.wait(1000);
		hp.logout();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@AfterClass(alwaysRun =  true)
	public void configAC() {
		System.out.println("====Close the browser====");
		driver.quit();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@AfterSuite (alwaysRun =  true)
	public void configAS() {
		System.out.println("====disconnect DB and report backup====");
		
		dblib.closeDBConnection();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
	}


}
