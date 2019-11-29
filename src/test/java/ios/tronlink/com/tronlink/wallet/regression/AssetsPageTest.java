package ios.tronlink.com.tronlink.wallet.regression;



import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * 资产页面测试
 */
public class AssetsPageTest extends BaseTest {


    @Test(description = "Assert TransportPage test",alwaysRun = true)
    public void test001_transportPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        TransferPage transferPage = assetPage.enterTransportPage();
        Assert.assertEquals(transferPage.title.getText(),"转账");
    }

    @Test(description = "Assert ReceiptPage test",alwaysRun = true)
    public void test002_gotoReceiptPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        ReceiptPage receiptPage = assetPage.enterReceiptCoinPage();
        Assert.assertEquals(receiptPage.title.getText(),"收款");
    }

    @Test(description = "Assert frozenandunfreezePage test",alwaysRun = true)
    public void test003_frozenandunfreezePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage receiptPage = assetPage.enterFrozenAndThawingPage();
        Assert.assertEquals(receiptPage.assert_title.getText(),"资源");
    }

    @Test(description = "Assert vote test",alwaysRun = true)
    public void test004_gotovotePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        VotePage receiptPage = assetPage.enterVotePage();
        Assert.assertEquals(receiptPage.vote_title.getText(),"投票");
    }

    @Test(description = "Assert add assseet test",alwaysRun = true)
    public void test005_gotoAssetPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        AddAssertPage receiptPage = assetPage.enterAddAssertPage();
        Assert.assertEquals(receiptPage.title.getText(),"资产");
    }

    @Test(description = "Assert energy rent test",alwaysRun = true)
    public void test006_gotoRentPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        EnergyRentPage receiptPage = assetPage.entereneryRantage();
        TimeUnit.SECONDS.sleep(3);
        Thread.sleep(5);
        Assert.assertTrue(Helper.isElementExist(receiptPage.driver,"租用记录"));
    }
}
