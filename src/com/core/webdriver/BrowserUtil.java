package com.core.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserUtil {

	public static CoreBrowser getChrome() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver webDriver;

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

		webDriver = new ChromeDriver(dc);

		// 等待页面加载时间 2秒
		// webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		// 全局等待元素加载 3秒
		webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		CoreBrowser coreBrowser = new CoreBrowser(webDriver);
		return coreBrowser;
	}

	public static void close(CoreBrowser coreBrowser) {
		coreBrowser.close();
	}

	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void getNoAlertPresentException(CoreBrowser coreBrowser){
	 * Alert alert = coreBrowser.switchTo().alert(); String alertText =
	 * alert.getText(); System.out.println("警报数据："+ alertText); alert.accept();
	 * }
	 */

}
