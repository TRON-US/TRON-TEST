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

    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe", DRIVER);
    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
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
        TimeUnit.SECONDS.sleep(2);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        sendTrxPage.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("1");
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }


    @Test(enabled = true,description = "Frozen Energy QRCode", alwaysRun = true)
    public void test002_frozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        TimeUnit.SECONDS.sleep(2);
        frozen.frozenTheEnergy(); //Freeze operating
        TimeUnit.SECONDS.sleep(5);
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        TimeUnit.SECONDS.sleep(8);
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
        TimeUnit.SECONDS.sleep(3);
        createProposePage.btn_confirm.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(alwaysRun = true)
    public void test005_NotActivedShow() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        sendTrxPage.receiveAddress_text.sendKeys("TV7xNv1n8or37qrSqCoPyqo26QRTxMzYUi");
        Assert.assertTrue(sendTrxPage.error_view.getText().contains("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
    }



    @Test(enabled = true,description = "Send trc10 QRCode", alwaysRun = true)
    public void test007_trc10QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(5);
        SendTrxPage sendTrxPage  = asset.enterSendTrc10Page();
        sendTrxPage.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("1");
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }


    @Test(enabled = true,description = "vote QRCode", alwaysRun = true)
    public void test008_voteQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("china");
        vote.enterSRPage();
        if (isElementShotId("btn_vote")){
            vote.enterVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            Assert.assertTrue(vote.btn_confirm.getText().contains("生成交易二维码"));
            vote.btn_confirm.click();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());

        }else if(isElementShotId("btn_voted_update")){
            vote.enterEditVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            Assert.assertTrue(vote.btn_confirm.getText().contains("生成交易二维码"));
            vote.btn_confirm.click();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
        }
    }


    @Test(enabled = true,description = "Send trc20 QRCode", alwaysRun = true)
    public void test009_trc20QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrc20Page();
        sendTrxPage.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        sendTrxPage.next_btn.click();
        sendTrxPage.tranferCount_text.sendKeys("5");
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        sendTrxPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(enabled = true,description = "Unfreeze transaction QRCode", alwaysRun = true)
    public void test011_UnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.toUnfreezePage();
        frozen.ll_container.click();
        TimeUnit.SECONDS.sleep(2);
        frozen.btn_next.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(frozen.btn_confirm.getText().contains("生成交易二维码"));
        frozen.btn_confirm.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(enabled = true,description = "Withdraw reward transaction QRCode", alwaysRun = true)
    public void test012_WithdrawRewardQRCode() throws Exception{

        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        if (isElementShotId("tv_profit")){
            Double ward = 0.0;
            if (vote.tv_profit.getText().contains("<0.001")){
                ward = 0.001;
            }else {
                ward = sepLeftNumberTextToDouble(vote.tv_profit.getText(),"TRX");
            }
            if (ward > 0){
                vote.enterGetReword();
                if (isElementShotId("tv_confirm_title")){
                    TimeUnit.SECONDS.sleep(2);
                    Assert.assertEquals(vote.tv_confirm_title.getText(),"确认交易");
                    Assert.assertEquals(vote.tv_info_title.getText(),"领取收益");
                    Assert.assertTrue(vote.tv_right.getText().contains("当前账户"));
                    Assert.assertTrue(vote.btn_confirm.getText().contains("生成交易二维码"));
                    vote.confirm_btn.click();
                    TimeUnit.SECONDS.sleep(2);
                    Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());

                }else {
                    log("未到24小时场景");
                }

            }else {
                log("待领取收益数值 0 无法领取");
            }
        }else {
            log("无待领取收益该处隐藏");

        }

    }



}
