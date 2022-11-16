package ios.tronlink.com.tronlink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;


/**
 * 关于我们功能测试
 */
public class ReceiptTest extends BaseTest {



    @Parameters("address")
 @Test(alwaysRun = true)
 public void test001_ReceiptPageTest(String address) throws Exception {
     AssetPage asset = new AssetPage(DRIVER);
     asset.enterReceiptPage();
     Assert.assertTrue(isElementExist(address));
     Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("保存二维码"));

    }

    @Parameters("address")
    @Test(alwaysRun = true)
    public void test002_TRXPageEnterReceiptPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        page.enterReceiptPage();
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("保存二维码"));

    }

    @Parameters("address")
    @Test(alwaysRun = true)
    public void test003_TRC10PageEnterReceiptPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        page.enterReceiptPage();
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("保存二维码"));

    }

    @Parameters("address")
    @Test(alwaysRun = true)
    public void test004_TRC20PageEnterReceiptPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        page.enterReceiptPage();
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("保存二维码"));

    }

}
