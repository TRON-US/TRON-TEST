package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportPrivateKey extends BaseTest {

    String privateKey = "6f3f2f61080aea622436c223bbc784c66049e8c4ca77ed98ad723655022ef934";
    String wallet = "WDelWallet";


    public ImportPrivateKeyPage enterImportPrivateKeyPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementByName("导入钱包").click();
        TimeUnit.SECONDS.sleep(2);
        return new ImportPrivateKeyPage(DRIVER);
    }

    @Test(description = "Import PrivateKey Format Incorrect", alwaysRun = true)
    public void test001_importPrivateKeyLengthIncorrect() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        Assert.assertFalse(importPrivateKey.getNext_btn().isEnabled());
        importPrivateKey.interPrivateKey("ecd4bbba178b1b0d2a123123343245463450c1e6e9108e0asdfasfddafjwijfiajsvnxzcviarjfjasfjlafcab");
        Assert.assertTrue(isElementExist("私钥不可超出 64 位，请检查后重试"));
    }

    @Test(description = "Import PrivateKey Format Incorrect", alwaysRun = true)
    public void test002_importPrivateKeyFormatIncorrect() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        Assert.assertFalse(importPrivateKey.getNext_btn().isEnabled());
        importPrivateKey.interPrivateKey("jljaljsdfldjsaaljsfdlkajsdkdfklsjie");
        Assert.assertTrue(isElementExist("请输入有效的私钥、助记词或 Keystore"));
    }


    @Test(description = "Password without uppercase letter", alwaysRun = true)
    public void test003_PrivateKeypasswordWithoutUppercaseLetter() throws Exception {
        ImportPrivateKeyPage importPrivateKey = enterImportPrivateKeyPage();
        importPrivateKey.content_textfield.sendKeys(privateKey);
        closeKeyBoard();
        DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
        WebElement nextButton =  DRIVER.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '导入私钥'");

        List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByClassName("XCUIElementTypeSecureTextField");
        if (Secure.size()>1){
            Secure.get(0).sendKeys("test0001");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            //test009_PrivateKeypasswordIsTooShort
            Secure.get(0).clear();
            Secure.get(0).sendKeys("Abcd123");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("Abcd123");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            //test010_PrivateKeypasswordIsNonumber
            Secure.get(0).clear();
            Secure.get(0).sendKeys("Abcdasdf");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("Abcdasdf");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            //test011_PrivateKeypasswordIsNoLowercase
            Secure.get(0).clear();
            Secure.get(0).sendKeys("ABCDEFGHI");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("ABCDEFGHI");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            //test012_privateKeyIsAllNumbers
            Secure.get(0).clear();
            Secure.get(0).sendKeys("1234567890");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("1234567890");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            //test013_privateKeyWithoutUppercaseLetter
            Secure.get(0).clear();
            Secure.get(0).sendKeys("zxcvbnm1234567");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("zxcvbnm1234567");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());

            Secure.get(0).clear();
            Secure.get(0).sendKeys("Test0001");
            Secure.get(1).clear();
            Secure.get(1).sendKeys("Test0002");
            closeKeyBoard();
            Assert.assertFalse(nextButton.isEnabled());
            Assert.assertTrue(isElementExist(" 两次输入密码不一致"));

        }


    }


}
