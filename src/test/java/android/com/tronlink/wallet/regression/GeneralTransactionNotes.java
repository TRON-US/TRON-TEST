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

import java.util.concurrent.TimeUnit;

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
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrxWithNote(Double.toString(sendAmount),"GeneralTransactionNotes");
        TimeUnit.SECONDS.sleep(2);

        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRX 转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
                Assert.assertEquals(detailAmount,sendAmount,0.5);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2022"));
                Assert.assertTrue(detail.tv_note.getText().contains("GeneralTransactionNotes"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }
//    @Test(enabled = true, description = "sendTrx10WithNoteSuccess", alwaysRun = true)
//    public void test002_sendTrx10WithNoteSuccess() throws Exception {
//        SendTrxPage transfer = enterToSendTrxPage();
//        transfer.selectCoinType("10");
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
//        transfer.selectCoinType("20");
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

     @Test(alwaysRun = true)
     public void test004_noteClearFunctionTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         TrxPage page =  asset.enterTrxPage();
         Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
         System.out.println("beforeSendBalance-----"+ beforeValue);
         SendTrxPage transfer = page.trxSendTrxPage();
         Double sendAmount = getAnAmount();
         System.out.println("sendTrxAmount-----"+ sendAmount);
         transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
         transfer.next_btn.click();
         transfer.tranferCount_text.sendKeys("1");
         transfer.iv_add_note.click();
         transfer.et_note.sendKeys("test004_noteClearFunctionTest");
         transfer.bt_note_remove.click();
         Assert.assertTrue(transfer.title.getText().contains("确认清除输入吗？"));
         transfer.confirm.click();
         Assert.assertEquals(transfer.et_note.getText(),"请输入备注，不超过 200 个字符");

     }

    @Test(enabled = true, description = "test004_testInputMaxLength", alwaysRun = true)
    public void test005_testInputMaxLength() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("1");
        transfer.iv_add_note.click();
        transfer.et_note.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890LongMax");
        Assert.assertEquals(transfer.et_note.getText(),"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

    }


//    @Parameters({"address"})
//    @Test(enabled = true, description = "Trx transaction detail 10 info note test", alwaysRun = true)
//    public void test007_trx10TransactionDetailInfo(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(1);
//        String recorderNumber = transactionInfo.tv_amount.getText().split(" ")[0];
//        String recorderNotes = transactionInfo.tv_note.getText();
//        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
//        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
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
//        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),"TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
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
