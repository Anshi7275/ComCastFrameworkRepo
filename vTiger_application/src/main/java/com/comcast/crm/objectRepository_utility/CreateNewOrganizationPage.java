package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPage {
	
WebDriver driver;
	
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	//create new orgname
	@FindBy(name = "accountname")
	private WebElement orgName;

	public WebElement getOrgName() {
		return orgName;
	}
	
	//industry and type dropdown
	@FindBy(name = "industry")
	private WebElement industrydropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typedropDown;
	
	//save btn
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement save;
	
	public WebElement getSave() {
		return save;
	}

	public WebElement getIndustry() {
		return industrydropDown;
	}

	public WebElement getType() {
		return typedropDown;
	}
	
	//contact text
	@FindBy(css = "[name=\"phone\"]")
	private WebElement contacttxt;
	
	public WebElement getContacttxt() {
		return contacttxt;
	}

	//search orgname and dropdown
	@FindBy(css = "[name=\"search_text\"]")
	private WebElement searchtxt;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	
	@FindBy(name = "submit")
	private WebElement searchbtn;

	public WebElement getSearchtxt() {
		return searchtxt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearch() {
		return searchbtn;
	}
	
	//some extra methods
	@FindBy(id = "email1")
	private WebElement emailtxt;

	public WebElement getEmailtxt() {
		return emailtxt;
	}

	public void createNewOrg(String orgname)
	{
		orgName.sendKeys(orgname);
	}
	

}
