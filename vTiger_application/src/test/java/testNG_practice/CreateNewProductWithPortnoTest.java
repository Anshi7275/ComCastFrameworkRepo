package testNG_practice;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateNewProductWithPortnoTest extends BaseClass {
	
	@Test
	
	public void createProductWithPortnoTest() throws Throwable {
		
		String productName = elib.getDatafromExcelfle("product", 4, 2)+jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4,3)+jlib.getRandomNumber();
		
		HomePage hp=new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
		
		CreateProductPage ccp=new CreateProductPage(driver);
		ccp.createproduct(productName, portno);
		ccp.getSavebtn();
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();
		if(actheader.contains(productName))
		{
			System.out.println(productName+" verified!!!");
		}
		else
		{
			System.out.println(productName+" not verified!!!");
		}
		
	}

}
