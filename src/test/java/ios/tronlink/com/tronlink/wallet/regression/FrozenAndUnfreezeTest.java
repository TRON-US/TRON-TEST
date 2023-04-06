package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FrozenAndUnfreezeTest extends BaseTest {


    public FrozenAndUnfreezePage interferonPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterDepositPage();
        return page;

    }


    @Test(description = "enter Details of the rules", alwaysRun = true)
    public void test001_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        DetailsAndRulesPage detailsAndRules = frozen.enterDetailsAndRulesPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("质押机制已升级为质押 2.0"));
        Assert.assertTrue(isElementExist("1. 质押已升级全新 2.0 版本，支持随时解锁 TRX ；"));
        Assert.assertTrue(isElementExist("了解更多"));
        Assert.assertTrue(isElementExist("我知道了"));
        detailsAndRules.iKnowButton.click();
        Assert.assertFalse(isElementExist("我知道了"));
    }

    @Test(description = "FreezePageDetail", alwaysRun = true)
    public void test002_FreezePageDetail() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("多重签名"));
        Assert.assertTrue(isElementExist("了解质押 2.0"));
        page.enterDepositPage();
        Assert.assertTrue(isElementExist("StakeV2 WillGet Tip"));
        Assert.assertTrue(isElementExist("* 仅支持给自己质押，质押获得的资源可随时代理给他人"));
        Assert.assertTrue(isElementExist("质押"));
        page.willGetTip().click();
        Assert.assertTrue(isElementExist("质押 2.0 说明"));
    }

    @Test(description = "Freeze Energy", alwaysRun = true)
    public void test003_freezeEnergy() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterDepositPage();
        page.inputFrozenCount("1");
        Assert.assertTrue(page.getFreeze_btn().isEnabled());
        page.getFreeze_btn().click();
        page.unTillSomeThing("确认交易");
        Assert.assertTrue(isElementExist("质押 2.0"));
        page.confirmAndInputPassword();
        page.unTillSomeThing("完成");
        Assert.assertTrue(isElementExist("质押成功"));
        Assert.assertTrue(isElementExist("去投票获取收益"));
    }

    @Test(description = "test004_freezeBandWidth", alwaysRun = true)
    public void test004_freezeBandWidth() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterDepositPage();
        page.tv_stake_bandwidth.click();
        page.inputFrozenCount("1");
        Assert.assertTrue(page.getFreeze_btn().isEnabled());
        page.getFreeze_btn().click();
        page.unTillSomeThing("确认交易");
        Assert.assertTrue(isElementExist("质押 2.0"));
        page.confirmAndInputPassword();
        page.unTillSomeThing("完成");
        Assert.assertTrue(isElementExist("质押成功"));
        Assert.assertTrue(isElementExist("去投票获取收益"));
    }

    //Freeze Energy with null trx
    @Test(description = "Freeze Energy with zero trx", alwaysRun = true)
    public void test005_freezeEnergyNullTrx() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.inputFrozenCount("0");
        Assert.assertTrue(isElementExist(" 最小质押数量为 1 TRX"));
        Assert.assertFalse(frozen.getFreeze_btn().isEnabled());
    }


    @Test(groups = {"P0"},alwaysRun = true)
    public void test006_inputPercentTest() throws Exception {
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
    public void test007_inputEnergyAndBandWidthCountTest() throws Exception {
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
    public void test008_inputBandWidthTRXCountTest() throws Exception {
        FrozenAndUnfreezePage frozen = interferonPage();
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        frozen.enterEnergyBandWidth("340");
        Assert.assertEquals(frozen.et_amount.getText(),"70");
        Assert.assertTrue(isElementExist("70"));
    }

    @Test(alwaysRun = true)
    public void test009_unFreezeTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterUnFreezePage();
        if(isElementExist("选择您要解锁的 TRX ")){
            page.getUnFreeze20().click();
        }
        Assert.assertTrue(isElementExist("* 解除 TRX 需要 1 天的等待期，解除质押的资产在 1 天后可取出"));
        if(isElementExist("0 TRX")){
            Assert.assertTrue(isElementExist("输入解锁数量"));
        }else {
            page.inputText("1");
            page.unFreezeAndInputPassword();
            Assert.assertTrue(isElementExist("解锁成功"));
        }
    }

    @Test(alwaysRun = true)
    public void test010_enterVotePageTest()throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("投票"));
        Assert.assertTrue(isElementExist("剩余投票权"));
        Assert.assertTrue(isElementExist("超级代表"));
    }

    @Test(alwaysRun = true)
    public void test011_enterResourcePageTest()throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterResourcePage();
        Assert.assertTrue(isElementExist("资源管理"));
        Assert.assertTrue(isElementExist("多重签名"));
    }


}
