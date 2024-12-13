package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
    WebDriver driver;
	
	public CreateContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnametxt;
	
	//support date
	@FindBy(name = "support_start_date")
	private WebElement startdatetext;
	
	@FindBy(name = "support_end_date")
	private WebElement enddatetxt;
	
	@FindBy(xpath = "//input[@name=\"account_name\"]/following-sibling::img")
	private WebElement orgIcon;

	public WebElement getLastnametxt() {
		return lastnametxt;
	}

	public WebElement getStartdatetext() {
		return startdatetext;
	}

	public WebElement getEnddatetxt() {
		return enddatetxt;
	}

	public WebElement getOrgIcon() {
		return orgIcon;
	}
	
	//save 
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	

}
