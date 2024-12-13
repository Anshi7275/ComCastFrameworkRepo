package listeners_Practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)

public class CreateNewProductWithPortnoTest extends BaseClass {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	
	public void createProductWithPortnoTest() throws Throwable {
		
		String productName = elib.getDatafromExcelfle("product", 4, 2)+jlib.getRandomNumber();
		String portno = elib.getDatafromExcelfle("product", 4,3)+jlib.getRandomNumber();
		
		HomePage hp=new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
		
		CreateProductPage ccp=new CreateProductPage(driver);
		ccp.getProductnametxt().sendKeys(productName);
		ccp.getPartno().sendKeys(portno);
		
		ccp.getSavebtn().click();
		
		ProductInfoPage pi=new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();
		boolean productMsg = actheader.contains(productName);
		
		Assert.assertEquals(productMsg, true);
		
		System.out.println(productName+" verified!!!");
		
	}

}
