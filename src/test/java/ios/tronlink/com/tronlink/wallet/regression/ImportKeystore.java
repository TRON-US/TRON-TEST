package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ImportKeystorePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImportKeystore extends BaseTest {
    String keystore = "{'version':3,'type':'private-key','crypto':{'ciphertext':'e9acd16091a4f659e497eb82a4cc4141e632fff583cd5e6e1dc717253d18e183','cipherparams':{'iv':'9581c5d224de5573ef5f018f2bfd5943'},'kdf':'scrypt','kdfparams':{'r':8,'p':6,'n':4096,'dklen':32,'salt':'8e81ea5fedc207eb691ba3b12fb29c03934cec0e1275e9eeefa460a459827625'},'mac':'c303a6d0ba6ac48d20594ed98e3c4d2f422bbfbd4a8cadce1fb79012ae6d4502','cipher':'aes-128-ctr'},'id':'e3723584-2c23-4794-a64a-37c32504e884','address':'41040e77C44E48bE3e04706ff8C96554AaB43E5cd3'}";
    String oldKeystore = "";
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
//    @Test(description = "test  input wrong format Password",alwaysRun = true)
//    public void test003_inputWrongPasswordKeystore() throws Exception {
//        ImportKeystorePage importKeystorePage = getImportKeystorePage();
//        importKeystorePage.inputKeyAndPassword(keystore,"aaasdfdsf");
//        Assert.assertTrue(DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '密码错误'").isDisplayed());
//
//    }

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
}
