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

    static  String unactiveAddr = "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq";
    static  String activeAddr = "TGPhR5Kaiirvctv4PhiVQL8bbXmVL4XfB5";

    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getWatchWalletSign("TL5oxDYUztR3bjMqChLVZKdR4dXAiJYUyo", DRIVER);
        enterOnlineAssetPage();
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }



    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }




//    @Test(groups = {"P0"},enabled = true,description = "Online watch wallet sendTrx QRCode", alwaysRun = true)
//    public void test001_onlineSendTrxQRCode() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        sendTrxPage.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        Assert.assertTrue(sendTrxPage.error_view.getText().contains("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
//        sendTrxPage.next_btn.click();
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(6);
//        sendTrxPage.confirm_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//
//    }
//
//    @Test(groups = {"P0"},enabled = true,description = "onlineHaveActiveTrx", alwaysRun = true)
//    public void test002_onlineHaveActiveTrx() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        sendTrxPage.receiveAddress_text.sendKeys("TPyjyZfsYaXStgz2NmAraF1uZcMtkgNan5");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(sendTrxPage.tv_address_book.getText().contains("加入到地址本"));
//    }


//    @Test(groups = {"P0"},enabled = true,description = "Online frozen Energy QRCode", alwaysRun = true)
//    public void test003_onlineFrozenEnergyQRCode() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheEnergy(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.confirmTransferPage();
//        frozen.btn_confirm.click();
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }




//    @Test(groups = {"P0"},enabled = true,description = "Online frozen Bandwidth QRCode", alwaysRun = true)
//    public void test004_onlineFrozenBandwidthQRCode() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheBandwidth(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.confirmTransferPage();
//        frozen.btn_confirm.click();
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }
    



//    @Test(groups = {"P0"},enabled = true,description = "Online send trc10 QRCode", alwaysRun = true)
//    public void test006_onlineTrc10QRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        sendTrxPage.SendTokenWithNameAmountWatch("1","BTT");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }
//
//
//    @Test(groups = {"P0"},enabled = true,description = "Online trc20 QRCode", alwaysRun = true)
//    public void test007_onlineTrc20QRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        sendTrxPage.SendTokenWithNameAmountWatch("0.00001","WIN");
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertEquals(sendTrxPage.info.getText(),"继续交易会消耗账户剩余 TRX/资源，且存在交易失败的风险，请确认是否继续");
//        sendTrxPage.btn_confirm_2.click();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//    }


//    @Test(groups = {"P0"},enabled = true,description = "Online unfreeze transaction QRCode", alwaysRun = true)
//    public void test008_OnlineUnfreezeQRCode() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.toUnfreezePage();
//        frozen.ll_container.click();
//        frozen.btn_next.click();
//        TimeUnit.SECONDS.sleep(6);
//        Assert.assertTrue(frozen.btn_confirm.getText().contains("生成交易二维码"));
//        frozen.btn_confirm.click();
//        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
//
//    }


    {

//    @Test(groups = {"P0"},enabled = true,description = "onlineMultiSignatureFeeCheck", alwaysRun = true)
//    public void test005_onlineMultiSignatureFeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(activeAddr);
//        Helper.swipScreenLitte(sendTrxPage.driver);
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"TRX");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        realNumber = realNumber.replace("≈","");
//        feeNumber = feeNumber.replace("≈","");
//        bNumber = bNumber.replace("≈","");
//        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(feeNumber.trim()) == 1);
//        Assert.assertTrue(Integer.parseInt(bNumber.trim()) >= 0);
//
//    }


