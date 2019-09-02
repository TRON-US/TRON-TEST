package tronlink;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class importAccount {

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
        //startup page
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //create account
        TronLink.testOperation(driver,TronLink.createWallet,"click","click create wallet");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //backup mnemonic
        TronLink.testOperation(driver,TronLink.backUpNow,"click","back up now");
        TronLink.testOperation(driver,TronLink.gotItButton,"click","got it");
        TronLink.getScreenshot(driver,"mnemonic");
        List<MobileElement> text = driver.findElementsById("com.tronlink.wallet:id/position");
        System.out.println(text.size());
        for (MobileElement data : text){
            System.out.println("postion:"+data.getText()+"postion");
        }
//        TronLink.testOperation(driver,TronLink.saveKey,"click","i have saved");
    }

    @Test
    public void import_PrivateKey(){
        //startup page
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //use Private Key import account
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.enterContent,"input","ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72","enter private key");
        TronLink.testOperation(driver,TronLink.enterContent,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");
//        TronLink.testOperation(driver,TronLink.riskIgnore,"click","cancel");
    }

    @Test
    public void import_WatchAddress(){
        //startup page
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import watch account
        TronLink.testOperation(driver,TronLink.watchWallet,"click","click watch wallet");
        TronLink.testOperation(driver,TronLink.enterContent,"input","TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of","enter address");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");
        TronLink.testOperation(driver,TronLink.riskIgnore,"click","cancel");

    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}
