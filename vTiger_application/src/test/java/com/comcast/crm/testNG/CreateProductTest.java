package com.comcast.crm.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.VendorNamePage;
import com.comcast.crm.objectRepository_utility.VendornameWindowPage;

public class CreateProductTest extends BaseClass {

	@Test

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
		if (actheader.contains(productName)) {
			System.out.println(productName + " verified!!!");
		} 
		else {
			System.out.println(productName + " not verified!!!");
		}

	}

	@Test
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
		if (ActProductName.contains(productName)) {
			System.out.println("product name verified!!!");
		} 
		else {
			System.out.println("product name is not verified!!!");
		}

		String actProduct = pi.getProductcatogry().getText();
		if (actProduct.contains(productCatogry)) {
			System.out.println("product catogry verified!!!");
		} 
		else {
			System.out.println("product catogry is not verified!!!");
		}
	}

	@Test
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

		if (actheader.contains(productName)) {
			System.out.println(productName + " verified!!!");
		} 
		else {
			System.out.println(productName + " not verified!!!");
		}

		String ActProductName = pi.getProductname().getText();
		if (ActProductName.contains(productName)) {
			System.out.println("product name verified!!!");
		} 
		else {
			System.out.println("product name is not verified!!!");
		}

		String actVendorName = pi.getVendorName().getText();
		if (actVendorName.contains(vendorname)) {
			System.out.println(vendorname + " is verified!!");
		} 
		else {
			System.out.println(vendorname + " is not verified!!");
		}
	}

}
