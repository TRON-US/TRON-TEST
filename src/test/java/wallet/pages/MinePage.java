package wallet.pages;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;
import wallet.UITest.base.Base;

/**
 * mine page
 */
public class MinePage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MinePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlink.wallet:id/tv_about")
    public WebElement aboutUs_btn;


    @FindBy(id = "com.tronlink.wallet:id/setting")
    public WebElement setting_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_friend_invitation")
    public WebElement friendInvitation_btn;


    @FindBy(id = "com.tronlink.wallet:id/announcement")
    public WebElement announcement_btn;


    @FindBy(id = "com.tronlink.wallet:id/transfer_history")
    public WebElement transferHistory_btn;


    @FindBy(id = "com.tronlink.wallet:id/wallet_manager")
    public WebElement myPurse_btn;



    //enter about us Page
    public AboutUsPage enterAboutUsPage(){
        try {
            aboutUs_btn.click();
        }catch (Exception e){
            new Base().log("aboutUs_btn button not found");
        }
        return new AboutUsPage(driver);
    }


    //enter Setting page
    public SettingPage enterSettingPage(){
        try {
            setting_btn.click();
        }catch (Exception e){
            new Base().log("setting_btn button not found");
        }
        return new SettingPage(driver);
    }


    //enter FriendInvitation Page
    public FriendInvitationPage enterFriendInvitationPage(){
        try {
            friendInvitation_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new FriendInvitationPage(driver);
    }


    //enter FriendInvitation Page
    public AnnouncementPage enterAnnouncementPage(){
        try {
            announcement_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new AnnouncementPage(driver);
    }


    public TransactionRecordPage enterTransactionRecordPage(){
        try {
            TimeUnit.SECONDS.sleep(3);
            transferHistory_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }

        return new TransactionRecordPage(driver);
    }


    //enter wallet page
    public MyPursePage enterMyPursePage(){
        try {
            myPurse_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }





}
