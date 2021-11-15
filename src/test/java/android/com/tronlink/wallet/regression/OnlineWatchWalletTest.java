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

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OnlineWatchWalletTest extends Base {

    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");

    static  String unactiveAddr = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
    static  String activeAddr = "TGPhR5Kaiirvctv4PhiVQL8bbXmVL4XfB5";

    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TL5oxDYUztR3bjMqChLVZKdR4dXAiJYUyo", DRIVER);
        enterOnlineAssetPage();

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




    @Test(groups = {"P0"},enabled = true,description = "Online watch wallet sendTrx QRCode", alwaysRun = true)
    public void test001_onlineSendTrxQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Assert.assertTrue(sendTrxPage.note_text.getText().contains("账户未激活")&&sendTrxPage.note_text.getText().contains("1.1"));
        Helper.swipScreenLitte(sendTrxPage.driver);
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(groups = {"P0"},enabled = true,description = "onlineHaveActiveTrx", alwaysRun = true)
    public void test002_onlineHaveActiveTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        sendTrxPage.receiveAddress_text.sendKeys("TGPhR5Kaiirvctv4PhiVQL8bbXmVL4XfB5");
//        Helper.tapScreen(sendTrxPage.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(sendTrxPage.tv_add_address.getText().contains("加入到地址本"));
    }
    @Test(groups = {"P0"},enabled = true,description = "onlineHaveActiveTrx", alwaysRun = true)
    public void test003_onlineHaveActiveTrx10() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc10Page();
        sendTrxPage.receiveAddress_text.sendKeys("TGPhR5Kaiirvctv4PhiVQL8bbXmVL4XfB5");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Assert.assertTrue(sendTrxPage.tv_add_address.getText().contains("加入到地址本"));
    }

    @Test(groups = {"P0"},enabled = true,description = "onlineHaveActiveTrx", alwaysRun = true)
    public void test004_onlineHaveActiveTrx20() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc20Page();
        sendTrxPage.receiveAddress_text.sendKeys("TGPhR5Kaiirvctv4PhiVQL8bbXmVL4XfB5");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Assert.assertFalse(sendTrxPage.isElementExist("com.tronlinkpro.wallet:id/tv_note"));
    }

    @Test(groups = {"P0"},enabled = true,description = "onlineMultiSignatureFeeCheck", alwaysRun = true)
    public void test005_onlineMultiSignatureFeeCheck() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        waiteTime();
        TimeUnit.SECONDS.sleep(3);
        sendTrxPage.transferAddress_deleteBtn.click();
        waiteTime();
        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
        waiteTime();
        sendTrxPage.receiveAddress_text.sendKeys(activeAddr);
        Helper.swipScreenLitte(sendTrxPage.driver);
        sendTrxPage.tranferCount_text.sendKeys("1");
        waiteTime();
        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
        Assert.assertEquals(removeSymbol(feeSendNumber.trim()),"1");
        waiteTime();
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"TRX");
        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
        Assert.assertTrue(Integer.parseInt(feeNumber.trim()) == 1);
        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
        Assert.assertEquals(sendTrxPage.to_address.getText(),activeAddr);
    }
    @Test(groups = {"P0"},enabled = true,description = "test006_onlineMultiSignatureUnActiveFeeCheck", alwaysRun = true)
    public void test006_onlineMultiSignatureUnActiveFeeCheck() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
        waiteTime();
        TimeUnit.SECONDS.sleep(3);
        sendTrxPage.transferAddress_deleteBtn.click();
        waiteTime();
        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
        waiteTime();
        sendTrxPage.receiveAddress_text.sendKeys(unactiveAddr);
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreenLitte(sendTrxPage.driver);
        sendTrxPage.tranferCount_text.sendKeys("0.1");
        Helper.swipScreen(DRIVER);
        waiteTime();
        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
        Assert.assertEquals(feeSendNumber.trim(),"2.1");
        waiteTime();
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"TRX");
        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
        log(realNumber.trim());
        Assert.assertTrue(realNumber.trim().equalsIgnoreCase("0.1"));
        Assert.assertEquals(feeNumber.trim(),"2.1");
        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
        Assert.assertEquals(sendTrxPage.to_address.getText(),unactiveAddr);

    }

