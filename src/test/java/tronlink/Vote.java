package tronlink;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class Vote {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = TronLink.driverTron;
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01Vote() {
        //System.out.println(driver.getPageSource());
        //TronLink.testOperation(driver,"com.tronlink.wallet:id/tv_walletname","click","");
        //TronLink.testOperation(driver,TronLink.marketsIconId,"click","Enter markets screen");
        //TronLink.testOperation(driver,TronLink.assetIconId,"click","Enter asset screen");
        TronLink.testOperation(driver,TronLink.voteId,"click","Enter to vote screen");
        System.out.println();
        TronLink.testOperation(driver,TronLink.voteResetId,"click","Reset vote");
        TronLink.testOperation(driver,TronLink.voteInputQuantityXPath,"input",String.valueOf(TronLink.voteQuantity),"Input vote amount");
        TronLink.testOperation(driver,TronLink.voteId,"click","Vote");
        TronLink.testOperation(driver,TronLink.voteNowId,"click","Vote now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for vote");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the vote transaction");
    }

    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
