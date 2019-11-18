package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainNetWithdraw extends BaseTest {



    //reset app turn to MainChain
    public void changeToMainChain() {
        try {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            nodeSet.enterSettingPageChoiseMainChain();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}

    }



    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }



    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset.enterTrxPage();
    }




    @Test(description = "Check transferOut Chain Name",alwaysRun = true)
    public void test001_checkTransferOutChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String chain = transferOut.chain_text.getText();
        Assert.assertTrue(chain.equals("MainChain"));
    }




    @Test(description = "Check transferOut Trx Count",alwaysRun = true)
    public void test002_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }



    @Test(description = "Check transferOut Hits",alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("hits");
        Assert.assertTrue(info.equals("转出需要执行智能合约。执行智能合约同时会消耗 Energy。") || info.contains("requires the execution of a smart contract"));
    }



    @Test(description = "Check transferOut Fee",alwaysRun = true)
    public void test004_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferPage();
        String info = transferOut.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        TrxPage trx = asset.enterTrxPage();
        int frozenCount = Integer.valueOf(removeSymbol(trx.freezeCount_text.getText()));
        TransferPage transferOut = trx.enterTransferPage();
        int availableBalance = Integer.valueOf(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == frozenCount + availableBalance);
    }



    @Test(description = "transferOut Success Checkout Available trx",alwaysRun = true)
    public void test006_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferOut =  trx.enterTransferPage();
        trx = transferOut.enterTrxPageWithTransferSuccess();
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }



    @Test(description = "transferOut Success Recording",alwaysRun = true)
    public void test007_transferOutSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut =  trx.enterTransferPage();
        String count = random(10,10);
        trx = transferOut.enterTrxPageWithTransferSuccess(count);
        int tries = 0;
        Boolean exist = false;
        while(exist == false && tries < 7) {
            tries++;
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrxPage();
                trx.tranfer_tab.get(3).click();
                TimeUnit.SECONDS.sleep(3);
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                if (count.equals(tranferInCount)){
                    exist = true;
                    break;
                }
            }catch (Exception e){}
        }
        Assert.assertTrue(exist);
    }












}
