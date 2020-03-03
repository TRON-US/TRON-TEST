package android.com.tronlink.wallet.dappChain;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
      new Helper().getSign(privateKey, DRIVER);
      enterTrxPage();
      try {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
      } catch (Exception e){}
  }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
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

    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception {
      SettingPage set = enterSettingPage();
      NodeSetPage nodeSet = set.enterNodeSetPage();
      set = nodeSet.enterSettingPageChoiseDappChain();
      MinePage mine = set.enterMinePage();
      AssetPage asset = mine.enterAssetPage();
      return asset.enterTrx10Page();
    }


    public SendTrxPage enterToSendTrc10Page() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc10Page();
        return transfer;
    }

    @Test(description = "SendTrc10 success test", alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {
      SendTrxPage transfer = enterToSendTrc10Page();
      beforeBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
      dappChainSendTrc10Amount = getAnAmount();
      transfer.sendTrc10(Float.toString(dappChainSendTrc10Amount));
    }

    @Test(description = "input max send number", alwaysRun = true)
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrc10Page();
        transfer.sendAllTrc10("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(description = "input mix send number", alwaysRun = true)
    public void test003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrc10Page();
        transfer.sendAllTrc10("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(description = "input too Much TRX send number", alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrc10Page();
        transfer.sendAllTrc10("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }


    @Test(description = "trc10 check 10name", alwaysRun = true)
    public void test005_check10Name() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrx10Page();
        SendTrxPage sendTrxPage = trxPage.enterSendTrc10Page();
        //TransferPage transferPage = trxPage.enterTransferPage();
        Assert.assertTrue(sendTrxPage.tvName_text.getText().contains("token"));
    }



    @Test(enabled = true,description = "Dapp chain send TRC10 recording")
    public void test006_dappChainSendTrc10Recording() throws Exception {
      TrxPage trx = enterTrxPage();
      int tries = 0;
      Boolean exist = false;
      while (exist == false && tries++ < 2) {
        try {
         AssetPage arret = trx.enterAssetPage();
          trx = arret.enterTrx10Page();
          trx.tranfer_tab.get(1).click();
          //todo 转出转入记录中没有最新数据
          String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
          System.out.println("dappChainSendTrc10Amount:" + dappChainSendTrc10Amount);
          System.out.println("tranferInCount:" + tranferInCount);
          if (Float.toString(dappChainSendTrc10Amount).substring(0, 5)
              .equals(tranferInCount.substring(0, 5))) {
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
    public void test007_balanceReduceAfterSendTrc10() throws Exception {
      SendTrxPage transfer = enterToSendTrc10Page();
      afterBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
      Assert.assertTrue(beforeBalance - afterBalance >= 1);
    }
  }
