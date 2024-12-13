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

public class CreateNewOpportunitiesWithContact_OrgName {

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
		
		String opportunitiesName = elib.getDatafromExcelfle("oppurnities", 4, 2)+jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("oppurnities", 4, 3)+jlib.getRandomNumber();
		
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
		
		// create orgname, enter all details and save
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		driver.findElement(By.cssSelector("[name=\"accountname\"]")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg info with expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
				
		if(headerInfo.contains(orgName))   //verify the partial data so we use contains function
		{
			System.out.println(orgName+" is verified!!!!");
		}
		else
		{
			System.out.println(orgName+" is not verified!!!!");
		}
		
		//navigate to opportunitiesName
		driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
		
		//create new opportunities 
		driver.findElement(By.xpath("//img[@alt=\"Create Opportunity...\"]")).click();
		
		//enter details along with orgName and contactLastname
		driver.findElement(By.cssSelector("[name=\"potentialname\"]")).sendKeys(opportunitiesName);
		
		//drop down
		wlib.selectByValue(driver.findElement(By.cssSelector("[name=\"related_to_type\"]")), "Accounts");
		
		driver.findElement(By.xpath("//input[@id=\"related_to_display\"]/following-sibling::img")).click();
		
		//switch to tab
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(orgName);
		driver.findElement(By.cssSelector("[name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch to tab
		wlib.switchToTabOnURL(driver, "Potentials&action");
		
		//save details
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the opertunityname
		String actOpporName = driver.findElement(By.cssSelector("[id=\"dtlview_Opportunity Name\"]")).getText();
		if(actOpporName.contains(opportunitiesName))
		{
			System.out.println(opportunitiesName+" is verified!!");
		}
		else
		{
			System.out.println(opportunitiesName+" is not verified!!");
		}
		
		//verify the orgName
		String actOrgName = driver.findElement(By.cssSelector("[title=\"Organizations\"]")).getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+" is verified!!!");
		}
		else
		{
			System.out.println(orgName+" is not verified!!");
		}
		
		driver.quit();

	}

}
