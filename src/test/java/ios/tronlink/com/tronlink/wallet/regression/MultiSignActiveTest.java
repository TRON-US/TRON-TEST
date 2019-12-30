package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MultiSignManagerPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MultiSignActiveTest extends BaseTest {

    public  String signName = "MultiSignActive";

    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage pursePage = minePage.enterMyPursePage();
        MultiSignManagerPage managerPage = pursePage.enterMultiSignManagerPageNew();
        try {
            if (managerPage.instructionBtn.isDisplayed()){
                System.out.println("\n1 times success 成功进入MultiSignMange");
                return managerPage;
            }else {
                System.out.println("\n1 times fails 进入MultiSignMange");
                System.out.println("\n2 times Try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if(managerPage.instructionBtn.isDisplayed()){
                    System.out.println("\n2 times success 进入MultiSignMange");
                }
                return managerPage;
            }
        }catch (Exception e){
            System.out.println("\n1 times fails 进入MultiSignMange Exception");
            System.out.println("\n2 times Try 进入MultiSignMange");
            managerPage = pursePage.enterMultiSignManagerPage();
            if(managerPage.instructionBtn.isDisplayed()){
                System.out.println("\n2 times success  进入MultiSignMange");
            }else {
                System.out.println("\n last try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if(managerPage.instructionBtn.isDisplayed()){
                    System.out.println("\n last times success 进入MultiSignMange");
                }
            }
            return managerPage;
        }


    }

    @Test(description = "MultiSignature Question Content Test", alwaysRun = true)
    public void test001_MultiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getInstructionString().contains("Active"));
    }

    @Test(description = "Add MultiSignature Test", alwaysRun = true)
    public void test002_multiSignature() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        multiSignManagerPage.addPermission(signName);
        System.out.println("multiSignManagerPage.havedaddActive() value Is:\n"+multiSignManagerPage.havedaddActive() + "\n----------\n");
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(description = "Modify signature Test", alwaysRun = true)
    public void test003_modifySignature() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.modifyPermission();
        Assert.assertTrue(multiSignManagerPage.havedaddfreezeAssetPower());
    }

    @Test(description = "signature is exist", alwaysRun = true)
    public void test004_signatureIsExist() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Helper.swipScreen(multiSignManagerPage.driver);
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(description = "delete signature Test", alwaysRun = true)
    public void test005_delSignature() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        Helper.swipRefreshScreen(multiSignManagerPage.driver);
        Assert.assertFalse(multiSignManagerPage.havedaddActive());
    }

    @Test(description = "signature Name Is Null", alwaysRun = true)
    public void test006_signatureNameIsNull() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getnameNullErrorString().contains("长度须1-32"));
    }

    @Test(description = "signature Name Is too long", alwaysRun = true)
    public void test007_signatureNameIsSoLong() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getnameToolongErrorString().contains("长度须1-32"));

    }
    @Test(description = "signature without choise Permission", alwaysRun = true)
    public void test008_signatureWithoutPermission() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getPremissonNullErrorString().contains("选择一个"));
    }

    @Test(description = "signature threshold > 100", alwaysRun = true)
    public void test009_thresholdTooLarge() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.ThrHoldErrWith("101").contains("阈值须≤100"));
    }

    @Test(description = "signature threshold Is 0", alwaysRun = true)
    public void test010_thresholdIsZero() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.ThrHoldErrWith("0").contains("阈值须≤100"));
    }


    @Test(description = "signature with error Adress", alwaysRun = true)
    public void test011_errorAdress() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.addressErrWith("AAtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE").contains("请填写正确的地址"));
    }

    @Test(description = "Adress Is Null", alwaysRun = true)
    public void test012_AdressIsNull() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.addressErrWith("").contains("请填写正确的地址"));
    }
    @Test(description = "two Adress is equals", alwaysRun = true)
    public void test013_adressIsEquals() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.makedouleAddressErr().contains("重复地址"));
    }

    @Test(description = "password is wrong", alwaysRun = true)
    public void test014_passwordIsWrong() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.inputPassword("wrongPassword"));
    }

    @Test(description = "password is null", alwaysRun = true)
    public void test015_passwordIsNull() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertFalse(multiSignManagerPage.inputEmptyPassword());
    }
}
