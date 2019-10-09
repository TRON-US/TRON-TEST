package wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 用户隐私协议页
 */

public class UserAgreementPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public UserAgreementPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement UserAgreementTitle;

}
