package android.com.wallet.pages;

import android.com.utils.Helper;

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

    @FindBy(id = "wallet.tronlink.harmony:id/tv_common_title")
    public WebElement title_text;

    @FindBy(id = "wallet.tronlink.harmony:id/btn_add_node")
    public WebElement addNote_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/li_bottom_button")
    public WebElement addNoteBottom;

    @FindBy(id = "wallet.tronlink.harmony:id/node_ip")
    public WebElement firstIP;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_node_port")
    public WebElement firstPort;

    @FindBy(id = "wallet.tronlink.harmony:id/node_ip")
    public List<WebElement> ip_list;

    @FindBy(id = "wallet.tronlink.harmony:id/node_ip")
    public WebElement firstIpByID;


    @FindBy(className = "android.view.ViewGroup")
    public List<WebElement> content_list;

    //wallet.tronlink.harmony:id/custom_latency
    @FindBy(id = "wallet.tronlink.harmony:id/custom_latency")
    public List<WebElement> custom_latency_list;

    //wallet.tronlink.harmony:id/tv_full_node
    @FindBy(id = "wallet.tronlink.harmony:id/tv_full_node")
    public WebElement fulllNode_text;

    //wallet.tronlink.harmony:id/iv_node_edit
    @FindBy(id = "wallet.tronlink.harmony:id/iv_node_edit")
    public WebElement edit_custom_icon;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_content")
    public WebElement tipContent;




    public AddCustomNodePage enterAddCustomNodePage() throws Exception{
//        clickOffsetElement(addNote_btn);
        addNote_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new AddCustomNodePage(driver);
    }


    public AddCustomNodePage enterEditCustomNodePage() throws Exception{
        edit_custom_icon.click();
        TimeUnit.SECONDS.sleep(3);
        return new AddCustomNodePage(driver);
    }


}
