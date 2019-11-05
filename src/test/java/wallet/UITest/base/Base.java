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
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.NodeSetPage;
import wallet.pages.SettingPage;

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
                desiredCapabilities.setCapability("clearSystemFiles", true);
                desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                System.out.println("mobile: " + deviceName + " " + udid);
                System.out.println("privateKey: " + privateKey);
                File appDir = new File(System.getProperty("user.dir"), ".//");
                File app = new File(appDir, "TronLink.apk");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
                //desiredCapabilities.setCapability("app", "/Users/tron/Documents/tronlink_task/testnet_release.apk");
                URL remoteUrl = new URL(url);
                DRIVER = new AndroidDriver(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                System.out.println("setUp DRIVER success");
            }catch (Exception e){
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
        screenOn();
    }




//    public  void setUp() throws Exception {
//        //String[] deviceInfo = getDeviceInfo();
//        File appDir = new File(System.getProperty("user.dir"), ".//");
//        File app = new File(appDir, "TronLink.apk");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        //capabilities.setCapability("deviceName", "CLB0218A10004233");
//        capabilities.setCapability("deviceName", "CLB0218A10004233");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "9");
//        //capabilities.setCapability("UninstallAfterCloseApp", true);
//        capabilities.setCapability("appPackage", "com.tronlink.wallet");//包名
//        capabilities.setCapability("appActivity", "com.tron.wallet.bussiness.welcome.WelcomeActivity");
//        capabilities.setCapability("unicodeKeyboard", true); // 输入中文
//        capabilities.setCapability("resetKeyboard", true);
//        capabilities.setCapability("noReset", false);
//        capabilities.setCapability("autoGrantPermissions", true);
//        capabilities.setCapability("noSign","true"); //with appium no sign again
//        //capabilities.setCapability("app", app.getAbsolutePath());
//        capabilities.setCapability("app", app.getAbsolutePath());
//        //create driver object
//        DRIVER = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//    }


//    //导签名、密码
//    public  void getSign(String testPrivateKey) throws Exception{
//        findWebElement("com.tronlink.wallet:id/tv_import").click();
//        //TimeUnit.SECONDS.sleep(3);
//        while (findWebElement("com.tronlink.wallet:id/bt_accept").isEnabled() == false) {
//            AndroidTouchAction action = new AndroidTouchAction(DRIVER);
//            int width = DRIVER.manage().window().getSize().width;
//            int height = DRIVER.manage().window().getSize().height;
//            //System.out.print("   " + width + "   " + height);
//            Duration duration = Duration.ofMillis(200);
//            action.press(
//                    PointOption.point(width/2, height*4/5))
//                    .waitAction(WaitOptions.waitOptions(duration))
//                    .moveTo(PointOption.point(width/2, height/5))
//                    .release().perform();
//        }
//        findWebElement("com.tronlink.wallet:id/bt_accept").click();
//        findWebElement("com.tronlink.wallet:id/cd_pk").click();
//        findWebElement("com.tronlink.wallet:id/et_content").sendKeys(testPrivateKey);
//        findWebElement("com.tronlink.wallet:id/bt_next").click();
//        findWebElement("com.tronlink.wallet:id/et_name").sendKeys("Auto-test");
//        findWebElement("com.tronlink.wallet:id/creat").click();
//        findWebElement("com.tronlink.wallet:id/et_password").sendKeys("Test0001");
//        findWebElement("com.tronlink.wallet:id/creat").click();
//        findWebElement("com.tronlink.wallet:id/creat").click();
//        findWebElement("com.tronlink.wallet:id/et_password").sendKeys("Test0001");
//        findWebElement("com.tronlink.wallet:id/creat").click();
//
//    }


//    /**
//     * 找元素加固，如果没有找到元素，则再找5次，每次停留2S
//     * @param element
//     * @return WebElement
//     * @throws Exception
//     */
//    public  WebElement findWebElement(String element) throws Exception {
//        int tries = 0;
//        Boolean Element_is_exist = false;
//        WebElement el = null;
//        while (!Element_is_exist && tries < 5) {
//            tries++;
//            try {
//                el = DRIVER.findElementById(element);
//            }catch (NoSuchElementException e){
//                Element_is_exist = true;
//                TimeUnit.SECONDS.sleep(2);
//            }
//        }
//        el = DRIVER.findElementById(element);
////        DRIVER.findElementById(element)
////        WebElement el = null;
////        try {
////             el = DRIVER.findElementById(element);
////        }catch (NoSuchElementException e){
////            while (!Element_is_exist && tries < 5) {
////                tries++;
////                TimeUnit.SECONDS.sleep(1);
////                if (DRIVER.findElementById(element).isDisplayed()) {
////                    Element_is_exist = true;
////                    return el;
////                }
////            }
////            System.err.println("Base.java try 5 times not found element: " + element);
////        }
//        return el;
//    }



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



    public  void screenOn() {
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


    public  void changeDappchain() throws Exception{
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (DRIVER.findElementById("com.tronlink.wallet:id/iv_pic").isDisplayed()){
                DRIVER.findElementById("com.tronlink.wallet:id/iv_close").click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        DRIVER.findElementById("com.tronlink.wallet:id/my").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementById("com.tronlink.wallet:id/setting").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementById("com.tronlink.wallet:id/node").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementsById("com.tronlink.wallet:id/iv_select").get(1).click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }


}
