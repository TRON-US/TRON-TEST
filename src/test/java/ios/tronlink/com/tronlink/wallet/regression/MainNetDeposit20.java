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

    @Test(description = "TransferIn Success Recording",alwaysRun = true)
    public void test001_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        double trc20before= Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferInPage();
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
    public void test002_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
    }



    @Test(description = "Check TransferIn Fee",alwaysRun = true)
    public void test003_checkTransferInFee() throws Exception {
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
    public void test004_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferIn = trx.enterTransferInPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount == availableBalance);
    }

    @Test(description = "input max send number",alwaysRun = true)
    public void test005_inputMaxSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage outPage = trx.enterTransferInPage();
        waiteTime();
        trx.maxBtn.click();
        waiteTime();
        Double ableNumber =  Double.parseDouble(removeSymbolNoDot(trx.amountDesContent.getText().split(" ")[1]));
        Double textNumber =  Double.parseDouble(removeSymbolNoDot(trx.textField.getText()));
        log("ableNumber:"+ ableNumber.toString() + " textNumber:" + textNumber);
        Assert.assertTrue(ableNumber.equals(textNumber));
        outPage.get_inter_btn().click();
        TimeUnit.SECONDS.sleep(10);
        Double confirmNumber = Double.parseDouble(removeSymbolNoDot(outPage.chargeText.getText().split(" ")[0]));
        log("confirmNumber:"+ confirmNumber.toString() + " textNumber:" + textNumber);
        Assert.assertTrue(confirmNumber.equals(textNumber) );
        Double feeNumber = Double.parseDouble(removeSymbolNoDot(outPage.bandwidthText.getText().split(" ")[0]));
        log("feeNumber:"+ feeNumber.toString());
        Assert.assertTrue(feeNumber > 0 );
        Assert.assertTrue(outPage.titleText.getText().contains("转入签名"));
//        Assert.assertTrue(outPage.bandwidthLabel.getText().contains("消耗资源"));
        Assert.assertFalse( outPage.get_finish_btn().isEnabled());
        outPage.password_input.click();
        outPage.password_input.sendKeys("balabala");
        Helper.tapWhitePlace(outPage.driver);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue( outPage.get_finish_btn().isEnabled());

    }



    @Test(description = "input mix send number",alwaysRun = true)
    public void test006_inputMixSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        trx.enterTransferInPage();
        waiteTime();
        trx.textField.click();
        trx.textField.sendKeys("0");
        Helper.tapWhitePlace(trx.driver);

        Assert.assertTrue( trx.amountErrorLabel.getText().contains("转账金额需大于 0"));

    }

    @Test(description = "Check OutNumberInRecord Deposit trx",alwaysRun = true)
    public void test007_CheckOutNumberInRecordDepositTrx() throws Exception {
        log("successNumber:"+successNumber);
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterDepositNumberRecordPage(findString));

    }

}
