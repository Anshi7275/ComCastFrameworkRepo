package assertion_Practices;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrgNameWithIndustryTest extends BaseClass {

	@Test(groups = "regressionTest")
	public void createOrgWithIndustryTest() throws Throwable {

		// read data from excel file
		String orgName = elib.getDatafromExcelfle("Org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDatafromExcelfle("Org", 4, 3).toString();
		String type = elib.getDatafromExcelfle("Org", 4, 4).toString();

		// navigate to organization
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();

		// click on "create organization" button
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();

		// create new org name with industry and type and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);

		WebElement industrydropDown = ORGNAME.getIndustry();
		wlib.select(industrydropDown, industry);

		WebElement typedropDown = ORGNAME.getType();
		wlib.select(typedropDown, type);

		ORGNAME.getSave().click();

		jlib.wait(2000);

		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actHeader = oi.getHeadermsg().getText();
		boolean headermsg = actHeader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===Org Name header is verified!!!===");
		
		//verification of industry and type
		String actIndustry = oi.getIndustryMag().getText();
		boolean Industrymsg = actIndustry.contains(industry);
		
		Assert.assertEquals(Industrymsg, true);
		
		String actType = oi.getTypemsg().getText();
		boolean Typemsg = actType.contains(type);
		
		Assert.assertEquals(Typemsg, true);
	}

}
