package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class PrivateKeySetPwdPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public PrivateKeySetPwdPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.harmony:id/et_password")
    public WebElement pwd_input;



    @FindBy(id = "wallet.tronlink.harmony:id/creat")
    public WebElement next_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_common_title")
    public WebElement pwd_title;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_error")
    public WebElement error_hits;


    @FindBy(id = "wallet.tronlink.harmony:id/iv_common_left")
    public WebElement back_btn;



    public PrivateKeySetPwdAgainPage enterPrivateKeySetPwdAgainPage(String pwd) throws Exception {
        pwd_input.sendKeys(pwd);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdAgainPage(driver);
    }


}
