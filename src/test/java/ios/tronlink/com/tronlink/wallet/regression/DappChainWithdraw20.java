package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DappChainWithdraw20 extends BaseTest {


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
            return asset.enterTrx20Page();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrx20Page();
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
    @Test(description = "guarantee Chain in Dappchain",alwaysRun = true)
    public void test000_GuaranteeChainName() throws Exception {
        Assert.assertTrue( Helper.guaranteeDappChain(DRIVER));
    }

//
//    @Test(description = "Check transferOut Trc20 Count",alwaysRun = true)
//    public void test002_checkTransferOutTrx() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferOut = trx.enterTransferOutPage();
//        transferOut.inputAndTapToTransferOut();
//        Assert.assertTrue(Helper.contentTexts(transferOut.textArray,"10"));
//    }



    @Test(description = "Check transferOut Trc20 Hits",alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
//        System.out.println("start");
//
//        System.out.println(transferOut.errTipLabel.getText());
//        System.out.println("end");

        Assert.assertTrue(Helper.contentTexts(transferOut.textArray,"转出需要执行智能合约"));
//转出需要执行智能合约。执行智能合约同时会消耗 Energy。
    }



    @Test(description = "Check transferOut Trc20  Fee",alwaysRun = true)
    public void test004_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        String val = transferOut.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        Assert.assertTrue(50 <= count && count <= 500);
    }



//    @Test(description = "Check Available Trc20 Balance",alwaysRun = true)
//    public void test005_checkAvailableBalance() throws Exception {
//        TrxPage trx = enterTrxPage();
//        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
//        System.out.println("trxCount:" + trxCount);
//        TransferPage transferOut = trx.enterTransferOutPage();
//        int availableBalance = Integer.parseInt(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
//        System.out.println("availableBalance:" + availableBalance);
//        Assert.assertTrue(trxCount ==  availableBalance);
//
//    }



    @Test(description = "transferOut Success Checkout Trc20 Available trx",alwaysRun = true)
    public void test006_transferOutSuccessCheckoutTrc20Availablealance() throws Exception {

        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferOut =  trx.enterTransferOutPage();
        trx = transferOut.enterTrxPageWithTransferOutSuccess();
        AssetPage page = trx.enterAssetPage();
        trx = page.enterTrx20Page();
        int trxCountNow = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("startCount: " + trxCount + "  endCountNow: " + trxCountNow);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }



//    @Test(description = "transferOut Success Recording",alwaysRun = true)
//    public void test007_transferOutSuccessRecording() throws Exception {
//        TrxPage trx = enterTrxPage();
//        double trc20before= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
//        TransferPage transferOut =  trx.enterTransferOutPage();
//        String count = removeSymbol(random(10,10));
//        trx = transferOut.enterTrxPageWithTransferOutSuccess(count);
//        TimeUnit.SECONDS.sleep(1);
//        AssetPage assetPage = trx.enterAssetPage();
//        trx =  assetPage.enterTrx20Page();
//        double trc20after = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
//        Assert.assertTrue(trc20after + Double.parseDouble(count) <= trc20before );
//
//    }




}
