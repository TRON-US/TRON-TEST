package android.com.tronlink.wallet.regression;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;


import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GeneralTransactionNotes extends Base {

     String beforeTRXBalance ;
     Double sendTrxAmount;
     String beforeTRC10Balance ;
     float sendTrc10Amount;
     String beforeTRC20Balance ;
     float sendTrc20Amount;

    static String trxnote = "I'm send TRX notes!!" ;
    static String trc10note = "I'm send TRC10 notes!!" ;
    static String trc20note = "I'm send TRC20 notes!!" ;

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


    public SendTrxPage enterToSendTrxPage() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }

    @Test(enabled = true, description = "sendTrxWithNoteSuccess", alwaysRun = true)
    public void test001_sendTrxWithNoteSuccess() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        beforeTRXBalance = removeSymbolFloat(transfer.balance_text.getText());
        System.out.println("beforeTRXBalance-----"+beforeTRXBalance);
        sendTrxAmount = getAnAmount();
        System.out.println("SendTRXBalance-----"+sendTrxAmount);
        transfer.sendTrxTypeWithNotes(Double.toString(sendTrxAmount),trxnote,"trx");
    }
//    @Test(enabled = true, description = "sendTrx10WithNoteSuccess", alwaysRun = true)
//    public void test002_sendTrx10WithNoteSuccess() throws Exception {
//        SendTrxPage transfer = enterToSendTrxPage();
//        transfer.selectCoinType("trc10");
//        beforeTRC10Balance = removeSymbolFloat(transfer.balance_text.getText());
//        System.out.println("beforeTRC10Balance-----"+beforeTRC10Balance);
//        sendTrc10Amount = getAnAmount();
//        System.out.println("SendTRC10Balance-----"+sendTrc10Amount);
//        transfer.sendTrxTypeWithNotes(Float.toString(sendTrc10Amount),trc10note,"trc10");
//    }
//
//    @Test(enabled = true, description = "sendTrx20WithNoteSuccess", alwaysRun = true)
//    public void test003_sendTrx20WithNoteSuccess() throws Exception {
//        SendTrxPage transfer = enterToSendTrxPage();
//        transfer.selectCoinType("trc20");
//        beforeTRC20Balance = removeSymbolFloat(transfer.balance_text.getText());
//        System.out.println("beforeTRC20Balance-----"+beforeTRC20Balance);
//        sendTrc20Amount = getAnAmount();
//        System.out.println("SendTRC20Balance-----"+sendTrc20Amount);
//        transfer.sendTrxTypeWithNotes(Float.toString(sendTrc20Amount),trc20note,"trc20");
//    }
//
//    @Test(enabled = true, description = "test004_testDeleteNote", alwaysRun = true)
//    public void test004_testDeleteNote() throws Exception {
//        SendTrxPage transfer = enterToSendTrxPage();
//        transfer.swip();
//        transfer.swip();
//        waiteTime();
//        transfer.add_note.click();
//        waiteTime();
//        transfer.et_note.sendKeys("I will be delete");
//        waiteTime();
//        Assert.assertEquals(transfer.et_note.getText(),"I will be delete");
//        waiteTime();
//        transfer.tv_delete.click();
//        Assert.assertEquals(transfer.et_note.getText(),"请输入备注，不超过200字");
//    }

    @Test(enabled = true, description = "test004_testInputMaxLength", alwaysRun = true)
    public void test005_testInputMaxLength() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.swip();
        transfer.swip();
        waiteTime();
        transfer.add_note.click();
        waiteTime();
        transfer.et_note.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890LongMax");
        waiteTime();
        Assert.assertEquals(transfer.et_note.getText(),"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

    }

    @Parameters({"address"})
    @Test(enabled = true, description = "Trx transaction detail info note test", alwaysRun = true)
    public void test006_trxTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(0);
        String recorderNumber = transactionInfo.tv_amount.getText().split(" ")[0];
        String recorderNotes = transactionInfo.tv_note.getText();
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),"TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        String detailPageSendAmount = recorderNumber.substring(1);
        String sendIcon = recorderNumber.substring(0,1);
        System.out.println("recorderNumber: " + recorderNumber + " sendIcon: " + sendIcon  + " detailPageSendAmount: " + detailPageSendAmount);
        Assert.assertTrue(sendIcon.equals("-"));
        Assert.assertEquals(detailPageSendAmount,String.valueOf(sendTrxAmount));
        Assert.assertEquals(recorderNotes,trxnote);

    }
//    @Parameters({"address"})
//    @Test(enabled = true, description = "Trx transaction detail 10 info note test", alwaysRun = true)
//    public void test007_trx10TransactionDetailInfo(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(1);
//        String recorderNumber = transactionInfo.tv_amount.getText().split(" ")[0];
//        String recorderNotes = transactionInfo.tv_note.getText();
//        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
//        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),"TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
//        Helper.swipScreen(transactionInfo.driver);
//        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
//        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
//        String detailPageSendAmount = recorderNumber.substring(1);
//        String sendIcon = recorderNumber.substring(0,1);
//        System.out.println("recorderNumber: " + recorderNumber + " sendIcon: " + sendIcon  + " detailPageSendAmount: " + detailPageSendAmount);
//        Assert.assertTrue(sendIcon.equals("-"));
//        Assert.assertEquals(detailPageSendAmount,String.valueOf(sendTrc10Amount));
//        Assert.assertEquals(recorderNotes,trc10note);
//
//    }
//    //需要适当的调整UI位置来进行识别
//    @Parameters({"address"})
//    @Test(enabled = true, description = "Trx transaction detail 20 info note test", alwaysRun = true)
//    public void test008_trx20TransactionDetailInfo(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(2);
//        String recorderNumber = transactionInfo.tv_amount.getText().split(" ")[0];
//        String recorderNotes = transactionInfo.tv_note.getText();
//
//        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
//        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),"TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
//        Helper.swipScreen(transactionInfo.driver);
//        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
//        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
//        String detailPageSendAmount = recorderNumber.substring(1);
//        String sendIcon = recorderNumber.substring(0,1);
//        System.out.println("recorderNumber: " + recorderNumber + " sendIcon: " + sendIcon  + " detailPageSendAmount: " + detailPageSendAmount);
//        Assert.assertTrue(sendIcon.equals("-"));
//        Assert.assertEquals(detailPageSendAmount,String.valueOf(sendTrc20Amount));
//        Assert.assertEquals(recorderNotes,trc20note);
//
//    }

}
