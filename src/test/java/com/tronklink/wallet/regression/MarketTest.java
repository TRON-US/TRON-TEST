package com.tronklink.wallet.regression;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.MarketPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.testng.annotations.*;

/**
 * 行情功能测试
 */

public class MarketTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
////      Base.getSign(privateKey);
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
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }

    public MarketPage enterMarketPage() {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        return marketPage;
    }


    @Test //测试进入市场页
    public void test01_checkEnterMarketPage() throws Exception {
        MarketPage marketPage = enterMarketPage();
        //AssetPage asset = new AssetPage(DRIVER);
        //MarketPage marketPage = asset.enterMarketPage();
        assertEquals(true,marketPage.Market_title.isDisplayed());
    }


    @Test //测试点击价格排序功能是否正常
    public void test02_newPriceSort() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortFun();
        String priceAsc = marketPage.sortFun();
        //assertThat(priceDesc,is(equalTo(priceAsc)));
        Assert.assertNotEquals(priceDesc,priceAsc);
    }





}
