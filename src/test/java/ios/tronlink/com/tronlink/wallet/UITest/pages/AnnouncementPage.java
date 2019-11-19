package ios.tronlink.com.tronlink.wallet.UITest.pages;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AnnouncementPage extends AbstractPage {

    public IOSDriver<?> driver;

    public AnnouncementPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "公告")
    public WebElement announcementPage_title;




}
