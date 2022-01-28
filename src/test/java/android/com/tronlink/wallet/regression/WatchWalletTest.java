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
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "Frozen Energy QRCode", alwaysRun = true)
    public void test002_frozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        TimeUnit.SECONDS.sleep(3);
        frozen.energy_btn.click();
        Helper.swipScreenLitte(DRIVER);
        frozen.freezeCount_input.sendKeys("1");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(DRIVER);
        frozen.frozenButtonClickAndConfirm();
//        frozen.confirm_btn().click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "Frozen Bandwidth QRCode", alwaysRun = true)
    public void test003_frozenBandwidthQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.bandwidth_btn.click();
        Helper.swipScreenLitte(DRIVER);
        frozen.freezeCount_input.sendKeys("1");
        Helper.swipScreen(DRIVER);
        frozen.frozenButtonClickAndConfirm();
//        frozen.confirm_btn().click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "create proposal QRCode", alwaysRun = true)
    public void test004_createCommitteeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        createProposePage.proValue_ipt.get(1).clear();
        createProposePage.proValue_ipt.get(1).sendKeys("9997");
        createProposePage.Creatconfirm_btn.click();
        createProposePage.confirm_btn().click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "muliSignature QRCode", alwaysRun = true)
    public void test005_muliSignatureQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage multiSignManager = myPursePage.enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest_0001");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



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
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
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
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "Deposit QRCode", alwaysRun = true)
    public void test010_depositTrxQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrxPage();
        TransferPage transfer = trxPage.enterTransferInPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        transfer.bt_send.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Unfreeze transaction QRCode", alwaysRun = true)
    public void test011_UnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.currentType_btn.click();
        frozen.unfreezeType_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreenLitte(frozen.driver);
        frozen.unfreezeTargetAddress_btn.click();
        frozen.unfreeze_btn.click();
        TimeUnit.SECONDS.sleep(1);
        frozen.unfreezeInfoConfirm_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Withdraw reward transaction QRCode", alwaysRun = true)
    public void test012_WithdrawRewardQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reward_btn.click();
        vote.confirm_btn().click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



}
