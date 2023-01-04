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
     Double sendTrc10Amount;
     String beforeTRC20Balance ;
     Double sendTrc20Amount;

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


    public SendTrxPage enterToSendTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }

    @Test(enabled = true, description = "sendTrxWithNoteSuccess", alwaysRun = true)
    public void test001_sendTrxWithNoteSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        TimeUnit.SECONDS.sleep(1);
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
                Assert.assertEquals(detailAmount,sendAmount,1.5);
                Helper.swipScreenLitte(DRIVER);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2023"));
                Assert.assertTrue(detail.tv_note.getText().contains("GeneralTransactionNotes"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }


     @Test(alwaysRun = true)
     public void test002_noteClearFunctionTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         TrxPage page =  asset.enterTrxPage();
         TimeUnit.SECONDS.sleep(1);
         Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
         System.out.println("beforeSendBalance-----"+ beforeValue);
         SendTrxPage transfer = page.trxSendTrxPage();
         Double sendAmount = getAnAmount();
         System.out.println("sendTrxAmount-----"+ sendAmount);
         transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
         transfer.next_btn.click();
         transfer.tranferCount_text.sendKeys("1");
         transfer.note_transfer.click();
         transfer.et_note.sendKeys("test004_noteClearFunctionTest");
         transfer.bt_note_remove.click();
         Assert.assertTrue(transfer.title.getText().contains("确认清除输入吗？"));
         transfer.confirm.click();
         Assert.assertEquals(transfer.et_note.getText(),"请输入备注，不超过 200 个字符");

     }

    @Test(enabled = true, description = "test004_testInputMaxLength", alwaysRun = true)
    public void test003_testInputMaxLength() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        TimeUnit.SECONDS.sleep(1);
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("1");
        transfer.note_transfer.click();
        transfer.et_note.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890LongMax");
        Assert.assertEquals(transfer.et_note.getText(),"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

    }



}
