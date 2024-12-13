package listeners_Practice;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Listeners_retryAnalyzer implements IRetryAnalyzer{

	//we can use this for network issue it will retry to launch the browser 
	//only we can use it with @TEST annotation
	//if assertion is failing it will retry---> disadvantage
	
	int count=5, max=5;
	public boolean retry(ITestResult result) {
		
		if(count<max)
		{
			count++;
			return true;
		}
		
		return false;
	}
	
	
}
