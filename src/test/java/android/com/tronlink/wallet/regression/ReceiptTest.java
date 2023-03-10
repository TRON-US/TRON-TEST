package android.com.tronlink.wallet.regression;


import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ReceiptPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * receipt trx test
 */
public class ReceiptTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
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


    @Parameters({"address"})
    @Test(description = "check Receipt Address", alwaysRun = true)
    public void test001_ckeckReceiptTrxAddress(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage = asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.tv_address.getText(), address);
        Assert.assertTrue(receiptPage.isElementExist("com.tronlinkpro.wallet:id/qr"));
        Assert.assertTrue(receiptPage.pagetitle.getText().contains("扫描二维码向我付款"));
        Assert.assertTrue(receiptPage.wallettitle.getText().contains("Auto-test"));
        receiptPage.copy_btn.click();
        Assert.assertTrue(assertToast("已复制"));
    }


//     @Test(alwaysRun = true)
//         public void test002_watchWalletTitle() throws Exception {
//             AssetPage asset = new AssetPage(DRIVER);
//             TimeUnit.SECONDS.sleep(2);
//             if (!asset.tv_address.getText().contains("THmx")){
//                 asset.addWatchWallet("THmxT8BKeXSKd4Zu4HJN3hXPVC1xiNhEnX");
//                 TimeUnit.SECONDS.sleep(8);
//             }
//
//             ReceiptPage receiptPage = asset.enterReceiptPage();
//             Assert.assertEquals(receiptPage.tv_address.getText(), "THmxT8BKeXSKd4Zu4HJN3hXPVC1xiNhEnX");
//             Assert.assertTrue(receiptPage.isElementExist("com.tronlinkpro.wallet:id/qr"));
//             Assert.assertTrue(receiptPage.tv_receive_watchonly.getText().contains("观察钱包"));
//             receiptPage.copy_btn.click();
//             Assert.assertTrue(assertToast("已复制"));
//     }

}
