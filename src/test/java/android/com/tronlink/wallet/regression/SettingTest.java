package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * setting function test
 */
public class SettingTest extends Base {
    String beforeLanguage;
    String afterLanguage;
    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(groups = {"P0"},description = "Switch Language Test")
    public void test001_lanaugeSwitchTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.languane_btn.click();
        List<WebElement> languageList = setting.language_list;
        List<WebElement> selectList = setting.selected_btn;
        beforeLanguage = languageList.get(0).getText();
        Assert.assertTrue(languageList.get(0).getText().contains("英文"));
        Assert.assertTrue(languageList.get(1).getText().contains("简体中文"));
        selectList.get(0).click();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test(description = "Language switch success check")
    public void test002_lanaugeContentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.languane_btn.click();
        List<WebElement> languageList = setting.language_list;
        List<WebElement> selectList = setting.selected_btn;
        beforeLanguage = languageList.get(0).getText();
        Assert.assertTrue(languageList.get(0).getText().contains("English"));
        Assert.assertTrue(languageList.get(1).getText().contains("Simplified Chinese"));
        selectList.get(1).click();
        TimeUnit.SECONDS.sleep(3);

    }

    @Test(description = "DAPP Browser Test", alwaysRun = true)
    public void test003_DAPP_Browser() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        DAPP_BrowerPage dapp = mine.enterDAPP_BrowerPage();
        Assert.assertEquals("DApp 浏览器", dapp.dappTtile_btn.getText());
        dapp.testUrl();
        dapp.findElementByText("USDT");
    }

    @Test(description = "Currency Test")
    public void test006_currencyTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.currency_btn.click();
        List<WebElement> currencyList = setting.currency_list;
        Assert.assertTrue(currencyList.get(0).getText().contains("CNY"));
        Assert.assertTrue(currencyList.get(1).getText().contains("USD"));
    }


    @Test(description = "choose Sever Sigapor", alwaysRun = true)
    public void test008_Sever_Setting() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        SeverSetPage sever = setting.enterSeverSetPage();
        sever.Sigapor_btn.click();
        Assert.assertTrue(sever.isElementExist("wallet.tronlink.global:id/iv_select2"));

    }

    @Test(description = "choose SeverUSA", alwaysRun = true)
    public void test009_Sever_Setting() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        SeverSetPage sever = setting.enterSeverSetPage();
        sever.USA_btn.click();
        Assert.assertTrue(sever.isElementExist("wallet.tronlink.global:id/iv_select1"));

    }


}
