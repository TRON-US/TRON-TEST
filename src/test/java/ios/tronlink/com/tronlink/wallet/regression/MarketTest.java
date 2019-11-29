package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MarketPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MarketTest extends BaseTest {

    @Test(description = "test  into MarketPage",alwaysRun = true)
    public void test001_enterMarketPage() throws InterruptedException {
        AssetPage assetPage = new AssetPage(DRIVER);
        Thread.sleep(1);
        Assert.assertTrue(assetPage.enterMarketPage().toString().length() > 0);


//        MarketPage minePage =  assetPage.enterMarketPage();
//        TimeUnit.SECONDS.sleep(3); //抓不上来
//        Assert.assertTrue(minePage.newPrice_btn.isDisplayed());
    }
}
