package android.com.tronlink.wallet.dappChain;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.NodeSetPage;
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

public class MainNetDeposit10 extends Base {
    Random rand = new Random();
    float depositTrc10Amount;

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
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset.enterTrx10Page();
    }

    @Test(description = "Change Chain", alwaysRun = true)
    public void test0001_changeChain() throws Exception {
        SettingPage set = enterSettingPage();
        String nodeName = set.node_name.getText();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        //String currentNodeName = set.node_name.getText();
        MinePage mine = set.enterMinePage();
        AssetPage assetPage = mine.enterAssetPage();
        String currentNodeName = assetPage.currChain_name.getText();
        System.out.println("change chain to : " + currentNodeName);
        Assert.assertNotEquals(nodeName, currentNodeName);
    }

    @Test(enabled = true,description = "Check TransferIn Chain Name", alwaysRun = true)
    public void test0002_checkTransferInChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String chain = transferIn.chain_text.getText();
        Assert.assertTrue(chain.equals("DAppChain"));
    }

    @Test(enabled = true,description = "Check TransferIn Trc10 Count", alwaysRun = true)
    public void test0003_checkTransferInTrc10() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }

    @Test(enabled = true,description = "Check TransferIn Hits", alwaysRun = true)
    public void test0004_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("hits");
        Assert.assertTrue(info.contains("转入需要执行智能合约。执行智能合约同时会消耗能量") || info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。") || info.contains("requires the execution of a smart contract"));
    }

    @Test(enabled = true,description = "Check TransferIn Fee", alwaysRun = true)
    public void test0005_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }

    @Test(enabled = true,description = "TransferIn Success Checkout Available trc10", alwaysRun = true)
    public void test0006_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount = " + trxCount);
        TransferPage transferIn = trx.enterTransferPage();
        trx = transferIn.enterTrxPageWithTransferSuccess();
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCountNow = " + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow);
    }

    @Test(enabled = true,description = "Check TransferIn Hits", alwaysRun = true)
    public void test007_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("hits");
        Assert.assertTrue(info.contains("智能合约") || info.contains("smart contract"));
    }

    @Test(enabled = true,description = "Deposit TRC10 to Dapp Chain succesfully", alwaysRun = true)
    public void test008_depositTrc10ToDappChainSuccess() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage depositTrc10 = trx.enterTransferPage();
        depositTrc10Amount = rand.nextFloat() + 1;
        depositTrc10.enterTrxPageWithTransferSuccess(Float.toString(depositTrc10Amount));
    }

    @Test(enabled = true,description = "Trc10 deposit into Dapp chain Success Recording", alwaysRun = true)
    public void test009_depositSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 5) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx10Page();
                trx.tranfer_tab.get(3).click();
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("dappChainSendTrc10Amount:" + depositTrc10Amount);
                System.out.println("tranferInCount:" + tranferInCount);
                if (Float.toString(depositTrc10Amount).substring(0, 5)
                    .equals(tranferInCount.substring(0, 5))) {
                    exist = true;
                    break;
                }
            } catch (Exception e) {
            }
        }
        Assert.assertTrue(exist);
    }

}
