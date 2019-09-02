package common.utils;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.util.FileUtils;
import org.apache.xml.serializer.ExtendedContentHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.FileCopyUtils;

import javax.naming.ldap.ExtendedRequest;

public class TronLink {

  public static String tronLinkUrl = "http://localhost:4723/wd/hub";

  public static String tronLinkApk = "/Users/tron/Documents/testnet-tronlink.apk";
//  public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
  //public static String tronLinkApk = "/Users/wangzihe/Documents/Android-iTRON-clone/app/qh360/release/app-qh360-release.apk";
  public static String platformVersion = "9";
  public static String deviceName = "Android Device";
  public static String platformName = "Android";
  public static String importAccountId = "com.tronlink.wallet:id/tv_import";
  public static String createAccountId = "com.tronlink.wallet:id/tv_create";
  public static String acceptImportAccount = "com.tronlink.wallet:id/bt_accept";
  public static String privateKey = "com.tronlink.wallet:id/cd_pk";
  public static String enterContent = "com.tronlink.wallet:id/et_content";
  public static String nextStep = "com.tronlink.wallet:id/bt_next";
  public static String privateKeyQR = "com.tronlink.wallet:id/iv_qr";
  public static String mnemonic = "com.tronlink.wallet:id/rl_mm";
  public static String keystore = "com.tronlink.wallet:id/cd_kt";
  public static String watchWallet = "com.tronlink.wallet:id/cd_ow";

  public static String createWallet = "com.tronlink.wallet:id/cd_cw";
  public static String setUpName = "com.tronlink.wallet:id/et_name";
  public static String creatNextStep = "com.tronlink.wallet:id/creat";
  public static String passWord = "com.tronlink.wallet:id/et_password";
  public static String creatNextStep2 = "com.tronlink.wallet:id/creat";
  public static String creatNextStep3 = "com.tronlink.wallet:id/creat";
  public static String riskIgnore = "com.tronlink.wallet:id/tv_cancle";
  public static String riskBackup = "com.tronlink.wallet:id/tv_ok";
  public static String addressText = "com.tronlink.wallet:id/tv_address";
  public static String backupPrivateKey = "com.tronlink.wallet:id/rl_privatekey2";
  public static String backupKeystore = "com.tronlink.wallet:id/rl_keystore2";
  public static String walletPassword = "com.tronlink.wallet:id/et_password";
  public static String confirm = "com.tronlink.wallet:id/tv_ok";
  public static String privateKeyText = "com.tronlink.wallet:id/tv_privatekey";
  public static String Done = "com.tronlink.wallet:id/backup";
  public static String backUpNow = "com.tronlink.wallet:id/backup";
  public static String gotItButton = "com.tronlink.wallet:id/bt_know";
  public static String saveKey = "com.tronlink.wallet:id/save";
  public static String keyIndexText = "com.tronlink.wallet:id/text";

  private AndroidDriver driver;


  public static DesiredCapabilities getTronLinkDesiredCapabilities(
      DesiredCapabilities desiredCapabilities) {
    desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
    desiredCapabilities.setCapability("platformName", TronLink.platformName);
    desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
    desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
    return desiredCapabilities;
  }

  public static void waitTargetElementAppear(AndroidDriver driver) {
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    return;
  }
//click action
  public static void testOperation(AndroidDriver driver, String resId, String step ,String description) {
    testOperation(driver, resId, step, "",description);
  }
//swipe action
  public static void testOperation(AndroidDriver driver, String step, String description) {
    testOperation(driver, "", step, "",description);
  }


  public static void testOperation(AndroidDriver driver, String resId, String action, String input, String description) {
    waitTargetElementAppear(driver);
    MobileElement element = null;
    if (!resId.isEmpty()) {
      if (resId.indexOf("com.tronlink.wallet:id") != -1){
        element = (MobileElement) driver.findElementById(resId);
      }else {
        element = (MobileElement) driver.findElementByXPath(resId);
      }
    }
    switch (action) {
      case "click":
        element.click();
        break;
      case "input":
        element.click();
        element.setValue(input);
        (new TouchAction(driver)).tap(657, 495).perform();
        break;
      case "swipeUp":
        swipeUp(driver);
        break;
      case "swipeDown":
        swipeDown(driver);
        break;
      case "swipeRight":
        swipeRight(driver);
        break;
      case "swipeLeft":
        swipeLeft(driver);
        break;
    }
    getScreenshot(driver,description);
  }

  public static void swipeUp(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.5);
    int starty = (int)(y*0.75);
    int endx = (int)(x*0.5);
    int endy = (int)(y*0.25);
    driver.swipe(startx,starty,endx,endy,100);
  }

  public static void swipeDown(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.5);
    int starty = (int)(y*0.25);
    int endx = (int)(x*0.5);
    int endy = (int)(y*0.75);
    driver.swipe(startx,starty,endx,endy,100);
  }

  public static void swipeRight(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.25);
    int starty = (int)(y*0.5);
    int endx = (int)(x*0.75);
    int endy = (int)(y*0.5);
    driver.swipe(startx,starty,endx,endy,500);
  }

  public static void swipeLeft(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.75);
    int starty = (int)(y*0.5);
    int endx = (int)(x*0.25);
    int endy = (int)(y*0.5);
    driver.swipe(startx,starty,endx,endy,500);
  }

//get screenshot
  public static void getScreenshot(AndroidDriver driver,String description){
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    String date = df.format(new Date());
    File screen = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
    File screenFile = new File("build/reports/tests/tronlink/screenShot/"+date+description+".png");
    try {
      org.apache.commons.io.FileUtils.copyFile(screen,screenFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  //判断设备是否休眠，并解锁设备
  public static void screenOn() {
    try {
      Runtime rt = Runtime.getRuntime();
      Process p = rt.exec("adb shell dumpsys power | findstr \"Display Power:state=\"");
      BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      String content = "";
      while ((line = in.readLine()) != null) {
        content = content + line;
      }
      if (content.contains("Display Power: state=OFF")) {
        // 模拟电源键
        Runtime.getRuntime().exec("adb shell input keyevent 26");
        // 模拟Home键
        Runtime.getRuntime().exec("adb shell input keyevent 3");
      }
      p.destroy();
    } catch (IOException ex) {
      return;
    }
  }

}
