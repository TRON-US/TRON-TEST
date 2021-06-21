package ui.mobile.base;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Base {
    public AndroidDriver<?> ADRIVER;
    public IOSDriver<?> IDRIVER;

    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss");
    public int RetryAgainTimes = 5;

    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    //setUp
    @Parameters({"port", "platformName", "platformVersion", "deviceName", "udid", "bpPort", "webDriverPort"})
    @BeforeTest(groups = {"P0"})
    public void startServer(String port, String platformName, String platformVersion, String deviceName, String udid, String bpPort, String webDriverPort) throws IOException {
        if (platformName.contains("iOS")){
            try {
                System.out.println("appium --session-override -a 127.0.0.1 -p " + port + " -bp " + bpPort + " --udid " + udid + " --webdriveragent-port " + webDriverPort);
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
        }else {
            try {
                System.out.println("startByPort: "+port+" \nudid:"+udid);
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

    }



}
