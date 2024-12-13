package com.crmVTiger.moduleTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.VendorNamePage;
import com.comcast.crm.objectRepository_utility.VendornameWindowPage;

/**
 * Test script for Create product module
 * @author Anshika
 */
@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateProductTest extends BaseClass {

	/**
	 * Scenarios 1: Login()--> navigate to product-->create new product with portNo-->verify
	 * Scenarios 2: Login()--> navigate to product-->create new product with product catogary-->verify
	 * Scenarios 3: Login()--> navigate to product-->create new product with vendor name-->verify
	 *     precondition: atleast one vendor name should be created
	 * @throws Throwable
	 */
	@Test
	public void createProductWithPortnoTest() throws Throwable {

		/*Read data from excel*/
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String productName = elib.getDatafromExcelfle("product", 4, 2) + jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4, 3) + jlib.getRandomNumber();
		
		/*navigate to product link*/
		UtilityClassObject.getTest().log(Status.INFO, "Login-----> navigate to Product");
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		/*click on create product button*/
		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();
		
		/*Create new product and enter all details*/
		UtilityClassObject.getTest().log(Status.INFO, "Create new product----->Enter all the details");
		CreateProductPage ccp = new CreateProductPage(driver);
		ccp.createproduct(productName, portno);
		
		/*Save all details*/
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ccp.getSavebtn().click();
		
		/*verification of product header msg*/
		jlib.wait(1000);
		UtilityClassObject.getTest().log(Status.INFO, "Verification  of product started!!!");
		ProductInfoPage pi = new ProductInfoPage(driver);
		
		String actheader = pi.getProductheader().getText();
		boolean headerText = actheader.contains(productName);
		
		Assert.assertEquals(headerText, true);
		System.out.println("===header got verified!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "Header msg got verified!!!");

	}

	@Test
	public void createProductWithCatogryTest() throws Throwable {

		/*Enter data from excel*/
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String productName = elib.getDatafromExcelfle("product", 1, 2) + jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 1, 3);

		/*navigate to product link*/
		UtilityClassObject.getTest().log(Status.INFO, "Login-----> navigate to product");
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		/*click on create product button*/
		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		/*create new product and enter all details along with product catogry*/
		UtilityClassObject.getTest().log(Status.INFO, "Create new product----> Enter all details");
		CreateProductPage cp = new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);

		jlib.wait(1000);

		WebElement catogryDD = cp.getCatorgyDropDown();
		wlib.selectByValue(catogryDD, "Hardware");
		jlib.wait(1000);

		/*save all details*/
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		cp.getSavebtn().click();

		/*verification of product header msg*/
		UtilityClassObject.getTest().log(Status.INFO, "Verification of product started!!!");
		ProductInfoPage pi = new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		boolean producttextmsg = ActProductName.contains(productName);
		
		Assert.assertEquals(producttextmsg, true);
		System.out.println("===product name get verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Product name got verified!!!");
		
		/*verification of product catogry msg*/
		String ActProductCatogry=pi.getProductcatogry().getText();
		boolean catogryText = ActProductCatogry.contains(productCatogry);
		
		Assert.assertTrue(catogryText);
		System.out.println("===product catogry got verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Product catogry got verified!!!");
	}

}
