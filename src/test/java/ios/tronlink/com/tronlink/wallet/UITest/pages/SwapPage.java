package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.ios.IOSDriver;

public class SwapPage extends AbstractPage {
    public IOSDriver<?> driver;


    public SwapPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "闪兑")
    public WebElement title;
}
