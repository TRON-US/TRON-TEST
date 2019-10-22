package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class PrivateKeySetNamePage extends AbstractPage {


    public AndroidDriver<?> driver;


    public PrivateKeySetNamePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/et_name")
    public WebElement name_input;



    @FindBy(id = "com.tronlink.wallet:id/creat")
    public WebElement next_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_error")
    public WebElement error_hits;






    public PrivateKeySetPwdPage enterPrivateKeySetPwdPage(String name) throws Exception {
        name_input.sendKeys(name);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdPage(driver);
    }



}
