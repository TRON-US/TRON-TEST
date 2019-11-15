package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PrivateKeySetPwdAgainPage extends AbstractPage {
    public IOSDriver<?> driver;

    public PrivateKeySetPwdAgainPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/et_password")
    public WebElement pwd_input;



    @FindBy(name = "com.tronlink.wallet:id/creat")
    public WebElement create_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_error")
    public WebElement error_hits;



    public AssetPage enterAssetPage(String pwdAgain) throws Exception {
        pwd_input.sendKeys(pwdAgain);
        create_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }



}
