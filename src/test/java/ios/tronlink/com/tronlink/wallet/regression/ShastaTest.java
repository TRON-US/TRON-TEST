package ios.tronlink.com.tronlink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AdvanceFuncPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.FrozenAndUnfreezePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MnemonicToolsPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetDetailPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ReceiptPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

/**
 * setting function test
 */
public class ShastaTest extends BaseTest {


    @Parameters({"bundleId"})
    @Test(description = "Shasta Transfer TRX")
    public void test001_ShastaMainPage(String bundleId) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        guaranteeShastaChain(bundleId);
        TimeUnit.SECONDS.sleep(8);
        String show1 = asset.nameLabel.getText();
        Assert.assertEquals("TRX",show1);

    }

    @Test(description = "Shasta Transfer TRX")
    public void test002_ShastaTrxMainPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterTrxPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("TRX"));
    }


    @Parameters({"address"})
    @Test(description = "test003_FrozenTestSuccess")
    public void test003_ReceivedPage(String address) throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterReceiptPage();
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("保存二维码"));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));

    }

    //shasta 一些拦截的地方
}
