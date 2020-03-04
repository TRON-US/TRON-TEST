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

    //    @FindBy(xpath = "(//XCUIElementTypeButton[@name='投票'])[3]")
//    public WebElement voteTitle_btn;
    @FindBy(xpath = "(//XCUIElementTypeButton[@name='投票'])[1]")
    public WebElement vote_title;

    @FindBy(name = "重置")
    public WebElement reset_btn;


//    @FindBy(name = "com.tronlink.wallet:id/et_input")

    @FindBy(xpath = "(//XCUIElementTypeApplication[@name='TronLink']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeTextField")
    public WebElement et_input;


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

    @FindBy(name = "voteNumberValueLabel")//从0开始
    public List<WebElement> voted_TotalGets;

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

    @FindBy(id = "voteViewNumberLabel")
    public WebElement myvoteNumberLabel;

    @FindBy(id = "awards")
    public WebElement awardsLabel;


    @FindBy(id = "votingRewardNumberLabel")
    public WebElement votingRewardNumberLabel;

    @FindBy(id = "voteField")
    public WebElement voteField;

    @FindBy(id = "voteactionBtn")
    public WebElement voteactionBtn;

    @FindBy(xpath = "//*[@text='可用投票数不足']")
    public WebElement availableVote_toast;

    @FindBy(xpath = "//*[@text='Insufficient number of votes available']")
    public WebElement english_availableVote_toast;

    @FindBy(xpath = "//*[@text='投票数为空']")
    public WebElement availableVote_toast_null;

    @FindBy(xpath = "//*[@text='0 vote']")
    public WebElement english_availableVote_toast_null;

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

    public boolean seccondPageClear() throws Exception {
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '清空'").click();
        TimeUnit.SECONDS.sleep(2);
        try {
            driver.findElementById("titleLabel").getText();
            return false;
        }catch (Exception e){
            return true;
        }

    }
    public void  getReward() throws Exception{

        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确认'").click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(8);
        try {
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'").click();
        }catch (Exception e){}
        TimeUnit.SECONDS.sleep(5);


    }
    public void inputVoteNumber(int num) throws Exception {
        reset_btn.click();
        log("will input num:" + intToString(num));
        driver.findElementById("voteField").click();
        driver.findElementById("voteField").sendKeys(intToString(num));
        Helper.closeKeyBoard(driver);
        voteactionBtn.click();
    }

    public void voteSecondStep() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '立即投票'").click();
        TimeUnit.SECONDS.sleep(2);
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        TimeUnit.SECONDS.sleep(8);
        try {
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '确定'").click();
        }catch (Exception e){}
        TimeUnit.SECONDS.sleep(5);
    }

    public boolean inInVotePage () throws Exception {
        try {
            voteactionBtn.getText();
            return  true;
        }catch (Exception e) {
            return false;
        }

    }

    public boolean getTostInfo() {
        boolean tostInfo = false;
        if (vote_btn != null) {
            tostInfo = true;
        }
        System.out.println(tostInfo);
        return tostInfo;
    }

    public void unusualVoteOperate() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        driver.findElementsByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = 'resetLabel'").get(0).click();
//        reset_btn.click();
        int surplusAvailableVoteNum = Integer.parseInt(surplusAvailableVote_text.getText().toString());
        int unusualVoteNum = surplusAvailableVoteNum + 20;
        et_input.sendKeys(String.valueOf(unusualVoteNum));
        vote_btn.click();
    }

    public VoteConfirmPage enterVoteConfirmPage() {
        reset_btn.click();
        et_input.sendKeys("1");
        vote_btn.click();
        return new VoteConfirmPage(driver);
    }

    public void checkTheSecondInfoOfVoted() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        String voteListContent = vote_list.getText();
        if (!voteListContent.equals("我投过的") || !voteListContent.equals("My Votes")) {
            myVoteAndAllWitnessList.click();
            TimeUnit.SECONDS.sleep(1);
            my_voted_item.click();
            TimeUnit.SECONDS.sleep(1);
        }
        TimeUnit.SECONDS.sleep(3);
    }

    public void checkTheSecondInfoOfVoted01() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        String voteListContent = vote_list.getText();
        if (!voteListContent.equals("我投过的") || !voteListContent.equals("My Votes")) {
            myVoteAndAllWitnessList.click();
            TimeUnit.SECONDS.sleep(1);
            my_voted_item.click();
            TimeUnit.SECONDS.sleep(1);
            String voteInfo = myVotesNetInfoList.get(1).getText();
            search_edit_text.sendKeys(voteInfo);
        }
    }

    public VoteConfirmPage setrVotePremise() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        all_witness_edit_text.get(0).sendKeys("1");
        all_witness_edit_text.get(1).sendKeys("1");
        vote_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new VoteConfirmPage(driver);
    }

    public VoteConfirmPage confirmVote() {
        return new VoteConfirmPage(driver);
    }

    public boolean VoteDetail() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        voteDetailBtn.click();
        TimeUnit.SECONDS.sleep(2);
        return Helper.isElementExist(driver, "投票细则");
    }

    public boolean VoteTextRightTest() throws Exception {
        Thread.sleep(3);
        driver.findElementsByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = 'resetLabel'").get(0).click();
        Thread.sleep(3);
        System.out.println("suze:-" + all_witness_edit_text.size());
        all_witness_edit_text.get(2).sendKeys("1");
        Helper.tapWhitePlace(driver);
        driver.findElementsByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '投票'").get(1).click();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '立即投票'").click();
        return driver.findElementsByName("numberLabel").get(0).getText().contains("1");
    }


    public String VoteRightTest() throws Exception {
        Thread.sleep(3);
        driver.findElementsByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = 'resetLabel'").get(0).click();
        Thread.sleep(3);
        all_witness_edit_text.get(2).sendKeys("1");
        Helper.tapWhitePlace(driver);
        driver.findElementsByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '投票'").get(1).click();
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '立即投票'").click();
        driver.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '完成'").click();
        return avilabelAmount.getText();
    }

}
