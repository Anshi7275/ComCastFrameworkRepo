package com.comcast.crm.Test_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateNewProductWithPortno {
	public static void main(String[] args) throws Throwable {
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		jlib.getRandomNumber();
		
		String productName = elib.getDatafromExcelfle("product", 4, 2)+jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4,3)+jlib.getRandomNumber();
		
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
		wlib.maximizeWindow(driver);
		wlib.waitPageLoad(driver);
		
		LoginPage lp= new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
		
		CreateProductPage ccp=new CreateProductPage(driver);
		ccp.createproduct(productName, portno);
		ccp.getSavebtn().click();
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();
		if(actheader.contains(productName))
		{
			System.out.println(productName+" verified!!!");
		}
		else
		{
			System.out.println(productName+" not verified!!!");
		}
		
		String ActProductName = pi.getProductname().getText();
		if(ActProductName.contains(productName))
		{
			System.out.println("product name verified!!!");
		}
		else
		{
			System.out.println("product name is not verified!!!");
		}
		
		hp.logout();
		driver.quit();
	}

}
