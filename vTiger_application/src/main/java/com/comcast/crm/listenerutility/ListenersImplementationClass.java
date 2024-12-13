package com.comcast.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenersImplementationClass implements ITestListener, ISuiteListener{

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ISuite suite) {
		System.out.println("===Report configuration===");
		
		//spark report--->configuration of the report is done!!!!
		
		String time = new Date().toString().replace(" ", "_").replace(":", ".");
		spark = new ExtentSparkReporter("./ExtentReports/Report"+time+".html");
		spark.config().setDocumentTitle("CRM app-->contact create test");
		spark.config().setReportName("contact create test");
		spark.config().setTheme(Theme.DARK);
		
		//environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome");
	}

	public void onFinish(ISuite suite) {
		System.out.println("===Report backup===");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		
		test.log(Status.INFO, result.getMethod().getMethodName()+"=====STARTED!!!!====");
		UtilityClassObject.setTest(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+"=====SUCCESS!!!!====");
	}

	public void onTestFailure(ITestResult result) {
		String time = new Date().toString().replace(" ", "_").replace(":", ".");
		
		String testName = result.getMethod().getMethodName()+time;
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
			
		//capture the screenshot attached to extent report
		String srcFile = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(srcFile, testName);
		test.log(Status.FAIL, result.getThrowable());
		
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=====FAILED!!!!====");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName()+"=====SKIPPED!!!!====");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=====FAILED!!!!====");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=====FAILED WITH TIMEOUT!!!!====");
	}

	public void onStart(ITestContext context) {
		System.out.println("====TEST START====");
	}

	public void onFinish(ITestContext context) {
		System.out.println("====TEST FINISH====");
	}
	
	
}
