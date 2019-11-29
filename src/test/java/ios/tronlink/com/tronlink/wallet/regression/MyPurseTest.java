package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyPurseTest extends BaseTest {
    String password = "Test0001";
    String keystore = "";
    @Test(description = "test goto MyPurse",alwaysRun = true)
    public void test001_gotoMyPurse() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertEquals(walletPage.title.getText(),"钱包管理");
    }

    @Test(description = "test have KeyStore",alwaysRun = true)
    public void test002_haveKeyStore() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        keystore = walletPage.getBackupKeystore("Test0001");
        Assert.assertTrue(keystore.length() > 10);
    }


    @Test(description = "test Clipboard have KeyStore",alwaysRun = true)
    public void test003_Clipboard_haveKeyStore() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        String copyed = walletPage.getBackupKeystoreInClipboard(password);
        Assert.assertTrue(copyed.contains("已复制"));
    }

    @Test(description = "test Delete Wallet Cancal",alwaysRun = true)
    public void  test004_testDeletewalletCancal(){
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertTrue(walletPage.deletableCancel(password));
    }
    @Test(description = "test Delete Wallet wrong password",alwaysRun = true)
    public void  test005_testDeletewalletWronpassCancal(){
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertTrue(walletPage.deletWallet(password+"123424"));
    }
    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test006_testDeletewalletSuccess() throws InterruptedException {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertTrue(walletPage.deletWallet(password));
    }
}
