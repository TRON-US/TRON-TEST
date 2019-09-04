package tronlink;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import common.utils.TronLink;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;

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



    @Test
    public void test01TransferTrx() {
        //driver.pressKeyCode(AndroidKeyCode.BACK);

        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.elementToBeClickable(By.id(TronLink.marketsIconId)));
        //TronLink.testOperation(driver,TronLink.marketsIconId,"click","Enter send coin screen");
        //TronLink.testOperation(driver,TronLink.assetIconId,"click","Enter send coin screen");

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.tronlink.wallet:id/tv_loading")));
        //System.out.print(driver.getPageSource());
        //TronLink.testOperation(driver,"com.tronlink.wallet:id/tv_loading","click","Enter send coin screen");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.tronlink.wallet:id/tv_loading")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.id(TronLink.marketsIconId)));
        //wait.until(ExpectedConditions.elementToBeClickable(By.id(TronLink.sendCoinId)));
/*        for (int second = 0;second <= 3; second++) {
            try {
                System.out.print(driver.getPageSource());
                driver.findElement(By.id(TronLink.sendCoinId)).isDisplayed();
                break;
            } catch (Exception e) {
                TronLink.waitTargetElementAppear(driver);
            }

        }*/
        //System.out.print(driver.getPageSource());
        TronLink.testOperation(driver,TronLink.marketsIconId,"click","Enter send coin screen");
        TronLink.testOperation(driver,TronLink.assetIconId,"click","Enter send coin screen");
        TronLink.testOperation(driver,TronLink.sendCoinId,"click","Enter send coin screen");
        TronLink.testOperation(driver,TronLink.receiveAddressId,"input",TronLink.receiverAddress,"Input receiver address");
        TronLink.testOperation(driver,TronLink.sendCoinAmountId,"input",String.valueOf(TronLink.sendCoinAmount),"Input send coin amount");
        TronLink.testOperation(driver,TronLink.sendCoinButtonId,"click","Send coin");
        TronLink.testOperation(driver,TronLink.transferNowId,"click","Transfer trx now");
        TronLink.testOperation(driver,TronLink.transferInputPasswordId,"input",TronLink.testPassword,"Input password for transfer trx");
        TronLink.testOperation(driver,TronLink.transferConfirmButtonId,"click","Confirm the transfer");

    }



    @AfterClass
    public void tearDown() {
       //driver.quit();
    }
}
