package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class PrivateKeySetPwdAgainPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public PrivateKeySetPwdAgainPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/et_password")
    public WebElement pwd_input;



    @FindBy(id = "com.tronlink.wallet:id/creat")
    public WebElement create_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_error")
    public WebElement error_hits;



    public AssetPage enterAssetPage(String pwdAgain) throws Exception {
        pwd_input.sendKeys(pwdAgain);
        create_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }



}
