package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TroubleTicketPage {
	
WebDriver driver;
	
	public TroubleTicketPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	@FindBy(xpath = "//img[@alt=\"Create Ticket...\"]")
	private WebElement tktIcon;

	public WebElement getTktIcon() {
		return tktIcon;
	}
	
	

}
