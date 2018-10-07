package com.report;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestReport {

	ZenXReport objReprot ;
	
	@BeforeClass
	public void beforeClass() {
		String fileName = "D:\\report.html";
		 objReprot = new ZenXReport(fileName, true);
		objReprot.addSystemInformation("Browser", "Firefox");
		objReprot.addSystemInformation("Environment", "DEV");
	}
	
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@BeforeTest
	public void beforTest() {
		
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
	
		/*Test test = method.getAnnotation(Test.class);
		String desc = test.description();
		String testId = "TS-1";
		objReprot.logTestCaseDetail(testId , desc);*/

	}
	
	@AfterMethod
	public void afterMethod(Method method) {
	
		Test test = method.getAnnotation(Test.class);
		String desc = test.description();
		String testId = "TS-1";
		//objReprot.logTestCaseDetail(testId , desc);

	}
	
	@Test(description="test description")
	public void testCase() {
		
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("acceptInsecureCerts", true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("marionette", true);
		System.setProperty("webdriver.gecko.driver", "D://Projects//Hilti//HiltiRepository//drivers//geckodriver.exe");
		WebDriver driver = new FirefoxDriver(capabilities);
		
	  
		driver.get("https://puneqa.ictoncloud.in/ontrack/#/");
		driver.findElement(By.id("login_uname"));
		
		String result = "PASS";
		
		if(result.equalsIgnoreCase(ReportConstants.PASS)){
			objReprot.log("tc-1","description","pass","expected","actual");
		}else {
			//objReprot.captureScreenShot();
			objReprot.log("tc-1","description","pass","expected","actual","screeshot");
		}
		
		driver.findElement(By.id("login_password"));
		driver.findElement(By.id("login_submit"));
		
		
	}
}
