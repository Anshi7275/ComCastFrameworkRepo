package listeners_Practice;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.comcast.crm.generic.baseClass.BaseClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class Listeners_method implements ITestListener
{
	JavaUtility jlib=new JavaUtility();
	public void onTestStart(ITestResult result) 
	{
		System.out.println("==on test Start===");
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("===on test success===");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("===on test failure===");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File src_File = ts.getScreenshotAs(OutputType.FILE);
		File dst_file = new File("./Screenshot/headerBar"+result.getName()+jlib.getRandomNumber()+".png");
		try {
			FileHandler.copy(src_File, dst_file);
		} catch (Exception e) {
			
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		System.out.println("===on test skipped===");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		System.out.println("==on test fail but within success percentage===");
		System.out.println("=="+result.getName()+"==");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File src_File = ts.getScreenshotAs(OutputType.FILE);
		File dst_file = new File("./Screenshot/headerBar"+result.getName()+jlib.getRandomNumber()+".png");
		try {
			FileHandler.copy(src_File, dst_file);
		} catch (Exception e) {
			
		}
	}
	
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		System.out.println("===on Test failed with timeOut===");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File src_File = ts.getScreenshotAs(OutputType.FILE);
		File dst_file = new File("./Screenshot/headerBar"+result.getName()+jlib.getRandomNumber()+".png");
		try {
			FileHandler.copy(src_File, dst_file);
		} catch (Exception e) {
			
		}
	}
	
	public void onStart(ITestContext context) 
	{
		System.out.println("===ITest context===");
	}
	
	public void onFinish(ITestContext context) 
	{
		System.out.println("===On finish===");	
	}
}
