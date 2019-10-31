package wallet.UITest.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.tools.ant.taskdefs.EchoXML;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

//@Listeners(RetryListener.class)

public class Base {

    public  AndroidDriver<?> DRIVER;

    private  SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public  int RetryAgainTimes = 4;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";

    //@Test(retryAnalyzer = TestRetryAnalyzer.class)


    //setUp
    @Parameters({"port","platformName", "platformVersion", "deviceName","udid"})
    @BeforeTest
    public void startServer(String port, String platformName, String platformVersion, String deviceName,String udid) {
        try {
            System.out.println(port+udid);
            Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -U " + udid);
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            Scanner sc=new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println(sb.toString());
            Runtime.getRuntime().exec("adb shell am start -n com.tronlink.wallet/com.tron.wallet.bussiness.welcome.WelcomeActivity");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","systemPort","privateKey"})
    @BeforeClass() //Increase stability(because some case star setup error)
    public void setUp(String port, String platformName, String platformVersion, String deviceName,String udid,String systemPort,String privateKey)throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                System.out.println("try start driver "+tries+" times");
                String url = "http://127.0.0.1:"+port+"/wd/hub";
                desiredCapabilities.setCapability("deviceName", deviceName);
                desiredCapabilities.setCapability("platformName", platformName);
                desiredCapabilities.setCapability("platformVersion", platformVersion);
                desiredCapabilities.setCapability("udid", udid);
                desiredCapabilities.setCapability("unicodeKeyboard", true);
                desiredCapabilities.setCapability("resetKeyboard", true);
                desiredCapabilities.setCapability("automationName", "Uiautomator2");
                desiredCapabilities.setCapability("privateKey", privateKey);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                File appDir = new File(System.getProperty("user.dir"), ".//");
                File app = new File(appDir, "TronLink.apk");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
//        desiredCapabilities.setCapability("app", "/Users/tron/Documents/tronlink_task/testnet_release.apk");
                URL remoteUrl = new URL(url);
                DRIVER = new AndroidDriver(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                screenOn(udid);
            }catch (Exception e){
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }


    public  void tearDownclass() {
        //writeLog("关闭app");
        DRIVER.closeApp();
        //writeLog("启动app");
        DRIVER.launchApp();
    }


    public  void tearDownAfterClass() {
        //DRIVER.quit();
    }


    public  void tearDownWithoutQuit() {
        //writeLog("remove App");
        //DRIVER.removeApp("com.tronlink.wallet");
        DRIVER.closeApp();
        DRIVER.launchApp();
    }


    public void log(String log) {
        String time = timeStamp.format(new Date()).toString();
        System.out.println(time + ": " + log);
    }


    public  List<String> getDevicesInfo() throws IOException {
        List<String> list = new ArrayList<>();
        Process proc = Runtime.getRuntime().exec("adb devices");
        //Process proc = Runtime.getRuntime().exec("ideviceinfo");
        BufferedInputStream bis = new BufferedInputStream(proc.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
        String line = null;
        while (!(line = br.readLine()).isEmpty()) {
            if (!line.contains("List")) {
                String serialNumber = line.split("\t")[0];
                list.add(serialNumber);
            }
        }
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        return list;
    }

    public  void screenOn(String udid) {
        try {
            DRIVER.unlockDevice();
            Runtime.getRuntime().exec("adb -s "+udid+" shell input keyevent 82");
        } catch (IOException ex) {
            return;
        }
    }


    public String removeSymbol(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        if (arg.contains(".")){
            String[] intValue = value.split("\\.");
            value = intValue[0];
        }
        return value;
    }



    // random a 6 decimal places digital, return String
    public static String random(float multiple,float min) {
        Random random = new Random();
        return String.format("%.6f",Math.random()*multiple + min);
    }


}
