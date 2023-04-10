package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


/**
 * 资产页面测试
 */
public class AssetsPageTest extends BaseTest {


    @Test(description = "Assert TransportPage test", alwaysRun = true)
    public void test001_transportPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        TransferPage transferPage = assetPage.enterTransportPage();
        Assert.assertTrue(isElementExist("转账 (1/2)"));

    }

    @Test(description = "Assert ReceiptPage test", alwaysRun = true)
    public void test002_gotoReceiptPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        ReceiptPage receiptPage = assetPage.enterReceiptCoinPage();
        Assert.assertEquals(receiptPage.title.getText(), "收款");
    }

    @Test(description = "Assert frozenandunfreezePage test", alwaysRun = true)
    public void test003_frozenAndUnfreezePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage receiptPage = assetPage.enterFrozenAndThawingPage();
        Assert.assertTrue(isElementExist("质押"));
    }


    @Test(description = "Assert vote test", alwaysRun = true)
    public void test004_56gotoVotePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        VotePage secondPage = assetPage.enterVotePage();
        Assert.assertTrue(isElementExist("超级代表"));
        secondPage.backNav();
        AddAssertPage addAssertPage = assetPage.enterAddAssertPage();
        Assert.assertTrue(Helper.isElementExist(addAssertPage.driver,"我的全部资产"));
//        secondPage.blackBackBtn.click();
//        SwapPage receiptPage = assetPage.enterSwapPage();
//        Assert.assertTrue(Helper.isElementExist(receiptPage.driver,"闪兑"));
    }



    @Test(enabled = true,description = "assets total amount test", alwaysRun = true)
    public void test007_AssetsAmountAndFrozenTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(isElementExist("已质押: 114")); //无ID 无法定位
        String about = asset.trxValue.getText();
        String result = sepLeftNumberTextToString(about,"TRX").trim();
        Double number = Double.parseDouble(removeSymbolString(result));
        System.out.println("Total number:" + number);
        Assert.assertTrue(number > 0 );
    }

    @Test(enabled = true,description = "assets total about amount test", alwaysRun = true)
    public void test008_AssetsTotalAboutAmountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        String about = asset.assetsLabel.getText();
        System.out.println("about number:" + about);
        String result;
        if (about.contains("$")){
            result = about.replace("≈$"," ").trim();
        }else {
            result = about.replace("≈¥"," ").trim();
        }
        Double number = Double.parseDouble(removeSymbolString(result));
        System.out.println("Total about number:" + number);

        Assert.assertTrue(number > 0 );
    }

    @Test(alwaysRun = true)
    public void test009_TRXProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TRON\""));
        Assert.assertTrue(isElementPredicateExist("label == \"https://tron.network/\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"6\""));
        Assert.assertTrue(isElementPredicateExist("label == \"Official Token of TRON Protocol\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2018/06/25 09:51:09\""));

    }
    @Test(alwaysRun = true)
    public void test010_TRC10ProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx10Page();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"通证 ID\""));
        Assert.assertTrue(isElementPredicateExist("label == \"1000002\""));
        Assert.assertTrue(isElementPredicateExist("label == \"http://nileex.io/\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"nileex_TestCoin\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2019/11/27 15:11:00\""));

    }

    @Test(alwaysRun = true)
    public void test011_TRC20ProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"Tronix(TRX)\""));
//        Assert.assertTrue(isElementPredicateExist("label == \"最新价格\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TCCcBZEdTHmS1NfFtCYfwpjBKeTv515n71\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TronlinknilheTRC20\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2019-12-04 03:29:01\""));

    }

}
