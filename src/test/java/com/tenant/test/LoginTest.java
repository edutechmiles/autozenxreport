package com.tenant.test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.report.ReportConstants;
import com.report.ZenXReport;
import com.tenant.page.LoginPage;


public class LoginTest{
	
	  public int defaultCount = 1;

		public WebDriver webDriver;
		public static Logger APP_LOG = null;
	    public static ZenXReport reportUtil;
		public static String reportStartTime = "";
		public static String executionEnviroment;
		public static String releaseVersion ;
		public static ArrayList<String> finalTestResult  = new ArrayList<String>();
		public static String currentTestCaseId;
		public static String currentJiraIdDesc;
		public static boolean testResultFlag = true;

		@BeforeSuite
		public void initializeWebDriver() throws InterruptedException{
			reportUtil = new ZenXReport("ZenXReport.html", true);
			reportUtil.addSystemInformation(ReportConstants.ENVIRONMENT,"DEV");
			reportUtil.addSystemInformation(ReportConstants.RELEASE_VERSION,"3.0");
			reportUtil.addSystemInformation(ReportConstants.BROWSER,"Firefox");
			
			
			reportUtil.startSuit("Suite Name");
			//webDriver = InitializeWebDriver.getWebDriver("firefox", webDriver, "D:\\Projects\\Hilti\\HiltiRepository", APP_LOG, "local");
			//webDriver.get("https://ontrack-q.hilti.com/ontrack/index.html#/");
			reportUtil.log("TS-1", "Open application", ReportConstants.PASS, "", "");
			Thread.sleep(4000);
		}
		
		@Test(description="Login into application")
		public void loginIntoApp() {
			LoginPage objLoginPage = new LoginPage(webDriver);
			objLoginPage.login(reportUtil);
		}
	  
		@Test(description="Login into application")
		public void loginIntoApp1() {
			LoginPage objLoginPage = new LoginPage(webDriver);
			objLoginPage.login(reportUtil);
		}
	  
		
		@Test(description="Login into application")
		public void loginIntoApp2() {
			LoginPage objLoginPage = new LoginPage(webDriver);
			objLoginPage.login(reportUtil);
		}
	  
		
		@Test(description="Login into application")
		public void loginIntoApp3() {
			LoginPage objLoginPage = new LoginPage(webDriver);
			objLoginPage.login(reportUtil);
		}
	  
		
		@Test(description="Login into application")
		public void loginIntoApp4() {
			LoginPage objLoginPage = new LoginPage(webDriver);
			objLoginPage.login(reportUtil);
		}
	  
		
		@AfterMethod
		public void tearDownAfterMethod(Method method) {
			
			Test objTest = method.getAnnotation(Test.class);
			String currentDesc = objTest.description();
			String currentTestName = objTest.testName();
			
			reportUtil.endTest(currentTestName, currentDesc);
		}

	
	@AfterSuite
	public void afterSuit() {
		reportUtil.endReport();
	}
	
}