//    @Test(groups = {"P0"},enabled = true,description = "onlineMultiSignatureTrc10FeeCheck", alwaysRun = true)
//    public void test007_onlineMultiSignatureTrc10FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(activeAddr);
//        waiteTime();
//        sendTrxPage.token_btn.click();
//        waiteTime();
//        sendTrxPage.driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BTT\")").click();
//        waiteTime();
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        Helper.swipScreen(DRIVER);
//        waiteTime();
//        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        Assert.assertEquals(feeSendNumber.trim(),"1.0");
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"BTT");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(feeNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),activeAddr);
//    }

//    @Test(groups = {"P0"},enabled = true,description = "test006_onlineMultiSignatureUnActiveTrc10FeeCheck", alwaysRun = true)
//    public void test008_onlineMultiSignatureUnActiveTrc10FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(unactiveAddr);
//        waiteTime();
//        sendTrxPage.token_btn.click();
//        waiteTime();
//        sendTrxPage.driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BTT\")").click();
//        TimeUnit.SECONDS.sleep(1);
//        waiteTime();
//        Assert.assertTrue(sendTrxPage.note_text.getText().contains("0.1")&&sendTrxPage.note_text.getText().contains("未激活"));
//        waiteTime();
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        Helper.swipScreen(DRIVER);
//        waiteTime();
//        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        Assert.assertEquals(feeSendNumber.trim(),"1.1");
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"BTT");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
//        Assert.assertEquals(feeNumber.trim(),"1.1");
//        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),unactiveAddr);
//
//    }

//    @Test(groups = {"P0"},enabled = true,description = "onlineMultiSignatureTrc20FeeCheck", alwaysRun = true)
//    public void test009_onlineMultiSignatureTrc20FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(activeAddr);
//        waiteTime();
//        sendTrxPage.token_btn.click();
//        waiteTime();
//        sendTrxPage.driver.findElementByAndroidUIAutomator("new UiSelector().text(\"USDJ\")").click();
//        waiteTime();
//        sendTrxPage.tranferCount_text.sendKeys("0.00001");
//        Helper.swipScreen(DRIVER);
//        waiteTime();
//        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        Assert.assertEquals(feeSendNumber.trim(),"1.0");
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"USDJ");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        Assert.assertEquals(realNumber.trim(),"0.00001");
//        Assert.assertTrue(Integer.parseInt(feeNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),activeAddr);
//        Assert.assertTrue(sendTrxPage.no_bandwidth.getText().contains("执行智能合约")&&sendTrxPage.no_bandwidth.getText().contains("燃烧")&&sendTrxPage.no_bandwidth.getText().contains("TRX"));
//
//    }

//    @Test(groups = {"P0"},enabled = true,description = "test006_onlineMultiSignatureUnActiveTrc20FeeCheck", alwaysRun = true)
//    public void test010_onlineMultiSignatureUnActiveTrc20FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(unactiveAddr);
//        waiteTime();
//        sendTrxPage.token_btn.click();
//        waiteTime();
//        sendTrxPage.driver.findElementByAndroidUIAutomator("new UiSelector().text(\"USDJ\")").click();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(sendTrxPage.note_text.getText().contains("不会激活该账户")&&sendTrxPage.note_text.getText().contains("账户未激活"));
//        waiteTime();
//        sendTrxPage.tranferCount_text.sendKeys("0.00001");
//        Helper.swipScreen(DRIVER);
//        waiteTime();
//        String feeSendNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        Assert.assertEquals(feeSendNumber.trim(),"1.0");
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"USDJ");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        Assert.assertEquals(realNumber.trim(),"0.00001");
//        Assert.assertTrue(Integer.parseInt(feeNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),unactiveAddr);
//        Assert.assertTrue(sendTrxPage.no_bandwidth.getText().contains("执行智能合约")&&sendTrxPage.no_bandwidth.getText().contains("燃烧")&&sendTrxPage.no_bandwidth.getText().contains("TRX"));
//
//    }


    @Test(groups = {"P0"},enabled = true,description = "Online frozen Energy QRCode", alwaysRun = true)
    public void test011_onlineFrozenEnergyQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        TimeUnit.SECONDS.sleep(5);
        frozen.energy_btn.click();
        Helper.swipScreenLitte(frozen.driver);
        frozen.freezeCount_input.sendKeys("1");
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(DRIVER);
        frozen.frozenButtonClickAndConfirm();
        frozen.confirm_btn().click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




    @Test(groups = {"P0"},enabled = true,description = "Online frozen Bandwidth QRCode", alwaysRun = true)
    public void test012_onlineFrozenBandwidthQRCode() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        TimeUnit.SECONDS.sleep(5);
        frozen.bandwidth_btn.click();
        Helper.swipScreenLitte(frozen.driver);
        frozen.freezeCount_input.sendKeys("1");
        Helper.swipScreen(DRIVER);
        frozen.frozenButtonClickAndConfirm();
        frozen.confirm_btn().click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

