package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;

import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransactionRecordPage;
import android.com.wallet.pages.TrxPage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrxSuccessPage;

public class SendTrc20 extends Base {
    Random rand = new Random();
    float sendTrc20Amount;
    int beforeSendBalance;
    int afterSendBalance;
  static String receiverAddress = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.receiverAddress");
  static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.currentMainNetBlockNum");
  static String trc20TokenName = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.trc20TokenName");
  
    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        } catch (Exception e){

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

    @Test(enabled = true,description = "SendTrc20 success test")
    public void test001_sendTrc20Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        double trc20Before = transfer.getTrc20Amount();
        sendTrc20Amount = rand.nextFloat() + 1;
        String trc20SendAmount = Float.toString(sendTrc20Amount);
        SendTrxSuccessPage stsp = transfer.normalSendTrc20(trc20SendAmount);
        stsp.driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        transfer = asset.enterSendTrxPage();
        double trc20After = transfer.getTrc20Amount();
        System.out.println(trc20After);
        Double min = trc20After + Double.valueOf(trc20SendAmount) - trc20Before;
        Assert.assertTrue(min < 1 && min > -1);
    }

    @Test(enabled = true,description = "input max send number")
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(enabled = true,description = "input mix send number")
    public void test003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("mix");
        String centent = transfer.formatErrorHits_text.getText();
      Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(enabled = true,description = "input too Much trc20 send number", alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }

    @Test(enabled = true,description = "Trc20 transfer success recording")
    public void test005_trc20TransferInSuccessRecording() throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      TrxPage trx = asset.enterTrx20Page();
      trx.tranfer_tab.get(1).click();
      System.out.println(trx.tranferIncount_text.get(1).getText());
      String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
      Assert.assertTrue(Float.toString(sendTrc20Amount).substring(0,5).equals(tranferInCount.substring(0,5)));
   /*   int tries = 0;
      Boolean exist = false;
      while (exist == false && tries++ < 5) {
        tries++;
        try {
          AssetPage arret = trx.enterAssetPage();
          trx = arret.enterTrx20Page();
          trx.tranfer_tab.get(1).click();
          System.out.println(trx.tranferIncount_text.get(1).getText());
          String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
          System.out.println("tranferInCount = " + tranferInCount);
          System.out.println("sendTrxAmount = " + sendTrc20Amount);
          if (Float.toString(sendTrc20Amount).substring(0,5).equals(tranferInCount.substring(0,5))) {
            exist = true;
            break;
          }
        } catch (Exception e) {
          System.out.println(e);
        }
      }
      Assert.assertTrue(exist);*/
  }


    @Test(enabled = true, description = "TRC20 transfer history record test", alwaysRun = true)
    public void test006_trc20TransactionHistory() throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      MinePage mine = asset.enterMinePage();
      TransactionRecordPage transaction = mine.enterTransactionRecordPage();
      String transactionType = transaction.transactionTypeList.get(0).getText();
      System.out.println(transactionType);
      Assert.assertTrue(transactionType.equals("触发智能合约") || transactionType.equals("Trigger Smart Contract"));
  }

  @Parameters({"address"})
  @Test(enabled = true, description = "Trc 20 transaction detail info test", alwaysRun = true)
  public void test007_trc20TransactionDetailInfo(String address) throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(2);
    Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
    Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
    Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
    Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
    Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
    Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
    System.out.println(transactionInfo.title_amount_test.getText());
    System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
    String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
    Assert.assertEquals(detailPageSendAmount.substring(0,6),String.valueOf(sendTrc20Amount).substring(0,6));
    Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
        > Long.valueOf(currentMainNetBlockNum) );
  }

  @Parameters({"address"})
  @Test(enabled = true, description = "Trc20 receive transaction detail info test", alwaysRun = true)
  public void test008_trc20ReceiveTransactionDetailInfo(String address) throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    TransactionDetailInfomaitonPage transactionInfo = asset.enterReceiverTransactionDetailPage(1);
    System.out.println(transactionInfo.title_amount_test.getText());
    System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
    String detailPageReceiveAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
    String receiveIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
    Assert.assertTrue(receiveIcon.equals("+"));
    Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
    Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),address);
    Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
    Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
    Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
    Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    Assert.assertTrue(Float.valueOf(detailPageReceiveAmount) > 0);
    Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
        > Long.valueOf(currentMainNetBlockNum));
  }


}
