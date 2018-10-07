package com.tenant.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.report.ZenXReport;

public class LoginPage {
	
	public WebDriver driver;
	
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(ZenXReport reportUtil) {
	
		driver.findElement(By.id("login_uname")).sendKeys("rominder@e2e.ch");
		
		reportUtil.log("TS-1", "Enter user name", "Pass", "", "");
		
		driver.findElement(By.id("login_password")).sendKeys("Global@123");
		reportUtil.log("TS-2", "Enter password", "Pass", "", "");

		driver.findElement(By.id("login_submit")).click();
		reportUtil.log("TS-3", "Submit form", "Pass", "", "");
		
		
	}
}
