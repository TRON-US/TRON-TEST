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

public class TransactionHistory {

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
        TronLink.testOperation(driver,TronLink.meIconId,"click","Enter me screen");
        TronLink.testOperation(driver,TronLink.transactionHistoryId,"click","Enter me screen");
        //All/Received/Sent result switch
        TronLink.testOperation(driver,TronLink.transactionHistoryReceiveXPath,"click","Switch to received result");
        TronLink.testOperation(driver,TronLink.transactionHistoryAllXPath,"click","Switch to all result");
        TronLink.testOperation(driver,TronLink.transactionHistorySentXPath,"click","Switch to sent result");

        //Check the sent detail
        String sentAddress = TronLink.getText(driver,TronLink.transactionHistorySentFirstResultAddressXPath);
        Assert.assertEquals(sentAddress,TronLink.ownerAddress);

        String contractType = TronLink.getText(driver,TronLink.transactionHistorySentFirstResultContractTypeXPath);
        Assert.assertFalse(contractType.isEmpty());

        String confirmeStatus = TronLink.getText(driver,TronLink.transactionHistorySentFirstResultConfirmedXPath);
        Assert.assertFalse(confirmeStatus.isEmpty());

        String dateDetail = TronLink.getText(driver,TronLink.transactionHistorySentFirstResultDateXPath);
        Assert.assertFalse(dateDetail.isEmpty());

        String sentAmount = TronLink.getText(driver,TronLink.transactionHistorySentFirstResultAmountXPath);
        Assert.assertFalse(sentAmount.isEmpty());

        //Check the receive detail
        TronLink.testOperation(driver,TronLink.transactionHistoryReceiveXPath,"click","Switch to received result");
        String receiverAddress = TronLink.getText(driver,TronLink.transactionHistoryReceivedFirstResultAddressXPath);
        Assert.assertEquals(receiverAddress,TronLink.ownerAddress);

        //Select wallet
        TronLink.testOperation(driver,TronLink.transactionHistoryQueryWalletId,"click","Check wallet information");
        String walletName = TronLink.getText(driver,TronLink.transactionHistoryWalletResultNameId);
        Assert.assertTrue(walletName.contains("Test_"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Enter to transcan to get the transaction detail
        TronLink.testOperation(driver,TronLink.transactionHistorySentXPath,"click","Switch to sent result");
        TronLink.testOperation(driver,TronLink.transactionHistorySentFirstResultConfirmedXPath,"click","Enter to tronscan");





    }


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
