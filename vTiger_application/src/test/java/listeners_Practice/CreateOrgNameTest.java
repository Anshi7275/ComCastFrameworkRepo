package listeners_Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrgNameTest extends BaseClass {
	@Test(groups = "smokeTest", retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOrgNameTest() throws Throwable {

		// read testScript data from Excelutility
		String orgName = elib.getDatafromExcelfle("Org", 1, 2) + jlib.getRandomNumber();

		// navigate to organization
		HomePage HOMEPAGE = new HomePage(driver);
		HOMEPAGE.getOrganization().click();

		// click on create org button
		OrganizationPage ORGPAGE = new OrganizationPage(driver);
		ORGPAGE.getCreate_org().click();

		// create new orgname and save
		CreateNewOrganizationPage ORGNAME = new CreateNewOrganizationPage(driver);
		ORGNAME.createNewOrg(orgName);

		ORGNAME.getSave().click();

		jlib.wait(2000);

		// verifcation of header msg
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);

		String actheader = HEADERMSG.getHeadermsg().getText();
		boolean headermsg = actheader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
		System.out.println("===headerMsg verified!!!===");
		
		
	}

}
