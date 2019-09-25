package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SendTrx {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = InitTest.driver;
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


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
