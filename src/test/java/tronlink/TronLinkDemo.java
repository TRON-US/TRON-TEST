package tronlink;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TronLinkDemo {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        TronLink.waitTargetElementAppear(driver);
        MobileElement tronElement = (MobileElement) driver.findElementById(TronLink.importAccountId);
        TronLink.waitTargetElementAppear(driver);
        tronElement.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public static MobileElement mobileElement;


}
