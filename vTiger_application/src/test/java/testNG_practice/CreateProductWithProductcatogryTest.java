package testNG_practice;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateProductWithProductcatogryTest extends BaseClass{
	
	@Test
	public void createProductWithCatogryTest() throws Throwable {
		
		String productName = elib.getDatafromExcelfle("product", 1, 2)+jlib.getRandomNumber();
		String productCatogry = elib.getDatafromExcelfle("product", 1, 3)+jlib.getRandomNumber();
		
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
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		if(ActProductName.contains(productName))
		{
			System.out.println("product name verified!!!");
		}
		else
		{
			System.out.println("product name is not verified!!!");
		}
		
		String actProduct = pi.getProductcatogry().getText();
		if(actProduct.contains(productCatogry))
		{
			System.out.println("product catogry verified!!!");
		}
		else
		{
			System.out.println("product catogry is not verified!!!");
		}
	}

}
