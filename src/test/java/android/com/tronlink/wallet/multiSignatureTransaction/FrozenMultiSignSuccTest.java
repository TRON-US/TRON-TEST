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





    @Parameters({"ownerPrivateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String ownerPrivateKey) throws Exception {
        new Helper().getSign(ownerPrivateKey, DRIVER);
    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        }catch (Exception e){
            try {
                DRIVER.closeApp();
                DRIVER.activateApp("wallet.tronlink.global");
            }catch (Exception e1){

            }
        }

    }



    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Parameters({"ownerPrivateKey","multiSignAddress"})
    @Test(description = "Sign Address Is Exists", alwaysRun = true)
    public void test002_signAddressIsExists(String ownerPrivateKey,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = asset.enterFrozenAndUnfreezePage();
        Helper.scrollToElementUntilVisible(DRIVER,frozenAndUnfreezePage.freeze_btn);
        TimeUnit.SECONDS.sleep(1);
        frozenAndUnfreezePage.freezeCount_input.sendKeys("5");
        frozenAndUnfreezePage.frozenButtonClickAndConfirm();
        frozenAndUnfreezePage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(frozenAndUnfreezePage.invalidTime_input.isDisplayed());
        Assert.assertTrue(frozenAndUnfreezePage.signAddress_input.get(0).getText().length() == 34);
        Assert.assertTrue(frozenAndUnfreezePage.signAddress_input.get(1).isDisplayed());
        Assert.assertTrue(frozenAndUnfreezePage.selectSignName_text.isDisplayed());
    }


    @Parameters({"ownerPrivateKey","multiSignAddress"})
    @Test(description = "send trx sign success options Test", alwaysRun = true)
    public void test005_sendTrxOptions(String ownerPrivateKey,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = asset.enterFrozenAndUnfreezePage();
        frozenAndUnfreezePage.forzenSign("5");
        TimeUnit.SECONDS.sleep(3);
    }




    @Parameters({"ownerPrivateKey","multiSignAddress"})
    @Test(description = "send trx sign success two times options Test", alwaysRun = true)
    public void test006_sendTrxTwoTimesOptions(String ownerPrivateKey,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = asset.enterFrozenAndUnfreezePage();
        frozenAndUnfreezePage.forzenSign("5");
        TimeUnit.SECONDS.sleep(3);
    }



    @Parameters({"multiSignPrivateKey"})
    @Test(description = "sign options Test", alwaysRun = true)
    public void test007_signOptions(String multiSignPrivateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(multiSignPrivateKey, DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        multiSignTransactionPage.sign();
        TimeUnit.SECONDS.sleep(3);
    }



    @Parameters({"multiSignPrivateKey"})
    @Test(description = "Sign options Test check TRX", alwaysRun = true)
    public void test008_signPageCheckTrx(String multiSignPrivateKey) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        Assert.assertTrue(multiSignTransactionPage.transConten_text.getText() != null);
        Assert.assertTrue(multiSignTransactionPage.transFrom_text.getText().length() == 34);
        Assert.assertTrue(multiSignTransactionPage.invaTime_text.isDisplayed());

    }



}