//    //线上账户缺钱，不足以支付100TRX
//    @Test(groups = {"P0"},enabled = true,description = "Online muliSignature QRCode", alwaysRun = true)
//    public void test013_onlineMuliSignatureQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        MyPursePage myPursePage = mine.enterMyPursePage();
//        MultiSignManagerPage multiSignManager = myPursePage.enterMultiSignManagerPage();
//        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
//        waiteTime();
//        add.inputInfo("AutoTest_0001");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }



    @Test(groups = {"P0"},enabled = true,description = "Online deposit QRCode", alwaysRun = true)
    public void test014_onlineDepositQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterBTTPage();
        TransferPage transfer = trxPage.enterTransferInPage();
        transfer.count_text.sendKeys("1");
        transfer.transferIn_btn.click();
        transfer.bt_send.click();
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }



    @Test(groups = {"P0"},enabled = true,description = "Online send trc10 QRCode", alwaysRun = true)
    public void test015_onlineTrc10QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc10Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Helper.swipScreenLitte(sendTrxPage.driver);
        sendTrxPage.tranferCount_text.sendKeys("1");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }




//    @Test(groups = {"P0"},enabled = true,description = "Online vote QRCode", alwaysRun = true)
//    public void test016_onlineVoteQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        vote.reset_btn.click();
//        vote.et_input.sendKeys("1");
//        vote.vote_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        try {
//            vote.btgo_btn.click();
//        } catch (Exception e) {
//
//        }
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }


//    @Test(groups = {"P0"},enabled = true,description = "Online vote TotalNumber", alwaysRun = true)
//    public void test017_onlineVoteQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        waiteTime();
//        long number = Long.parseLong(removeSymbol(vote.total_vote.getText())) ;
//        Assert.assertTrue(number > 0);
//
//    }

    @Test(groups = {"P0"},enabled = true,description = "Online trc20 QRCode", alwaysRun = true)
    public void test018_onlineTrc20QRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage  = asset.enterOnlineSendTrc20Page();
        sendTrxPage.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Helper.swipScreenLitte(sendTrxPage.driver);
        sendTrxPage.tranferCount_text.sendKeys("0.00001");
        Helper.swipScreen(DRIVER);
        sendTrxPage.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        sendTrxPage.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
    }

    @Test(groups = {"P0"},enabled = true,description = "Online unfreeze transaction QRCode", alwaysRun = true)
    public void test019_OnlineUnfreezeQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.currentType_btn.click();
        frozen.unfreezeType_btn.click();
        if(frozen.isElementExist("com.tronlinkpro.wallet:id/iv_unfreeze")){
            frozen.unfreezeTargetAddress_btn.click();
            Helper.swipScreen(frozen.driver);
            frozen.unfreeze_btn.click();
            frozen.unfreezeInfoConfirm_btn.click();
            Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
        }else {
            log("未到解锁时间!!");
        }

    }

//    @Test(groups = {"P0"},enabled = true,description = "Online Withdraw reward transaction QRCode", alwaysRun = true)
//    public void test020_OnlineWithdrawRewardQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        vote.reward_btn.click();
//        vote.btgo_btn.click();
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }

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
        TimeUnit.SECONDS.sleep(10);
        AssetPage asset = new AssetPage(DRIVER);
        return asset;
    }




}
