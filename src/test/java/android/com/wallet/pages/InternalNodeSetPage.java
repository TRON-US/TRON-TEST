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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement title_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_add_node")
    public WebElement addNote_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/li_bottom_button")
    public WebElement addNoteBottom;

    @FindBy(id = "com.tronlinkpro.wallet:id/node_ip")
    public WebElement firstIP;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_node_port")
    public WebElement firstPort;

    @FindBy(id = "com.tronlinkpro.wallet:id/node_ip")
    public List<WebElement> ip_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/node_ip")
    public WebElement firstIpByID;


    @FindBy(className = "android.view.ViewGroup")
    public List<WebElement> content_list;

    //com.tronlinkpro.wallet:id/custom_latency
    @FindBy(id = "com.tronlinkpro.wallet:id/custom_latency")
    public List<WebElement> custom_latency_list;

    //com.tronlinkpro.wallet:id/tv_full_node
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_full_node")
    public WebElement fulllNode_text;

    //com.tronlinkpro.wallet:id/iv_node_edit
    @FindBy(id = "com.tronlinkpro.wallet:id/iv_node_edit")
    public WebElement edit_custom_icon;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_content")
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
