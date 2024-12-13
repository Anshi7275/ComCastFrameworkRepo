package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author anshika
 * 
 * contains Login page elements and business libraries like login()
 */
public class LoginPage {
	
	//++++++++++++++++++++++++++++Rule 1: create saprate java class++++++++++++++++++++++++
	
	//++++++++++++++++++++++++++++Rule 2: Object creation++++++++++++++++++++++++++++++++++
	
	WebDriver driver;  //global variable
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(name="user_name")
	private WebElement username;
	
	@FindBy(name="user_password")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;

	
	//++++++++++++++++++++++++++++++Rule 3: object initialization++++++++++++++++++++++++++++
	
	//++++++++++++++++++++++++++++++Rule 4: Object encapsulation+++++++++++++++++++++++++++++
	
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	//++++++++++++++++++++++++++++++++Rule 5:Object Utilization+++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++provide Action++++++++++++++++++++++++++++++++++++++++++
	
	/**
	 * login to application based on username and password
	 * @param user_name
	 * @param pass_word
	 */
	public void loginToapp(String user_name, String pass_word) {
		
		username.sendKeys(user_name);
		password.sendKeys(pass_word);
		loginbtn.click();
		
	}

}
