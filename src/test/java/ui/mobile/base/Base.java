package ui.mobile.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;

public class Base {
    public AndroidDriver<?> ADRIVER;
    public IOSDriver<?> IDRIVER;

    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss");
    public int RetryAgainTimes = 5;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public static AtomicInteger systemAtomicPortAndroid = new AtomicInteger(8200);

    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "bpPort", "webDriverPort"})
    @BeforeTest(groups = {"P0"})
    public void startServer( @Optional("port") String port,
                             String platformName,
                             @Optional("platformVersion") String platformVersion,
                             @Optional("deviceName") String deviceName,
                             @Optional("udid") String udid,
                             @Optional("bpPort") String bpPort,
                             @Optional("webDriverPort") String webDriverPort) throws IOException {
        if (platformName.contains("iOS")){
            startServeriOS( port,  udid,  bpPort,  webDriverPort);
        }else {
            startServerAndroid(port,udid);
        }

    }

    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","systemPort","privateKey","noReset","webDriverPort","xcodeSigningId", "automationName", "xcodeOrgId", "bundleId"})
    @BeforeClass(groups = {"P0"})
    public void startApp(@Optional("port") String port,
                         @Optional("platformName") String platformName,
                         @Optional("platformVersion") String platformVersion,
                         @Optional("deviceName") String deviceName,
                         @Optional("udid") String udid,
                         @Optional("systemPort") String systemPort,
                         @Optional("privateKey") String privateKey,
                         @Optional("noReset") String noReset,
                         @Optional("webDriverPort") String webDriverPort,
                         @Optional("xcodeSigningId") String xcodeSigningId,
                         @Optional("automationName") String automationName,
                         @Optional("xcodeOrgId") String xcodeOrgId,
                         @Optional("bundleId") String bundleId) throws Exception{
        if (platformName.contains("iOS")){
            startAppiOS( port,  platformName,  platformVersion,  deviceName,  udid,  webDriverPort, xcodeSigningId, noReset,  automationName,  xcodeOrgId,
                       bundleId);
        }else {
            startAppAndroid(port,platformName,platformVersion,deviceName,udid,privateKey,noReset);
        }
    }






    public void startAppAndroid(String port, String platformName, String platformVersion, String deviceName,String udid,String privateKey,String noReset)throws Exception {
        screenOnAndroid();
        log("启动设备名称: " + deviceName);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                System.out.println("try start driver "+tries+" times");
                String url = "http://127.0.0.1:"+port+"/wd/hub";
                desiredCapabilities.setCapability("deviceName", deviceName);
                desiredCapabilities.setCapability("language", "zh");
                desiredCapabilities.setCapability("locale", "CN");
                desiredCapabilities.setCapability("platformName", platformName);
                desiredCapabilities.setCapability("platformVersion", platformVersion);
                desiredCapabilities.setCapability("udid", udid);
                desiredCapabilities.setCapability("unicodeKeyboard", true);
                desiredCapabilities.setCapability("resetKeyboard", true);
                desiredCapabilities.setCapability("automationName", "Uiautomator2");
                desiredCapabilities.setCapability("privateKey", privateKey);
                desiredCapabilities.setCapability("clearSystemFiles", true);
                desiredCapabilities.setCapability("noReset", noReset);
                desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemAtomicPortAndroid.addAndGet(1));
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                if (systemAtomicPortAndroid.get() == 8299) {
                    systemAtomicPortAndroid.set(8200);
                }
                System.out.println("mobile: " + deviceName + " " + udid);
                System.out.println("privateKey: " + privateKey);
                File appDir = new File(System.getProperty("user.dir"), "");
                File app = new File(appDir, "TronLink.apk");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
                URL remoteUrl = new URL(url);
                ADRIVER = new AndroidDriver(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                System.out.println("setUp DRIVER success");
            }catch (Exception e){
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }

    public void startAppiOS(String port, String platformName, String platformVersion, String deviceName, String udid, String webDriverPort,String xcodeSigningId,String noReset, String automationName, String xcodeOrgId,
                      String bundleId) throws Exception {
        log("启动设备名称: " + deviceName);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 3) {
            tries++;
            try {
                String url = "http://127.0.0.1:" + port + "/wd/hub";
                desiredCapabilities.setCapability("deviceName", deviceName);
                desiredCapabilities.setCapability("platformName", platformName);
                desiredCapabilities.setCapability("platformVersion", platformVersion);
                desiredCapabilities.setCapability("udid", udid);
                desiredCapabilities.setCapability("automationName", automationName);
                desiredCapabilities.setCapability("newCommandTimeout", 500);
                desiredCapabilities.setCapability("autoAcceptAlerts", true);
                desiredCapabilities.setCapability("noReset", noReset);
                desiredCapabilities.setCapability("xcodeOrgId",xcodeOrgId );
                desiredCapabilities.setCapability("xcodeSigningId", xcodeSigningId);
                desiredCapabilities.setCapability("bundleId",bundleId);
                desiredCapabilities.setCapability("xcodeOrgId",xcodeOrgId);
                desiredCapabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT,webDriverPort);
                File appDir = new File(System.getProperty("user.dir"), "");
                File app = new File(appDir, "Tronlink.ipa");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
                URL remoteUrl = new URL(url);
                IDRIVER = new IOSDriver<WebElement>(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                System.out.println("setUp DRIVER success");

            } catch (Exception e) {
                System.out.println("setUp DRIVER fail");
                System.out.println(e);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }


    @BeforeMethod
    public void testStart(Method method) {
        log(">>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName());
    }




    public void startServeriOS(String port, String udid, String bpPort, String webDriverPort) throws IOException{
        try {
            Process process = Runtime.getRuntime().exec("appium --session-override -a 127.0.0.1 -p " + port + " -bp " + bpPort + " --udid " + udid + " --webdriveragent-port " + webDriverPort);
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            Scanner sc = new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println("\n startServer  Success \n" );
        } catch (Exception e) {
            System.out.println("\n startServer  Fail \n" );
            e.printStackTrace();
        }
    }

    public void startServerAndroid(String port,String udid) throws IOException{
        try {
            Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -U " + udid);
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            Scanner sc=new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println("\n startServer  Success \n" );
        } catch (Exception e) {
            System.out.println("\n startServer  Fail \n" );
            e.printStackTrace();
        }
    }

    public  void screenOnAndroid() {
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
                Runtime.getRuntime().exec("adb shell input keyevent 82");
            }
            p.destroy();
        } catch (IOException ex) {
            return;
        }
    }


    public void log(String log) {
        String time = timeStamp.format(new Date()).toString();
        System.out.println(time + ": " + log);
    }

}
