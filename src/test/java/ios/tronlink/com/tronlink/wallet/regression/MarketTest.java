package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MarketPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * 关于我们功能测试
 */
public class MarketTest extends BaseTest {


    @Test(description = "Enter Market  test",alwaysRun = true)
    public void test001_EnterMarket() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MarketPage marketPage =  assetPage.enterMarketPage();
        Assert.assertTrue(marketPage.my_Pair.isDisplayed());
        Assert.assertTrue(marketPage.usdt_Pair.isDisplayed());
        Assert.assertTrue(marketPage.trx_Pair.isDisplayed());
        Assert.assertTrue(marketPage.amount_OrderBtn.isDisplayed());
        Assert.assertTrue(marketPage.newlest_OrderBtn.isDisplayed());
        Assert.assertTrue(marketPage.updown_OrderBtn.isDisplayed());
        Assert.assertTrue(marketPage.market_Search.isDisplayed());
    }


    @Test(description = "firstNoPairTest",alwaysRun = true)
    public void test002_firstNoPairTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MarketPage marketPage =  assetPage.enterMarketPage();
        marketPage.my_Pair.click();
        waiteTime();
        Assert.assertTrue(marketPage.noPair_Text.isDisplayed());
    }

    @Test(description = "test003_tapPairTest",alwaysRun = true)
    public void test003_tapPairTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MarketPage marketPage =  assetPage.enterMarketPage();
        waiteTime();
        Assert.assertTrue(marketPage.trxlist.size() > 2);
        marketPage.my_Pair.click();
        Assert.assertTrue(marketPage.noPair_Text.isDisplayed());
        marketPage.usdt_Pair.click();
        Assert.assertTrue(marketPage.usdtlist.size() > 2);

    }








}
