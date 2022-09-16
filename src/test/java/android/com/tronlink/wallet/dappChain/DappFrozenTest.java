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

    private String password = "Test0001";
    private String otherAddress = "TLVh1FcdR57fukbFZL7DgjYY6VW9WRdNzt";


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
            DRIVER.activateApp("com.tronlink.global");
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
     * Freeze Energy
     */
    @Test(groups = {"P0"},enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test0001_freezeEnergySuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
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
    @Test(groups = {"P0"}, enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test002_freezeBandwidthSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.frozenTheBandwidth(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.confirmTransferPage();
        frozen.btn_confirm.click();
        frozen.inputPassword(password);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
        Assert.assertTrue(frozen.tv_right_first.getText().contains("带宽"));

    }


    /**
     * Freeze Energy Other
     */
    @Test(groups = {"P0"},enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
    public void test003_freezeEnergyOtherSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.frozenTheEnergy(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.inputOtherAddress(otherAddress);
        frozen.btn_confirm.click();
        frozen.inputPassword(password);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
        Assert.assertTrue(frozen.tv_right_first.getText().contains("能量"));

    }

    /**
     * freeze Bandwidth Other
     */
    @Test(groups = {"P0"}, enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
    public void test004_freezeBandwidthOtherSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.frozenTheBandwidth(); //Freeze operating
        frozen.et_amount.sendKeys("1");
        frozen.inputOtherAddress(otherAddress);
        frozen.btn_confirm.click();
        frozen.inputPassword(password);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(frozen.tv_result.getText().contains("质押成功"));
        Assert.assertTrue(frozen.tv_right_first.getText().contains("带宽"));

    }


    @Test(enabled = true, description = "Freeze Details of the rules", alwaysRun = true)
    public void test005_enterDetailsOfTheRules() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterDetailsAndRulesPage();
        Assert.assertTrue(frozen.doc0_spe.getText().contains("1. 质押 TRX 可以选择获得带宽或者能量中的一种，同时可以获得投票权，质押 1 TRX 可以获得 1 个投票权。"));
        frozen.btn_know_it.click();
        Assert.assertFalse(isElementShotId("btn_know_it"));
    }

    @Test(alwaysRun = true)
    public void test006_multiSignGuideTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.interMultiSignGuidePage();
        Assert.assertTrue(frozen.tv_common_title.getText().contains("使用教程"));
    }


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
        frozen.iv_select.click();
        Assert.assertTrue(frozen.tv_content.getText().contains("可解锁时间"));
    }

    @Test(alwaysRun = true)
    public void test009_PageNumberTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(1/2)"));
        frozen.et_amount.sendKeys("1");
        frozen.freeze_btn.click();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(2/2)"));
    }

    @Test(alwaysRun = true)
    public void test010_PageNumberMultiSignTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.tv_common_right2.click();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(1/3)"));
        frozen.tv_address.click();
        frozen.btn_next.click();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(2/3)"));
        frozen.et_amount.sendKeys("1");
        frozen.freeze_btn.click();
        Assert.assertTrue(frozen.tv_common_title2.getText().contains("(3/3)"));
    }

}
