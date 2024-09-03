package RAW.Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class MyTestListener extends BaseTest implements ITestListener{
	
	ExtentReports extent = MyExtentReport.genExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	  }

	 
	public void onTestSuccess(ITestResult result) {
	    // not implemented
	  }

	  
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String scPath = null;
	    try {
	    	WebDriver driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    	scPath=takeSreenShot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    extentTest.get().addScreenCaptureFromPath(scPath);
	  }

	public void onTestSkipped(ITestResult result) {
	    // not implemented
		extentTest.get().skip(result.getMethod().getMethodName()+" failed");
	  }

	  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  
	public void onStart(ITestContext context) {
	    // not implemented
	  }

	  
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }

}
