package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;


public class AddPermissionPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public AddPermissionPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlink.wallet:id/et_permission_name")
    public WebElement permissionName_input;



    @FindBy(id = "com.tronlink.wallet:id/tv_operation")
    public WebElement weights_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_threshold")
    public WebElement threshold_input;



    @FindBy(id = "com.tronlink.wallet:id/et_key_address")
    public WebElement address_input;



    @FindBy(id = "com.tronlink.wallet:id/et_weight")
    public WebElement weight_input;


    @FindBy(id = "com.tronlink.wallet:id/tv_confirm")
    public WebElement confirm_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;

    //add permission
    public MultiSignManagerPage addPermission(String signName){
        try {
            permissionName_input.sendKeys(signName);
            weights_btn.click();
            threshold_input.sendKeys("1");
            address_input.sendKeys("THph9K2M2nLvkianrMGswRhz5hjSA9fuH7");
            weight_input.sendKeys("1");
            confirm_btn.click();
            TimeUnit.SECONDS.sleep(1);
            password_input.sendKeys("Test0001");
            send_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }







}
