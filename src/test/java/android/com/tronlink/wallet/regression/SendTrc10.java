package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendTrc10 extends Base {
    Random rand = new Random();
    float sendTrxAmount;
    int beforeSendBalance;
    int afterSendBalance;
    static String receiverAddress = Configuration.getByPath("testng.conf")
            .getString("foundationAccount.receiverAddress");
    static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
            .getString("foundationAccount.currentMainNetBlockNum");
    static String trc10TokenName = Configuration.getByPath("testng.conf")
            .getString("foundationAccount.trc10TokenName");
    static String TRXandTRC10InNileprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveTRXandTRC10InNile.privateKey1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        } catch (Exception e) {
        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    public SendTrxPage enterToSendTrxPage() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


    @Test(groups = {"P0"}, enabled = true, description = "SendTrc10 success test", alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        Assert.assertTrue(transfer.tvName_text.getText().contains("tronlink_token"));
        beforeSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText()));
        sendTrxAmount = getAnAmount();
        transfer.sendTrc10(Float.toString(sendTrxAmount));
    }

    @Test(enabled = true, description = "input max send number", alwaysRun = true)
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("max");
        Assert.assertTrue(transfer.transferNow_btn.isEnabled());
    }

    //使用一个没有足够冻结能量的账户,点击转账会出现激活消耗的0.1trx
    @Test(enabled = true,description = "test003_inputNotEnoughBandWidthSendMax20NumberUNActive")
    public void test003_inputNotEnoughBandWidthSendMax10NumberUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(TRXandTRC10InNileprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        String allNumber  =   removeSymbolFloat(transfer.sendMaxCoinWithType("10"));
        String number = removeSymbolFloat(StringUtils.substringBeforeLast(transfer.real_money.getText(),"tronlink_token").trim());
        Assert.assertEquals(sepLeftNumberTextToString(transfer.fee_text.getText(),"TRX"),"1.1");
        log("beforeMax: " + allNumber + " confirmNumber: " + number);
        Assert.assertEquals(allNumber, number);
    }

    //max 未激活的显示
    @Test(enabled = true, description = "test004_inputHaveBandWidthSendMax10NumberToUNActive")
    public void test004_inputHaveBandWidthSendMax10NumberToUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(haveBandwidthprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        Float allNumber =    Float.parseFloat(removeSymbolFloat(transfer.sendMaxCoinWithType("10")));
        String number = removeSymbol(StringUtils.substringBeforeLast(transfer.real_money.getText(),"tronlink_token").trim());
        log("fee: " + transfer.fee_text.getText());
        Assert.assertTrue(sepLeftNumberTextToFloat(transfer.fee_text.getText(), "TRX") == 1);
        Assert.assertEquals(String.format("%.1f",allNumber), String.format("%.1f",Float.valueOf(number)));
    }

    @Parameters({"privateKey"})
    @Test(enabled = true, description = "input mix send number", alwaysRun = true)
    public void test005_inputMixSendNumber(String privateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(privateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.contains("转账金额需大于 0") || centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(enabled = true, description = "input too Much trc10 send number", alwaysRun = true)
    public void test006_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }


    @Test(groups = {"P0"}, enabled = true, description = "Trc10 transfer success recording")
    public void test008_trc10TransferInSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trx = asset.enterTrx10Page();
        trx.tranfer_tab.get(1).click();
        String tranfercount = trx.tranferIncount_text.get(1).getText().substring(1);
        System.out.println("tranferCount: " + tranfercount);
        System.out.println("sendTrxAmount: " + sendTrxAmount);
        Assert.assertTrue(Float.toString(sendTrxAmount).equals(tranfercount));

    }

    @Test(enabled = true, description = "Trc10 transfer balance decrease check")
    public void test009_trc10BalanceReduceAfterSendCoin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        afterSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText()));
        System.out.println("beforeSendBalance:" + beforeSendBalance);
        System.out.println("afterSendBalance:" + afterSendBalance);
        Assert.assertTrue(beforeSendBalance - afterSendBalance >= 1);
    }

    @Test(enabled = true, description = "test015_BandWidthShowTest", alwaysRun = true)
    public void test010_BandWidthShowTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        waiteTime();
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content, "带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Test(enabled = true, description = "TRC10 transfer history record test", alwaysRun = true)
    public void test011_transactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionTypeList.get(0).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.contains("TRC10 通证转账") || transactionType.contains("TRC10") || transactionType.equals("transfer TRC10 token"));
    }

    @Parameters({"address"})
    @Test(enabled = true, description = "Trc10 transaction detail info test", alwaysRun = true)
    public void test012_trc10TransactionDetailInfo(String address) throws Exception {


        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(1);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
        String sendIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
        Helper.swipScreenLitte(asset.driver);
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("2021"));
        Assert.assertTrue(sendIcon.contains("-"));
        Assert.assertEquals(detailPageSendAmount.substring(1),String.valueOf(sendTrxAmount));
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
                > Long.valueOf(currentMainNetBlockNum));
        Helper.swipScreenLitte(asset.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);

    }


    @Parameters({"address"})
    @Test(enabled = true, description = "Trc10 receive transaction detail info test", alwaysRun = true)
    public void test013_trc10ReceiveTransactionDetailInfo(String address) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterReceiverTransactionDetailPage(1);
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageReceiveAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
        String receiveIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
        Assert.assertTrue(receiveIcon.contains("+"));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(""));
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Float.valueOf(removeSymbolFloat(detailPageReceiveAmount)) > 0);

        Helper.swipScreenLitte(transactionInfo.driver);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
                > Long.valueOf(currentMainNetBlockNum));
        System.out.println("transaction_time_text:" + transactionInfo.transaction_time_text.getText());
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) >= 0);
    }


}
