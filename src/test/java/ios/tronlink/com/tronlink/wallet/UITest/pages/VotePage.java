package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class VotePage extends AbstractPage {


    public IOSDriver<?> driver;

    public VotePage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='投票'])[1]")
    public WebElement vote_title;

    @FindBy(name = "重置")
    public WebElement reset_btn;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name=\"voteHome searchBtn N\"])[1]")
    public WebElement searchBtn;

    public void sliderToSearch() throws Exception{
        slideScreenBottom();
    }

    @FindBy(id = "multiSign transfer tip icon")
    public WebElement multiSignTip;

    public void openTheTips(){
        multiSignTip.click();
    }
    public void closeTheTips(){
        TapAnyWhere(100,100);
    }
    public void enterWebPageMultiSignIntro(){
        driver.findElementByName("使用教程").click();
    }

    @FindBy(id = "voteAmountLabel")
    public WebElement voteAmountLabel;

    @FindBy(id = "nameLabel")
    public WebElement nameLabel ;

    @FindBy(id = "addressLabel")
    public WebElement addressLabel;

    @FindBy(id = "voteCountLabel")
    public WebElement voteCountLabel;

    public void enterFirstSRPage(){
        nameLabel.click();
    }

    public boolean isVoteButton(){
        try {
            voteButton();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public WebElement voteButton(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '投票'");
    }

    public boolean isModifyButton(){
        try {
            modifyButton();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public WebElement modifyButton(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '修改投票'");
    }

    public boolean isCancelButton(){
        try {
            cancelButton();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public WebElement cancelButton(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '取消投票'");
    }

    public WebElement voteConfirmButton(){
        return driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认投票'");
    }

    @FindBy(id = "topNetworkLabel")
    public WebElement topNetworkLabel;

    @FindBy(id = "topWalletNameLabel")
    public WebElement topWalletNameLabel;

    @FindBy(id = "inputTextField")
    public WebElement inputTextField;

    public void enterVoteStep1ToConfirm() throws Exception{
        voteButton().click();
        voteAuto();
    }

    public void voteAuto() throws Exception{
        if (inputTextField.getText().contains("1")){
            enterVoteNumber("2");
        }else {
            enterVoteNumber("1");
        }
        voteConfirmButton().click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void enterVoteNumber(String number) throws Exception{
        inputTextField.clear();
        inputTextField.sendKeys(number);
        closeKeyBoard();
    }

    public void enterVoteStep2Password() throws Exception{
        confirm_btn().click();
        passwordInputFinish();
    }

    public void enterMultiSignVoteStep2Password() throws Exception{
        confirm_btn().click();
        TimeUnit.SECONDS.sleep(3);
        confirm_btn().click();
        passwordInputFinish();
    }
    public void enterEditVoteStep1ToConfirm() throws Exception{
        modifyButton().click();
        voteAuto();
    }
    public void enterCancelVoteStep1ToConfirm() throws Exception {
        cancelButton().click();
        TimeUnit.SECONDS.sleep(3);
    }

    @FindBy(id = "votedLabel")
    public WebElement votedLabel;

    @FindBy(id = "搜索超级代表")
    public WebElement searchSRText;

    @FindBy(className = "XCUIElementTypeTextField")
    public WebElement SRField;

    public void enterSearch(String key) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        searchSRText.click();
        TimeUnit.SECONDS.sleep(1);
        SRField.sendKeys(key);
        TimeUnit.SECONDS.sleep(3);
    }


    @FindBy(name = "usedVoteAmountLabel")
    public WebElement usedVoteAmountLabel;

    @FindBy(name = "totalVoteAmountLabel")
    public WebElement totalVoteAmountLabel;

    @FindBy(name = "availableAmountLabel")
    public WebElement availableAmountLabel;

    @FindBy(id = "voteRewardHome  introduce")
    public WebElement introduce;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"我知道了\"]")
    public WebElement know;

    @FindBy(name = "去质押")
    public WebElement goStake;

    @FindBy(id = "stakedLabel")
    public WebElement stakedLabel;

    public void enterStake(){
        goStake.click();
    }

    public void enterIntroduce(){
        introduce.click();
    }


    @FindBy(xpath = "(//XCUIElementTypeButton[@name=\"voteReward sort\"])[1]")
    public WebElement sort;

    public void enterSortPobView(){
        sort.click();
    }

    public void enterOwenAddressAndNext(String addr) throws Exception{
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeTextView'").sendKeys(addr);
        closeKeyBoard();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '下一步'").click();
        TimeUnit.SECONDS.sleep(8);
    }

    @FindBy(name = "多重签名投票")
    public WebElement multi;

    public void enterMulti()throws Exception{
        multi.click();
        TimeUnit.SECONDS.sleep(1);
    }


    @FindBy(xpath = "(//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeTextField")
    public WebElement et_input;


    @FindBy(id = "accountValueLabel")
    public WebElement accountValueLabel;

    @FindBy(id = "feeValueLabel")
    public WebElement feeValueLabel;
    public void openFeeContent(){
        feeValueLabel.click();
    }

    @FindBy(id = "vote sortPop close")
    public WebElement sortFirst;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='投票'])[3]")
    public WebElement vote_btn;

    @FindBy(name = "//XCUIElementTypeStaticText[@name=\"871\"]")
    public WebElement surplusAvailableVote_text;

    @FindBy(name = "com.tronlink.wallet:id/ll_vote_select")
    public WebElement vote_list;

    @FindBy(name = "addressValueLabel")
    public List<WebElement> addressList;

    @FindBy(name = "addressValueLabel")
    public WebElement addressfirst;

    @FindBy(name = "com.tronlink.wallet:id/tv_me")
    public WebElement my_voted_item;


    @FindBy(name = "XCUIElementTypeTextField")//从2开始
    public List<WebElement> all_witness_edit_text;

    @FindBy(name = "com.tronlink.wallet:id/tv_vote_role")
    public WebElement myVoteAndAllWitnessList;

    @FindBy(name = "com.tronlink.wallet:id/tv_person_name")
    public List<WebElement> myVotesNetInfoList;

    @FindBy(name = "com.tronlink.wallet:id/et_search")
    public WebElement search_edit_text;

    @FindBy(id = "remainingNumberLabel")
    public WebElement avilabelAmount;

    @FindBy(id = "assets instruction")
    public WebElement voteDetailBtn;


    @FindBy(id = "voteactionBtn")
    public WebElement voteactionBtn;


    public boolean getHits() {
        boolean hits = false;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (vote_btn != null) {
            hits = true;
        }
        return hits;
    }


    public void backNav(){

        blackBackBtn.click();

    }


}
