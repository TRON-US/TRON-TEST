package com.tronklink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.ReceiptPage;
import org.testng.annotations.*;
/**
 * receipt trx test
 */
public class ReceiptTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
//    }

    @Parameters({"privateKey"})
    @BeforeMethod()
    public void setUpBefore(String privateKey) throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign(privateKey);
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "check Receipt Address")
    public void test001_ckeckReceiptTrxAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage =  asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.ownerAddress_btn.getText(),"TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    }

//    @Test(description = "Is the copy function normal")
//    public void test002_copyNormal() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        ReceiptPage receiptPage =  asset.enterReceiptPage();
//        receiptPage.copy_btn.click();
//        Assert.assertEquals(false,receiptPage.copy_btn.isEnabled());
//    }


}
