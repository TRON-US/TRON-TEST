package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;

public class ReceiveTrx extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01Receiver() {
         testOperation(  assetIconId,"click","Enter asset screen");
         testOperation(  receiveCoinId,"click","Enter receive screen");
         testOperation(  copyAddressIconId,"click","Copy address");


        //String copyAddress = driver.getClipboard(ClipboardContentType.PLAINTEXT);


        //String copyAddress = ((HasClipboard) driver).getClipboardText();
        //Assert.assertEquals(copyAddress, ownerAddress);
        Assert.assertEquals( getText(  receiveScreenAddressTextId), ownerAddress);
         testOperation(  receiveScreenSameQRCodeId,"click","Save QR code");


    }



}
