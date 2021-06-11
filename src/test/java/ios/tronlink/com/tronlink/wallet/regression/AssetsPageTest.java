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
        Assert.assertEquals(transferPage.title.getText(), "转账");

    }

    @Test(description = "Assert ReceiptPage test", alwaysRun = true)
    public void test002_gotoReceiptPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        ReceiptPage receiptPage = assetPage.enterReceiptCoinPage();
        Assert.assertEquals(receiptPage.title.getText(), "收款");
    }

    @Test(description = "Assert frozenandunfreezePage test", alwaysRun = true)
    public void test003_frozenandunfreezePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage receiptPage = assetPage.enterFrozenAndThawingPage();
        Assert.assertEquals(receiptPage.assert_title.getText(), "资源");
    }



    @Test(description = "Assert vote test", alwaysRun = true)
    public void test004_56gotovotePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        VotePage secondPage = assetPage.enterVotePage();
        Assert.assertTrue(Helper.isElementExist(secondPage.driver,"投票"));
        secondPage.whiteBackBtn.click();
        AddAssertPage assassetPage = assetPage.enterAddAssertPage();
        Assert.assertTrue(Helper.isElementExist(assassetPage.driver,"资产"));
        secondPage.blackBackBtn.click();
        SwapPage receiptPage = assetPage.enterSwapPage();
        Assert.assertTrue(Helper.isElementExist(receiptPage.driver,"闪兑"));
    }



    @Test(enabled = true,description = "assets total amount test", alwaysRun = true)
    public void test007_AssetsTotalAmountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        String about = asset.trxValue.getText();
        String result = sepLeftNumberTextToString(about,"TRX").trim();
        Double number = Double.parseDouble(removeSymbolFloat(result));
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
        Double number = Double.parseDouble(removeSymbolFloat(result));
        System.out.println("Total about number:" + number);

        Assert.assertTrue(number > 0 );
    }

}
