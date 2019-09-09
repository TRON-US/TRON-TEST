package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AddAssets {

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

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01AddAssets() {
        TronLink.testOperation(driver,TronLink.addAssetId,"click","Enter add asset screen");
        TronLink.testOperation(driver,TronLink.enter_NameIdContractAddress_InputBox_id,"input",String.valueOf(TronLink.assetIdOfQuery),"Input query asset id");
        Assert.assertTrue(driver.findElementById(TronLink.assetDisplayAreaId).isEnabled());
        TronLink.testOperation(driver,TronLink.assetSwitchId,"click","Turn on/off the asset switch");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        TronLink.testOperation(driver,TronLink.assetsDisplayedXPath,"click","Display asset");
        Assert.assertTrue(driver.findElementByXPath(TronLink.assetsDisplayedFirstElementXPath).isDisplayed());
    }


    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
