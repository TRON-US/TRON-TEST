package ios.tronlink.com.tronlink.wallet.UITest.base;



import android.com.utils.AppiumTestCase;
import android.com.utils.IHookableImp;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

import javax.management.DescriptorRead;

@Listeners(IHookableImp.class)
public class Base {

    public IOSDriver<?> DRIVER;

    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss ");

    public int RetryAgainTimes = 2;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


    //setUp
    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "bpPort", "webDriverPort"})
    @BeforeTest(groups = {"P0"})
    public void startServer(String port, String platformName, String platformVersion, String deviceName, String udid, String bpPort, String webDriverPort) throws IOException {

        try {
            System.out.println("startByPort: "+port+" \nudid:"+udid);
            Process process;
            if (isOneVersion()){
                process = Runtime.getRuntime().exec("appium -a 127.0.0.1  -p " + port  + " --udid " + udid );
                System.out.println("***** appium Version one *****");
            }else {
                process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -pa /wd/hub -p " + port  + " --use-drivers " + udid );
                System.out.println("appium -a 127.0.0.1 -pa /wd/hub -p " + port  + " --use-drivers " + udid);
                System.out.println("***** appium Version two *****");
            }

            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            Scanner sc = new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println("\n appium  setup  success \n" );

        } catch (Exception e) {
            System.out.println("\n appium  setup  fail \n" );
            ScreenShot("AppiumFail");
            e.printStackTrace();

        }
    }



    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "webDriverPort","xcodeSigningId","noReset", "automationName", "xcodeOrgId", "bundleId"})
    @BeforeClass(groups = {"P0"}) //Increase stability(because some case star setup error)
    public void setUp(String port, String platformName, String platformVersion, String deviceName, String udid, String webDriverPort,String xcodeSigningId,String noReset, String automationName, String xcodeOrgId,
        String bundleId) throws Exception {
        log("启动设备名称: " + deviceName);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                System.out.println("Try start driver "+tries+" times");
                String url = "http://127.0.0.1:" + port + "/wd/hub";
                desiredCapabilities.setCapability("deviceName", deviceName);
                desiredCapabilities.setCapability("platformName", platformName);
                desiredCapabilities.setCapability("platformVersion", platformVersion);
                desiredCapabilities.setCapability("udid", udid);
                desiredCapabilities.setCapability("automationName", automationName);
                desiredCapabilities.setCapability("newCommandTimeout", 4800);
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
                URL remoteUrl = new URL(url);
                System.out.println(url);
                System.out.println(desiredCapabilities.getCapability("app"));
                DRIVER = new IOSDriver(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                System.out.println("setUp DRIVER success");

            } catch (Exception e) {
                System.out.println("setUp DRIVER fail");
                System.out.println(e);
                ScreenShot("DRIVERFail");
                reInstall(udid);
                System.out.print("\nInstall " + udid + " Success\n");
            }
        }

        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.tronlink.hdwallet");
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("----startApp----");
        DRIVER.executeScript("mobile: activateApp", params);
        TimeUnit.SECONDS.sleep(4);
    }

    public void ScreenShot(String name){
        //调用截图功能
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-hh-mm-ss");
            String path = "build/tmp/" + name + "." + dateFormat.format(new Date()) + ".png";
            System.out.println(path);
            Runtime.getRuntime().exec("idevicescreenshot " + path);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void tearDownclass() {
        DRIVER.closeApp();
        DRIVER.launchApp();
    }


    public void tearDownAfterClass() {
    }

    public boolean isOneVersion()  throws IOException{
        Process process = Runtime.getRuntime().exec("appium --version");
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner s=new Scanner(isr);
        if (s.hasNext()){
            String ver = s.next();
            return ver.startsWith("1");
        }else {
            return true;
        }
    }

    public void tearDownWithoutQuit() {
        //writeLog("remove App");
        //DRIVER.removeApp("com.tronlink.wallet");
        DRIVER.closeApp();
        DRIVER.launchApp();
    }


    public void log(String log) {
        String time = timeStamp.format(new Date()).toString();
        System.out.println(time + ": " + log);
    }


    public List<String> getDevicesInfo() throws IOException {
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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }


    public void screenOn() {
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


    public String removeSymbol(String arg) {
        String value = arg;
        if (arg.contains(",")) {
            value = arg.replace(",", "");
        }
        if (arg.contains(".")) {
            String[] intValue = value.split("\\.");
            value = intValue[0];
        }
        return value;
    }

    public String removeSymbolNoDot(String arg) {
        String value = arg;
        if (arg.contains(",")) {
            value = arg.replace(",", "");
        }
        return value;
    }

    public Double getAnAmount() {
        Random rand = new Random();
        String amountStr = Double.toString(rand.nextFloat() + 1);
        while (amountStr.length() != 8) {
            amountStr = Float.toString(rand.nextFloat() + 1);
        }
        log("\ngetAnAmount: " + amountStr);
        return Double.valueOf(amountStr);
    }

    public Double getAnAmountZero() {
        Random rand = new Random();
        String amountStr = Double.toString(rand.nextFloat());
        while (amountStr.length() != 8) {
            amountStr = Float.toString(rand.nextFloat());
        }
        log("\ngetAnAmount() amountStr: " + amountStr);
        return Double.valueOf(amountStr);
    }

    // random a 6 decimal places digital, return String
    public static String random(float multiple, float min) {
        Random random = new Random();
        return String.format("%.6f", Math.random() * multiple + min);
    }

    //TODO:换id为name
    public void changeDappchain() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(2);
            // if page display AD , cloese the AD
            if (DRIVER.findElementById("com.tronlink.wallet:id/iv_pic").isDisplayed()) {
                DRIVER.findElementById("com.tronlink.wallet:id/iv_close").click();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
        }
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

    public void waiteTime(long time) {
        DRIVER.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(10);
    }



    public void screenshotAction(String dest) {
        try {
            File srcFile = DRIVER.getScreenshotAs(OutputType.FILE);
            System.out.println(srcFile);
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
            File location = new File("build/tmp/screenshot");
            File targetFile = new File(location.getAbsolutePath() + File.separator + dest + dateFormat.format(new Date()) + ".png");
            log("----------------- file is " + targetFile.getPath());
            try {
                FileUtils.copyFile(srcFile, targetFile);
            } catch (IOException e1) {
                System.out.println("error: " + e1);
                e1.printStackTrace();
            }


        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }


    public String removeSymbolString(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
    }

    public Double removeSymbolDouble(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return Double.parseDouble(value);
    }

    public float sepLeftNumberTextToFloat(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  Float.parseFloat(removeSymbolString(realNumber.trim()));

    }
    public Integer sepLeftNumberTextToInter(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  Integer.parseInt(removeSymbolString(realNumber.trim()));
    }
    public String  sepLeftNumberTextToString(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        return  removeSymbolString(realNumber.trim());

    }
    public float sepRightNumberTextToFloat(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  Float.parseFloat(removeSymbolString(realNumber.trim()));
    }
    public Double sepRightNumberTextToDouble(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  Double.parseDouble(removeSymbolString(realNumber.trim()));
    }

    public Double sepMiddleNumberTextToDouble(String content,String leftString,String rightString){
        String realNumber = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(content,leftString),rightString);
        return  Double.parseDouble(removeSymbolString(realNumber.trim()));
    }

    public Integer sepRightNumberTextToInter(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  Integer.parseInt(removeSymbolString(realNumber.trim()));
    }
    public String  sepRightNumberTextToString(String content,String lastString){
        String realNumber = StringUtils.substringAfterLast(content,lastString);
        return  removeSymbolString(realNumber.trim());

    }




    public Double sepLeftNumberTextToDouble(String content,String lastString){
        String realNumber = StringUtils.substringBeforeLast(content,lastString);
        realNumber = prettyString(realNumber);
        realNumber = realNumber.replace("+","");
        realNumber = realNumber.replace("-","");
        return  Double.parseDouble(removeNegative(realNumber.trim()));
    }


    public String prettyString(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
    }


    public String removeNegative(String arg){
        String value = arg;
        if (arg.contains("-")){
            value = arg.replace("-","");
        }
        if (arg.contains("+")){
            value = arg.replace("+","");
        }
        return value;
    }

    public String removeSymbolFloat(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        return value;
    }
    public void reInstall(String id) throws Exception {
        log("开始卸载app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + id); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
        TimeUnit.SECONDS.sleep(4);
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + id);
        TimeUnit.SECONDS.sleep(10);
        log("等待10s");

    }

    public boolean isWebView(){
        try {
            DRIVER.findElementByClassName("XCUIElementTypeWebView");
            return true;

        }catch (Exception e){
            return false;
        }
    }
    public  boolean isElementPredicateExist( String iosPre) {
        waiteTime(5);
        try {
            DRIVER.findElementByIosNsPredicate(iosPre);
                    waiteTime();
                    return true;
        }catch (Exception e){
            waiteTime();
            return false;
        }

    }

        public  boolean isElementExist( String name) {
        IOSDriver driver = DRIVER;
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            driver.findElementByName(name);
            System.out.println("IsFindByName: "+name);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                driver.findElementById(name);
                System.out.println("IsFindById: "+name);
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    driver.findElementByLinkText(name);
                    System.out.println("findElementByLinkText: "+name);
                    return  true;
                }catch (org.openqa.selenium.NoSuchElementException e3){
                    try {
                        if (driver.findElementByClassName("XCUIElementTypeButton").getText().contains(name)){
                            System.out.println("IsFindByBtn: "+name);
                            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                            return  true;
                        }else {
                            System.out.println("NotFound: "+name);
                            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                            return  false;
                        }
                    }catch (org.openqa.selenium.NoSuchElementException e){
                        System.out.println("NotFound: "+name);
                        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                        return  false;
                    }
                }

            }
        }
    }

    public boolean isXpathElementExist(String path){
        IOSDriver driver = DRIVER;
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            driver.findElementByXPath(path);
            System.out.println("FindByXpath: "+path);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex) {
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            return false;
        }

    }

    public  void closeKeyBoard()  throws Exception{
        IOSDriver driver = DRIVER;
        try {
            driver.findElementByName("完成").click();
            System.out.println("close by 完成");

        }catch (Exception e){
            try {
                driver.findElementByName("Done").click();
                System.out.println("close by Done");
            }catch (Exception el){
                System.out.println("not found keyboard done");
                TouchAction action = new TouchAction(driver);
                PointOption whiteplace = PointOption.point(8,200);
                action.tap(whiteplace).perform().release();
            }
        }

    }

}
