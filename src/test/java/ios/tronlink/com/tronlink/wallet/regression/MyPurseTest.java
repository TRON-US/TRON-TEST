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
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(Helper.contentTexts(walletPage.textArray,"钱包管理"));
    }


}
