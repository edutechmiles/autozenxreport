package com.tenant.test;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

 
public class InitializeWebDriver {
	public static final DesiredCapabilities capabilities = new DesiredCapabilities();

	
	public static WebDriver getWebDriver(String browser, WebDriver driver,
			String projectPath, Logger APP_LOGS, String runOnMachine) {
		// WebDriver driver; APP_LOGS.debug("getWebDriver browser=" + browser);
		if (runOnMachine.equalsIgnoreCase("local") || runOnMachine.equalsIgnoreCase("127.0.0.1")) {
			if (browser.equalsIgnoreCase("Firefox")) {
				if (driver instanceof FirefoxDriver) {
					return driver;
				}
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("acceptInsecureCerts", true);
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("marionette", true);
				System.setProperty("webdriver.gecko.driver", projectPath+"//drivers//geckodriver.exe");
				driver = new FirefoxDriver(capabilities);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("Chrome")) {
				if (driver instanceof ChromeDriver) {
					return driver;
				}

				System.setProperty("webdriver.chrome.driver",
						projectPath+"//drivers//chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("--js-flags=--expose-gc");
				options.addArguments("--enable-precise-memory-info");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-default-apps");
				options.addArguments("test-type=browser");
				options.addArguments("disable-infobars");
				// capabilities = WebCaps.chrome();
				capabilities.setCapability(
						CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
						true);
				capabilities.setCapability("chrome.switches",
						Arrays.asList("--incognito"));
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);
				driver.manage().deleteAllCookies();
				
			} else if ((browser.equalsIgnoreCase("InternetExplorer"))
					|| (browser.equalsIgnoreCase("IE"))) {
				if (driver instanceof InternetExplorerDriver) {
					return driver;
				}
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

				System.setProperty("webdriver.ie.driver",
						projectPath+"//drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
			}
		}
		return driver;
	}
}
