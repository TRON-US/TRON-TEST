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
        return asset.enterTrx10Page();

    }

    //enter AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        return asset;
    }

//    @Test(description = "TransferIn Success Recording",alwaysRun = true)
//    public void test001_transferInSuccessRecording() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TimeUnit.SECONDS.sleep(1);
//        Double trcbefore= Double.parseDouble(removeSymbolNoDot(trx.trxTotal_text.getText()));
//        System.out.println( " trcbefore：  " + trcbefore  );
//        TransferPage transferIn =  trx.enterTransferInPage();
//        Double sendcount = getAnAmountZero();
//        String count = Double.toString(sendcount);
//        successNumber = count;
//        System.out.println( " SendNumber ：  " + successNumber );
//        trx = transferIn.enterTrxPageWithTransferSuccess(count);
//        TimeUnit.SECONDS.sleep(1);
//        Helper.refreshWalletScreen(DRIVER);
//        Double trcafter = Double.parseDouble(removeSymbolNoDot(trx.trxTotal_text.getText()));
//        System.out.println( " trcafter：  " + trcafter );
//        Assert.assertEquals(trcbefore ,trcafter + Double.parseDouble(count) ,0.000001);
//    }
//
//    @Test(description = "Check TransferIn Trx Count",alwaysRun = true)
//    public void test002_checkTransferInTrx() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferInPage();
//        transferIn.inputAndTapToTransfer();
//        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"10"));
//    }
//
//
//    @Test(description = "Check TransferIn Hits",alwaysRun = true)
//    public void test003_checkTransferInHits() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferInPage();
//        transferIn.inputAndTapToTransfer();
//        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
//    }
//
//
//    @Test(description = "Check TransferIn Fee",alwaysRun = true)
//    public void test004_checkTransferInFee() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferInPage();
//        transferIn.inputAndTapToTransfer();
//        String val = transferIn.getvalueofBandwidthText();
//        int count = Integer.parseInt(removeSymbolNoDot(val));
//        System.out.println("-------:----");
//        Assert.assertTrue(Double.parseDouble(transferIn.getFeeText()) > 0 || 200 <= count && count <= 500  );
//    }
//
//
//
//    @Test(description = "input min send number",alwaysRun = true)
//    public void test005_inputMixSendNumber() throws Exception {
//        TrxPage trx = enterTrxPage();
//        trx.enterTransferInPage();
//        waiteTime();
//        trx.textField.click();
//        trx.textField.sendKeys("0");
//        closeKeyBoard();
//        Assert.assertTrue(trx.amountErrorLabel.getText().contains("转入金额需大于 0"));
//
//    }
//
//    @Test(description = "Check OutNumberInRecord Deposit trx",alwaysRun = true)
//    public void test006_CheckOutNumberInRecordDepositTrx() throws Exception {
//        log("successNumber:"+successNumber);
//        TrxPage page = enterTrxPage();
//        Assert.assertTrue(page.enterDepositTrc10NumberRecordPage(successNumber));
//        Assert.assertTrue(isElementExist("触发智能合约"));
//        Assert.assertTrue(isElementExist("查看详细数据"));
//        Assert.assertTrue(isElementExist("触发合约"));
//
//    }



}
