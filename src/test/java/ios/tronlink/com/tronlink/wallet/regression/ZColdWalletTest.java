package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.ColdBaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class ZColdWalletTest extends ColdBaseTest {

    @Test(groups = {"P0"},enabled = true,description = "Cold wallet asset page test", alwaysRun = true)
    public void test001_enterColdWallet() {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        Assert.assertTrue(coldHelper.AssetBtn.isEnabled());
        Assert.assertTrue(coldHelper.MineBtn.isEnabled());
        Assert.assertTrue(coldHelper.recieveLabel.getText().contains("接收"));
        Assert.assertTrue(coldHelper.offlineLabel.getText().contains("离线签名"));
        Assert.assertTrue(coldHelper.AddWalletBtn.isEnabled());
        Assert.assertTrue(coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '立即扫描'").isEnabled());
    }

    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true,description = "Cold wallet receive test", alwaysRun = true)
    public void test002_coldWalletCanReceiveTrx(String address)  {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        coldHelper.recieveLabel.click();
        Assert.assertTrue(coldHelper.addressLabel.getText().contains(address));
        Assert.assertTrue(coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '复制'").isEnabled());
    }

    @Test(enabled = true,description = "Cold wallet knowledge test", alwaysRun = true)
    public void test003_coldWalletKnowledgeTest() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        Helper.swipRefreshScreen(DRIVER);
        coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '了解冷钱包'").click();
        waiteTime();
        Assert.assertTrue(coldHelper.coldWallettitle.getText().contains("冷钱包的使用说明"));
    }


    @Test(groups = {"P0"},enabled = true,description = "Cold wallet add wallet test", alwaysRun = true)
    public void test005_coldWalletAddWalletTest() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        coldHelper.AddWalletBtn.click();
        Assert.assertTrue(coldHelper.importtitle.getText().contains("私钥"));
        Assert.assertTrue(coldHelper.memtitle.getText().contains("助记词"));
        Assert.assertTrue(coldHelper.Keystorettitle.getText().contains("Keystore"));
        Assert.assertTrue(coldHelper.createttitle.getText().contains("创建钱包"));
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


    @Test(description = "Cold wallet Into Mnemonic Page Test",alwaysRun = true)
    public void test010_ColdWalletIntoMnemonicPagetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        AdvanceFuncPage advanceFuncPage = mine.enterAdvancePage();
        MnemonicToolsPage toolsPage = advanceFuncPage.enternemTools_btnPage();
        Assert.assertEquals(toolsPage.title.getText(),"助记词转换工具");
    }


    @Test(groups = {"P0"},enabled = true,description = "Shield Cold wallet knowledge test", alwaysRun = true)
    public void test015_coldShieldWalletKnowledgeTest() throws Exception {
        ColdWalletHelpPage coldHelper = new ColdWalletHelpPage(DRIVER);
        Helper.swipRefreshScreen(DRIVER);
        coldHelper.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '了解冷钱包'").click();
        waiteTime();
        Assert.assertTrue(coldHelper.coldWallettitle.getText().contains("冷钱包的使用说明"));
    }

    @Test(groups = {"P0"},description = "Cold Shield wallet  Me into GroupPage", alwaysRun = true)
    public void test016_ColdShieldWalletEnterGroupPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AboutUsPage aboutuspage =  minePage.enterAboutUsPage();
        GroupPage groupPage = aboutuspage.enterGroupPage();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(groupPage.groupInto_title.getText(), "加入社群");

    }

}
