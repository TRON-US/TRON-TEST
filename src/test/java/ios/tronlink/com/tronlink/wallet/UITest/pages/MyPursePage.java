package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyPursePage extends AssetPage {



    public IOSDriver<?> driver;


    public MyPursePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "钱包密码")
    public WebElement walletPassword_btn;

    @FindBy(name = "钱包管理")
    public WebElement title;

    @FindBy(name = "titleLabel")
    public List<WebElement> titleLabels;

    @FindBy(name = "备份keystore")
    public WebElement backkeystore_btn;
    @FindBy(name = "删除钱包")
    public WebElement deletewallet_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_address")
    public WebElement address_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_name")
    public WebElement walletname_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_create")
    public WebElement newCreate_btn;

    @FindBy(name = "com.tronlink.wallet:id/rl_keystore2")
    public WebElement backupKeystore_btn;

    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement password_et;

//    @FindBy(name = "type =='XCUIElementTypeButton' AND name == '确定'")
//    public WebElement confirm_btn;
//    @FindBy(partialLinkText = "type =='XCUIElementTypeButton' AND name == '取消'")
//    public WebElement cancal_btn;

    @FindBy(className = "XCUIElementTypeTextView")
    public WebElement keystore_text;

    @FindBy(name = "备份完成")
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
            backkeystore_btn.click();
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            WebElement  confirm_btn;
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
            keystore = keystore_text.getText();
            done_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return keystore;
    }

//XCUIElementTypeButton
public String getBackupKeystoreInClipboard(String password){
    String keystore = "";
    try {
        Helper.swipScreen(driver);
        backkeystore_btn.click();
        TimeUnit.SECONDS.sleep(1);
        password_et.sendKeys(password);
        WebElement  confirm_btn;
        confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        WebElement clipboard = driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '复制'");
        clipboard.click();
        keystore =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '已复制'").getText();
//        keystore = driver.getClipboard(ClipboardContentType.PLAINTEXT); // get plaintext  //由于方法不可拿到结果，曲线得到已复制文字
        done_btn.click();
    }catch (Exception e){
        System.out.println(e);
    }
    return keystore;
}

    //XCUIElementTypeButton
    public boolean deletableCancel(String password){
        WebElement  confirm_btn;
        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(1);
            deletewallet_btn.click();
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '取消'");
            confirm_btn.click();
            return  !confirm_btn.isDisplayed();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deletWallet(String password){
        WebElement  confirm_btn;
        try {
            Helper.swipScreen(driver);
            TimeUnit.SECONDS.sleep(1);
            deletewallet_btn.click();
            TimeUnit.SECONDS.sleep(1);
            password_et.sendKeys(password);
            confirm_btn =  driver.findElementByIosNsPredicate("type =='XCUIElementTypeButton' AND name == '确定'");
            confirm_btn.click();
            return  !confirm_btn.isDisplayed();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
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
