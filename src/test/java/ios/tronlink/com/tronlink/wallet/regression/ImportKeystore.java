package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ImportKeystore extends BaseTest {
  String keystore = "";
  String password = "Test0001";

  @Test(description = "import android.com.wallet with incorrect keystore")
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

  @Test(description = "import android.com.wallet with keystore by incorrect password",alwaysRun = true)
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
  @Test(description = "import android.com.wallet with correct keystore",alwaysRun = true)
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
  }

}
