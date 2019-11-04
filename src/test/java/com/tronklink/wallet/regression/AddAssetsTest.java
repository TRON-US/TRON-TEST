package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AddAssertPage;
import wallet.pages.AssetPage;
import wallet.pages.SearchAssertPage;

/**
 * add asset test
 */
public class AddAssetsTest extends Base {


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
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



    @Test(description = "test add assert",alwaysRun = true)
    public void test002_addAsset() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage addAssert =  asset.enterAddAssertPage();
        SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
        searchAssert.addAssert_input.sendKeys("1000029");
        searchAssert.openAssert();
        TimeUnit.SECONDS.sleep(5);
        addAssert = searchAssert.enterAddAssertPage();
        addAssert.mainPageAssetManage_tab.get(1).click();
        Assert.assertTrue(addAssert.myNewAddAsset_btn.isDisplayed());
    }


    @Test(description = "test remove asset",alwaysRun = true)
    public void test003_removeAsset(){
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage addAssert =  asset.enterAddAssertPage();
        addAssert.mainPageAssetManage_tab.get(1).click();
        addAssert.removeAsset();
        Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
    }




}
