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
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"更新于2023年3月8日"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"波宝钱包用户条款和隐私政策"));
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
