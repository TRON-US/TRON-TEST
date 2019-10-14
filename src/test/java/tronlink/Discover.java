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

public class Discover extends AppiumTestCase {


    @Test(enabled = false, threadPoolSize = 1, invocationCount = 1)
    public void test01DiscoverScreen() {
        importWallet(testPrivateKey);

        //Enter to discover screen
         testOperation(  discoverIconId,"click","Enter Discover screen");
         testOperation( "swipeDown","");
         testOperation( "swipeUp","");

        //Query dapp
         testOperation(  discoverSearchEnterId,"click","Enter discover search screen");
         testOperation(  discoverSearchInputId,"input","TRX","Search keyword of trx from dapp search ");
         testOperation(  discoverSearchConfirmId,"click","Confirm the discover search content");
        String firstSearchedDappName =  getText(  discoverSearchResultNameId);
        String firttSearchedDappDescription =  getText(  discoverSearchresultDescriptionId);
        Assert.assertTrue(firstSearchedDappName.contains("TRX"));
        Assert.assertTrue(firttSearchedDappDescription.contains("TRX"));

        //Test the link is clickable.
         testOperation(  discoverSearchFirstResultXPath,"click","Try to enter thrid-party Dapp");
        Assert.assertTrue( isEnabled(  enterConfirmIconOfFirstDiscoverSearchId));
         testOperation(  enterCancelIconOfFirstDiscoverSearchId,"click","Cancel discover search");
        Assert.assertTrue( isEnabled(  discoverSearchConfirmId));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //History function
         testOperation(  discoverSearchHistoryId,"click","Search discover search history");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Scan function
         testOperation(  discoverSearchScanId,"click","Scan function");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

}
