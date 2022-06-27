package android.com.tronlink.wallet.abandon;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MarketPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * market function test
 */

public class ImportMoreWallets extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }



     @Test(alwaysRun = true)
     public void test001_AddWalletAndroid() throws Exception {
         new AssetPage(DRIVER);
         TimeUnit.SECONDS.sleep(20);

         for (int i = 0; i < 10; i++) {
             TimeUnit.SECONDS.sleep(8);
             String privateKey =  Configuration.getByPath("testng.conf").getString("androidMultiSignAccount.privateKey" + (i+1));
             log(privateKey);
             new Helper().AddMoreWalletWithPrivateKey(privateKey, DRIVER);
         }
     }



}
