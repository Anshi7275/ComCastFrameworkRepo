package extentReport_practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners(com.comcast.crm.listenerutility.ListenersImplementationClass.class)
public class CreateContactPage_reportTest {
	ExtentSparkReporter spark ;
	ExtentReports report ;
	
	@Test
	public void createContactTest()
	{
		spark = new ExtentSparkReporter("./Advance_Reports/reports.html");
		spark.config().setDocumentTitle("CRM test suit result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER", "Chrome");
		ExtentTest test = report.createTest("contact create");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		Assert.assertEquals("HDFC", "HDFC");
		  test.log(Status.PASS, "contact created!!!");
	}
	
	@Test
	public void createContactWithOrgTest() {
		spark = new ExtentSparkReporter("./Advance_Reports/reports.html");
		spark.config().setDocumentTitle("CRM test suit result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER", "Chrome");
		ExtentTest test = report.createTest("contact create with org name");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to org page");
		test.log(Status.INFO, "create org");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact with org name");
		Assert.assertEquals("HDFC", "HDFC");
		  test.log(Status.PASS, "contact created!!!");
	}
	
	@Test
	public void createContactWithPhoneNoTest() {
		spark = new ExtentSparkReporter("./Advance_Reports/reports.html");
		spark.config().setDocumentTitle("CRM test suit result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER", "Chrome");
		ExtentTest test = report.createTest("contact create with phone number");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact with phone number");
		Assert.assertEquals("HDFC", "HDFC2");
		  test.log(Status.PASS, "contact created!!!");
	}

}
