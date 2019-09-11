package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MutiSignature {

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
    public void test01MutiSignScreen() {
        //Enter to Multi-signature Management screen
        TronLink.testOperation(driver,TronLink.meIconId,"click","Enter me screen");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","Enter my wallet manager screen");
        TronLink.testOperation(driver,TronLink.mutliSignatureManagementId,"click","Enter to multi-signature screen");

        //Mutli sign question content
        TronLink.testOperation(driver,TronLink.mutliSignQuestionId,"click","View the multi sign question content");
        String multiSignQuestionContent = TronLink.getText(driver,TronLink.mutliSignQuestionContentXPath);
        Assert.assertTrue(multiSignQuestionContent.contains("Active Permission"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Add permission
        TronLink.testOperation(driver,TronLink.mutliSignAddPermissionId,"click","Enter add permission screen");
        TronLink.testOperation(driver,TronLink.activeScreenMoreId,"click","Show more in add active screen");
        TronLink.testOperation(driver,TronLink.addPermissionId,"click","Enter add permission");
        Date date = new Date();
        String activePermissionName = "activePermission" + date.getTime();
        TronLink.testOperation(driver,TronLink.permissionNameInputId,"input","Permission_"+activePermissionName,"input permission name");
        TronLink.testOperation(driver,TronLink.trxTransferOperationInAddPermissionXPath,"click","Add TRX transfer operation");
        TronLink.testOperation(driver,TronLink.trc10TransferOperationInAddPermissionXPath,"click","Add TRC10 transfer operation");
        TronLink.testOperation(driver,TronLink.trc20TransferOperationInAddPermissionXPath,"click","Add TRC20 transfer operation");
        TronLink.testOperation(driver,TronLink.thresholdInAddPermissionInputId,"input",TronLink.activePermissionManager1Address,"Input manager 1 address");
        TronLink.testOperation(driver,TronLink.permissionWeightInputId,"input",String.valueOf(24),"Input manager 1 weight");
        TronLink.testOperation(driver,TronLink.addKeyInAddPermissionId,"click","Add key");
        TronLink.testOperation(driver,TronLink.secondPermissionAddressInputXPath,"input",TronLink.activePermissionManager2Address,"Input manager 2 address");
        TronLink.testOperation(driver,TronLink.secondPermissionWeightInputXPath,"input",String.valueOf(26),"Input manager 2 weight");
        TronLink.testOperation(driver,TronLink.addPermissionConfirmId,"click","Confirm the add permission");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for add permission");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the add permission");


        //Modify permission.
        TronLink.swipeRight(driver);
        TronLink.swipeRight(driver);
        TronLink.testOperation(driver,TronLink.activeScreenMoreId,"click","Show the modify and delete icon");
        TronLink.testOperation(driver,TronLink.modifyPermissionsId,"click","Click modify permissions");
        Assert.assertTrue(TronLink.isDisplayed(driver,TronLink.permissionOperationId));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Delete permission.
        TronLink.testOperation(driver,TronLink.deletePermissionId,"click","Click delete permissions");
        TronLink.testOperation(driver,TronLink.deletePermissionCancelId,"click","Cancel delete permissions");
        TronLink.testOperation(driver,TronLink.deletePermissionId,"click","Click delete permissions");
        TronLink.testOperation(driver,TronLink.deletePermissionConfirmId,"click","Confirm delete permissions");
        TronLink.testOperation(driver,TronLink.transactionConfirmInputPasswordId,"input",TronLink.testPassword,"Input password for delete permission");
        TronLink.testOperation(driver,TronLink.transactionConfirmButtonId,"click","Confirm the delete permission");
    }


    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
