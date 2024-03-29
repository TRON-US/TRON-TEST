package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DappChainWithdraw10 extends BaseTest {


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
            return asset.enterTrx10Page();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrx10Page();
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


    @Test(description = "transferOut Success Checkout Trc10 Available trx",alwaysRun = true)
    public void test001_checktransferOutSuccessAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        double trxCount = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println(trxCount);
        String count = removeSymbol(random(10,10));
        count = Helper.getPrettyNumber(count);
        successNumber = count;

        log(count);
        TransferPage transferOut =  trx.enterTransferOutPage();
        trx = transferOut.enterTrxPageWithTransferOutSuccess(count);
        AssetPage page = trx.enterAssetPage();

        trx = page.enterTrx10Page();
        double trxCountNow = Double.parseDouble(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println(trxCountNow);
        waiteTime();
        System.out.println(count   + "   " + trxCount  + " " + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow +  Double.parseDouble(count) );

    }


    @Test(description = "Check transferOut Trc10 Hits",alwaysRun = true)
    public void test002_checkTransferOutHits() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        Assert.assertTrue(Helper.isElementExist(transferOut.driver,"转出需要执行智能合约，智能合约同时可能会消耗能量。"));
//转出需要执行智能合约。执行智能合约同时会消耗 Energy。
    }



    @Test(description = "Check transferOut Trc10  Fee",alwaysRun = true)
    public void test003_checkTransferOutFee() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        String val = transferOut.getvalueofBandwidthText();
        int count = Integer.parseInt(removeSymbol(val));
        Assert.assertTrue(50 <= count && count <= 500);
    }



    @Test(description = "Check Available Trc10 Balance",alwaysRun = true)
    public void test004_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrxPage();
        int trxCount = Integer.parseInt(removeSymbol(trx.trxTotal_text.getText()));
        TransferPage transferOut = trx.enterTransferOutPage();
        int availableBalance = Integer.parseInt(removeSymbol(transferOut.availableBalance_text.getText().split(" ")[1]));
        Assert.assertTrue(trxCount ==  availableBalance);

    }

    @Test(description = "Check transferOut Trc10 Count",alwaysRun = true)
    public void test005_checkTransferOutTrx() throws Exception {
        TrxPage trx = enterTrxPage();
        TransferPage transferOut = trx.enterTransferOutPage();
        transferOut.inputAndTapToTransferOut();
        Assert.assertTrue(transferOut.trxLabel.getText().contains("10"));

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
        Double confirmNumber = Double.parseDouble(removeSymbolNoDot(outPage.trxLabel.getText().split(" ")[0]));
        log("confirmNumber:"+ confirmNumber.toString() + " textNumber:" + textNumber);
        Assert.assertTrue(confirmNumber.equals(textNumber) );
        Double feeNumber = Double.parseDouble(removeSymbolNoDot(outPage.bandwidthText.getText().split(" ")[0]));
        log("feeNumber:"+ feeNumber.toString());
        Assert.assertTrue(feeNumber > 0 );
        Assert.assertTrue(outPage.titleLabel.getText().contains("确认转出"));
        Assert.assertTrue(Helper.isElementExist(outPage.driver,"转出需要执行智能合约，智能合约同时可能会消耗能量。"));
        Assert.assertTrue( outPage.comfirm_btn().isEnabled());
        outPage.comfirm_btn().click();
        outPage.password_input.click();
        outPage.password_input.sendKeys("balabala");
        Helper.tapWhitePlace(outPage.driver);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue( outPage.get_finish_btn().isEnabled());

    }



    @Test(description = "input min send number",alwaysRun = true)
    public void test007_inputMinSendNumber() throws Exception {
        TrxPage trx = enterTrxPage();
        trx.enterTransferOutPage();
        waiteTime();
//        trx.textField.click();
        trx.textField.sendKeys("0");
        Helper.closeKeyBoard(trx.driver);
        Assert.assertTrue( trx.amountErrorLabel.getText().contains("转账金额需大于 0"));

    }


    @Test(description = "Check OutNumberInRecord WithDraw trx10",alwaysRun = true)
    public void test008_checkOutNumberInRecordWithDrawTrx10() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx10Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterWithDrawNumberRecordPage(findString));

    }



}
