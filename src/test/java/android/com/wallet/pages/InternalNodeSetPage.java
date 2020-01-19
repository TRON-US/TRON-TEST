package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class InternalNodeSetPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public  InternalNodeSetPage(AndroidDriver<?> driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_common_title")
    public WebElement title_text;

    @FindBy(id = "com.tronlink.wallet:id/tv_bg_right")
    public WebElement tv_bg_right_btn;


    public AddCustomNodePage enterAddCustomNodePage() throws Exception{
        tv_bg_right_btn.click();
        TimeUnit.SECONDS.sleep(3);
        return new AddCustomNodePage(driver);
    }

}
