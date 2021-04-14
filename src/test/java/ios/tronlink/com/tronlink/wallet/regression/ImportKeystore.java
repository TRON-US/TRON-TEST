package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImportKeystore extends BaseTest {
    String keystore = "{\"id\":\"b894d3f1-b156-4deb-a6c1-7b105c162d17\",\"crypto\":{\"ciphertext\":\"15483ff7a027f46f4a3f876a0ff0e8673662b20cb355f9add54c7a40249bb359\",\"cipherparams\":{\"iv\":\"aeb1f58b81c7a1ca4f2ac687ac925252\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"r\":8,\"p\":6,\"n\":4096,\"dklen\":32,\"salt\":\"0f09f7445ea05f4d041b29845e12f268d37a7b09066ab12b22c1b25856193946\"},\"mac\":\"6e67950fc77217e268a683165137f1a1011e5d628c5b1d2de0dde1b69535fd10\",\"cipher\":\"aes-128-ctr\"},\"version\":3,\"address\":\"418993Bb2Ed34e06A49E4D982108982a7cab751C49\",\"type\":\"private-key\"}";
    String oldKeystore;
    public ImportKeystorePage getImportKeystorePage(){
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        try {
            DRIVER.findElementById("normalWallet").click();
        }catch (Exception ee){
            log(" removed in nile");
        }
        DRIVER.findElementByName("Keystore").click();
        return new ImportKeystorePage(DRIVER);
    }

    public ImportKeystorePage mypurpustokeystore(MyPursePage page){
        waiteTime();
        page.addwallet.click();

        waiteTime();
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
        Assert.assertTrue(importKeystorePage.errorStr.getText().contains("格式错误"));
//        Assert.assertTrue(Helper.isElementExist(importKeystorePage.driver,"格式错误"));

    }
    @Test(description = "test  input wrong format Password",alwaysRun = true)
    public void test003_inputWrongPasswordKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.inputKeyAndPassword(keystore,"aaasdfdsf");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(importKeystorePage.errorStr.getText().contains("密码错误"));
//        Assert.assertTrue(Helper.isElementExist(importKeystorePage.driver,"密码错误"));

    }
    @Parameters({"privateKey"})
    @Test(description = "test  input haved Keystore",alwaysRun = true)
    public void test004_inputHaveExistWallet(String privateKey) throws Exception {
        new Helper().importFirstWallet(Helper.importType.normal,privateKey,DRIVER);

        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage walletPage = asset.enterMyPursePage();
        oldKeystore = walletPage.getBackupKeystore("Test0001");
        ImportKeystorePage importKeystorePage = mypurpustokeystore(walletPage);
        importKeystorePage.inputKeyAndPassword(oldKeystore,"Test0001");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(importKeystorePage.errorStr.getText().contains("钱包已存在"));
    }



    @Test(groups = {"P0"},description = "test import Keystore Wallet Success",alwaysRun = true)
    public  void test007_keystoreNameSetSuccess() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        PrivateKeySetNamePage setNamePage = importKeystorePage.enterPrivateKeySetNamePage(keystore,"Qqqqqqq1");
        setNamePage.name_input.sendKeys("willbedelete");
        Helper.tapWhitePlace(DRIVER);
        setNamePage.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'").click();
        TimeUnit.SECONDS.sleep(3);
        AssetPage assetPage = new AssetPage(DRIVER);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("willbedelete"));
    }

    @Test(groups = {"P0"},description = "test Delete Wallet  password",alwaysRun = true)
    public void  test008_testDeletewalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage = assetPage.enterMyPursePage();
        walletPage.deletWallet("Qqqqqqq1");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }
}
