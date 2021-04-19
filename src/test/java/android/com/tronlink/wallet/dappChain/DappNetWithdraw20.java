package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
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

public class DappNetWithdraw20 extends Base {
    Random rand = new Random();
    float withdrawTrc20Amount;
    static String dappNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.dappNetGateWay");
    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");
    static String trc20TokenName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trc20TokenName");

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
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
            TimeUnit.SECONDS.sleep(1);
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

    public void changeToDappChain() {
        try {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            nodeSet.enterSettingPageChoiseDappChain();
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


    //enter TRXPage
    public TrxPage enterTrc20Page() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrx20Page();
    }


//    @Test(enabled = true,description = "Check withdraw from dapp chain information", alwaysRun = true)
//    public void test001_withdrawInformation() throws Exception {
//        TrxPage trx = enterTrc20Page();
//        TransferPage transferOut = trx.enterTransferPage();
//        String info = transferOut.getTransferInfo("hits");
//        Assert.assertTrue(info.contains("转出需要执行智能合约，执行智能合约同时会消耗能量。") || info.contains("requires the execution of a smart contract"));
//    }
//
//
//    @Test(enabled = true,description = "Check withdraw from dapp chain fee", alwaysRun = true)
//    public void test002_checkTransferOutFee() throws Exception {
//        TrxPage trx = enterTrc20Page();
//        TransferPage transferOut = trx.enterTransferPage();
//        String info = transferOut.getTransferInfo("fee");
//int fee = Integer.valueOf(info);
//        Assert.assertTrue(fee >= 0);
//    int bandwidth = Integer.valueOf(transferOut.bandwidth.getText().replace("带宽","").trim());
//        Assert.assertTrue(bandwidth >= 50 && bandwidth <= 900);
//    }
//
//    @Test(groups = {"P0"},enabled = true,description = "Withdraw from dapp chain success and checkout available trx", alwaysRun = true)
//    public void test003_checkAvailableBalance() throws Exception {
//        TrxPage trx = enterTrc20Page();
//        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
//        System.out.println("trxCount = " + trxCount);
//        TransferPage transferOut = trx.enterTransferPage();
//        withdrawTrc20Amount = getAnAmount();
//        trx = transferOut.enterTrxPageWithTransferSuccess(Float.toString(withdrawTrc20Amount));
//        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
//        System.out.println("trxCountNow = " + trxCountNow);
//        Assert.assertTrue(trxCount >= trxCountNow);
//    }
//
//
////    @Test(groups = {"P0"},enabled = true,description = "Withdraw trc20 from dapp chain Recording")
////    public void test004_transferOutSuccessRecording() throws Exception {
////        TrxPage trx = enterTrc20Page();
////        int tries = 0;
////        Boolean exist = false;
////        while (exist == false && tries++ < 5) {
////            try {
////                AssetPage arret = trx.enterAssetPage();
////                trx = arret.enterTrx20Page();
////                trx.tranfer_tab.get(3).click();
////                System.out.println(trx.tranferIncount_text.get(1).getText());
////                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[0];
////                if (Float.toString(withdrawTrc20Amount).substring(0, 5)
////                    .equals(tranferInCount.substring(1, 6))) {
////                    exist = true;
////                    break;
////                }
////            } catch (Exception e) {
////                System.out.println(e);
////            }
////        }
////        Assert.assertTrue(exist);
////    }
////
////
////    @Parameters({"address"})
////    @Test(enabled = true, description = "Dapp net withdraw Trc20 transaction detail info test", alwaysRun = true)
////    public void test005_DappNetWithdrawTrc20TransactionDetailInfo(String address) throws Exception {
////        AssetPage asset = new AssetPage(DRIVER);
////        TransactionDetailInfomaitonPage transactionInfo = asset.enterWithdrawTransactionDetailPage(2);
////        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
////        //尼罗河测链gateway
////        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),dappNetGateWay);
////        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
////        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
////        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
////        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
////        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
////        Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(withdrawTrc20Amount).substring(0,6));
////        Helper.swipScreen(transactionInfo.driver);
////        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
////        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
////    }
//

}