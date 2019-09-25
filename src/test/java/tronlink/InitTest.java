package tronlink;


import org.aspectj.weaver.ast.And;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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
    public static AndroidDriver driver = null;
    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","bootstrap_port"})
    @BeforeSuite
    public void startServer(String port, String platformName, String platformVersion, String deviceName,String udid,String bootstrap_port) throws MalformedURLException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(port+udid);
                    Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -u " + udid + " -bp " + bootstrap_port);
                    InputStreamReader isr=new InputStreamReader(process.getInputStream());
                    Scanner sc=new Scanner(isr);
                    StringBuffer sb = new StringBuffer();
                    sb.append(sc.next());
                    System.out.println(sb.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println(port);
        String url = "http://localhost:"+port+"/wd/hub";
//        ArrayList<String> devices = TronLink.devicesReturn(TronLink.adb + " devices");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        System.out.println(devices);
//        for (String udid :devices){
//            TronLink.platformVersion = TronLink.cmdReturn(TronLink.adb + " -s " + udid + " shell getprop ro.build.version.release");
//            TronLink.deviceName = TronLink.cmdReturn(TronLink.adb + " -s " + udid + " -d shell getprop ro.product.model");
//            desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
//            desiredCapabilities.setCapability("platformName", TronLink.platformName);
//            desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
//            desiredCapabilities.setCapability("udid", udid);
//        }
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("udid", udid);
        desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
        URL remoteUrl = new URL(url);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @AfterSuite
    public void endServer(){
        driver.quit();
    }
}
