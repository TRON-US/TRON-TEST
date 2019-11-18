package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MarketPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class MarketFunctionTest extends BaseTest {


    @Test(alwaysRun = true) //测试进入市场页
    public void test01_checkEnterMarketPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        Assert.assertEquals(true,marketPage.Market_title.isDisplayed());
    }


    @Test(alwaysRun = true) //测试点击价格排序功能是否正常
    public void test02_newPriceSort() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MarketPage marketPage = asset.enterMarketPage();
        String priceDesc = marketPage.sortPrice();
        String priceAsc = marketPage.sortPrice();
        //assertThat(priceDesc,is(equalTo(priceAsc)));
        Assert.assertNotEquals(priceDesc,priceAsc);
    }



}
