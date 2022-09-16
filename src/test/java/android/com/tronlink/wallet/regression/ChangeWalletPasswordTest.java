package android.com.tronlink.wallet.regression;

import android.com.wallet.pages.WalletPasswordPage;
import android.com.utils.Helper;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MyPursePage;

public class ChangeWalletPasswordTest extends Base {
    String oldPassword = "Test0001";
    String newPassword = "Admin1234";

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.global");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    public WalletPasswordPage walletPasswordPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage myPursePage = asset.enterMyPursePage();
        WalletPasswordPage walletPasswordPage = myPursePage.enterwalletPasswordPage();
        return walletPasswordPage;
    }

    @Test(description = "Input dont match password", alwaysRun = true)
    public void test001InputIncorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, "Test00011", newPassword);
        String error = walletPasswordPage.error.getText();
        Assert.assertTrue(error.equals("Password does not match") || error.equals("两次密码不一致"));
    }


    @Test(description = "Input correct password", alwaysRun = true)
    public void test002ChangePassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, newPassword);
        TimeUnit.SECONDS.sleep(5);
        MyPursePage purse = new MyPursePage(DRIVER);
        walletPasswordPage =  purse.enterwalletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, newPassword);
        String error = walletPasswordPage.error.getText();
        Assert.assertTrue(error.equals("密码错误"));

    }

    @Test(description = "input incorrect password", alwaysRun = true)
    public void test003InputOldPasswordTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage pursePage= asset.enterMyPursePage();
        pursePage.findByShotId("rl_privatekey").click();
        pursePage.findByShotId("et_password").sendKeys(oldPassword);
        pursePage.findByShotId("tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementTextExist("钱包详情"));
        pursePage.findByShotId("rl_keystore").click();
        pursePage.findByShotId("et_password").sendKeys(oldPassword);
        pursePage.findByShotId("tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementTextExist("钱包详情"));
    }

    @Test(description = "input incorrect password", alwaysRun = true)
    public void test004InputNewPasswordTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage pursePage= asset.enterMyPursePage();
        pursePage.findByShotId("rl_privatekey").click();
        pursePage.findByShotId("et_password").sendKeys(newPassword);
        pursePage.findByShotId("tv_ok").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementTextExist("备份私钥"));
        pursePage.backBtn.click();
        pursePage.findByShotId("rl_keystore").click();
        pursePage.findByShotId("et_password").sendKeys(newPassword);
        pursePage.findByShotId("tv_ok").click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementTextExist("备份 Keystore"));
    }




}
