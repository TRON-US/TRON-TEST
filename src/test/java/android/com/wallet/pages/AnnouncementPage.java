package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class AnnouncementPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public AnnouncementPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement announcementPage_title;




}
