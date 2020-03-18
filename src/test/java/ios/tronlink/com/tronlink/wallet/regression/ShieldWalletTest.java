package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.AppiumTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ImportKeystorePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.PrivateKeySetNamePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.WalletPasswordPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class ShieldWalletTest extends Base {
    String keystore = "{\"version\":3,\"id\":\"9ccf9d8c-3215-481d-b7c7-d4baf18758ed\",\"crypto\":{\"ciphertext\":\"35dea764547ea2b75b9f89b649b26dabe3a3eacc6b800f412bf5f824892a9ecb\",\"cipherparams\":{\"iv\":\"34499d307dc014529f9ec5a6474c502a\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"r\":8,\"p\":6,\"n\":4096,\"dklen\":32,\"salt\":\"a20438bd6dc815473c71f535e0b33f66c2d69f1f2671471a3168198db5f017df\"},\"mac\":\"b5e53e6868c110405b2e8c58ab5878b67f3966e367b6ca23b740f1da00229c98\",\"cipher\":\"aes-128-ctr\"},\"type\":\"private-key\",\"address\":\"4109abebC9f471b3508d23763a3d41d145599485A7\"}";
    String mnemonic = "unique spider amount maple manage apple eight empty area trim energy example";

    @Parameters({"shieldSK", "udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String shieldSK,String udid) throws Exception {
        log("我是BaseTest类的Before");
        System.out.println("pk: " + shieldSK + " udid: " + udid);
        DRIVER.closeApp();
        log("开始移除app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        new Helper().importFirstWallet(Helper.importType.shieldWallet,shieldSK, DRIVER);
    }
    @Parameters({"bundleId"})
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
        try {
            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();
            screenshotAction(name);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
        } catch (Exception e) {
        }

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
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

    @Parameters({"bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String bundleId) throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", bundleId);
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

    @Test(description = "Test shieldWallet is Imported'", alwaysRun = true)
    public void test001_checkImportSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Assert.assertTrue(Helper.contentTexts(asset.nameLabels,"TRZ"));
//        Assert.assertTrue(asset.scanBlockView.isDisplayed());

    }

    @Test(description = "Test shieldWallet Created'", alwaysRun = true)
    public void test002_shieldWalletCreateSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        waiteTime();
        asset.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementById("shieldedWallet").click();
        waiteTime();
        DRIVER.findElementByName("创建钱包").click();
        waiteTime();
        DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys("WalletName");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("下一步").click();
        waiteTime();
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("下一步").click();
        TimeUnit.SECONDS.sleep(5);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("完成").click();
        TimeUnit.SECONDS.sleep(10);
        DRIVER.findElementByName("跳过").click();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(asset.walletNameBtn.getText().contains("WalletName"));
        Assert.assertTrue(Helper.contentTexts(asset.nameLabels,"TRZ"));
//        Assert.assertTrue(asset.scanBlockView.isDisplayed());
    }

    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test003_testDeletewalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage  = assetPage.enterMyPursePage();
        walletPage.deletWallet("Test0001");
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }

    @Test(description = "Test shieldWallet Imported use Mnemonic'", alwaysRun = true)
    public void test004_testImportWithMnemonicSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        waiteTime();
        asset.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementById("shieldedWallet").click();
        waiteTime();
        DRIVER.findElementByName("助记词").click();
        waiteTime();
        DRIVER.findElementByName("HD 钱包").click();
        waiteTime();
        DRIVER.findElementByClassName("XCUIElementTypeTextView").sendKeys(mnemonic);
        Helper.tapWhitePlace(DRIVER);
        waiteTime();
        DRIVER.findElementByName("下一步").click();
        waiteTime();
        DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys("Mnemonic");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("下一步").click();
        waiteTime();
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("下一步").click();
        TimeUnit.SECONDS.sleep(5);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("完成").click();
        TimeUnit.SECONDS.sleep(10);
        Assert.assertTrue(asset.walletNameBtn.getText().contains("Mnemonic"));

    }

    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test005_testDeletewalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage  = assetPage.enterMyPursePage();
        walletPage.deletWallet("Test0001");
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }


    @Test(description = "Test shieldWallet Imported use KeyStore'", alwaysRun = true)
    public void test006_testImportWithKeyStoreSuccess() throws Exception {
        ImportKeystorePage importKeystorePage = getImportKeystorePage();
        PrivateKeySetNamePage setNamePage = importKeystorePage.enterPrivateKeySetNamePage(keystore,"Qqqqqqq1");
        setNamePage.name_input.sendKeys("keystore");
        Helper.tapWhitePlace(DRIVER);
        setNamePage.driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(6);
        AssetPage assetPage = new AssetPage(DRIVER);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("keystore"));
    }

    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test007_testDeletewalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MyPursePage walletPage  = assetPage.enterMyPursePage();
        walletPage.deletWallet("Qqqqqqq1");
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }

    @Parameters({"shieldSK"})
    @Test(description = "Test shieldWallet Imported use same sk privateKey'", alwaysRun = true)
    public void test008_testImportWithsameskprivateKeySuccess(String shieldSK) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        waiteTime();
        asset.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementById("shieldedWallet").click();
        waiteTime();
        DRIVER.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        DRIVER.findElementByName("私钥导入").click();
        DRIVER.findElementByClassName("XCUIElementTypeTextView").sendKeys(shieldSK);
        Helper.tapWhitePlace(DRIVER);
        DRIVER.findElementByName("下一步").click();
        waiteTime();
        Assert.assertTrue(DRIVER.findElementById("errorStr").getText().contains("已存在"));

    }



    public ImportKeystorePage getImportKeystorePage(){
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementById("shieldedWallet").click();
        waiteTime();
        DRIVER.findElementByName("Keystore").click();
        return new ImportKeystorePage(DRIVER);
    }

}
