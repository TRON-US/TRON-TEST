package tronlink;


import common.utils.AppiumTestCase;

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

public class CreateAccount extends AppiumTestCase {

    private AndroidDriver driver;
    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01createAccount() {
        //create account
         testOperation(   createAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");
         testOperation(  setUpName,"click","click set up name");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","1:input name");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"click","input name");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","click carry out");

        //backup mnemonic
         testOperation(  backUpNow,"click","back up now");
         testOperation(  gotItButton,"click","got it");
        ArrayList<String> allTextList =  getTextList(  keyIndexText);
         testOperation( "swipeUp","");
         testOperation(  saveKey,"click","i have saved");

        //confirm mnemonic
        List<MobileElement> confirmElements = driver.findElementsById( itemText);
        confirmElements.get( getSameMnemonicIdex( allTextList, itemText, numberIndex)).click();
         testOperation(  nextStepButton,"click","click next step");

        confirmElements = driver.findElementsById( itemText);
        confirmElements.get( getSameMnemonicIdex( allTextList, itemText, numberIndex)).click();
         testOperation(  nextStepButton,"click","click carry out");
    }

    @Test
    public void test02changeAccount(){
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");

         testOperation(  addWallet,"click","click add wallet");
         testOperation(  privateKey,"click","click Private key");
         testOperation(  enterContent,"input", testPrivateKey,"enter private key");
         testOperation(  nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","1:input name");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

         testOperation(  assetsCount,"click","click assetscount");
        String balance1 = getText(balance);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        String walletBalance1 =  getText(walletBalance);
        Assert.assertEquals(balance1 + "TRX",walletBalance1);

         testOperation(  manageropen,"click","click manageropen");
         testOperation(  selectWallet,"click","select wallet");
    }

    @AfterClass
    public void teardown(){
        driver.resetApp();
    }
}
