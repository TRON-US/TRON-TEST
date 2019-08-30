package apptest;

import io.appium.java_client.MobileElement;
        import io.appium.java_client.android.AndroidDriver;
        import junit.framework.TestCase;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import java.net.MalformedURLException;
        import java.net.URL;
        import org.openqa.selenium.remote.DesiredCapabilities;

public class TronLinkTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Android Device");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("app", "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("com.tronlink.wallet:id/tv_import");
        el1.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
