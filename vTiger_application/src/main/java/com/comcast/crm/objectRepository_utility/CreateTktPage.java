package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateTktPage {
	
WebDriver driver;
	
	public CreateTktPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name = "ticket_title")
	private WebElement tktTitle;
	
	@FindBy(name = "parent_type")
	private WebElement dropdown;
	
	@FindBy(name = "ticketstatus")
	private WebElement statusdropdown;
	
	@FindBy(xpath = "//input[@name=\"parent_name\"]/following-sibling::img")
	private WebElement dropdownIcon;
	
	@FindBy(xpath = "//input[@name=\"product_name\"]/following-sibling::img")
	private WebElement pnameIcon;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	public WebElement getTktTitle() {
		return tktTitle;
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getStatusdropdown() {
		return statusdropdown;
	}

	public WebElement getDropdownIcon() {
		return dropdownIcon;
	}

	public WebElement getPnameIcon() {
		return pnameIcon;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	

}
