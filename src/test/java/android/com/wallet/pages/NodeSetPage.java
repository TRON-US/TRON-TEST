package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class NodeSetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public NodeSetPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlink.wallet:id/iv_select")
    public List<WebElement> node_selected;



    @FindBy(id = "com.tronlink.wallet:id/ll_common_left")
    public WebElement back_btn;





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



}
