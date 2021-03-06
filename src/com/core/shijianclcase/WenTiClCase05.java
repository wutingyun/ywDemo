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
import com.core.page.question.DealQuestion;
import com.core.page.question.PaiFaQuestion;
import com.core.page.question.SBaoQuestion;
import com.core.page.question.YclQuestion;
import com.core.webdriver.BrowserUtil;
import com.core.webdriver.CoreBrowser;

public class WenTiClCase05 {
	/**
	 * 问题处理测试用例：
	 * 5、网格长上报提交-镇街中心派发给职能办，片区长，网格长-职能办，片区长，网格长反馈-镇街中心处理-结案。
	 */
	
	List<Account> accounts;
	CoreBrowser browser;
	LoginPage page;
	FrontPage frontPage;
	DclQuestion dclQuestion;
	DealQuestion dealQuestion;
	YclQuestion yclQuestion;
	PaiFaQuestion paifapage ;

	String title;
	String shifadizhi;
	String wentimiaoshu;
	String scfujian;
	String jashuoming;
	String tngfankui;
	String paiFaSm ;//派发说明
	String fkuishuoming;//片区长反馈说明
	String scfkfujian;//上传反馈附件
	String fkuishuoming2;//镇街职能办反馈说明
	String fkuishuoming4 ;//网格长反馈说明
	

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
		dealQuestion = new DealQuestion();
		yclQuestion = new YclQuestion();
		paifapage = new PaiFaQuestion();

		title = "网格长标题-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		shifadizhi = "网格长事发地址-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		wentimiaoshu = "网格长问题描述-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		scfujian = "D:\\uplode_file2.jpg";
		jashuoming = "镇街中心结案说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		tngfankui = "镇街中心同年哥反馈说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		paiFaSm = "网格长派发说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		fkuishuoming = "片区长反馈说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		scfkfujian = "D:\\uplode_file2.jpg";
		fkuishuoming2 = "镇街职能办反馈说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		fkuishuoming4 = "网格长反馈说明-结案自动化" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		

	}

	@Test
	public void wgz1() {
		// 网格长登录
		page.login(accounts.get(0));
	//	BrowserUtil.sleep(3);
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

		shangbaoxinzeng.addShangBao(title, shifadizhi, wentimiaoshu, scfujian);
		// 点击退出按钮，即退出页面。
		shangbaoxinzeng.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void zjzx1() {
		/**
		 * 镇街中心登录，进行派发操作
		 */
		page.login(accounts.get(1));
		BrowserUtil.sleep(3);
		// 镇街中心进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 镇街中心进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
	//	BrowserUtil.sleep(3);
		// 点击高级查询
		frontPage.switchToPage(dclQuestion);
		String biaoti = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti, "查询不成功");
		// 镇街中心，待办理问题页面，点击“派发”,进入派发页面。
		dclQuestion.clickPaiFa();
		dclQuestion.switchToPage(paifapage);
		paifapage.paiFa(paiFaSm);
		// 镇街中心派发成功后，点击“退出”。
		paifapage.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@Test
	public void pqz1() {
		/**
		 * 片区长登录，进入待处理问题页面，反馈问题
		 */
		page.login(accounts.get(2));
	//	BrowserUtil.sleep(3);
		// 镇街中心进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 镇街中心进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
		BrowserUtil.sleep(3);
		// 点击高级查询
		frontPage.switchToPage(dclQuestion);
		String biaoti2 = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti2, "查询不成功");
		// 片区长登录，待办理问题页面，点击“处理”,进入处理页面。
		dclQuestion.clickChuLi();
		dclQuestion.switchToPage(dealQuestion);
		dealQuestion.deal(fkuishuoming, scfkfujian);
		// 退出
		dealQuestion.switchToPage(frontPage);
	//	BrowserUtil.sleep(3);
		frontPage.clickTuiChu();
		
	}

	@Test
	public void znb1() {
		/**
		 * 镇街职能办登录，进入待处理问题页面，反馈问题
		 */
		page.login(accounts.get(3));
   // 	BrowserUtil.sleep(3);
		// 镇街职能办进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 镇街职能办进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
		// 点击高级查询
		frontPage.switchToPage(dclQuestion);
		String biaoti3 = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti3, "查询不成功");
		// 镇街职能办登录，待办理问题页面，点击“处理”,进入处理页面。
		dclQuestion.clickChuLi();
		dclQuestion.switchToPage(dealQuestion);
		dealQuestion.deal(fkuishuoming2, scfkfujian);
		// 退出
		dealQuestion.switchToPage(frontPage);
		BrowserUtil.sleep(3);
		frontPage.clickTuiChu();
	}

	@Test
	public void wgz2() {
		/**
		 * 网格长登录，进入待处理问题页面，反馈问题
		 */
		page.login(accounts.get(0));
		BrowserUtil.sleep(3);
		// 镇街职能办进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 镇街职能办进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
		// 点击高级查询
		frontPage.switchToPage(dclQuestion);
		String biaoti4 = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti4, "查询不成功");
		// 镇街职能办登录，待办理问题页面，点击“处理”,进入处理页面。
		dclQuestion.clickChuLi();
		dclQuestion.switchToPage(dealQuestion);
		dealQuestion.deal(fkuishuoming4, scfkfujian);
		// 退出
		dealQuestion.switchToPage(frontPage);
	//	BrowserUtil.sleep(3);
		frontPage.clickTuiChu();
	}

	@Test
	public void zjzx2() {
		/**
		 * 镇街中心登录，对片区长，职能办，网格长反馈上来的问题，进行处理，处理完成，则结案。
		 */
		page.login(accounts.get(1));
	//	BrowserUtil.sleep(3);
		// 镇街职能办进入页面首页，点击抬头“事件处理”
		page.switchToPage(frontPage);
		frontPage.waitSjchuli();
		frontPage.clickSjchuli();
		// 镇街职能办进入页面首页，点击左侧的 问题处理>>“待处理问题”
		frontPage.waitDclQuestion();
		frontPage.clickDclQuestion();
		// 点击高级查询
		frontPage.switchToPage(dclQuestion);
		String biaoti5 = dclQuestion.findBybiaoti(title);
		assertEquals(title, biaoti5, "查询不成功");
		// 镇街职能办登录，待办理问题页面，点击“处理”,进入处理页面。
		dclQuestion.clickZjzxChuLi();
		dclQuestion.switchToPage(dealQuestion);
		dealQuestion.dealFkQuestion(jashuoming);
		// 在已处理页面，验证是否结案成功。
		dealQuestion.switchToPage(frontPage);
		frontPage.clickYiClQuestion();
		frontPage.switchToPage(yclQuestion);
		String ybbiaoti = yclQuestion.findYbBybiaoti(title);
		assertEquals(title, ybbiaoti, "结案不成功");
	//	BrowserUtil.sleep(3);
		// 退出
		yclQuestion.switchToPage(frontPage);
		frontPage.clickTuiChu();
	}

	@AfterTest
	public void afterTest() {
		browser.close();
	}

}
