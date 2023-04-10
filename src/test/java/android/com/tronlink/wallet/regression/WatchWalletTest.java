package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WatchWalletTest extends Base {

    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe", DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
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



    @Test(enabled = true,description = "test001_homePageTipsShowAndClose", alwaysRun = true)
    public void test001_homePageTipsShowAndClose() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("观察钱包仅支持查看资产，如您持有当前钱包对应的离线冷钱包，可进行配对以开放更多功能权限。  前往配对>",asset.tv_watch_reminder_tip.getText());
        asset.closeWatchTips();
        Assert.assertFalse(isElementShotId("tv_watch_reminder_tip"));
    }


    @Test(enabled = true,description = "Frozen Energy QRCode", alwaysRun = true)
    public void test002_addWalletInfoTestTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterImportView();
        TimeUnit.SECONDS.sleep(1);
        asset.swipScreenLitte();
        Assert.assertEquals("配对冷钱包",asset.cold_pair_title.getText());
        Assert.assertEquals("添加观察钱包",asset.observation_title.getText());
        Assert.assertEquals("仅可查看钱包的余额和交易信息",asset.observation_des.getText());
        asset.enterWatchImportView();
        Assert.assertTrue(isElementTextExist("观察钱包仅支持实时查看钱包的余额和交易信息，如您需要和离线冷钱包交互，请选择 “配对冷钱包”。"));
    }

    @Test(enabled = true,description = "Frozen Energy QRCode", alwaysRun = true)
    public void test003_watchInWalletManager() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        asset.enterWalletManager();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("配对离线冷钱包",asset.tv_cold_pair.getText());
        Assert.assertEquals("配对离线冷钱包，可开放更多功能权限",asset.cold_pair_info.getText());
        asset.enterColdPair();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals("配对冷钱包",asset.tv_common_title.getText());
        Assert.assertTrue(isElementTextExist("如何配对？"));
        asset.enterColdPairPage();
        Assert.assertTrue(isElementShotId("iv_qr3"));

    }



}
