package tronlink;
import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class importAccount {

    private AndroidDriver driver;
    public static String walletAddress = "";
    public static String walletPassword = "Test0001";
    public static String walletPrivateKey = "";
    public static String backupMnemonic = "";
    public static String backupkeystore = "";


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
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
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

        //backup mnemonic
        TronLink.testOperation(driver,TronLink.backUpNow,"click","back up now");
        TronLink.testOperation(driver,TronLink.gotItButton,"click","got it");
        ArrayList<String> allTextList = TronLink.getTextList(driver,TronLink.keyIndexText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.saveKey,"click","i have saved");

        //confirm mnemonic
        List<MobileElement> confirmElements = driver.findElementsById(TronLink.itemText);
        confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
        TronLink.testOperation(driver,TronLink.nextStepButton,"click","click next step");
        confirmElements = driver.findElementsById(TronLink.itemText);
        confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
        TronLink.testOperation(driver,TronLink.nextStepButton,"click","click carry out");

        //tab me
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
        TronLink.testOperation(driver,TronLink.addressText,"click","click ok");

        //get information
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
//        TronLink.testOperation(driver,TronLink.backupMnemonic,"click","click backup Mnemonic");
//        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
//        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");

        TronLink.testOperation(driver,TronLink.backupPrivateKey,"click","click backup PrivateKey");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
        walletPrivateKey = TronLink.getText(driver,TronLink.privateKeyText);
        TronLink.testOperation(driver,TronLink.done,"click","click done");
        TronLink.testOperation(driver,"swipeUp","");


        TronLink.testOperation(driver,TronLink.backupKeystore,"click","click backup Keystore");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
        backupkeystore = TronLink.getText(driver,TronLink.keystoreText);
        TronLink.testOperation(driver,TronLink.done,"click","click done");

    }

    @Test
    public void import_PrivateKey(){
        //startup page
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //use Private Key import account
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.enterContent,"input","ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72","enter private key");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");
        //if (TronLink.isElement(driver,TronLink.riskBackup)) driver.navigate().back();

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
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
//        if (TronLink.isElement(driver,TronLink.riskBackup)) driver.navigate().back();

        //delete wallet
        MobileElement el5 = (MobileElement) driver.findElementById("com.tronlink.wallet:id/my");
        el5.click();
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
//        try {
//            Runtime.getRuntime().exec("adb shell am force-stop com.tronlink.wallet");
//            Thread.sleep(2);
//            Runtime.getRuntime().exec("adb shell am start -n com.tronlink.wallet/com.tron.wallet.bussiness.welcome.WelcomeActivity");
//            Thread.sleep(2);
//        }catch (Exception e){
//            System.out.println(e);
//        }
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}
