package ios.tronlink.com.tronlink.wallet.regression;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ImportPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class ImportMnemonic extends BaseTest {
    String mnemonic = "welcome aim afford bid shine cousin session angry speed satisfy field correct ";

    @Test(description = "Import Mnemonic",alwaysRun = true)
    public void test001_importMnemonicSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        ImportPage importpage = assetPage.enterImportPage();
        importpage.inputTextField.sendKeys(mnemonic);
        closeKeyBoard();
        importpage.nextButton.click();
        Helper.swipScreenLitter(importpage.driver);
        importpage.inputTextField.click();
        DRIVER.findElementByName("清除文本").click();
        importpage.inputTextField.sendKeys("name");
        closeKeyBoard();
        List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByClassName("XCUIElementTypeSecureTextField");
        if (Secure.size()>1){
            Secure.get(0).sendKeys("Test0002");
            closeKeyBoard();
            Secure.get(1).sendKeys("Test0002");
            closeKeyBoard();
        }
        DRIVER.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '导入助记词'").click();
        TimeUnit.SECONDS.sleep(6);
        Assert.assertTrue(Helper.isElementExist(importpage.driver,"TWiop6oghdTq1iTxfSEwxtZRwJnfgS6s1m"));
        Assert.assertTrue(Helper.isElementExist(importpage.driver,"确认导入"));
        DRIVER.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '确认导入'").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("name"));

    }

     @Test(alwaysRun = true)
     public void test002_deleteMnemonicWalletTest() throws Exception {
         AssetPage assetPage = new AssetPage(DRIVER);
         MyPursePage walletPage = assetPage.enterMyPursePage();
         walletPage.deleteWallet("Test0002");
         TimeUnit.SECONDS.sleep(2);
         Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
     }
}
