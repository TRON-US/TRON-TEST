package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public AddressBookPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement addressBook_title;

    @FindBy(id = "com.tronlink.wallet:id/iv_qr")
    public WebElement addAddressBook_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_bg_right")
    public WebElement save_btn;

    @FindBy(id = "com.tronlink.wallet:id/et_address_name")
    public WebElement addName_input;

    @FindBy(id = "com.tronlink.wallet:id/et_address")
    public WebElement addAddress_input;

    @FindBy(id = "com.tronlink.wallet:id/et_description")
    public WebElement addNote_input;

    @FindBy(id = "com.tronlink.wallet:id/tv_address_name")
    public WebElement name_display;

    @FindBy(id = "com.tronlink.wallet:id/tv_address")
    public WebElement address_display;

    @FindBy(id = "com.tronlink.wallet:id/tv_description")
    public WebElement note_display;

    @FindBy(id = "com.tronlink.wallet:id/rl_address_copy")
    public WebElement copy_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_address_edit")
    public WebElement edit_btn;

    @FindBy(id = "com.tronlink.wallet:id/iv_delete")
    public WebElement deleteBook_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_delete")
    public WebElement deleteAddress_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_confirm")
    public WebElement deleteConfirm_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_cancle")
    public WebElement deleteCancle_btn;

    @FindBy(id = "com.tronlink.wallet:id/rl_scan")
    public WebElement scan_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_name_error")
    public WebElement nameError_info;

    @FindBy(id = "com.tronlink.wallet:id/tv_address_error")
    public WebElement addressError_info;

}
