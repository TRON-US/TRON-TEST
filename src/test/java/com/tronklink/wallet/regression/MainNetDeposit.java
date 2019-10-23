package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.NodeSetPage;
import wallet.pages.SettingPage;
import wallet.pages.TransferInPage;
import wallet.pages.TrxPage;


public class MainNetDeposit extends Base {


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



    public SettingPage enterSettingPage() {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
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
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        TrxPage trx = asset.enterTrxPage();
        TransferInPage transferIn = trx.enterTransferInPage();
        String chain = transferIn.chain_text.getText();
        Assert.assertTrue(chain.equals("DAppChain"));
    }




    @Test(description = "Check TransferIn Trx Count")
    public void test003_checkTransferInTrx() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        TrxPage trx = asset.enterTrxPage();
        TransferInPage transferIn = trx.enterTransferInPage();
        String info = transferIn.getTransferInInfo("trx");
        Assert.assertTrue(info.contains("10"));
    }



    @Test(description = "Check TransferIn Hits")
    public void test004_checkTransferInHits() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        TrxPage trx = asset.enterTrxPage();
        TransferInPage transferIn = trx.enterTransferInPage();
        String info = transferIn.getTransferInInfo("hits");
        Assert.assertTrue(info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。"));
    }



    @Test(description = "Check TransferIn Fee")
    public void test005_checkTransferInFee() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseMainChain();
        MinePage mine  = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        TrxPage trx = asset.enterTrxPage();
        TransferInPage transferIn = trx.enterTransferInPage();
        String info = transferIn.getTransferInInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 500);
    }







}
