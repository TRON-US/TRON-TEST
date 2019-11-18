package ios.tronlink.com.tronlink.wallet.UITest.pages;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class DAPP_SearchResultPage extends AbstractPage {

    public IOSDriver<?> driver;

    public DAPP_SearchResultPage(IOSDriver<?> driver) {
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
