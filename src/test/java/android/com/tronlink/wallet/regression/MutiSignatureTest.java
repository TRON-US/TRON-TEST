package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddPermissionPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.ModifyPermissionPage;
import android.com.wallet.pages.MultiSignManagerPage;
import android.com.wallet.pages.MyPursePage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MutiSignatureTest extends Base {

//    @Parameters({"privateKey"})
////    @BeforeMethod()
////    public void setUpBefore(String privateKey) throws Exception{
////        DRIVER.closeApp();
////        DRIVER.launchApp();
////        getSign(privateKey);
////    }

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


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    // if exist mult sign del this sign
    public void delSignData(MultiSignManagerPage multiSign){
        int tries = 0;
        Boolean exist = false;
        while(exist == false && tries < 7) {
            tries++;
            try {
                multiSign.mulSign_span.isDisplayed();
                Helper.swipeLeftScreen(DRIVER);
                multiSign.delSign();
            }catch (Exception e){}
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



    @Test(description = "MutiSignature Question Content Test",alwaysRun = true)
    public void test001_MutiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        String content = multiSignManager.questionClick();
        Assert.assertTrue(content.contains("Active Permission"));
    }




    @Test(description = "Add MutiSignature Test",alwaysRun = true)
    public void test002_mutiSignature() throws Exception {
        String signName = "AutoTest-" + System.currentTimeMillis();
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        //del before sign
        delSignData(multiSignManager);
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        multiSignManager = add.addPermission(signName);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(signName,multiSignManager.permissionName_text.getText());
    }



    @Test(description = "delete signature Test",alwaysRun = true)
    public void test003_delSignature() throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        String signName = multiSignManager.permissionName_text.getText();
        multiSignManager.delSign();
        Assert.assertNotEquals(signName,multiSignManager.permissionName_text.getText());
    }



    //Modify signature,Return to the before state
    @Parameters({"address"})
    @Test(description = "Modify signature Test",alwaysRun = true)
    public void test004_modifySignature(String address) throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPermissionPage();
        multiSignManager = modifyPermission.modify(address);
        String signName = multiSignManager.permissionName_text.getText();
        Assert.assertEquals(signName,"active");
    }




    @Test(description = "signature is exist",alwaysRun = true)
    public void test005_signatureIsExist() throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.permissionName_input.sendKeys("active");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("请更换名称") || tip.contains("one permission with this"));
    }




    @Test(description = "signature Name Is Null",alwaysRun = true)
    public void test006_signatureNameIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("名字长度须") || tip.contains("Name length must be"));
    }



    @Test(description = "signature Name Is too long",alwaysRun = true)
    public void test007_signatureNameIsSoLong() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.permissionName_input.sendKeys("TXtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("名字长度须") || tip.contains("Name length must be"));
    }



    @Test(description = "signature without choise Permission",alwaysRun = true)
    public void test008_signatureWithoutPermission() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfoWithoutPermission("AutoTest");
        String tip = add.operations_tip.getText();
        Assert.assertTrue(tip.contains("选择一个") || tip.contains("Please select"));
    }


    @Test(description = "signature threshold > 100",alwaysRun = true)
    public void test009_thresholdTooLarge() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.threshold_input.sendKeys("101");
        //add.inputInfoWithoutPermission("AutoTest");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.threshold_tip.getText();
        Assert.assertTrue(tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }



    @Test(description = "signature threshold Is 0",alwaysRun = true)
    public void test010_thresholdIsZero() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.threshold_input.sendKeys("0");
        //add.inputInfoWithoutPermission("AutoTest");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.threshold_tip.getText();
        Assert.assertTrue(tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }




    @Test(description = "signature with error Adress",alwaysRun = true)
    public void test011_errorAdress() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.address_input.get(0).sendKeys("AAtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.equals("请填写正确的地址") || tip.contains("enter the correct address"));
    }



    @Test(description = "Adress Is Null",alwaysRun = true)
    public void test012_AdressIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.equals("请填写正确的地址") || tip.contains("enter the correct address"));
    }



    @Test(description = "two Adress is equals",alwaysRun = true)
    public void test013_adressIsEquals() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        //add.address_input.sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        add.inputSameAddress();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.contains("key重复") || tip.contains("has been added"));
    }



    @Test(description = "password is null",alwaysRun = true)
    public void test014_passwordIsNull() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest");
        add.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(add.inputPassword_title.isDisplayed());
    }



    @Test(description = "password is wrong",alwaysRun = true)
    public void test015_passwordIsWrong() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.inputInfo("AutoTest");
        add.password_input.sendKeys("error_password");
        add.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(add.inputPassword_title.isDisplayed());
    }







}
