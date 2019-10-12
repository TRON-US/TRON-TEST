package tronlink;
import common.utils.AppiumTestCase;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ImportAccount extends AppiumTestCase {

    public   String walletAddress = "";
    public   String walletPassword = "Test0001";
    public   String walletPrivateKey = "";
    public   String backupMnemonic = "";
    public   StringBuffer backupMnemonicBf = new StringBuffer();
    public   String backupkeystore = "";

    @Test
    public void createAccount() {
        //startup page
         getScreenshot( "Startup page");
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //create account
         testOperation(  createWallet,"click","click create wallet");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","1:input name");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //backup mnemonic
         testOperation(  backUpNow,"click","back up now");
         testOperation(  gotItButton,"click","got it");

        //backup mnemonic
         testOperation( "swipeUp","back up now");
         testOperation(  saveKey,"click","back up now");
        ArrayList<String> allTextList =  getTextList(  keyIndexText);
        for (String data : allTextList){
            backupMnemonicBf.append(data);
            backupMnemonicBf.append(" ");
            System.out.println(data);
        }
        backupMnemonic = backupMnemonicBf.toString();
        System.out.println(backupMnemonic);
        //confirm mnemonic
        List<MobileElement> confirmElements = driver.findElementsById( itemText);
        confirmElements.get( getSameMnemonicIdex( allTextList, itemText, numberIndex)).click();
         testOperation(  nextStepButton,"click","back up now");
        confirmElements = driver.findElementsById( itemText);
        confirmElements.get( getSameMnemonicIdex( allTextList, itemText, numberIndex)).click();
         testOperation(  nextStepButton,"click","back up now");

        //tab me
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");

        //get information
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");

         testOperation(  backupPrivateKey,"click","click backup PrivateKey");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");
        walletPrivateKey =  getText(  privateKeyText);
         testOperation(  done,"click","click done");
         testOperation( "swipeUp","");


         testOperation(  backupKeystore,"click","click backup Keystore");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");
        backupkeystore =  getText(  keystoreText);
         testOperation(  done,"click","click done");
         testOperation(  deleteWallet,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");

        System.out.println("walletAddress:" + walletAddress + "\nwalletPassword:" + walletPassword + "\nwalletPrivateKey:" +walletPrivateKey +"\nbackupMnemonic:" +backupMnemonic + "\nbackupkeystore:" + backupkeystore);
         QRCode(walletAddress,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/walletAddress.png","walletAddress");
         QRCode(walletPrivateKey,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/walletPrivateKey.png","walletPrivateKey");
         QRCode(backupMnemonic,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/backupMnemonic.png","backupMnemonic");
         QRCode(backupkeystore,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/backupkeystore.png","backupkeystore");
    }

    @Test
    public void import_PrivateKey(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //use Private Key import account
         testOperation(  privateKey,"click","click Private key");
         testOperation(  enterContent,"input",walletPrivateKey,"enter private key");
         testOperation(  nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","1:input name");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet2,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");

    }

    @Test(enabled = false)
    public void import_QRPrivateKey(){

        //use PrivateKey QRcode import account
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");
         testOperation(  privateKey,"click","click Private key");
         testOperation(  qrButton,"click","click qr code");
         clickPicture( "walletPrivateKey");

         testOperation(  nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","1:input name");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet2,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");
    }

    @Test
    public void import_WatchAddress(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import watch account
         testOperation(  watchWallet,"click","click watch wallet");
         testOperation(  enterContent,"input",walletAddress,"enter address");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
         testOperation(  deleteWalletTop,"click","click delete wallet");
         testOperation(  confirm,"click","click confirm");

    }

    @Test(enabled = false)
    public void import_QRWatchAddress(){

        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import watch account use qrcode
         testOperation(  watchWallet,"click","click watch wallet");
         testOperation(  qrButton,"click","click qr code");
         clickPicture( "walletAddress");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
         testOperation(  deleteWalletTop,"click","click delete wallet");
         testOperation(  confirm,"click","click confirm");
    }

    @Test
    public void import_Mnemonic(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import Mnemonic account
         testOperation(  mnemonic,"click","click mnemonic wallet");
         testOperation(  hdWallet,"click","click hdWallet");
         testOperation(  enterContent,"input",backupMnemonic,"enter backupMnemonic");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","click next step");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");

    }

    @Test(enabled = false)
    public void import_QRMnemonic(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import Mnemonic account use qrcode
         testOperation(  mnemonic,"click","click mnemonic wallet");
         testOperation(  hdWallet,"click","click hdWallet");
         testOperation(  qrButton,"click","click qr code");
         clickPicture( "backupMnemonic");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","click next step");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  creatNextStep2,"click","2:click next step");
         testOperation(  passWord,"input","Test0001","input password again");
         testOperation(  creatNextStep3,"click","3:click carry out");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");
    }

    @Test
    public void import_Backupkeystore(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import Backupkeystore account
         testOperation(  keystore,"click","click mnemonic wallet");
         testOperation(  enterContent,"input",backupkeystore,"enter backupkeystore");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","click next step");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet2,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");



    }

    @Test(enabled = false)
    public void import_QRBackupkeystore(){
        //startup page
         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import Backupkeystore account
         testOperation(  keystore,"click","click mnemonic wallet");
         testOperation(  enterContent,"input",backupkeystore,"enter backupkeystore");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","click next step");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet2,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");


         testOperation(   importAccountId,"click","click import Account");
        while (! isEnabled(  acceptImportAccount)){
             testOperation( "swipeUp","");
        }
         testOperation(  acceptImportAccount,"click","click Accept");

        //import Backupkeystore account use qrcode
         testOperation(  keystore,"click","click keystore wallet");
         testOperation(  qrButton,"click","click qr code");
         systemAllow();
         clickPicture( "backupkeystore");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  nextStep,"click","click next step");
        timestamp = String.valueOf(date.getTime());
         testOperation(  setUpName,"input","Test_"+timestamp,"input name");
         testOperation(  creatNextStep,"click","click next step");

        //delete wallet
         testOperation(  tabMy,"click","click tab My");
         testOperation(  my_walletManager,"click","click wallet manager");
        walletAddress =  getText(  addressText);
         testOperation( "swipeUp","");
         testOperation(  deleteWallet2,"click","click delete wallet");
         testOperation(  passWord,"input","Test0001","input password");
         testOperation(  riskBackup,"click","click ok");

    }



    @AfterClass
    public void teardown(){
        driver.resetApp();
    }
}
