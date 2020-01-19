package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomNodePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public  AddCustomNodePage(AndroidDriver<?> driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/et_node_ip")
    public WebElement nodeIp_input;

    @FindBy(id = "com.tronlink.wallet:id/et_node_port")
    public WebElement nodePort_input;

    @FindBy(id = "com.tronlink.wallet:id/bt_next")
    public WebElement save_btn;







}
