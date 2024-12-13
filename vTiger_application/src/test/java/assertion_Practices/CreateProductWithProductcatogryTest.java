package assertion_Practices;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateProductWithProductcatogryTest extends BaseClass{
	
	@Test
	public void createProductWithCatogryTest() throws Throwable {
		
		String productName = elib.getDatafromExcelfle("product", 3, 2)+jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 3, 3)+jlib.getRandomNumber();
		
		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
				
		CreateProductPage cp=new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);
		
		jlib.wait(1000);
		
		WebElement catogryDD = cp.getCatorgyDropDown();
		wlib.selectByValue(catogryDD, "Hardware");
		jlib.wait(1000);
		
		cp.getSavebtn().click();
		
		//verification of product name and product catogry by softAssert
		ProductInfoPage pi=new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		boolean productnamemsg = ActProductName.contains(productName);
		
		assertEquals(productnamemsg, productName);
		    System.out.println("product name verified!!!");
		
		String actProduct = pi.getProductcatogry().getText();
		boolean productcat = actProduct.contains(productCatogry);
		
		assertEquals(productcat, productCatogry);
		
			System.out.println("product catogry verified!!!");
			
		driver.quit();	
	}

}
