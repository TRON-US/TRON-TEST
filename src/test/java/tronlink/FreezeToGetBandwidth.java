package tronlink;

import org.junit.Assert;
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

public class FreezeToGetBandwidth {

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
    public void test03FreezeToGetBandwidth() {
        //Freeze 细则说明
        TronLink.testOperation(driver,TronLink.freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
        TronLink.testOperation(driver,TronLink.freezeRuleId,"click","Enter to freeze rule screen");
        Assert.assertTrue(TronLink.getText(driver,TronLink.freezeDoc1Id).contains("TRX"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //TronLink.testOperation(driver,TronLink.freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
        TronLink.testOperation(driver,TronLink.bandwidthOptionIconId,"click","Choose bandwidth option");
        TronLink.testOperation(driver,TronLink.frozenQuantityInputId,"input",String.valueOf(TronLink.frozenQuantityForBandwidth),"Input frozen quantity for bandwidth");
        TronLink.swipeUp(driver);
        TronLink.testOperation(driver,TronLink.freezeIconId,"click","Click freeze icon");
        TronLink.testOperation(driver,TronLink.freezeNowIconId,"click","Freeze bandwidth now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for freeze bandwidth");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the freeze bandwidth transaction");
    }


    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
