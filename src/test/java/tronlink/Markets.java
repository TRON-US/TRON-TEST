package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import common.utils.TronLink;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Markets {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = TronLink.driverTron;
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01marketsScreen() {
        //Test "Price Change" sort function
        TronLink.testOperation(driver,TronLink.marketsIconId,"click","Enter markets screen");
        String originSortFirstPrice = TronLink.getText(driver,TronLink.firstPriceOfPriceChangeXPath);
        TronLink.testOperation(driver,TronLink.priceChangeId,"click","Price sort change by \"Price Change\"");
        String changeSortFirstPrice = TronLink.getText(driver,TronLink.firstPriceOfPriceChangeXPath);
        Assert.assertFalse(originSortFirstPrice.equals(changeSortFirstPrice));

        //Test "Lastest Price" sort function
        TronLink.testOperation(driver,TronLink.lastestPriceXPath,"click","Price sort change by \"Latest Price\"");
        originSortFirstPrice = TronLink.getText(driver,TronLink.firstPriceOfLastestPriceXPath);
        //System.out.println("originSortFirstPrice:" + originSortFirstPrice);
        TronLink.testOperation(driver,TronLink.lastestPriceXPath,"click","Price sort change by \"Latest Price\"");
        changeSortFirstPrice = TronLink.getText(driver,TronLink.firstPriceOfLastestPriceXPath);
        //System.out.println("changeSortFirstPrice:" + changeSortFirstPrice);
        Assert.assertFalse(originSortFirstPrice.equals(changeSortFirstPrice));

        //Test search markets function
        TronLink.testOperation(driver,TronLink.marketsSearchId,"click","Enter markets search screen");
        TronLink.testOperation(driver,TronLink.marketsSearchInputId,"input","TRX","Search trx");
        //System.out.println(TronLink.getText(driver,TronLink.firstExchangeInMarketsSearchScreenXPath));
        Assert.assertTrue(TronLink.getText(driver,TronLink.firstExchangeInMarketsSearchScreenXPath).contains("TRX"));







    }


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
