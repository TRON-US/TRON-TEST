package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImportKeystore extends BaseTest {
    String keystore = "{\"id\":\"b894d3f1-b156-4deb-a6c1-7b105c162d17\",\"crypto\":{\"ciphertext\":\"15483ff7a027f46f4a3f876a0ff0e8673662b20cb355f9add54c7a40249bb359\",\"cipherparams\":{\"iv\":\"aeb1f58b81c7a1ca4f2ac687ac925252\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"r\":8,\"p\":6,\"n\":4096,\"dklen\":32,\"salt\":\"0f09f7445ea05f4d041b29845e12f268d37a7b09066ab12b22c1b25856193946\"},\"mac\":\"6e67950fc77217e268a683165137f1a1011e5d628c5b1d2de0dde1b69535fd10\",\"cipher\":\"aes-128-ctr\"},\"version\":3,\"address\":\"418993Bb2Ed34e06A49E4D982108982a7cab751C49\",\"type\":\"private-key\"}";
    String oldKeystore;
    public ImportKeystorePage getImportKeystorePage(){
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet_btn.click();
        DRIVER.findElementByName("导入钱包").click();
        return new ImportKeystorePage(DRIVER);
    }



    @Test(description = "test goto ImportFromKeyStore",alwaysRun = true)
    public void test001_gotoimportFromeKeyStroe() throws Exception {
        getImportKeystorePage();
        Assert.assertTrue(isElementExist("导入钱包"));
        Assert.assertTrue(isElementExist("Import Create Nav Scan"));
        Assert.assertTrue(isElementExist("下一步"));
    }

    @Test(description = "test  input wrong format Keystore",alwaysRun = true)
    public void test002_inputWrongFormatKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.content_text.sendKeys("wrongkeystoreformatwrongkeystoreformat");
        closeKeyBoard();
        Assert.assertTrue(isElementExist("请输入有效的私钥、助记词或 Keystore"));
    }

    @Test(description = "test  input wrong format Password",alwaysRun = true)
    public void test003_inputWrongPasswordKeystore() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.inputKeyAndPassword(keystore,"aaasdfdsf");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(importKeystorePage.importKeystore().isDisplayed());
    }

    @Test(groups = {"P0"},description = "test import Keystore Wallet Success",alwaysRun = true)
    public  void test004_keystoreNameSetSuccess() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        importKeystorePage.enterPrivateKeySetNamePage(keystore,"Qqqqqqq1");
        TimeUnit.SECONDS.sleep(5);
        AssetPage assetPage = new AssetPage(DRIVER);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("name"));
    }

    @Test(groups = {"P0"},description = "test import Keystore Wallet Success",alwaysRun = true)
    public  void test005_keystoreModifyPasswordSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage = assetPage.enterMyPursePage();
        walletPage.modifyPassword("Test0002");
        Assert.assertTrue(isElementExist("name"));
    }

    @Test(groups = {"P0"},description = "test import Keystore Wallet Success",alwaysRun = true)
    public  void test006_keystoreModifyNameSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage = assetPage.enterMyPursePage();
        walletPage.modifyName("newName");
        Assert.assertTrue(isElementExist("newName"));
    }



    @Test(groups = {"P0"},description = "test Delete Wallet  password",alwaysRun = true)
    public void  test007_testDeletewalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage = assetPage.enterMyPursePage();
        walletPage.deleteWallet("Test0002");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }
}
