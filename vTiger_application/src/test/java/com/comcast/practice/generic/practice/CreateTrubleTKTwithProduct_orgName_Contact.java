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

public class CreateTrubleTKTwithProduct_orgName_Contact {

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
		
		String title = elib.getDatafromExcelfle("TroubleTicket", 1, 2)+jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("TroubleTicket", 1, 3)+jlib.getRandomNumber();
		//String contactLastName = elib.getDatafromExcelfle("TroubleTicket", 1, 4)+jlib.getRandomNumber();
		String productName = elib.getDatafromExcelfle("TroubleTicket", 1, 5)+jlib.getRandomNumber();
		String status = elib.getDatafromExcelfle("TroubleTicket", 1, 5);
		
		WebDriver driver=null;
		if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
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
				
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName+" is verified!!!!");
		}
		else {
			System.out.println(orgName+" is not verified!!!!");
		}	
				
		//navigate to product, create product,enter all details and save
		driver.findElement(By.linkText("Products")).click();	
		driver.findElement(By.xpath("//img[@alt=\"Create Product...\"]")).click();
		driver.findElement(By.cssSelector("[name=\"productname\"]")).sendKeys(productName);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the header product name with expected value
		String actheader = driver.findElement(By.cssSelector("[class=\"lvtHeaderText\"]")).getText();

		if(actheader.contains(productName)) {
			System.out.println(productName+" is verified!!");
		}
		else {
			System.out.println(productName+" is not verified!!");
		}
		
		//navigate to trouble ticket
		driver.findElement(By.linkText("Trouble Tickets")).click();
		
		//create new ticket
		driver.findElement(By.xpath("//img[@alt=\"Create Ticket...\"]")).click();
		
		//enter all details
		driver.findElement(By.cssSelector("[name=\"ticket_title\"]")).sendKeys(title);
		
		//select orgName with drop down
		Select sel = new Select(driver.findElement(By.cssSelector("[name=\"parent_type\"]")));
		sel.selectByValue("Accounts");
		
		driver.findElement(By.xpath("//input[@name=\"parent_name\"]/following-sibling::img")).click();
		
		wlib.switchToTabOnURL(driver, "Accounts&action");
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(orgName);
		driver.findElement(By.cssSelector("[name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		wlib.switchToTabOnURL(driver, "HelpDesk&action");
		
		//select product name
		driver.findElement(By.xpath("//input[@name=\"product_name\"]/following-sibling::img")).click();
		
		wlib.switchToTabOnURL(driver, "Products&action");
		driver.findElement(By.cssSelector("[name=\"search_text\"]")).sendKeys(productName);
		driver.findElement(By.cssSelector("[name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
		
		wlib.switchToTabOnURL(driver, "HelpDesk&action");
		
		wlib.selectByValue(driver.findElement(By.cssSelector("[name=\"ticketstatus\"]")), "Open");
		
		//save all the details
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify the header of trouble tkt information with expected 
		String actheader1 = driver.findElement(By.cssSelector("[class=\"dvHeaderText\"]")).getText();
		if(actheader1.contains(title)){
			System.out.println(title+" header is verified!!");
		}
		else {
			System.out.println(title+" is not verified!!");
		}
		
		//verift the tkt title with expected value
		String actTitle = driver.findElement(By.cssSelector("[id=\"dtlview_Title\"]")).getText();
		if(actTitle.contains(title)) {
			System.out.println(title+" is verified!!");
		}
		else {
			System.out.println(title+" is not verified!!");
		}
		
		//verify the orgName with expected value
		String actOrgNmae = driver.findElement(By.xpath("//a[text()='"+orgName+"']")).getText();
		if(actOrgNmae.contains(orgName)) {
			System.out.println(orgName+" is verified!!");
		}
		else {
			System.out.println(orgName+" is verified!!");
		}
		
		//verify the product name with expected value
		String actProduct = driver.findElement(By.cssSelector("[id=\"dtlview_Product Name\"]")).getText();
		if(actProduct.contains(productName)) {
			System.out.println(productName+" is verified!!");
		}
		else {
			System.out.println(productName+" is not verified!!");
		}
		
		//verify the status of tkt with expected value
		String actstatus = driver.findElement(By.cssSelector("[id=\"dtlview_Status\"]")).getText();
		
		if(actstatus.contains(status)) {
			System.out.println(status+" status is verified!!");
		}
		else {
			System.out.println(status+" status is not verified");
		}
		
		driver.quit();
	}

}
