package tronlink;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class DappChain {
    public static String walletPrivateKey = "66cba88ad4d8d987bd6895c8913009d34ad6adc42be19e0318b81f4107663242";
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
    public void import_PrivateKey(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //use Private Key import account
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.enterContent,"input",walletPrivateKey,"enter private key");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");
    }


    @Test
    public void test01deposit() {
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
        TronLink.testOperation(driver,TronLink.setting_node,"click","click node");

        List<MobileElement> list = driver.findElementsById(TronLink.setting_dapp_change);
        for (MobileElement a : list){
            if (!a.isSelected())
            a.click();
        }
        TronLink.testOperation(driver,TronLink.common_left,"click","click back");
        TronLink.testOperation(driver,TronLink.common_left,"click","click back");
        TronLink.testOperation(driver,TronLink.tabAssets,"click","click tab Assets");

        List<MobileElement> assets = driver.findElementsById(TronLink.assetsList);
        assets.get(0).click();
        TronLink.testOperation(driver,TronLink.deposit,"click","click withdraw");
        TronLink.testOperation(driver,TronLink.sendCoinAmountId,"input","100","input amount of withdraw");
        TronLink.testOperation(driver,TronLink.withdrawButton,"click","click withdraw");
    }

    @Test
    public void test02withdraw() {
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.settings,"click","click settings");
        TronLink.testOperation(driver,TronLink.setting_node,"click","click node");

        List<MobileElement> list = driver.findElementsById(TronLink.setting_dapp_change);
        for (MobileElement a : list){
            if (!a.isSelected())
                a.click();
        }
        TronLink.testOperation(driver,TronLink.common_left,"click","click back");
        TronLink.testOperation(driver,TronLink.common_left,"click","click back");
        TronLink.testOperation(driver,TronLink.tabAssets,"click","click tab Assets");

        List<MobileElement> assets = driver.findElementsById(TronLink.assetsList);
        assets.get(0).click();
        TronLink.testOperation(driver,TronLink.deposit,"click","click withdraw");
        TronLink.testOperation(driver,TronLink.sendCoinAmountId,"input","100","input amount of withdraw");
        TronLink.testOperation(driver,TronLink.withdrawButton,"click","click withdraw");

    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
