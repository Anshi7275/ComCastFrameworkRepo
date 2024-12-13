package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	
    WebDriver driver;
	
	public CreateProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name = "productname")
	private WebElement productnametxt;
	
	@FindBy(name = "sales_start_date")
	private WebElement salesStartdatetxt;
	
	@FindBy(name = "sales_end_date")
	private WebElement salesEnddate;
	
	@FindBy(name = "start_date")
	private WebElement startdate;
	
	@FindBy(name = "expiry_date")
	private WebElement expirydate;
	
	@FindBy(name = "productcategory")
	private WebElement catogry;
	
	@FindBy(xpath = "//img[@title=\"Select\"]")
	private WebElement vendornameIcon;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;
	
	@FindBy(name = "vendor_part_no")
	private WebElement partno;
	
	@FindBy(css = "[name=\"productcategory\"]")
	private WebElement catorgyDropDown;

	public WebElement getCatorgyDropDown() {
		return catorgyDropDown;
	}

	public WebElement getProductnametxt() {
		return productnametxt;
	}

	public WebElement getSalesStartdatetxt() {
		return salesStartdatetxt;
	}

	public WebElement getSalesEnddate() {
		return salesEnddate;
	}

	public WebElement getStartdate() {
		return startdate;
	}

	public WebElement getExpirydate() {
		return expirydate;
	}

	public WebElement getCatogry() {
		return catogry;
	}

	public WebElement getVendornameIcon() {
		return vendornameIcon;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public WebElement getPartno() {
		return partno;
	}

	public void createproduct(String productName, String portno ) 
	{
		productnametxt.sendKeys(productName);
		partno.sendKeys(portno);
		vendornameIcon.click();
	}
	
	public void createproduct(String productCatogry)
	{
		catogry.sendKeys(productCatogry);
	}
	
	
}
