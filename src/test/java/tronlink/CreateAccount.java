package tronlink;


import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAccount {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01createAccount() {
        //create account
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.createAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
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
    }

    @Test
    public void test02changeAccount(){
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");

        TronLink.testOperation(driver,TronLink.addWallet,"click","click add wallet");
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.enterContent,"input",TronLink.testPrivateKey,"enter private key");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        TronLink.testOperation(driver,TronLink.assetsCount,"click","click assetscount");
        String balance = TronLink.getText(driver,TronLink.balance);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        String walletBalance = TronLink.getText(driver,TronLink.walletBalance);
        Assert.assertEquals(balance + "TRX",walletBalance);

        TronLink.testOperation(driver,TronLink.manageropen,"click","click manageropen");
        TronLink.testOperation(driver,TronLink.selectWallet,"click","select wallet");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
