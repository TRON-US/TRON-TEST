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
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    // if exist mult sign del this sign
    public void delSignData(MultiSignManagerPage multiSign) throws Exception {
        int tries = 0;
        Boolean exist = false;
        int mulCount = multiSign.mulSign_span.size();
        System.out.println("current mulSign count is : " + mulCount);
        if (mulCount >= 3) {
            for (int i = 3; i <= mulCount; i++) {
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


    @Test(description = "MutiSignature Question Content Test", alwaysRun = true)
    public void test001_MutiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        String content = multiSignManager.questionClick();
        System.out.println("MutiSignature question content is : " + content);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(content.contains("Active"));
    }


    @Test(description = "MutiSignature Test", alwaysRun = true)
    public void test002_mutiSignature() throws Exception {
        String signName = "AutoTest-" + System.currentTimeMillis();
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        //del before sign
        delSignData(multiSignManager);
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        multiSignManager = add.addPermission(signName);
        TimeUnit.SECONDS.sleep(3);
        //Assert.assertEquals(signName,multiSignManager.permissionName_text.getText());
        Assert.assertTrue(multiSignManager.permissionName_text.isDisplayed());
    }


    //Modify signature,Return to the before state
    @Parameters({"address"})
    @Test(description = "Modify signature Test", alwaysRun = true)
    public void test003_modifySignature(String address) throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPermissionPage();
        //multiSignManager = modifyPermission.modify(address);
        //String signName = multiSignManager.permissionName_text.getText();
        //System.out.println("get modify mulSign name is : " + signName);
        //Assert.assertEquals(signName, "active_est");
        Assert.assertTrue(modifyPermission.title_text.isDisplayed());
    }




    @Test(description = "delete signature Test", alwaysRun = true)
    public void test005_delSignature() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        String signName = multiSignManager.permissionName_text.getText();
        multiSignManager.delSign();
        Assert.assertNotEquals(signName, multiSignManager.permissionName_text.getText());
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
        Assert.assertTrue(tip.contains("名字长度须") || tip.contains("Name length must be"));
    }


    @Test(description = "signature Name Is too long", alwaysRun = true)
    public void test008_signatureNameIsSoLong() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.permissionName_input.sendKeys("TXtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.tip_hits.getText();
        Assert.assertTrue(tip.contains("名字长度须") || tip.contains("Name length must be"));
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
        Assert.assertTrue(tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }


    @Test(description = "signature threshold Is 0", alwaysRun = true)
    public void test011_thresholdIsZero() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.threshold_input.sendKeys("0");
        //add.inputInfoWithoutPermission("AutoTest");
        TimeUnit.SECONDS.sleep(1);
        String tip = add.threshold_tip.getText();
        Assert.assertTrue(tip.contains("阈值须≤100") || tip.contains("threhold value of"));
    }


    @Test(description = "signature with error Adress", alwaysRun = true)
    public void test012_errorAdress() throws Exception {
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        add.address_input.get(0).sendKeys("AAtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE");
        TimeUnit.SECONDS.sleep(1);
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
        //add.address_input.sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        add.inputSameAddress();
        TimeUnit.SECONDS.sleep(1);
        String tip = add.addkey_tip.getText();
        Assert.assertTrue(tip.contains("重复") || tip.contains("has been added"));
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


}
