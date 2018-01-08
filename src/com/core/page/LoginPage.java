package com.core.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;

import com.core.page.base.AbstractPage;
import com.core.page.base.Account;

public class LoginPage extends AbstractPage {

	/**
	 * 登录页面
	 * 
	 * @param username
	 */
	String username_loc = "username";
	public void setUserName(String username) {
		setInputByName(username_loc, username);
	}

	String password_loc = "password";
	public void setPassword(String password) {
		setInputByName(password_loc, password);
	}

	String login_loc = "login_btn";
	public void clickLogin() {
		getWebElementByClassName(login_loc).click();
	}

	public void login(Account account) {
		try {
			setUserName(account.getUsername());
			setPassword(account.getPassword());
			clickLogin();

		} catch (UnhandledAlertException f) {
			try {
				// BrowserUtil.getNoAlertPresentException(browser);
				Alert alert = coreBrowser.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("警报数据：" + alertText);
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
	}

}
