package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OppurtunitiesPage {
	
	WebDriver driver;
	
	public OppurtunitiesPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//img[@alt=\"Create Opportunity...\"]")
	private WebElement createOpperIcon;

	public WebElement getCreateOpperIcon() {
		return createOpperIcon;
	}
	
}
