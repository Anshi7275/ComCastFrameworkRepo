package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
WebDriver driver;  //global variable
	
	public ContactInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement productmsg;
	
	@FindBy(css = "[class=\"dvHeaderText\"]")
	private WebElement pheadermsg;

	public WebElement getProductmsg() {
		return productmsg;
	}

	public WebElement getPheadermsg() {
		return pheadermsg;
	}
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgName;

	public WebElement getOrgName() {
		return orgName;
	}
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement startDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement enddate;

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEnddate() {
		return enddate;
	}
	

}
