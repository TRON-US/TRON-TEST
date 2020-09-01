package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NodeSetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public NodeSetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_select")
    public List<WebElement> node_selected;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public List<WebElement> chain_list;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/back3")
    public List<WebElement> forward_btn;



    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
        node_selected.get(1).click();
        back_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new SettingPage(driver);
    }


    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
        node_selected.get(0).click();
        TimeUnit.SECONDS.sleep(3);
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new SettingPage(driver);
    }

    public InternalNodeSetPage enterInternalMainChainPage() throws Exception{
        node_selected.get(0).click();
        TimeUnit.SECONDS.sleep(3);
        forward_btn.get(0).click();
        TimeUnit.SECONDS.sleep(1);
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterInternalDAppChainPage() throws Exception{
        node_selected.get(1).click();
        TimeUnit.SECONDS.sleep(3);
        forward_btn.get(1).click();
        TimeUnit.SECONDS.sleep(1);
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterMainChainNodeSettingPage() throws Exception{
        chain_list.get(0).click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterDappChainNodeSettingPage() throws Exception{
        chain_list.get(1).click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }





}
