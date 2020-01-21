package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SettingPage extends AbstractPage {

    public IOSDriver<?> driver;

    public SettingPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "设置")
    public WebElement title;

    @FindBy(name = "语言")
    public WebElement languane_btn;


    @FindBy(name = "com.tronlink.wallet:id/selected")
    public List<WebElement> selected_btn;


    @FindBy(name = "开发者选项")
    public WebElement developerOptions_btn;


    @FindBy(name = "节点设置")
    public WebElement testnode_text;


    @FindBy(name = "连接")
    public WebElement connect_btn;

    @FindBy(name = "已选择 Shasta 网络")
    public WebElement connected_title;

    @FindBy(name = "未选择")
    public WebElement disconnected_title;

    @FindBy(name = "断开")
    public WebElement disconnec_btn;

    @FindBy(name = "取消")
    public WebElement connect_cancel_btn;

    @FindBy(name = "Dapp测试工具")
    public WebElement dapp_btn;



    @FindBy(name = "节点设置")
    public WebElement node_btn;

    @FindBy(name = "com.tronlink.wallet:id/switch_version")
    public WebElement version_btn;


    @FindBy(name = "com.tronlink.wallet:id/tv_node_name")
    public WebElement node_name;


    @FindBy(name = "black path")
    public WebElement back_btn;

    @FindBy(name = "转换工具")
    public WebElement mnemTools_btn;


    @FindBy(name =  "英文")
    public WebElement english_btn;

    @FindBy(name =  "简体中文")
    public WebElement chinese_btn;

    @FindBy(name =  "取消")
    public WebElement Cancal_btn;
    @FindBy(name =  "Language")
    public WebElement languaneEn_btn;

    @FindBy(name =  "Simplified Chinese")
    public WebElement languaneCn_btn;

    @FindBy(name =  "货币单位")
    public WebElement currencyUnit_btn;

    @FindBy(name =  "USD")
    public WebElement USDUnit_btn;

    @FindBy(name =  "CNY")
    public WebElement CNYUnit_btn;

    @FindBy(name =  "MainChain")
    public WebElement MainChain_btn;

    @FindBy(id =  "切换版本")
    public WebElement switchVersionBtn;

    @FindBy(name = "正式版本")
    public  WebElement releaseBtn;

    @FindBy(name = "测试版本")
    public  WebElement devBtn;

    @FindBy(className = "XCUIElementTypeStaticText")
    public List<WebElement> textArray;

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

    //turn Developer options
    public void trunOffDeveloperOptions(){
        try {
            developerOptions_btn.click();
            TimeUnit.SECONDS.sleep(2);
            disconnec_btn.click();
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
        waiteTime();
        node_btn.click();
        waiteTime();
        return new NodeSetPage(driver);
    }

    public NodeSetPage enterVersionSetPage() throws Exception {
        version_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new NodeSetPage(driver);
    }

    public MnemonicToolsPage enternemTools_btnPage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        mnemTools_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new MnemonicToolsPage(driver);
    }

    public MinePage enterMinePage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new MinePage(driver);
    }



    public void switchToRelease() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        switchVersionBtn.click();
        TimeUnit.SECONDS.sleep(1);
        releaseBtn.click();
        TimeUnit.SECONDS.sleep(1);

    }

    public void switchToDev() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        switchVersionBtn.click();
        TimeUnit.SECONDS.sleep(1);
        devBtn.click();
        TimeUnit.SECONDS.sleep(1);

    }

}
