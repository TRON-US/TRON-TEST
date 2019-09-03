package tronlink;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class createAccount {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void createAccount() {
        //create account
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.createAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");
        TronLink.testOperation(driver,TronLink.setUpName,"click","click set up name");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"click","input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","click carry out");

        //backup mnemonic
        TronLink.testOperation(driver,TronLink.backUpNow,"click","back up now");
        TronLink.testOperation(driver,TronLink.gotItButton,"click","got it");
        TronLink.getScreenshot(driver,"mnemonic");
        MobileElement a = (MobileElement) driver.findElementById("com.tronlink.wallet:id/text");
        System.out.println(a.getText());
        List<MobileElement> text = driver.findElementsById("com.tronlink.wallet:id/text");
        System.out.println(text.size());
        for (MobileElement data : text){
            System.out.println("postion:"+data.getText()+"postion");
        }
        TronLink.testOperation(driver,TronLink.saveKey,"click","i have saved");
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}
