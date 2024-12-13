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

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		
		//create fileutility object
		FileUtility flib=new FileUtility();
		
		//create excelutility object
		ExcelUtility elib=new ExcelUtility();
				
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		// create object for wait 
		WebDriverUtility wlib=new WebDriverUtility();
		
		//read data from fileutility
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excelutility 
		String orgName = elib.getDatafromExcelfle("Org", 1, 2)+jlib.getRandomNumber();
		
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
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg info with expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		
		if(headerInfo.contains(orgName))   //verify the partial data so we use contains function
		{
			System.out.println(orgName+" is created!!!!");
			System.out.println("=========PASS===========");
		}
		else
		{
			System.out.println(orgName+" is not created!!!!");
			System.out.println("=========FAIL===========");
		}
		
		//verify the headerOrgName info with expected result
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(actualOrgName.equals(orgName))
		{
			System.out.println(orgName+" is created!!!!");
			System.out.println("=========PASS===========");
		}
		else
		{
			System.out.println(orgName+" is not created!!!!");
			System.out.println("=========FAIL===========");
		}
		
		//logOut
		
		driver.quit();
	}

}
