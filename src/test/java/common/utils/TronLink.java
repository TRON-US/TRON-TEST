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
  //public static String tronLinkUrl = "http://192.168.56.101:5555";

//  public static String tronLinkApk = "/Users/tron/Documents/testnet-tronlink.apk";
  //public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
  public static String tronLinkApk = "/Users/wangzihe/Documents/Android-iTRON-clone/app/baidu/release/app-baidu-release.apk";
  public static String platformVersion = "9";
  public static String deviceName = "Android Device";
  //public static String deviceName = "192.168.56.101:5555";
  public static String platformName = "Android";
  public static String importAccountId = "com.tronlink.wallet:id/tv_import";
  public static String createAccountId = "com.tronlink.wallet:id/tv_create";
  public static String sendCoinId = "com.tronlink.wallet:id/rl_send";
  public static String sendCoinXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout[1]/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]";
  public static String receiveCoinId = "com.tronlink.wallet:id/rl_receive";
  public static String receiveCoinXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout[1]/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]";
  public static String freezeBalanceId = "com.tronlink.wallet:id/rl_freeze_unfreeze";
  public static String tronLendingId = "com.tronlink.wallet:id/rl_energy_lease";
  public static String voteId = "com.tronlink.wallet:id/rl_vote";
  public static String addAsset = "com.tronlink.wallet:id/rl_add_assets";
  public static String sendCoinAmountId = "com.tronlink.wallet:id/et_count";
  public static Long sendCoinAmount = 1L;
  public static String sendCoinButtonId = "com.tronlink.wallet:id/send";
  public static String transferNowId = "com.tronlink.wallet:id/bt_go";
  public static String transferConfirmButtonId = "com.tronlink.wallet:id/bt_send";
  public static String transferInputPasswordId = "com.tronlink.wallet:id/et_new_password";
  public static String receiveAddressId = "com.tronlink.wallet:id/et_address";
  public static String acceptImportAccount = "com.tronlink.wallet:id/bt_accept";
  public static String assetIconId = "com.tronlink.wallet:id/assets";
  public static String marketsIconId = "com.tronlink.wallet:id/appmarket";
  public static String discoverIconId = "com.tronlink.wallet:id/app1";
  public static String meIconId = "com.tronlink.wallet:id/my";
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
  public static String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";
  public static String receiverAddress = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
  public static String testPassword = "Test0001";

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
    if (resId.contains("hierarchy")){
      element = (MobileElement) driver.findElement(MobileBy.xpath(resId));
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
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width/2, height*4/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height/5))
            .release().perform();
  }

  public static void swipeDown(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width/2, height/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height*4/5))
            .release().perform();
  }

  public static void swipeRight(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width*3/4, height/2))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/4, height/2))
            .release().perform();
  }

  public static void swipeLeft(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width/4, height/2))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width*3/4, height/2))
            .release().perform();
  }

//get screenshot
  public static void getScreenshot(AndroidDriver driver,String description){
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    String date = df.format(new Date());
    if(description.equals("got it") || description.equals("back up now")) {
      return;
    }
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

  public static AndroidDriver importWallet(AndroidDriver driver,String privateKey) {
    try {
      TronLink.testOperation(driver, TronLink.importAccountId, "click", "click import Account");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, "swipeUp", "");
      TronLink.testOperation(driver, TronLink.acceptImportAccount, "click", "click Accept");
      //use Private Key import account
      TronLink.testOperation(driver, TronLink.privateKey, "click", "click Private key");
      TronLink.testOperation(driver, TronLink.enterContent, "input", privateKey, "enter private key");
      TronLink.testOperation(driver, TronLink.nextStep, "click", "click Next step");
      Date date = new Date();
      String timestamp = String.valueOf(date.getTime());
      TronLink.testOperation(driver, TronLink.setUpName, "input", "Test_" + timestamp, "input name");
      TronLink.testOperation(driver, TronLink.creatNextStep, "click", "1:input name");
      TronLink.testOperation(driver, TronLink.passWord, "input", testPassword, "input password");
      TronLink.testOperation(driver, TronLink.creatNextStep2, "click", "2:click next step");
      TronLink.testOperation(driver, TronLink.passWord, "input", testPassword, "input password again");
      TronLink.testOperation(driver, TronLink.creatNextStep3, "click", "3:click carry out");
    }
    catch (Exception ex) {
      System.out.print(ex);
      return null;
    }
    return driver;
  }

}
