package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FreezeToGetEnergy {

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
    public void test04FreezeToGetEnergy() {
        TronLink.testOperation(driver,TronLink.energyOptionIconId,"click","Choose energy option");
        TronLink.testOperation(driver,TronLink.frozenQuantityInputId,"input",String.valueOf(TronLink.frozenQuantityForEnergy),"Input frozen quantity for energy");
        TronLink.swipeUp(driver);
        TronLink.testOperation(driver,TronLink.freezeIconId,"click","Click freeze icon");
        TronLink.testOperation(driver,TronLink.freezeNowIconId,"click","Freeze bandwidth now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for freeze energy");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the freeze energy transaction");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
