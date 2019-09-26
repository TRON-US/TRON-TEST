package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import common.utils.AppiumTestCase;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Markets extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01marketsScreen() {
        importWallet(testPrivateKey);

        //Test "Price Change" sort function
         testOperation(  marketsIconId,"click","Enter markets screen");
        String originSortFirstPrice =  getText(  firstPriceOfPriceChangeXPath);
         testOperation(  priceChangeId,"click","Price sort change by \"Price Change\"");
        String changeSortFirstPrice =  getText(  firstPriceOfPriceChangeXPath);
        Assert.assertFalse(originSortFirstPrice.equals(changeSortFirstPrice));

        //Test "Lastest Price" sort function
         testOperation(  lastestPriceXPath,"click","Price sort change by \"Latest Price\"");
        originSortFirstPrice =  getText(  firstPriceOfLastestPriceXPath);
        //System.out.println("originSortFirstPrice:" + originSortFirstPrice);
         testOperation(  lastestPriceXPath,"click","Price sort change by \"Latest Price\"");
        changeSortFirstPrice =  getText(  firstPriceOfLastestPriceXPath);
        //System.out.println("changeSortFirstPrice:" + changeSortFirstPrice);
        Assert.assertFalse(originSortFirstPrice.equals(changeSortFirstPrice));

        //Test search markets function
         testOperation(  marketsSearchId,"click","Enter markets search screen");
         testOperation(  marketsSearchInputId,"input","TRX","Search trx");
        //System.out.println( getText(  firstExchangeInMarketsSearchScreenXPath));
        Assert.assertTrue( getText(  firstExchangeInMarketsSearchScreenXPath).contains("TRX"));







    }



}
