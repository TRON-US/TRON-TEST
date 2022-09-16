package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeverSetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public SeverSetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }




    @FindBy(id = "com.tronlink.global:id/ll_common_left")
    public WebElement back_btn;

    @FindBy(id = "com.tronlink.global:id/usa_layout") //com.tronlink.global:id/iv_select1
    public WebElement USA_btn;

    @FindBy(id = "com.tronlink.global:id/sigapor_layout") //    com.tronlink.global:id/iv_select2
    public WebElement Sigapor_btn;

//    public SettingPage enterSettingPageChoiseDappChain() throws Exception {
//        node_selected.get(1).click();
//        back_btn.click();
//        TimeUnit.SECONDS.sleep(3);
//        return new SettingPage(driver);
//    }
//
//
//    public SettingPage enterSettingPageChoiseMainChain() throws Exception {
//        node_selected.get(0).click();
//        TimeUnit.SECONDS.sleep(3);
//        back_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        return new SettingPage(driver);
//    }






}
