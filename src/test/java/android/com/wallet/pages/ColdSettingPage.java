package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ColdSettingPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public ColdSettingPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/languane")
    public WebElement languane_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/selected")
    public List<WebElement> selected_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public List<WebElement> language_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public List<WebElement> currency_list;


    @FindBy(id = "com.tronlinkpro.wallet:id/testnode")
    public WebElement developerOptions_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_testnode")
    public WebElement testnode_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public WebElement connect_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/dapp")
    public WebElement dapp_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/node")
    public WebElement node_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/switch_version")
    public WebElement version_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_node_name")
    public WebElement node_name;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/money")
    public WebElement currency_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public List<WebElement> chain_list;

    //
    @FindBy(id = "com.tronlinkpro.wallet:id/convert")
    public WebElement changeTool_btn;





//    public void switchLanguage(String language){
//        try {
//            switch (language){
//                case "chinese":
//                    selected_btn.get(1).click();
//            }
//            languane_btn.click();
//            TimeUnit.SECONDS.sleep(1);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }


    //turn Developer options
    public void trunDeveloperOptions(){
        try {
            developerOptions_btn.click();
            TimeUnit.SECONDS.sleep(2);
            connect_btn.click();
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public DAPP_BrowerPage enterDAPP_BrowerPage(){
        dapp_btn.click();
        return new DAPP_BrowerPage(driver);
    }




    public NodeSetPage enterNodeSetPage() throws Exception {
        node_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new NodeSetPage(driver);
    }

    public NodeSetPage enterVersionSetPage() throws Exception {
        version_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new NodeSetPage(driver);
    }



    public MinePage enterMinePage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new MinePage(driver);
    }





}
