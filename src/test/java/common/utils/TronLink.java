package common.utils;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.touch.WaitOptions;

public class TronLink {

  public static String tronLinkUrl = "http://localhost:4723/wd/hub";

  public static String tronLinkApk = "/Users/tron/Documents/testnet-tronlink.apk";
  //public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
//  public static String tronLinkApk = "/Users/wangzihe/Documents/Android-iTRON-clone/app/baidu/release/app-baidu-release.apk";
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

  public static String tabAssets = "com.tronlink.wallet:id/assets";
  public static String tabAppmarket = "com.tronlink.wallet:id/appmarket";
  public static String tabApp1 = "com.tronlink.wallet:id/app1";
  public static String tabMy = "com.tronlink.wallet:id/my";

  public static String my_walletManager = "com.tronlink.wallet:id/wallet_manager";
  public static String deleteWallet = "com.tronlink.wallet:id/delete";
  public static String etPassword = "com.tronlink.wallet:id/et_password";

  private AndroidDriver driver;
  public static AndroidTouchAction action;


  public static DesiredCapabilities getTronLinkDesiredCapabilities(
      DesiredCapabilities desiredCapabilities) {
    desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
    desiredCapabilities.setCapability("platformName", TronLink.platformName);
    desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
    desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
    return desiredCapabilities;
  }

  public static void waitTargetElementAppear(AndroidDriver driver) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        //element = (MobileElement) driver.findElement(MobileBy.id(resId));
        //element = driver.findElement(MobileBy.id(resId));

      }else {
        element = (MobileElement) driver.findElement(MobileBy.id(resId));
        //element = (MobileElement) driver.findElementByXPath(resId);

      }
    }
    switch (action) {
      case "click":
        element.click();
        break;
      case "input":
        element.click();
        element.setValue(input);
        driver.navigate().back();
//        (new TouchAction(driver)).tap(657, 495).perform();
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
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(100);
    //action.press(PointOption.point(511,1789)).moveTo(PointOption.point(511,420)).release().perform();
    action.press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/2, height/4)).release().perform();

    //action.longPress(LongPressOptions.longPressOptions().withDuration(duration));



  }

  public static void swipeDown(AndroidDriver driver){
    (new TouchAction(driver)).press(PointOption.point(511,420)).moveTo(PointOption.point(511,1789)).release().perform();
  }

  public static void swipeRight(AndroidDriver driver){
    (new TouchAction(driver)).press(PointOption.point(700,1789)).moveTo(PointOption.point(300,1789)).release().perform();
  }

  public static void swipeLeft(AndroidDriver driver){
    (new TouchAction(driver)).press(PointOption.point(300,1789)).moveTo(PointOption.point(700,1789)).release().perform();
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

  public static boolean isElement(AndroidDriver driver,String element){
    boolean flag = false;
    if (!element.isEmpty()){
      try {
        driver.findElementsById(element);
        flag = true;
      }catch (Exception e){
        e.printStackTrace();
        flag = false;
      }
    }
    return flag;
  }



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
        Runtime.getRuntime().exec("adb shell input keyevent 26");
        Runtime.getRuntime().exec("adb shell input keyevent 3");
      }
      p.destroy();
    } catch (IOException ex) {
      return;
    }
  }

}
