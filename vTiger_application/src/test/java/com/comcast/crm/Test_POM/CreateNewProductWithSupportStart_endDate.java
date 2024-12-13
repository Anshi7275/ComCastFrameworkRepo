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
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateNewProductWithSupportStart_endDate {

	public static void main(String[] args) throws Throwable {
		
		//read data from property file
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		//String USERNAME = flib.getDataFromPropertiesFile("username");
		//String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		String productName = elib.getDatafromExcelfle("product", 4, 2)+jlib.getRandomNumber();
		//String portno = elib.getDatafromExcelfle("product", 4, 3)+jlib.getRandomNumber();
		
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
		
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
				
		
		CreateProductPage cp=new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);
		
		String Startdate = jlib.getSystemdate();
		String salesEndDate = jlib.getRequiredDate(30);
		String expiryDate = jlib.getRequiredDataByYear(1);
		
		cp.getSalesStartdatetxt().clear();
		cp.getSalesStartdatetxt().sendKeys(Startdate);
		
		cp.getSalesEnddate().clear();
		cp.getSalesEnddate().sendKeys(salesEndDate);
		
		cp.getStartdate().clear();
		cp.getStartdate().sendKeys(Startdate);
		
		cp.getExpirydate().clear();
		cp.getExpirydate().sendKeys(expiryDate);
		cp.getSavebtn();
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		String actSalestartDate = pi.getSalestartDate().getText();
		if(actSalestartDate.contains(Startdate)) 
		{
			System.out.println(Startdate+" is verified!!");
		}
		else
		{
			System.out.println(Startdate+" is not verified!!");
		}
		
		String actSaleEndDate = pi.getSaleEndDate().getText();
		if(actSaleEndDate.contains(salesEndDate))
		{
			System.out.println(salesEndDate+" is verified!!");
		}
		else
		{
			System.out.println(salesEndDate+" is not verified!!");
		}
		
		String actStartDate = pi.getSupportStartDate().getText();
		if(actStartDate.contains(Startdate))
		{
			System.out.println(Startdate+" is verified!!");
		}
		else
		{
			System.out.println(Startdate+" is not verified!!");
		}
		
		String actEndDate = pi.getSupportEndDate().getText();
		if(actEndDate.contains(expiryDate))
		{
			System.out.println(expiryDate+" is verified!!");
		}
		else
		{
			System.out.println(expiryDate+" is not verified!!");
		}	
		
		hp.logout();
		driver.quit();

	}

}
