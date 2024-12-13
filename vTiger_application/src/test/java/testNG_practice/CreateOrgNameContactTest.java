package testNG_practice;

import org.testng.annotations.Test;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;

public class CreateOrgNameContactTest extends BaseClass {

	@Test(groups = "regressionTest")
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
		if (actheader.contains(orgName)) {
			System.out.println(orgName + " is created!!!!");
		} else {
			System.out.println(orgName + " is not created!!!!");
		}

		String actphone = oip.getPhonemsg().getText();
		if (actphone.contains(phoneNumber)) {
			System.out.println(phoneNumber + " is verified!!!");
		} else {
			System.out.println(phoneNumber + " is not verified!!!");
		}
	}

}
