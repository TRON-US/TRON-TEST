package android.com.tronlink.wallet.manualTest;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DetailsAndRulesPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.TransactionRecordPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Frozen page function test
 */
public class FrozenAndUnfreezeTest extends Base {

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

    /**
     * Freeze Energy
     */
    @Test(enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test0001_freezeEnergySuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        frozen.energy_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndUnfreezePage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }


    /**
     * freeze Bandwidth
     */
    @Test(enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test0002_freezeBandwidthSuccess() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        //frozen.bandwidth_btn.click();
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndUnfreezePage();
        int currentVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }


    @Test(enabled = true, description = "Freeze Details of the rules", alwaysRun = true)
    public void test0003_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        String trxCount = asset.getTrxCount();
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(detailsAndRules.detailsAndRules_title.isDisplayed());
    }


    @Test(enabled = true, description = "Freeze energy detail", alwaysRun = true)
    public void test0004_FreezeEnergyDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.freezeEnergyDetail_btn.click();
        int myFreeze = Integer.valueOf(removeSymbol(frozen.myFreeze_btn.getText()));
        int otherFreeze = Integer.valueOf(removeSymbol(frozen.otherFreeze_btn.getText()));
        int totalFreeze = Integer.valueOf(removeSymbol(frozen.totalFreeze_btn.getText()));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }


    @Test(enabled = true, description = "Freeze bandwidth detail", alwaysRun = true)
    public void test0005_BandwidthDetail() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.freezeBandwidthDetail_btn.click();
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int otherBandwidth = Integer.valueOf(removeSymbol(frozen.otherFreezeBandwidth_btn.getText()));
        int totalBandwidth = Integer.valueOf(removeSymbol(frozen.totalFreezeBandwidth_btn.getText()));
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }


    @Test(enabled = true, description = "Vote power Test", alwaysRun = true)
    public void test0006_checkVotingPower() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.freezeEnergyDetail_btn.click();
        frozen.freezeBandwidthDetail_btn.click();
        int myFreeze = Integer.valueOf(removeSymbol(frozen.myFreeze_btn.getText()));
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        Assert.assertTrue(myFreeze + myBandwidth == myVotingPower);
    }

    @Test(enabled = true, description = "Energy Question Test", alwaysRun = true)
    public void test0007_checkEnergyQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.energy_btn.click();
        frozen.questionClick();
        String questionContent = frozen.questionContent_btn.getText();
        Assert.assertTrue(questionContent.contains("Energy") || questionContent.contains("能量"));
    }

    @Test(enabled = true, description = "Bandwidth Question Test", alwaysRun = true)
    public void test0008_checkBandwidthQuestion() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        //frozen.bandwidth_btn.click();
        frozen.questionClick();
        String questionContent = frozen.questionContent_btn.getText();
        Assert.assertTrue(questionContent.contains("Bandwidth") || questionContent.contains("带宽"));
    }

    //Balance in frozen mainPage equal
    @Test(enabled = true, description = "Count remaining and voting equal trx", alwaysRun = true)
    public void test0009_countRemainingAndVotingEqualTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        int myVotingPower = Integer.valueOf(removeSymbol(frozen.votingPower_btn.getText()));
        int currentCanUseTrx = Integer.valueOf(removeSymbol(frozen.getCurrentCanUseTrx()));
        System.out.println(trxCount + "......" + myVotingPower + "....." + currentCanUseTrx);
        Assert.assertTrue(myVotingPower + currentCanUseTrx == trxCount);
    }


    //Freeze Energy more than trx
    @Test(enabled = true, description = "Freeze Energy more than trx", alwaysRun = true)
    public void test0010_freezeEnergyMoreThanTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.inputFrozenCount("99999999");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.equals("可用 TRX 不足") || prompt.equals("Insufficient TRX"));
    }


    //Freeze Energy equals trx
    @Test(enabled = true, description = "Freeze Energy equal available trx balance", alwaysRun = true)
    public void test0011_freezeEnergyEqualBalance() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        String availableTrx = frozen.getAvailableTrx();
        frozen.inputFrozenCount(removeSymbol(availableTrx));
        Assert.assertTrue(frozen.freezeNow_btn.isDisplayed());
    }


    //Freeze Energy with 0 trx
    @Test(enabled = true, description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test0012_freezeEnergyZeroTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.inputFrozenCount("0");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }


    //Freeze Energy with null trx
    @Test(enabled = true, description = "Freeze Energy with empty input", alwaysRun = true)
    public void test0013_freezeEnergyNullTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.inputFrozenCount("");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小冻结数量为") || prompt.contains("Minimum freeze is"));
    }


    @Test(enabled = true, description = "freeze Energy with Error Receiving Address", alwaysRun = true)
    public void test0014_freezeEnergyErrorReceivingAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("地址格式不正确") || prompt.contains("address format is incorrect"));
    }


    @Test(enabled = true, description = "freeze Energy with not active Receiving Address", alwaysRun = true)
    public void test0015_freezeEnergyNotActiveReceivingAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("请重新填写接收地址") || prompt.contains("has not been activated"));
    }
    
    @Test(enabled = true, description = "Freeze transaction record test", alwaysRun = true)
    public void test0016_transactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionRecords.get(0).findElement(By.id("com.tronlink.wallet:id/tv_contract_title")).getText();
        String resourceType = transaction.transactionRecords.get(0).findElement(By.id("com.tronlink.wallet:id/tv_two")).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.equals("冻结资产") || transactionType.equals("Freeze Asset"));
        Assert.assertTrue(resourceType.contains("BANDWIDTH"));
    }
}
