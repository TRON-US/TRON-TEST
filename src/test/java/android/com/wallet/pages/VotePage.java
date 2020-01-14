package android.com.wallet.pages;

import android.com.wallet.UITest.base.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 收款页
 */

public class VotePage extends AbstractPage {

    public AndroidDriver<?> driver;


    public VotePage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/title")
    public WebElement voteTitle_btn;


    @FindBy(id = "com.tronlink.wallet:id/reset")
    public WebElement reset_btn;


    @FindBy(id = "com.tronlink.wallet:id/et_input")
    public WebElement et_input;


    @FindBy(id = "com.tronlink.wallet:id/rl_vote")
    public WebElement vote_btn;

    @FindBy(id = "com.tronlink.wallet:id/surplus_available")
    public WebElement surplusAvailableVote_text;

    @FindBy(id = "com.tronlink.wallet:id/ll_vote_select")
    public WebElement vote_list;

    @FindBy(id = "com.tronlink.wallet:id/tv_all")
    public WebElement all_witness_item;

    @FindBy(id = "com.tronlink.wallet:id/tv_me")
    public WebElement my_voted_item;

    @FindBy(id = "com.tronlink.wallet:id/et_input")
    public List<WebElement> all_witness_edit_text;

    @FindBy(id = "com.tronlink.wallet:id/address")
    public List<WebElement> voted_address;

    @FindBy(id = "com.tronlink.wallet:id/tv_vote_role")
    public WebElement myVoteAndAllWitnessList;

    @FindBy(id = "com.tronlink.wallet:id/tv_person_name")
    public List<WebElement> myVotesNetInfoList;

    @FindBy(id = "com.tronlink.wallet:id/et_search")
    public WebElement search_edit_text;

    @FindBy(id = "com.tronlink.wallet:id/bt_go")
    public WebElement btgo_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_awards")
    public WebElement reward_btn;

    @FindBy(id = "com.tronlink.wallet:id/root")
    public List<WebElement> witness_list;

    @FindBy(id = "com.tronlink.wallet:id/et_input")
    public WebElement vote_number_input;

    @FindBy(id = "com.tronlink.wallet:id/vote_count")
    public WebElement witness_total_vote_text;



    @FindBy(xpath = "//*[@text='可用投票数不足']")
    public WebElement availableVote_toast;

    @FindBy(xpath = "//*[@text='Insufficient number of votes available']")
    public WebElement english_availableVote_toast;

    @FindBy(xpath = "//*[@text='投票数为空']")
    public WebElement availableVote_toast_null;

    @FindBy(xpath = "//*[@text='0 vote']")
    public WebElement english_availableVote_toast_null;

    public boolean getHits(){
        boolean hits = false;
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (vote_btn != null) {
            hits = true;
        }
        return hits;
    }

    public boolean getTostInfo() {
        boolean tostInfo = false;
        if (vote_btn != null) {
            tostInfo = true;
        }
        System.out.println(tostInfo);
        return tostInfo;
    }

    public void unusualVoteOperate() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        reset_btn.click();
        int surplusAvailableVoteNum = Integer.parseInt(new Base().removeSymbol(surplusAvailableVote_text.getText().toString()));
        int unusualVoteNum = surplusAvailableVoteNum + 20;
        et_input.sendKeys(String.valueOf(unusualVoteNum));
        vote_btn.click();
    }

    public boolean getExist(){
        return true;
    }
    public VoteConfirmPage enterVoteConfirmPage(){
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

    public VoteConfirmPage setrVotePremise() throws Exception{
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
}
