package com.practice.coding_Standards;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.LoginPage;

/**
 * test class for contact module
 * @author Anshika
 */
public class SearchContactTest extends BaseClass {
	
	/**
	 * Scenarios: Login()--> navigate to contact-->create new contact-->verify
	 */
	@Test
	public void searchContactTest() {
		
		/* step1: login to app*/
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("admin", "admin");
	}

}
