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

//    /**
//     * Freeze Energy
//     */
//    @Test(enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
//    public void test001_freezeEnergySuccess() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheEnergy(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.confirmTransferPage();
//        frozen.btn_confirm.click();
//        frozen.inputPassword(password);
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
//        Assert.assertTrue(frozen.tv_right_first.getText().contains("能量"));
//
//    }
//
//    /**
//     * freeze Bandwidth
//     */
//    @Test(enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
//    public void test002_freezeBandwidthSuccess() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheBandwidth(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.confirmTransferPage();
//        frozen.btn_confirm.click();
//        frozen.inputPassword(password);
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
//        Assert.assertTrue(frozen.tv_right_first.getText().contains("带宽"));
//
//    }
//
//
//    /**
//     * Freeze Energy Other
//     */
//    @Test(enabled = true,description = "Freeze Energy Success", alwaysRun = true)
//    public void test003_freezeEnergyOtherSuccess() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheEnergy(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.inputOtherAddress(otherAddress);
//        frozen.btn_confirm.click();
//        frozen.inputPassword(password);
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
//        Assert.assertTrue(frozen.tv_right_first.getText().contains("能量"));
//
//    }
//
//    /**
//     * freeze Bandwidth Other
//     */
//    @Test( enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
//    public void test004_freezeBandwidthOtherSuccess() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.frozenTheBandwidth(); //Freeze operating
//        frozen.et_amount.sendKeys("1");
//        frozen.inputOtherAddress(otherAddress);
//        frozen.btn_confirm.click();
//        frozen.inputPassword(password);
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
//        Assert.assertTrue(frozen.tv_right_first.getText().contains("带宽"));
//
//    }


    @Test(enabled = true, description = "Freeze Details of the rules", alwaysRun = true)
    public void test005_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(frozen.doc0_spe.getText().contains("1. 质押 TRX 可以选择获得带宽或者能量中的一种，同时可以获得投票权，质押 1 TRX 可以获得 1 个投票权。"));
        frozen.btn_know_it.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertFalse(isElementShotId("btn_know_it"));
    }

//    @Test(alwaysRun = true)
//    public void test006_multiSignGuideTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.interMultiSignGuidePage();
//        Assert.assertTrue(frozen.tv_web_title.getText().contains("如何使用多重签名质押 – 帮助中心"));
//    }


    @Test(alwaysRun = true)
    public void test007_multiSignListTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.tv_common_right2.click();
        frozen.iv_tip2.click();
        Assert.assertTrue(frozen.tv_content.getText().contains("您拥有控制权限的多签账户"));
    }

     @Test(alwaysRun = true)
     public void test008_unFreezeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.toUnfreezePage();
        Assert.assertTrue(frozen.tv_multi_sign.getText().contains("多重签名解锁"));
        Assert.assertTrue(frozen.error_view.getText().contains("解锁会减少您的资源和投票权，并使您当前的投票全部被取消"));
        Assert.assertFalse(frozen.btn_next.isEnabled());

     }

      @Test(alwaysRun = true)
      public void test009_PageNumberTest() throws Exception {
          AssetPage asset = new AssetPage(DRIVER);
          FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
          Assert.assertTrue(frozen.tv_common_title2.getText().contains("(1/2)"));
          frozen.et_amount.sendKeys("1");
          frozen.btn_next_step.click();
          TimeUnit.SECONDS.sleep(1);
          Assert.assertTrue(frozen.tv_common_title2.getText().contains("(2/2)"));
      }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test010_PageNumberMultiSignTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterMultiSign();
        Assert.assertTrue(frozen.tv_step.getText().contains("(1/3)"));
        frozen.inputMultiAddress(address);
        frozen.gotoMultiPageTwo();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(2/3)"));
        TimeUnit.SECONDS.sleep(1);
        frozen.stakeAmountAndNext("1");
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(3/3)"));

    }


    @Test(groups = {"P0"},alwaysRun = true)
    public void test011_inputPercentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
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
    public void test012_inputEnergyAndBandWidthCountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.selectEnergyTab();
        frozen.inputAmount("80");
        frozen.slideScreenBottom();
        Assert.assertTrue(removeSymbolDouble(frozen.tv_resource_get_amount.getText())>20000);
        Assert.assertEquals(frozen.tv_resource_per_transaction.getText(),"约可以支付 1 笔转账的能量消耗");
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"80");
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(removeSymbolDouble(frozen.tv_resource_get_amount.getText())>345);
        Assert.assertEquals(frozen.tv_resource_per_transaction.getText(),"约可以支付 1 笔转账的带宽消耗");
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"80");
    }


//    @Test(alwaysRun = true)
//    public void test013_inputEnergyTRXCountTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.selectEnergyTab();
//        frozen.slideScreenBottom();
//        frozen.enterEnergyBandWidth("20000");
//        Assert.assertEquals(frozen.et_amount.getText(),"74");
//        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"74");
//    }

    @Test(alwaysRun = true)
    public void test014_inputBandWidthTRXCountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.selectBandWidthTab();
        frozen.slideScreenBottom();
        frozen.enterEnergyBandWidth("345");
        Assert.assertEquals(frozen.et_amount.getText(),"70");
        Assert.assertEquals(frozen.tv_vote_get_amount.getText(),"70");
    }

}
