package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	
	@FindBy(xpath = "//img[@alt=\"Create Product...\"]")
	private WebElement createproductIcon;

	public WebElement getCreateproductIcon() {
		return createproductIcon;
	}
	
	

}
