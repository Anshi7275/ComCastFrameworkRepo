package assertion_Practices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.VendorNamePage;
import com.comcast.crm.objectRepository_utility.VendornameWindowPage;

public class CreateProductWithVendorNameTest extends BaseClass {
	
	@Test
	public void createProductWithVendorNameTest() throws Throwable {
		
		wlib.waitPageLoad(driver);
		
		String productName = elib.getDatafromExcelfle("product", 7, 2)+jlib.getRandomNumber();
		String vendorname = elib.getDatafromExcelfle("product", 7, 3)+jlib.getRandomNumber();
		
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
		boolean headerMsg = actheader.contains(productName);
		
		Assert.assertEquals(headerMsg, true);
		
		
		String ActProductName = pi.getProductname().getText();
		boolean productname = ActProductName.contains(vendorname);
		assertEquals(productname, vendorname);
		//assertTrue(true);
			System.out.println("product name verified!!!");
		
		String actVendorName = pi.getVendorName().getText();
		boolean vendornametext = actVendorName.contains(vendorname);
		
		assertEquals(vendornametext, vendorname);
		
			System.out.println(vendorname + " is verified!!");
		
		driver.quit();
	}

}
