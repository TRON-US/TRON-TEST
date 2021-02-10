package android.com.tronlink.wallet.dappChain;

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

public class DappSendTrc10 extends Base {
    Random rand = new Random();
    float dappChainSendTrc10Amount;
    float beforeBalance;
    float afterBalance;
  static String receiverAddress = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.receiverAddress");
  static String dappNetGateWay = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.dappNetGateWay");
  static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.currentDappNetBlockNum");
  static String trc10TokenName = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.trc10TokenName");

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
      new Helper().getSign(privateKey, DRIVER);
      setToDAppChain();
      try {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlinkpro.wallet");
      } catch (Exception e){}
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

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
      AssetPage asset = new AssetPage(DRIVER);
      MinePage mine = asset.enterMinePage();
     return mine.enterSettingPage();
    }

    public TrxPage enterTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Helper.swipScreenLitte(asset.driver);
        return asset.enterTrx10Page();
    }



    public SendTrxPage enterToSendTrc10Page() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        return transfer;
    }

    @Test(groups = {"P0"},description = "SendTrc10 success test", alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {
      SendTrxPage transfer = enterToSendTrc10Page();
      beforeBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
      dappChainSendTrc10Amount = getAnAmount();
      transfer.sendTrc10(Float.toString(dappChainSendTrc10Amount));
    }

//    @Test(description = "input max send number", alwaysRun = true)
//    public void test002_inputMaxSendNumber() throws Exception {
//        SendTrxPage transfer = enterToSendTrc10Page();
//        transfer.sendAllTrc10("max");
//        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
//    }
//
//
//    @Test(description = "input mix send number", alwaysRun = true)
//    public void test003_inputMixSendNumber() throws Exception {
//        SendTrxPage transfer = enterToSendTrc10Page();
//        transfer.sendAllTrc10("mix");
//        String centent = transfer.formatErrorHits_text.getText();
//        Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
//    }
//
//
//    @Test(description = "input too Much TRX send number", alwaysRun = true)
//    public void test004_inputTooMuchSendNumber() throws Exception {
//        SendTrxPage transfer = enterToSendTrc10Page();
//        transfer.sendAllTrc10("tooMuch");
//        String centent = transfer.formatErrorHits_text.getText();
//        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
//    }


    @Test(description = "trc10 check 10name", alwaysRun = true)
    public void test005_check10Name() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrx10Page();
        SendTrxPage sendTrxPage = trxPage.enterSendTrc10Page();
        //TransferPage transferPage = trxPage.enterTransferPage();
        Assert.assertTrue(sendTrxPage.tvName_text.getText().contains("token"));
    }

    @Test(enabled = true, description = "BandWidthShowTest", alwaysRun = true)
    public void test006_BandWidthShowTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("0.000001");
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        waiteTime();
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Test(enabled = true,description = "Dapp chain send TRC10 recording")
    public void test007_dappChainSendTrc10Recording() throws Exception {
      TrxPage trx = enterTrxPage();
      int tries = 0;
      Boolean exist = false;
      while (exist == false && tries++ < 2) {
        try {
         AssetPage arret = trx.enterAssetPage();
          trx = arret.enterTrx10Page();
          trx.tranfer_tab.get(1).click();
          //todo 转出转入记录中没有最新数据
            String tranfercount = trx.tranferIncount_text.get(1).getText().substring(1);
            System.out.println("tranferCount: " + tranfercount);
            System.out.println("dappChainSendTrxAmount: " + dappChainSendTrc10Amount);
            if (tranfercount
                    .equals(dappChainSendTrc10Amount)) {
                exist = true;
                break;
            }

        } catch (Exception e) {
          System.out.println(e);
        }
      }
      Assert.assertTrue(exist);
    }

    @Test(enabled = true,description = "TRC10 transfer balance decrease check")
    public void test008_balanceReduceAfterSendTrc10() throws Exception {
      SendTrxPage transfer = enterToSendTrc10Page();
      afterBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
      Assert.assertTrue(beforeBalance - afterBalance >= 1);
    }

  @Parameters({"address"})
  @Test(enabled = true, description = "Dapp send Trc 10 transaction detail info test", alwaysRun = true)
  public void test009_DappSendTrc10TransactionDetailInfo(String address) throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(1);
    Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
    //尼罗河测链gateway
    Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
    Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
    Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
    Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
    System.out.println(transactionInfo.title_amount_test.getText());
    System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
    String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
    Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(dappChainSendTrc10Amount).substring(0,6));
    Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc10TokenName));
    Helper.swipScreen(transactionInfo.driver);
    Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
    Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
    Assert.assertTrue(Integer.parseInt(number.trim()) >= 0);
  }

  }
