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



    @FindBy(id = "wallet.tronlink.global:id/address")
    public WebElement ownerAddress_btn;


    @FindBy(id = "wallet.tronlink.global:id/copy")
    public WebElement copy_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_top")
    public WebElement pagetitle;

    @FindBy(id = "wallet.tronlink.global:id/title")
    public WebElement wallettitle;


}
