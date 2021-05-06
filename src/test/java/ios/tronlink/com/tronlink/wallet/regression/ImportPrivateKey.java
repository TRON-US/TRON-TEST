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
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        try {
            DRIVER.findElementById("normalWallet").click();
        }catch (Exception ee){
            log(" removed in nile");
        }
        DRIVER.findElementByName("私钥").click();
        TimeUnit.SECONDS.sleep(2);
        return new ImportPrivateKeyPage(DRIVER);
    }

    @Test(description = "Import PrivateKey Format Incorrect", alwaysRun = true)
    public void test001_importPrivateKeyFormatIncorrect() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        //test002_importPrivateKeyIsNull
        Assert.assertFalse(importPrivateKey.getNext_btn().isEnabled());

        String hits = importPrivateKey.checkPrivateKey("ecd4bbba178b1b0d2a0c1e6e9108e0cab8");
        System.out.println("/nError is:" + hits);
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("格式错误"));
        //test003_importPrivateKeyIsTooLarge
        importPrivateKey.content_text.clear();
        importPrivateKey.content_text.sendKeys("ecd4bbba178b1b0d2a123123343245463450c1e6e9108e0asdfasfddafjwijfiajsvnxzcviarjfjasfjlafcab");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(Helper.isElementExist(importPrivateKey.driver,"格式错误"));
    }


    @Test(description = "PrivateKey Name Too Long", alwaysRun = true)
    public void test004_privateKeyNameTooLong() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("123456789012345");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(setName.toolongname.isDisplayed());
        //test005_privateKeychineseNameTooLong
        setName.name_input.clear();
        setName.name_input.sendKeys("一二三四五六七超");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(setName.toolongname.isDisplayed());
        //test006_PrivateKeywalletNameHasAleradyExist
        setName.name_input.clear();
        setName.name_input.sendKeys("Auto_test");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertTrue(Helper.isElementExist(setName.driver,"钱包名称已存在"));
        //test007_PrivateKeysameNameButDifferentCapitalization
        setName.name_input.clear();
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
        //test009_PrivateKeypasswordIsTooShort
        setPwd.pwd_input.clear();
        setPwd.pwd_input.sendKeys("Abcd123");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
        //test010_PrivateKeypasswordIsNonumber
        setPwd.pwd_input.clear();
        setPwd.pwd_input.sendKeys("Abcdasdf");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
        //test011_PrivateKeypasswordIsNoLowercase
        setPwd.pwd_input.clear();
        setPwd.pwd_input.sendKeys("ABCDEFGHI");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
        //test012_privateKeyIsAllNumbers
        setPwd.pwd_input.clear();
        setPwd.pwd_input.sendKeys("1234567890");
        Helper.tapWhitePlace(DRIVER);
        Assert.assertFalse(setPwd.getNext_btn().isEnabled());
        //test013_privateKeyWithoutUppercaseLetter
        setPwd.pwd_input.clear();
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
        setPwdAgain.getComplish_btn().click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(Helper.isElementExist(setPwdAgain.driver,"两次输入密码不一致"));

    }
    //todo: none test add


//    @Test(description = "test import privateKey Success",alwaysRun = true)
//    public  void test015_PrivateKeyNameSetSuccess() throws Exception {
//        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
//        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
//        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage(wallet);
//        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
//        setPwdAgain.pwd_input.sendKeys("Test0001");
//        Helper.tapWhitePlace(DRIVER);
//        setPwdAgain.getComplish_btn().click();
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]);
//        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) == 0);
//    }
//
//    @Test(description = "test Delete Wallet  password",alwaysRun = true)
//    public void  test016_testDeletePrivateKeywalletSuccess() throws InterruptedException {
//        AssetPage assetPage = new AssetPage(DRIVER);
//        MinePage minePage =  assetPage.enterMinePage();
//        MyPursePage walletPage = minePage.enterMyPursePage();
//        walletPage.deletWallet("Test0001");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(Helper.isElementExist(DRIVER,"冷钱包"));
//
////        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) != 0);
//    }
}
