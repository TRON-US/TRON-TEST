package android.com.tronlink.wallet.regression;


import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ColdAssetPage;
import android.com.wallet.pages.ColdMinePage;
import android.com.wallet.pages.ColdSettingPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SearchAssertPage;
import android.com.wallet.pages.SettingPage;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 资产页面测试
 */
public class ColdWalletTest extends Base {
    String addressString;


    @Parameters({"privateKey","address","udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey,String address,String udid) throws Exception {
        AppiumTestCase.cmdReturn("adb -s " + udid + " shell svc wifi disable");
        TimeUnit.SECONDS.sleep(3);
        new Helper().getColdWalletSign(privateKey, DRIVER);
        addressString = address;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @Parameters({"udid"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        //Base.tearDownAfterClass();
        try {
            AppiumTestCase.cmdReturn("adb -s " + udid + " shell svc wifi enable");
            TimeUnit.SECONDS.sleep(3);
            DRIVER.quit();
        } catch (Exception e) {
        }

    }


    @Test(enabled = true,description = "Cold wallet asset page test", alwaysRun = true)
    public void test001_enterColdWallet() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        Assert.assertTrue(coldAsset.scanQrSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.receive_btn.isEnabled());
        Assert.assertTrue(coldAsset.offlineSign_btn.isEnabled());
        Assert.assertTrue(coldAsset.deleteColdWalletKnowledge_btn.isEnabled());
        Assert.assertTrue(coldAsset.me_btn.isEnabled());
    }

    @Test(groups = {"P0"},enabled = true,description = "Cold wallet receive test", alwaysRun = true)
    public void test002_coldWalletCanReceiveTrx() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        coldAsset.receive_btn.click();
        Assert.assertTrue(coldAsset.address_text.getText().equals(addressString));
        Assert.assertTrue(coldAsset.copy_btn.isEnabled());
    }

    @Test(enabled = true,description = "Cold wallet knowledge test", alwaysRun = true)
    public void test003_coldWalletKnowledgeTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        coldAsset.coldWalletKnowledge_btn.click();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(coldAsset.knowledgeText_list.get(0).getText().contains("冷钱包的使用说明"));
    }

    @Test(enabled = true,description = "Cold wallet delete knowledge icon test", alwaysRun = true)
    public void test004_coldWalletDeleteKnowledgeBtnTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        Assert.assertTrue(coldAsset.coldWalletKnowledge_btn.isEnabled());

        coldAsset.deleteColdWalletKnowledge_btn.click();
        coldAsset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        Boolean knowldegeBtnIsDelete = false;
        try {
            coldAsset.coldWalletKnowledge_btn.isDisplayed();
        } catch (Exception e) {
            knowldegeBtnIsDelete = true;
        }
        Assert.assertTrue(knowldegeBtnIsDelete);
    }


    @Test(enabled = true,description = "Cold wallet add wallet test", alwaysRun = true)
    public void test005_coldWalletAddWalletTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        coldAsset.addWallet_btn.click();
        coldAsset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        try {
            coldAsset.accountType_normalAccount.click();
        } catch (Exception e) {

        }
        Assert.assertTrue(coldAsset.privateKey_btn.isEnabled());
        Assert.assertTrue(coldAsset.createWallet_btn.isEnabled());
        Assert.assertTrue(coldAsset.keystore_btn.isEnabled());
        Assert.assertTrue(coldAsset.mnemonic_btn.isEnabled());

        coldAsset.keystore_btn.click();
        coldAsset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Assert.assertTrue(coldAsset.privateKeyQrScan_btn.isEnabled());
    }



    @Test(groups = {"P0"},enabled = true,description = "Cold wallet manager test", alwaysRun = true)
    public void test006_coldWalletManagerTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        ColdMinePage coldMine = coldAsset.enterColdMinePage();
        coldMine.walletManager_btn.click();
        coldMine.address_test.getText().equals(addressString);
        coldMine.walletName_test.getText().contains("Cold");
    }

    @Test(groups = {"P0"},enabled = true,description = "Cold wallet setting test", alwaysRun = true)
    public void test007_coldWalletSettingTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        ColdMinePage coldMine = coldAsset.enterColdMinePage();
        ColdSettingPage coldSetting = coldMine.enterSettingPage();
        Assert.assertTrue(coldSetting.languane_btn.isEnabled());
        Assert.assertTrue(coldSetting.currency_btn.isEnabled());

    }

    @Test(enabled = true,description = "Cold wallet language switch success check")
    public void test008_coldLanaugeContentTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        ColdMinePage coldMine = coldAsset.enterColdMinePage();
        ColdSettingPage coldSetting = coldMine.enterSettingPage();
        coldSetting.languane_btn.click();
        List<WebElement> languageList = coldSetting.language_list;
        List<WebElement> selectList = coldSetting.selected_btn;
        Assert.assertTrue(languageList.get(0).getText().contains("English"));
        Assert.assertTrue(languageList.get(1).getText().contains("简体中文"));
        selectList.get(1).click();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test(enabled = true,description = "Cold wallet currency Test")
    public void test009_coldCurrencyTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.currency_btn.click();
        List<WebElement> currencyList = setting.currency_list;
        Assert.assertTrue(currencyList.get(0).getText().contains("CNY"));
        Assert.assertTrue(currencyList.get(1).getText().contains("USD"));
    }

    @Test(enabled = true,description = "coldWalletCloseSafeTipsTest", alwaysRun = true)
    public void test010_coldWalletCloseSafeTipsTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        waiteTime();
        coldAsset.addWallet_btn.click();
        waiteTime();
        coldAsset.create_wallet.click();
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/et_name").sendKeys("SafeTipsM");
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/creat").click();
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/creat").click();
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/creat").click();
        TimeUnit.SECONDS.sleep(10);
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/ll_common_left").click();

        if (coldAsset.isElementExist("com.tronlinkpro.wallet:id/rl_cold_tip")){ //不应该出现
            coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/iv_cold_close").click();
        }

        if (coldAsset.isElementExist("com.tronlinkpro.wallet:id/rl_safe_tip")){
            log("开始测试关闭备份助记词提示框");
            coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/iv_close").click();
            TimeUnit.SECONDS.sleep(1);
            Assert.assertFalse(coldAsset.isElementExist("com.tronlinkpro.wallet:id/rl_safe_tip"));
        }

    }

    @Test(enabled = true,description = "coldWalletDeleteSafeTipsWallet", alwaysRun = true)
    public void test011_coldWalletDeleteSafeTipsWallet() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        waiteTime();
        coldAsset.tv_walletname.click();
        TimeUnit.SECONDS.sleep(5);
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/delete").click();
        TimeUnit.SECONDS.sleep(3);
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/et_password").sendKeys("Test0001");
        waiteTime();
        coldAsset.driver.findElementById("com.tronlinkpro.wallet:id/tv_ok").click();
        waiteTime();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(coldAsset.tv_walletname.getText().contains("Cold-test"));

    }




    }
