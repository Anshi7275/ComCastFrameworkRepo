package com.comcast.crm.Extent_Report;

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

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateProductTest extends BaseClass {

	@Test
	public void createProductWithPortnoTest() throws Throwable {

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String productName = elib.getDatafromExcelfle("product", 4, 2) + jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4, 3) + jlib.getRandomNumber();
		
		UtilityClassObject.getTest().log(Status.INFO, "Login-----> navigate to Product");
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create new product----->Enter all the details");
		CreateProductPage ccp = new CreateProductPage(driver);
		ccp.createproduct(productName, portno);
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		ccp.getSavebtn().click();
		
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

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String productName = elib.getDatafromExcelfle("product", 1, 2) + jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 1, 3);

		UtilityClassObject.getTest().log(Status.INFO, "Login-----> navigate to product");
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create new product----> Enter all details");
		CreateProductPage cp = new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);

		jlib.wait(1000);

		WebElement catogryDD = cp.getCatorgyDropDown();
		wlib.selectByValue(catogryDD, "Hardware");
		jlib.wait(1000);

		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		cp.getSavebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Verification of product started!!!");
		ProductInfoPage pi = new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		boolean producttextmsg = ActProductName.contains(productName);
		
		Assert.assertEquals(producttextmsg, true);
		System.out.println("===product name get verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Product name got verified!!!");
		
		String ActProductCatogry=pi.getProductcatogry().getText();
		boolean catogryText = ActProductCatogry.contains(productCatogry);
		
		Assert.assertTrue(catogryText);
		System.out.println("===product catogry got verified!!!===");
		UtilityClassObject.getTest().log(Status.PASS, "Product catogry got verified!!!");
	}

	@Test
	public void createProductWithVendorNameTest() throws Throwable {

		wlib.waitPageLoad(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel file");
		String productName = elib.getDatafromExcelfle("product", 7, 2) + jlib.getRandomNumber();
		String vendorname = elib.getDatafromExcelfle("product", 7, 3) + jlib.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Login-----> navigate to vendors");
		HomePage hp = new HomePage(driver);
		WebElement dropdown = hp.getQuickLink();
		wlib.selectByValue(dropdown, "Vendors");

		UtilityClassObject.getTest().log(Status.INFO, "create new vendors----->Enter all details");
		VendorNamePage vp = new VendorNamePage(driver);
		vp.getVendornametxt().sendKeys(vendorname);
		
		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		vp.getSavebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to product");
		hp.getProductlnk().click();
		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create new product-----> Enter all details along with vendor name");
		CreateProductPage cp = new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);
		cp.getVendornameIcon().click();

		wlib.switchToTabOnURL(driver, "Vendors&action");

		UtilityClassObject.getTest().log(Status.INFO, "Switch to Vendor tab----->Select the vendor name");
		VendornameWindowPage vnp = new VendornameWindowPage(driver);
		vnp.getSearchtxt().sendKeys(vendorname);
		vnp.getSearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + vendorname + "']")).click();

		wlib.switchToTabOnURL(driver, "Products&action");

		UtilityClassObject.getTest().log(Status.INFO, "Save all details");
		cp.getSavebtn().click();

		// verification
		UtilityClassObject.getTest().log(Status.INFO, "Verification of product started!!!!");
		ProductInfoPage pi = new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();

		boolean headerText = actheader.contains(productName);
		
		Assert.assertEquals(headerText, true);
		System.out.println("===header got verified!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "Header msg got verified!!!");

		String ActProductName = pi.getProductname().getText();
		boolean productNametext = ActProductName.contains(productName);
		
		Assert.assertEquals(productNametext, true);
		System.out.println("===product name got verififed!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "Product name got verified!!!");

		String actVendorName = pi.getVendorName().getText();
		boolean vendorNametext = actVendorName.contains(vendorname);
		
		Assert.assertEquals(vendorNametext, true);
		System.out.println("===vendor name text feild got verififed!!!===");
		UtilityClassObject.getTest().log(Status.INFO, "Vendor name got verified!!!");
		
	}

}
