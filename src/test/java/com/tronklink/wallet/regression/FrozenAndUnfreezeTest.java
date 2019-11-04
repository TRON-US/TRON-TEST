package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.DetailsAndRulesPage;
import wallet.pages.FrozenAndUnfreezePage;
import org.testng.annotations.*;
/**
 * Frozen page function test
 */
public class FrozenAndUnfreezeTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }




    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "enter Details of the rules")
    public void test001_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        String trxCount = asset.getTrxCount();
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(detailsAndRules.detailsAndRules_title.isDisplayed());
    }


    @Test(description = "Freeze energy detail")
    public void test002_FreezeEnergyDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen =  asset.enterFrozenAndThawingPage();
        frozen.freezeEnergyDetail_btn.click();
        int myFreeze = Integer.valueOf(frozen.myFreeze_btn.getText());
        int otherFreeze = Integer.valueOf(removeSymbol(frozen.otherFreeze_btn.getText()));
        int totalFreeze = Integer.valueOf(removeSymbol(frozen.totalFreeze_btn.getText()));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }


    @Test(description = "Bandwidth Detail detail")
    public void test003_BandwidthDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen =  asset.enterFrozenAndThawingPage();
        frozen.freezeBandwidthDetail_btn.click();
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int otherBandwidth = Integer.valueOf(removeSymbol(frozen.otherFreezeBandwidth_btn.getText()));
        int totalBandwidth = Integer.valueOf(removeSymbol(frozen.totalFreezeBandwidth_btn.getText()));
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }


    @Test(description = "Vote power Test")
    public void test004_checkVotingPower() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.freezeEnergyDetail_btn.click();
        frozen.freezeBandwidthDetail_btn.click();
        int myFreeze = Integer.valueOf(removeSymbol(frozen.myFreeze_btn.getText()));
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myFreeze + myBandwidth == myVotingPower);
    }


    @Test(description = "Energy Question Test")
    public void test005_checkEnergyQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Energy"));
    }


    @Test(description = "Bandwidth Question Test")
    public void test006_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }


    @Test(description = "Bandwidth Question Test")
    public void test007_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }

    //Balance in frozen mainPage equal
    @Test(description = "count remaining and voting equal trx")
    public void test008_countRemainingAndVotingEqualTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        int currentCanUseTrx = Integer.valueOf(removeSymbol(frozen.getCurrentCanUseTrx()));
        System.out.println(trxCount+"......"+myVotingPower+"....."+ currentCanUseTrx);
        Assert.assertTrue(myVotingPower + currentCanUseTrx == trxCount);
    }


    /**
     * Freeze Energy
     */
    @Test(description = "Freeze Energy Scuuess")
    public void test009_freezeEnergySuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }


    //Freeze Energy more than trx
    @Test(description = "Freeze Energy more than trx")
    public void test010_freezeEnergyMoreThanTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("99999999");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.equals("可用TRX不足") || prompt.equals("Insufficient TRX"));
    }


    //Freeze Energy equals trx
    @Test(description = "Freeze Energy more than trx")
    public void test011_freezeEnergyMoreThanTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        String availableTrx = frozen.getAvailableTrx();
        frozen.inputFrozenCount(removeSymbol(availableTrx));
        Assert.assertTrue(frozen.freezeNow_btn.isDisplayed());
    }


    //Freeze Energy with 0 trx
    @Test(description = "Freeze Energy with zero trx")
    public void test012_freezeEnergyZeroTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("0");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }




    //Freeze Energy with null trx
    @Test(description = "Freeze Energy with zero trx")
    public void test013_freezeEnergyNullTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }



    @Test(description = "freeze Energy with Error Receiving Address")
    public void test014_freezeEnergyErrorReceivingAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("地址错误") || prompt.contains("Incorrect address"));
    }



    @Test(description = "freeze Energy with not active Receiving Address")
    public void test015_freezeEnergyNotActiveReceivingAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("请重新填写接收地址") || prompt.contains("has not been activated"));
    }



    /**
     * freeze Bandwidth
     */
    @Test(description = "Freeze Bandwidth Success")
    public void test016_freezeBandwidthSuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        frozen.bandwidth_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }






}
