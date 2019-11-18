package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.DAPP_BrowerPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * setting function test
 */
public class SettingTest extends BaseTest {



    @Test(description = "switch Language Test")
    public void test002_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
    }

    @Test(description = "Developer options Test",alwaysRun = true)
    public void test002_developerOptions() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        String developer = setting.testnode_text.getText();
        setting.trunDeveloperOptions();
        String developerNow = setting.testnode_text.getText();
        Assert.assertNotEquals(developer,developerNow);
    }

    @Test(description = "DAPP Browser Test",alwaysRun = true)
    public void test003_DAPP_Browser() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        DAPP_BrowerPage dapp =  setting.enterDAPP_BrowerPage();
        dapp.testUrl();
        Assert.assertEquals("TEST",dapp.dappTtile_btn.getText());
    }


}
