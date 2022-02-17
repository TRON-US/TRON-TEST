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
        if(Helper.assetFindMainChain(asset)) {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
        }
        return asset.enterSendTrxPage();
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

    public void guaranteeDappChain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.assetFindMainChain(asset)) {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            mine.enterAssetPage();
        }

    }

    @Test(description = "SendTrc10 success test",alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {
        guaranteeDappChain();
        AssetPage assetpage = new AssetPage(DRIVER);
        TrxPage tokenpage = assetpage.enterTrx10Page();
        double trc10Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));

        SendTrxPage transfer = tokenpage.enterTransferPage();
        String count = random(10, 10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        transfer.sendTrx10WithNumber(successNumber);
        TimeUnit.SECONDS.sleep(2);

        double trc10after = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println("   count:" +count + "   trc10Before:" + trc10Before + " trc10after:" + trc10after);
        //TODO ： 刷新页面
        Assert.assertTrue(trc10after + Integer.parseInt(removeSymbol(count)) <= trc10Before);

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
