package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import wallet.UITest.base.Base;
import wallet.pages.AddwalletPage;
import wallet.pages.AssetPage;
import wallet.pages.GuidePage;
import wallet.pages.ImportPrivateKeyPage;
import wallet.pages.MinePage;
import wallet.pages.MyPursePage;
import wallet.pages.PrivateKeySetNamePage;
import wallet.pages.PrivateKeySetPwdAgainPage;
import wallet.pages.PrivateKeySetPwdPage;
import wallet.pages.UserAgreementPage;


public class ImportPrivateKey extends Base {


    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.launchApp();
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        UserAgreementPage userAgreement = guide.enterUserAgreementPage();
        AddwalletPage addwallet = userAgreement.enterAddwalletPage();
        ImportPrivateKeyPage importPrivateKey = addwallet.enterImportPrivateKeyPage();
        return importPrivateKey;
    }


    @Test(description = "Import PrivateKey Format Incorrect",alwaysRun = true)
    public void test001_importPrivateKeyFormatIncorrect() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.checkPrivateKey("ecd4bbba178b1b0d2a0c1e6e9108e0cab8");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("私钥错误"));
    }



    @Test(description = "Import PrivateKey Is NULL",alwaysRun = true)
    public void test002_importPrivateKeyIsNull() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        Assert.assertFalse(importPrivateKey.next_btn.isEnabled());
    }



    @Test(description = "Import PrivateKey Is Too Large",alwaysRun = true)
    public void test003_importPrivateKeyIsTooLarge() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.checkPrivateKey("ecd4bbba178b1b0d2a0c1e6e9108e0cab");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("私钥错误"));
    }


    @Parameters({"privateKey"})
    @Test(description = "PrivateKey Name Too Long",alwaysRun = true)
    public void test004_privateKeyNameTooLong(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("123456789012345");
        String name = setName.name_input.getText();
        Assert.assertTrue(name.equals("12345678901234"));
    }



    @Parameters({"privateKey"})
    @Test(description = "Password without uppercase letter",alwaysRun = true)
    public void test005_passwordWithoutUppercaseLetter(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Auto_Test");
        setPwd.pwd_input.sendKeys("test0001");
        Assert.assertFalse(setPwd.next_btn.isEnabled());
    }



    @Parameters({"privateKey"})
    @Test(description = "Password Is Too Short",alwaysRun = true)
    public void test006_passwordIsTooShort(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Auto_Test");
        setPwd.pwd_input.sendKeys("Abcd123");
        Assert.assertFalse(setPwd.next_btn.isEnabled());
    }


    @Parameters({"privateKey"})
    @Test(description = "Two Password Is diffent",alwaysRun = true)
    public void test007_twoPasswordDiffent(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Auto_Test");
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        setPwdAgain.pwd_input.sendKeys("Test0002");
        setPwdAgain.create_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String hits = setPwdAgain.error_hits.getText();
        Assert.assertTrue(hits.contains("密码不一致") || hits.contains("Password does not match"));
    }



    @Parameters({"privateKey"})
    @Test(description = "Wallet Name Has Alerady Exist",alwaysRun = true)
    public void test008_walletNameHasAleradyExist(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("AutoM_Test");
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        AssetPage asset = setPwdAgain.enterAssetPage("Test0001");
        MinePage mine = asset.enterMinePage();
        MyPursePage myPurse = mine.enterMyPursePage();
        AddwalletPage addwallet = myPurse.enterAddwalletPage();
        importPrivateKey = addwallet.enterImportPrivateKeyPage();
        setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setName.name_input.sendKeys("AutoM_Test");
        setName.next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String hits = setName.error_hits.getText();
        Assert.assertTrue(hits.contains("钱包已存在") || hits.contains("wallet already exists"));
    }



    @Parameters({"privateKey"})
    @Test(description = "Wallet The same name but different capitalization",alwaysRun = true)
    public void test009_sameNameButDifferentCapitalization(String privateKey) throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("auto_case");
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        AssetPage asset = setPwdAgain.enterAssetPage("Test0001");
        MinePage mine = asset.enterMinePage();
        MyPursePage myPurse = mine.enterMyPursePage();
        AddwalletPage addwallet = myPurse.enterAddwalletPage();
        importPrivateKey = addwallet.enterImportPrivateKeyPage();
        setName = importPrivateKey.enterPrivateKeySetNamePage(privateKey);
        setPwd = setName.enterPrivateKeySetPwdPage("AUTO_CASE");
        Assert.assertTrue(setPwd.pwd_title.isDisplayed());
    }




    @Test(description = "privateKey Is All Numbers",alwaysRun = true)
    public void test010_privateKeyIsAllNumbers() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.inputErrorKeyGetHits("1234567890");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("私钥错误"));
    }




    @Test(description = "privateKey Without Uppercase Letter",alwaysRun = true)
    public void test011_privateKeyWithoutUppercaseLetter() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        String hits = importPrivateKey.inputErrorKeyGetHits("zxcvbnm1234567");
        Assert.assertTrue(hits.contains("Incorrect private key") || hits.contains("私钥错误"));
    }







}
