package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.ColdBaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * 关于我们功能测试
 */
//public class ZColdWalletTest extends BaseTest {
//
//    @Parameters({"privateKey"})
//    @BeforeClass(alwaysRun = true)
//    public void setUpBefore(String privateKey) throws Exception {
//        log("我是BaseTest类的Before");
//        new Helper().getColdSign(privateKey, DRIVER);
//    }
public class ZColdWalletTest extends ColdBaseTest {

    @Test(enabled = true,description = "Cold wallet asset page test", alwaysRun = true)
    public void test001_enterColdWallet() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        Assert.assertTrue(coldHelper.AssetBtn.isEnabled());
        Assert.assertTrue(coldHelper.MineBtn.isEnabled());
        Assert.assertTrue(coldHelper.recieveLabel.isDisplayed());
        Assert.assertTrue(coldHelper.offlineLabel.isDisplayed());
        Assert.assertTrue(coldHelper.AddWalletBtn.isEnabled());
        Assert.assertTrue(coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '立即扫描'").isEnabled());

    }


    @Parameters({"address"})
    @Test(enabled = true,description = "Cold wallet receive test", alwaysRun = true)
    public void test002_coldWalletCanReceiveTrx(String address) throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        coldHelper.recieveLabel.click();
        waiteTime();
        Helper.swipScreen(DRIVER);
        Assert.assertTrue(coldHelper.addressLabel.getText().equals(address));
        Assert.assertTrue(coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '复制收款地址'").isEnabled());
    }

    @Test(enabled = true,description = "Cold wallet knowledge test", alwaysRun = true)
    public void test003_coldWalletKnowledgeTest() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        Helper.swipRefreshScreen(DRIVER);
        coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '了解冷钱包'").click();
        waiteTime();
        Assert.assertTrue(coldHelper.coldWallettitle.getText().contains("冷钱包的使用说明"));
    }


    @Test(enabled = true,description = "Cold wallet add wallet test", alwaysRun = true)
    public void test005_coldWalletAddWalletTest() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        coldHelper.AddWalletBtn.click();
        waiteTime();
        Assert.assertTrue(coldHelper.importtitle.isEnabled());
        Assert.assertTrue(coldHelper.memtitle.isEnabled());
        Assert.assertTrue(coldHelper.Keystorettitle.isEnabled());
        Assert.assertTrue(coldHelper.createttitle.isEnabled());

    }

    @Test(enabled = true,description = "Cold wallet manager test", alwaysRun = true)
    public void test006_coldWalletManagerTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(Helper.contentTexts(walletPage.textArray,"钱包管理"));
    }


    @Test(description = "Cold wallet Me into about us", alwaysRun = true)
    public void test007_coldWalletEnterAboutUs() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AboutUsPage aboutusPage = minePage.enterAboutUsPage();
        Assert.assertTrue(aboutusPage.title.isDisplayed());

    }


    @Test(description = "Cold wallet  Me into SettingPage", alwaysRun = true)
    public void test008_coldWalletEnterSettingPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        SettingPage setPage = minePage.enterSettingPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(setPage.title.getText(), "设置");

    }

    @Test(description = "Cold wallet  Me into GroupPage", alwaysRun = true)
    public void test009_ColdWalletEnterGroupPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        GroupPage groupPage = minePage.enterGroupPage();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(groupPage.groupInto_title.getText(), "加入社群");
    }

    @Test(description = "Cold wallet Into Mnemonic Page Test",alwaysRun = true)
    public void test007_ColdWalletIntoMnemonicPagetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        Assert.assertEquals(toolsPage.title.getText(),"助记词转换工具");
    }

    @Test(description = " ColdWallet Developer options On Test",alwaysRun = true)
    public void test008_ColdWalletDeveloperOnOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.trunDeveloperOptions();
        String developerNow = setting.connected_title.getText();
        Assert.assertTrue(developerNow.contains("Shasta"));
    }
    @Test(description = " ColdWallet Developer options Off Test",alwaysRun = true)
    public void test009_ColdWalletDeveloperOffOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.trunOffDeveloperOptions();
        String developerNow = setting.disconnected_title.getText();
        Assert.assertEquals(developerNow,"未选择");
    }
}
