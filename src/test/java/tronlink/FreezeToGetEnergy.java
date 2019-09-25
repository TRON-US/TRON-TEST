package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FreezeToGetEnergy extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test04FreezeToGetEnergy() {
         testOperation(  energyOptionIconId,"click","Choose energy option");
         testOperation(  frozenQuantityInputId,"input",String.valueOf( frozenQuantityForEnergy),"Input frozen quantity for energy");
         swipeUp();
         testOperation(  freezeIconId,"click","Click freeze icon");
         testOperation(  freezeNowIconId,"click","Freeze bandwidth now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for freeze energy");
         testOperation(  transactionConfirmButtonId,"click","Confirm the freeze energy transaction");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }


}
