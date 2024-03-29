package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.AppiumTestCase;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.WatchWalletHelpPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

    @Test(groups = {"P0"},enabled = true, description = "Frozen Energy QRCode", alwaysRun = true)
    public void test004_frozenEnergyQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.frozenTRX();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

    //
//    @Test(enabled = true, description = "Frozen Bandwidth QRCode", alwaysRun = true)
//    public void test005_frozenBandwidthQRCode() throws Exception {
//        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
//        helpPage.frozenTRXbandwidth();
//        Assert.assertTrue(helpPage.isEnterColdPage());
//    }


    @Test(groups = {"P0"},enabled = true, description = "create proposal QRCode", alwaysRun = true)
    public void test006_createCommitteeQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.makeApropos();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }


    @Test(groups = {"P0"},enabled = true, description = "muliSignature QRCode", alwaysRun = true)
    public void test007_multiSignatureQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.makemultiSign();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

    @Test(groups = {"P0"},enabled = true, description = "transferIn QRCode", alwaysRun = true)
    public void test008_transferInQRCode() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        helpPage.maketransferIn();
        Assert.assertTrue(helpPage.isEnterColdPage());
    }

}
