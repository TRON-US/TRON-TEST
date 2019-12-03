package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DetailsAndRulesPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

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
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(description = "enter Details of the rules", alwaysRun = true)
    public void test001_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        String trxCount = asset.getTrxCount();
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(detailsAndRules.detailsAndRules_title.isDisplayed());
    }


    @Test(description = "Freeze energy detail", alwaysRun = true)
    public void test002_FreezeEnergyDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.freezeEnergyDetail_btn.click();
        int myFreeze = Integer.valueOf(removeSymbol(frozen.myFreeze_btn.getText()));
        int otherFreeze = Integer.valueOf(removeSymbol(frozen.otherFreeze_btn.getText()));
        int totalFreeze = Integer.valueOf(removeSymbol(frozen.totalFreeze_btn.getText()));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }


    @Test(description = "Bandwidth Detail detail", alwaysRun = true)
    public void test003_BandwidthDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.freezeBandwidthDetail_btn.click();
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int otherBandwidth = Integer.valueOf(removeSymbol(frozen.otherFreezeBandwidth_btn.getText()));
        int totalBandwidth = Integer.valueOf(removeSymbol(frozen.totalFreezeBandwidth_btn.getText()));
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }


    @Test(description = "Vote power Test", alwaysRun = true)
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


    @Test(description = "Energy Question Test", alwaysRun = true)
    public void test005_checkEnergyQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Energy"));
    }


    @Test(description = "Bandwidth Question Test", alwaysRun = true)
    public void test006_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }


    @Test(description = "Bandwidth Question Test", alwaysRun = true)
    public void test007_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.bandwidth_btn.click();
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("Bandwidth"));
    }

    //Balance in frozen mainPage equal
    @Test(description = "count remaining and voting equal trx", alwaysRun = true)
    public void test008_countRemainingAndVotingEqualTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        int currentCanUseTrx = Integer.valueOf(removeSymbol(frozen.getCurrentCanUseTrx()));
        System.out.println(trxCount + "......" + myVotingPower + "....." + currentCanUseTrx);
        Assert.assertTrue(myVotingPower + currentCanUseTrx == trxCount);
    }


    /**
     * Freeze Energy
     */
    @Test(description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test009_freezeEnergySuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }


    //Freeze Energy more than trx
    @Test(description = "Freeze Energy more than trx", alwaysRun = true)
    public void test010_freezeEnergyMoreThanTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("99999999");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.equals("可用TRX不足") || prompt.equals("Insufficient TRX"));
    }


    //Freeze Energy equals trx
    @Test(description = "Freeze Energy more than trx", alwaysRun = true)
    public void test011_freezeEnergyMoreThanTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        String availableTrx = frozen.getAvailableTrx();
        frozen.inputFrozenCount(removeSymbol(availableTrx));
        Assert.assertTrue(frozen.freezeNow_btn.isDisplayed());
    }


    //Freeze Energy with 0 trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test012_freezeEnergyZeroTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("0");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }


    //Freeze Energy with null trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test013_freezeEnergyNullTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputFrozenCount("");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }


    @Test(description = "freeze Energy with Error Receiving Address", alwaysRun = true)
    public void test014_freezeEnergyErrorReceivingAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("地址格式不正确") || prompt.contains("address format is incorrect"));
    }


    @Test(description = "freeze Energy with not active Receiving Address", alwaysRun = true)
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
    @Test(description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test016_freezeBandwidthSuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        frozen.bandwidth_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }


}
