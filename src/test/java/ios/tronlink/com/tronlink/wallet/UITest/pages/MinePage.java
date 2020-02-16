package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

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

    @FindBy(name = "委员会提议")
    public WebElement committee_btn;

    //enter about us Page
    public AboutUsPage enterAboutUsPage() {
        try {
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            //TimeUnit.SECONDS.sleep(1);
            aboutUs_btn.click();
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            //TimeUnit.SECONDS.sleep(1);

        } catch (Exception e) {
            new Base().log("aboutUs_btn button not found");
        }
        return new AboutUsPage(driver);
    }

    public CommitteePage enterCommitteePage() {
        try {
            TimeUnit.SECONDS.sleep(3);
            waiteTime(15);
            committee_btn.click();
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            new Base().log("committee_btn button not found");
        }
        return new CommitteePage(driver);
    }

    //enter Setting page
    public SettingPage enterSettingPage() {
        try {
            Helper.swipScreen(driver);
            waiteTime();
            setting_btn.click();
            waiteTime();
        } catch (Exception e) {
            new Base().log("setting_btn button not found");
        }
        return new SettingPage(driver);
    }

    //enter Group page
    public GroupPage enterGroupPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            intoGroup_btn.click();
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            new Base().log("intoGroup_btn button not found");
        }
        return new GroupPage(driver);
    }

    //enter Help page
    public HelpPage enterHelpPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            helpCenter_btn.click();
            TimeUnit.SECONDS.sleep(5);

        } catch (Exception e) {
            new Base().log("intoGroup_btn button not found");
        }
        return new HelpPage(driver);
    }

    //enter FriendInvitation Page
    public FriendInvitationPage enterFriendInvitationPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
            friendInvitation_btn.click();
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new FriendInvitationPage(driver);
    }


    //enter FriendInvitation Page
    public AnnouncementPage enterAnnouncementPage() {
        try {
            announcement_btn.click();
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new AnnouncementPage(driver);
    }


    public TransactionRecordPage enterTransactionRecordPage() {
        try {
            TimeUnit.SECONDS.sleep(3);
            transferHistory_btn.click();
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            System.out.println(e);
        }

        return new TransactionRecordPage(driver);
    }


    //enter android.com.wallet page
    public MyPursePage enterMyPursePage() {
        try {
            waiteTime();
            myPurse_btn.click();
            waiteTime();
        } catch (Exception e) {
            myPurse_btn.click();
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }


    public AssetPage enterAssetPage() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        assets_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }


}
