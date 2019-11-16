package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
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



    public WalletPasswordPage enterwalletPasswordPage() {
        walletPassword_btn.click();
        return new WalletPasswordPage(driver);
    }






}
