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

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TransferTrx {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities = TronLink.getTronLinkDesiredCapabilities(desiredCapabilities);
        URL remoteUrl = new URL(TronLink.tronLinkUrl);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }



    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01TransferTrx() {
        TronLink.testOperation(driver,TronLink.meIconId,"click","Enter to market screen");
        TronLink.testOperation(driver,TronLink.discoverIconId,"click","Enter to market screen");
        TronLink.testOperation(driver,TronLink.marketsIconId,"click","Enter to market screen");
        TronLink.testOperation(driver,TronLink.assetIconId,"click","Enter asset screen");
        TronLink.testOperation(driver,TronLink.sendCoinId,"click","Enter send coin screen");
        TronLink.testOperation(driver,TronLink.receiveAddressId,"input",TronLink.receiverAddress,"Input receiver address");
        TronLink.testOperation(driver,TronLink.sendCoinAmountId,"input",String.valueOf(TronLink.sendCoinAmount),"Input send coin amount");
        TronLink.testOperation(driver,TronLink.sendCoinButtonId,"click","Send coin");
        TronLink.testOperation(driver,TronLink.transferNowId,"click","Transfer trx now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for transfer trx");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the transfer");


        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test02EnterToTronLending() {
        TronLink.testOperation(driver,TronLink.tronLendingId,"click","Enter energy lease screen");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test03FreezeToGetBandwidth() {
        TronLink.testOperation(driver,TronLink.freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
        TronLink.testOperation(driver,TronLink.bandwidthOptionIconId,"click","Choose bandwidth option");
        TronLink.testOperation(driver,TronLink.frozenQuantityInputId,"input",String.valueOf(TronLink.frozenQuantityForBandwidth),"Input frozen quantity for bandwidth");
        TronLink.swipeUp(driver);
        TronLink.testOperation(driver,TronLink.freezeIconId,"click","Click freeze icon");
        TronLink.testOperation(driver,TronLink.freezeNowIconId,"click","Freeze bandwidth now");
        Assert.assertEquals(driver.findElement(By.id(TronLink.transactionConfirmInputPasswordId)).getText(),"Confirm");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for freeze bandwidth");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the freeze bandwidth transaction");

    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test04FreezeToGetEnergy() {
        TronLink.testOperation(driver,TronLink.energyOptionIconId,"click","Choose energy option");
        TronLink.testOperation(driver,TronLink.frozenQuantityInputId,"input",String.valueOf(TronLink.frozenQuantityForEnergy),"Input frozen quantity for energy");
        TronLink.swipeUp(driver);
        TronLink.testOperation(driver,TronLink.freezeIconId,"click","Click freeze icon");
        TronLink.testOperation(driver,TronLink.freezeNowIconId,"click","Freeze bandwidth now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for freeze energy");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the freeze energy transaction");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test05Vote() {
        TronLink.testOperation(driver,TronLink.voteId,"click","Enter to vote screen");
        TronLink.testOperation(driver,TronLink.voteResetId,"click","Reset vote");
        TronLink.testOperation(driver,TronLink.voteInputQuantityXPath,"input",String.valueOf(TronLink.voteQuantity),"Input vote amount");
        TronLink.testOperation(driver,TronLink.voteId,"click","Vote");
        TronLink.testOperation(driver,TronLink.voteNowId,"click","Vote now");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for vote");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the vote transaction");
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
