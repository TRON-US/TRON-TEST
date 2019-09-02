package common.utils;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.xml.serializer.ExtendedContentHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.ldap.ExtendedRequest;

public class TronLink {

  public static String tronLinkUrl = "http://localhost:4723/wd/hub";

  //public static String tronLinkApk = "/Users/tron/Documents/tronlink_baidu_v3.1.0.apk";
  public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
  //public static String tronLinkApk = "/Users/wangzihe/Documents/Android-iTRON-clone/app/qh360/release/app-qh360-release.apk";
  public static String platformVersion = "9";
  public static String deviceName = "Android Device";
  public static String platformName = "Android";
  public static String importAccountId = "com.tronlink.wallet:id/tv_import";
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
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    return;
  }

  public static void stepElement(AndroidDriver driver, String resId, String step) {
    stepElement(driver, resId, step, "");
  }

  public static void stepElement(AndroidDriver driver, String step) {
    stepElement(driver, "", step, "");
  }

  public static void stepElement(AndroidDriver driver, String resId, String step, String input) {
//        if (!resId.isEmpty()){
//            WebDriverWait wait = new WebDriverWait(driver, 5);
//            wait.until(ExpectedConditions
//                .visibilityOfElementLocated(By.id(resId)));
//        }
    waitTargetElementAppear(driver);
    MobileElement element = null;
    if (!resId.isEmpty()) {
      element = (MobileElement) driver.findElementById(resId);
    }
    switch (step) {
      case "click":
        element.click();
        break;
      case "input":
        element.setValue(input);
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
  }

  public static void swipeUp(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.5);
    int starty = (int)(y*0.75);
    int endx = (int)(x*0.5);
    int endy = (int)(y*0.25);
    driver.swipe(startx,starty,endx,endy,500);
  }

  public static void swipeDown(AndroidDriver driver){
    int x = driver.manage().window().getSize().width;
    int y = driver.manage().window().getSize().height;
    int startx = (int)(x*0.5);
    int starty = (int)(y*0.25);
    int endx = (int)(x*0.5);
    int endy = (int)(y*0.75);
    driver.swipe(startx,starty,endx,endy,500);
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
