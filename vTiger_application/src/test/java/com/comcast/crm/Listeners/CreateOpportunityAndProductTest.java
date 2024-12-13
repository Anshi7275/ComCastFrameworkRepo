package com.comcast.crm.Listeners;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.CreateOppurnutiesPage;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OpportunitiesInfoPage;
import com.comcast.crm.objectRepository_utility.OppurtunitiesPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductPage;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateOpportunityAndProductTest extends BaseClass {

	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOppWithOrgNameTest() throws Throwable {
		wlib.waitPageLoad(driver);

		String opportunitiesName = elib.getDatafromExcelfle("oppurnities", 4, 2) + jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("oppurnities", 4, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();

		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(orgName);
		cnp.getSave().click();

		// verifcation
		OrganizationInformationPage oi = new OrganizationInformationPage(driver);
		String actHeaderMsg = oi.getHeadermsg().getText();
		boolean headerMsg = actHeaderMsg.contains(orgName);
		
		Assert.assertEquals(headerMsg, true);
		System.out.println("===header msg got verified!!!===");

		jlib.wait(1000);

		hp.getOpportunitieslnk().click();
		OppurtunitiesPage opp = new OppurtunitiesPage(driver);
		opp.getCreateOpperIcon().click();

		CreateOppurnutiesPage cop = new CreateOppurnutiesPage(driver);
		cop.getOppornutyName().sendKeys(opportunitiesName);
		WebElement dropdown = cop.getRelateddropdown();
		wlib.selectByValue(dropdown, "Accounts");

		cop.getPulseIcon().click();
		OrganizationWindowPage owp = new OrganizationWindowPage(driver);
		wlib.switchToTabOnURL(driver, "Accounts&action");

		owp.getSearchtxt().sendKeys(orgName);
		owp.getSearchbtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wlib.switchToTabOnURL(driver, "Potentials&action");
		cop.getSavebtn().click();

		// verification
		OpportunitiesInfoPage oip = new OpportunitiesInfoPage(driver);
		
		String actHeader = oip.getHeaderOppName().getText();
		boolean headermsg = actHeader.contains(opportunitiesName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===header msg got verified!!!===");

		String actOpp = oip.getOppName().getText();
		boolean oppTextMsg = actOpp.contains(opportunitiesName);
		
		Assert.assertEquals(oppTextMsg, true);
		System.out.println("===Oppertunity name got verified!!!===");

		String actorgName = oip.getOrgName().getText();
		boolean orgnameMsg = actorgName.contains(orgName);
		
		Assert.assertEquals(orgnameMsg, true);
		System.out.println("===org name got verified!!!===");

	}

	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createProductWithdatesTest() throws Throwable {
		String productName = elib.getDatafromExcelfle("product", 4, 2) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		CreateProductPage cp = new CreateProductPage(driver);
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

		ProductInfoPage pi = new ProductInfoPage(driver);
		
		String ActProductName = pi.getProductname().getText();
		boolean productNameText = ActProductName.contains(productName);	
		
		Assert.assertEquals(productNameText, true);
		System.out.println(productName+"product name verified!!!!");
		
		String actSalestartDate = pi.getSalestartDate().getText();
		boolean startDateText = actSalestartDate.contains(Startdate);
		
		Assert.assertEquals(startDateText, true);
		System.out.println(Startdate+"date is verified!!!");
		
		String actSaleEndDate = pi.getSaleEndDate().getText();
		boolean endDateText = actSaleEndDate.contains(salesEndDate);
		
		Assert.assertEquals(endDateText, true);
		System.out.println(salesEndDate+"end date is verified!!!");
		
		String actStartDate = pi.getSupportStartDate().getText();
		boolean startDtaemsg = actStartDate.contains(Startdate);
		
		Assert.assertEquals(startDtaemsg, true);
		System.out.println(Startdate+"start date is verified!!!");

		String actEndDate = pi.getSupportEndDate().getText();
		boolean endDateMsg = actEndDate.contains(expiryDate);
		
		Assert.assertEquals(endDateMsg, true);
		System.out.println(expiryDate+"expiry date is verified!!!");

	}
}
