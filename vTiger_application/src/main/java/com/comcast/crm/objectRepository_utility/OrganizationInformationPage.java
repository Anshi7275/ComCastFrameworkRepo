package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
WebDriver driver;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	@FindBy(xpath = "//span[@id=\"dtlview_Organization Name\"]")
	private WebElement org_name;

	public WebElement getHeadermsg() {
		return headermsg;
	}
	
	public WebElement getOrg_name() {
		return org_name;
	}
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryMsg;
	
	public WebElement getIndustryMag() {
		return industryMsg;
	}
	
	@FindBy(id = "dtlview_Type")
	private WebElement typemsg;

	public WebElement getPhonemsg() {
		return phonemsg;
	}

	public WebElement getTypemsg() {
		return typemsg;
	}
	
	@FindBy(css = "[id=\"dtlview_Phone\"]")
	private WebElement phonemsg;

	public void headermsg(String orgName) 
	{
		if(headermsg.getText().contains(orgName))
		{
			System.out.println(orgName+" is created!!!!");
		}
		else
		{
			System.out.println(orgName+" is not created!!!!");
		}
		
	}
	
	public void organizationName(String orgname)
	{
		if(org_name.getText().contains(orgname))
		{
			System.out.println(orgname+" is created!!!!");
		}
		else
		{
			System.out.println(orgname+" is created!!!!");
		}
	}
	

}
