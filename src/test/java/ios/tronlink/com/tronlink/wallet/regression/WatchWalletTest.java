package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.FrozenAndUnfreezePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MultiSignManagerPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.WatchWalletHelpPage;

import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WatchWalletTest extends BaseTest {

  

    @Test(groups = {"P0"},description = "watch wallet add WatchWallet", alwaysRun = true)
    public void test001_addWatchWallet() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.importWatchWallet();
        Assert.assertTrue(assetPage.getWalletName().contains("WatchWallet"));
    }

    @Test(groups = {"P0"},enabled = true, description = "watch wallet sendTrx QRCode", alwaysRun = true)
    public void test002_sendTrxQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.sendTrx();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }
    @Test(enabled = true, description = "watch wallet send10Token QRCode", alwaysRun = true)
    public void test003_send10TokenQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.send10token();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

    @Test(enabled = true, description = "watch wallet send10Token QRCode", alwaysRun = true)
    public void test004_send20TokenQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.send20token();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

    @Test(groups = {"P0"},enabled = true, description = "Frozen Energy QRCode", alwaysRun = true)
    public void test005_frozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("1");
        frozen.getFreeze_btn().click();
        Assert.assertFalse(frozen.confirmDeposit().isEnabled());
        frozen.agreeClick();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(frozen.confirmDeposit().isEnabled());
        frozen.confirmDeposit().click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementExist("1 票"));
        frozen.confirmWatchBtn().click();
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        Assert.assertTrue(helpPage.isEnterColdPage());
    }


    @Test(groups = {"P0"},enabled = true, description = "create proposal QRCode", alwaysRun = true)
    public void test006_createCommitteeQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.makeApropos();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }


    @Test(groups = {"P0"},enabled = true, description = "muliSignature QRCode", alwaysRun = true)
    public void test007_multiSignatureQRCode() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.addPermissionWatch("AddActive");
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

//    @Test(enabled = true, description = "transferIn QRCode", alwaysRun = true)
//    public void test008_transferInQRCode() throws Exception {
//        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
//        helpPage.maketransferIn();
//        Assert.assertTrue(helpPage.isEnterColdPage());
//    }

}
