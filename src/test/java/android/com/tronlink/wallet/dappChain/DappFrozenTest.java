package android.com.tronlink.wallet.dappChain;

import android.com.utils.Helper;
import android.com.wallet.pages.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;

public class DappFrozenTest extends Base {
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //reset DAPP chain trun main chain
        //changeToMainChain();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        setToDAppChain();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    //reset app turn to MainChain
    public void changeToMainChain() {
        try {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            nodeSet.enterSettingPageChoiseMainChain();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }
    }


    //enter TRXPage
    public FrozenAndUnfreezePage enterFreezePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterFrozenAndUnfreezePage();
    }

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    /**
     * freeze Bandwidth
     */
    @Test(enabled = true,description = "Dapp chain freeze bandwidth Success", alwaysRun = true)
    public void test0001_freezeBandwidthSuccess() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.bandwidth_btn.click();
        frozen.et_amount.sendKeys("1");
        frozen.frozenTheBandwidth(); //Freeze operating
    }

    /**
     * freeze Bandwidth
     */
    @Test(enabled = true,description = "Dapp chain freeze energy success", alwaysRun = true)
    public void test0002_freezeEnergySuccess() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.energy_btn.click();
        frozen.et_amount.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
    }

    @Test(enabled = true,description = "Dapp chain freeze page details check", alwaysRun = true)
    public void test0003_enterDetailsOfTheRules() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(detailsAndRules.detailsAndRules_title.isDisplayed());
    }

    @Test(enabled = true,description = "Dapp chain freeze energy detail check", alwaysRun = true)
    public void test0004_FreezeEnergyDetail() throws Exception{
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.freezeEnergyDetail_btn.click();
        int myFreeze = Integer.valueOf(removeSymbol(frozen.myFreeze_btn.getText()));
        int otherFreeze = Integer.valueOf(removeSymbol(frozen.otherFreeze_btn.getText()));
        int totalFreeze = Integer.valueOf(removeSymbol(frozen.totalFreeze_btn.getText()));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
    }

    @Test(enabled = true,description = "Dapp chain freeze bandwidth detail check", alwaysRun = true)
    public void test0005_BandwidthDetail() throws Exception{
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.freezeBandwidthDetail_btn.click();
        int myBandwidth = Integer.valueOf(removeSymbol(frozen.myFreezeBandwidth_btn.getText()));
        int otherBandwidth = Integer.valueOf(removeSymbol(frozen.otherFreezeBandwidth_btn.getText()));
        int totalBandwidth = Integer.valueOf(removeSymbol(frozen.totalFreezeBandwidth_btn.getText()));
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);
    }

    @Test(enabled = true,description = "Dapp chain energy question Test", alwaysRun = true)
    public void test0006_checkEnergyQuestion() throws Exception{
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.energy_btn.click();
        Helper.swipScreenLitte(frozen.driver);
        frozen.questionClick();
        String questionContent = frozen.questionContent_btn.getText();
        Assert.assertTrue(questionContent.contains("Energy") || questionContent.contains("能量"));
    }

    @Test(enabled = true,description = "Dapp chain bandwidth question Test", alwaysRun = true)
    public void test0007_checkBandwidthQuestion() throws Exception{
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.bandwidth_btn.click();
        Helper.swipScreenLitte(frozen.driver);
        frozen.questionClick();
        String questionContent = frozen.questionContent_btn.getText();
        Assert.assertTrue(questionContent.contains("Bandwidth") || questionContent.contains("带宽"));
    }

    //Freeze Energy more than trx
    @Test(enabled = true,description = "Dapp chain freeze energy more than trx", alwaysRun = true)
    public void test0008_freezeEnergyMoreThanTrx() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.inputFrozenCount("99999999");
        String prompt = frozen.error_hits.getText();
        System.out.println("prompt:" + prompt);
        Assert.assertTrue(prompt.equals("TRX不足") || prompt.equals("Insufficient TRX") || prompt.equals("可用 TRX 不足"));
    }


    //Freeze Energy equals trx
    @Test(enabled = true,description = "Dapp chain freeze energy with all balance", alwaysRun = true)
    public void test0009_freezeEnergyMoreThanTrx() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        String availableTrx = frozen.getAvailableTrx();
        frozen.inputFrozenCount(removeSymbol(availableTrx));
        Assert.assertTrue(frozen.bt_send.isDisplayed());
    }


    //Freeze Energy with 0 trx
    @Test(enabled = true,description = "Dapp chain freeze energy with zero trx", alwaysRun = true)
    public void test0010_freezeEnergyZeroTrx() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.inputFrozenCount("0");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小质押数量为") || prompt.contains("Minimum freeze is"));
    }


    //Freeze Energy with null trx
    @Test(enabled = true,description = "Dapp chain freeze energy with empty input", alwaysRun = true)
    public void test0011_freezeEnergyNullTrx() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.inputFrozenCount("");
        String prompt = frozen.error_hits.getText();
        Assert.assertTrue(prompt.contains("最小质押数量为") || prompt.contains("Minimum freeze is"));
    }


    @Test(enabled = true,description = "Dapp chain freeze Energy with Error Receiving Address", alwaysRun = true)
    public void test0012_freezeEnergyErrorReceivingAddress() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("地址格式不正确") || prompt.contains("address format is incorrect"));
    }


    @Test(enabled = true,description = "Dapp chain freeze energy with non-active receiver Address", alwaysRun = true)
    public void test0013_freezeEnergyNotActiveReceivingAddress() throws Exception {
        FrozenAndUnfreezePage frozen = enterFreezePage();
        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
        String prompt = frozen.errorAddress_hits.getText();
        Assert.assertTrue(prompt.contains("请重新填写接收地址") || prompt.contains("has not been activated"));
    }

    @Test(enabled = true, description = "Dapp chain freeze transaction record test", alwaysRun = true)
    public void test0014_dappChainFreezeTransactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionRecords.get(0).findElement(By.id("com.tronlinkpro.wallet:id/tv_contract_title")).getText();
        String resourceType = transaction.transactionRecords.get(0).findElement(By.id("com.tronlinkpro.wallet:id/tv_two")).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.equals("质押资产") || transactionType.equals("Freeze Asset"));
        Assert.assertTrue(resourceType.contains("ENERGY"));
    }

}
