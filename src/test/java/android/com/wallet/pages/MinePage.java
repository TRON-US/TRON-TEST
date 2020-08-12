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
//        try {
//            TimeUnit.SECONDS.sleep(2);
//            // if page display AD , cloese the AD
//            if (ad_pic.isDisplayed()){
//                adClose_btn.click();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){}

    }

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_pic")
    public WebElement ad_pic;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_close")
    public WebElement adClose_btn;


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


    @FindBy(id = "com.tronlinkpro.wallet:id/transfer_history")
    public WebElement transferHistory_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/wallet_manager")
    public WebElement myPurse_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/assets")
    public WebElement assets_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/dapp")
    public WebElement dapp_btn;


    //com.tronlinkpro.wallet:id/commit_proposal
    //com.tronlinkpro.wallet:id/commit_proposal
    //com.tronlinkpro.wallet:id/committee_proposals
    @FindBy(id = "com.tronlinkpro.wallet:id/commit_proposal")
    public WebElement committeeProposals_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_advanced_features")
    public WebElement advanced_features_btn;



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
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new FriendInvitationPage(driver);
    }

    //enter AddressBook Page
    public AddressBookPage enterAddressBookPage(){
        try {
            addressBook_btn.click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new AddressBookPage(driver);
    }


    //enter FriendInvitation Page
    public AnnouncementPage enterAnnouncementPage(){
        try {
            announcement_btn.click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new AnnouncementPage(driver);
    }


    public TransactionRecordPage enterTransactionRecordPage(){
        try {
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
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
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return new MyPursePage(driver);
    }


    public CommitteeProposalPage enterCommitteeProposalPage(){
        try {
            TimeUnit.SECONDS.sleep(3);
            advanced_features_btn.click();
            TimeUnit.SECONDS.sleep(2);
            committeeProposals_btn.click();
            //committeeProposals_btn.click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println(e);
        }
        return new CommitteeProposalPage(driver);
    }


    public AssetPage enterAssetPage() throws Exception {
        assets_btn.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        return new AssetPage(driver);
    }

    public DAPP_BrowerPage enterDAPP_BrowerPage() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        advanced_features_btn.click();
        TimeUnit.SECONDS.sleep(1);
        dapp_btn.click();
        return new DAPP_BrowerPage(driver);
    }





}
