package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class SettingPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SettingPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/languane")
    public WebElement languane_btn;


    @FindBy(id = "com.tronlink.wallet:id/selected")
    public List<WebElement> selected_btn;

    @FindBy(id = "com.tronlink.wallet:id/title")
    public List<WebElement> language_list;

    @FindBy(id = "com.tronlink.wallet:id/title")
    public List<WebElement> currency_list;


    @FindBy(id = "com.tronlink.wallet:id/testnode")
    public WebElement developerOptions_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_testnode")
    public WebElement testnode_text;


    @FindBy(id = "com.tronlink.wallet:id/tv_ok")
    public WebElement connect_btn;


    @FindBy(id = "com.tronlink.wallet:id/server")
    public WebElement server_btn;

    @FindBy(id = "com.tronlink.wallet:id/node")
    public WebElement node_btn;

    @FindBy(id = "com.tronlink.wallet:id/switch_version")
    public WebElement version_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_node_name")
    public WebElement node_name;


    @FindBy(id = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlink.wallet:id/money")
    public WebElement currency_btn;

    @FindBy(id = "com.tronlink.wallet:id/root")
    public List<WebElement> chain_list;

    //com.tronlink.wallet:id/select_online
    @FindBy(id = "com.tronlink.wallet:id/select_online")
    public WebElement online_version_icon;


    @FindBy(id = "com.tronlink.wallet:id/tv_advanced_features")
    public WebElement advanced_features_btn;




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


    public SeverSetPage enterSeverSetPage() throws  Exception {
        server_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return  new SeverSetPage(driver);
    }

    public MinePage enterMinePage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new MinePage(driver);
    }





}
