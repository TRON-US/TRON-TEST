package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * setting function test
 */
public class SettingTest extends BaseTest {



    @Test(description = "into Chain Set Page Test")
    public void test001_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        Assert.assertEquals(nodeSetPage.title.getText(),"设置");

    }

    @Test(description = "Developer options On Test",alwaysRun = true)
    public void test002_developerOnOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.trunDeveloperOptions();
        String developerNow = setting.connected_title.getText();
        Assert.assertEquals(developerNow,"已选择shasta网络");
    }
    @Test(description = "Developer options Off Test",alwaysRun = true)
    public void test003_developerOffOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.trunOffDeveloperOptions();
        String developerNow = setting.disconnected_title.getText();
        Assert.assertEquals(developerNow,"未选择");
    }

    @Test(description = "Language Switch Test",alwaysRun = true)
    public void test004_Languagetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.languane_btn.click();
        setting.Cancal_btn.click();
        setting.languane_btn.click();
        setting.english_btn.click();
        setting.languaneEn_btn.click();
        setting.languaneCn_btn.click();
    }

    @Test(description = "currencyUnit Switch Test",alwaysRun = true)
    public void test005_currencyUnittest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.currencyUnit_btn.click();
        setting.USDUnit_btn.click();
        setting.USDUnit_btn.click();
        setting.CNYUnit_btn.click();
        setting.CNYUnit_btn.click();
        setting.Cancal_btn.click();

    }

    @Test(description = "SideChain Test",alwaysRun = true)
    public void test006_SideChaintest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        NodeSetDetailPage detailPage =  nodeSetPage.enterSettingPageMainChain();
        NodeSetPage nodeSetPage1 = detailPage.backToAction();
//        NodeSetDetailPage detailPaged =  nodeSetPage1.enterSettingPageDAppChain();
//        NodeSetPage nodeSetPage2 = detailPaged.backToAction();
        Assert.assertNotNull(nodeSetPage1.title);
    }

    @Test(description = "Into Mnemonic Page Test",alwaysRun = true)
    public void test007_IntoMnemonicPagetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        Assert.assertEquals(toolsPage.title.getText(),"助记词转换工具");
    }
    @Test(description = "Mnemonic Exchange Test",alwaysRun = true)
    public void test008_MnemonicExchangetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        toolsPage.switchMnems();
        Assert.assertNotNull(toolsPage.assetExpectText);

    }

    @Test(description = "DAPP BrowserPage Test",alwaysRun = true)
    public void test009_DAPP_Browser()  {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        DAPP_BrowerPage dapp1 =  setting.enterDAPP_BrowerPage();
        dapp1.testUrl();
//        Assert.assertEquals("TEST",dapp.dappTtile_btn.getText());
    }


}
