package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainNetDeposit extends BaseTest {


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }



    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.fastFindMainChain(asset.textArray)){
            return asset.enterTrxPage();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrxPage();
        }
    }

    //enter AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.fastFindMainChain(asset.textArray)){
            return asset;
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset;
        }
    }


//    @Test(description = "Change Chain DappChain",alwaysRun = true)
//    public void test001_changeChain() throws Exception {
//        SettingPage set = enterSettingPage();
//        if(Helper.contentTexts(set.textArray,"MainChain")){
//            NodeSetPage nodeSet = set.enterNodeSetPage();
//            set = nodeSet.enterSettingPageChoiseDappChain();
//            TimeUnit.SECONDS.sleep(2);
//            Assert.assertTrue(Helper.contentTexts(set.textArray,"DAppChain"));
//        }
//
//    }
//
//    @Test(description = "Change Chain MainChain",alwaysRun = true)
//    public void test0011_checkTransferInChainName() throws Exception {
//        SettingPage set = enterSettingPage();
//        if(Helper.contentTexts(set.textArray,"DAppChain")){
//            NodeSetPage nodeSet = set.enterNodeSetPage();
//            set = nodeSet.enterSettingPageChoiseMainChain();
//            TimeUnit.SECONDS.sleep(2);
//            Assert.assertTrue(Helper.contentTexts(set.textArray,"MainChain"));
//        }
//    }

    @Test(description = "Check TransferIn Chain Name",alwaysRun = true)
    public void test002_checkTransferInChainName() throws Exception {
        Helper.guaranteeMainChain(DRIVER);
        TrxPage trx = enterTrxPage();
        Assert.assertTrue(trx.transferIn_btnArray.size()>0);
    }




    @Test(description = "Check TransferIn Trx Count",alwaysRun = true)
    public void test003_checkTransferInTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"10"));
    }



    @Test(description = "Check TransferIn Hits",alwaysRun = true)
    public void test004_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
//转入需要执行智能合约。执行智能合约同时会消耗 Energy。
    }



    @Test(description = "Check TransferIn Fee",alwaysRun = true)
    public void test005_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        String val = transferIn.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        System.out.println("-------:----");
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",alwaysRun = true)
    public void test006_checkAvailableBalance() throws Exception {

        AssetPage asset = enterAssetPage();
        int trxCount = Integer.parseInt(removeSymbol(asset.getTrxCount()));
        TrxPage trx = asset.enterTrxPage();
        int frozenCount = Integer.parseInt(removeSymbol(trx.freezeCount_text.getText()));
        TransferPage transferIn = trx.enterTransferPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == frozenCount + availableBalance);
    }



    @Test(description = "TransferIn Success Checkout Available trx",alwaysRun = true)
    public void test007_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        trx = transferIn.enterTrxPageWithTransferSuccess();
        AssetPage page = trx.enterAssetPage();
        trx =  page.enterTrxPage();
        int trxCountNow = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }



    @Test(description = "TransferIn Success Recording",alwaysRun = true)
    public void test008_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        double trcbefore= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        System.out.println("---------:" +trcbefore);
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        TimeUnit.SECONDS.sleep(1);
        AssetPage assetPage = trx.enterAssetPage();
        trx =  assetPage.enterTrxPage();
        double trcafter = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("---------:" +trcafter);
        Assert.assertTrue(trcafter + Double.parseDouble(count) <= trcbefore );
    }








}
