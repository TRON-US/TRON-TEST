package com.tronklink.wallet.regression;

import com.tronlink.wallet.UITest.base.Base;
import com.tronlink.wallet.pages.AssetPage;
import com.tronlink.wallet.pages.MarketPage;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class MarketFunctionTest extends Base {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Base.setUpBeforeClass();
//      Base.getSign();
    }

    @BeforeMethod()
    public static void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        Base.getSign();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    @Test //测试进入市场页
    public void test01_checkEnterMarketPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        assertEquals(true,marketPage.Market_title.isDisplayed());
    }


    @Test //测试点击价格排序功能是否正常
    public void test02_newPriceSort() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortFun();
        String priceAsc = marketPage.sortFun();
        //assertThat(priceDesc,is(equalTo(priceAsc)));
        assertNotEquals(priceDesc,priceAsc);
    }



}
