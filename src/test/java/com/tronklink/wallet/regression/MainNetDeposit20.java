package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.NodeSetPage;
import wallet.pages.SettingPage;
import wallet.pages.TransferPage;
import wallet.pages.TrxPage;

public class MainNetDeposit20 extends Base {



    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }



    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }


    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
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
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset.enterTrx20Page();
    }




    @Test(description = "Change Chain")
    public void test001_changeChain() throws Exception {
        SettingPage set = enterSettingPage();
        String nodeName = set.node_name.getText();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        String currentNodeName = set.node_name.getText();
        Assert.assertNotEquals(nodeName,currentNodeName);
    }



    @Test(description = "Check TransferIn Chain Name")
    public void test002_checkTransferInChainName() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String chain = transferIn.chain_text.getText();
        Assert.assertTrue(chain.equals("DAppChain"));
    }




    @Test(description = "Check TransferIn Trc20 Count")
    public void test003_checkTransferInTrc10() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }



    @Test(description = "Check TransferIn Hits")
    public void test004_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("hits");
        Assert.assertTrue(info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。") || info.contains("requires the execution of a smart contract"));
    }



    @Test(description = "Check TransferIn Fee")
    public void test005_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",enabled = false)
    public void test006_checkAvailableBalance() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        TrxPage trx = asset.enterTrx10Page();
        int frozenCount = Integer.valueOf(removeSymbol(trx.freezeCount_text.getText()));
        TransferPage transferIn = trx.enterTransferPage();
        int availableBalance = Integer.valueOf(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == frozenCount + availableBalance);
    }



    @Test(description = "TransferIn Success Checkout Available trc20",enabled = false)
    public void test007_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        trx = transferIn.enterTrxPageWithTransferSuccess();
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }



    @Test(description = "TransferIn Success Recording")
    public void test008_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn =  trx.enterTransferPage();
        String count = random(10,10);
        System.out.println("count = " + count);
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        int tries = 0;
        Boolean exist = false;
        while(exist == false && tries < 7) {
            tries++;
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx20Page();
                trx.tranfer_tab.get(3).click();
                TimeUnit.SECONDS.sleep(3);
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("tranferInCount = " + tranferInCount);
                if (count.equals(tranferInCount)){
                    exist = true;
                    break;
                }
            }catch (Exception e){}
        }
        Assert.assertTrue(exist);
    }



}
