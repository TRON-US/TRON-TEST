package ios.tronlink.com.tronlink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ImportPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class ImportMnemonic extends BaseTest {
    String mnemonic = "welcome aim afford bid shine cousin session angry speed satisfy field correct ";

    @Test(description = "Import Mnemonic",alwaysRun = true)
    public void test001_importMnemonicSuccess() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        ImportPage importpage = assetPage.enterImportPage();
        importpage.mnemonicButton.click();
        importpage.HDWalletButton.click();
        importpage.inputTextField.sendKeys(mnemonic);
        Helper.closeKeyBoard(importpage.driver);
        importpage.nextButton.click();
        Assert.assertTrue(Helper.isElementExist(importpage.driver,"TWiop6oghdTq1iTxfSEwxtZRwJnfgS6s1m"));
        importpage.inputTextField.sendKeys("mnemonic");
        Helper.closeKeyBoard(importpage.driver);
        importpage.nextButton.click();
        importpage.inputPasswordTextField.sendKeys("Test0001");
        Helper.closeKeyBoard(importpage.driver);
        importpage.nextButton.click();
        importpage.inputPasswordTextField.click();
        importpage.inputPasswordTextField.sendKeys("Test0001");
        Helper.closeKeyBoard(importpage.driver);
        importpage.queding_btn().click();
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(Helper.isElementExist(importpage.driver,"TWiop6oghdTq1iTxfSEwxtZRwJnfgS6s1m"));
        Assert.assertTrue(Helper.isElementExist(importpage.driver,"mnemonic"));
    }

}
