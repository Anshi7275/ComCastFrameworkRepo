package listeners_Practice;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import org.testng.Assert;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrgNameContactTest extends BaseClass {

	@Test(groups = "regressionTest", retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void createOrgnameWithPhone() throws Throwable {
		// read data from excel
		String orgName = elib.getDatafromExcelfle("Org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDatafromExcelfle("Org", 7, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrganization().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreate_org().click();

		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.getContacttxt().sendKeys(phoneNumber);
		cno.getOrgName().sendKeys(orgName);

		cno.getSave().click();

		jlib.wait(2000);

		// verification
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actheader = oip.getHeadermsg().getText();
		boolean headermsg = actheader.contains(orgName);
		
		Assert.assertEquals(headermsg, true);
			System.out.println(orgName + " is created!!!!");

		String actphone = oip.getPhonemsg().getText();
		boolean phonemsg = actphone.contains(actheader);
		
		assertEquals(phonemsg,phoneNumber);
			System.out.println(phoneNumber + " is verified!!!");
	}

}
