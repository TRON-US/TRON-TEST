package android.com.tronlink.wallet.regression;


import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AnnouncementPage;
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
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String address,String udid) throws Exception {
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        new Helper().getColdWalletSign(privateKey, DRIVER);
        addressString = address;
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @Parameters({"udid"})
    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        //Base.tearDownAfterClass();
        try {
            wifiOpen(udid);
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
        Assert.assertTrue(coldAsset.knowledgeText_list.get(0).getText().contains("TronLink冷钱包教程"));
    }

    @Test(enabled = true,description = "Cold wallet delete knowledge icon test", alwaysRun = true)
    public void test004_coldWalletDeleteKnowledgeBtnTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        Assert.assertTrue(coldAsset.coldWalletKnowledge_btn.isEnabled());
        coldAsset.deleteColdWalletKnowledge_btn.click();

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
        Assert.assertTrue(coldAsset.createWallet_btn.isEnabled());
        Assert.assertTrue(coldAsset.importWallet_btn.isEnabled());

    }



    @Test(enabled = true,description = "Cold wallet manager test", alwaysRun = true)
    public void test006_coldWalletManagerTest() throws Exception {
        ColdAssetPage coldAsset = new ColdAssetPage(DRIVER);
        ColdMinePage coldMine = coldAsset.enterColdMinePage();
        coldMine.walletManager_btn.click();
        Assert.assertTrue(coldMine.nav_title.getText().contains("钱包详情"));
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

    @Test(alwaysRun = true)
    public void test010_shieldWalletManageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        Assert.assertTrue(minePage.findByShotId("tv_shield_wallet").getText().contains("匿名账户管理"));
        minePage.findByShotId("tv_shield_wallet").click();
        Assert.assertTrue(minePage.findByShotId("content").getText().contains("当前无匿名账户，是否新建？"));
        Assert.assertTrue(isElementShotId("btn_cancel"));
        Assert.assertTrue(isElementShotId("btn_confirm"));
        minePage.findByShotId("btn_cancel").click();
        Assert.assertFalse(isElementTextExist("创建"));
    }

    @Test(alwaysRun = true)
    public void test011_advanceTest()  throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        Assert.assertTrue(isElementTextExist("高级功能"));
        minePage.findElementByText("高级功能").click();
        Assert.assertTrue(minePage.nav_title.getText().contains("高级功能"));
    }

    @Test(description = "Announcement Test", alwaysRun = true)
    public void test012_AnnouncementTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        minePage.enterAnnouncementPage();
        Assert.assertTrue(assertToast("冷钱包不支持此功能"));
    }


    }
