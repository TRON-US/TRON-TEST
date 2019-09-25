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

public class Discover {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = InitTest.driver;
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01DiscoverScreen() {
        //Enter to discover screen
        TronLink.testOperation(driver,TronLink.discoverIconId,"click","Enter Discover screen");
        TronLink.testOperation(driver,"swipeDown","");
        TronLink.testOperation(driver,"swipeUp","");

        //Query dapp
        TronLink.testOperation(driver,TronLink.discoverSearchEnterId,"click","Enter discover search screen");
        TronLink.testOperation(driver,TronLink.discoverSearchInputId,"input","TRX","Search keyword of trx from dapp search ");
        TronLink.testOperation(driver,TronLink.discoverSearchConfirmId,"click","Confirm the discover search content");
        String firstSearchedDappName = TronLink.getText(driver,TronLink.discoverSearchResultNameId);
        String firttSearchedDappDescription = TronLink.getText(driver,TronLink.discoverSearchresultDescriptionId);
        Assert.assertTrue(firstSearchedDappName.contains("TRX"));
        Assert.assertTrue(firttSearchedDappDescription.contains("TRX"));

        //Test the link is clickable.
        TronLink.testOperation(driver,TronLink.discoverSearchFirstResultXPath,"click","Try to enter thrid-party Dapp");
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.enterConfirmIconOfFirstDiscoverSearchId));
        TronLink.testOperation(driver,TronLink.enterCancelIconOfFirstDiscoverSearchId,"click","Cancel discover search");
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.discoverSearchConfirmId));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //History function
        TronLink.testOperation(driver,TronLink.discoverSearchHistoryId,"click","Search discover search history");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Scan function
        TronLink.testOperation(driver,TronLink.discoverSearchScanId,"click","Scan function");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));












    }


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
