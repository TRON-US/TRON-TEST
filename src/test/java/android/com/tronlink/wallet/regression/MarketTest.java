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
            DRIVER.activateApp("com.tronlink.wallet");
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


    @Test(groups = {"P0"},description = "MainNet market price test", alwaysRun = true)
    public void test01_marketPriceTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String title = marketPage.Market_title.getText();
        System.out.println(title);
        Assert.assertTrue(marketPage.market_search_btn.isEnabled());


        marketPage.price_btn.click();
        String price1 = marketPage.priceList.get(1).getText();
        System.out.println("price1 : " + price1);


        marketPage.price_btn.click();
        String price2 = marketPage.priceList.get(1).getText();
        System.out.println("price2 : " + price2);

        Assert.assertTrue(Float.valueOf(price1) > Float.valueOf(price2));

    }

    @Test(description = "MainNet market change test", alwaysRun = true)
    public void test02_marketChangeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();

        marketPage.quoteChange_btn.click();
        String range1 = marketPage.rangeList.get(1).getText();
        System.out.println("range1 : " + range1);


        marketPage.quoteChange_btn.click();
        String range2 = marketPage.rangeList.get(1).getText();
        System.out.println("range2 : " + range2);

        Assert.assertFalse(range1.equals(range2));

    }

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter dapp page
    public AssetPage enterDappAssetPage() throws Exception {
        SettingPage set = enterSettingPage();

        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset;
    }





}
