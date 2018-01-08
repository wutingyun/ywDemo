package com.core.shijianclcase;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.core.data.AccountRead;
import com.core.data.CommonRandUtil;
import com.core.page.FrontPage;
import com.core.page.LoginPage;
import com.core.page.base.Account;
import com.core.page.question.DclQuestion;
import com.core.page.question.SBaoQuestion;
import com.core.webdriver.BrowserUtil;
import com.core.webdriver.CoreBrowser;

public class WenTiClCase01 {
	/**
	 * 问题处理测试用例： 1、网格长登录，上报问题，完成之后，点击暂存(必填信息)。
	 */
	List<Account> accounts;
	CoreBrowser browser;
	LoginPage page;
	FrontPage frontPage;
	DclQuestion dclQuestion;

	String title;
	String shifadizhi;
	String wentimiaoshu;

	@BeforeTest
	public void beforeTest() {
		// 获取数据
		accounts = AccountRead.getAll();
		// 打开浏览器
		browser = BrowserUtil.getChrome();

		// 打开登录页
		page = new LoginPage();
		browser.open(page);

		frontPage = new FrontPage();
		dclQuestion = new DclQuestion();

		title = "网格长标题-暂存自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		shifadizhi = "网格长事发地址-暂存自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		wentimiaoshu = "网格长问题描述-暂存自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
	}

	@Test
	public void wgz_zancun() {
		// 网格长登录
		page.login(accounts.get(0));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
		// 点击"上报"按钮，进入上报页面

		frontPage.switchToPage(dclQuestion);
		dclQuestion.clickSBao();
		// 进入新增问题页面
		SBaoQuestion shangbaoxinzeng = new SBaoQuestion();
		dclQuestion.switchToPage(shangbaoxinzeng);
		shangbaoxinzeng.addZanCun(title, shifadizhi, wentimiaoshu);
		// 验证是否暂存成功，即在待处理问题页面，找到记录。
		shangbaoxinzeng.switchToPage(dclQuestion);
		String biaoti = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti, "暂存不成功");
		// 点击退出按钮，即退出页面。
		dclQuestion.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@AfterTest
	public void afterTest() {
		browser.close();
	}

}
