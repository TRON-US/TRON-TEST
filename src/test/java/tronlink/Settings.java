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
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
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
        }else {
            elements.get(0).click();
            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"Total assets");
            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"Energy");
            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"Bandwidth");
        }
    }

//    @Test
//    public void test02Currency() {
//        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
//        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
//        TronLink.testOperation(driver,TronLink.setting_languane,"click","click language");
//
//        List<MobileElement> elements = driver.findElementsById(TronLink.language_title);
//        if (elements.get(0).isDisplayed()){
//            elements.get(1).click();
//            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"总资产");
//            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"能量");
//            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"带宽");
//        }else {
//            elements.get(0).click();
//            Assert.assertEquals(TronLink.getText(driver,TronLink.totalAssets),"Total assets");
//            Assert.assertEquals(TronLink.getText(driver,TronLink.energyOptionIconId),"Energy");
//            Assert.assertEquals(TronLink.getText(driver,TronLink.bandwidthOptionIconId),"Bandwidth");
//        }
//    }
//



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
