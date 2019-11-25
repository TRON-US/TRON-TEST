package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MarketPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MarketTest extends BaseTest {

    @Test(description = "test Me into MarketPage",alwaysRun = true)
    public void test001_enteraboutus() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MarketPage minePage =  assetPage.enterMarketPage();

//        Assert.assertTrue(Helper.contentTexts(minePage.textArray,"行情"));
    }
}
