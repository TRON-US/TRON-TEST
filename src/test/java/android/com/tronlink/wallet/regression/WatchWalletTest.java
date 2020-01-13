package android.com.tronlink.wallet.regression;

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


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }



    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        //TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe
        //b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647
        new Helper().getWatchWalletSign("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe", DRIVER);

    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
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
        Helper.swipScreen(DRIVER);
        frozen.freezeCount_input.sendKeys("1");
        Helper.swipScreen(DRIVER);
        frozen.freeze_btn.click();
        TimeUnit.SECONDS.sleep(1);
        frozen.freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "Frozen Bandwidth QRCode", alwaysRun = true)
    public void test003_frozenBandwidthQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        Helper.swipScreen(DRIVER);
        frozen.bandwidth_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        Helper.swipScreen(DRIVER);
        frozen.freeze_btn.click();
        TimeUnit.SECONDS.sleep(1);
        frozen.freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(1);
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
        createProposePage.confirm_btn.click();
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



    @Test(enabled = true,description = "tranferIn QRCode", alwaysRun = true)
    public void test006_tranferInQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrxPage();
        TransferPage transfer = trxPage.enterTransferPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "tranferOut QRCode", alwaysRun = true)
    public void test007_trc10QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrx10Page();
        TransferPage transfer = trxPage.enterTransferPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(enabled = true,description = "vote QRCode", alwaysRun = true)
    public void test008_voteQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        vote.et_input.sendKeys("1");
        vote.vote_btn.click();
        TimeUnit.SECONDS.sleep(2);
        vote.btgo_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "tranferOut QRCode", alwaysRun = true)
    public void test009_trc20QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrx10Page();
        TransferPage transfer = trxPage.enterTransferPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "tranferOut QRCode", alwaysRun = true)
    public void test010_tranferOutQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrxPage();
        TransferPage transfer = trxPage.enterTransferPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Unfreeze transaction QRCode", alwaysRun = true)
    public void test011_UnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.currentType_btn.click();
        frozen.unfreezeType_btn.click();
        frozen.unfreezeTargetAddress_btn.click();
        frozen.unfreeze_btn.click();
        frozen.unfreezeInfoConfirm_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Withdraw reward transaction QRCode", alwaysRun = true)
    public void test012_WithdrawRewardQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reward_btn.click();
        vote.btgo_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }









}
