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

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class Vote extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01Vote() {
        importWallet(testPrivateKey);

        //System.out.println(driver.getPageSource());
        // testOperation( "com.tronlink.wallet:id/tv_walletname","click","");
        // testOperation(  marketsIconId,"click","Enter markets screen");
        // testOperation(  assetIconId,"click","Enter asset screen");
         testOperation(  voteId,"click","Enter to vote screen");
        System.out.println();
         testOperation(  voteResetId,"click","Reset vote");
         testOperation(  voteInputQuantityXPath,"input",String.valueOf( voteQuantity),"Input vote amount");
         testOperation(  voteId,"click","Vote");
         testOperation(  voteNowId,"click","Vote now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for vote");
         testOperation(  transactionConfirmButtonId,"click","Confirm the vote transaction");
    }
    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
