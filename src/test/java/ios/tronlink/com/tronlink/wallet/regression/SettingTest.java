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



    @Test(description = "into Chain Set Page Test")
    public void test001_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        Assert.assertTrue(Helper.isElementExist(nodeSetPage.driver,"设置"));
    }


    @Test(description = "SideChain Test",alwaysRun = true)
    public void test006_NodeIntertest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        NodeSetDetailPage detailPage =  nodeSetPage.enterSettingPageMainChain();
        Assert.assertTrue(detailPage.title.getText().contains("节点设置"));
        nodeSetPage = detailPage.backToAction();
        detailPage =  nodeSetPage.enterSettingPageDAppChain();
        Assert.assertTrue(detailPage.title.getText().contains("节点设置"));
        nodeSetPage = detailPage.backToAction();
        detailPage =  nodeSetPage.enterSettingPageShasta();
        Assert.assertTrue(detailPage.title.getText().contains("节点设置"));

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
