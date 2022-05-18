package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class ReceiptPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public ReceiptPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


@FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
public WebElement tv_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/address")
    public WebElement ownerAddress_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/copy")
    public WebElement copy_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_scan_qr_and_pay")
    public WebElement pagetitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_wallet_name")
    public WebElement wallettitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_receive_watchonly")
    public WebElement tv_receive_watchonly;

}
