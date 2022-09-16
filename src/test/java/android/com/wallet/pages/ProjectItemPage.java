package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class ProjectItemPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public ProjectItemPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.global:id/assets_name")
    public WebElement assets_name;

    @FindBy(id = "com.tronlink.global:id/assets_tag")
    public WebElement assets_tag;

    @FindBy(id = "com.tronlink.global:id/token_id")
    public WebElement token_id;

    @FindBy(id = "com.tronlink.global:id/token_publisher")
    public WebElement token_publisher;

    @FindBy(id = "com.tronlink.global:id/start_time")
    public WebElement start_time;

    @FindBy(id = "com.tronlink.global:id/end_time")
    public WebElement end_time;

    @FindBy(id = "com.tronlink.global:id/total_circulation")
    public WebElement total_circulation;

    @FindBy(id = "com.tronlink.global:id/token_url")
    public WebElement token_url;
}
