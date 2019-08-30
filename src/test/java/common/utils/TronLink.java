package common.utils;


import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class TronLink {
    public static String tronLinkUrl = "http://localhost:4723/wd/hub";
    public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
    public static String platformVersion = "9";
    public static String deviceName = "Android Device";
    public static String platformName = "Android";
    public static String importAccountId = "com.tronlink.wallet:id/tv_import";
    private AndroidDriver driver;

    public static DesiredCapabilities getTronLinkDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
        desiredCapabilities.setCapability("platformName", TronLink.platformName);
        desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
        desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
        return desiredCapabilities;
    }

    public static void waitTargetElementAppear(AndroidDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return;
    }




}
