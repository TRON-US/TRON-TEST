package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import wallet.UITest.base.Base;
import wallet.pages.AddAssertPage;
import wallet.pages.AssetPage;
import wallet.pages.SearchAssertPage;

/**
 * 添加资产测试
 */
public class AddAssetsTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
//    }

    @BeforeMethod()
    public void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign();
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "test remove assert")
    public void test001_removeAsset(){
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage addAssert =  asset.enterAddAssertPage();
        addAssert.mainPageAssetManage_tab.get(1).click();
        addAssert.removeAsset();
        Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
    }


//    @Test(description = "test add assert")
//    public void test002_addAsset() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert =  asset.enterAddAssertPage();
//        SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
//        searchAssert.addAssert_input.sendKeys("1000029");
//        searchAssert.openAssert();
//        TimeUnit.SECONDS.sleep(5);
//        addAssert = searchAssert.enterAddAssertPage();
//        addAssert.mainPageAssetManage_tab.get(1).click();
//        System.out.println("点击完了mainPageAssetManage_tab");
//        Assert.assertTrue(addAssert.myNewAddAsset_btn.isDisplayed());
//    }





}
