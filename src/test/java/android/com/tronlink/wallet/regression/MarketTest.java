package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MarketPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * market function test
 */

public class MarketTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    public MarketPage enterMarketPage() {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        return marketPage;
    }


    @Test(groups = {"P0"},description = "marketDisplayTest", alwaysRun = true)
    public void test001_marketDisplayTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        Assert.assertTrue(marketPage.market_search_btn.isDisplayed());
        Assert.assertTrue(marketPage.market_vol_btn.isDisplayed());
        Assert.assertTrue(marketPage.price_btn.isDisplayed());
        Assert.assertTrue(marketPage.riseChange_btn.isDisplayed());
        Assert.assertTrue(marketPage.priceList.size()>0);
    }

    @Test(description = "testSearchTextFieldDisplayAndClose",alwaysRun = true)
    public void test002_testSearchTextFieldDisplayAndClose() {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        marketPage.market_search_btn.click();
        waiteTime();
        Assert.assertTrue(marketPage.search_TF.getText().contains("请输入通证简称"));
        marketPage.findElementByText("取消").click();
        Assert.assertTrue(marketPage.market_search_btn.isDisplayed());
        Assert.assertTrue(marketPage.market_vol_btn.isDisplayed());
        Assert.assertTrue(marketPage.price_btn.isDisplayed());
        Assert.assertTrue(marketPage.riseChange_btn.isDisplayed());
        Assert.assertTrue(marketPage.priceList.size()>0);
    }

    @Test (description = "volchangeTest",alwaysRun = true)
    public void test003_volChangeTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String bigStr = marketPage.first_vol.getText();
        String bigName = marketPage.tv_top_name.getText();
        log("bigStr: " + bigStr + " bigName: " + bigName);
        marketPage.market_vol_btn.click();
        TimeUnit.SECONDS.sleep(3);
        String smallStr = marketPage.first_vol.getText();
        String smallName = marketPage.tv_top_name.getText();
        log("smallStr: " + smallStr + " smallName: " + smallName);
        Assert.assertNotEquals(bigName,smallName);
        Assert.assertNotEquals(bigStr,smallStr);
    }

    @Test (description = "priceChangeTest",alwaysRun = true)
    public void test004_priceChangeTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        marketPage.price_btn.click();
        TimeUnit.SECONDS.sleep(3);
        String bigStr = marketPage.tv_usd_price.getText();
        String bigName = marketPage.tv_top_name.getText();
        log("bigStr: " + bigStr + " bigName: " + bigName);
        marketPage.price_btn.click();
        TimeUnit.SECONDS.sleep(3);
        String smallStr = marketPage.tv_usd_price.getText();
        String smallName = marketPage.tv_top_name.getText();
        log("smallStr: " + smallStr + " smallName: " + smallName);
        Assert.assertNotEquals(bigName,smallName);
        Assert.assertNotEquals(bigStr,smallStr);
    }

    @Test (description = "riseChangeTest",alwaysRun = true)
    public void test005_riseChangeTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        marketPage.price_btn.click();
        TimeUnit.SECONDS.sleep(3);
        String bigStr = marketPage.firstRise.getText();
        String bigName = marketPage.tv_top_name.getText();
        log("bigStr: " + bigStr + " bigName: " + bigName);
        marketPage.price_btn.click();
        TimeUnit.SECONDS.sleep(3);
        String smallStr = marketPage.firstRise.getText();
        String smallName = marketPage.tv_top_name.getText();
        log("smallStr: " + smallStr + " smallName: " + smallName);
        Assert.assertNotEquals(bigName,smallName);
//        Assert.assertNotEquals(bigStr,smallStr);
    }

    @Test(description = "test006_testSearchBttPair",alwaysRun = true)
    public void test006_testSearchBttPair() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        marketPage.market_search_btn.click();
        waiteTime();
        marketPage.search_TF.sendKeys("BTT");
        TimeUnit.SECONDS.sleep(3);
        String topName = marketPage.tv_top_name.getText();
        Assert.assertEquals(topName,"BTT");
        Assert.assertTrue(marketPage.iv_delete.isDisplayed());
        marketPage.findElementByText("交易对");
        marketPage.findElementByText("最新价格");
        marketPage.findElementByText("涨跌幅");

    }



}
