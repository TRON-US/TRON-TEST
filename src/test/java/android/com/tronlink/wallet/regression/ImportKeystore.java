package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddwalletPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ImportKeystorePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MyPursePage;
import android.com.wallet.pages.PrivateKeySetNamePage;

public class ImportKeystore extends Base {
    String keystore = "";
    String password = "Test0001";

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
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(description = "import android.com.wallet with incorrect keystore")
    public void test0001ImportsErrorKeystore() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        keystore = myPursePage.getBackupKeystore(password);
        AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();
        ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
        String errorHits = importKeystorePage.inputErrorKeyGetHits(keystore+ "error", password );
        log(errorHits);
        Assert.assertTrue( errorHits.contains("Keystore 错误，请重新输入"));
    }

    @Test(description = "import android.com.wallet with keystore by incorrect password", alwaysRun = true)
    public void test0002ImportsErrorKeystore() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();
        ;
        ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
        String errorHits = importKeystorePage.inputErrorPasswordHits(keystore, password + "error");
        log(errorHits);
        Assert.assertTrue(errorHits.contains("密码与 Keystore 不匹配，请输入原钱包的密码")||errorHits.contains("Keystore 错误，请重新输入"));
    }


    @Parameters({"address"})
    @Test(description = "import android.com.wallet with correct keystore", alwaysRun = true)
    public void test0003ImportsKeystore(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();
        ;
        ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
        PrivateKeySetNamePage setName = importKeystorePage.enterPrivateKeySetNamePage(keystore, password);
        setName.setName("Auto_Test_ks");
        TimeUnit.SECONDS.sleep(2);
        asset.enterMinePage();
        minePage.enterMyPursePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(myPursePage.getAddress(), address);
    }

}
