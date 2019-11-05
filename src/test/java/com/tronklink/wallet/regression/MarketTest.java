package com.tronklink.wallet.regression;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.MarketPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.testng.annotations.*;

/**
 * market function test
 */

public class MarketTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
////      Base.getSign(privateKey);
//    }


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
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


    @Test(description = "Enter MarketPage Test",alwaysRun = true)
    public void test01_checkEnterMarketPage() throws Exception {
        MarketPage marketPage = enterMarketPage();
        //AssetPage asset = new AssetPage(DRIVER);
        //MarketPage marketPage = asset.enterMarketPage();
        assertEquals(true,marketPage.Market_title.isDisplayed());
    }


    @Test(description = "newest price Sort Test",alwaysRun = true) //测试点击价格排序功能是否正常
    public void test02_newPriceSort() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortPrice();
        String priceAsc = marketPage.sortPrice();
        //assertThat(priceDesc,is(equalTo(priceAsc)));
        Assert.assertNotEquals(priceDesc,priceAsc);
    }


    @Test(description = "Quote change Sort Test",alwaysRun = true)
    public void test03_quoteChange() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortQuoteChange();
        String priceAsc = marketPage.sortQuoteChange();
        Assert.assertNotEquals(priceDesc,priceAsc);
    }





}
