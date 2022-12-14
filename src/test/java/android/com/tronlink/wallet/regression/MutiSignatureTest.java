package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddPermissionPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.ModifyPermissionPage;
import android.com.wallet.pages.MultiSignManagerPage;
import android.com.wallet.pages.MyPursePage;

import android.com.wallet.pages.TransactionRecordPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MutiSignatureTest extends Base {
    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }



    //public method. enter the MultiSignManagerPage
    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage MultiSignManager = myPursePage.enterMultiSignManagerPage();
        return MultiSignManager;
    }


    @Test(description = "MutiSignature Question Content Test", alwaysRun = true)
    public void test001_MutiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        String content = multiSignManager.questionClick();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("MutiSignature question content is : " + content);
        Assert.assertTrue(content.contains("Active"));
    }


    @Test(groups = {"P0"},description = "Add MutiSignature Test", alwaysRun = true)
    public void test002_multiSignAddSuccessTest() throws Exception {
        String signName = "AutoTest-" + System.currentTimeMillis();
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.enterMyPursePage();
        MultiSignManagerPage multiSignManager = assetPage.enterActiveChangePage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        multiSignManager = add.addPermission(signName);
        TimeUnit.SECONDS.sleep(7);
        multiSignManager.swipLeftTimes();
        String currentName = multiSignManager.permissionName_text.getText();
        System.out.println(currentName  + "   " + signName );
        Assert.assertTrue(currentName.contains(signName));
    }
    @Test(groups = {"P0"},description = "delete signature Test", alwaysRun = true)
    public void test003_delSignature() throws Exception {
        MultiSignManagerPage manager = enterMultiSignManagerPage();
        manager.swipLeftTimes();
        String signName = manager.permissionName_text.getText();
        manager.delSign();
        manager.swipLeftTimes();
        String newName =  manager.permissionName_text.getText();
        System.out.println(signName);
        System.out.println(newName);
        Assert.assertNotEquals(signName,newName);

    }

    //Modify signature,Return to the before state
    @Parameters({"address"})
    @Test(description = "Modify signature Test", alwaysRun = true)
    public void test004_modifySignature(String address) throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPermissionPage();
        Assert.assertTrue(modifyPermission.title_text.isDisplayed());
    }


    @Test(description = "signature is exist", alwaysRun = true)
    public void test005_signatureIsExist() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.permissionName_input.sendKeys("active");
        Assert.assertTrue(add.permissionName_input.isDisplayed());
    }


    @Test(description = "signature Name Is Null", alwaysRun = true)
    public void test007_signatureNameIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        Helper.swipScreen(DRIVER);
        add.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreenToTop(DRIVER);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("名称长度须") || tip.contains("名字长度须") || tip.contains("Name length must be"));
    }


    @Test(description = "signature Name Is too long", alwaysRun = true)
    public void test008_signatureNameIsSoLong() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.permissionName_input.sendKeys("TXtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("名称长度须") || tip.contains("名字长度须") || tip.contains("Name length must be"));
    }


    @Test(description = "signature without choise Permission", alwaysRun = true)
    public void test009_signatureWithoutPermission() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfoWithoutPermission("AutoTest");
        String tip = add.operations_tip.getText();
        Assert.assertTrue(tip.contains("选择一个") || tip.contains("Please select"));
    }


    @Test(description = "signature threshold > 100", alwaysRun = true)
    public void test010_thresholdTooLarge() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.threshold_input.sendKeys("101");
        //add.inputInfoWithoutPermission("AutoTest");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.threshold_tip.getText();
        Assert.assertTrue(tip.contains("阈值须为 ≤100 的正整数") || tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }


    @Test(description = "signature threshold Is 0", alwaysRun = true)
    public void test011_thresholdIsZero() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.threshold_input.sendKeys("0");
        //add.inputInfoWithoutPermission("AutoTest");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.threshold_tip.getText();
        Assert.assertTrue(tip.contains("阈值须为 ≤100 的正整数") || tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }


    @Test(description = "signature with error Adress", alwaysRun = true)
    public void test012_errorAdress() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.address_input.get(0).sendKeys("AAtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(2);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.equals("请填写正确的地址") || tip.contains("enter the correct address"));
    }


    @Test(description = "Adress Is Null", alwaysRun = true)
    public void test013_AdressIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        Helper.swipScreen(DRIVER);
        add.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.equals("请填写正确的地址") || tip.contains("enter the correct address"));
    }


    @Test(description = "two Adress is equals", alwaysRun = true)
    public void test014_adressIsEquals() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputSameAddress();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        System.out.println("tip:" + tip);
        Assert.assertTrue(tip.contains("该地址与已添加的地址重复") );
    }


    @Test(description = "password is null", alwaysRun = true)
    public void test015_passwordIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest");
        add.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(add.inputPassword_title.isDisplayed());
    }


    @Test(description = "password is wrong", alwaysRun = true)
    public void test016_passwordIsWrong() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest");
        add.password_input.sendKeys("error_password");
        add.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(add.inputPassword_title.isDisplayed());
    }

    @Test(groups = {"P0"},enabled = true, description = "Mutisign history record test", alwaysRun = true)
    public void test017_transactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionTypeList.get(0).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.contains("更新账户权限"));
    }



}
