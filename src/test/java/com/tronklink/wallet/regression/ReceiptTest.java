package com.tronklink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.ReceiptPage;
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
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        Helper.getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

//    @AfterClass
//    public void tearDownAfterClass() {
//        DRIVER.quit();
//    }


    @Parameters({"address"})
    @Test(description = "check Receipt Address")
    public void test001_ckeckReceiptTrxAddress(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage =  asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.ownerAddress_btn.getText(),address);
    }

//    @Test(description = "Is the copy function normal")
//    public void test002_copyNormal() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        ReceiptPage receiptPage =  asset.enterReceiptPage();
//        receiptPage.copy_btn.click();
//        Assert.assertEquals(false,receiptPage.copy_btn.isEnabled());
//    }


}
