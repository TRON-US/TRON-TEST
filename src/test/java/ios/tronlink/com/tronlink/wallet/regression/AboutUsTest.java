package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


/**
 * 关于我们功能测试
 */
public class AboutUsTest extends BaseTest {


    @Test(description = "test version test",alwaysRun = true)
    public void test001_AboutUsFirstPageTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        Helper.swipScreenLitter(minePage.driver);
        minePage.enterAboutUsPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"v4.13.0(Build 3)"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"TronLink"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"关于我们"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"用户协议"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"版本日志"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"版本更新"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"官方网站"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"Telegram"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"Twitter"));
    }

    @Test(description = "test enterGroup test" ,alwaysRun = true)
    public void test002_enterUserAgreementTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.enterUsersAgreement();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"用户协议"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"policy browse"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"policy back"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"更新于2022年10月25日"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"波宝钱包用户条款和隐私政策"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"感谢您使用波宝钱包服务。波宝钱包（以下称“该服务”、“服务”或“我们”）是一个为用户提供数字资产安全管理服务及数字资产交易的平台。只要登陆该产品的自然人或其他主体均为本产品的用户（以下简称“您”或“用户”）。为便利用户，可能提供多个语言版本，若有冲突或遗漏等情况，以中文内容为准。"));
    }

    @Test(alwaysRun = true)
    public void test003_versionLogsTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.enterVersionLog();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"版本日志"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"black path"));
        minePage.navBack();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"关于我们"));
    }

    @Test(alwaysRun = true)
    public void test004_UpdateTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.updateClick();
        Assert.assertTrue(minePage.iosToast("当前已经是最新版本"));
    }

    @Test(alwaysRun = true)
    public void test005_OfficeWebTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.enterOffice();
        Assert.assertTrue(isElementExist("Safari浏览器"));
    }
    @Test(alwaysRun = true)
    public void test006_TelegramWebTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.enterTelegram();
        Assert.assertTrue(isElementExist("Safari浏览器"));
    }

    @Test(alwaysRun = true)
    public void test007_TwitterWebTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        minePage.enterTwitter();
        Assert.assertTrue(isElementExist("Safari浏览器"));
    }




}
