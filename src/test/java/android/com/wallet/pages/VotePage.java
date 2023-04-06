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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_main_title")
    public WebElement tv_main_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_to_stake")
    public WebElement tv_to_stake;

    @FindBy(id = "com.tronlinkpro.wallet:id/ic_token_right_arrow")
    public WebElement ic_token_right_arrow;



    @FindBy(id = "com.tronlinkpro.wallet:id/available_vote")
    public WebElement available_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/available_vote_right_title")
    public WebElement available_vote_right_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_multi_sign")
    public WebElement tv_multi_sign;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_to_promote")
    public WebElement tv_to_promote;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vote_tips")
    public WebElement tv_vote_tips;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_tips")
    public WebElement iv_tips;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_available_votes")
    public WebElement tv_available_votes;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_total_vote_rights")
    public WebElement tv_total_vote_rights;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_already_vote")
    public WebElement tv_already_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_close")
    public WebElement close;

    @FindBy(id = "com.tronlinkpro.wallet:id/caim_reward")
    public WebElement caim_reward;

    @FindBy(id = "com.tronlinkpro.wallet:id/get_votes")
    public WebElement get_votes;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_profit")
    public WebElement tv_profit;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_get_profit")
    public WebElement btn_get_profit;

    public void enterGetReword() throws Exception{
        btn_get_profit.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void confirmAction() throws Exception{
        confirm_btn.click();
        password_input.sendKeys("Test0001");
        bt_send.click();
        TimeUnit.SECONDS.sleep(5);
    }
//取消投票成功 取消投票显示 tvresult 文案
    //投票成功
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_right")
    public WebElement tv_right;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_batch_vote")
    public WebElement tv_batch_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_sort")
    public WebElement iv_sort;

    public void orderPopView(){
        iv_sort.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/rb_my_vote")
    public WebElement rb_my_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/rb_apr")
    public WebElement rb_apr;

    @FindBy(id = "com.tronlinkpro.wallet:id/rb_voted_count")
    public WebElement rb_voted_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_my_voted")
    public WebElement tv_my_voted;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_walletaddress")
    public WebElement tv_walletaddress;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_canuse_trx")
    public WebElement tv_canuse_trx;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_canuse_votes")
    public WebElement tv_canuse_votes;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_right2")
    public WebElement tv_common_right2;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_person_name")
    public WebElement firstSR;

    @FindBy(id = "com.tronlinkpro.wallet:id/no_more")
    public WebElement no_more;

    public void enterFirstSR(){
        firstSR.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_voting_onsiderations")
    public WebElement tv_voting_onsiderations;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_address")
    public WebElement tv_address;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_sr_number")
    public WebElement tv_sr_number;

    @FindBy(id = "com.tronlinkpro.wallet:id/vote_count")
    public WebElement vote_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_voted_count")
    public WebElement tv_voted_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_total_vote")
    public WebElement tv_total_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_voted_number")
    public WebElement tv_voted_number;

    @FindBy(id = "com.tronlinkpro.wallet:id/reset")
    public WebElement reset_btn;
    @FindBy(id = "com.tronlinkpro.wallet:id/rl_bottom_next")
    public WebElement rl_bottom_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_vote_amount")
    public WebElement et_input;

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_vote")
    public WebElement vote_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_vote")
    public WebElement vote_page_btn;

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

    @FindBy(id = "com.tronlinkpro.wallet:id/et_search")
    public WebElement et_search;

    public void searchString(String str){
        et_search.sendKeys(str);
    }
    @FindBy(id = "com.tronlinkpro.wallet:id/btn_asset_confirm")
    public WebElement btgo_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/caim_reward")
    public WebElement reward_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement confirm_btn;
    @FindBy(id = "com.tronlinkpro.wallet:id/btn_confirm")
    public WebElement btn_confirm;

    @FindBy(id = "com.tronlinkpro.wallet:id/root")
    public List<WebElement> witness_list;

    @FindBy(id = "com.tronlinkpro.wallet:id/et_input")
    public WebElement vote_number_input;

    public void enterVoteNumber(String number){
        vote_number_input.clear();
        vote_number_input.sendKeys(number);
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/vote_count")
    public WebElement witness_total_vote_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_batch_vote")
    public WebElement btn_batch_vote;
    public void signActiontoDone(){
        btn_batch_vote.click();
        bt_go.click();
        et_new_password.sendKeys("Test0001");
        bt_send.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_my_vote")
    public WebElement tv_my_vote;

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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement tv_common_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/all_stake_count_title")
    public WebElement all_stake_count_title;
    @FindBy(id = "com.tronlinkpro.wallet:id/btn_vote") //投票
    public WebElement btn_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_voted_update") //修改投票
    public WebElement btn_voted_update;

    @FindBy(id = "com.tronlinkpro.wallet:id/btn_voted_cancel") //取消投票
    public WebElement btn_voted_cancel;

    @FindBy(id = "com.tronlinkpro.wallet:id/bt_next")
    public WebElement bt_next;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_stake_amount")
    public WebElement tv_stake_amount;

    public void enterFastVote(){
        tv_to_promote.click();
    }

    public void enterStake(){
        ic_token_right_arrow.click();
    }

    public void enterVoteStep1ToConfirm() throws Exception{
        btn_vote.click();
        if (vote_number_input.getText().contains("1")){//retry 场景覆盖
            enterVoteNumber("2");
        }else {
            enterVoteNumber("1");
        }
        bt_next.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void enterVoteStep2Password() throws Exception{
        btn_confirm.click();
        password_input.sendKeys("Test0001");
        bt_send.click();
        TimeUnit.SECONDS.sleep(5);
    }
    public void enterEditVoteStep1ToConfirm() throws Exception{
        btn_voted_update.click();
        if (vote_number_input.getText().contains("1")){
            enterVoteNumber("2");
        }else {
            enterVoteNumber("1");
        }
        bt_next.click();
        TimeUnit.SECONDS.sleep(3);
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vote_sr")
    public WebElement tv_vote_sr;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_cancel_all_vote")
    public WebElement tv_cancel_all_vote;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_available_vote_count")
    public WebElement tv_available_vote_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_total_vote_count")
    public WebElement tv_total_vote_count;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_more_vote_right")
    public WebElement tv_more_vote_right;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_search_view")
    public WebElement ll_search_view;


    public void enterSearch(String key)throws Exception{
        ll_search_view.click();
        et_search.sendKeys(key);
        TimeUnit.SECONDS.sleep(1);
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_witness_name")
    public WebElement tv_witness_name;

    public void enterSRPage(){
        tv_witness_name.click();
    }

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ranking")
    public WebElement tv_ranking;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_name")
    public WebElement tv_name;

    public void enterBatchPage(){
        tv_batch_vote.click();
    }

    public void enterGetMoreTP(){
        tv_more_vote_right.click();
    }


    public void getVotes(){
        get_votes.click();
    }

    public void calmReward(){
        caim_reward.click();
    }

@FindBy(id = "com.tronlinkpro.wallet:id/tv_info_title")
public WebElement tv_info_title;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_search")
    public WebElement iv_search;

    public void openSearch(){
        iv_search.click();
    }
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
