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

public class CreateContactWithOrganization {

	public static void main(String[] args) throws Throwable {
		
		//Read common data from property file
		FileUtility flib=new FileUtility();
		
		//webdriver utility for switch window
		WebDriverUtility wlib=new WebDriverUtility();
		
		//create javautility of random number
		JavaUtility jlib=new JavaUtility();
		jlib.getRandomNumber();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//read testScript data from Excel file
		
		ExcelUtility elib=new ExcelUtility();
		String orgName = elib.getDatafromExcelfle("contact", 7, 2)+jlib.getRandomNumber();
		String ContactLastNumber = elib.getDatafromExcelfle("contact", 7, 3).toString();
		
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

		//implicitly wait
		wlib.waitPageLoad(driver);
		
		driver.get(URL);
		
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
		
		//click on the org name
		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();
		
		//switch the window from main page to org page
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		//enter the org name on search bar and click on search button
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(orgName);
		driver.findElement(By.cssSelector("[type=\"button\"]")).click();
		
		//click on the orgName link
		/*driver.findElement(By.xpath("//a[text()='faceBook']")).click(); as this is dynamic so at the place of facebook we write 
		orgname variable for dynamic path*/
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	
		//switch again to parent window
		wlib.switchToTabOnURL(driver, "Contacts&action");
		
		//save all details 
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the contact header msg with expected
		String actheader = driver.findElement(By.cssSelector("[id=\"dtlview_Last Name\"]")).getText();
		if(actheader.contains(ContactLastNumber))
		{
			System.out.println(ContactLastNumber+" is created!!!!");
			System.out.println("=========PASS===========");
		}
		else
		{
			System.out.println(ContactLastNumber+" is created!!!!");
			System.out.println("=========FAIL===========");
		}
		
		//verify the orgName in organization feild
		String actOrgName = driver.findElement(By.cssSelector("[id=\"mouseArea_Organization Name\"]")).getText();
		if(actOrgName.trim().equals(orgName))
		{
			System.out.println(orgName+" is created!!!!");
			System.out.println("=========PASS===========");
		}
		else
		{
			System.out.println(orgName+" is created!!!!");
			System.out.println("=========FAIL===========");
		}
		
		//logOut
		
		driver.quit();
		System.out.println("=====EXECUTION DONE SUCCESSFULLY WITH EXPECTED DATA!!!=====");
		
	}

}
