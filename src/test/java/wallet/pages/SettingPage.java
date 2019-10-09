package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class SettingPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SettingPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


}
