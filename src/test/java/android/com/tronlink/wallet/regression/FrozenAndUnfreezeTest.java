package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DetailsAndRulesPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;

import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.TransactionRecordPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import android.com.utils.Configuration;
import org.testng.annotations.AfterClass;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


/**
 * Frozen page function test
 */
public class FrozenAndUnfreezeTest extends Base {

    private String password = "Test0001";
    private String otherAddress = "TLVh1FcdR57fukbFZL7DgjYY6VW9WRdNzt";

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e) {
        }
    }

    @AfterClass(groups = {"P0"},alwaysRun = true)
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
    public void test001_freezeEnergySuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterFrozen();
        frozen.frozenTheEnergy(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        frozen.btn_confirm.click();
        frozen.inputPassword(password);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
        Assert.assertTrue(frozen.tv_right_first.getText().contains("能量"));

    }

    /**
     * freeze Bandwidth
     */
    @Test(enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test002_freezeBandwidthSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterFrozen();
        frozen.frozenTheBandwidth(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        frozen.btn_confirm.click();
        frozen.inputPassword(password);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
        Assert.assertTrue(frozen.tv_right_first.getText().contains("带宽"));

    }


    @Test(enabled = true, description = "Freeze Details of the rules", alwaysRun = true)
    public void test003_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(frozen.title.getText().contains("质押机制已升级为质押 2.0"));
        frozen.btn_cancel.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("btn_cancel"));
    }

    @Test(alwaysRun = true)
    public void test004_multiSignGuideTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.interMultiSignGuidePage();
        Assert.assertTrue(isElementShotId("iv_refresh"));
    }

    @Test(alwaysRun = true)
    public void test005_multiSignListTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterMultiSign();
        frozen.iv_tip2.click();
        Assert.assertTrue(frozen.tv_content.getText().contains("您拥有控制权限的多签账户"));
    }

     @Test(alwaysRun = true)
     public void test006_unFreezeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.toUnfreezePage();
        Assert.assertTrue(frozen.tv_common_title.getText().contains("解锁 TRX"));
     }


    @Test(groups = {"P0"},alwaysRun = true)
    public void test007_inputPercentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterFrozen();
        Double total = sepLeftNumberTextToDouble(frozen.tv_available_amount.getText(),"TRX");
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
    public void test008_inputEnergyAndBandWidthCountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterFrozen();
        frozen.selectEnergyTab();
        frozen.inputAmount("200");
        frozen.slideScreenBottom();
        Assert.assertEquals(frozen.tv_resource_per_transaction.getText(),"约可以支付 1 笔转账的能量消耗");
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"200");
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"200");
    }



    @Test(alwaysRun = true)
    public void test009_inputBandWidthTRXCountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterFrozen();
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        frozen.enterEnergyBandWidth("345");
        Assert.assertEquals(frozen.et_amount.getText(),"71");
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"71");
    }

}
