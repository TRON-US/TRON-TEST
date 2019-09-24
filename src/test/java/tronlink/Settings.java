package tronlink;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Settings {
    public static String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";
    public static String receiverAddress = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
    public static String ownerAddress = "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of";
    public static String testPassword = "Test0001";
    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = TronLink.driverTron;
        driver = TronLink.createWallet(driver);
    }

    @Test
    public void test01Language() {
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
        TronLink.testOperation(driver,TronLink.setting_languane,"click","click language");

        List<MobileElement> elements = driver.findElementsById(TronLink.language_title);
        if (elements.get(0).isDisplayed()){
            elements.get(1).click();
            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"总资产");
            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"能量");
            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"带宽");

            TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
            TronLink.testOperation(driver,TronLink.settings,"click","click settings");
            TronLink.testOperation(driver,TronLink.setting_languane,"click","click language");
            elements.get(0).click();

            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"Total assets");
            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"Energy");
            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"Bandwidth");
        }else {
            elements.get(0).click();
            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"Total assets");
            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"Energy");
            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"Bandwidth");

            TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
            TronLink.testOperation(driver,TronLink.settings,"click","click settings");
            TronLink.testOperation(driver,TronLink.setting_languane,"click","click language");

            elements.get(1).click();
            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"总资产");
            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"能量");
            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"带宽");
        }
    }

    @Test
    public void test02Currency() {
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
        TronLink.testOperation(driver,TronLink.setting_currency,"click","click currency");

        List<MobileElement> elements = driver.findElementsById(TronLink.language_title);
        if (elements.get(0).isDisplayed()){
            elements.get(1).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            TronLink.testOperation(driver,TronLink.tabAssets,"click","click start up");
            Assert.assertTrue(TronLink.getText(driver,TronLink.moneyValue).indexOf("USD") != -1);

            TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
            TronLink.testOperation(driver,TronLink.settings,"click","click settings");
            TronLink.testOperation(driver,TronLink.setting_currency,"click","click currency");

            elements.get(0).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            TronLink.testOperation(driver,TronLink.tabAssets,"click","click start up");
            Assert.assertTrue(TronLink.getText(driver,TronLink.moneyValue).indexOf("CNY") != -1);

        }else {
            elements.get(0).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            TronLink.testOperation(driver,TronLink.tabAssets,"click","click start up");
            Assert.assertTrue(TronLink.getText(driver,TronLink.moneyValue).indexOf("CNY") != -1);

            TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
            TronLink.testOperation(driver,TronLink.settings,"click","click settings");
            TronLink.testOperation(driver,TronLink.setting_currency,"click","click currency");

            elements.get(1).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            TronLink.testOperation(driver,TronLink.tabAssets,"click","click start up");
            Assert.assertTrue(TronLink.getText(driver,TronLink.moneyValue).indexOf("USD") != -1);
        }
    }


    @Test
    public void test03Conversion() {
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
        TronLink.testOperation(driver,TronLink.setting_conversion,"click","click conversion");
        TronLink.testOperation(driver,TronLink.mnemonicTool,"input",TronLink.mnemonicText,"input mnemonicText");
        System.out.println(TronLink.mnemonicText);
        System.out.println(TronLink.privateKeyText);
        TronLink.testOperation(driver,TronLink.oneClickConvert,"click","click conversion");
        String privateKeyTest = TronLink.getText(driver,TronLink.privateKeyText);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test
    public void test04DeveloperOptions() {
        TronLink.testOperation(driver,TronLink.setting_developer,"click","click developer options");
        TronLink.testOperation(driver,TronLink.confirm,"click","click connect");
        Assert.assertTrue(TronLink.getText(driver,TronLink.nodeShastText).indexOf("shasta") != -1);
        TronLink.testOperation(driver,TronLink.setting_developer,"click","click developer options");
        TronLink.testOperation(driver,TronLink.confirm,"click","click close connect");
    }

    @Test
    public void test05DappBrowser() {
        TronLink.testOperation(driver,TronLink.setting_dapp,"click","click dapp browser");
        TronLink.testOperation(driver,TronLink.dappUrl,"input","http://dapp.tronlink.org/#/","input url");
        TronLink.testOperation(driver,TronLink.dappButton,"click","click button");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }



    @AfterClass
    public void tearDown() {
        driver.resetApp();
    }
}
