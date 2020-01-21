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

    String successNumber;

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }



    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrx20Page();
    }

    //enter AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        return asset;

    }


    @Test(description = "guarantee Chain in MainChain",alwaysRun = true)
    public void test001_GuaranteeChainName() throws Exception {
        Assert.assertTrue( Helper.guaranteeMainChain(DRIVER));
    }


    @Test(description = "TransferIn Success Recording",alwaysRun = true)
    public void test002_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        double trc20before= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        successNumber = count;
        waiteTime(8);
        Helper.swipRefreshScreen(DRIVER);
        waiteTime(8);
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        waiteTime();
        AssetPage assetPage = trx.enterAssetPage();
        trx =  assetPage.enterTrx20Page();
        double trc20after = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        Assert.assertTrue(trc20after + Double.parseDouble(count) <= trc20before );

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
        waiteTime(8);
        Helper.swipRefreshScreen(DRIVER);
        waiteTime(8);
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        String val = transferIn.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Balance",enabled = false)
    public void test006_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn = trx.enterTransferPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == availableBalance);
    }



    @Test(description = "TransferIn Success Checkout Available trc20",enabled = false)
    public void test007_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferPage();
        trx = transferIn.enterTrxPageWithTransferSuccess();
        AssetPage page = trx.enterAssetPage();
        trx =  page.enterTrx20Page();
        waiteTime();
        int trxCountNow = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("startCount:" + trxCount + "endCountNow:" + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow + 10);
    }





    @Test(description = "Check OutNumberInRecord Deposit trx",alwaysRun = true)
    public void test008_CheckOutNumberInRecordDepositTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterDepositNumberRecordPage(findString));

    }

}
