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

public class TransactionHistory extends AppiumTestCase {


    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01DiscoverScreen() {
        importWallet(testPrivateKey);

        //Enter to discover screen
         testOperation(  meIconId,"click","Enter me screen");
         testOperation(  transactionHistoryId,"click","Enter me screen");
        //All/Received/Sent result switch
         testOperation(  transactionHistoryReceiveXPath,"click","Switch to received result");
         testOperation(  transactionHistoryAllXPath,"click","Switch to all result");
         testOperation(  transactionHistorySentXPath,"click","Switch to sent result");

        //Check the sent detail
        String sentAddress =  getText(  transactionHistorySentFirstResultAddressXPath);
        Assert.assertEquals(sentAddress, ownerAddress);

        String contractType =  getText(  transactionHistorySentFirstResultContractTypeXPath);
        Assert.assertFalse(contractType.isEmpty());

        String confirmeStatus =  getText(  transactionHistorySentFirstResultConfirmedXPath);
        Assert.assertFalse(confirmeStatus.isEmpty());

        String dateDetail =  getText(  transactionHistorySentFirstResultDateXPath);
        Assert.assertFalse(dateDetail.isEmpty());

        String sentAmount =  getText(  transactionHistorySentFirstResultAmountXPath);
        Assert.assertFalse(sentAmount.isEmpty());

        //Check the receive detail
         testOperation(  transactionHistoryReceiveXPath,"click","Switch to received result");
        String receiverAddress =  getText(  transactionHistoryReceivedFirstResultAddressXPath);
        Assert.assertEquals(receiverAddress, ownerAddress);

        //Select wallet
         testOperation(  transactionHistoryQueryWalletId,"click","Check wallet information");
        String walletName =  getText(  transactionHistoryWalletResultNameId);
        Assert.assertTrue(walletName.contains("Test_"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Enter to transcan to get the transaction detail
         testOperation(  transactionHistorySentXPath,"click","Switch to sent result");
         testOperation(  transactionHistorySentFirstResultConfirmedXPath,"click","Enter to tronscan");

    }

    @AfterClass
    public void teardown(){
        driver.resetApp();
    }

}
