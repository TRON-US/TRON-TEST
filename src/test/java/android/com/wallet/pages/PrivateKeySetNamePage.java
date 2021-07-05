package android.com.wallet.pages;

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


    @FindBy(id = "wallet.tronlink.global:id/et_name")
    public WebElement name_input;



    @FindBy(id = "wallet.tronlink.global:id/creat")
    public WebElement next_btn;



    @FindBy(id = "wallet.tronlink.global:id/tv_error")
    public WebElement error_hits;






    public PrivateKeySetPwdPage enterPrivateKeySetPwdPage(String name) throws Exception {
        name_input.sendKeys(name);
        next_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new PrivateKeySetPwdPage(driver);
    }

    public void setName(String name){
        name_input.sendKeys(name);
        next_btn.click();
    }



}
