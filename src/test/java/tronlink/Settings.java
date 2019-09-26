package tronlink;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import common.utils.AppiumTestCase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Settings extends AppiumTestCase {
    public  String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";
    public  String receiverAddress = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
    public  String ownerAddress = "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of";
    public  String testPassword = "Test0001";

    @Test
    public void test01Language() {
        testOperation(  tabMy,"click","click tab My");
         testOperation(  settings,"click","click settings");
         testOperation(  setting_languane,"click","click language");

        List<MobileElement> elements = driver.findElementsById( language_title);
        if (elements.get(0).isDisplayed()){
            elements.get(1).click();
            Assert.assertEquals( getText(  totalAssets),"总资产");
            Assert.assertEquals( getText(  energyOptionIconId),"能量");
            Assert.assertEquals( getText(  bandwidthOptionIconId),"带宽");

             testOperation(  tabMy,"click","click tab My");
             testOperation(  settings,"click","click settings");
             testOperation(  setting_languane,"click","click language");
            elements.get(0).click();

            Assert.assertEquals( getText(  totalAssets),"Total assets");
            Assert.assertEquals( getText(  energyOptionIconId),"Energy");
            Assert.assertEquals( getText(  bandwidthOptionIconId),"Bandwidth");
        }else {
            elements.get(0).click();
            Assert.assertEquals( getText(  totalAssets),"Total assets");
            Assert.assertEquals( getText(  energyOptionIconId),"Energy");
            Assert.assertEquals( getText(  bandwidthOptionIconId),"Bandwidth");

             testOperation(  tabMy,"click","click tab My");
             testOperation(  settings,"click","click settings");
             testOperation(  setting_languane,"click","click language");

            elements.get(1).click();
            Assert.assertEquals( getText(  totalAssets),"总资产");
            Assert.assertEquals( getText(  energyOptionIconId),"能量");
            Assert.assertEquals( getText(  bandwidthOptionIconId),"带宽");
        }
    }

    @Test
    public void test02Currency() {
         testOperation(  tabMy,"click","click tab My");
         testOperation(  settings,"click","click settings");
         testOperation(  setting_currency,"click","click currency");

        List<MobileElement> elements = driver.findElementsById( language_title);
        if (elements.get(0).isDisplayed()){
            elements.get(1).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
             testOperation(  tabAssets,"click","click start up");
            Assert.assertTrue( getText(  moneyValue).indexOf("USD") != -1);

             testOperation(  tabMy,"click","click tab My");
             testOperation(  settings,"click","click settings");
             testOperation(  setting_currency,"click","click currency");

            elements.get(0).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
             testOperation(  tabAssets,"click","click start up");
            Assert.assertTrue( getText(  moneyValue).indexOf("CNY") != -1);

        }else {
            elements.get(0).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
             testOperation(  tabAssets,"click","click start up");
            Assert.assertTrue( getText(  moneyValue).indexOf("CNY") != -1);

             testOperation(  tabMy,"click","click tab My");
             testOperation(  settings,"click","click settings");
             testOperation(  setting_currency,"click","click currency");

            elements.get(1).click();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
             testOperation(  tabAssets,"click","click start up");
            Assert.assertTrue( getText(  moneyValue).indexOf("USD") != -1);
        }
    }


    @Test
    public void test03Conversion() {
         testOperation(  tabMy,"click","click tab My");
         testOperation(  settings,"click","click settings");
         testOperation(  setting_conversion,"click","click conversion");
         testOperation(  mnemonicTool,"input", mnemonicText,"input mnemonicText");
        System.out.println( mnemonicText);
        System.out.println( privateKeyText);
         testOperation(  oneClickConvert,"click","click conversion");
        String privateKeyTest =  getText(  privateKeyText);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test
    public void test04DeveloperOptions() {
         testOperation(  setting_developer,"click","click developer options");
         testOperation(  confirm,"click","click connect");
        Assert.assertTrue( getText(  nodeShastText).indexOf("shasta") != -1);
         testOperation(  setting_developer,"click","click developer options");
         testOperation(  confirm,"click","click close connect");
    }

    @Test
    public void test05DappBrowser() {
         testOperation(  setting_dapp,"click","click dapp browser");
         testOperation(  dappUrl,"input","http://dapp.tronlink.org/#/","input url");
         testOperation(  dappButton,"click","click button");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

}
