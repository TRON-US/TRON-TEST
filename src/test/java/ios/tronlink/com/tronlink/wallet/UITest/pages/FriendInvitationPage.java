package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FriendInvitationPage extends AbstractPage {

    public IOSDriver<?> driver;

    public FriendInvitationPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "好友邀请")
    public WebElement friendInvitation_title;


}
