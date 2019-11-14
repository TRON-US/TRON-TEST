package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import java.util.concurrent.TimeUnit;

/**
 * UserAgreementPage
 */

public class UserAgreementPage extends AbstractPage {

    public IOSDriver<?> driver;


    public UserAgreementPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/tv_common_title")
    public WebElement UserAgreementTitle;



    @FindBy(name = "com.tronlink.wallet:id/bt_accept")
    public WebElement accept_btn;



    public AddwalletPage enterAddwalletPage() throws Exception {
        new Helper().swipUntilElementEnable("id/bt_accept",driver);
        accept_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddwalletPage(driver);
    }






}
