package android.com.tronlink.wallet.multiSignatureTransaction;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FrozenMultiSignSuccTest extends Base {





    @Parameters({"multiSignPrivateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String multiSignPrivateKey) throws Exception {
        new Helper().getSign(multiSignPrivateKey, DRIVER);
    }



    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){

        }

    }



    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

//    @Parameters({"ownerAddress"})
//     @Test(groups = {"P0"},alwaysRun = true)
//     public void test001_FrozenEnergySuccess(String ownerAddress) throws Exception {
//         AssetPage asset = new AssetPage(DRIVER);
//         FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//         frozen.enterMultiSign();
//         frozen.inputMultiAddress(ownerAddress);
//         frozen.gotoMultiPageTwo();
//         frozen.stakeEnergyWithAmount("1");
//         frozen.stakeWithThisAddress();
//         frozen.stakeConfirm();
//         frozen.multiSignOptionSign();
//         frozen.inputPopViewPassword("Test0001");
//         TimeUnit.SECONDS.sleep(5);
//         Assert.assertTrue(frozen.tv_trans_content.getText().contains("1"));
//        Assert.assertTrue(frozen.tv_trans_type.getText().contains("质押资产"));
//        Assert.assertTrue(frozen.resource_type.getText().contains("能量"));
//
//     }
//
//    @Parameters({"ownerAddress"})
//    @Test(groups = {"P0"},alwaysRun = true)
//    public void test002_FrozenBandWidthSuccess(String ownerAddress) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
//        frozen.enterMultiSign();
//        frozen.inputMultiAddress(ownerAddress);
//        frozen.gotoMultiPageTwo();
//        frozen.stakeEnergyWithAmount("1");
//        frozen.stakeWithThisAddress();
//        frozen.stakeConfirm();
//        frozen.multiSignOptionSign();
//        frozen.inputPopViewPassword("Test0001");
//        TimeUnit.SECONDS.sleep(5);
//        Assert.assertTrue(frozen.tv_trans_content.getText().contains("1"));
//        Assert.assertTrue(frozen.tv_trans_type.getText().contains("质押资产"));
//        Assert.assertTrue(frozen.resource_type.getText().contains("能量"));
//    }

    @Test(alwaysRun = true)
    public void test003_ControlViewTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterMultiSign();
        Assert.assertTrue(frozen.tv_tutorial.getText().contains("使用教程"));
        Assert.assertTrue(frozen.tv_main_title.getText().contains("多重签名质押"));
        Assert.assertTrue(frozen.tv_step.getText().contains("(1/3)"));
        Assert.assertTrue(frozen.tv_account.getText().contains("控制账户列表"));
        Assert.assertTrue(frozen.tv_address.getText().contains("T"));
    }


    @Parameters({"ownerAddress"})
     @Test(alwaysRun = true)
     public void test004_multiSignTipsTest(String ownerAddress) throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
         frozen.enterMultiSign();
         frozen.inputMultiAddress(ownerAddress);
         frozen.gotoMultiPageTwo();
         String tips = frozen.tv_under_control_tips.getText();
         log(tips);//当前正在操作 TX99hM37vw18V6GzTHfeftrgGH61jMjQHc 账户的质押
         Assert.assertTrue(tips.contains("当前正在操作"));
     }

     @Parameters({"ownerAddress"})
     @Test(alwaysRun = true)
     public void test005_AmountNotEqualTest(String ownerAddress) throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
         Double amountOne =  sepLeftNumberTextToDouble(frozen.tv_available_amount.getText(),"TRX");
         frozen.enterMultiSign();
         frozen.inputMultiAddress(ownerAddress);
         frozen.gotoMultiPageTwo();
         TimeUnit.SECONDS.sleep(1);
         Double amountTwo =  sepLeftNumberTextToDouble(frozen.tv_available_amount.getText(),"TRX");
         System.out.println(amountOne +" " +  amountTwo);
        Assert.assertNotEquals(amountOne,amountTwo);
     }




}
