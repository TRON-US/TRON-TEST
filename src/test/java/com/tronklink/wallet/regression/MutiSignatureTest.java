package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AddPermissionPage;
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.MultiSignManagerPage;
import wallet.pages.MyPursePage;

public class MutiSignatureTest extends Base {

//    @Parameters({"privateKey"})
////    @BeforeMethod()
////    public void setUpBefore(String privateKey) throws Exception{
////        DRIVER.closeApp();
////        DRIVER.launchApp();
////        getSign(privateKey);
////    }

    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        Helper.getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }


//    @AfterClass
//    public void tearDownAfterClass() {
//        //Base.tearDownAfterClass();
//        DRIVER.quit();
//    }


    //public method. enter the MultiSignManagerPage
    public MultiSignManagerPage enterMultiSignManagerPage(){
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage MultiSignManager = myPursePage.enterMultiSignManagerPage();
        return MultiSignManager;
    }



    @Test(description = "MutiSignature Question Content Test")
    public void test001_MutiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage MultiSignManager = enterMultiSignManagerPage();
        String content = MultiSignManager.questionClick();
        Assert.assertTrue(content.contains("Active Permission"));
    }



    @Test(description = "Add MutiSignature Test",enabled = false)
    public void test002_mutiSignature() throws Exception {
        String signName = "AutoTest-" + System.currentTimeMillis();
        MultiSignManagerPage MultiSignManager =enterMultiSignManagerPage();
        AddPermissionPage add = MultiSignManager.enterAddPermissionPage();
        MultiSignManager = add.addPermission(signName);
        Assert.assertEquals(signName,MultiSignManager.permissionName_text.getText());
    }



    @Test(description = "delete signature Test",enabled = false)
    public void test003_delSignature() throws Exception {
        MultiSignManagerPage MultiSignManager =enterMultiSignManagerPage();
        String signName = MultiSignManager.permissionName_text.getText();
        MultiSignManager.delSign();
        Assert.assertNotEquals(signName,MultiSignManager.permissionName_text.getText());
    }


}
