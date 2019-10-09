package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;
import wallet.UITest.base.Base;

/**
 * 我的钱包页面
 */
public class MinePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MinePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_about")
    public WebElement aboutUs_btn;

    @FindBy(id = "com.tronlink.wallet:id/setting")
    public WebElement setting_btn;


    //enter about us Page
    public AboutUsPage enterAboutUsPage(){
        try {
            aboutUs_btn.click();
        }catch (Exception e){
            new Base().log("aboutUs_btn button not found");
        }
        return new AboutUsPage(driver);
    }

    //enter Setting page
    public SettingPage enterSettingPage(){
        try {
            setting_btn.click();
        }catch (Exception e){
            new Base().log("setting_btn button not found");
        }
        return new SettingPage(driver);
    }



}
