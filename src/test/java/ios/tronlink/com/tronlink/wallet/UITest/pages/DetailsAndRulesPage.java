package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsAndRulesPage extends AbstractPage {

    public IOSDriver<?> driver;

    public DetailsAndRulesPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement detailsAndRules_title;



    @FindBy(name = "我知道了")
    public WebElement iKnowButton;
}
