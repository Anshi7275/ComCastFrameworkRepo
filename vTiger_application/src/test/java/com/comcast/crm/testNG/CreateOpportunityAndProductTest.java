package com.comcast.crm.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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


public class CreateOpportunityAndProductTest extends BaseClass {

	@Test
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
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);
		HEADERMSG.headermsg(orgName);

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
		OpportunitiesInfoPage oi = new OpportunitiesInfoPage(driver);
		
		String actHeader = oi.getHeaderOppName().getText();
		if (actHeader.contains(opportunitiesName)) {
			System.out.println(opportunitiesName + " is verified!!");
		} 
		else {
			System.out.println(opportunitiesName + " is not verified!!");
		}

		String actOpp = oi.getOppName().getText();
		if (actOpp.contains(opportunitiesName)) {
			System.out.println(opportunitiesName + " is verified!!");
		} 
		else {
			System.out.println(opportunitiesName + " is not verified!!");
		}

		String actorgName = oi.getOrgName().getText();
		if (actorgName.contains(orgName)) {
			System.out.println(orgName + " is verified!!!");
		} 
		else {
			System.out.println(orgName + " is not verified!!");
		}

	}

	@Test
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
		if (ActProductName.contains(productName)) {
			System.out.println("product name verified!!!");
		} 
		else {
			System.out.println("product name is not verified!!!");
		}
		
		String actSalestartDate = pi.getSalestartDate().getText();
		if (actSalestartDate.contains(Startdate)) {
			System.out.println(Startdate + " is verified!!");
		} 
		else {
			System.out.println(Startdate + " is not verified!!");
		}

		String actSaleEndDate = pi.getSaleEndDate().getText();
		if (actSaleEndDate.contains(salesEndDate)) {
			System.out.println(salesEndDate + " is verified!!");
		} 
		else {
			System.out.println(salesEndDate + " is not verified!!");
		}

		String actStartDate = pi.getSupportStartDate().getText();
		if (actStartDate.contains(Startdate)) {
			System.out.println(Startdate + " is verified!!");
		} 
		else {
			System.out.println(Startdate + " is not verified!!");
		}

		String actEndDate = pi.getSupportEndDate().getText();
		if (actEndDate.contains(expiryDate)) {
			System.out.println(expiryDate + " is verified!!");
		} 
		else {
			System.out.println(expiryDate + " is not verified!!");
		}

	}
}
