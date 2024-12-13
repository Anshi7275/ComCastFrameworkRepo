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
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to product
		driver.findElement(By.linkText("Products")).click();	
		driver.findElement(By.xpath("//img[@alt=\"Create Product...\"]")).click();
		
		//enter all details
		driver.findElement(By.cssSelector("[name=\"productname\"]")).sendKeys(productName);
		
		//enter port no
		driver.findElement(By.cssSelector("[name=\"vendor_part_no\"]")).sendKeys(portno);
		
		//save the details
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the header product name with expected value
		String actProductName = driver.findElement(By.cssSelector("[id=\"dtlview_Product Name\"]")).getText();
		if(actProductName.contains(productName))
		{
			System.out.println(productName+" is verified!!!");
		}
		else
		{
			System.out.println(productName+" is not verified!!!");
		}
		
		//verify the vendor no with expected vendor no
		String actPortNo = driver.findElement(By.cssSelector("[id=\"dtlview_Vendor Part No\"]")).getText();
		if(actPortNo.contains(portno))
		{
			System.out.println(portno+" is verified!!!");
		}
		else
		{
			System.out.println(portno+" is not verified!!");
		}
		
		driver.quit();
	}
		

	}
