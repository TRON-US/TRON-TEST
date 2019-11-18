package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class DiscoverPage extends AbstractPage {


    public IOSDriver<?> driver;

    public DiscoverPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/iv_one")
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
