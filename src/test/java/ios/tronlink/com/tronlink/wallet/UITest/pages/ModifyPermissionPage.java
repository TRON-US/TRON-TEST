package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ModifyPermissionPage extends AbstractPage {


    public IOSDriver<?> driver;


    public ModifyPermissionPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/et_permission_name")
    public WebElement modifyPermissionName_input;



    @FindBy(id = "com.tronlink.wallet:id/et_key_address")
    public WebElement modifyAddress_input;



    @FindBy(id = "com.tronlink.wallet:id/tv_confirm")
    public WebElement confirm_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;



    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement pay_btn;




    //modify sign info
    public MultiSignManagerPage modify(String address) throws Exception {
        modifyPermissionName_input.clear();
        modifyPermissionName_input.sendKeys("active");
        modifyAddress_input.clear();
        modifyAddress_input.sendKeys(address);
        confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        password_input.sendKeys("Test0001");
        pay_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new MultiSignManagerPage(driver);
    }



}
