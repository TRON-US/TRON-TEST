package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DappChainWithdraw extends BaseTest {


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }



    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset.enterTrxPage();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrxPage();
        }

    }
    //enter AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset;
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset;
        }
    }



    @Test(description = "Check transferOut Chain Name",alwaysRun = true)
    public void test001_checkTransferOutChainName() throws Exception {
        Assert.assertTrue( Helper.guaranteeDappChain(DRIVER));
    }




    @Test(description = "Check transferOut Trx Count",alwaysRun = true)
    public void test002_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        Assert.assertTrue(Helper.contentTexts(transferOut.textArray,"10"));
    }



    @Test(description = "Check transferOut Hits",alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        Assert.assertTrue(Helper.contentTexts(transferOut.textArray,"转出需要执行智能合约"));
//转出需要执行智能合约。执行智能合约同时会消耗 Energy。
    }



    @Test(description = "Check transferOut Fee",alwaysRun = true)
    public void test004_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        String val = transferOut.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
        AssetPage asset = enterAssetPage();
        int trxCount = Integer.parseInt(removeSymbol(asset.getTrxCount()));
        TrxPage trx = asset.enterTrxPage();
        int frozenCount = Integer.parseInt(removeSymbol(trx.freezeCount_text.getText()));
        TransferPage transferOut = trx.enterTransferOutPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
        System.out.println("frozenCount:" + frozenCount + "\navailableBalance:" + availableBalance + "\n 应等于:trxCount" + trxCount);
        Assert.assertTrue(trxCount >= frozenCount + availableBalance);

    }



    @Test(description = "transferOut Success Checkout Available trx",alwaysRun = true)
    public void test006_checktransferOutSuccessedAvailableBalance() throws Exception {

        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferOut =  trx.enterTransferOutPage();
        trx = transferOut.enterTrxPageWithTransferOutSuccess();
        AssetPage page = trx.enterAssetPage();
        int trxCountNow = Integer.parseInt(removeSymbol(page.getTrxCount()));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }


//
//    @Test(description = "transferOut TRX Success Recording",alwaysRun = true)
//    public void test007_transferOutSuccessRecording() throws Exception {
//        TrxPage trx = enterTrxPage();
//        double trcbefore= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
//
//        TransferPage transferOut =  trx.enterTransferOutPage();
//        String count = removeSymbol(random(10,10));
//        trx = transferOut.enterTrxPageWithTransferOutSuccess(count);
//        TimeUnit.SECONDS.sleep(1);
//        AssetPage assetPage = trx.enterAssetPage();
//        trx =  assetPage.enterTrxPage();
//        double trcafter = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
//        Assert.assertTrue(trcafter + Double.parseDouble(count) <= trcbefore );
////        int tries = 0;
////        Boolean exist = false;//XCUIElementTypeStaticText  XCUIElementTypeCell
////        while(!exist && tries < 7) {
////            tries++;
////            try {
////                AssetPage arret = trx.enterAssetPage();
////                trx = arret.enterTrxPage();
////                trx.tranferOut_tab.get(2).click();
////                TimeUnit.SECONDS.sleep(3);
////                List<WebElement> lintiest = trx.getFirstTransferOutNumber();
////                if(Helper.contentTexts(lintiest,count)){
////                    exist = true;
////                    break;
////                }
////
////            }catch (Exception e){}
////        }
////        Assert.assertTrue(exist);
//    }












}
