package android.com.tronlink.wallet.mainTest;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DetailPage;
import android.com.wallet.pages.NFTPage;
import android.com.wallet.pages.ReceiptPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrxSuccessPage;
import android.com.wallet.pages.TrxPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ActiveAccountTest extends Base {


    private String NotActivePrivateKey = "7382169c3e67e6154d4b6273babd06a966b0571bdd73a0b98373b7cc5832d315";
    private String NotActiveAddress = "TBBBisuGArcTb6irH2stTpTp9Rj8YnxYCU";
//    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        log(NotActiveAddress);
        new Helper().getSign(NotActivePrivateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e){

        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

     @Test(alwaysRun = true)
     public void test001_NotActiveTransferTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterTransferPage();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法转账。请转入 TRX 激活账户。");
        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签转账");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterTransferPage();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_step.getText(),"(1/3)");
     }

    @Test(alwaysRun = true)
    public void test002_NotActiveReceiveTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterReceiptPage();
        Assert.assertEquals(asset.tv_scan_qr_and_pay.getText(),"扫描二维码向我付款");
    }

    @Test(alwaysRun = true)
    public void test003_NotActiveDepositTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterFrozenAndUnfreezePage();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法质押。您可进行多重签名质押，或转入 TRX 激活账户。");
        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签质押");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterFrozenAndUnfreezePage();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_step.getText(),"(1/3)");
    }

    @Test(alwaysRun = true)
    public void test004_NotActiveVoteTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterVotePage();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法投票。您可进行多重签名投票，或转入 TRX 激活账户。");
//        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签转账");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterVotePage();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_main_title.getText(),"多重签名投票");
    }

    @Test(alwaysRun = true)
    public void test005_NotActiveTrxPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        page.enterSendPage();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法转账。请转入 TRX 激活账户。");
        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签转账");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterTrxPage();
        page.enterSendPage();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_step.getText(),"(1/3)");
    }

    @Test(alwaysRun = true)
    public void test006_NotActiveTrc10PageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterSendTrc10Page();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法转账。请转入 TRX 激活账户。");
        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签转账");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterSendTrc10Page();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_step.getText(),"(1/3)");
    }

    @Test(alwaysRun = true)
    public void test007_NotActiveTrc20PageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterSendTrc20Page();
        Assert.assertEquals(asset.title.getText(),"当前账户未激活，无法转账。请转入 TRX 激活账户。");
        Assert.assertEquals(asset.btn_cancel.getText(),"发起多签转账");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterSendTrc20Page();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_step.getText(),"(1/3)");
    }

}
