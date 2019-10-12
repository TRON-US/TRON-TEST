package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AddAssets extends AppiumTestCase {

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01AddAssets() {
        importWallet(testPrivateKey);
         testOperation(  addAssetId,"click","Enter add asset screen");
         testOperation(  enter_NameIdContractAddress_InputBox_id,"input",String.valueOf( assetIdOfQuery),"Input query asset id");
         Assert.assertTrue(driver.findElementById( assetDisplayAreaId).isEnabled());
         testOperation(  assetSwitchId,"click","Turn on/off the asset switch");
         driver.pressKey(new KeyEvent(AndroidKey.BACK));
//         testOperation(  assetsDisplayedXPath,"click","Display asset");
//         Assert.assertTrue(driver.findElementByXPath( assetsDisplayedFirstElementXPath).isDisplayed());
    }

    @AfterClass
    public void teardown(){
        driver.resetApp();
    }
}
