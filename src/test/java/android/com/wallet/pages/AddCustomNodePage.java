package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomNodePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public  AddCustomNodePage(AndroidDriver<?> driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "wallet.tronlink.harmony:id/et_node_ip")
    public WebElement nodeIp_input;

    @FindBy(id = "wallet.tronlink.harmony:id/et_node_port")
    public WebElement nodePort_input;

    @FindBy(id = "wallet.tronlink.harmony:id/bt_next")
    public WebElement save_btn;


    @FindBy(id = "wallet.tronlink.harmony:id/bt_delete")
    public WebElement delete_btn;



    public InternalNodeSetPage saveNode() throws Exception{
        save_btn.click();
        TimeUnit.SECONDS.sleep(10);
        return new InternalNodeSetPage(driver);
    }


    public InternalNodeSetPage deleteNode() throws Exception{
        delete_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }








}
