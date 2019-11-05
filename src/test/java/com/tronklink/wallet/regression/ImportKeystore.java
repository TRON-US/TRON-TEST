package com.tronklink.wallet.regression;

import common.utils.Helper;
import java.util.concurrent.TimeUnit;
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

public class ImportKeystore extends Base {
  String keystore = "";
  String password = "Test0001";

  @Parameters({"privateKey"})
  @BeforeClass(alwaysRun = true)
  public void setUpBefore(String privateKey) throws Exception {
    new Helper().getSign(privateKey,DRIVER);
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(){
    DRIVER.closeApp();
    DRIVER.activateApp("com.tronlink.wallet");
  }

  @AfterClass
  public void tearDownAfterClass() {
    DRIVER.quit();
  }

  @Test(description = "import wallet with incorrect keystore")
  public void test0001ImportsErrorKeystore() throws Exception{
    AssetPage asset = new AssetPage(DRIVER);
    MinePage minePage = asset.enterMinePage();
    MyPursePage myPursePage = minePage.enterMyPursePage();
    keystore = myPursePage.getBackupKeystore(password);
    AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();
    ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
    String errorHits = importKeystorePage.inputErrorKeyGetHits(keystore,password + "error");
    Assert.assertTrue(errorHits.equals("Keystore incorrect") ||errorHits.equals("Keystore不正确"));
  }

  @Test(description = "import wallet with keystore by incorrect password",alwaysRun = true)
  public void test0002ImportsErrorKeystore() throws Exception{
    AssetPage asset = new AssetPage(DRIVER);
    MinePage minePage = asset.enterMinePage();
    MyPursePage myPursePage = minePage.enterMyPursePage();
    AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();;
    ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
    String errorHits = importKeystorePage.inputErrorPasswordHits(keystore,password + "error");
    Assert.assertTrue(errorHits.equals("incorrect password") ||errorHits.equals("密码错误"));
  }

  @Parameters({"address"})
  @Test(description = "import wallet with correct keystore",alwaysRun = true)
  public void test0003ImportsKeystore(String address) throws Exception{
    AssetPage asset = new AssetPage(DRIVER);
    MinePage minePage = asset.enterMinePage();
    MyPursePage myPursePage = minePage.enterMyPursePage();
    AddwalletPage addwalletPage = myPursePage.enterAddwalletPage();;
    ImportKeystorePage importKeystorePage = addwalletPage.enterImportKeystorePage();
    PrivateKeySetNamePage setName = importKeystorePage.enterPrivateKeySetNamePage(keystore,password);
    setName.setName("Auto_Test_ks");
    TimeUnit.SECONDS.sleep(2);
    asset.enterMinePage();
    minePage.enterMyPursePage();
    TimeUnit.SECONDS.sleep(1);
    Assert.assertEquals(myPursePage.getAddress(),address);
    Assert.assertEquals("Auto_Test_ks",myPursePage.walletname_text.getText());
  }

}
