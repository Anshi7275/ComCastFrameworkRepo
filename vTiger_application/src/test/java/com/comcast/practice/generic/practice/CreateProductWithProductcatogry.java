package com.comcast.practice.generic.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

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
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to product
		driver.findElement(By.linkText("Products")).click();	
		driver.findElement(By.xpath("//img[@alt=\"Create Product...\"]")).click();
		
		//enter all details
		driver.findElement(By.cssSelector("[name=\"productname\"]")).sendKeys(productName);
		
		//select product catogry
		Select sel=new Select(driver.findElement(By.cssSelector("[name=\"productcategory\"]")));
		sel.selectByVisibleText("Hardware");
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the header product name with expected value
		String actheader = driver.findElement(By.cssSelector("[class=\"lvtHeaderText\"]")).getText();
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
		String actcatogry = driver.findElement(By.cssSelector("[id=\"dtlview_Product Category\"]")).getText();
		if(actcatogry.equals(productCatogry))
		{
			System.out.println(productCatogry+" is verified!!");
		}
		else
		{
			System.out.println(productCatogry+" is not verified!!");
		}
		
		driver.quit();
	}

}
