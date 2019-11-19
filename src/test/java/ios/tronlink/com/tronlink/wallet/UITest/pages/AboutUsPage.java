package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.concurrent.TimeUnit;
/**
 * 关于我们页面
 */
public class AboutUsPage extends AbstractPage {

    public IOSDriver<?> driver;

    public AboutUsPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "关于我们")
    public WebElement title;





}
