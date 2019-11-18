package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptPage extends AbstractPage {
    public IOSDriver<?> driver;


    public ReceiptPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(name = "com.tronlink.wallet:id/address")
    public WebElement ownerAddress_btn;


    @FindBy(name = "com.tronlink.wallet:id/copy")
    public WebElement copy_btn;




}
