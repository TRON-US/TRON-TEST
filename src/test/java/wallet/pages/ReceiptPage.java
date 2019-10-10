package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class ReceiptPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public ReceiptPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlink.wallet:id/address")
    public WebElement ownerAddress_btn;


    @FindBy(id = "com.tronlink.wallet:id/copy")
    public WebElement copy_btn;




}
