package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	
WebDriver driver;  //global variable
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(css = "[id=\"dtlview_Product Name\"]")
	private WebElement productname;
	
	@FindBy(css = "[class=\"lvtHeaderText\"]")
	private WebElement productheader;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getProductname() {
		return productname;
	}

	public WebElement getProductheader() {
		return productheader;
	}
	
	@FindBy(xpath = "//span[@id=\"dtlview_Product Category\"]")
	private WebElement productcatogry;

	public WebElement getProductcatogry() {
		return productcatogry;
	}
	
	@FindBy(css = "[id=\"dtlview_Vendor Part No\"]")
	private WebElement portno;
	
	@FindBy(css = "[id=\"dtlview_Sales Start Date\"]")
	private WebElement salestartDate;
	
	@FindBy(css = "[id=\"dtlview_Sales End Date\"]")
	private WebElement saleEndDate;
	
	@FindBy(css = "[id=\"dtlview_Support Start Date\"]")
	private WebElement supportStartDate;
	
	@FindBy(css = "[id=\"dtlview_Support Expiry Date\"]")
	private WebElement supportEndDate;

	public WebElement getPortno() {
		return portno;
	}

	public WebElement getSalestartDate() {
		return salestartDate;
	}

	public WebElement getSaleEndDate() {
		return saleEndDate;
	}

	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}
	
	@FindBy(css = "[id=\"mouseArea_Vendor Name\"]")
	private WebElement vendorName;

	public WebElement getVendorName() {
		return vendorName;
	}

}
