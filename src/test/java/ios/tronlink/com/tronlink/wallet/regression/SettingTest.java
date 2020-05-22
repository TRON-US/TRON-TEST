package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * setting function test
 */
public class SettingTest extends BaseTest {


//
    @Test(description = "into Chain Set Page Test")
    public void test001_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        Assert.assertTrue(Helper.isElementExist(nodeSetPage.driver,"设置"));
    }

    @Test(description = "Developer options On Test",alwaysRun = true)
    public void test002_developerOnOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.trunDeveloperOptions();
        String developerNow = setting.connected_title.getText();
        Assert.assertTrue(developerNow.contains("Shasta"));
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


    @Test(description = "SideChain Test",alwaysRun = true)
    public void test006_SideChaintest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        NodeSetDetailPage detailPage =  nodeSetPage.enterSettingPageMainChain();
        NodeSetPage nodeSetPage1 = detailPage.backToAction();
        Assert.assertNotNull(nodeSetPage1.title);
    }

    @Test(description = "Into Mnemonic Page Test",alwaysRun = true)
    public void test007_IntoMnemonicPagetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        AdvanceFuncPage setting = mine.enterAdvancePage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        Assert.assertEquals(toolsPage.title.getText(),"助记词转换工具");
    }
    @Test(groups = {"P0"},description = "Mnemonic Exchange Test",alwaysRun = true)
    public void test008_MnemonicExchangetest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        AdvanceFuncPage setting = mine.enterAdvancePage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        toolsPage.switchMnems();
        Assert.assertNotNull(toolsPage.assetExpectText);

    }


}
