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

public class DappMutiSignatureTest extends Base {


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
    public void delSignData(MultiSignManagerPage multiSign) throws Exception{
        int tries = 0;
        Boolean exist = false;
        int mulCount = multiSign.mulSign_span.size();
        System.out.println("current mulSign count is : " + mulCount);
        if (mulCount >= 3){
            for (int i = 3;i <= mulCount; i++){
                System.out.println("delete a mulSign");
                TimeUnit.SECONDS.sleep(4);
                Helper.swipeLeftScreen(DRIVER);
                Helper.swipeLeftScreen(DRIVER);
                multiSign.delSign();
            }
        }
//        while(exist == false && tries < 5) {
//            tries++;
//            try {
//                //multiSign.mulSign_span.isDisplayed();
//                System.out.println("delete a mulSign");
//                Helper.swipeLeftScreen(DRIVER);
//                Helper.swipeLeftScreen(DRIVER);
//                multiSign.delSign();
//            }catch (Exception e){}
//        }
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
        changeDappchain();
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        String content = multiSignManager.questionClick();
        System.out.println("MutiSignature question content is : " + content);
        Assert.assertTrue(content.contains("Active"));
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
        //Assert.assertEquals(signName,multiSignManager.permissionName_text.getText());
        Assert.assertTrue(multiSignManager.permissionName_text.isDisplayed());
    }



    //Modify signature,Return to the before state
    @Parameters({"address"})
    @Test(description = "Modify signature Test",alwaysRun = true)
    public void test003_modifySignature(String address) throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPermissionPage();
        multiSignManager = modifyPermission.modify(address);
        String signName = multiSignManager.permissionName_text.getText();
        Assert.assertEquals(signName,"active_new");
    }



    @Test(description = "delete signature Test",alwaysRun = true)
    public void test004_delSignature() throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        String signName = multiSignManager.permissionName_text.getText();
        multiSignManager.delSign();
        Assert.assertNotEquals(signName,multiSignManager.permissionName_text.getText());
    }



    //Modify signature,Return to the before state
    @Parameters({"address"})
    @Test(description = "Modify signature Test",alwaysRun = true)
    public void test005_modifySignature(String address) throws Exception {
        MultiSignManagerPage multiSignManager =enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPermissionPage();
        multiSignManager = modifyPermission.modify(address);
        String signName = multiSignManager.permissionName_text.getText();
        Assert.assertEquals(signName,"active_newest");
    }








}
