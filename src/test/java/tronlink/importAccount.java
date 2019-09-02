package tronlink;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class importAccount {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.AcceptImportAccount,"click","click Accept");
        TronLink.testOperation(driver,TronLink.CreateWallet,"click","click create wallet");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
