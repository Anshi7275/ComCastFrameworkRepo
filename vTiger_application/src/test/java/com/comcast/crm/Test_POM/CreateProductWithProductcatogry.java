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
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateProductWithProductcatogry {

	public static void main(String[] args) throws Throwable {
		
		//read data from property file
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//data from excel
		String productName = elib.getDatafromExcelfle("product", 3, 2)+jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 3, 3)+jlib.getRandomNumber();
		
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
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
				
		
		CreateProductPage cp=new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);
		
		WebElement catogryDD = cp.getCatorgyDropDown();
		wlib.selectByValue(catogryDD, "Hardware");
		jlib.wait(1000);
		cp.getSavebtn().click();
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		
		String ActProductName = pi.getProductname().getText();
		if(ActProductName.contains(productName))
		{
			System.out.println("product name verified!!!");
		}
		else
		{
			System.out.println("product name is not verified!!!");
		}
		String actProduct = pi.getProductcatogry().getText();
		if(actProduct.contains(productCatogry))
		{
			System.out.println("product catogry verified!!!");
		}
		else
		{
			System.out.println("product catogry is not verified!!!");
		}
		
	}

}
