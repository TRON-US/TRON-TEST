package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MarketPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.junit.Assert.assertEquals;

/**
 * market function test
 */

public class MarketTest extends BaseTest {

//    @BeforeClass
//    public void setUpBeforeClass() throws Exception {
//        setUp();
////      Base.getSign(privateKey);
//    }


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
