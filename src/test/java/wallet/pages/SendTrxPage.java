package wallet.pages;

import common.utils.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * 转帐页
 */

public class SendTrxPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SendTrxPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement transferTtile_btn;

    @FindBy(id = "com.tronlink.wallet:id/et_address")
    public WebElement receiveAddress_text;


    @FindBy(id = "com.tronlink.wallet:id/et_count")
    public WebElement tranferCount_text;

    @FindBy(id = "com.tronlink.wallet:id/send")
    public WebElement send_btn;


    @FindBy(id = "com.tronlink.wallet:id/bt_go")
    public WebElement transferNow_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement InputPasswordConfim_btn;


    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement confirm_btn;


    public void swip(){
        Helper.swipScreen(driver);
    }


    public SendTrxSuccessPage enterSendTrxSuccessPage(){
        confirm_btn.click();
        return new SendTrxSuccessPage(driver);
    }



}
