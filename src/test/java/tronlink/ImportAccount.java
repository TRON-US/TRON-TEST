package tronlink;
import common.utils.TronLink;
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

public class ImportAccount {

    private AndroidDriver driver;
    public static String walletAddress = "";
    public static String walletPassword = "Test0001";
    public static String walletPrivateKey = "";
    public static String backupMnemonic = "";
    public static StringBuffer backupMnemonicBf = new StringBuffer();
    public static String backupkeystore = "";



    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = InitTest.driver;
    }

    @Test
    public void createAccount() {
        //startup page
        TronLink.getScreenshot(driver,"Startup page");
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //create account
        TronLink.testOperation(driver,TronLink.createWallet,"click","click create wallet");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //backup mnemonic
        TronLink.testOperation(driver,TronLink.backUpNow,"click","back up now");
        TronLink.testOperation(driver,TronLink.gotItButton,"click","got it");

        //backup mnemonic
        TronLink.testOperation(driver,"swipeUp","back up now");
        TronLink.testOperation(driver,TronLink.saveKey,"click","back up now");
        ArrayList<String> allTextList = TronLink.getTextList(driver,TronLink.keyIndexText);
        for (String data : allTextList){
            backupMnemonicBf.append(data);
            backupMnemonicBf.append(" ");
            System.out.println(data);
        }
        backupMnemonic = backupMnemonicBf.toString();
        System.out.println(backupMnemonic);
        //confirm mnemonic
        List<MobileElement> confirmElements = driver.findElementsById(TronLink.itemText);
        confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
        TronLink.testOperation(driver,TronLink.nextStepButton,"click","back up now");
        confirmElements = driver.findElementsById(TronLink.itemText);
        confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
        TronLink.testOperation(driver,TronLink.nextStepButton,"click","back up now");

        //tab me
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");

        //get information
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");

        TronLink.testOperation(driver,TronLink.backupPrivateKey,"click","click backup PrivateKey");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
        walletPrivateKey = TronLink.getText(driver,TronLink.privateKeyText);
        TronLink.testOperation(driver,TronLink.done,"click","click done");
        TronLink.testOperation(driver,"swipeUp","");


        TronLink.testOperation(driver,TronLink.backupKeystore,"click","click backup Keystore");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
        backupkeystore = TronLink.getText(driver,TronLink.keystoreText);
        TronLink.testOperation(driver,TronLink.done,"click","click done");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");

        System.out.println("walletAddress:" + walletAddress + "\nwalletPassword:" + walletPassword + "\nwalletPrivateKey:" +walletPrivateKey +"\nbackupMnemonic:" +backupMnemonic + "\nbackupkeystore:" + backupkeystore);
        TronLink.QRCode(walletAddress,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/walletAddress.png","walletAddress");
        TronLink.QRCode(walletPrivateKey,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/walletPrivateKey.png","walletPrivateKey");
        TronLink.QRCode(backupMnemonic,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/backupMnemonic.png","backupMnemonic");
        TronLink.QRCode(backupkeystore,"/Users/tron/Documents/TestUi/tron-UITest/src/test/resources/QRresources/backupkeystore.png","backupkeystore");
    }

    @Test
    public void import_PrivateKey(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //use Private Key import account
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.enterContent,"input",walletPrivateKey,"enter private key");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet2,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");

    }

    @Test
    public void import_QRPrivateKey(){

        //use PrivateKey QRcode import account
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");
        TronLink.testOperation(driver,TronLink.privateKey,"click","click Private key");
        TronLink.testOperation(driver,TronLink.qrButton,"click","click qr code");
        TronLink.clickPicture(driver,"walletPrivateKey");

        TronLink.testOperation(driver,TronLink.nextStep,"click","click Next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet2,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
    }

    @Test
    public void import_WatchAddress(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import watch account
        TronLink.testOperation(driver,TronLink.watchWallet,"click","click watch wallet");
        TronLink.testOperation(driver,TronLink.enterContent,"input",walletAddress,"enter address");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        TronLink.testOperation(driver,TronLink.deleteWalletTop,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.confirm,"click","click confirm");

    }

    @Test
    public void import_QRWatchAddress(){

        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import watch account use qrcode
        TronLink.testOperation(driver,TronLink.watchWallet,"click","click watch wallet");
        TronLink.testOperation(driver,TronLink.qrButton,"click","click qr code");
        TronLink.clickPicture(driver,"walletAddress");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        TronLink.testOperation(driver,TronLink.deleteWalletTop,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.confirm,"click","click confirm");
    }

    @Test
    public void import_Mnemonic(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import Mnemonic account
        TronLink.testOperation(driver,TronLink.mnemonic,"click","click mnemonic wallet");
        TronLink.testOperation(driver,TronLink.hdWallet,"click","click hdWallet");
        TronLink.testOperation(driver,TronLink.enterContent,"input",backupMnemonic,"enter backupMnemonic");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");

    }

    @Test
    public void import_QRMnemonic(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import Mnemonic account use qrcode
        TronLink.testOperation(driver,TronLink.mnemonic,"click","click mnemonic wallet");
        TronLink.testOperation(driver,TronLink.hdWallet,"click","click hdWallet");
        TronLink.testOperation(driver,TronLink.qrButton,"click","click qr code");
        TronLink.clickPicture(driver,"backupMnemonic");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
        TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
    }

    @Test
    public void import_Backupkeystore(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import Backupkeystore account
        TronLink.testOperation(driver,TronLink.keystore,"click","click mnemonic wallet");
        TronLink.testOperation(driver,TronLink.enterContent,"input",backupkeystore,"enter backupkeystore");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","click next step");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet2,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");



    }

    @Test
    public void import_QRBackupkeystore(){
        //startup page
        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import Backupkeystore account
        TronLink.testOperation(driver,TronLink.keystore,"click","click mnemonic wallet");
        TronLink.testOperation(driver,TronLink.enterContent,"input",backupkeystore,"enter backupkeystore");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","click next step");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet2,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");


        TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
        while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
            TronLink.testOperation(driver,"swipeUp","");
        }
        TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

        //import Backupkeystore account use qrcode
        TronLink.testOperation(driver,TronLink.keystore,"click","click keystore wallet");
        TronLink.testOperation(driver,TronLink.qrButton,"click","click qr code");
        TronLink.clickPicture(driver,"backupkeystore");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.nextStep,"click","click next step");
        timestamp = String.valueOf(date.getTime());
        TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
        TronLink.testOperation(driver,TronLink.creatNextStep,"click","click next step");

        //delete wallet
        TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
        TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");
        walletAddress = TronLink.getText(driver,TronLink.addressText);
        TronLink.testOperation(driver,"swipeUp","");
        TronLink.testOperation(driver,TronLink.deleteWallet2,"click","click delete wallet");
        TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
        TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");

    }



    @AfterClass
    public void tearDown() {
//        driver.resetApp();
    }
}
