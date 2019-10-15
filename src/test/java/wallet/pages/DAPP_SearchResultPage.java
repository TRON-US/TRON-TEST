package wallet.pages;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class DAPP_SearchResultPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DAPP_SearchResultPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_name")
    public WebElement searchResultFirst_btn;


    public DAPP_BrowerPage enterDAPP_BrowerPage() throws Exception{
        searchResultFirst_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new DAPP_BrowerPage(driver);
    }

}
