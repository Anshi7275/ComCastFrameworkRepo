package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesInfoPage {
	
    WebDriver driver;  //global variable
	
	public OpportunitiesInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(css = "[id=\"dtlview_Opportunity Name\"]")
	private WebElement oppName;
	
	@FindBy(css = "[title=\"Organizations\"]")
	private WebElement orgName;
	
	@FindBy(css = "[class=\"dvHeaderText\"]")
	private WebElement headerOppName;

	public WebElement getOppName() {
		return oppName;
	}

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getHeaderOppName() {
		return headerOppName;
	}
	
	

}
