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

public class MutiSignature {

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
    public void test01MutiSignScreen() {
        //Enter to Multi-signature Management screen
        TronLink.testOperation(driver,TronLink.meIconId,"click","Enter me screen");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","Enter my wallet manager screen");
        TronLink.testOperation(driver,TronLink.mutliSignatureManagementId,"click","Enter to multi-signature screen");

        //Mutli sign question content
        TronLink.testOperation(driver,TronLink.mutliSignQuestionId,"click","View the multi sign question content");
        String multiSignQuestionContent = TronLink.getText(driver,TronLink.mutliSignQuestionContentXPath);
        Assert.assertTrue(multiSignQuestionContent.contains("Active Permission"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Add permission
        TronLink.testOperation(driver,TronLink.mutliSignAddPermissionId,"click","Enter add permission screen");
        TronLink.testOperation(driver,TronLink.activeScreenMoreId,"click","Show more in add active screen");


    }


    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
