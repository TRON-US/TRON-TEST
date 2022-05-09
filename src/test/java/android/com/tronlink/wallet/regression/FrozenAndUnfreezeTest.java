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
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e) {
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

//    /**
//     * Freeze Energy
//     */
//    @Test(groups = {"P0"},enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
//    public void test0001_freezeEnergySuccess() throws Exception{
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
//    @Test(groups = {"P0"}, enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
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
//    @Test(groups = {"P0"},enabled = true,description = "Freeze Energy Scuuess", alwaysRun = true)
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
//    @Test(groups = {"P0"}, enabled = true, description = "Freeze Bandwidth Success", alwaysRun = true)
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


//    @Test(enabled = true, description = "Freeze Details of the rules", alwaysRun = true)
//    public void test005_enterDetailsOfTheRules() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.enterDetailsAndRulesPage();
//        Assert.assertTrue(frozen.doc0_spe.getText().contains("1. 质押 TRX 可以选择获得带宽或者能量中的一种，同时可以获得投票权，质押 1 TRX 可以获得 1 个投票权。"));
//        frozen.btn_know_it.click();
//        Assert.assertFalse(isElementShotId("btn_know_it"));
//    }


//    @Test(enabled = true, description = "frozen30Account", alwaysRun = true)
//    public void frozen30Account() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//
//        Helper.swipScreenLitte(frozen.driver);
//        frozen.freezeCount_input.sendKeys("1");
//        Helper.swipScreen(frozen.driver);
//        frozen.cleanAddress_btn.click();

//        int book = 1;
//        while (book < 11){
//            log("iosMultiSignAccount.owner"+String.valueOf(book)+"Address");
//
//            String addressString =  Configuration.getByPath("testng.conf")
//                    .getString("androidMultiSignAccount.multiSign"+String.valueOf(book)+"Address");
//            log("find address: " + addressString);
//            frozen.freezeCount_input.clear();
//            frozen.freezeCount_input.sendKeys("1");
//            frozen.freezeAddress_input.clear();
//            frozen.freezeAddress_input.clear();
//            frozen.freezeAddress_input.sendKeys(addressString);
//            frozen.frozenTheBandwidth();
//            book++;
//        }

//        while (book < 21){
//            log("第几个 "+String.valueOf(book)+"Address");
//
//                        String addressString = Configuration.getByPath("testng.conf")
//                    .getString("androidMultiSignAccount.owner" + String.valueOf(book-10) + "Address");
//            log("find address: " + addressString);
//            frozen.freezeCount_input.clear();
//            frozen.freezeCount_input.sendKeys("1");
//            frozen.freezeAddress_input.click();
//            frozen.freezeAddress_input.clear();
//            frozen.freezeAddress_input.sendKeys(addressString);
//            frozen.frozenTheBandwidth();
//            book++;
//        }
//
//        while (book < 31){
//            log("iosMultiSignAccount.owner"+String.valueOf(book)+"Address");
//
//            String addressString = Configuration.getByPath("testng.conf")
//                    .getString("iosMultiSignAccount.owner" + String.valueOf(book-20) + "Address");
//            log("find address: " + addressString);
//            frozen.freezeCount_input.clear();
//            frozen.freezeCount_input.sendKeys("1");
//            frozen.freezeAddress_input.clear();
//            frozen.freezeAddress_input.clear();
//            frozen.freezeAddress_input.sendKeys(addressString);
//            frozen.frozenTheBandwidth();
//            book++;
//        }
//}


}
