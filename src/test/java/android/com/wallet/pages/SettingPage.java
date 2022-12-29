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


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_language")
    public WebElement languane_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/switch_button")
    public WebElement switch_button;

    @FindBy(id = "com.tronlinkpro.wallet:id/selected")
    public List<WebElement> selected_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public List<WebElement> language_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public List<WebElement> currency_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/network_setting_title")
    public WebElement network_setting_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_network_name")
    public WebElement tv_network_name;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_node_speed")
    public WebElement tv_node_speed;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_node_root")
    public WebElement ll_node_root;

    @FindBy(id = "com.tronlinkpro.wallet:id/server")
    public WebElement server_name_speed;

    @FindBy(id = "com.tronlinkpro.wallet:id/testnode")
    public WebElement developerOptions_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_node_name")
    public WebElement tv_node_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_testnode")
    public WebElement testnode_text;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public WebElement connect_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/server")
    public WebElement server_btn;

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

    @FindBy(id = "com.tronlinkpro.wallet:id/node_ip")
    public List<WebElement> node_ips;

    //com.tronlinkpro.wallet:id/select_online
    @FindBy(id = "com.tronlinkpro.wallet:id/select_online")
    public WebElement online_version_icon;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_add_node")
    public WebElement btn_add_node;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_shasta_select")
    public WebElement li_node_name_shasta;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_mainnet_select")
    public WebElement li_node_name;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_dappchain_select")
    public WebElement li_node_name_dappchain;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_add_node")
    public WebElement addNote_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_node_edit")
    public WebElement iv_node_edit;

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

    public AddCustomNodePage enterAddCustomNodePage() throws Exception{
        addNote_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AddCustomNodePage(driver);
    }

    public AddCustomNodePage enterEditCustomNodePage() throws Exception{
        iv_node_edit.click();
        TimeUnit.SECONDS.sleep(2);
        return new AddCustomNodePage(driver);
    }

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
    //        MainChain, DAppChain, Shasta;
    public void changNetWorkTo(String type) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        tv_network_name.click();
        switch (type){
            case "Shasta":
                System.out.println("Shasta");
                li_node_name_shasta.click();
                btn_confirm.click();
                break;

            case "MainChain":
                System.out.println("MainChain");
                li_node_name.click();
                break;
            case "DAppChain":
                System.out.println("DAppChain");
                li_node_name_dappchain.click();
                btn_confirm.click();
                break;
            default:
                System.out.println("default");
                li_node_name.click();
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
