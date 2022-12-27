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
//        System.out.println( " successNumber：  " + successNumber );
//        trx = transferIn.enterTrxPageWithTransferSuccess(count);
//        TimeUnit.SECONDS.sleep(3);
//        Helper.refreshWalletScreen(DRIVER);
//        Double trcafter = Double.parseDouble(removeSymbolNoDot(trx.trxTotal_text.getText()));
//        System.out.println( " after：  " + trcbefore + "count: " + count + " = trcbefore:" + trcbefore);
//        Assert.assertEquals(trcafter + Double.parseDouble(count) ,trcbefore ,0.000001 );
//    }
//
//
//    @Test(description = "Check TransferIn Hits",alwaysRun = true)
//    public void test002_checkTransferInHits() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferInPage();
//        transferIn.inputAndTapToTransfer();
//        Assert.assertTrue(Helper.contentTexts(transferIn.textArray,"转入需要执行智能合约"));
//    }
//
//    @Test(description = "Check TransferIn Fee",alwaysRun = true)
//    public void test003_checkTransferInFee() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferInPage();
//        transferIn.inputAndTapToTransfer();
//        String val = transferIn.getvalueofBandwidthText();
//        int count = Integer.parseInt(removeSymbolNoDot(val));
//        System.out.println("-------:----");
//        Assert.assertTrue(Double.parseDouble(transferIn.getFeeText()) > 0 || 200 <= count && count <= 500  );
//    }
//
//    @Test(description = "Check Available Balance",enabled = false)
//    public void test004_checkAvailableBalance() throws Exception {
//        TrxPage trx = enterTrxPage();
//        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
//        TransferPage transferIn = trx.enterTransferInPage();
//        int availableBalance = Integer.parseInt(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
//        Assert.assertTrue(trxCount == availableBalance);
//    }
//
//    @Test(description = "Check OutNumberInRecord Deposit trx",alwaysRun = true)
//    public void test005_CheckOutNumberInRecordDepositTrx() throws Exception {
//        log("successNumber:"+successNumber);
//        TrxPage page = enterTrxPage();
//        Assert.assertTrue(page.enterDepositNumberRecordPage(successNumber));
//        Assert.assertTrue(isElementExist("TRC20 通证转账"));
//        Assert.assertTrue(isElementExist("查看详细数据"));
//
//    }



}
