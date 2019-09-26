package tronlink;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.utils.AppiumTestCase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TransferTrx extends AppiumTestCase {

    private AndroidDriver driver;

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test01TransferTrx() {
        importWallet(testPrivateKey);

        testOperation(  meIconId,"click","Enter to market screen");
         testOperation(  discoverIconId,"click","Enter to market screen");
         testOperation(  marketsIconId,"click","Enter to market screen");
         testOperation(  assetIconId,"click","Enter asset screen");
         testOperation(  sendCoinId,"click","Enter send coin screen");
         testOperation(  receiveAddressId,"input", receiverAddress,"Input receiver address");
         testOperation(  sendCoinAmountId,"input",String.valueOf( sendCoinAmount),"Input send coin amount");
         testOperation(  sendCoinButtonId,"click","Send coin");
         testOperation(  transferNowId,"click","Transfer trx now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for transfer trx");
         testOperation(  transactionConfirmButtonId,"click","Confirm the transfer");


        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test02EnterToTronLending() {
         testOperation(  tronLendingId,"click","Enter energy lease screen");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test03FreezeToGetBandwidth() {
         testOperation(  freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
         testOperation(  bandwidthOptionIconId,"click","Choose bandwidth option");
         testOperation(  frozenQuantityInputId,"input",String.valueOf( frozenQuantityForBandwidth),"Input frozen quantity for bandwidth");
         swipeUp();
         testOperation(  freezeIconId,"click","Click freeze icon");
         testOperation(  freezeNowIconId,"click","Freeze bandwidth now");
        Assert.assertEquals(driver.findElement(By.id( transactionConfirmInputPasswordId)).getText(),"Confirm");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for freeze bandwidth");
         testOperation(  transactionConfirmButtonId,"click","Confirm the freeze bandwidth transaction");

    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test04FreezeToGetEnergy() {
         testOperation(  energyOptionIconId,"click","Choose energy option");
         testOperation(  frozenQuantityInputId,"input",String.valueOf( frozenQuantityForEnergy),"Input frozen quantity for energy");
         swipeUp();
         testOperation(  freezeIconId,"click","Click freeze icon");
         testOperation(  freezeNowIconId,"click","Freeze bandwidth now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for freeze energy");
         testOperation(  transactionConfirmButtonId,"click","Confirm the freeze energy transaction");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test05Vote() {
         testOperation(  voteId,"click","Enter to vote screen");
         testOperation(  voteResetId,"click","Reset vote");
         testOperation(  voteInputQuantityXPath,"input",String.valueOf( voteQuantity),"Input vote amount");
         testOperation(  voteId,"click","Vote");
         testOperation(  voteNowId,"click","Vote now");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for vote");
         testOperation(  transactionConfirmButtonId,"click","Confirm the vote transaction");
    }
    @AfterClass
    public void teardown(){
        driver.resetApp();
    }

}
