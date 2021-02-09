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



    @FindBy(id = "com.tronlinkpro.wallet:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_dappchain_node_name")
    public  WebElement dappChainName;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_dappchain_select")
    public  WebElement dappChainBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_mainnet_node_name")
    public  WebElement mainChainName;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_mainnet_select")
    public  WebElement mainChainBtn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_shasta_node_name")
    public WebElement shastaName;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_shasta_select")
    public WebElement shastaBtn;


    //3.9.2开始 切换就会重启
    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
        waiteTime();
        dappChainBtn.click();
        try {
            confirmBan.click();
        }catch (Exception e){
            System.out.println("already in mainchain");
        }
        return new SettingPage(driver);
    }


    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
        mainChainBtn.click();
        try {
            confirmBan.click();
        }catch (Exception e){
            System.out.println("already in dappchain");
        }
        return new SettingPage(driver);
    }

    public InternalNodeSetPage enterInternalMainChainPage() throws Exception{
        mainChainBtn.click();
        try {
            confirmBan.click();
        }catch (Exception e){
            System.out.println("already in mainchain");
        }
        mainChainName.click();
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterInternalDAppChainPage() throws Exception{
        dappChainBtn.click();
        try {
            confirmBan.click();
        }catch (Exception e){
            System.out.println("already in dappchain");
        }
        dappChainName.click();
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterMainChainNodeSettingPage() throws Exception{
        mainChainName.click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterDappChainNodeSettingPage() throws Exception{
        dappChainName.click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }

    public InternalNodeSetPage enterShastaNodeSettingPage() throws Exception{
        shastaName.click();
        TimeUnit.SECONDS.sleep(3);
        return new InternalNodeSetPage(driver);
    }


}
