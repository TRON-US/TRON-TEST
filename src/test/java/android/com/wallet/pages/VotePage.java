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


    @FindBy(id = "com.tronlinkpro.wallet:id/caim_reward")
    public WebElement caim_reward;

    @FindBy(id = "com.tronlinkpro.wallet:id/get_votes")
    public WebElement get_votes;

    @FindBy(id = "com.tronlinkpro.wallet:id/title")
    public WebElement voteTitle_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_batch_vote")
    public WebElement tv_batch_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_canuse_trx")
    public WebElement tv_canuse_trx;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_canuse_votes")
    public WebElement tv_canuse_votes;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement tv_common_right2;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public WebElement firstSR;

    @FindBy(id = "com.tronlinkpro.wallet:id/reset")
    public WebElement reset_btn;
    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement rl_bottom_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_vote_amount")
    public WebElement et_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_vote")
    public WebElement vote_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/surplus_available")
    public WebElement surplusAvailableVote_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_vote_select")
    public WebElement vote_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_all")
    public WebElement all_witness_item;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_me")
    public WebElement my_voted_item;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_input")
    public List<WebElement> all_witness_edit_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/address")
    public List<WebElement> voted_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vote_role")
    public WebElement myVoteAndAllWitnessList;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_person_name")
    public List<WebElement> myVotesNetInfoList;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_search")
    public WebElement search_edit_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_go")
    public WebElement btgo_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_awards")
    public WebElement reward_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public List<WebElement> witness_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_input")
    public WebElement vote_number_input;

    @FindBy(id = "com.tronlinkpro.wallet:id/vote_count")
    public WebElement witness_total_vote_text;



    @FindBy(xpath = "//*[@text='可用投票数不足']")
    public WebElement availableVote_toast;

    @FindBy(xpath = "//*[@text='Insufficient number of votes available']")
    public WebElement english_availableVote_toast;

    @FindBy(xpath = "//*[@text='投票数为空']")
    public WebElement availableVote_toast_null;

    @FindBy(xpath = "//*[@text='0 vote']")
    public WebElement english_availableVote_toast_null;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement password_input;

    @FindBy(id = "com.tronlinkpro.wallet:id/total_vote")
    public WebElement total_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_clear")
    public WebElement rl_clear;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_address")
    public WebElement et_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/v_common_title")
    public WebElement v_common_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_vote")
    public WebElement btn_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_voted_update")
    public WebElement btn_voted_update;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_voted_cancel")
    public WebElement voted_cancel;


    public void entermultiSignFromPage(){
        tv_common_right2.click();
    }

    public void changeFromeAddress(String mutaddr){
        rl_clear.click();
        et_address.sendKeys(mutaddr);
        confirmBan.click();
    }
    public void votefirstSRuseMutiSign() throws Exception{
        TimeUnit.SECONDS.sleep(1);
        firstSR.click();
        try {
            btn_vote.click();
        }catch (Exception ee){
            btn_voted_update.click();
        }
        et_input.sendKeys("2");
        bt_send.click();
        rl_bottom_next.click();
        password_input.sendKeys("Test0001");
        bt_send.click();
        TimeUnit.SECONDS.sleep(5);
    }


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
