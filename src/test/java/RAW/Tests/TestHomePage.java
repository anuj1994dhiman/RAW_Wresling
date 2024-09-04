package RAW.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RAW.Base.BaseTest;
import RAW.Base.RetryTest;



public class TestHomePage extends BaseTest{
	@Test(dataProvider = "provideExelData", groups = "Smoke")
	public void Test1(String usename, String pass) {
		landingPage.login(usename, pass);
//		System.out.println(usename);
//		System.out.println(pass);
	}
	@Test(retryAnalyzer = RetryTest.class)
	public void test2() {
		landingPage.login("anu@example.com", "anjdh");
		Assert.assertEquals(landingPage.getErrMessage(), "Incorrect email o password.");
	}
	@Test
	public void test3() {
		System.out.println("i am test3");
	}
//	@DataProvider
//	public Object[][] getDataUsingObject() {
//		Object[][] data = new Object[2][2];
//		data[0][0] = "anu@example.com";
//		data[0][1] = "Anu@1234";
//		
//		data[1][0] = "anu@example.com";
//		data[1][1] = "Anu@1234";
//		
//		return data;
//	}
//	@DataProvider
//	public Object[][] proveJsonData() throws IOException {
//		List<HashMap<String, String>> data= getJsonData();
//		
//		return new Object[][] {{data.get(0)},{data.get(1)}};
//	}
	
	@DataProvider
	public Object[][] provideExelData() throws IOException {
		Object[][] data = getExelData();
		return data;
	}
}
