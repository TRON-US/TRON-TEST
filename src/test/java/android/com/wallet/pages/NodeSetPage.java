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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_dappchain_node_name")
    public  WebElement dappChainName;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_dappchain_select")
    public  WebElement dappChainBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_mainnet_node_name")
    public  WebElement mainChainName;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_mainnet_select")
    public  WebElement mainChainBtn;


    //3.9.2开始 切换就会重启
    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
        waiteTime();
        dappChainBtn.click();
        confirmBan.click();
        return new SettingPage(driver);
    }


    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
        mainChainBtn.click();
        confirmBan.click();
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
