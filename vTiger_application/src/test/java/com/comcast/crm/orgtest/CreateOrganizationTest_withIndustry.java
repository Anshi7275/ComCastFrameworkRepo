package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationTest_withIndustry {

	public static void main(String[] args) throws Throwable {
		
		//create object for property file
		FileUtility flib=new FileUtility();
				
		//create object for excel file
		ExcelUtility elib=new ExcelUtility();
		
		// create object for wait 
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excel file
		String orgName=elib.getDatafromExcelfle("Org",4 , 2)+jlib.getRandomNumber();
		String industry=elib.getDatafromExcelfle("Org",4 , 3).toString();
		String type=elib.getDatafromExcelfle("Org",4 , 4).toString();
		
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
		
		//navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create organization button
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		
		//Enter all the details and create new organization	
		driver.findElement(By.cssSelector("[name=\"accountname\"]")).sendKeys(orgName);
		
		//select value from drop down
		
		wlib.select(driver.findElement(By.cssSelector("[name=\"industry\"]")), industry);
		
		wlib.select(driver.findElement(By.cssSelector("[name=\"accounttype\"]")), type);
		
		//save all details 
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		
		//verify industry info with expected result
		String actualIndustry = driver.findElement(By.xpath("//span[@id=\"dtlview_Industry\"]")).getText();
		
		if(actualIndustry.equals(industry))   //verify the partial data so we use contains function
		{
			System.out.println(industry+" information is verified!!!!");
			System.out.println("=========PASS=========");
		}
		else
		{
			System.out.println(industry+" information is not verified!!!!");
			System.out.println("=========FAIL=========");
		}
		
		//verify the type info with expected result
		String actualType = driver.findElement(By.xpath("//span[@id=\"dtlview_Type\"]")).getText();
		
		if(actualType.equals(type))
		{
			System.out.println(type+" information is verified!!!!");
			System.out.println("=========PASS===========");
		}
		else
		{
			System.out.println(type+" information is not verified!!!!");
			System.out.println("=========FAIL===========");
		}
		
		//logOut
		
		driver.quit();

	}

}
