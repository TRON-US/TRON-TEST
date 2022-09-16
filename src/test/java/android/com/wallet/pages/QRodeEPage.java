package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QRodeEPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public QRodeEPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.global:id/iv_qr3")
    public WebElement QRcode_text;







}
