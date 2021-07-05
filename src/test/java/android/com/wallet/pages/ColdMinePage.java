package android.com.wallet.pages;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import io.appium.java_client.android.AndroidDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * mine page
 */
public class ColdMinePage extends AbstractPage {


    public ColdMinePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "wallet.tronlink.global:id/wallet_manager")
    public WebElement walletManager_btn;

    @FindBy(id = "wallet.tronlink.global:id/announcement")
    public WebElement announcement_btn;

    @FindBy(id = "wallet.tronlink.global:id/join_community")
    public WebElement joinCommunity_btn;

    @FindBy(id = "wallet.tronlink.global:id/help")
    public WebElement howToUse_btn;

    @FindBy(id = "wallet.tronlink.global:id/about")
    public WebElement about_btn;

    @FindBy(id = "wallet.tronlink.global:id/setting")
    public WebElement setting_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_address")
    public WebElement address_test;

    @FindBy(id = "wallet.tronlink.global:id/tv_name")
    public WebElement walletName_test;



  //enter about us Page
    public AboutUsPage enterAboutUsPage(){
        try {
          about_btn.click();
        }catch (Exception e){
            new Base().log("aboutUs_btn button not found");
        }
        return new AboutUsPage(driver);
    }


    //enter Setting page
    public ColdSettingPage enterSettingPage(){
        Helper.swipScreen(driver);
        try {
            Helper.swipScreen(driver);
            setting_btn.click();
        }catch (Exception e){
            new Base().log("setting_btn button not found");
        }
        return new ColdSettingPage(driver);
    }


    //enter FriendInvitation Page
    public AnnouncementPage enterAnnouncementPage(){
        try {
            announcement_btn.click();
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new AnnouncementPage(driver);
    }

    //enter wallet manager
    public MyPursePage enterWalletMananerPage(){
        try {
            walletManager_btn.click();
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }

}
