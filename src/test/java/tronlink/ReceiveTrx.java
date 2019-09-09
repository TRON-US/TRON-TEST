package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;

public class ReceiveTrx {

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
    public void test01Receiver() {
        TronLink.testOperation(driver,TronLink.assetIconId,"click","Enter asset screen");
        TronLink.testOperation(driver,TronLink.receiveCoinId,"click","Enter receive screen");
        TronLink.testOperation(driver,TronLink.copyAddressIconId,"click","Copy address");


        //String copyAddress = driver.getClipboard(ClipboardContentType.PLAINTEXT);


        //String copyAddress = ((HasClipboard) driver).getClipboardText();
        //Assert.assertEquals(copyAddress,TronLink.ownerAddress);
        Assert.assertEquals(TronLink.getText(driver,TronLink.receiveScreenAddressTextId),TronLink.ownerAddress);
        TronLink.testOperation(driver,TronLink.receiveScreenSameQRCodeId,"click","Save QR code");


    }


    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
