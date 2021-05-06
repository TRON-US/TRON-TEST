package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;

import java.rmi.registry.LocateRegistry;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import android.com.utils.Helper;
import android.com.wallet.pages.MinePage;

public class MainNetDeposit20 extends Base {

    Random rand = new Random();
    float depositTrc20Amount;
    static String mainNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.mainNetGateWay");

    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");
    static String trc20TokenName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trc20TokenName");

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
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
        Helper.swipScreenLitte(asset.driver);
        return asset.enterTrx20Page();
    }

    @Test(groups = {"P0"},enabled = true,description = "Deposit TRC20 Success and checkout available trx", alwaysRun = true)
    public void test001_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrc20Page();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount = " + trxCount);
        TransferPage transferIn = trx.enterTransferInPage();
        depositTrc20Amount = getAnAmount();
        trx = transferIn.enterTrxPageWithTransferSuccess(Float.toString(depositTrc20Amount));
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCountNow = " + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow);
    }

    @Test(enabled = true,description = "Check TransferIn Hits")
    public void test002_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrc20Page();
        TransferPage transferIn = trx.enterTransferInPage();
        String info = transferIn.getTransferInfo("hits");
        Assert.assertTrue(info.contains("转入需要执行智能合约，执行智能合约同时会消耗能量。") || info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。") || info.contains("requires the execution of a smart contract"));
    }


    @Test(enabled = true,description = "Check TransferIn Fee")
    public void test003_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrc20Page();
        TransferPage transferIn = trx.enterTransferInPage();
        String info = transferIn.getTransferInfo("fee");
        int fee = Integer.valueOf(info);
        Assert.assertTrue(fee >= 0);
        int bandwidth = Integer.valueOf(transferIn.bandwidth.getText().replace("带宽","").trim());
        Assert.assertTrue(bandwidth >= 50 && bandwidth <= 900);
    }



    @Test(enabled = true,description = "Trc20 deposit success recording")
    public void test004_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrc20Page();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 5) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx20Page();
                trx.tranfer_tab.get(3).click();
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[0];
                System.out.println("tranferInCount =" + tranferInCount + " depositTrc20Amount =" + depositTrc20Amount);
                if (Float.toString(depositTrc20Amount).substring(0,5).equals(tranferInCount.substring(1,6))) {
                    exist = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);
    }

    @Parameters({"address"})
    @Test(enabled = true, description = "Trc20 depisit transaction detail info test", alwaysRun = true)
    public void test005_trc20DepositTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterDepositTransactionDetailPage(2);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),mainNetGateWay);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
            > Long.valueOf(currentDappNetBlockNum));
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        String recorderNumber = transactionInfo.title_amount_test.getText().split(" ")[0];
        String detailPageSendAmount = recorderNumber.substring(1);
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
        Assert.assertEquals(detailPageSendAmount.substring(0,6),String.valueOf(depositTrc20Amount).substring(0,6));
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    }





}