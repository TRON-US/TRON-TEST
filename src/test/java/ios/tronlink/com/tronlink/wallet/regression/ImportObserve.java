package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportObserve extends BaseTest {

    String addressNew = "TXzBUz6qrD2AXmJsMVamTaWudoUsAPGqcG";


    public ObservePage enterImportObservePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet();
        DRIVER.findElementByName("添加观察钱包").click();
        TimeUnit.SECONDS.sleep(2);
        return new ObservePage(DRIVER);
    }

    @Test(description = "Observe Address Format Incorrect", alwaysRun = true)
    public void test001_importObserveFormatIncorrect() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        Assert.assertFalse(importObservePage.getNext_btn().isEnabled());
        importObservePage.inputAddreseString("ecd4bbba178b1b0d2a0c1e6e9108e0cab8");
        Assert.assertTrue(isElementExist(" 账户地址格式不正确"));

    }


    @Test(description = "Observe Name Too Long", alwaysRun = true)
    public void test002_ObserveNameTooLong() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(addressNew);
        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
        List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByClassName("XCUIElementTypeTextField");
        if (Secure.size() > 1) {
            Secure.get(1).click();
            DRIVER.findElementByName("清除文本").click();
            Secure.get(1).sendKeys("12345678901234567890123456789");
            closeKeyBoard();
            Assert.assertTrue(Secure.get(1).getText().equalsIgnoreCase("1234567890123456789012345678"));
        }
    }

    @Test(description = "Observe Name chinese Too Long", alwaysRun = true)
    public void test003_ObserveNameChineseTooLongAndAddSuccessTest() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(addressNew);
        List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByClassName("XCUIElementTypeTextField");
        if (Secure.size() > 1) {
            Secure.get(1).click();
            DRIVER.findElementByName("清除文本").click();
            Secure.get(1).sendKeys("一二三四五六七八九十十一十四十六十八二十二十三二十六二十九");
            closeKeyBoard();
        }
        DRIVER.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND label = '添加观察钱包'").click();
        TimeUnit.SECONDS.sleep(1);
        AssetPage assetPage = new AssetPage(DRIVER);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("一二三四五六七八九十十一十四十六十八二十二十三二十六二十"));
    }


    @Test(description = "test Delete Wallet  password",alwaysRun = true)
    public void  test004_testDeleteWalletSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.enterMyPursePage();
        Helper.swipScreen(DRIVER);
        DRIVER.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '删除钱包'").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确认'").click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
    }

    @Test(description = "import Cold Wallet Test",alwaysRun = true)
    public void  test005_importColdWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.addWallet();
        asset.enterPairColdWallet();
        Assert.assertTrue(isElementExist("配对冷钱包"));
        Assert.assertTrue(isElementExist("实时查看钱包的余额和交易信息，且可配合另一台设备的“离线冷钱包”互相扫码完成签名，以便提升资产安全。"));
        asset.enterAddressColdWallet();
        Assert.assertTrue(isElementExist("请使用冷钱包扫描二维码"));
    }

}
