package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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
    public List<WebElement> address_input;



    @FindBy(id = "com.tronlink.wallet:id/et_weight")
    public WebElement weight_input;


    @FindBy(id = "com.tronlink.wallet:id/tv_confirm")
    public WebElement confirm_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;



    @FindBy(id = "com.tronlink.wallet:id/tv_permissionname_tip")
    public WebElement tip_hits;



    @FindBy(id = "com.tronlink.wallet:id/tv_operations_tip")
    public WebElement operations_tip;



    @FindBy(id = "com.tronlink.wallet:id/tv_threshold_tip")
    public WebElement threshold_tip;



    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_addkey_tip")
    public WebElement addkey_tip;



    @FindBy(id = "com.tronlink.wallet:id/rl_add_key_button")
    public WebElement addKey_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_title")
    public WebElement inputPassword_title;



    public void inputInfo(String signName) throws Exception{
        permissionName_input.sendKeys(signName);
        weights_btn.click();
        threshold_input.sendKeys("1");
        address_input.get(0).sendKeys("THph9K2M2nLvkianrMGswRhz5hjSA9fuH7");
        weight_input.sendKeys("1");
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
    }


    //add permission
    public MultiSignManagerPage addPermission(String signName){
        try {
            inputInfo(signName);
            password_input.sendKeys("Test0001");
            send_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MultiSignManagerPage(driver);
    }




    public void inputInfoWithoutPermission(String signName){
        permissionName_input.sendKeys(signName);
        threshold_input.sendKeys("1");
        address_input.get(0).sendKeys("THph9K2M2nLvkianrMGswRhz5hjSA9fuH7");
        weight_input.sendKeys("1");
        confirm_btn.click();
    }



    public void inputSameAddress() throws Exception {
        address_input.get(0).sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        addKey_btn.click();
        TimeUnit.SECONDS.sleep(1);
        address_input.get(1).sendKeys("TKG4UtDejJfAQx3FsyAUs86cpcRzYcijth");
        TimeUnit.SECONDS.sleep(1);
    }



}
