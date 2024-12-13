package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
WebDriver driver;
	
	public OrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement create_org;

	public WebElement getCreate_org() {
		return create_org;
	}

}
