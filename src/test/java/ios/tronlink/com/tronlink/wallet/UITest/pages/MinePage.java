package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MinePage extends AbstractPage {

    public IOSDriver<?> driver;


    public MinePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "切换网络")
    public WebElement changeNetBtn;

    public void changeNet(){
        changeNetBtn.click();
    }

    public WebElement mainBtn(){
        return driver.findElementByIosNsPredicate("label == \"TRON 主网\"");
    }
    public WebElement shastaBtn(){
        return driver.findElementByIosNsPredicate("label == \"TRON Shasta 测试网\"");
    }
    public WebElement DAppChainBtn(){
        return driver.findElementByIosNsPredicate("label == \"DAppChain 主网\"");
    }

    @FindBy(name = "切换节点")
    public WebElement nodeBtn;
    public void enterNode(){
        nodeBtn.click();
    }


    @FindBy(name = "取消")
    public WebElement cancelBtn;

    @FindBy(name = "确认")
    public WebElement confirmBtn;



    @FindBy(name = "关于我们")
    public WebElement aboutUs_btn;


    @FindBy(name = "设置")
    public WebElement setting_btn;

    @FindBy(name = "高级功能")
    public WebElement advanceFunc_btn;

    public void enterAdv(){
        advanceFunc_btn.click();
    }

    @FindBy(name = "好友邀请")
    public WebElement friendInvitation_btn;

    public void enterInvitation(){
        friendInvitation_btn.click();
    }

    @FindBy(name = "官方网站")
    public WebElement officeWeb;

    @FindBy(id = "nodeNameLabel")
    public List<WebElement> nodeNameLabels ;

    @FindBy(name = "公告")
    public WebElement announcement_btn;

    @FindBy(name = "帮助中心")
    public WebElement helpCenter_btn;

    @FindBy(name = "加入社群")
    public WebElement intoGroup_btn;

    @FindBy(name = "交易历史")
    public WebElement transferHistory_btn;

    @FindBy(name = "用户协议")
    public WebElement usersAgreement_btn;

    @FindBy(name = "钱包管理")
    public WebElement myPurse_btn;

    @FindBy(name = "版本日志")
    public WebElement versionNote_btn;

    @FindBy(name = "tab assets")
    public WebElement assets_btn;

    @FindBy(name = "委员会提议")
    public WebElement committee_btn;

    @FindBy(name = "版本更新")
    public WebElement update_btn ;
    
    @FindBy(name = "地址本")
    public WebElement addressBook_btn;

    @FindBy(name = "货币单位")
    public WebElement currency;

    @FindBy(name = "label")
    public WebElement label;

    @FindBy(name = "centerBtn")
    public WebElement centerBtn;


    public WebElement cnyBtn(){
        return driver.findElementByIosNsPredicate("label == \"CNY\"");
    }

    public WebElement usdBtn(){
        return driver.findElementByIosNsPredicate("label == \"USD\"");
    }

    public void changeCurrency(String type){
        if (type.contains("cny")){
            currency.click();
            cnyBtn().click();
        }else {
            currency.click();
            usdBtn().click();
        }
    }



    @FindBy(name = "DApp连接管理")
    public WebElement DAppBtn;

    public void enterDAppManage() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        DAppBtn.click();
    }

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"TronLink\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
    public WebElement noticeBtn;

    public void enterNotice() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        noticeBtn.click();
    }
    
    @FindBy(name = "message setting close")
    public WebElement switchBtn;

    @FindBy(id = "titleLabel")
    public WebElement titleLabel;

    @FindBy(name = "关闭")
    public WebElement closeBtn;

    @FindBy(name = "系统通知权限")
    public WebElement sysNoticeBtn;

    public void enterAddressBook(){
        addressBook_btn.click();
    }

    public void updateClick(){
        update_btn.click();
    }

    public void enterOffice(){
        Twitter.click();
    }

    @FindBy(id = "Telegram")
    public WebElement Telegram;

    public void enterTelegram(){
        Telegram.click();
    }

    @FindBy(id = "Twitter")
    public WebElement Twitter;

    public void enterTwitter(){
        officeWeb.click();
    }



    @FindBy(id = "addressBook add")
    public WebElement bookAdd;

    public void enterBookAdd(){bookAdd.click();}
    //enter about us Page
    public AboutUsPage enterAboutUsPage() {
        waiteTime();
        Helper.swipScreenLitter(driver);
        aboutUs_btn.click();
        return new AboutUsPage(driver);
    }

    public CommitteePage enterCommitteePage() {
        try {
            TimeUnit.SECONDS.sleep(3);
            waiteTime();
            committee_btn.click();
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            new Base().log("committee_btn button not found");
        }
        return new CommitteePage(driver);
    }


    //enter Setting page
    public SettingPage enterSettingPage() {
        setting_btn.click();
        return new SettingPage(driver);
    }

    //enter Group page
    public GroupPage enterGroupPage() {

            Helper.swipScreenLitter(driver);
            aboutUs_btn.click();
            intoGroup_btn.click();

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

    @FindBy(name = "语言")
    public WebElement languagecn;

    @FindBy(name = "Language")
    public WebElement languageen;

    public WebElement englishButton(){
        return  driver.findElementByIosNsPredicate("label == \"English\"");
    }
    public WebElement cnButton(){
        return  driver.findElementByIosNsPredicate("label == \"简体中文\" AND name == \"textLabel\"");
    }
    public void changeLanguage(String type){
        if (type.contains("cn")){
            languageen.click();
            cnButton().click();
        }else {
            languagecn.click();
            englishButton().click();
        }
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

    public AdvanceFuncPage enterAdvancePage() {
        try {
            advanceFunc_btn.click();
        } catch (Exception e) {
            System.out.println(e);
        }

        return new AdvanceFuncPage(driver);
    }

    public TransactionRecordPage enterTransactionRecordPage() {
        transferHistory_btn.click();
        return new TransactionRecordPage(driver);
    }

    public void enterUsersAgreement(){
        usersAgreement_btn.click();
    }

    public void enterVersionLog(){
        versionNote_btn.click();
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
        driver.findElementByIosNsPredicate("label == \"tab assets\"").click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }


}
