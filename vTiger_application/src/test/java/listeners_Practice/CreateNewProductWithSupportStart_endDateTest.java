package listeners_Practice;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateNewProductWithSupportStart_endDateTest extends BaseClass {

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createProductWithdatesTest() throws Throwable {
		String productName = elib.getDatafromExcelfle("product", 4, 2) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getCreateproductIcon().click();
				
		CreateProductPage cp=new CreateProductPage(driver);
		cp.getProductnametxt().sendKeys(productName);

		String Startdate = jlib.getSystemdate();
		String salesEndDate = jlib.getRequiredDate(30);
		String expiryDate = jlib.getRequiredDataByYear(1);
		
		cp.getSalesStartdatetxt().clear();
		cp.getSalesStartdatetxt().sendKeys(Startdate);
		
		cp.getSalesEnddate().clear();
		cp.getSalesEnddate().sendKeys(salesEndDate);
		
		cp.getStartdate().clear();
		cp.getStartdate().sendKeys(Startdate);
		
		cp.getExpirydate().clear();
		cp.getExpirydate().sendKeys(expiryDate);
		cp.getSavebtn().click();
		
		// verifcation header msg with actual header msg

		ProductInfoPage pi=new ProductInfoPage(driver);
		String ActProductName = pi.getProductname().getText();
		boolean productmsg = ActProductName.contains(productName);
		
		Assert.assertEquals(productmsg, true);
		
		if(ActProductName.contains(productName))
		System.out.println("product name verified!!!");
		
		
		String actSalestartDate = pi.getSalestartDate().getText();
		boolean startDateMsg = actSalestartDate.contains(Startdate);
		
		assertEquals(startDateMsg, Startdate);
		   System.out.println(Startdate+" is verified!!");
		
		String actSaleEndDate = pi.getSaleEndDate().getText();
		boolean saleEnddatemsg = actSaleEndDate.contains(salesEndDate);
		
		assertEquals(saleEnddatemsg, salesEndDate);
			System.out.println(salesEndDate+" is verified!!");
		
		String actStartDate = pi.getSupportStartDate().getText();
		boolean startdatemsg = actStartDate.contains(Startdate);
		
		assertEquals(startdatemsg, Startdate);
		
			System.out.println(Startdate+" is verified!!");
		
		String actEndDate = pi.getSupportEndDate().getText();
		boolean expiryendDatemsg = actEndDate.contains(expiryDate);
		
		assertEquals(expiryendDatemsg, expiryDate);
		
			System.out.println(expiryDate+" is verified!!");
		
	}

}