//    @Test(groups = {"P0"},enabled = true,description = "test006_onlineMultiSignatureUnActiveFeeCheck", alwaysRun = true)
//    public void test006_onlineMultiSignatureUnActiveFeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        waiteTime();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        waiteTime();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        waiteTime();
//        sendTrxPage.receiveAddress_text.sendKeys(unactiveAddr);
//        TimeUnit.SECONDS.sleep(1);
//        Helper.swipScreenLitte(sendTrxPage.driver);
//        sendTrxPage.tranferCount_text.sendKeys("0.1");
//        Helper.swipScreen(DRIVER);
//        waiteTime();
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"TRX");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        realNumber = realNumber.replace("≈","");
//        feeNumber = feeNumber.replace("≈","");
//        Assert.assertTrue(realNumber.trim().equalsIgnoreCase("0.1"));
//        Assert.assertEquals(feeNumber.trim(),"2.1");
//
//    }

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
//        sendTrxPage.selectTokenByName("BTTOLD");
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        Helper.swipScreen(DRIVER);
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"BTTOLD");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        String bNumber = StringUtils.substringBeforeLast(sendTrxPage.bandwidth_text.getText(),"带宽");
//        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
//        feeNumber = feeNumber.replace("≈","");
//        bNumber = bNumber.replace("≈","");
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
//        sendTrxPage.selectTokenByName("BTTOLD");
//        TimeUnit.SECONDS.sleep(1);
//        waiteTime();
//        Assert.assertTrue(sendTrxPage.note_text.getText().contains("1.1")&&sendTrxPage.note_text.getText().contains("激活"));
//        waiteTime();
//        sendTrxPage.tranferCount_text.sendKeys("1");
//        Helper.swipScreen(DRIVER);
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"BTTOLD");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        feeNumber = feeNumber.replace("≈","");
//        Assert.assertTrue(Integer.parseInt(realNumber.trim()) == 1);
//        Assert.assertEquals(feeNumber.trim(),"2.1");
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),unactiveAddr);
//
//    }

//    @Test(groups = {"P0"},enabled = true,description = "onlineMultiSignatureTrc20FeeCheck", alwaysRun = true)
//    public void test009_onlineMultiSignatureTrc20FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        sendTrxPage.receiveAddress_text.sendKeys(activeAddr);
//        sendTrxPage.selectTokenByName("JST");
//        sendTrxPage.tranferCount_text.sendKeys("0.00001");
//        Helper.swipScreen(DRIVER);
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"USDJ");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        realNumber = realNumber.replace("JST","");
//        Assert.assertEquals(realNumber.trim(),"0.00001");
//        feeNumber = feeNumber.replace("≈","");
//        Assert.assertTrue(Double.parseDouble(feeNumber.trim()) >= 1.0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),activeAddr);
//
//    }

//    @Test(groups = {"P0"},enabled = true,description = "test006_onlineMultiSignatureUnActiveTrc20FeeCheck", alwaysRun = true)
//    public void test010_onlineMultiSignatureUnActiveTrc20FeeCheck() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage sendTrxPage  = asset.enterSendTrxPage();
//        TimeUnit.SECONDS.sleep(3);
//        sendTrxPage.transferAddress_deleteBtn.click();
//        sendTrxPage.transferAddress_text.sendKeys("TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        sendTrxPage.receiveAddress_text.sendKeys(unactiveAddr);
//        sendTrxPage.selectTokenByName("JST");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(sendTrxPage.note_text.getText().contains("不会激活该账户")&&sendTrxPage.note_text.getText().contains("账户未激活"));
//        sendTrxPage.tranferCount_text.sendKeys("0.00001");
//        Helper.swipScreen(DRIVER);
//        sendTrxPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String realNumber = StringUtils.substringBeforeLast(sendTrxPage.real_money.getText(),"USDJ");
//        String feeNumber = StringUtils.substringBeforeLast(sendTrxPage.fee_text.getText(),"TRX");
//        realNumber = realNumber.replace("JST","");
//        Assert.assertEquals(realNumber.trim(),"0.00001");
//        feeNumber = feeNumber.replace("≈","");
//        Assert.assertTrue(Double.parseDouble(feeNumber.trim()) >= 1.0);
//        Assert.assertEquals(sendTrxPage.from_address.getText(),"TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A");
//        Assert.assertEquals(sendTrxPage.to_address.getText(),unactiveAddr);
//
//    }
    }




    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }



}
