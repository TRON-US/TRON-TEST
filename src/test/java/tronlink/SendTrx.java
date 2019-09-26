package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SendTrx extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
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
    @AfterClass
    public void teardown(){
        driver.resetApp();
    }


}
