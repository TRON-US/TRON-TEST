package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.AppiumTestCase;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MultiSignTest extends Base {

    @Parameters({"ownerPrivateKey","udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String ownerPrivateKey,String udid) throws Exception {
//        System.out.println("pk: " + ownerPrivateKey + " udid: " + udid);
        DRIVER.closeApp();
        log("开始移除app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        AssetPage.closedADView = false;
        new Helper().getSign(ownerPrivateKey,DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        try {
            DRIVER.closeApp();
            DRIVER.launchApp();
        }catch (Exception e){}

    }
    @Parameters({"udid"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        try {
            DRIVER.closeApp();
            System.out.println("开始移除app");
            AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
            System.out.println("开始安装app");
            AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
            AssetPage.closedADView = false;
            DRIVER.quit();
        }catch (Exception e){}

    }

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

//    @Parameters({"ownerPrivateKey","udid"})
//    @Test(description = "wallet deals",alwaysRun = true)
//    public void test000_ValueSignAddressIsRight(String ownerPrivateKey,String udid) throws Exception{
//        System.out.println("pk: " + ownerPrivateKey + " udid: " + udid);
//        DRIVER.closeApp();
//        log("开始移除app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
//        log("开始安装app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
//        log("开始导入ownerPrivatekey");
//        DRIVER.closeApp();
//
//        DRIVER.launchApp();
//    }

    @Parameters({"multiSignAddress"})
    @Test(description = "valued sign address is right",alwaysRun = true)
    public void test001_ValueSignAddressIsRight(String multiSignAddress) throws Exception{
        System.out.println("test_001ValueSignAddressIsRight:");
        MultiSignManagerPage managerPage = enterMultiSignManagerPage();
        Assert.assertTrue(managerPage.ownerAllkeys().contains(multiSignAddress));
    }


    @Parameters({"multiSignPrivateKey"})
    @Test(description = "add sign account", alwaysRun = true)
    public void test002_addSignAccountSuccess(String multiSignPrivateKey) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet_btn.click();
        TimeUnit.SECONDS.sleep(2);
        DRIVER.findElementByName("私钥导入").click();
        TimeUnit.SECONDS.sleep(2);
        ImportPrivateKeyPage importPrivateKey = new ImportPrivateKeyPage(DRIVER);
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(multiSignPrivateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Signed");
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        TimeUnit.SECONDS.sleep(1);
        setPwdAgain.pwd_input.sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        setPwdAgain.getComplish_btn().click();
        TimeUnit.SECONDS.sleep(8);
        String trxtext = assetPage.getTrxCount();
        log("value:" + trxtext);
        Assert.assertTrue(Integer.parseInt(trxtext) < 100);

    }

    @Test(description = "change account", alwaysRun = true)
    public void test003_swipChangeAccountSuccess() throws Exception {
            AssetPage assetPage = new AssetPage(DRIVER);
            TimeUnit.SECONDS.sleep(3);
            String oldName = assetPage.walletNameBtn.getText();
            log("\nfrom Wallet: " + oldName );
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            log("\nfrom Wallet: " + oldName + "  to Wallet " + assetPage.walletNameBtn.getText());
            Assert.assertFalse(assetPage.walletNameBtn.getText().contains(oldName));
    }

    @Test(description = "swip account address is change", alwaysRun = true)
    public void test004_swipAccountAddressChange() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        String oldName = assetPage.walletNameBtn.getText();
        MyPursePage myPursePage = assetPage.enterMyPursePage();
        myPursePage.swipWalletTochangeNext();
        log("\nfrom Wallet: " + oldName + "  to Wallet " + assetPage.walletNameBtn.getText());
        Assert.assertFalse(assetPage.walletNameBtn.getText().contains(oldName));
    }

    @Test(description = "make account address to Signed", alwaysRun = true)
    public void test005_makeAccountToSigned() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        String oldName = assetPage.walletNameBtn.getText();
        if (oldName.contains("Signed")){
            Assert.assertTrue(true);
        }else {
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Signed"));
        }
    }

}
