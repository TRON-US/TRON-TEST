package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.TransactionRecordPage;

import org.testng.annotations.*;
/**
 * Transaction Record function test
 */
public class TransactionRecordTest extends Base {


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



    @Test(description = "Transaction Record test")
    public void test001_transactionRecord() {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        transaction.navigation_tab.click();
        Assert.assertEquals(transaction.owner_text.getText(),"TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of");
    }


}
