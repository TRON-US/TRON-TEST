package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddPermissionPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.CommitteeProposalPage;
import android.com.wallet.pages.CreateProposePage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MultiSignManagerPage;
import android.com.wallet.pages.MyPursePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.QRodeEPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;
import android.com.wallet.pages.VotePage;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OnlineWatchWalletTest extends Base {

    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");



    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TRqgwhHbfscXq3Ym3FJSFwxprpto1S4nSW", DRIVER);
        enterOnlineAssetPage();

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




    @Test(groups = {"P0"},enabled = true,description = "Online watch wallet sendTrx QRCode", alwaysRun = true)
    public void test001_onlineSendTrxQRCode() throws Exception {
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




    @Test(groups = {"P0"},enabled = true,description = "Online frozen Energy QRCode", alwaysRun = true)
    public void test002_onlineFrozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        TimeUnit.SECONDS.sleep(3);
        Helper.swipScreen(DRIVER);
        frozen.energy_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(DRIVER);
        frozen.freeze_btn.click();
        TimeUnit.SECONDS.sleep(1);
        frozen.freezeNow_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(groups = {"P0"},enabled = true,description = "Online frozen Bandwidth QRCode", alwaysRun = true)
    public void test003_onlineFrozenBandwidthQRCode() throws Exception {
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


    @Test(groups = {"P0"},enabled = true,description = "Online muliSignature QRCode", alwaysRun = true)
    public void test004_onlineMuliSignatureQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage multiSignManager = myPursePage.enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest_0001");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(groups = {"P0"},enabled = true,description = "Online deposit QRCode", alwaysRun = true)
    public void test005_onlineDepositQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrxPage();
        TransferPage transfer = trxPage.enterTransferPage();
        transfer.count_text.sendKeys("10");
        transfer.transferIn_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(groups = {"P0"},enabled = true,description = "Online send trc10 QRCode", alwaysRun = true)
    public void test006_onlineTrc10QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc10Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(groups = {"P0"},enabled = true,description = "Online vote QRCode", alwaysRun = true)
    public void test007_onlineVoteQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        vote.et_input.sendKeys("1");
        vote.vote_btn.click();
        TimeUnit.SECONDS.sleep(2);
        try {
            vote.btgo_btn.click();
        } catch (Exception e) {

        }
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(groups = {"P0"},enabled = true,description = "Online trc20 QRCode", alwaysRun = true)
    public void test008_onlineTrc20QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc20Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(groups = {"P0"},enabled = true,description = "Online unfreeze transaction QRCode", alwaysRun = true)
    public void test009_OnlineUnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.currentType_btn.click();
        frozen.unfreezeType_btn.click();
        frozen.unfreezeTargetAddress_btn.click();
        frozen.unfreeze_btn.click();
        frozen.unfreezeInfoConfirm_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(groups = {"P0"},enabled = true,description = "Online Withdraw reward transaction QRCode", alwaysRun = true)
    public void test010_OnlineWithdrawRewardQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reward_btn.click();
        vote.btgo_btn.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter online page
    public AssetPage enterOnlineAssetPage() throws Exception {
        SettingPage set = enterSettingPage();
        set.version_btn.click();
        set.online_version_icon.click();

        try {
            TimeUnit.SECONDS.sleep(10);

        } catch (Exception e){}

        AssetPage asset = new AssetPage(DRIVER);
        return asset;
    }




}
