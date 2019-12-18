package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainNetDeposit10 extends BaseTest {




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
            return asset.enterTrx10Page();
        }else {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            MinePage mine = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrx10Page();
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



    @Test(description = "Check TransferIn Chain Name",alwaysRun = true)
    public void test002_checkTransferInChainName() throws Exception {
        Helper.guaranteeMainChain(DRIVER);
        TrxPage trx = enterTrxPage();
        Assert.assertTrue(trx.transferIn_btnArray.size()>0);

    }




    @Test(description = "Check TransferIn Trc10 Count",alwaysRun = true)
    public void test003_checkTransferInTrc10() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"10"));
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
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",alwaysRun = true)
    public void test006_checkAvailableBalance() throws Exception {

        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn = trx.enterTransferPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == availableBalance);
    }


    @Test(description = "TransferIn Success Checkout Available trc10",alwaysRun = true)
    public void test007_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount" + trxCount);
        TransferPage transferIn =  trx.enterTransferPage();
        trx = transferIn.enterTrxPageWithTransferSuccess();
        AssetPage page = trx.enterAssetPage();
        TimeUnit.SECONDS.sleep(5);
        trx = page.enterTrx10Page();
        int trxCountNow = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCountNow" + trxCountNow);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }



    @Test(description = "TransferIn Success Recording",alwaysRun = true)
    public void test008_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        double trc10before= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        TimeUnit.SECONDS.sleep(1);
        AssetPage assetPage = trx.enterAssetPage();
        trx =  assetPage.enterTrx10Page();
        double trc10after = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        Assert.assertTrue(trc10after + Double.parseDouble(count) <= trc10before );

    }

}
