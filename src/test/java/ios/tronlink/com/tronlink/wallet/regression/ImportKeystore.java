package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImportKeystore extends BaseTest {
    String keystore = "{\"id\":\"9f3f57f3-95f1-4725-ab5b-a074a921a2d3\",\"version\":3,\"crypto\":{\"ciphertext\":\"ab54e64f4f6424cd67b534d2fbaed626d2718589b400e3aad86b1104f7e90673\",\"cipherparams\":{\"iv\":\"230307285d920b7b1ee3d798d2164d85\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"r\":8,\"p\":6,\"n\":4096,\"dklen\":32,\"salt\":\"f84e9561e94ff33313e76d4a3c1288bb811580a0fa34bf3852c037086edb421a\"},\"mac\":\"e086407b58e5021e32c6ceec7970959fcdca4db1d1be0d95079a754606d40667\",\"cipher\":\"aes-128-ctr\"},\"address\":\"416F366492B1bBA81A17470d7A8980430BA4200058\",\"type\":\"private-key\"}";
    String oldKeystore;
    public ImportKeystorePage getImportKeystorePage(){
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet_btn.click();
        DRIVER.findElementByName("Keystore").click();
        return new ImportKeystorePage(DRIVER);
    }
    @Test(description = "test goto ImportFromKeyStore",alwaysRun = true)
    public void test001_gotoimportFromeKeyStroe() throws Exception {

        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        Assert.assertTrue(Helper.contentTexts(importKeystorePage.textArray,"Keystore 导入"));


    }

    @Test(description = "test  input wrong format Keystore",alwaysRun = true)
    public void test002_inputWrongFormatKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.content_text.sendKeys("wrong keysotre format");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '格式错误'").isDisplayed());

    }
    @Test(description = "test  input wrong format Password",alwaysRun = true)
    public void test003_inputWrongPasswordKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.inputKeyAndPassword(keystore,"aaasdfdsf");
        Assert.assertTrue(DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '密码错误'").isDisplayed());

    }

    @Test(description = "test have KeyStore",alwaysRun = true)
    public void test000_getKeyStore() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        oldKeystore = walletPage.getBackupKeystore("Test0001");
    }

    @Test(description = "test  input haved Keystore",alwaysRun = true)
    public void test004_inputhavedKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.inputKeyAndPassword(oldKeystore,"Test0001");
        Assert.assertTrue(DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '钱包已存在'").isDisplayed());
    }

    @Test(description = "test import Keystore name Too long number",alwaysRun = true)
    public  void test005_keystoreNameSetlongNumber() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        PrivateKeySetNamePage setNamePage = importKeystorePage.enterPrivateKeySetNamePage(keystore,"Qqqqqqq1");
        setNamePage.name_input.sendKeys("123456789012345");
        Helper.tapWhitePlace(DRIVER);
        boolean testresult = setNamePage.toolongname.isDisplayed();
        setNamePage.goback();
        Assert.assertTrue(testresult);
    }

    @Test(description = "test import Keystore name Too long Chines",alwaysRun = true)
    public  void test006_keystoreNameSetlongChines() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        PrivateKeySetNamePage setNamePage = importKeystorePage.enterPrivateKeySetNamePage(keystore,"Qqqqqqq1");
        setNamePage.name_input.sendKeys("一二三四五六七超");
        Helper.tapWhitePlace(DRIVER);
        boolean testresult = setNamePage.toolongname.isDisplayed();
        setNamePage.goback();
        Assert.assertTrue(testresult);
    }

//    @Test(description = "test import Keystore Wallet Success",alwaysRun = true)
//    public  void test007_keystoreNameSetSuccess() throws Exception {
//        ImportKeystorePage importKeystorePage = getImportKeystorePage();
//        PrivateKeySetNamePage setNamePage = importKeystorePage.enterPrivateKeySetNamePage(keystore,"Test0001");
//        setNamePage.name_input.sendKeys("willbedelete");
//        Helper.tapWhitePlace(DRIVER);
//        setNamePage.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertEquals(DRIVER.findElementByName("trxLabel").getText().split(" ")[0],"0");
////        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) == 0);
//    }

    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test008_testDeletewalletSuccess(){
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        walletPage.deletWallet("Test0001");
        Assert.assertNotEquals(DRIVER.findElementByName("trxLabel").getText().split(" ")[0],"0");
    }
}
