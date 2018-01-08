package com.core.renwuclcase;

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
import com.core.page.task.DclTask;
import com.core.page.task.DealTask;
import com.core.page.task.JiaoBanTask;
import com.core.page.task.YclTask;
import com.core.webdriver.BrowserUtil;
import com.core.webdriver.CoreBrowser;

public class RenWuClCase13 {
	/**
	 * 测试用例：13、义乌中心发起任务，直接分派镇街,点击提交；镇街中心进行任务下发，后进行处理；义乌中心审核结案。（此处紧急非紧急无用）
	 */
	List<Account> accounts;
	CoreBrowser browser;
	LoginPage page;
	FrontPage frontPage;
	DclTask dcltask;
	JiaoBanTask jiaobantask;
	DealTask dealtask;
	YclTask ycltask;

	String tasktitle;
	String taskmiaoshu;
	String shangchuanfujian;
	String shangchuanfankuifujian;
	String newybbianhao;

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
		dcltask = new DclTask();
		jiaobantask = new JiaoBanTask();
		dealtask = new DealTask();
		ycltask = new YclTask();

		tasktitle = "ywzx任务标题自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		taskmiaoshu = "ywzx任务描述自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		shangchuanfujian = "D:\\uplode_file.jpg";
		shangchuanfankuifujian = "D:\\uplode_file2.jpg";

	}

	@Test
	public void qxzxRenWuJiaoBantj() {
		// 义乌中心登录
		page.login(accounts.get(4));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclRenWu();
		frontPage.switchToPage(dcltask);
		dcltask.clickTaskJiaoBan();
		// 进入任务交办任务
		dcltask.switchToPage(jiaobantask);
		jiaobantask.qxzxtijiaoTask(tasktitle, taskmiaoshu, shangchuanfujian);
		// 点击退出按钮，即退出页面。
		jiaobantask.switchToPage(frontPage);
		frontPage.clickYiWuTuiChu();
	}

	@Test
	public void zjzxXiaFa() {
		/**
		 * 镇街中心登录后，对任务进行下发。
		 */
		// 镇街中心登录
		page.login(accounts.get(1));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”。
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 “待处理任务”。
		frontPage.waitDclRenWu();
		// frontPage.clickDclRenWu();
		// 在待处理页面，根据标题进行查询需要办理的任务。
		frontPage.switchToPage(dcltask);
		dcltask.findbybiaoti(tasktitle);
		dcltask.clickXiaFa();
		dcltask.switchToPage(dealtask);
		dealtask.zjzxXiaFa();
		//进入已处理任务页面，提取任务编号
		dealtask.switchToPage(frontPage);
		frontPage.clickYiClTask();
		frontPage.switchToPage(ycltask);
		newybbianhao = ycltask.findybtaskByBiaoTi2(tasktitle);
		System.out.println(newybbianhao);
		
		// 点击退出按钮，即退出页面。
		dealtask.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void wgzChuLi() {
		/**
		 * 网格长登录后，对任务进行处理。
		 */
		// 网格长登录
		page.login(accounts.get(0));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”。
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 “待处理任务”。
		frontPage.waitDclRenWu();
		// frontPage.clickDclRenWu();
		// 在待处理页面，根据标题进行查询需要办理的任务。
		frontPage.switchToPage(dcltask);
		dcltask.findbybiaoti(tasktitle);
		dcltask.clickChuLi();
		dcltask.switchToPage(dealtask);
		dealtask.wgzChuLi(shangchuanfankuifujian);
		// 点击退出按钮，即退出页面。
		dealtask.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void zjzxShenHe() {
		/**
		 * 镇街中心对任务进行审核，结案
		 */
		// 镇街中心登录
		page.login(accounts.get(1));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 “待处理任务”，在待任务处理页面，点击审核。
		frontPage.waitDclRenWu();
		// frontPage.clickDclRenWu();
		frontPage.switchToPage(dcltask);
		//dcltask.findbybiaoti(tasktitle);
		dcltask.findbybianhao(newybbianhao);//根据任务编号进行查询
		dcltask.clickTaskShenHe();
		// 进入审核页面
		dcltask.switchToPage(dealtask);
		dealtask.zjzxShenHe();
		// 在已处理任务页面，验证是否结案成功。
		dealtask.switchToPage(frontPage);
		frontPage.clickYiClTask();
		frontPage.switchToPage(ycltask);
		String newybbiaoti = ycltask.findybtaskByBiaoTi(tasktitle);
		assertEquals(tasktitle, newybbiaoti, "镇街中心结案失败");
		// 点击退出按钮，即退出页面。
		ycltask.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void zjzxChuLi() {
		/**
		 * 镇街中心登录后，对任务进行处理。
		 */
		// 镇街中心登录
		page.login(accounts.get(1));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”。
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 “待处理任务”。
		frontPage.waitDclRenWu();
		// frontPage.clickDclRenWu();
		// 在待处理页面，根据标题进行查询需要办理的任务。
		frontPage.switchToPage(dcltask);
		dcltask.findbybiaoti(tasktitle);
		dcltask.clickChuLi2();
		dcltask.switchToPage(dealtask);
		dealtask.zjzxChuLi(shangchuanfankuifujian);
		// 点击退出按钮，即退出页面。
		dealtask.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void qxzxShenHe() {
		/**
		 * 区县中心对任务进行审核，结案
		 */
		// 义乌中心登录
		page.login(accounts.get(4));
		BrowserUtil.sleep(3);
		// 进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 进入页面首页，点击左侧的 “待处理任务”，在待任务处理页面，点击审核。
		frontPage.waitDclRenWu();
		// frontPage.clickDclRenWu();
		frontPage.switchToPage(dcltask);
		dcltask.findbybiaoti(tasktitle);
		dcltask.clickTaskShenHe();
		// 进入审核页面
		dcltask.switchToPage(dealtask);
		dealtask.qxldShenHe();
		// 在已处理任务页面，验证是否结案成功。
		dealtask.switchToPage(frontPage);
		frontPage.clickYiClTask();
		frontPage.switchToPage(ycltask);
		String newybbiaoti = ycltask.findybtaskByBiaoTi(tasktitle);
		assertEquals(tasktitle, newybbiaoti, "义乌中心结案失败");
		// 点击退出按钮，即退出页面。
		ycltask.switchToPage(frontPage);
		frontPage.clickYiWuTuiChu();
	}

	@AfterTest
	public void afterTest() {
		browser.close();
	}
}
