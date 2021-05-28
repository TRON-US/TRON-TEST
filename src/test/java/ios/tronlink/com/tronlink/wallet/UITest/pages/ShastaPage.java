package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.ios.IOSDriver;


public class ShastaPage extends AbstractPage {

    public IOSDriver<?> driver;

    public ShastaPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "公告")
    public WebElement announcementPage_title;




}
