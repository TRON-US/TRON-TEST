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
        return asset.enterTrxPage();

    }

    //enter AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        return asset;

    }


    @Test(groups = {"P0"},description = "TransferIn Success Recording",alwaysRun = true)
    public void test001_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        TimeUnit.SECONDS.sleep(1);
        double trcbefore= Double.parseDouble(removeSymbolNoDot(trx.trxTotal_text.getText()));
        TransferPage transferIn =  trx.enterTransferInPage();
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        successNumber = count;
        trx = transferIn.enterTrxPageWithTransferSuccess(count);
        TimeUnit.SECONDS.sleep(1);
        double trcafter = Double.parseDouble(removeSymbolNoDot(trx.trxTotal_text.getText()));
        System.out.println( " before：  " + trcbefore  );
        System.out.println( " sendCount：  " + successNumber );
        System.out.println( " after：  " + trcafter );
        Assert.assertEquals(trcafter + Double.parseDouble(count) ,trcbefore );
    }

    @Test(description = "Check TransferIn Trx Count",alwaysRun = true)
    public void test002_checkTransferInTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"10"));
    }


    @Test(description = "Check TransferIn Hits",alwaysRun = true)
    public void test003_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
    }


    @Test(description = "Check TransferIn Fee",alwaysRun = true)
    public void test004_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferIn = trx.enterTransferInPage();
        transferIn.inputAndTapToTransfer();
        String val = transferIn.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbolNoDot(val));
        System.out.println("-------:----");
        Assert.assertTrue(Double.parseDouble(transferIn.getFeeText()) > 0 || 200 <= count && count <= 500);
    }



    //ios 有bug
    @Test(groups = {"P0"},description = "Check Available Balance",alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
        AssetPage asset = enterAssetPage();
        Double trxCount = Double.parseDouble(removeSymbolNoDot(asset.getTrxCount()));
        TrxPage trx = asset.enterTrxPage();
        Double frozenCount = Double.parseDouble(removeSymbolNoDot(trx.freezeCount_text.getText()));
        TransferPage transferIn = trx.enterTransferInPage();
        Double availableBalance = Double.parseDouble(removeSymbolNoDot(transferIn.availableBalance_text.getText().split(" ")[1]));
        System.out.println("trxCount startCount:" + trxCount + "availableBalance:" + availableBalance  + "frozenCount:" +  frozenCount);
        Assert.assertEquals(trxCount , frozenCount + availableBalance);
    }


    @Test(description = "input min send number",alwaysRun = true)
    public void test007_inputMixSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        trx.enterTransferInPage();
        waiteTime();
        trx.textField.click();
        trx.textField.sendKeys("0");
        Helper.tapWhitePlace(trx.driver);
        Assert.assertTrue( trx.amountErrorLabel.getText().contains("至少转入 10 TRX"));

    }


    @Test(groups = {"P0"},description = "Check OutNumberInRecord Deposit trx",alwaysRun = true)
    public void test008_CheckOutNumberInRecordDepositTrx() throws Exception {
        log("successNumber:"+successNumber);
        TrxPage page = enterTrxPage();
        Assert.assertTrue(page.enterDepositNumberRecordPage(successNumber));
        Assert.assertTrue(isElementExist("触发智能合约"));
        Assert.assertTrue(isElementExist("查看详细数据"));
        Assert.assertTrue(isElementExist("触发合约"));

    }








}
