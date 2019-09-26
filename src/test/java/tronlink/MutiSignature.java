package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MutiSignature extends AppiumTestCase {



    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01MutiSignScreen() {
        importWallet(testPrivateKey);

        //Enter to Multi-signature Management screen
         testOperation(  meIconId,"click","Enter me screen");
         testOperation(  my_walletManager,"click","Enter my wallet manager screen");
         testOperation(  mutliSignatureManagementId,"click","Enter to multi-signature screen");

        //Mutli sign question content
         testOperation(  mutliSignQuestionId,"click","View the multi sign question content");
        String multiSignQuestionContent =  getText(  mutliSignQuestionContentXPath);
        Assert.assertTrue(multiSignQuestionContent.contains("Active Permission"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Add permission
         testOperation(  mutliSignAddPermissionId,"click","Enter add permission screen");
        // testOperation(  activeScreenMoreId,"click","Show more in add active screen");
        // testOperation(  addPermissionId,"click","Enter add permission");
        Date date = new Date();
        String activePermissionName = "activePermission" + date.getTime();
         testOperation(  permissionNameInputId,"input",activePermissionName,"input permission name");
         testOperation(  trxTransferOperationInAddPermissionXPath,"click","Add TRX transfer operation");
         testOperation(  trc10TransferOperationInAddPermissionXPath,"click","Add TRC10 transfer operation");
         testOperation(  trc20TransferOperationInAddPermissionXPath,"click","Add TRC20 transfer operation");
         testOperation(  thresholdInAddPermissionInputId,"input",String.valueOf(50),"Input threshold");

         testOperation(  permissionAddressInputId,"input", activePermissionManager1Address,"Input manager 1 address");
         testOperation(  permissionWeightInputId,"input",String.valueOf(24),"Input manager 1 weight");
         testOperation(  addKeyInAddPermissionId,"click","Add key");
         testOperation(  secondPermissionAddressInputXPath,"input", activePermissionManager2Address,"Input manager 2 address");
         testOperation(  secondPermissionWeightInputXPath,"input",String.valueOf(26),"Input manager 2 weight");
         testOperation(  addPermissionConfirmId,"click","Confirm the add permission");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for add permission");
        // testOperation(  transactionConfirmButtonId,"click","Confirm the add permission");


/*        //Modify permission.
         swipeRight(driver);
         swipeRight(driver);
         testOperation(  activeScreenMoreId,"click","Show the modify and delete icon");
         testOperation(  modifyPermissionsId,"click","Click modify permissions");
        Assert.assertTrue( isDisplayed(  permissionOperationId));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Delete permission.
         testOperation(  deletePermissionId,"click","Click delete permissions");
         testOperation(  deletePermissionCancelId,"click","Cancel delete permissions");
         testOperation(  deletePermissionId,"click","Click delete permissions");
         testOperation(  deletePermissionConfirmId,"click","Confirm delete permissions");
         testOperation(  transactionConfirmInputPasswordId,"input", testPassword,"Input password for delete permission");
         testOperation(  transactionConfirmButtonId,"click","Confirm the delete permission");*/
    }

    @AfterClass
    public void teardown(){
        driver.resetApp();
    }

}
