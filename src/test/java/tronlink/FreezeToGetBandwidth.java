package tronlink;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FreezeToGetBandwidth extends AppiumTestCase {

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test03FreezeToGetBandwidth() {
        //Freeze 细则说明
         testOperation(  freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
         testOperation(  freezeRuleId,"click","Enter to freeze rule screen");
        Assert.assertTrue( getText(  freezeDoc1Id).contains("TRX"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        // testOperation(  freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
         testOperation(  bandwidthOptionIconId,"click","Choose bandwidth option");
         testOperation(  frozenQuantityInputId,"input",String.valueOf( frozenQuantityForBandwidth),"Input frozen quantity for bandwidth");
         swipeUp();
         testOperation(  freezeIconId,"click","Click freeze icon");
         testOperation(  freezeNowIconId,"click","Freeze bandwidth now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for freeze bandwidth");
         testOperation(  transactionConfirmButtonId,"click","Confirm the freeze bandwidth transaction");
    }



}
