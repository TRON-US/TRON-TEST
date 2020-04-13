package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class InternalNodeSetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public  InternalNodeSetPage(AndroidDriver<?> driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement title_text;

    @FindBy(id = "com.tronlink.wallet:id/tv_bg_right")
    public WebElement tv_bg_right_btn;


    @FindBy(id = "com.tronlink.wallet:id/node_ip")
    public List<WebElement> ip_list;

    @FindBy(id = "com.tronlink.wallet:id/tv_node_port")
    public List<WebElement> port_list;

    //com.tronlink.wallet:id/custom_latency
    @FindBy(id = "com.tronlink.wallet:id/custom_latency")
    public List<WebElement> custom_latency_list;

    //com.tronlink.wallet:id/tv_full_node
    @FindBy(id = "com.tronlink.wallet:id/tv_full_node")
    public WebElement fulllNode_text;

    //com.tronlink.wallet:id/iv_node_edit
    @FindBy(id = "com.tronlink.wallet:id/iv_node_edit")
    public WebElement edit_custom_icon;





    public AddCustomNodePage enterAddCustomNodePage() throws Exception{
        tv_bg_right_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new AddCustomNodePage(driver);
    }


    public AddCustomNodePage enterEditCustomNodePage() throws Exception{
        edit_custom_icon.click();
        TimeUnit.SECONDS.sleep(3);
        return new AddCustomNodePage(driver);
    }


}
