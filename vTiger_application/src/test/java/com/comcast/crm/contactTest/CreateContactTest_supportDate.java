package com.comcast.crm.contactTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactTest_supportDate {

	public static void main(String[] args) throws Throwable {
		
		//create object for property file
		FileUtility flib=new FileUtility();
				
		//create object for excel file
		ExcelUtility elib=new ExcelUtility();
		
		// create object for wait and date 
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		//read the common data from fileutility class
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testscript from excelutility
		String lastName = elib.getDatafromExcelfle("contact", 4, 2)+jlib.getRandomNumber();
		
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
		
		//enter support start date for 30 day duration
		String startdate = jlib.getSystemdate();
		String endDate = jlib.getRequiredDate(30);
		
		driver.findElement(By.cssSelector("[name=\"support_start_date\"]")).clear();
		driver.findElement(By.cssSelector("[name=\"support_start_date\"]")).sendKeys(startdate);
		
		driver.findElement(By.cssSelector("[name=\"support_end_date\"]")).clear();
		driver.findElement(By.cssSelector("[name=\"support_end_date\"]")).sendKeys(endDate);
		
		//save all details 
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		
		//verify the start date with expected start date
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startdate))
		{
			System.out.println(startdate+" information is verified!!!");
		}
		else
		{
			System.out.println(startdate+" is not verified!!!");
		}
		
		//verify the end date with expected end date
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate+" is verified!!!");
			System.out.println("=====PASS=====");
		}
		else
		{
			System.out.println(endDate+" is not verified!!!");
			System.out.println("=====FAIL=====");
		}
		
		//logOut
		
		driver.quit();
	}

}
