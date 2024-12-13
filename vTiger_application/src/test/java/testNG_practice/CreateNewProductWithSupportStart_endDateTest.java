package testNG_practice;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

public class CreateNewProductWithSupportStart_endDateTest extends BaseClass {

	@Test
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
		if(ActProductName.contains(productName))
		{
			System.out.println("product name verified!!!");
		}
		else
		{
			System.out.println("product name is not verified!!!");
		}
		String actSalestartDate = pi.getSalestartDate().getText();
		if(actSalestartDate.contains(Startdate)) 
		{
			System.out.println(Startdate+" is verified!!");
		}
		else
		{
			System.out.println(Startdate+" is not verified!!");
		}
		
		String actSaleEndDate = pi.getSaleEndDate().getText();
		if(actSaleEndDate.contains(salesEndDate))
		{
			System.out.println(salesEndDate+" is verified!!");
		}
		else
		{
			System.out.println(salesEndDate+" is not verified!!");
		}
		
		String actStartDate = pi.getSupportStartDate().getText();
		if(actStartDate.contains(Startdate))
		{
			System.out.println(Startdate+" is verified!!");
		}
		else
		{
			System.out.println(Startdate+" is not verified!!");
		}
		
		String actEndDate = pi.getSupportEndDate().getText();
		if(actEndDate.contains(expiryDate))
		{
			System.out.println(expiryDate+" is verified!!");
		}
		else
		{
			System.out.println(expiryDate+" is not verified!!");
		}	
		
	}

}
