package android.com.wallet.pages;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;

/**
 * mine page
 */
public class MinePage extends AbstractPage {


    public MinePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/wallet_manager")
    public WebElement wallet_manager;

    @FindBy(id = "com.tronlinkpro.wallet:id/select_online")
    public WebElement online_version_icon;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_left")
    public WebElement userAgreementBackBtn;

    @FindBy(id = " com.tronlinkpro.wallet:id/log")
    public WebElement logItem;

    @FindBy(id = " com.tronlinkpro.wallet:id/update")
    public WebElement updateItem;

    @FindBy(id = " com.tronlinkpro.wallet:id/user_agreement")
    public WebElement userAgreement;

    @FindBy(id = " com.tronlinkpro.wallet:id/join_community")
    public WebElement joinCommunity;

    @FindBy(id = " com.tronlinkpro.wallet:id/tv_content")
    public WebElement tv_content;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_pic")
    public WebElement ad_pic;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_close")
    public WebElement adClose_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_title")
    public WebElement userAgreementTitle;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_about")
    public WebElement aboutUs_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/setting")
    public WebElement setting_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_friend_invitation")
    public WebElement friendInvitation_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/address_book")
    public WebElement addressBook_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/announcement")
    public WebElement announcement_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/about")
    public WebElement abuoutus_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/transfer_history")
    public WebElement transferHistory_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/wallet_manager")
    public WebElement myPurse_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/assets")
    public WebElement assets_btn;

//    @FindBy(id = "com.tronlinkpro.wallet:id/dapp")
//    public WebElement dapp_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/commit_proposal")
    public WebElement committeeProposals_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_advanced_features")
    public WebElement advanced_features_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_en")
    public WebElement enTelegramID;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_twitter")
    public WebElement tv_twitter;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_wechat")
    public WebElement tv_wechat;

    public void enterLogPage(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"版本日志\")").click();
    }

    public void enterUpdatePage(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"版本更新\")").click();
    }
    public void enterUserAgreementPage(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"用户协议\")").click();
    }

    public void enterjoinCommunityPage(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"加入社群\")").click();
    }
    //enter about us Page
    public AboutUsPage enterAboutUsPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        Helper.swipScreen(driver);
        try {
            aboutUs_btn.click();
        }catch (Exception e){
            new Base().log("aboutUs_btn button not found");
        }
        return new AboutUsPage(driver);
    }


    //enter Setting page
    public SettingPage enterSettingPage(){
        Helper.swipScreen(driver);
        try {
            Helper.swipScreen(driver);
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
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new FriendInvitationPage(driver);
    }

    //enter AddressBook Page
    public AddressBookPage enterAddressBookPage(){
        try {
            addressBook_btn.click();
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new AddressBookPage(driver);
    }


    //enter FriendInvitation Page
    public AnnouncementPage enterAnnouncementPage(){
        try {
            swipScreenLitte();
            announcement_btn.click();
        }catch (Exception e){
            System.out.println(e);
        }
        return new AnnouncementPage(driver);
    }


    public TransactionRecordPage enterTransactionRecordPage(){
        try {
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
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }

    public CommitteeProposalPage enterCommitteeProposalPage(){
        try {
            Helper.swipScreenLitte(driver);
            TimeUnit.SECONDS.sleep(1);
            advanced_features_btn.click();
            TimeUnit.SECONDS.sleep(2);
            committeeProposals_btn.click();
            TimeUnit.SECONDS.sleep(14);
        }catch (Exception e){
            System.out.println(e);
        }
        return new CommitteeProposalPage(driver);
    }
    @FindBy(id = "com.tronlinkpro.wallet:id/ll_tab_assets")
    public WebElement assetsMain_btn;

    public AssetPage enterAssetPage() throws Exception {
        assetsMain_btn.click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return new AssetPage(driver);
    }

    public DAPP_BrowerPage enterDAPP_BrowerPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        advanced_features_btn.click();
        TimeUnit.SECONDS.sleep(1);
        findElementByText("DApp 浏览器").click();
        return new DAPP_BrowerPage(driver);
    }





}
