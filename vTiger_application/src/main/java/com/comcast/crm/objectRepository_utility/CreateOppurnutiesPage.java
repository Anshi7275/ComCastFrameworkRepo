package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOppurnutiesPage {
	
WebDriver driver;
	
	public CreateOppurnutiesPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name = "potentialname")
	private WebElement oppornutyName;
	
	@FindBy(id = "related_to_type")
	private WebElement relateddropdown;

	public WebElement getOppornutyName() {
		return oppornutyName;
	}

	public WebElement getRelateddropdown() {
		return relateddropdown;
	}
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	public WebElement getSavebtn() {
		return savebtn;
	}
	@FindBy(xpath = "//img[@src=\"themes/softed/images/select.gif\"]")
	private WebElement pulseIcon;

	public WebElement getPulseIcon() {
		return pulseIcon;
	}

}
