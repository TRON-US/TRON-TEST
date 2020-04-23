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
//缺少最大值最小值的case
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

    @Test(description = "transferOut Success Checkout Trc20 Available trx",alwaysRun = true)
    public void test001_transferOutSuccessCheckoutTrc20Availablealance() throws Exception {

        TrxPage trx = enterTrxPage();
        double trxCount = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        successNumber = count;

        TransferPage transferOut =  trx.enterTransferOutPage();
        trx = transferOut.enterTrxPageWithTransferOutSuccess(successNumber);
        AssetPage page = trx.enterAssetPage();

        trx = page.enterTrx20Page();
        waiteTime();
        double trxCountNow =  Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));

        int tries = 0;
        Boolean value_is_changed = false;
        while (!value_is_changed && tries < 10) {
            System.out.println("value_is_changedFindTimes:" + tries);
            tries++;
            if(trxCount == trxCountNow){
                waiteTime();
                trx.back_btn.click();
                waiteTime();
                trx = page.enterTrx20Page();
                waiteTime();
                trxCountNow =  Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
            }else{
                value_is_changed = true;
            }
        }
        System.out.println("-----------\n before: "+ trxCount    + " after: " + trxCountNow  + "value successNumber : " + successNumber);
        Assert.assertTrue(trxCount >= trxCountNow +  Double.parseDouble(count) );
    }

    @Test(description = "Check transferOut Trc20 Count",alwaysRun = true)
    public void test002_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        Assert.assertTrue(Helper.contentTexts(transferOut.textArray,"10"));
    }



    @Test(description = "Check transferOut Trc20 Hits",alwaysRun = true)
    public void test003_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
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



    @Test(description = "Check Available Trc20 Balance",alwaysRun = true)
    public void test005_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount:" + trxCount);
        TransferPage transferOut = trx.enterTransferOutPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
        System.out.println("availableBalance:" + availableBalance);
        Assert.assertTrue(trxCount ==  availableBalance);

    }

    @Test(description = "input max send number",alwaysRun = true)
    public void test006_inputMaxSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage outPage = trx.enterTransferOutPage();
        waiteTime();
        trx.maxBtn.click();
        waiteTime();
        Double ableNumber =  Double.parseDouble(removeSymbolNoDot(trx.amountDesContent.getText().split(" ")[1]));
        Double textNumber =  Double.parseDouble(removeSymbolNoDot(trx.textField.getText()));
        log("ableNumber:"+ ableNumber.toString() + " textNumber:" + textNumber);
        Assert.assertTrue(ableNumber.equals(textNumber));
        outPage.get_out_btn().click();
        TimeUnit.SECONDS.sleep(10);
        Double confirmNumber = Double.parseDouble(removeSymbolNoDot(outPage.chargeText.getText().split(" ")[0]));
        log("confirmNumber:"+ confirmNumber.toString() + " textNumber:" + textNumber);
        Assert.assertTrue(confirmNumber.equals(textNumber) );
        Double feeNumber = Double.parseDouble(removeSymbolNoDot(outPage.bandwidthText.getText().split(" ")[0]));
        log("feeNumber:"+ feeNumber.toString());
        Assert.assertTrue(feeNumber > 0 );
        Assert.assertTrue(outPage.titleText.getText().contains("转出签名"));
        Assert.assertTrue(outPage.bandwidthLabel.getText().contains("手续费"));
        Assert.assertFalse( outPage.get_finish_btn().isEnabled());
        outPage.password_input.click();
        outPage.password_input.sendKeys("balabala");
        Helper.tapWhitePlace(outPage.driver);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue( outPage.get_finish_btn().isEnabled());

    }



    @Test(description = "input mix send number",alwaysRun = true)
    public void test007_inputMixSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        trx.enterTransferOutPage();
        waiteTime();
        trx.textField.click();
        trx.textField.sendKeys("0");
        Helper.tapWhitePlace(trx.driver);
        Assert.assertTrue( trx.amountErrorLabel.getText().contains("格式错误"));

    }

    @Test(description = "Check OutNumberInRecord WithDraw trx20",alwaysRun = true)
    public void test008_checkOutNumberInRecordWithDrawTrx20() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterWithDrawNumberRecordPage(findString));

    }



}
