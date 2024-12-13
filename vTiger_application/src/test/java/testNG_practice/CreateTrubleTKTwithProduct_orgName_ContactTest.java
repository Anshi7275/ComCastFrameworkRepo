package testNG_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.CreateProductPage;
import com.comcast.crm.objectRepository_utility.CreateTktPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;
import com.comcast.crm.objectRepository_utility.ProductInfoPage;
import com.comcast.crm.objectRepository_utility.ProductNameWindowPage;
import com.comcast.crm.objectRepository_utility.ProductPage;
import com.comcast.crm.objectRepository_utility.TroubleTicketInfoPage;
import com.comcast.crm.objectRepository_utility.TroubleTicketPage;

public class CreateTrubleTKTwithProduct_orgName_ContactTest extends BaseClass {

	@Test
	public void createTroubletktTest() throws Throwable {
		
		wlib.waitPageLoad(driver);
		
		String title = elib.getDatafromExcelfle("TroubleTicket", 1, 2)+jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("TroubleTicket", 1, 3)+jlib.getRandomNumber();
		//String contactLastName = elib.getDatafromExcelfle("TroubleTicket", 1, 4)+jlib.getRandomNumber();
		String productName = elib.getDatafromExcelfle("TroubleTicket", 1, 5)+jlib.getRandomNumber();
		String status = elib.getDatafromExcelfle("TroubleTicket", 1, 5);
		
		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();

		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();

		// create new orgname and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);

		ORGNAME.getSave().click();

		jlib.wait(2000);

		// verifcation of header msg
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);

		HEADERMSG.organizationName(orgName);
		
		//navigate to product 
		hp.getProductlnk().click();

		ProductPage pp = new ProductPage(driver);
		pp.getCreateproductIcon().click();

		CreateProductPage ccp = new CreateProductPage(driver);
		ccp.getProductnametxt().sendKeys(productName);
		ccp.getSavebtn();

		ProductInfoPage pi = new ProductInfoPage(driver);
		String actheader = pi.getProductheader().getText();
		if (actheader.contains(productName)) {
			System.out.println(productName + " verified!!!");
		} 
		else {
			System.out.println(productName + " not verified!!!");
		}
		
		//navigate to trouble tkt

		hp.getTroubletktlnk().click();

		TroubleTicketPage tp = new TroubleTicketPage(driver);
		tp.getTktIcon().click();

		CreateTktPage ctp = new CreateTktPage(driver);
		ctp.getTktTitle().sendKeys(title);
		
		//select orgName with drop down
		WebElement parentDropDown = ctp.getDropdown();
		wlib.selectByValue(parentDropDown, "Accounts");

		ctp.getDropdownIcon().click();
		wlib.switchToTabOnURL(driver, "Accounts&action");

		OrganizationWindowPage owp = new OrganizationWindowPage(driver);
		owp.getSearchtxt().sendKeys(orgName);
		owp.getSearchbtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wlib.switchToTabOnURL(driver, "HelpDesk&action");
		
		//navigate to product txt feild
		ctp.getPnameIcon().click();
		
		wlib.switchToTabOnURL(driver, "Products&action");
		ProductNameWindowPage pnw=new ProductNameWindowPage(driver);
		pnw.getSearchtxt().sendKeys(productName);
		pnw.getSearchbtn().click();
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();

		wlib.switchToTabOnURL(driver, "HelpDesk&action");

		WebElement statusDropDown = ctp.getStatusdropdown();
		wlib.selectByValue(statusDropDown, "Open");

		ctp.getSavebtn().click();

		String actheader1 = driver.findElement(By.cssSelector("[class=\"dvHeaderText\"]")).getText();
		if (actheader1.contains(title)) {
			System.out.println(title + " header is verified!!");
		} else {
			System.out.println(title + " is not verified!!");
		}

		// verift the tkt title with expected value
		TroubleTicketInfoPage ttip = new TroubleTicketInfoPage();
		String actTitle = ttip.getTitle().getText();
		if (actTitle.contains(title)) {
			System.out.println(title + " is verified!!");
		} else {
			System.out.println(title + " is not verified!!");
		}

		// verify the status of tkt with expected value
		String actstatus = ttip.getStatus().getText();

		if (actstatus.contains(status)) {
			System.out.println(status + " status is verified!!");
		} else {
			System.out.println(status + " status is not verified");
		}
	}

}
