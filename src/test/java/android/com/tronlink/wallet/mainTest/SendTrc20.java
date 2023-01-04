package android.com.tronlink.wallet.mainTest;

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

    public Double sentAmountRecoder;


    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e){

        }
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
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

    @Test(groups = {"P0"},description = "Send trx success test", alwaysRun = true)
    public void test001_sendTrc20Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        sentAmountRecoder = sendAmount;
        System.out.println("sendAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        transfer.btn_done.click();
        asset.enterTrx20Page();
        Double afterValue =  Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);
        Assert.assertEquals(sendAmount + afterValue,beforeValue,0.000001);
    }

    @Test(alwaysRun = true)
    public void test002_redDotTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage page = asset.enterMinePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementShotId("tv_bell"));
        page.tv_bell.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(page.firstContent.getText().contains(sentAmountRecoder.toString()));
        DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertFalse(isElementShotId("tv_bell"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test003_sendTrc20DetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRC20 通证转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
                System.out.println("detailAmount-----"+ detailAmount);
                Assert.assertEquals(detailAmount,sendAmount);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2023"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }


    @Test(enabled = true,description = "input max send number")
    public void test004_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("max");
        Assert.assertTrue(transfer.bt_send.isEnabled());
    }

    @Parameters({"privateKey","udid"})
    @Test(enabled = true,description = "input min send number")
    public void test005_inputMinSendNumber(String privateKey,String udid) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendTokenMin("20",udid);
        Assert.assertTrue(isElementTextExist("   转账数量需大于 0"));
    }


    @Test(enabled = true,description = "input too Much trc20 send number", alwaysRun = true)
    public void test006_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        Assert.assertTrue(isElementTextExist("   转账数量不可大于可用数量"));

    }


    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test007_availableAmountInTransfer() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        Double avValue =  Double.parseDouble(removeSymbolString(asset.tv_count.getText()));
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.normalSendStepOne();
        Double stepOneValue =  Double.parseDouble(removeSymbolString(transfer.balance_text.getText()));
        System.out.println(avValue);
        System.out.println(stepOneValue);
        Assert.assertEquals(avValue,stepOneValue);
    }

}
