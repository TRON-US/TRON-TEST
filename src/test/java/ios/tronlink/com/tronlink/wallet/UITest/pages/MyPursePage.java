package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MyPursePage extends AssetPage {



    public IOSDriver<?> driver;


    public MyPursePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "钱包密码")
    public WebElement walletPassword_btn;
    

    @FindBy(name = "com.tronlink.wallet:id/tv_address")
    public WebElement address_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_name")
    public WebElement walletname_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_create")
    public WebElement newCreate_btn;

    @FindBy(name = "com.tronlink.wallet:id/rl_keystore2")
    public WebElement backupKeystore_btn;

    @FindBy(name = "com.tronlink.wallet:id/et_password")
    public WebElement password_et;

    @FindBy(name = "com.tronlink.wallet:id/tv_ok")
    public WebElement confirm_btn;

    @FindBy(name = "com.tronlink.wallet:id/tv_keystore")
    public WebElement keystore_text;

    @FindBy(name = "com.tronlink.wallet:id/backup")
    public WebElement done_btn;

    @FindBy(name = "com.tronlink.wallet:id/rl_sign_manager")
    public WebElement multSignManager_btn;

    public MultiSignManagerPage enterMultiSignManagerPage() {
        try {
            multSignManager_btn.click();
            TimeUnit.SECONDS.sleep(1);

        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }


    public AddwalletPage enterAddwalletPage() throws Exception {
        newCreate_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }
    public WalletPasswordPage enterwalletPasswordPage() throws Exception {
        walletPassword_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new WalletPasswordPage(driver);
    }



    public String getBackupKeystore(String password){
        String keystore = "";
        try {
            Helper.swipScreen(driver);
            backupKeystore_btn.click();
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
            keystore = keystore_text.getText();
            done_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return keystore;
    }

    public String getAddress(){
        String address = "";
        try {
            address = address_text.getText();
        }catch (Exception e){
            System.out.println(e);
        }
        return address;
    }



}
