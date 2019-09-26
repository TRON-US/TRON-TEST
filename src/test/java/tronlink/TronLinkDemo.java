package tronlink;

import common.utils.AppiumTestCase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TronLinkDemo extends AppiumTestCase {

  @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
  public void sampleTest() {
      importWallet(testPrivateKey);

      testOperation( "swipeLeft","");
     testOperation( "swipeRight","");
     testOperation(   importAccountId,"click");
     testOperation( "swipeUp","");
     testOperation( "swipeUp","");
     waitTargetElementAppear();
  }


}
