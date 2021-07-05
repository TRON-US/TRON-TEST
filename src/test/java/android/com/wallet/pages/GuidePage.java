package android.com.wallet.pages;

import android.com.wallet.UITest.base.Base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class GuidePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public GuidePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "wallet.tronlink.global:id/tv_import")
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
