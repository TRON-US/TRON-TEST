package com.tronklink.wallet.regression;

import common.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;
import wallet.pages.AddwalletPage;
import wallet.pages.AssetPage;
import wallet.pages.ImportKeystorePage;
import wallet.pages.MinePage;
import wallet.pages.MyPursePage;
import wallet.pages.PrivateKeySetNamePage;
import wallet.pages.PrivateKeySetPwdPage;
import wallet.pages.WalletPasswordPage;

public class ChangeWalletPassword extends Base {
  String keystore = "";
  String oldPassword = "Test0001";
  String newPassword = "Admin1234";
  String confirmPassword = "Admin1234";

  @Parameters({"privateKey"})
  @BeforeClass()
  public void setUpBefore(String privateKey) throws Exception {
    new Helper().getSign(privateKey,DRIVER);
  }

  @AfterMethod
  public void afterMethod(){
    DRIVER.closeApp();
    DRIVER.activateApp("com.tronlink.wallet");
  }

  @AfterClass
  public void tearDownAfterClass() {
    DRIVER.quit();
  }

  public WalletPasswordPage walletPasswordPage() throws Exception{
    AssetPage asset = new AssetPage(DRIVER);
    MinePage minePage = asset.enterMinePage();
    MyPursePage myPursePage = minePage.enterMyPursePage();
    WalletPasswordPage walletPasswordPage = myPursePage.enterwalletPasswordPage();
    return walletPasswordPage;
  }


  @Test(description = "")
  public void test0001ImportCorrectPassword() throws Exception{
    WalletPasswordPage walletPasswordPage = walletPasswordPage();
    walletPasswordPage.changePassword(oldPassword,oldPassword,oldPassword);
  }

  @Test(description = "")
  public void test0002ImportIncorrectPassword() throws Exception{
    WalletPasswordPage walletPasswordPage = walletPasswordPage();
    walletPasswordPage.changePassword(oldPassword,newPassword,newPassword);
    String error = walletPasswordPage.error.getText();
    Assert.assertTrue(error.equals("Password does not match")||error.equals("两次密码不一致"));
  }

  @Test(description = "")
  public void test0003ImportIncorrectPassword() throws Exception{
    WalletPasswordPage walletPasswordPage = walletPasswordPage();
    walletPasswordPage.changePassword(oldPassword,newPassword,newPassword);
    MyPursePage myPursePage = new MyPursePage(DRIVER);
    keystore = myPursePage.getBackupKeystore();
    Assert.assertFalse(keystore.isEmpty());
  }

  @Test(description = "")
  public void test0004ImportCorrectPassword() throws Exception{
    WalletPasswordPage walletPasswordPage = walletPasswordPage();
    walletPasswordPage.changePassword(oldPassword,oldPassword,oldPassword);
    String error = walletPasswordPage.error.getText();
    Assert.assertTrue(error.equals("incorrect password")||error.equals("密码错误"));
  }
}
