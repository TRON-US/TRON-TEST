package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;

import java.lang.reflect.Method;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class  DappNetWithdraw extends Base {
    Random rand = new Random();
    float withdrawTrxAmount;
    static String dappNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.dappNetGateWay");
    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");

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
            DRIVER.activateApp("wallet.tronlink.harmony");
        }catch (Exception e){}
    }


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }


    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrxPage();
    }


    @Test(enabled = true,description = "Check transferOut Chain Name", alwaysRun = true)
    public void test001_checkTransferOutChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        String chain = transferOut.chain_text.getText();
        Assert.assertTrue(chain.equals("MainChain"));
    }


    @Test(enabled = true,description = "Check transferOut Trx Count", alwaysRun = true)
    public void test002_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        String info = transferOut.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }


    @Test(enabled = true,description = "Check transferOut Hits", alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        String info = transferOut.getTransferInfo("hits");
        Assert.assertTrue(info.contains("转出需要执行智能合约，执行智能合约同时会消耗能量。") || info.contains("requires the execution of a smart contract"));
    }


    @Test(enabled = true,description = "Check transferOut Fee", alwaysRun = true)
    public void test004_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        String info = transferOut.getTransferInfo("fee");
        int fee = Integer.valueOf(info);
        Assert.assertTrue(fee >= 0);
        int bandwidth = Integer.valueOf(transferOut.bandwidth.getText().replace("带宽","").trim());
        Assert.assertTrue(bandwidth >= 50 && bandwidth <= 900);
    }


    @Test(enabled = true,description = "Check Available Balance", alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trx = asset.enterTrxPage();
        int trxCount = Integer.valueOf(removeSymbol(trx.totalBalance.getText()));
        int frozenCount = Integer.valueOf(removeSymbol(trx.freezeCount_text.getText()));
        TransferPage transferOut = trx.enterTransferInPage();
        TimeUnit.SECONDS.sleep(1);
        log("availableBalance_text : " + transferOut.availableBalance_text.getText());
        int availableBalance = Integer.valueOf(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
        log( "trxCount: " + trxCount + " frozenCount: " + frozenCount + " availableBalance: " + availableBalance );
        Assert.assertTrue(trxCount == frozenCount + availableBalance);
    }


    @Test(groups = {"P0"},enabled = true,description = "transferOut Success Checkout Available trx", alwaysRun = true)
    public void test006_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount = " + trxCount);
        TransferPage transferOut = trx.enterTransferInPage();
        trx = transferOut.enterTrxPageWithTransferSuccess();
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCountNow = " + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow);
    }


    @Test(enabled = true,description = "Check transferOut Hits", alwaysRun = true)
    public void test007_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        String info = transferOut.getTransferInfo("hits");
        Assert.assertTrue(info.contains("行智能合约") || info.contains("smart contract"));
    }


    @Test(groups = {"P0"},enabled = true,description = "transferOut Success Recording")
    public void test008_transferOutSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferInPage();
        withdrawTrxAmount = getAnAmount() + 9;
        System.out.println("withdrawTrxAmount: " + Float.toString(withdrawTrxAmount).substring(0, 5) );
        trx = transferOut.enterTrxPageWithTransferSuccess(Float.toString(withdrawTrxAmount));
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 10) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrxPage();
                trx.tranfer_tab.get(3).click();
                //todo 转出转入记录中没有最新数据
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[0];
                System.out.println(" tranferInCount:" + tranferInCount.substring(0, 6));
                if (Float.toString(withdrawTrxAmount).substring(0, 5)
                    .equals(tranferInCount.substring(1, 6))) {
                    exist = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            TimeUnit.SECONDS.sleep(6);
        }
        Assert.assertTrue(exist);
    }



    @Parameters({"address"})
    @Test(enabled = true, description = "Dapp net withdraw Trx transaction detail info test", alwaysRun = true)
    public void test009_DappNetWithdrawTrxTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterWithdrawTransactionDetailPage(0);
        String recordNumber = transactionInfo.title_amount_test.getText();
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        //尼罗河测链gateway
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),dappNetGateWay);
        Helper.swipScreen(transactionInfo.driver);

        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        System.out.println("recordNumber: " + recordNumber);
        String detailPageSendAmount = recordNumber.split(" ")[0];
        Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(withdrawTrxAmount).substring(0,6));
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    }




}
