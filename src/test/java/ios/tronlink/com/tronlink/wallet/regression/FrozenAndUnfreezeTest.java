package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FrozenAndUnfreezeTest extends BaseTest {


    public FrozenAndUnfreezePage interferonPage(){
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterFrozenAndThawingPage();
    }


    @Test(description = "enter Details of the rules", alwaysRun = true)
    public void test001_enterDetailsOfTheRules()  {
        FrozenAndUnfreezePage frozen = interferonPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(Helper.contentTexts(detailsAndRules.textArray,"质押解锁细则"));
    }

    @Test(description = "Freeze energy detail", alwaysRun = true)
    public void test002_FreezeEnergyDetail() {
        FrozenAndUnfreezePage frozen = interferonPage();
        waiteTime();
        frozen.energyFoldBtn.click();
        int myFreeze = Integer.parseInt(removeSymbol(frozen.freezenEnergyNumber.getText().split(" ")[0]));
        int otherFreeze = Integer.parseInt(removeSymbol(frozen.otherfreezenEnergyNumber.getText().split(" ")[0]));
        int totalFreeze = Integer.parseInt(removeSymbol(frozen.totalfreezenEnergyNumber.getText().split(" ")[0]));
        Assert.assertTrue(myFreeze + otherFreeze == totalFreeze);
        //ohter test Merge
        frozen.bandwidthFoldBtn.click();
        int myBandwidth = Integer.parseInt(removeSymbol(frozen.freezenbandwidthNumber.get(1).getText().split(" ")[0]));
        int otherBandwidth = Integer.parseInt(removeSymbol(frozen.otherfreezenbandwidthNumber.getText().split(" ")[0]));
        int totalBandwidth = Integer.parseInt(removeSymbol(frozen.totalfreezenbandwidthNumber.getText().split(" ")[0]));
        log("-----------");
        System.out.println(myBandwidth);
        System.out.println(otherBandwidth);
        System.out.println(totalBandwidth);
        log("-----------");
        Assert.assertTrue(myBandwidth + otherBandwidth == totalBandwidth);

    }


    @Test(description = "Energy Question Test", alwaysRun = true)
    public void test003_checkEnergyQuestion() {
        FrozenAndUnfreezePage frozen = interferonPage();
        Helper.swipScreen(frozen.driver);
        frozen.questionClick();
        Assert.assertTrue(frozen.questionContent_btn.getText().contains("获取能量"));
    }



    //Freeze Energy more than trx
    @Test(description = "Freeze Energy more than trx", alwaysRun = true)
    public void test004_freezeEnergyMoreThanTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("99999999");
        TimeUnit.SECONDS.sleep(3);
        Helper.swipScreen(frozen.driver);
        Assert.assertFalse(frozen.freeze_btn.isEnabled());

    }

    //Freeze Energy equals trx
    @Test(description = "Freeze Energy equals trx", alwaysRun = true)
    public void test011_freezeEnergyEqualTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        String availableTrx = frozen.getAvailableTrx();
        log(availableTrx);
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

    @Test(description = "freeze Energy with Error Receiving Address", alwaysRun = true)
    public void test014_freezeEnergyErrorReceivingAddress() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("1");
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());

    }


    @Test(description = "freeze Energy with not active Receiving Address", alwaysRun = true)
    public void test015_freezeEnergyNotActiveReceivingAddress() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("1");
        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());

    }

    /**
     freeze Energy
     */
    @Test(groups = {"P0"},description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test016_freezeEnergySuccess() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        String availableTrxOld = frozen.getAvailableTrx();
        frozen.inputFrozenCount("1");
        frozen.frozenTheEnergy(); //Freeze operating
        TimeUnit.SECONDS.sleep(3);
        String availableTrxNew = frozen.getAvailableTrx();
        availableTrxOld = availableTrxOld.replace(",","");
        availableTrxNew = availableTrxNew.replace(",","");
        log("availableTrxOld: "+availableTrxOld + "availableTrxNew: " + availableTrxNew);
        Assert.assertTrue(Double.parseDouble(availableTrxNew) + 1 == Double.parseDouble(availableTrxOld));

    }

}
