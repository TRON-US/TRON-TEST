package android.com.wallet.UITest.base;

import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.atomic.AtomicInteger;

//@Listeners(RetryListener.class)

public class Base {



    public  AndroidDriver<?> DRIVER;

    private  SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public  int RetryAgainTimes = 3;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


    public static AtomicInteger systemAtomicPort = new AtomicInteger(8200);

    @Parameters({ "deviceName"})
    @BeforeMethod
    public void testStart(Method method,String deviceName) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName() + " Device: " + deviceName);
    }


    //setUp
    @Parameters({"port","platformName", "platformVersion", "deviceName","udid"})
    @BeforeTest(groups = {"P0"})
    public void startServer(String port, String platformName, String platformVersion, String deviceName,String udid) {
        try {
            System.out.println("startByPort: "+port+" \nudid:"+udid);
            Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -U " + udid);
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            Scanner sc=new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println(sb.toString());
            System.out.println("appium setup  success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","systemPort","privateKey","noReset"})
    @BeforeClass(groups = {"P0"}) //Increase stability(because some case star setup error)
    public void setUp(String port, String platformName, String platformVersion, String deviceName,String udid,String systemPort,String privateKey,String noReset)throws Exception {
        TimeUnit.SECONDS.sleep(2);
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
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
//                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemAtomicPort.addAndGet(1));
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//                if (systemAtomicPort.get() == 8299) {
//                    systemAtomicPort.set(8200);
//                }
                System.out.println("mobile: " + deviceName + " " + udid);
                System.out.println("privateKey: " + privateKey);
                File appDir = new File(System.getProperty("user.dir"), "");
                File app = new File(appDir, "TronLink.apk");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
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




    public void DeviceRestart(){
        DRIVER.closeApp();
        DRIVER.launchApp();//   DRIVER.activateApp("com.tronlinkpro.wallet");
    }
    public void DeviceQuit(){
        DRIVER.quit();
    }
    public void DeviceLaunch(){
        DRIVER.launchApp();//   DRIVER.activateApp("com.tronlinkpro.wallet");
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

    public String removeSymbolFloat(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
    }

    public String prettyString(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
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

    public float getAnAmount() {
        Random rand = new Random();
        String amountStr = Float.toString(rand.nextFloat() + 1);
        while (amountStr.length() != 8) {
            amountStr = Float.toString(rand.nextFloat() + 1);
        }
        return Float.valueOf(amountStr);
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
            if (DRIVER.findElementById("com.tronlinkpro.wallet:id/iv_pic").isDisplayed()){
                DRIVER.findElementById("com.tronlinkpro.wallet:id/iv_close").click();
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){}
        DRIVER.findElementById("com.tronlinkpro.wallet:id/my").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementById("com.tronlinkpro.wallet:id/setting").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementById("com.tronlinkpro.wallet:id/node").click();
        TimeUnit.SECONDS.sleep(1);
        DRIVER.findElementsById("com.tronlinkpro.wallet:id/iv_select").get(1).click();
        TimeUnit.SECONDS.sleep(1);
        //DRIVER.closeApp();
        //DRIVER.activateApp("com.tronlinkpro.wallet");
        DRIVER.navigate().refresh();
        TimeUnit.SECONDS.sleep(6);
    }

    public void waiteTime(long time) {
        DRIVER.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(15);
    }


    public float sepLeftNumberTextToFloat(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  Float.parseFloat(removeSymbolFloat(realNumber.trim()));

    }
    public Integer sepLeftNumberTextToInter(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  Integer.parseInt(removeSymbolFloat(realNumber.trim()));
    }
    public String  sepLeftNumberTextToString(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  removeSymbolFloat(realNumber.trim());

    }
    public float sepRightNumberTextToFloat(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  Float.parseFloat(removeSymbolFloat(realNumber.trim()));

    }
    public Integer sepRightNumberTextToInter(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  Integer.parseInt(removeSymbolFloat(realNumber.trim()));
    }
    public String  sepRightNumberTextToString(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  removeSymbolFloat(realNumber.trim());

    }


    public void setToDAppChain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        asset.changeChainToDappChain();
    }

    public boolean assertToast(String toast){
        try {
            WebDriverWait wait = new WebDriverWait(DRIVER,20);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+toast+"')]")));
            return true;
        }catch (Exception e){
            return false;
        }



    }


    public void longPress(String text){
        AndroidTouchAction act = new AndroidTouchAction(DRIVER);
        WebElement el = DRIVER.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
        act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(el))).perform();
    }

    public AssetPage enterOnlineAssetPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.findElementByText("自用测试").click();
        mine.findElementByText("切换版本").click();
        mine.online_version_icon.click();
        TimeUnit.SECONDS.sleep(5);
        if(asset.isElementExist("线上版本")){
            DeviceRestart();
            TimeUnit.SECONDS.sleep(2);
        }
        return asset;
    }

}
