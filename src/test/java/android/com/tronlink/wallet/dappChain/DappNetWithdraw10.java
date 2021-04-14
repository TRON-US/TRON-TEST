package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import android.com.utils.Helper;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SettingPage;

public class DappNetWithdraw10 extends Base {
  Random rand = new Random();
  float withdrawTrc10Amount;
  static String dappNetGateWay = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.dappNetGateWay");
  static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.currentDappNetBlockNum");
  static String trc10TokenName = Configuration.getByPath("testng.conf")
      .getString("foundationAccount.trc10TokenName");

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //reset DAPP chain trun main chain
        //changeToMainChain();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        setToDAppChain();

    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    //reset app turn to MainChain
    public void changeToMainChain() {
        try {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            nodeSet.enterSettingPageChoiseMainChain();
            TimeUnit.SECONDS.sleep(1);
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


    @Test(enabled = true,description = "Check transferOut Chain Name", alwaysRun = true)
    public void test001_checkTransferOutChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String chain = transferOut.chain_text.getText();
        Assert.assertTrue(chain.equals("MainChain"));
    }


    @Test(enabled = true,description = "Check transferOut Trx Count", alwaysRun = true)
    public void test002_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }


    @Test(enabled = true,description = "Check transferOut Hits", alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("hits");
      Assert.assertTrue(info.contains("转出需要执行智能合约。执行智能合约同时会消耗能量") || info.contains("requires the execution of a smart contract"));
    }


    @Test(enabled = true,description = "Check transferOut Fee", alwaysRun = true)
    public void test004_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }




//    @Test(groups = {"P0"},enabled = true,description = "Withdraw from dappChain of Trc10 Success Recording", alwaysRun = true)
//    public void test005_transferOutSuccessRecording() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferOut = trx.enterTransferPage();
//        withdrawTrc10Amount = getAnAmount();
//        trx = transferOut.enterTrxPageWithTransferSuccess(Float.toString(withdrawTrc10Amount));
//        int tries = 0;
//        Boolean exist = false;
//        while (exist == false && tries++ < 5) {
//            try {
//                AssetPage arret = trx.enterAssetPage();
//                trx = arret.enterTrx10Page();
//                trx.tranfer_tab.get(3).click();
//                trx.driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
//                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[0];
//                System.out.println("tranferInCount = " + tranferInCount);
//              if (Float.toString(withdrawTrc10Amount).substring(0, 5)
//                  .equals(tranferInCount.substring(1, 6))) {
//                exist = true;
//                break;
//              }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        Assert.assertTrue(exist);
//    }
//
//  @Parameters({"address"})
//  @Test(enabled = true, description = "Dapp net withdraw Trc10 transaction detail info test", alwaysRun = true)
//  public void test006_DappNetWithdrawTrc10TransactionDetailInfo(String address) throws Exception {
//    AssetPage asset = new AssetPage(DRIVER);
//    TransactionDetailInfomaitonPage transactionInfo = asset.enterWithdrawTransactionDetailPage(1);
//    Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
//    //尼罗河测链gateway
//    Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),dappNetGateWay);
//    Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
//    Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc10TokenName));
//    Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
//    Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
//    System.out.println(transactionInfo.title_amount_test.getText().split(" ")[0]);
//    String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
//    Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(withdrawTrc10Amount).substring(0,6));
//    Helper.swipScreen(transactionInfo.driver);
//    Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
//    Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
//  }




}
