package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class DiscoverPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public DiscoverPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/iv_one")
    public WebElement search_btn;


    public DAPP_SearchPage enterDAPP_SearchPage(){
        try {
            search_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new DAPP_SearchPage(driver);
    }






}
