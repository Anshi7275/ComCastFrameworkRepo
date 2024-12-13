package com.comcast.crm.contactTest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		
		//create object for property file
		FileUtility flib=new FileUtility();
				
		//create object for excel file
		ExcelUtility elib=new ExcelUtility();
		
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		
		//login to application
		driver.findElement(By.cssSelector("[name=\"user_name\"]")).sendKeys(USERNAME);
		driver.findElement(By.cssSelector("[type=\"password\"]")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to contact module
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//click on create contact button
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		
		//Enter all the details and create new contact	
		driver.findElement(By.cssSelector("[name=\"lastname\"")).sendKeys(lastName);
		
		//save all details 
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		
		//verify headermsg info with expected result
		String actualInfo1 = driver.findElement(By.cssSelector("[class=\"dvHeaderText\"]")).getText();
		
		if(actualInfo1.contains(lastName))   //verify the partial data so we use contains function
		{
			System.out.println(lastName+" information is verified!!!!");
			System.out.println("=========PASS=========");
		}
		else
		{
			System.out.println(lastName+" information is not verified!!!!");
			System.out.println("=========FAIL=========");
		}
		
		//verify lastname info with expected result
		String actualInfo2 = driver.findElement(By.cssSelector("[id=\"dtlview_Last Name\"]")).getText();
		
		if(actualInfo2.equals(lastName))
		{
			System.out.println(lastName+" information is verified!!!!");
			System.out.println("=========PASS=========");
		}
		else
		{
			System.out.println(lastName+" information is verified!!!!");
			System.out.println("=========FAIL=========");
		}
		
		//logOut
		
		driver.quit();
	}

}
