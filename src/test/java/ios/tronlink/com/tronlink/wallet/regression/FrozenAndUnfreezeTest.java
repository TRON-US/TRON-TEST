package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FrozenAndUnfreezeTest extends BaseTest {


    public FrozenAndUnfreezePage interferonPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        return asset.enterFrozenAndThawingPage();
    }


    @Test(description = "enter Details of the rules", alwaysRun = true)
    public void test001_enterDetailsOfTheRules() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("质押解锁说明"));
        Assert.assertTrue(isElementExist("1. 质押 TRX 可以选择获得带宽或者能量中的一种，同时可以获得投票权，质押 1 TRX 可以获得 1 个投票权。"));
        Assert.assertTrue(isElementExist("我知道了"));
        detailsAndRules.iKnowButton.click();
        Assert.assertFalse(isElementExist("我知道了"));
    }

    @Test(description = "Freeze energy detail", alwaysRun = true)
    public void test002_3_FreezeEnergyDetail() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("预计可得"));
        Assert.assertTrue(isElementExist("下一步"));
        Assert.assertTrue(isElementExist("多重签名质押"));
        Assert.assertTrue(isElementExist("质押 TRX  (1/2)"));
        page.bandWidthTab().click();
        Assert.assertTrue(isElementExist("预计可得"));
        Assert.assertTrue(isElementExist("下一步"));
        Assert.assertTrue(isElementExist("多重签名质押"));
        Assert.assertTrue(isElementExist("质押 TRX  (1/2)"));
    }


    //Freeze Energy more than trx
//    @Test(description = "Freeze Energy more than trx", alwaysRun = true)
//    public void test004_freezeEnergyMoreThanTrx() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.inputFrozenCount("99999999");
//        Assert.assertTrue(isElementExist(" TRX 不足"));
//        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
//
//    }
//    //Freeze Energy with 0 trx
//    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
//    public void test005_freezeEnergyZeroTrx() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.inputFrozenCount("0");
//        Assert.assertTrue(isElementExist(" 最小质押数量为 1 TRX"));
//        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
//    }
//
//    //Freeze Energy equals trx
//    @Test(description = "Freeze Energy equals trx", alwaysRun = true)
//    public void test006_freeze100EqualTrx() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.click100();
//        Assert.assertTrue(frozen.getFreeze_btn().isEnabled());
//        //TODO: 能量变化，投票权变化
//    }

    //Freeze Energy with null trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test007_freezeEnergyNullTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("0");
        Assert.assertTrue(isElementExist(" 最小质押数量为 1 TRX"));
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
    }

    @Test(description = "freeze Energy with Error Receiving Address", alwaysRun = true)
    public void test008_freezeEnergyReceivingErrorAddress() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("1");
        frozen.getFreeze_btn().click();
        frozen.inputReceivingAddress("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMn");
        Assert.assertTrue(isElementExist(" 账户地址格式不正确，账户地址须以 T 开头，长度为 34 个字符"));
        Assert.assertFalse(frozen.confirmDeposit().isEnabled());
    }

    @Test(description = "freeze Energy with not active Receiving Address", alwaysRun = true)
    public void test009_freezeEnergyNotActiveReceivingAddress() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("1");
        frozen.getFreeze_btn().click();
        frozen.inputReceivingAddress("TWRjSKWxoDMetK4dhFeM763zGJZqu5oBxQ");
        Assert.assertTrue(isElementExist(" 该账户未在波场网络上激活，请重新填写"));
        Assert.assertFalse(frozen.confirmDeposit().isEnabled());
    }

//    /**
//     freeze Energy
//     */
//    @Test(groups = {"P0"},description = "Freeze Energy Scuuess", alwaysRun = true)
//    public void test010_freezeEnergySuccess() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.inputFrozenCount("1");
//        frozen.getFreeze_btn().click();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertFalse(frozen.confirmDeposit().isEnabled());
//        frozen.agreeClick();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertTrue(frozen.confirmDeposit().isEnabled());
//        frozen.confirmDeposit().click();
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(isElementExist("1 票"));
//        frozen.confirm_btn().click();
//        frozen.frozenInputPassword();
//        Assert.assertTrue(isElementExist("质押成功"));
//        Assert.assertTrue(isElementExist("去投票获取收益"));
//        Assert.assertTrue(isElementExist("1"));
//        Assert.assertTrue(frozen.getFinish_btn().isEnabled());
//        frozen.getFinish_btn().click();
//        Assert.assertTrue(isElementExist("我的资源"));
//    }
//
//    @Test(groups = {"P0"},description = "Freeze Energy Scuuess", alwaysRun = true)
//    public void test011_freezeBandWidthSuccess() throws Exception {
//        FrozenAndUnfreezePage frozen = interferonPage();
//        frozen.bandWidthTab().click();
//        frozen.inputFrozenCount("1");
//        frozen.getFreeze_btn().click();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertFalse(frozen.confirmDeposit().isEnabled());
//        frozen.agreeClick();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertTrue(frozen.confirmDeposit().isEnabled());
//        frozen.confirmDeposit().click();
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(isElementExist("1 票"));
//        frozen.confirm_btn().click();
//        frozen.frozenInputPassword();
//        Assert.assertTrue(isElementExist("质押成功"));
//        Assert.assertTrue(isElementExist("去投票获取收益"));
//        Assert.assertTrue(isElementExist("1"));
//        Assert.assertTrue(frozen.getFinish_btn().isEnabled());
//        frozen.getFinish_btn().click();
//        Assert.assertTrue(isElementExist("我的资源"));
//    }
/////
    @Test(groups = {"P0"},alwaysRun = true)
    public void test012_inputPercentTest() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        Double total = sepLeftNumberTextToDouble(frozen.tv_available_amount.getText().replace("可用: ",""),"TRX");
        frozen.amount_percent_25.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total/4.0,1.0);
        frozen.amount_percent_50.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total/2.0,1.0);
        frozen.amount_percent_75.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),3*total/4.0,1.0);
        frozen.amount_percent_100.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total,2.0);

    }

    @Test(alwaysRun = true)
    public void test013_inputEnergyAndBandWidthCountTest() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.selectEnergyTab();
        frozen.inputAmount("80");
        frozen.slideScreenBottom();
        Assert.assertTrue(isElementExist("80"));
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("80"));
    }


    @Test(alwaysRun = true)
    public void test014_inputEnergyTRXCountTest() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.selectEnergyTab();
        frozen.slideScreenBottom();
        frozen.enterEnergyBandWidth("20000");
        Assert.assertEquals(frozen.et_amount.getText(),"74");
        Assert.assertTrue(isElementExist("74"));
    }

    @Test(alwaysRun = true)
    public void test015_inputBandWidthTRXCountTest() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        frozen.enterEnergyBandWidth("345");
        Assert.assertEquals(frozen.et_amount.getText(),"70");
        Assert.assertTrue(isElementExist("70"));
    }

}
