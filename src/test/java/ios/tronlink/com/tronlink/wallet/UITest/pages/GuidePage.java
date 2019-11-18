package ios.tronlink.com.tronlink.wallet.UITest.pages;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class GuidePage extends AbstractPage {
    public IOSDriver<?> driver;

    public GuidePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }
    
    @FindBy(name = "com.tronlink.wallet:id/tv_import")
    public WebElement impAccount;


    public UserAgreementPage enterUserAgreementPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            impAccount.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            new Base().log("impAccount button not found");
        }
        return new UserAgreementPage(driver);
    }





}
