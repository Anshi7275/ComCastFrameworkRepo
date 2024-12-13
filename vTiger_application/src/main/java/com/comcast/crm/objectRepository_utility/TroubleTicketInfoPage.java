package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TroubleTicketInfoPage {
	
	@FindBy(css = "[id=\"dtlview_Title\"]")
	private WebElement title;
	
	@FindBy(css = "[id=\"dtlview_Status\"]")
	private WebElement status;

	public WebElement getTitle() {
		return title;
	}

	public WebElement getStatus() {
		return status;
	}
	
	

}
