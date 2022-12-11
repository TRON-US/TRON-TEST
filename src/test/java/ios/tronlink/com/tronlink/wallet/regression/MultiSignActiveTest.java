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



    @Test(description = "MultiSignature Question Content Test", alwaysRun = true)
    public void test001_MultiSignatureQuestionContentTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.fasternterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getInstructionString().contains("Active"));
    }

    @Test(groups = {"P0"},description = "Add MultiSignature Test", alwaysRun = true)
    public void test002_multiSignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        multiSignManagerPage.addPermission(signName);
        System.out.println("-------------\nmultiSignManagerPage.havedaddActive() value Is:\n"+multiSignManagerPage.havedaddActive() + "\n----------\n");
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(groups = {"P0"},description = "Modify signature Test", alwaysRun = true)
    public void test003_modifySignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.modifyPermission();
        Assert.assertTrue(multiSignManagerPage.havedaddfreezeAssetPower());
    }

    @Test(description = "signature is exist", alwaysRun = true)
    public void test004_signatureIsExist() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Helper.swipScreen(multiSignManagerPage.driver);
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(groups = {"P0"},description = "delete signature Test", alwaysRun = true)
    public void test005_delSignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        Helper.swipRefreshScreen(multiSignManagerPage.driver);
        Assert.assertFalse(multiSignManagerPage.havedaddActive());
    }

    @Test(description = "signature Name Is Null", alwaysRun = true)
    public void test006_signatureNameIsNull() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getnameNullErrorString().contains("1-32"));
    }

    @Test(description = "signature Name Is too long", alwaysRun = true)
    public void test007_signatureNameIsSoLong() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getnameToolongErrorString().contains("1-32"));

    }
    @Test(description = "signature without choise Permission", alwaysRun = true)
    public void test008_signatureWithoutPermission() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getPremissonNullErrorString().contains("选择一个"));
    }

    @Test(description = "signature threshold > 100", alwaysRun = true)
    public void test009_thresholdTooLarge() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.ThrHoldErrWith("101").contains("阈值须"));
    }

    @Test(description = "signature threshold Is 0", alwaysRun = true)
    public void test010_thresholdIsZero() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.ThrHoldErrWith("0").contains("阈值须"));
    }


    @Test(description = "signature with error Adress", alwaysRun = true)
    public void test011_errorAdress() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.addressErrWith("AAtrbmfwZ2LxtoCveEhZT86fTss1w8rwJE").contains("请填写正确的账户地址"));
    }

    @Test(description = "Adress Is Null", alwaysRun = true)
    public void test012_AdressIsNull() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.addressErrWith("").contains("请填写正确的账户地址"));
    }
    @Test(description = "two Adress is equals", alwaysRun = true)
    public void test013_adressIsEquals() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.makedouleAddressErr().contains("不能输入重复账户"));
    }

    @Test(description = "password is wrong", alwaysRun = true)
    public void test014_passwordIsWrong() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.inputPassword("wrongPassword"));
    }

    @Test(description = "password is null", alwaysRun = true)
    public void test015_passwordIsNull() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        Assert.assertFalse(multiSignManagerPage.inputEmptyPassword());
    }
}
