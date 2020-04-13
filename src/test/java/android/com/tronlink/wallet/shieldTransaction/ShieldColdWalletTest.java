package android.com.tronlink.wallet.shieldTransaction;


import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ColdAssetPage;
import android.com.wallet.pages.ColdMinePage;
import android.com.wallet.pages.ColdSettingPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SettingPage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 资产页面测试
 */
public class ShieldColdWalletTest extends Base {
    String addressString;


    @Parameters({"shieldSK","shieldAddress","udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String shieldSK,String shieldAddress,String udid) throws Exception {
        AppiumTestCase.cmdReturn("adb -s " + udid + " shell svc wifi disable");
        TimeUnit.SECONDS.sleep(3);
        new Helper().getShieldColdWalletSign(shieldSK, DRIVER);
        addressString = shieldAddress;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @Parameters({"udid"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        //Base.tearDownAfterClass();
        try {
            AppiumTestCase.cmdReturn("adb -s " + udid + " shell svc wifi enable");
            TimeUnit.SECONDS.sleep(3);
            DRIVER.quit();
        } catch (Exception e) {
        }

    }


    @Test(groups = {"P0"},enabled = true,description = "Shield cold wallet asset page test", alwaysRun = true)
    public void test001_enterShieldColdWallet() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        Assert.assertTrue(coldAsset.scanQrSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.receive_btn.isEnabled());
        Assert.assertTrue(coldAsset.offlineSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.deleteColdWalletKnowledge_btn.isEnabled());
        Assert.assertTrue(coldAsset.me_btn.isEnabled());
    }

    @Test(groups = {"P0"},enabled = true,description = "Shield cold wallet receive test", alwaysRun = true)
    public void test002_shieldColdWalletCanReceiveTrx() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        coldAsset.receive_btn.click();
        Assert.assertTrue(coldAsset.address_text.getText().equals(addressString));
        Assert.assertTrue(coldAsset.copy_btn.isEnabled());
    }

    @Test(enabled = true,description = "Shield cold wallet manager test", alwaysRun = true)
    public void test003_coldWalletManagerTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        ColdMinePage coldMine = coldAsset.enterColdMinePage();
        coldMine.walletManager_btn.click();
        coldMine.address_test.getText().equals(addressString);
        coldMine.walletName_test.getText().contains("Cold");
    }







}
