package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author anshika
 * 
 * contains home page elements like organization link,contact link, oppurnutity link.. etc 
 * with bussiness libraries like logout()
 */
public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	//organization link
	@FindBy(xpath = "//a[text()='Organizations']")
	private WebElement organization;

	//getter methods
	public WebElement getOrganization() {
		return organization;
	}
	
	//contact link
	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;

	public WebElement getContactlnk() {
		return contactlnk;
	}
	
	//opernutities link
	@FindBy(linkText = "Opportunities")
	private WebElement Opportunitieslnk;

	public WebElement getOpportunitieslnk() {
		return Opportunitieslnk;
	}
	
	//product link
	@FindBy(linkText = "Products")
	private WebElement productlnk;

	public WebElement getProductlnk() {
		return productlnk;
	}
	
	//trouble ticket link
	@FindBy(linkText = "Trouble Tickets")
	private WebElement troubletktlnk;

	public WebElement getTroubletktlnk() {
		return troubletktlnk;
	}
	
	//document link
	@FindBy(linkText = "Documents")
	private WebElement documentlnk;
	
	public WebElement getDocumentlnk() {
		return documentlnk;
	}

	//more link
	@FindBy(xpath = "//a[text()='More']")
	private WebElement morelnk;

	public WebElement getMorelnk() {
		return morelnk;
	}
	
	@FindBy(xpath = "//a[text()='Vendors']")
	private WebElement vendorlnk;

	public WebElement getVendorlnk() {
		return vendorlnk;
	}
	
	@FindBy(xpath = "//select[@id=\"qccombo\"]")
	private WebElement quickLink;
	
	public WebElement getQuickLink() {
		return quickLink;
	}

	//log-out
	@FindBy(xpath = "//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement adminImg;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement singOut;

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSingOut() {
		return singOut;
	}
	
	/**
	 * Logout to the application
	 */
	public void logout()
	{
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		adminImg.click();
		act.moveToElement(singOut).perform();
		singOut.click();
	}

}
