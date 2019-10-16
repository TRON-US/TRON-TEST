package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.DetailsAndRulesPage;
import wallet.pages.FrozenAndThawingPage;
import org.testng.annotations.*;
/**
 * Frozen page function test
 */
public class FrozenAndThawingTest extends Base {


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
        Helper.getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

//    @AfterClass
//    public void tearDownAfterClass() {
//        DRIVER.quit();
//    }


    @Test(description = "enter Details of the rules")
    public void test001_enterDetailsOfTheRules() {
        AssetPage asset = new AssetPage(DRIVER);
        String trxCount = asset.getTrxCount();
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(detailsAndRules.detailsAndRules_title.isDisplayed());
    }


    @Test(description = "Freeze energy detail")
    public void test002_FreezeEnergyDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen =  asset.enterFrozenAndThawingPage();
        frozen.freezeEnergyDetail_btn.click();
        int myFreeze = Integer.valueOf(frozen.myFreeze_btn.getText());
        int otherFreeze = Integer.valueOf(frozen.otherFreeze_btn.getText());
        int totalFreeze = Integer.valueOf(frozen.totalFreeze_btn.getText());
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }


    @Test(description = "Bandwidth Detail detail",enabled = false)
    public void test003_BandwidthDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen =  asset.enterFrozenAndThawingPage();
        frozen.freezeBandwidthDetail_btn.click();
        int myBandwidth = Integer.valueOf(frozen.myFreezeBandwidth_btn.getText());
        int otherBandwidth = Integer.valueOf(frozen.otherFreezeBandwidth_btn.getText());
        int totalBandwidth = Integer.valueOf(frozen.totalFreezeBandwidth_btn.getText());
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }


    @Test(description = "Vote power Test")
    public void test004_checkVotingPower() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        frozen.freezeEnergyDetail_btn.click();
        frozen.freezeBandwidthDetail_btn.click();
        int myFreeze = Integer.valueOf(frozen.myFreeze_btn.getText());
        int myBandwidth = Integer.valueOf(frozen.myFreezeBandwidth_btn.getText());
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        Assert.assertTrue(myFreeze + myBandwidth == myVotingPower);
    }


    @Test(description = "Energy Question Test")
    public void test005_checkEnergyQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Energy"));
    }


    @Test(description = "Bandwidth Question Test")
    public void test006_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }


    @Test(description = "Bandwidth Question Test")
    public void test007_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }

    //Balance in frozen mainPage equal
    @Test(description = "count remaining and voting equal trx",enabled = false)
    public void test008_countRemainingAndVotingEqualTrx() {
        AssetPage asset = new AssetPage(DRIVER);
        int trxCount = Integer.valueOf(asset.getTrxCount());
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        int currentCanUseTrx = Integer.valueOf(frozen.getCurrentCanUseTrx());
        System.out.println(trxCount+"......"+myVotingPower+"....."+ currentCanUseTrx);
        Assert.assertTrue(myVotingPower + currentCanUseTrx == trxCount);
    }


    //Freeze Energy
    @Test(description = "Freeze Energy")
    public void test009_freezeEnergy() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndThawingPage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        int currentVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }






}
