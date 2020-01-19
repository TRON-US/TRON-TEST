package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * setting function test
 */
public class SettingTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(description = "switch Language Test")
    public void test002_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
    }

    @Test(description = "Developer options Test", alwaysRun = true)
    public void test002_developerOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        String developer = setting.testnode_text.getText();
        setting.trunDeveloperOptions();
        String developerNow = setting.testnode_text.getText();
        Assert.assertNotEquals(developer, developerNow);
    }

    @Test(description = "DAPP Browser Test", alwaysRun = true)
    public void test003_DAPP_Browser() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        DAPP_BrowerPage dapp = setting.enterDAPP_BrowerPage();
        dapp.testUrl();
        Assert.assertEquals("TEST", dapp.dappTtile_btn.getText());
    }

    @Test(description = "choose mainChain", alwaysRun = true)
    public void test004_Node_Setting() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage node = setting.enterNodeSetPage();
        InternalNodeSetPage internal = node.enterInternalMainChainPage();
        Assert.assertEquals("节点设置",internal.title_text.getText());

    }

    @Test(description = "choose DAppChain", alwaysRun = true)
    public void test005_Node_Setting() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage node = setting.enterNodeSetPage();
        InternalNodeSetPage internal = node.enterInternalDAppChainPage();
        Assert.assertEquals("节点设置",internal.title_text.getText());

    }

}
