package ios.tronlink.com.tronlink.wallet.UITest.base;



import android.com.utils.AppiumTestCase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;


public class Base {

    public IOSDriver<?> DRIVER;

    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss ");

    public int RetryAgainTimes = 5;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";


    //setUp
    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "bpPort", "webDriverPort"})
    @BeforeTest(groups = {"P0"})
    public void startServer(String port, String platformName, String platformVersion, String deviceName, String udid, String bpPort, String webDriverPort) throws IOException {
//        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        try {
            System.out.println(port + udid);
            Process process = Runtime.getRuntime().exec("appium --session-override -a 127.0.0.1 -p " + port + " -bp " + bpPort + " --udid " + udid + " --webdriveragent-port " + webDriverPort);
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            Scanner sc = new Scanner(isr);
            StringBuffer sb = new StringBuffer();
            sb.append(sc.next());
            System.out.println(sb.toString());
            System.out.println("\n startServer  Success \n" );

        } catch (Exception e) {
            System.out.println("\n startServer  Fail \n" );
            e.printStackTrace();
        }
    }



    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "webDriverPort","xcodeSigningId","noReset", "automationName", "xcodeOrgId", "bundleId"})
    @BeforeClass(groups = {"P0"}) //Increase stability(because some case star setup error)
    public void setUp(String port, String platformName, String platformVersion, String deviceName, String udid, String webDriverPort,String xcodeSigningId,String noReset, String automationName, String xcodeOrgId,
        String bundleId) throws Exception {
        log("我是Base类的Before");
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 3) {
            tries++;
            try {
                String url = "http://127.0.0.1:" + port + "/wd/hub";
                System.out.println("try start driver " + tries + " times  URL: " + url + "\n");

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
                File appDir = new File(System.getProperty("user.dir"), ".//");
                File app = new File(appDir, "Tronlink.ipa");
                desiredCapabilities.setCapability("app", app.getAbsolutePath());
                System.out.println(app.getAbsoluteFile());
                System.out.println("--------------------\n"+ desiredCapabilities.toString());
                System.out.println("\n URL: " +url+ "\n--------------------\n");
                URL remoteUrl = new URL(url);
                DRIVER = new IOSDriver<WebElement>(remoteUrl, desiredCapabilities);
                driver_is_start = true;
                System.out.println("setUp DRIVER success");

            } catch (Exception e) {
                System.out.println("setUp DRIVER fail");
                System.out.println(e);
                TimeUnit.SECONDS.sleep(1);
            }
        }
        screenOn();
    }


    public void tearDownclass() {
        //writeLog("关闭app");
        DRIVER.closeApp();
        //writeLog("启动app");
        DRIVER.launchApp();
    }


    public void tearDownAfterClass() {
        //DRIVER.quit();
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

    public float getAnAmount() {
        Random rand = new Random();
        String amountStr = Float.toString(rand.nextFloat() + 1);
        while (amountStr.length() != 6) {
            amountStr = Float.toString(rand.nextFloat() + 1);
        }
        log("\ngetAnAmount() amountStr: " + amountStr);
        return Float.valueOf(amountStr);
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


}
