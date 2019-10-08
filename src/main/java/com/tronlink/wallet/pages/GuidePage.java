package com.tronlink.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.tronlink.wallet.UITest.base.Base;
import io.appium.java_client.android.AndroidDriver;

public class GuidePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public GuidePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_import")
    public WebElement impAccount;


    public UserAgreementPage enterUserAgreementPage() {
        try {
            impAccount.click();
        }catch (Exception e){
            Base.log("impAccount button not found");
        }
        return new UserAgreementPage(driver);
    }




}
