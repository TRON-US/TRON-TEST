package wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 收款页
 */

public class VotePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public VotePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/title")
    public WebElement voteTitle_btn;

}
