package ios.tronlink.com.tronlink.wallet.UITest.pages;


import android.com.tronlink.wallet.regression.WatchWalletTest;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptPage extends AbstractPage {
    public IOSDriver<?> driver;


    public ReceiptPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "收款")
    public WebElement title;

    @FindBy(id = "topLabel")
    public WebElement topLabel;


    @FindBy(name = "复制收款账户")
    public WebElement copy_btn;

    @FindBy(id = "addressLabel")
    public WebElement addressLabel;



}
