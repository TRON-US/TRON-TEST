package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MarketPage;

import org.testng.Assert;

import static org.hamcrest.core.Is.is;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.*;

public class MarketFunctionTest extends Base {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
////      Base.getSign(privateKey);
//    }

//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }


    @Test(alwaysRun = true) //测试进入市场页
    public void test01_checkEnterMarketPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        Assert.assertEquals(true, marketPage.Market_title.isDisplayed());
    }


    @Test(alwaysRun = true) //测试点击价格排序功能是否正常
    public void test02_newPriceSort() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortPrice();
        String priceAsc = marketPage.sortPrice();
        //assertThat(priceDesc,is(equalTo(priceAsc)));
        Assert.assertNotEquals(priceDesc, priceAsc);
    }


}
