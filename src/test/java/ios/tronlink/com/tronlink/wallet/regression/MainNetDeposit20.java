package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainNetDeposit20 extends BaseTest {



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
            return asset.enterTrx20Page();
        }else {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            MinePage mine = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrx20Page();
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




    @Test(description = "Change Chain DappChain",alwaysRun = true)
    public void test001_changeChain() throws Exception {
        SettingPage set = enterSettingPage();
        if(Helper.contentTexts(set.textArray,"MainChain")){
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertTrue(Helper.contentTexts(set.textArray,"DAppChain"));
        }

    }

    @Test(description = "Change Chain MainChain",alwaysRun = true)
    public void test0011_checkTransferInChainName() throws Exception {
        SettingPage set = enterSettingPage();
        if(Helper.contentTexts(set.textArray,"DAppChain")){
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            TimeUnit.SECONDS.sleep(2);
            Assert.assertTrue(Helper.contentTexts(set.textArray,"MainChain"));
        }
    }




    @Test(description = "Check TransferIn Trc20 Count",alwaysRun = true)
    public void test003_checkTransferInTrc10() throws Exception {
        TrxPage trx = enterTrxPage();
        Assert.assertTrue(trx.transferIn_btnArray.size()>1);

    }



    @Test(description = "Check TransferIn Hits",alwaysRun = true)
    public void test004_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
    }



    @Test(description = "Check TransferIn Fee",alwaysRun = true)
    public void test005_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        String val = transferIn.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        System.out.println("-------:----");
        Assert.assertTrue(50 <= count && count <= 1000);
    }




    @Test(description = "TransferIn Success Recording",alwaysRun = true)
    public void test008_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        double trc20before= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        TimeUnit.SECONDS.sleep(1);
        AssetPage assetPage = trx.enterAssetPage();
        trx =  assetPage.enterTrx20Page();
        double trc20after = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        Assert.assertTrue(trc20after + Double.parseDouble(count) <= trc20before );

    }



}
