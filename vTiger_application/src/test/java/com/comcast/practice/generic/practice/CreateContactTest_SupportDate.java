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

public class CreateContactTest_SupportDate {

	public static void main(String[] args) throws Throwable {
		
		//Read common data from property file
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excel file
		String orgName = elib.getDatafromExcelfle("contact", 7, 2)+jlib.getRandomNumber();
		String ContactLastNumber = elib.getDatafromExcelfle("contact", 7, 3);
		
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
		wlib.maximizeWindow(driver);
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
		
		//save the data
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
		
		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//create contact button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		
		//enter all details of contact 
		driver.findElement(By.name("lastname")).sendKeys(ContactLastNumber);
		
		//click on + symbol of orgName
		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();
		
		//switch to orgname window
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(orgName);
		driver.findElement(By.cssSelector("[type=\"button\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch to parent window
		wlib.switchToTabOnURL(driver, "Contacts&action");
		
		//enter start date and enter end date
		
		String startDate = jlib.getSystemdate();
		
		String endDate = jlib.getRequiredDate(30);
		
		driver.findElement(By.cssSelector("[name=\"support_start_date\"]")).clear();
		driver.findElement(By.cssSelector("[name=\"support_start_date\"]")).sendKeys(startDate);
		
		driver.findElement(By.cssSelector("[name=\"support_end_date\"]")).clear();
		driver.findElement(By.cssSelector("[name=\"support_end_date\"]")).sendKeys(endDate);
		
		//save the data
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the contact header with expected header
		
		String actHeader = driver.findElement(By.cssSelector("[class=\"dvHeaderText\"]")).getText();
		if(actHeader.contains(ContactLastNumber))
		{
			System.out.println(ContactLastNumber+" is verified with expected data");
		}
		else
		{
			System.out.println(ContactLastNumber+" is not verified with expected data");
		}
		
		//verify orgname with expected
		String actOrgName = driver.findElement(By.cssSelector("[id=\"mouseArea_Organization Name\"]")).getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+" is verified with expected value!!");
		}
		else
		{
			System.out.println(orgName+" is not verified with expected value!!");
		}
		
		//verify the start date and end date with expected date
		
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getTagName();
		System.out.println(actStartDate);
		
		System.out.println(startDate);
		if(actStartDate.trim().equals(startDate))
		{
			System.out.println(startDate+" is verified with expected value!!");
		}
		else
		{
			System.out.println(startDate+" is not verified with expected value!!");
		}
		
		String actEnddate = driver.findElement(By.cssSelector("[id=\"dtlview_Support End Date\"]")).getText();
		if(actEnddate.contains(endDate))
		{
			System.out.println(endDate+" is verified with expected value!!");
		}
		else
		{
			System.out.println(endDate+" is not verified with expected value!!");
		}
		
		//logout
		driver.close();
	}

}
