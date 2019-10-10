package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class SendTrxSuccessPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SendTrxSuccessPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_count")
    public WebElement trxCount;




}
