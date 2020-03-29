package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;


public class MainNetDeposit extends Base {
    Random rand = new Random();
    float depositTrxAmount;
    static String mainNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.mainNetGateWay");
    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");


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
            DRIVER.activateApp("com.tronlink.wallet");
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
/*        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();*/
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        return asset.enterTrxPage();
    }

    @Test(enabled = true,description = "Check TransferIn Chain Name", alwaysRun = true)
    public void test001_checkTransferInChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String chain = transferIn.chain_text.getText();
        Assert.assertTrue(chain.equals("DAppChain"));
    }


    @Test(enabled = true,description = "Check TransferIn Trx Count", alwaysRun = true)
    public void test002_checkTransferInTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }


    @Test(enabled = true,description = "Check TransferIn Hits", alwaysRun = true)
    public void test003_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("hits");
        System.out.println("info:" + info);
        Assert.assertTrue(info.contains("转入需要执行智能合约。执行智能合约同时会消耗能量") || info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。") || info.contains("requires the execution of a smart contract"));
    }


    @Test(enabled = true,description = "Check TransferIn Fee", alwaysRun = true)
    public void test004_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }


    @Test(enabled = true,description = "Check Available Balance", alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
/*        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();*/
        AssetPage asset = new AssetPage(DRIVER);
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        System.out.println("trxCount = " + trxCount);
        TrxPage trx = asset.enterTrxPage();
        int frozenCount = Integer.valueOf(removeSymbol(trx.freezeCount_text.getText()));
        System.out.println("frozenCount = " + frozenCount);
        TransferPage transferIn = trx.enterTransferPage();
        int availableBalance = Integer.valueOf(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        System.out.println("availableBalance = " + availableBalance);
        Assert.assertTrue(trxCount == frozenCount + availableBalance);
    }


    @Test(enabled = true,description = "Deposit trx into Dapp chain success checkout available trx", alwaysRun = true)
    public void test006_depositTrxIntoDappChain() throws Exception {
        TrxPage trx = enterTrxPage();
        depositTrxAmount = getAnAmount() + 9;
        TransferPage transferIn = trx.enterTransferPage();
        transferIn.enterTrxPageWithTransferSuccess(Float.toString(depositTrxAmount));
    }

    @Test(enabled = true,description = "Trc10 deposit into Dapp chain Success Recording", alwaysRun = true)
    public void test007_depositSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 5) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrxPage();
                trx.tranfer_tab.get(3).click();
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("dappChainSendTrc10Amount:" + depositTrxAmount);
                System.out.println("tranferInCount:" + tranferInCount);
                if (Float.toString(depositTrxAmount).substring(0, 5)
                    .equals(tranferInCount.substring(0, 5))) {
                    exist = true;
                    break;
                }
            } catch (Exception e) {
            }
        }
        Assert.assertTrue(exist);
    }


    @Parameters({"address"})
    @Test(enabled = true, description = "Trx depisit transaction detail info test", alwaysRun = true)
    public void test008_trxTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterDepositTransactionDetailPage(0);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),mainNetGateWay);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
            > Long.valueOf(currentDappNetBlockNum) );
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
        Assert.assertEquals(detailPageSendAmount.substring(0,6),String.valueOf(depositTrxAmount).substring(0,6));

        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    }





}
