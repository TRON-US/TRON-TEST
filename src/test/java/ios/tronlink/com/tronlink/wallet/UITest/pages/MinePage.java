package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import java.util.concurrent.TimeUnit;

public class MinePage extends AbstractPage {

    public IOSDriver<?> driver;


    public MinePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "关于我们")
    public WebElement aboutUs_btn;


    @FindBy(name = "设置")
    public WebElement setting_btn;


    @FindBy(name = "好友邀请")
    public WebElement friendInvitation_btn;


    @FindBy(name = "公告")
    public WebElement announcement_btn;

    @FindBy(name = "帮助中心")
    public WebElement helpCenter_btn;

    @FindBy(name = "加入社群")
    public WebElement intoGroup_btn;

    @FindBy(name = "交易记录")
    public WebElement transferHistory_btn;


    @FindBy(name = "钱包管理")
    public WebElement myPurse_btn;


    @FindBy(name = "资源")
    public WebElement assets_btn;



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

    //enter Group page
    public GroupPage enterGroupPage(){
        try {
            intoGroup_btn.click();
        }catch (Exception e){
            new Base().log("intoGroup_btn button not found");
        }
        return new GroupPage(driver);
    }
    //enter Help page
    public HelpPage enterHelpPage(){
        try {
            helpCenter_btn.click();
        }catch (Exception e){
            new Base().log("intoGroup_btn button not found");
        }
        return new HelpPage(driver);
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


    //enter android.com.wallet page
    public MyPursePage enterMyPursePage(){
        try {
            myPurse_btn.click();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }


    public AssetPage enterAssetPage() throws Exception {
        assets_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }




}
