package tronlink;


import org.aspectj.weaver.ast.And;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;

public class InitTest {
    String port = "4723";
    String url = "http://localhost:"+port+"/wd/hub";
    @BeforeSuite
    public void startServer() throws IOException {
        Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner sc=new Scanner(isr);
        StringBuffer sb = new StringBuffer();
        sb.append(sc.next());
        System.out.println(sb.toString());
    }

    @Test
    public void setUp()throws IOException{
        ArrayList<String> devices = TronLink.devicesReturn(TronLink.adb + " devices");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        System.out.println(devices);
        for (String udid :devices){
            TronLink.platformVersion = TronLink.cmdReturn(TronLink.adb + " -s " + udid + " shell getprop ro.build.version.release");
            TronLink.deviceName = TronLink.cmdReturn(TronLink.adb + " -s " + udid + " -d shell getprop ro.product.model");
            desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
            desiredCapabilities.setCapability("platformName", TronLink.platformName);
            desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
            desiredCapabilities.setCapability("udid", udid);
        }
        System.out.println(TronLink.platformVersion);
        System.out.println(TronLink.deviceName);
        desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
        URL remoteUrl = new URL(url);
        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        TronLink.driverTron = driver;
    }
}
