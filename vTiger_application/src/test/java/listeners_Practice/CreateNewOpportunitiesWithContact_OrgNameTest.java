package listeners_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.CreateOppurnutiesPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OpportunitiesInfoPage;
import com.comcast.crm.objectRepository_utility.OppurtunitiesPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

public class CreateNewOpportunitiesWithContact_OrgNameTest extends BaseClass{

	@Test (retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOppWithOrgNameTest() throws Throwable {
		wlib.waitPageLoad(driver);
		
		String opportunitiesName = elib.getDatafromExcelfle("oppurnities", 4, 2)+jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("oppurnities", 4, 3)+jlib.getRandomNumber();
		
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
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		
		//verification using softAssert
		String actOpp = oi.getOppName().getText();
		boolean oppMsg = actOpp.contains(opportunitiesName);
		
		Assert.assertEquals(oppMsg, true);
		
		System.out.println(opportunitiesName + " is verified!!");

		String actorgName = oi.getOrgName().getText();
		boolean orgNamemsg = actorgName.contains(orgName);
		
		Assert.assertEquals(orgNamemsg, true);
	    System.out.println(orgName + " is verified!!!");
		
	}

}
