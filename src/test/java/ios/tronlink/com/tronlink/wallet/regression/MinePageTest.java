package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * 我的页面功能测试
 */
public class MinePageTest extends BaseTest {


    @Test(alwaysRun = true)
    public void test001_MinePageElementTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        Assert.assertTrue(isElementExist("我的钱包"));
        Assert.assertTrue(isElementExist("message noti"));
        Assert.assertTrue(isElementExist("交易历史"));
        Assert.assertTrue(isElementExist("钱包管理"));
        Assert.assertTrue(isElementExist("地址本"));
        Assert.assertTrue(isElementExist("设置"));
        Assert.assertTrue(isElementExist("好友邀请"));
        Assert.assertTrue(isElementExist("高级功能"));
        Helper.swipScreenLitter(DRIVER);
        Assert.assertTrue(isElementExist("公告"));
        Assert.assertTrue(isElementExist("帮助中心"));
        Assert.assertTrue(isElementExist("关于我们"));
    }

    @Test(description = "test Me into wallet", alwaysRun = true)
    public void test002_enterWallet() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertEquals(walletPage.title.getText(), "钱包管理");
    }


    @Test(description = "test Me into about us", alwaysRun = true)
    public void test002_enterAboutUs() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AboutUsPage aboutusPage = minePage.enterAboutUsPage();
        Assert.assertTrue(aboutusPage.title.isDisplayed());

    }

    @Test(description = "test Me into TransactionRecordPage", alwaysRun = true)
    public void test003_enterRecorder() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        TimeUnit.SECONDS.sleep(2);
        minePage.enterTransactionRecordPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("ic account"));
        Assert.assertTrue(isElementExist("全部"));
        Assert.assertTrue(isElementExist("转账"));
        Assert.assertTrue(isElementExist("收款"));
    }


    @Test(alwaysRun = true)
    public void test004_AddressBookTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        mine.enterAddressBook();
        Assert.assertTrue(isElementExist("地址本"));
        Assert.assertTrue(isElementExist("addressBook add"));
        Assert.assertTrue(isElementExist("空列表"));
        Assert.assertTrue(isElementExist("暂无数据"));
    }


    @Test(description = "test Me into SettingPage", alwaysRun = true)
    public void test005_enterSettingPageTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        SettingPage setPage = minePage.enterSettingPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(setPage.title.getText(), "设置");
    }

     @Test(alwaysRun = true)
     public void test006_friendInvestTest() throws Exception {
         AssetPage assetPage = new AssetPage(DRIVER);
         MinePage mine = assetPage.enterMinePage();
         mine.enterInvitation();
         TimeUnit.SECONDS.sleep(8);
         Assert.assertTrue(isElementExist("好友邀请"));
         Assert.assertTrue(isElementExist("旧版"));
         Assert.assertTrue(isElementExist("链接波场TRON生态"));
     }

      @Test(alwaysRun = true)
      public void test007_advancedTest() throws Exception {
          AssetPage assetPage = new AssetPage(DRIVER);
          MinePage mine = assetPage.enterMinePage();
          mine.enterAdv();
          Assert.assertTrue(isElementExist("高级功能"));
          Assert.assertTrue(isElementExist("委员会提议"));
          Assert.assertTrue(isElementExist("DApp测试工具"));
          Assert.assertTrue(isElementExist("助记词转换工具"));

      }

    @Test(description = "test Me into AnnouncementPage", alwaysRun = true)
    public void test008_enterannoun() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AnnouncementPage announPage = minePage.enterAnnouncementPage();
        TimeUnit.SECONDS.sleep(15);
        Assert.assertTrue(Helper.contentTexts(announPage.textArray,"公告"));

    }

    @Test(description = "test Me into HelpPage", alwaysRun = true)
    public void test009_enterHelpPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        HelpPage helpPage = minePage.enterHelpPage();
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(helpPage.title.isDisplayed());

    }

}
