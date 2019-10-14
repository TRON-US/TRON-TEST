package wallet.pages;

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


    @FindBy(id = "com.tronlink.wallet:id/testnode")
    public WebElement developerOptions_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_testnode")
    public WebElement testnode_text;


    @FindBy(id = "com.tronlink.wallet:id/tv_ok")
    public WebElement connect_btn;


    @FindBy(id = "com.tronlink.wallet:id/dapp")
    public WebElement dapp_btn;



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










}
