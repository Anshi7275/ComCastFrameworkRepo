package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorNamePage {
	
	WebDriver driver;
	
	public VendorNamePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name = "vendorname")
	private WebElement vendornametxt;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	public WebElement getVendornametxt() {
		return vendornametxt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	

}
