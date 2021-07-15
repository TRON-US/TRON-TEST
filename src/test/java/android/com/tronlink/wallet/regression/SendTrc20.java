package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;

import android.com.wallet.pages.MinePage;
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
  static String onlyhaveTRC20privatekey = Configuration.getByPath("testng.conf")
            .getString("onlyHaveTRC20InNile.privateKey1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");
  
    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.harmony");
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

    @Test(groups = {"P0"},enabled = true,description = "SendTrc20 success test")
    public void test001_sendTrc20Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        double trc20Before = transfer.getTrc20Amount();
        sendTrc20Amount = getAnAmount();
        String trc20SendAmount = Float.toString(sendTrc20Amount);
        System.out.println("trc20SendAmount : " + trc20SendAmount);
        TrxPage trxpage = transfer.normalSendTrc20(trc20SendAmount);
        TimeUnit.SECONDS.sleep(5);
        Helper.swipeDownScreen(trxpage.driver);
        double afterdouble = Double.parseDouble(prettyString(trxpage.trxTotal_text.getText()));
        String rsoultString = "";
        for (int i =  0 ; i< 8 ;i++){
            afterdouble = Double.parseDouble(prettyString(trxpage.trxTotal_text.getText()));
            rsoultString =  String.format("%.6f" ,trc20Before - Double.parseDouble(trc20SendAmount));
            System.out.println("第"+i + "次\n " + " trc20Before: " + trc20Before  + " trc20SendAmount: " + trc20SendAmount +  " afterdouble: " + afterdouble + " result：" +  rsoultString);
            if (String.valueOf(afterdouble).equals(rsoultString)  ){
                log("equal!!!");
                break;
            }
            TimeUnit.SECONDS.sleep(5);
            Helper.swipeDownScreen(trxpage.driver);

        }
        Assert.assertTrue(String.valueOf(afterdouble).equals(rsoultString) );

    }
//
    @Test(enabled = true,description = "input max send number")
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }

    //使用一个没有trx但是又20币的账户转账20,会出现消耗trx的确认信息,点击转账后会失败
    @Test(enabled = true,description = "test003_inputNotEnoughBandWidthSendMax20NumberUNActive")
    public void test003_inputNotEnoughBandWidthSendMax20NumberUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(onlyhaveTRC20privatekey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendMaxCoinWithType("20");
        Assert.assertFalse(transfer.send_btn.isEnabled());
        Helper.swipeDownScreen(transfer.driver);
        Assert.assertTrue(transfer.formatErrorHits_text.getText().contains("账户未激活") );
        Assert.assertTrue(transfer.note_text.getText().contains("账户未激活，可正常转账 TRC20 通证，但不会激活该账户。"));

    }
    //使用一个带宽充足账户转给未激活地址20,手续费trx是0,不转账!!
    @Test(enabled = true,description = "test004_inputHaveBandWidthSendMax20NumberToUNActive")
    public void test004_inputHaveBandWidthSendMax20NumberToUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(haveBandwidthprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        Float allNumber =   Float.parseFloat(removeSymbolFloat(transfer.sendMaxCoinWithType("20")));
        Float number =  sepLeftNumberTextToFloat(transfer.real_money.getText(),"TRX");
        Assert.assertTrue(sepLeftNumberTextToFloat(transfer.fee_text.getText(),"TRX") == 0);
        Assert.assertEquals(allNumber,number);
    }

    @Parameters({"privateKey"})
    @Test(enabled = true,description = "input mix send number")
    public void test005_inputMixSendNumber(String privateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(privateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("mix");
        String centent = transfer.formatErrorHits_text.getText();
      Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(enabled = true,description = "input too Much trc20 send number", alwaysRun = true)
    public void test006_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }

    @Test(enabled = true, description = "BandWidthShowTest", alwaysRun = true)
    public void test007_BandWidthShowTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        waiteTime();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.selectTokenType("20");
        waiteTime();
        transfer.tranferCount_text.sendKeys("0.000001");
        waiteTime();
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        waiteTime();
        String no_bandwidthTips = transfer.tv_no_energy.getText();
        Assert.assertTrue(no_bandwidthTips.contains("此交易需消耗能量，如您的能量不足，会额外燃烧TRX来获得能量。请保证拥有足够的能量或TRX，以避免交易失败。"));
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Test(groups = {"P0"},enabled = true,description = "Trc20 transfer success recording")
    public void test008_trc20TransferInSuccessRecording() throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      TrxPage trx = asset.enterTrx20Page();
      trx.tranfer_tab.get(1).click();
      String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[0];
      System.out.println("now : " + tranferInCount);
      System.out.println("sendTrc20Amount : " + sendTrc20Amount);
        Assert.assertTrue(Float.toString(sendTrc20Amount).substring(0,5).equals(tranferInCount.substring(1,6)));

  }



    @Test(enabled = true, description = "TRC20 transfer history record test", alwaysRun = true)
    public void test009_trc20TransactionHistory() throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      MinePage mine = asset.enterMinePage();
      TransactionRecordPage transaction = mine.enterTransactionRecordPage();
      String transactionType = transaction.transactionTypeList.get(0).getText();
      System.out.println(transactionType);
      Assert.assertTrue(transactionType.equals("触发智能合约") || transactionType.equals("Trigger Smart Contract"));
  }


  @Parameters({"address"})
  @Test(enabled = true, description = "Trc 20 transaction detail info test", alwaysRun = true)
  public void test010_trc20TransactionDetailInfo(String address) throws Exception {

      AssetPage asset = new AssetPage(DRIVER);
      TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(2);
      Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
      Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
      Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
      System.out.println(transactionInfo.title_amount_test.getText());
//      String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
      String sendIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
      Helper.swipScreenLitte(asset.driver);
      Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("2021"));
      Assert.assertTrue(sendIcon.contains("-"));
//      Assert.assertEquals(detailPageSendAmount.substring(1),String.valueOf(sendTrc20Amount));
      Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
              > Long.valueOf(currentMainNetBlockNum));
      Helper.swipScreenLitte(asset.driver);
      Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
      Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
      String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
      if (Integer.parseInt(number.trim()) == 0){
          String numberCost = StringUtils.substringBeforeLast(transactionInfo.fee_coast.getText(),"TRX");
          Float floater = Float.parseFloat(numberCost);
          Assert.assertTrue(floater != 0.0);
      }else {
          Assert.assertTrue(Integer.parseInt(number.trim()) > 0);

      }

      String LRnumber = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
//      String Rnumber = StringUtils.substringAfterLast(LRnumber,"带宽");
      Assert.assertTrue(Integer.parseInt(LRnumber.trim()) > 0);

  }

  @Parameters({"address"})
  @Test(enabled = true, description = "Trc20 receive transaction detail info test", alwaysRun = true)
  public void test011_trc20ReceiveTransactionDetailInfo(String address) throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      TransactionDetailInfomaitonPage transactionInfo = asset.enterReceiverTransactionDetailPage(2);
      System.out.println(transactionInfo.title_amount_test.getText());
      System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
      String detailPageReceiveAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
      String receiveIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
      Assert.assertTrue(receiveIcon.contains("+"));
      Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
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
      String recorders = transactionInfo.resouce_cost.getText();
      String bandwidth = StringUtils.substringBeforeLast(recorders,"带宽");
      String energy = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(recorders,"带宽"),"能量");
      log( " bandwidth : " + bandwidth + " energy: "+ energy);
      Assert.assertTrue(Integer.parseInt(bandwidth.trim()) >= 0);
      Assert.assertTrue(Integer.parseInt(energy.trim()) >= 0);

  }


}
