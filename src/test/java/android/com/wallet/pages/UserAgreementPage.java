package android.com.wallet.pages;

import android.com.utils.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * UserAgreementPage
 */

public class UserAgreementPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public UserAgreementPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement UserAgreementTitle;



    @FindBy(id = "com.tronlinkpro.wallet:id/bt_accept")
    public WebElement accept_btn;



    public AddwalletPage enterAddwalletPage() throws Exception {
        new Helper().swipUntilElementEnable("com.tronlinkpro.wallet:id/bt_accept",driver);
        accept_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }






}
