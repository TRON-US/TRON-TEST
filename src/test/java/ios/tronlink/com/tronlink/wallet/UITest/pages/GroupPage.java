package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupPage extends AbstractPage {


    public IOSDriver<?> driver;

    public GroupPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "加入社群")
    public WebElement groupInto_title;

}
