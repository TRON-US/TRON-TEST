package android.com.wallet.pages;

import android.com.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class ModifyPermissionPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public ModifyPermissionPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "wallet.tronlink.harmony:id/tv_fee")
    public WebElement fee_text;

    @FindBy(id = "wallet.tronlink.harmony:id/et_permission_name")
    public WebElement modifyPermissionName_input;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_right")
    public WebElement feetext;

    @FindBy(id = "wallet.tronlink.harmony:id/et_key_address")
    public WebElement modifyAddress_input;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_fee_amount_bw")
    public WebElement bandwidth;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_confirm")
    public WebElement confirm_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/et_new_password")
    public WebElement password_input;



    @FindBy(id = "wallet.tronlink.harmony:id/bt_send")
    public WebElement pay_btn;


    @FindBy(id = "wallet.tronlink.harmony:id/tv_common_title")
    public WebElement title_text;


    //modify sign info
    public MultiSignManagerPage modify(String address) throws Exception {
        modifyPermissionName_input.clear();
        modifyPermissionName_input.sendKeys("active_est");
        System.out.println("modify the mulSign name is : active_est");
        modifyAddress_input.clear();
        modifyAddress_input.sendKeys(address);
        Helper.swipScreen(driver);
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        pay_btn.click();
        TimeUnit.SECONDS.sleep(5);
        return new MultiSignManagerPage(driver);
    }



    public void nextBtnClick(){
        findElementByText("下一步").click();
    }




}
