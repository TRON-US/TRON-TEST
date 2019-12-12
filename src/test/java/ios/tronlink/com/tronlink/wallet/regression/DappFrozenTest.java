package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DappFrozenTest extends BaseTest {



    //enter TRXPage  DappChain
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.fastFindMainChain(asset.textArray)){
            return asset.enterTrxPage();
        }else{
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrxPage();
        }
    }


    public FrozenAndUnfreezePage interferonPage(){
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterFrozenAndThawingPage();
    }

//    @Test(description = "guarantee Chain in Dappchain",alwaysRun = true)
//    public void test000_GuaranteeChainName() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferOut = trx.enterTransferOutPage();
//        String chain = transferOut.chain_text.getText();
//        Assert.assertTrue(chain.contains("MainChain"));
//    }


    @Test(description = "enter Details of the rules", alwaysRun = true)
    public void test001_enterDetailsOfTheRules() throws Exception {
        Helper.guaranteeMainChain(DRIVER);
        FrozenAndUnfreezePage frozen = interferonPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(Helper.isElementExist(detailsAndRules.driver,"TRX冻结解冻细则"));
    }

    @Test(description = "Freeze energy detail", alwaysRun = true)
    public void test002_FreezeEnergyDetail() {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.freezeEnergyDetail_btn.get(0).click();

        int myFreeze = Integer.parseInt(removeSymbol(frozen.myFreeze_btn.get(0).getText().split(" ")[0]));
        int otherFreeze = Integer.parseInt(removeSymbol(frozen.otherFreeze_btn.get(0).getText().split(" ")[0]));
        int totalFreeze = Integer.parseInt(removeSymbol(frozen.totalFreeze_btn.get(0).getText().split(" ")[0]));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }

    @Test(description = "Bandwidth Detail detail", alwaysRun = true)
    public void test003_BandwidthDetail() {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.freezeEnergyDetail_btn.get(1).click();
        int myBandwidth = Integer.parseInt(removeSymbol(frozen.myFreeze_btn.get(1).getText().split(" ")[0]));
        int otherBandwidth = Integer.parseInt(removeSymbol(frozen.otherFreeze_btn.get(1).getText().split(" ")[0]));
        int totalBandwidth = Integer.parseInt(removeSymbol(frozen.totalFreeze_btn.get(1).getText().split(" ")[0]));
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }

    @Test(description = "Energy Question Test", alwaysRun = true)
    public void test004_checkEnergyQuestion() {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Energy"));
    }

    @Test(description = "Bandwidth Question Test", alwaysRun = true)
    public void test005_checkBandwidthQuestion() {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.getbandwidth_btn().click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }
    @Test(description = "ChangeFreezeUnfreeze Test", alwaysRun = true)
    public void test006_checkBandwidthQuestion() {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.getDirectionFzUfz_btn().click();
        boolean textLabel = Helper.isElementExist(frozen.driver,"解冻") && Helper.isElementExist(frozen.driver,"冻结");
        Assert.assertTrue(textLabel);
    }


    //Freeze Energy more than trx
    @Test(description = "Freeze Energy more than trx", alwaysRun = true)
    public void test008_freezeEnergyMoreThanTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("99999999");
        TimeUnit.SECONDS.sleep(3);
        if (Helper.isElementExist(frozen.driver,"incorrectLabel")){
            String prompt = frozen.error_hits.getText();
            System.out.println(prompt);
            Assert.assertTrue(prompt.contains("TRX") && prompt.contains("不足")|| prompt.equals("Insufficient TRX"));
        }else {
            Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
        }
    }


   // Freeze Change to Unfreeze
    @Test(description = "Freeze Change to Unfreeze", alwaysRun = true)
    public void test010_freezeEnergyChangetoUnfreeze()  {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.getDirectionFzUfz_btn().click();
        frozen.unfreeze_btn.click();
        if(Helper.isElementExist(frozen.driver,"预计可得")){
            Assert.assertFalse(frozen.driver.findElementByName("预计可得").isDisplayed());
        }else {
            Assert.assertTrue(Helper.isElementExist(frozen.driver,"可解冻时间"));
        }
    }

    //Freeze Energy equals trx
    @Test(description = "Freeze Energy equals trx", alwaysRun = true)
    public void test011_freezeEnergyEqualTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        String availableTrx = frozen.getAvailableTrx();
        frozen.inputFrozenCount(removeSymbol(availableTrx));
        Assert.assertTrue(frozen.getFreeze_btn().isEnabled());
    }


    //Freeze Energy with 0 trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test012_freezeEnergyZeroTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("0");
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
    }


    //Freeze Energy with null trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test013_freezeEnergyNullTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("");
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
  }

//    @Test(description = "freeze Energy with Error Receiving Address", alwaysRun = true)
//    public void test014_freezeEnergyErrorReceivingAddress() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.inputFrozenCount("1");
//        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
//        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
////        String prompt = frozen.errorAddress_hits.getText();
////        Assert.assertTrue(prompt.contains("地址格式不正确") || prompt.contains("address format is incorrect"));
//    }
//
//
//    @Test(description = "freeze Energy with not active Receiving Address", alwaysRun = true)
//    public void test015_freezeEnergyNotActiveReceivingAddress() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.inputFrozenCount("1");
//        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
//        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
//
////        String prompt = frozen.errorAddress_hits.getText();
////        Assert.assertTrue(prompt.contains("未在TRON网络上激活") || prompt.contains("has not been activated"));
//    }

    /**
    freeze Energy
    */
    @Test(description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test016_freezeEnergySuccess() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        String availableTrxOld = frozen.getAvailableTrx();
        frozen.inputFrozenCount("1");
        Helper.tapWhitePlace(frozen.driver);
        frozen.frozenTheEnergy(); //Freeze operating
        TimeUnit.SECONDS.sleep(1);
        String availableTrxNew = frozen.getAvailableTrx();
        Assert.assertTrue(Double.parseDouble(availableTrxNew) + 1 == Double.parseDouble(availableTrxOld));

    }
    /**
     * freeze Bandwidth
     */
    @Test(description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test017_freezeBandwidthSuccess() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.getbandwidth_btn().click();
        String availableTrxOld = frozen.getAvailableTrx();
        frozen.inputFrozenCount("1");
        Helper.tapWhitePlace(frozen.driver);
        frozen.frozenTheEnergy(); //Freeze operating
        TimeUnit.SECONDS.sleep(1);
        String availableTrxNew = frozen.getAvailableTrx();
        Assert.assertTrue(Double.parseDouble(availableTrxNew) + 1 == Double.parseDouble(availableTrxOld));
    }
}
