package com.comcast.practice.generic.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateProductWithVendorName {

	public static void main(String[] args) throws Throwable {
		
		//read data from property file
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib =new WebDriverUtility();		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		String productName = elib.getDatafromExcelfle("product", 7, 2)+jlib.getRandomNumber();
		String vendorname = elib.getDatafromExcelfle("product", 7, 3)+jlib.getRandomNumber();
		
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
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Select sel = new Select(driver.findElement(By.cssSelector("[id=\"qccombo\"]")));
		//sel.selectByValue("Vendors");
		
		wlib.selectByValue(driver.findElement(By.cssSelector("[id=\"qccombo\"]")), "Vendors");
		
		//driver.findElement(By.cssSelector("[alt=\"Create Vendor...\"]")).click();
		
		//Enter vendor details and save
		driver.findElement(By.cssSelector("[name=\"vendorname\"]")).sendKeys(vendorname);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//navigate to product
		driver.findElement(By.linkText("Products")).click();	
		driver.findElement(By.xpath("//img[@alt=\"Create Product...\"]")).click();
				
		//enter all details
		driver.findElement(By.cssSelector("[name=\"productname\"]")).sendKeys(productName);
		
		//add vender name
		driver.findElement(By.cssSelector("[alt=\"Select\"]")).click();
		
		
		
		//switch to child window
		wlib.switchToTabOnURL(driver, "Vendors&action");
		
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(vendorname);
		driver.findElement(By.cssSelector("[name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+vendorname+"']")).click();
		
		//switch to parent window
		wlib.switchToTabOnURL(driver, "Products&action");
		
		//save the details
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the header product name with expected value
		String actheader = driver.findElement(By.cssSelector("[class=\"lvtHeaderText\"]")).getText();
		System.out.println(actheader);
		if(actheader.contains("productName"))
		{
			System.out.println(productName+" is verified!!");
		}
		else
		{
			System.out.println(productName+" is not verified!!");
		}
		
		//verify the product name with expected name
		String actProductName = driver.findElement(By.cssSelector("[id=\"dtlview_Product Name\"]")).getText();
		if(actProductName.contains(productName))
		{
			System.out.println(productName+" is verified!!!");
		}
		else
		{
			System.out.println(productName+" is not verified!!!");
		}
		
		//verify the product catogry with expected value
		String actvendorName = driver.findElement(By.cssSelector("[id=\"mouseArea_Vendor Name\"]")).getText();
		if(actvendorName.contains(vendorname))
		{
			System.out.println(vendorname+" is verified!!");
		}
		else
		{
			System.out.println(vendorname+" is not verified!!");
		}
		
		driver.quit();

	}

}
