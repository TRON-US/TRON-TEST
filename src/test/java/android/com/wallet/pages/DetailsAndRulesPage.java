package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class DetailsAndRulesPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DetailsAndRulesPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.harmony:id/tv_common_title")
    public WebElement detailsAndRules_title;





}
