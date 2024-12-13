package com.comcast.crm.Test_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepository_utility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepository_utility.CreateOppurnutiesPage;
import com.comcast.crm.objectRepository_utility.HomePage;
import com.comcast.crm.objectRepository_utility.LoginPage;
import com.comcast.crm.objectRepository_utility.OpportunitiesInfoPage;
import com.comcast.crm.objectRepository_utility.OppurtunitiesPage;
import com.comcast.crm.objectRepository_utility.OrganizationInformationPage;
import com.comcast.crm.objectRepository_utility.OrganizationPage;
import com.comcast.crm.objectRepository_utility.OrganizationWindowPage;

public class CreateNewOpportunitiesWithContact_OrgName {

	public static void main(String[] args) throws Throwable {
		
		//read data from property file
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		String opportunitiesName = elib.getDatafromExcelfle("oppurnities", 4, 2)+jlib.getRandomNumber();
		String orgName = elib.getDatafromExcelfle("oppurnities", 4, 3)+jlib.getRandomNumber();
		
		WebDriver driver=null;
		if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		driver.get(URL);
		wlib.waitPageLoad(driver);
		wlib.maximizeWindow(driver);	
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.getOrganization().click();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreate_org().click();
		
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(orgName);
		cnp.getSave().click();
	
		//verifcation 
		OrganizationInformationPage HEADERMSG = new OrganizationInformationPage(driver);
		HEADERMSG.headermsg(orgName);
		
		hp.getOpportunitieslnk().click();
		OppurtunitiesPage opp=new OppurtunitiesPage(driver);
		opp.getCreateOpperIcon().click();
		
		CreateOppurnutiesPage cop=new CreateOppurnutiesPage(driver);
		cop.getOppornutyName().sendKeys(opportunitiesName);
		WebElement dropdown = cop.getRelateddropdown();
		wlib.selectByValue(dropdown, "Accounts");
		
		cop.getPulseIcon().click();
		OrganizationWindowPage owp=new OrganizationWindowPage(driver);
		wlib.switchToTabOnURL(driver, "Accounts&action");
		
		owp.getSearchtxt().sendKeys(orgName);
		owp.getSearchbtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		wlib.switchToTabOnURL(driver, "Potentials&action");	
		cop.getSavebtn().click();
		
		//verification
		OpportunitiesInfoPage oi=new OpportunitiesInfoPage(driver);
		String actHeader = oi.getHeaderOppName().getText();
		if(actHeader.contains(opportunitiesName))
		{
			System.out.println(opportunitiesName+" is verified!!");
		}
		else
		{
			System.out.println(opportunitiesName+" is not verified!!");
		}
		
		String actOpp = oi.getOppName().getText();
		if(actOpp.contains(opportunitiesName))
		{
			System.out.println(opportunitiesName+" is verified!!");
		}
		else
		{
			System.out.println(opportunitiesName+" is not verified!!");
		}
		
		String actorgName = oi.getOrgName().getText();
		if(actorgName.contains(orgName))
		{
			System.out.println(orgName+" is verified!!!");
		}
		else
		{
			System.out.println(orgName+" is not verified!!");
		}
		
		hp.logout();
		driver.quit();
	}

}
