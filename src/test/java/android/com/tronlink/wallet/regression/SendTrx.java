package android.com.tronlink.wallet.regression;


import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SendTrxPage;

import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransactionRecordPage;
import android.com.wallet.pages.TrxPage;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 转帐功能测试
 */
public class SendTrx extends Base {
    static String receiverAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.receiverAddress");
    static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentMainNetBlockNum");
    static String TRXandTRC10InNileprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveTRXandTRC10InNile.privateKey1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");
    Random rand = new Random();
    float sendTrxAmount;
    int beforeSendBalance;
    int afterSendBalance;



    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.harmony");
        }catch (Exception e){}
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
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

    @Test(groups = {"P0"},enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test001_sendTrxSuccess() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        beforeSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText()));
        System.out.println("beforeSendBalance-----"+beforeSendBalance);
        sendTrxAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+sendTrxAmount);
        transfer.sendTrx(Float.toString(sendTrxAmount));
    }



    @Test(enabled = true, description = "input Privatekey to Receiving address", alwaysRun = true)
    public void test002_inputPrivatekey() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        log("hits: " + hits);
        Assert.assertTrue(hits.equals("钱包地址格式不正确") || hits.equals("Wrong format"));
    }


    @Test(enabled = true, description = "input error address to Receiving address", alwaysRun = true)
    public void test003_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        log("hits: " + hits);
        Assert.assertTrue(hits.equals("钱包地址格式不正确") || hits.equals("Wrong format"));
    }

//开发
    @Test(enabled = true, description = "input not active Receiving address", alwaysRun = true)
    public void test004_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        String hits = transfer.note_text.getText();
        log("hits: " + hits);
        Assert.assertTrue(hits.contains("账户未激活") || hits.contains("Address not activated"));
    }


    @Parameters({"address"})
    @Test(enabled = true, description = "input Receiving address same as send address", alwaysRun = true)
    public void test005_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, address);
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        log("hits: " + hits);
        Assert.assertTrue(hits.equals("转出账户和接收账户不能相同")  || hits.contains("cannot be the same"));
    }


    @Test(enabled = true, description = "input Null Receiving address", alwaysRun = true)
    public void test006_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        Assert.assertFalse(transfer.send_btn.isEnabled()); //send btn can click
    }


    @Test(enabled = true, description = "input max send number", alwaysRun = true)
    public void test007_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }

    //使用一个没有足够冻结带宽的账户,点击转账会出现激活消耗的0.1trx
    @Test(enabled = true,description = "test008_inputNotEnoughBandWidthSendMaxNumberUNActive")
    public void test008_inputNotEnoughBandWidthSendMaxNumberUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(TRXandTRC10InNileprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        String allnumber = removeSymbol(transfer.sendMaxCoinWithType());
        System.out.println("allnumber : " + allnumber);
        String comfirmnumber = removeSymbol(StringUtils.substringBeforeLast(transfer.real_money.getText(),"TRX").trim());
        System.out.println("comfirmnumber : " + comfirmnumber);
        Assert.assertEquals(allnumber, comfirmnumber);
        Assert.assertEquals(sepLeftNumberTextToString(transfer.fee_text.getText(),"TRX"),"1.1");
        Assert.assertFalse(transfer.isElementExist("wallet.tronlink.harmony:id/tv_no_bandwidth"));


    }

    //max 未激活的显示
    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
    public void test009_inputHaveBandWidthSendMaxNumberToUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(haveBandwidthprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        Float allNumber =  Float.parseFloat(removeSymbolFloat(transfer.sendMaxCoinWithType()));
        Float number = sepLeftNumberTextToFloat(transfer.real_money.getText(), "TRX");
        Assert.assertTrue(sepLeftNumberTextToFloat(transfer.fee_text.getText(), "TRX") == 1);
        Assert.assertEquals(allNumber, number);
        Assert.assertFalse(transfer.isElementExist("wallet.tronlink.harmony:id/tv_no_bandwidth"));


    }

    @Parameters({"privateKey"})
    @Test(enabled = true, description = "input mix send number", alwaysRun = true)
    public void test010_inputMixSendNumber(String privateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(privateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(enabled = true, description = "input too Much TRX send number", alwaysRun = true)
    public void test011_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }


    @Test(enabled = true, description = "password error", alwaysRun = true)
    public void test012_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        transfer.transferNow_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isEnabled());
    }


    @Test(enabled = true, description = "Receiving address trim", alwaysRun = true)
    public void test013_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(enabled = true, description = "Receiving Minimum Trx", alwaysRun = true)
    public void test014_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(enabled = true, description = "check trx name", alwaysRun = true)
    public void test015_sendTrxCheckName() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        Assert.assertTrue(transfer.tvName_text.getText().contains("TRX"));
    }



    @Test(groups = {"P0"},enabled = true,description = "Trx transfer success recording")
    public void test017_transferInSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trx = asset.enterTrxPage();
        trx.tranfer_tab.get(1).click();
        String tranfercount = trx.tranferIncount_text.get(1).getText().replace("-","");
        System.out.println("tranferCount: " + tranfercount);
        System.out.println("sendTrxAmount: " + sendTrxAmount);
        Assert.assertTrue(Float.toString(sendTrxAmount).equals(tranfercount));

    }


    @Test(enabled = true, description = "test015_BandWidthShowTest", alwaysRun = true)
    public void test018_BandWidthShowTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        waiteTime();
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }


    @Test(groups = {"P0"},enabled = true,description = "Trx transfer balance decrease check")
    public void test019_balanceReduceAfterSendCoin() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        afterSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText()));
        System.out.println("beforeSendBalance:" + beforeSendBalance);
        System.out.println("afterSendBalance:" + afterSendBalance);
        Assert.assertTrue(beforeSendBalance - afterSendBalance >= 1);
    }

    @Test(groups = {"P0"},enabled = true, description = "Trx transfer history record test", alwaysRun = true)
    public void test020_transactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionTypeList.get(0).getText();
        System.out.println(transactionType);
        Assert.assertTrue( transactionType.equals("TRX 转账") || transactionType.equals("Send Trx"));
    }

    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true, description = "Trx transaction detail info test", alwaysRun = true)
    public void test021_trxTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(0);
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
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);

    }


    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true, description = "Trx receive transaction detail info test", alwaysRun = true)
    public void test022_trxReceiveTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterReceiverTransactionDetailPage(0);
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageReceiveAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
        String receiveIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
        Assert.assertTrue(receiveIcon.contains("+"));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains("TRX"));
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Float.valueOf(removeSymbol(detailPageReceiveAmount)) > 0);

        transactionInfo.swipScreenLitte();
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
                > Long.valueOf(currentMainNetBlockNum));
        System.out.println("transaction_time_text:" + transactionInfo.transaction_time_text.getText());
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        transactionInfo.swipScreenLitte();
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) >= 0);
    }







}
