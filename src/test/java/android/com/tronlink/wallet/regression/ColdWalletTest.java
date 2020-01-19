package android.com.tronlink.wallet.regression;


import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ColdAssetPage;
import android.com.wallet.pages.SearchAssertPage;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 资产页面测试
 */
public class ColdWalletTest extends Base {
    String addressString;


    @Parameters({"privateKey","address"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey,String address) throws Exception {
        AppiumTestCase.cmdReturn("adb shell svc wifi disable");
        new Helper().getColdWalletSign(privateKey, DRIVER);
        addressString = address;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            AppiumTestCase.cmdReturn("adb shell svc wifi enable");
            DRIVER.quit();
        } catch (Exception e) {
        }

    }


    @Test(enabled = false,description = "Cold wallet asset page test", alwaysRun = true)
    public void test001_enterColdWallet() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        Assert.assertTrue(coldAsset.scanQrSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.receive_btn.isEnabled());
        Assert.assertTrue(coldAsset.offlineSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.deleteColdWalletKnowledge_btn.isEnabled());
        Assert.assertTrue(coldAsset.me_btn.isEnabled());
    }

    @Test(enabled = false,description = "Cold wallet receive test", alwaysRun = true)
    public void test002_coldWalletCanReceiveTrx() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        coldAsset.receive_btn.click();
        Assert.assertTrue(coldAsset.address_text.getText().equals(addressString));
    }


}
