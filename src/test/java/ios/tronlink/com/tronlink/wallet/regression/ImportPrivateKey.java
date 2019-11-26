package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImportPrivateKey extends BaseTest {

    String privateKey = "6f3f2f61080aea622436c223bbc784c66049e8c4ca77ed98ad723655022ef934";

    String wallet = "WDelWallet";

    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet_btn.click();
        DRIVER.findElementByName("私钥导入").click();
        return new ImportPrivateKeyPage(DRIVER);
    }

    @Test(description = "Import PrivateKey Format Incorrect", alwaysRun = true)
    public void test001_importPrivateKeyFormatIncorrect() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.checkPrivateKey("ecd4bbba178b1b0d2a0c1e6e9108e0cab8");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("格式错误"));
    }


    @Test(description = "Import PrivateKey Is NULL", alwaysRun = true)
    public void test002_importPrivateKeyIsNull() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        Assert.assertFalse(importPrivateKey.getNext_btn().isEnabled());
    }


    @Test(description = "Import PrivateKey Is Too Large", alwaysRun = true)
    public void test003_importPrivateKeyIsTooLarge() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.checkPrivateKey("ecd4bbba178b1b0d2a0c1e6e9108e0cab");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("格式错误"));
    }


    @Test(description = "PrivateKey Name Too Long", alwaysRun = true)
    public void test004_privateKeyNameTooLong() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("123456789012345");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(setName.toolongname.isDisplayed());
    }
    @Test(description = "PrivateKey Name chinese Too Long", alwaysRun = true)
    public void test005_privateKeychineseNameTooLong() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();

        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("一二三四五六七超");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(setName.toolongname.isDisplayed());
    }
    @Test(description = "Wallet Name Has Alerady Exist", alwaysRun = true)
    public void test006_PrivateKeywalletNameHasAleradyExist() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("Auto_test");
        Helper.tapWhitePlace(DRIVER);
        String hits = setName.getError_hits();
        Assert.assertTrue(hits.contains("钱包名已存在") || hits.contains("already exists"));
    }

    @Test(description = "Wallet The same name but different capitalization", alwaysRun = true)
    public void test007_PrivateKeysameNameButDifferentCapitalization() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("AUto_test");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(setName.getNext_btn().isEnabled());
    }

    @Test(description = "Password without uppercase letter", alwaysRun = true)
    public void test008_PrivateKeypasswordWithoutUppercaseLetter() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("test0001");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }


    @Test(description = "Password Is Too Short", alwaysRun = true)
    public void test009_PrivateKeypasswordIsTooShort() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("Abcd123");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }
    @Test(description = "Password has no number", alwaysRun = true)
    public void test010_PrivateKeypasswordIsNonumber() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("Abcdasdf");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }

    @Test(description = "Password has no lowCaseChar", alwaysRun = true)
    public void test011_PrivateKeypasswordIsNoLowercase() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("ABCDEFGHI");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }

    @Test(description = "privateKey Is All Numbers", alwaysRun = true)
    public void test012_privateKeyIsAllNumbers() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("1234567890");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }


    @Test(description = "privateKey Without Uppercase Letter", alwaysRun = true)
    public void test013_privateKeyWithoutUppercaseLetter() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        setPwd.pwd_input.sendKeys("zxcvbnm1234567");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
    }

    @Test(description = "Two Password Is diffent", alwaysRun = true)
    public void test014_PrivateKeytwoPasswordDiffent() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        setPwdAgain.pwd_input.sendKeys("Test0002");
        Helper.tapWhitePlace(DRIVER);
        String hits = setPwdAgain.getError_hits();
        Assert.assertTrue(hits.contains("两次输入密码不一致") || hits.contains("wrong"));
    }


    @Test(description = "test import privateKey Success",alwaysRun = true)
    public  void test015_PrivateKeyNameSetSuccess() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        setPwdAgain.pwd_input.sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        setPwdAgain.getComplish_btn().click();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]);
        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) == 0);
    }

    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test016_testDeletePrivateKeywalletSuccess(){
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        walletPage.deletWallet("Test0001");
        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) != 0);
    }
}
