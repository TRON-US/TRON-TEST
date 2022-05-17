package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WatchWalletTest extends Base {

    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");



    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe", DRIVER);
    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }



    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }




    @Test(enabled = true,description = "watch wallet sendTrx QRCode", alwaysRun = true)
    public void test001_sendTrxQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("1");
        sendTrxPage.send_btn.click();
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "Frozen Energy QRCode", alwaysRun = true)
    public void test002_frozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.frozenTheEnergy(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        frozen.btn_confirm.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "Frozen Bandwidth QRCode", alwaysRun = true)
    public void test003_frozenBandwidthQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.frozenTheBandwidth(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        frozen.btn_confirm.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "create proposal QRCode", alwaysRun = true)
    public void test004_createCommitteeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        createProposePage.proValue_ipt.get(1).clear();
        createProposePage.proValue_ipt.get(1).sendKeys("9919");
        createProposePage.Creatconfirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        createProposePage.btn_confirm.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

     @Test(alwaysRun = true)
     public void test005_NotActivedShow() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
         sendTrxPage.receiveAddress_text.sendKeys("TV7xNv1n8or37qrSqCoPyqo26QRTxMzYUi");
         Assert.assertTrue(sendTrxPage.error_view.getText().contains("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
     }

//    @Test(enabled = true,description = "muliSignature QRCode", alwaysRun = true)
//    public void test005_muliSignatureQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        MyPursePage myPursePage = mine.enterMyPursePage();
//        MultiSignManagerPage multiSignManager = myPursePage.enterMultiSignManagerPage();
//        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
//        add.inputInfo("AutoTest_0001");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }



    @Test(enabled = true,description = "Deposit QRCode", alwaysRun = true)
    public void test006_DepositQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrxPage();
        TransferPage transfer = trxPage.enterTransferInPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        transfer.bt_send.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "Send trc10 QRCode", alwaysRun = true)
    public void test007_trc10QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(5);
        SendTrxPage sendTrxPage  = asset.enterSendTrc10Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("1");
        sendTrxPage.send_btn.click();
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "vote QRCode", alwaysRun = true)
    public void test008_voteQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        try {
            vote.close.click();
        }catch (Exception e){
            log("not found pop view");
        }
        vote.firstSR.click();
        TimeUnit.SECONDS.sleep(1);
        vote.vote_page_btn.click();
        vote.et_input.sendKeys("1");
        vote.bt_send.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "Send trc20 QRCode", alwaysRun = true)
    public void test009_trc20QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrc20Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("1");
        sendTrxPage.send_btn.click();
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "Unfreeze transaction QRCode", alwaysRun = true)
    public void test011_UnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.toUnfreezePage();
        frozen.ll_container.click();
        frozen.btn_next.click();
        Assert.assertTrue(frozen.btn_confirm.getText().contains("生成交易二维码"));
        frozen.btn_confirm.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Withdraw reward transaction QRCode", alwaysRun = true)
    public void test012_WithdrawRewardQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.caim_reward.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(vote.btn_confirm.getText().contains("生成交易二维码"));
        vote.btn_confirm.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



}
