package com.comcast.crm.Test_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.VendorNamePage;
import com.comcast.crm.objectRepository_utility.VendornameWindowPage;

public class CreateProductWithVendorName {

	public static void main(String[] args) throws Throwable {

		// read data from property file

		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		String productName = elib.getDatafromExcelfle("product", 7, 2) + jlib.getRandomNumber();
		String vendorname = elib.getDatafromExcelfle("product", 7, 3) + jlib.getRandomNumber();

		WebDriver driver = null;
		if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		wlib.maximizeWindow(driver);
		wlib.waitPageLoad(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);

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
		
		driver.findElement(By.xpath("//a[text()='"+vendorname+"']")).click();

		wlib.switchToTabOnURL(driver, "Products&action");

		cp.getSavebtn().click();

		// verification
		ProductInfoPage pi = new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();

		if (actheader.contains(productName)) {
			System.out.println(productName + " verified!!!");
		} else {
			System.out.println(productName + " not verified!!!");
		}

		String ActProductName = pi.getProductname().getText();
		if (ActProductName.contains(productName)) {
			System.out.println("product name verified!!!");
		} else {
			System.out.println("product name is not verified!!!");
		}

		String actVendorName = pi.getVendorName().getText();
		if (actVendorName.contains(vendorname)) {
			System.out.println(vendorname + " is verified!!");
		} else {
			System.out.println(vendorname + " is not verified!!");
		}

		hp.logout();
		driver.quit();

	}

}
