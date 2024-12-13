package com.comcast.crm.Listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.VendorNamePage;
import com.comcast.crm.objectRepository_utility.VendornameWindowPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateProductTest extends BaseClass {

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)

	public void createProductWithPortnoTest() throws Throwable {

		String productName = elib.getDatafromExcelfle("product", 4, 2) + jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		CreateProductPage ccp = new CreateProductPage(driver);
		ccp.createproduct(productName, portno);
		ccp.getSavebtn();

		ProductInfoPage pi = new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();
		boolean headerMsg = actheader.contains(productName);
		
		Assert.assertEquals(headerMsg, true);
		System.out.println("===header msg got verified!!!===");

	}

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createProductWithCatogryTest() throws Throwable {

		String productName = elib.getDatafromExcelfle("product", 3, 2) + jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 3, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		CreateProductPage cp = new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);

		jlib.wait(1000);

		WebElement catogryDD = cp.getCatorgyDropDown();
		wlib.selectByValue(catogryDD, "Hardware");
		jlib.wait(1000);

		cp.getSavebtn().click();

		ProductInfoPage pi = new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		boolean producttextmsg = ActProductName.contains(productName);
		
		Assert.assertEquals(producttextmsg, true);
		System.out.println("===product name get verified!!!===");
		
		String ActProductCatogry = pi.getProductcatogry().getText();
		boolean catogryText = ActProductCatogry.contains(productCatogry);
		
		Assert.assertEquals(catogryText, true);
		System.out.println("===product catogry got verified!!!===");
	}

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createProductWithVendorNameTest() throws Throwable {

		wlib.waitPageLoad(driver);

		String productName = elib.getDatafromExcelfle("product", 7, 2) + jlib.getRandomNumber();
		String vendorname = elib.getDatafromExcelfle("product", 7, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		WebElement dropdown = hp.getQuickLink();
		wlib.selectByValue(dropdown, "Vendors");

		VendorNamePage vp = new VendorNamePage(driver);
		vp.getVendornametxt().sendKeys(vendorname);
		vp.getSavebtn().click();

		hp.getProductlnk().click();
		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		CreateProductPage cp = new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);
		cp.getVendornameIcon().click();

		wlib.switchToTabOnURL(driver, "Vendors&action");

		VendornameWindowPage vnp = new VendornameWindowPage(driver);
		vnp.getSearchtxt().sendKeys(vendorname);
		vnp.getSearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + vendorname + "']")).click();

		wlib.switchToTabOnURL(driver, "Products&action");

		cp.getSavebtn().click();

		// verification
		ProductInfoPage pi = new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();

		boolean headerText = actheader.contains(productName);
		
		Assert.assertEquals(headerText, true);
		System.out.println("===header got verified!!!===");

		String ActProductName = pi.getProductname().getText();
		boolean productNametext = ActProductName.contains(productName);
		
		Assert.assertEquals(productNametext, true);
		System.out.println("===product name got verififed!!!===");

		String actVendorName = pi.getVendorName().getText();
		boolean vendorNametext = actVendorName.contains(vendorname);
		
		Assert.assertEquals(vendorNametext, true);
		System.out.println("===vendor name text feild got verififed!!!===");
		
	}

}
