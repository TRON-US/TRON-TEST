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
            TimeUnit.SECONDS.sleep(2);
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
        Assert.assertEquals(asset.btn_cancel.getText(),"多重签名质押");
        Assert.assertEquals(asset.confirm.getText(),"确认");
        asset.confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("confirm"));
        asset.enterFrozenAndUnfreezePage();
        asset.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(asset.tv_main_title.getText(),"多重签名");
        Assert.assertEquals(asset.net_error.getText(),"当前账户无可控制的账户");
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

     @Test(alwaysRun = true)
     public void test008_CreateAccountNotBackup() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         asset.createWalletNotBackUp();
         Assert.assertEquals(asset.tv_backup.getText(),"立即备份");
         Assert.assertEquals(asset.tv_desc.getText(),"您的钱包助记词尚未备份，为了防止丢失资产，请务必尽快备份助记词。");
         Assert.assertEquals(asset.tv_title.getText(),"安全提醒");
     }

     @Test(alwaysRun = true)
     public void test009_NotBackUpAccountTransferTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         asset.enterTransferPage();
         Assert.assertEquals(asset.title.getText(),"安全提醒");
         Assert.assertEquals(asset.info.getText(),"为了防止因忘记密码、应用卸载、手机丢失等情况导致资产损失，发起转账前需要先完成助记词备份。");
         Assert.assertEquals(asset.btn_confirm.getText(),"立即备份");
         Assert.assertEquals(asset.btn_cancel.getText(),"取消");
         asset.btn_confirm.click();
         TimeUnit.SECONDS.sleep(1);
         Assert.assertTrue(isElementTextExist("钱包管理"));

     }

    @Test(alwaysRun = true)
    public void test010_NotBackUpReceivedTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterReceiptPage();
        Assert.assertEquals(asset.title.getText(),"安全提醒");
        Assert.assertEquals(asset.info.getText(),"为了防止因忘记密码、应用卸载、手机丢失等情况导致资产损失，查看收款地址需要先完成助记词备份。");
        Assert.assertEquals(asset.btn_confirm.getText(),"立即备份");
        Assert.assertEquals(asset.btn_cancel.getText(),"取消");
        asset.btn_confirm.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementTextExist("钱包管理"));
    }

    @Test(alwaysRun = true)
    public void test012_NotBackUpVoteTest() throws Exception {
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

}
