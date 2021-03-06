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
    String keystore = "";
    String oldPassword = "Test0001";
    String newPassword = "Admin1234";
    String confirmPassword = "Admin1234";

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
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

    public WalletPasswordPage walletPasswordPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        WalletPasswordPage walletPasswordPage = myPursePage.enterwalletPasswordPage();
        return walletPasswordPage;
    }


    @Test(description = "Input correct password", alwaysRun = true)
    public void test0001InputCorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, oldPassword, oldPassword);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test(description = "Input dont match password", alwaysRun = true)
    public void test0002InputIncorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, oldPassword);
        String error = walletPasswordPage.error.getText();
        Assert.assertTrue(error.equals("Password does not match") || error.equals("两次密码不一致"));
        TimeUnit.SECONDS.sleep(1);
    }

    @Test(groups = {"P0"},description = "input correct password", alwaysRun = true)
    public void test0003InputIncorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, newPassword);
        TimeUnit.SECONDS.sleep(3);
        MyPursePage myPurse = new MyPursePage(DRIVER);
        keystore = myPurse.getBackupKeystore(newPassword);
        Assert.assertTrue(myPurse.getExitst());
        TimeUnit.SECONDS.sleep(1);
    }

    @Test(description = "input incorrect password", alwaysRun = true)
    public void test0004InputIncorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(newPassword, newPassword, newPassword);
        TimeUnit.SECONDS.sleep(3);
        MyPursePage myPursePage = new MyPursePage(DRIVER);
        keystore = myPursePage.getBackupKeystore(oldPassword);
        Assert.assertTrue(keystore.isEmpty());
        TimeUnit.SECONDS.sleep(1);
    }

    @Test(description = "input incorrect password", alwaysRun = true)
    public void test0005InputCorrectPassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, newPassword);
        String error = walletPasswordPage.error.getText();
        Assert.assertTrue(error.equals("incorrect password") || error.equals("密码错误"));
        TimeUnit.SECONDS.sleep(1);
    }
}
