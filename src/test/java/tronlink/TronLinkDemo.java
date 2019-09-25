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

public class TronLinkDemo {

  private AndroidDriver driver;

  @BeforeClass
  public void setUp() throws MalformedURLException {
    TronLink.screenOn();
    driver = InitTest.driver;
  }

  @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
  public void sampleTest() {
    TronLink.testOperation(driver,"swipeLeft","");
    TronLink.testOperation(driver,"swipeRight","");
    TronLink.testOperation(driver, TronLink.importAccountId,"click");
    TronLink.testOperation(driver,"swipeUp","");
    TronLink.testOperation(driver,"swipeUp","");
    TronLink.waitTargetElementAppear(driver);
  }

  @AfterClass
  public void tearDown() {
    driver.resetApp();
  }
}
