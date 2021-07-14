package android.com.wallet.pages;

import android.com.utils.Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

/**
 * 我的钱包页面
 */
public class MyPursePage extends AbstractPage {



    public AndroidDriver<?> driver;



    public MyPursePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }




    @FindBy(id = "wallet.tronlink.harmony:id/rl_sign_manager")
    public WebElement multSignManager_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_address")
    public WebElement address_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_name")
    public WebElement walletname_text;



    @FindBy(id = "wallet.tronlink.harmony:id/rl_password")
    public WebElement walletPassword_btn;




    //@FindBy(id = "wallet.tronlink.harmony:id/tv_create")
    //public WebElement newCreate_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/iv_create")
    public WebElement newCreate_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/create_option_desc")
    public WebElement normal_account_type;

    //wallet.tronlink.harmony:id/create_option_desc_shield
    @FindBy(id = "wallet.tronlink.harmony:id/create_option_desc_shield")
    public WebElement shield_account_type;


    @FindBy(id = "wallet.tronlink.harmony:id/rl_keystore2")
    public WebElement backupKeystore_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/rl_privatekey")
    public WebElement backupPrivateKey_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/et_password")
    public WebElement password_et;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_ok")
    public WebElement confirm_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_keystore")
    public WebElement keystore_text;




    @FindBy(id = "wallet.tronlink.harmony:id/backup")
    public WebElement done_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_address")
    public WebElement walletAddress_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_chain_name")
    public WebElement chainName_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/iv_common_left")
    public WebElement back_btn;




    @FindBy(xpath = "//*[@text='多重签名交易']")
    public WebElement mulSignTranfer_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/editText")
    public WebElement sk_text;

    @FindBy(id = "wallet.tronlink.harmony:id/editText")
    public WebElement shieldAddress_text;

    @FindBy(id = "wallet.tronlink.harmony:id/bt_know")
    public WebElement i_know_btn;




    public MultiSignManagerPage enterMultiSignManagerPage() {
        try {
            multSignManager_btn.click();
            TimeUnit.SECONDS.sleep(1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }




    public AddwalletPage enterAddwalletPage() throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        try {
            normal_account_type.click();
        } catch (Exception e) {

        }
        return new AddwalletPage(driver);
    }

    public AddwalletPage enterAddwalletPage(Boolean isShieldWallet) throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        try {
            shield_account_type.click();
        } catch (Exception e) {

        }
        return new AddwalletPage(driver);
    }



    public WalletPasswordPage enterwalletPasswordPage() throws Exception {
        walletPassword_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new WalletPasswordPage(driver);
    }



    public boolean getExitst() {
        boolean eitst = true;
        return eitst;
    }



    public String getBackupKeystore(String password) {
        String keystore = "";
        try {
            Helper.swipScreen(driver);
            findElementByText("备份 Keystore").click();
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(2);
            try {
                i_know_btn.click();
            } catch (Exception e) {
                System.out.println("I know btn is disappear");
            }
            TimeUnit.SECONDS.sleep(1);
            keystore = keystore_text.getText();
            done_btn.click();
        } catch (Exception e) {
            System.out.println(e);
        }
        return keystore;
    }




    public String getAddress() {
        String address = "";
        try {
            address = address_text.getText();
        } catch (Exception e) {
            System.out.println(e);
        }
        return address;
    }


    public AssetPage changeWalletAccount(String walletName) throws Exception {
        switch (walletName){
            case "FromAccount":
                swipToChangeWallet("TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS");
                break;
            case "SignAccount":
                swipToChangeWallet("TS9XrumdDFBs5bQkVnhFTexoqwqaxUVG8v");
                break;
            case "other":
                swipToDefaultWallet();
                break;
        }
        TimeUnit.SECONDS.sleep(4);
        return new AssetPage(driver);
    }



    public void swipToChangeWallet(String accountAddress) throws Exception {
        int count = 0;
        while (!walletAddress_text.getText().equals(accountAddress)){
            System.out.println("current walletAddress is = " + walletAddress_text.getText());
            TimeUnit.SECONDS.sleep(1);
            count++;
            Helper.swipToChangeMyPaurse(driver);
            TimeUnit.SECONDS.sleep(1);
            if (count >=7){
                System.out.println("cannot find the wallet Address …… break");
                break;
            }
        }
        chainName_btn.click();
    }



    public void swipToChangeAddress(String accountAddress) throws Exception {
        int count = 0;
        while (!walletAddress_text.getText().equals(accountAddress)){
            System.out.println("current walletAddress is = " + walletAddress_text.getText());
            count++;
            Helper.swipToChangeMyPaurse(driver);
            TimeUnit.SECONDS.sleep(1);
            if (count >=7){
                System.out.println("cannot find the wallet Address …… break");
                break;
            }
        }
    }



    public void swipToDefaultWallet() throws Exception {
        int count = 0;
        while (walletAddress_text.getText().equals("TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS") || walletAddress_text.getText().equals("TS9XrumdDFBs5bQkVnhFTexoqwqaxUVG8v")){
            System.out.println("current walletAddress is = " + walletAddress_text.getText());
            count++;
            Helper.swipToChangeMyPaurse(driver);
            TimeUnit.SECONDS.sleep(1);
            if (count >=7){
                System.out.println("cannot find the [default] wallet Address …… break");
                break;
            }
        }
        chainName_btn.click();
    }


    public MultiSignTransactionPage enterMultiSignTransactionPage() throws Exception {
        mulSignTranfer_btn.click();
        TimeUnit.SECONDS.sleep(2);
        return new MultiSignTransactionPage(driver);
    }



    public MinePage enterMinePage() throws Exception {
        back_btn.click();
        return new MinePage(driver);
    }





}



