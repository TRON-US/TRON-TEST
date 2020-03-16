package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DappSendTrc10 extends BaseTest {
    String successNumber;

    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


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
            return asset.enterTrxPage();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrxPage();
        }

    }
    //enter Dapp AssetPage
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



    @Test(description = "SendTrc10 success test",alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        String count = random(10,10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        TrxPage tokenpage = transfer.sendTrx10WithNumber(successNumber);
        double trc10Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        transfer.back_bt.click();//返回到首页资产页
        waiteTime();
        AssetPage assetpage = new AssetPage(DRIVER);
        tokenpage = assetpage.enterTrx10Page();
        double trc10after = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println(count   + "   " + trc10Before  + " " + trc10after);
        Assert.assertTrue(trc10after + Integer.parseInt(removeSymbol(count)) <= trc10Before);
    }



    @Test(description = "input max send number",alwaysRun = true)
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("max");
        transfer.transferNow_btn.click();
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
    }


    @Test(description = "input mix send number",alwaysRun = true)
    public void test003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("mix");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("格式错误"));
    }


    @Test(description = "input too Much trc10 send number",alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("tooMuch");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("余额不足"));
    }

    @Test(description = "ssendaddressChanged test",alwaysRun = true)
    public void test005_sendaddressChanged() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(0).sendKeys(" ");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(transfer.transferErrorLabel.getText().contains("地址格式不正确"));
    }

    @Test(description = "Check OutNumberInRecord Trx10", alwaysRun = true)
    public void test006_CheckOutNumberInRecordTrx10() throws Exception {
        log("successNumber:"+successNumber);
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx10Page();
        String findString = "-" + successNumber;
        log(findString);
        Assert.assertTrue(page.enterNumberRecordPage(findString));

    }
}
