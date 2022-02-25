package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.GuidePage;
import android.com.wallet.pages.ImportRoutePage;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class ImportWalletTest extends Base {

public static String mem = "knife use drift junk scale spin doctor reduce animal permit secret baby";
public static String privatekeystr = "3fa24d8c8807b228245b67edd96530d244f5ea57ea435760fa889cdd3ac5b18a";
public static String keystorestr = "{\"id\":\"ff40e017-2877-436c-b367-446ee03b8ccc\",\"address\":\"41B50B87ab4f262ba84269795DC7498BC8820a06FE\",\"version\":3,\"crypto\":{\"ciphertext\":\"999135516cf2900530191690a5c0a683b9f0c1464df64c653a9954d0e805442d\",\"cipherparams\":{\"iv\":\"af032ff9357be1b51a4a658bf1e31dfb\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"r\":8,\"p\":6,\"n\":4096,\"dklen\":32,\"salt\":\"292f58d30d5f8738b92a3f79e2ae55595372fbe177134a6202a06bfbb90cd158\"},\"mac\":\"0bab2840fb09c19c08d3d10be330c9f18d4ee7b601841a9ec1bf0b331827441a\",\"cipher\":\"aes-128-ctr\"},\"type\":\"private-key\"}";
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
        }catch (Exception e){}
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        try {
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){
        }
    }

    @Parameters({"udid"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        try {
            wifiOpen(udid);
            TimeUnit.SECONDS.sleep(3);
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Parameters({"udid"})
    @Test( alwaysRun = true)
    public void test001_firstImportPrivateKeyTest(String udid) throws Exception {
        GuidePage page = new GuidePage(DRIVER);
        ImportRoutePage impage = page.enterImportPage();
        impage.importContent.sendKeys(privatekeystr);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.passwordInputAgain.sendKeys(useKeyPassword);
        impage.NextBtn.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(impage.walletName.getText().contains("导入"));
        impage.deleteWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));

    }

    @Test( alwaysRun = true)
    public void test002_firstImportKeyStoreTest() throws Exception {
        GuidePage page = new GuidePage(DRIVER);
        ImportRoutePage impage = page.enterImportPageNone();
        impage.importContent.sendKeys(keystorestr);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.NextBtn.click();
        Assert.assertTrue(impage.wallettag.getText().contains("Non-HD"));
        impage.deleteWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
    }

    @Test( alwaysRun = true)
    public void test003_firstImportMemTest() throws Exception {
        GuidePage page = new GuidePage(DRIVER);
        ImportRoutePage impage = page.enterImportPageNone();
        impage.importContent.sendKeys(mem);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.passwordInputAgain.sendKeys(useKeyPassword);
        TimeUnit.SECONDS.sleep(1);
        impage.NextBtn.click();
        TimeUnit.SECONDS.sleep(2);
        impage.memNextBtn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(impage.walletName.getText().contains("导入"));
        impage.deleteWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
    }

    @Test( alwaysRun = true)
    public void test004_createWalletTest() throws Exception {
        GuidePage page = new GuidePage(DRIVER);
        ImportRoutePage impage = page.enterCreatePage();
        impage.passwordCreatInput.sendKeys(useKeyPassword);
        impage.passwordCreatInputAgain.sendKeys(useKeyPassword);
        impage.creatBtn.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(impage.toptitle.getText().contains("创建成功"));
        impage.laterBackup.click();
        impage.deleteWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
    }

    @Test( alwaysRun = true)
    public void test005_ObservationWalletTest() throws Exception {
        GuidePage page = new GuidePage(DRIVER);
        ImportRoutePage impage = page.enterObservationPage();
        impage.watchTextField.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        impage.watchBtn.click();
        Assert.assertTrue(impage.walletName.getText().contains("观察"));
        impage.deleteWalletNone();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));

    }



    @Parameters({"udid"})
    @Test( alwaysRun = true)
    public void test007_coldImportPrivateKeyTest(String udid) throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        guide.modeconfirmBtn.click();
        ImportRoutePage impage = guide.enterImportPageNone();
        impage.importContent.sendKeys(privatekeystr);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.passwordInputAgain.sendKeys(useKeyPassword);
        impage.NextBtn.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(impage.title.getText().contains("冷钱包模式"));
        impage.deleteColdWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);
    }
    @Parameters({"udid"})
    @Test( alwaysRun = true)
    public void test008_coldImportKeyStoreTest(String udid) throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        guide.modeconfirmBtn.click();
        ImportRoutePage impage = guide.enterImportPageNone();
        impage.importContent.sendKeys(keystorestr);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.NextBtn.click();
        Assert.assertTrue(impage.title.getText().contains("冷钱包模式"));
        impage.deleteColdWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);
    }
    @Parameters({"udid"})
    @Test( alwaysRun = true)
    public void test009_coldImportMemTest(String udid) throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        guide.modeconfirmBtn.click();
        ImportRoutePage impage = guide.enterImportPageNone();
        impage.importContent.sendKeys(mem);
        impage.NextBtn.click();
        impage.passwordInput.sendKeys(useKeyPassword);
        impage.passwordInputAgain.sendKeys(useKeyPassword);
        TimeUnit.SECONDS.sleep(1);
        impage.NextBtn.click();
        TimeUnit.SECONDS.sleep(2);
        impage.memNextBtn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(impage.title.getText().contains("冷钱包模式"));
        impage.deleteColdWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);
    }

    @Parameters({"udid"})
    @Test( alwaysRun = true)
    public void test010_coldCreateWalletTest(String udid) throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        guide.modeconfirmBtn.click();
        ImportRoutePage impage = guide.enterCreatePage();
        impage.passwordCreatInput.sendKeys(useKeyPassword);
        impage.passwordCreatInputAgain.sendKeys(useKeyPassword);
        impage.creatBtn.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(impage.toptitle.getText().contains("创建成功"));
        impage.laterBackup.click();
        impage.deleteColdWallet();
        Assert.assertTrue(impage.title.getText().contains("波场支持"));
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);
    }


}
