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

    public  String signName = "MutiSignActive";

    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage pursePage = minePage.enterMyPursePage();
        MultiSignManagerPage managerPage = pursePage.enterMultiSignManagerPageNew();
        try {
            if (managerPage.instructionBtn.isDisplayed()){
                System.out.println("成功进入MutisignManag");
                return managerPage;
            }else {
                System.out.println("1 times fails 进入MutisignManag");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if(managerPage.instructionBtn.isDisplayed()){
                    System.out.println("1 times success 进入MutisignManag");
                }
                return managerPage;
            }
        }catch (Exception e){
            System.out.println("1 times fails 进入MutisignManag Exception");
            managerPage = pursePage.enterMultiSignManagerPageNew();
            if(managerPage.instructionBtn.isDisplayed()){
                System.out.println("1 times success 进入MutisignManag Exception");
            }
            return managerPage;
        }


    }
    @Test(description = "MultiSignature Question Content Test", alwaysRun = true)
    public void test001_MultiSignatureQuestionContentTest() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        Assert.assertTrue(multiSignManagerPage.getInstructionString().contains("Active"));
    }

    @Test(description = "Add MutiSignature Test", alwaysRun = true)
    public void test002_multiSignature() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        multiSignManagerPage.addPermission(signName);
        Helper.refreshWalletScreen(DRIVER);
        TimeUnit.SECONDS.sleep(3);
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
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(description = "delete signature Test", alwaysRun = true)
    public void test005_delSignature() throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        Helper.swipRefreshScreen(multiSignManagerPage.driver);
        TimeUnit.SECONDS.sleep(3);
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



}
