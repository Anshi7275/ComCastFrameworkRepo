package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInfoPage {
	
	WebDriver driver;
	public DocumentInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//span[@id=\"dtlview_Title\"]")
	private WebElement titletxtfeild;
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getTitletxtfeild() {
		return titletxtfeild;
	}

	public void setTitletxtfeild(WebElement titletxtfeild) {
		this.titletxtfeild = titletxtfeild;
	}
	
	

}
