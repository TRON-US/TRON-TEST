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

/**
 * receipt trx test
 */
public class ReceiptTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

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


    @Parameters({"address"})
    @Test(description = "check Receipt Address", alwaysRun = true)
    public void test001_ckeckReceiptTrxAddress(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage = asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.ownerAddress_btn.getText(), address);
    }

//    @Test(description = "Is the copy function normal")
//    public void test002_copyNormal() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        ReceiptPage receiptPage =  asset.enterReceiptPage();
//        receiptPage.copy_btn.click();
//        Assert.assertEquals(false,receiptPage.copy_btn.isEnabled());
//    }


}
