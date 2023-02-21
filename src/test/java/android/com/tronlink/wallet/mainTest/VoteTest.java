package android.com.tronlink.wallet.mainTest;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.VotePage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * vote function test
 */
public class VoteTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }



    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test001_VoteMainPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(vote.tv_main_title.getText(),"投票");
        Assert.assertEquals(vote.available_vote_right_title.getText(),"剩余投票权");
        Assert.assertEquals(vote.tv_multi_sign.getText(),"多重签名投票");
        Double total = removeSymbolDouble(vote.tv_total_vote_rights.getText());
        Double av = removeSymbolDouble(vote.tv_available_votes.getText());
        Double use = removeSymbolDouble(vote.tv_already_vote.getText());
        System.out.println("total: "+total+" av: "+av+" use: "+ use);
        Assert.assertEquals((use+av),total,0.1);
    }

     @Test(groups = {"P0"},alwaysRun = true)
     public void test002_VotePageGetRewardTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         VotePage vote = asset.enterVotePage();
         TimeUnit.SECONDS.sleep(2);
         if (isElementShotId("tv_profit")){
             Double ward = 0.0;
             if (vote.tv_profit.getText().contains("<0.001")){
                 ward = 0.001;
             }else {
                 ward = sepLeftNumberTextToDouble(vote.tv_profit.getText(),"TRX");
             }
             if (ward > 0){
                 vote.enterGetReword();
                 if (isElementShotId("tv_confirm_title")){
                     Assert.assertEquals(vote.tv_confirm_title.getText(),"确认交易");
                     Assert.assertEquals(vote.tv_info_title.getText(),"领取收益");
                     Assert.assertTrue(vote.tv_right.getText().contains("当前账户"));
                     vote.confirmAction();
                     Assert.assertEquals(vote.tv_result.getText(),"领取收益成功");
                 }else {
                     log("未到24小时场景");
                 }

             }else {
                 log("待领取收益数值 0 无法领取");
             }
         }else {
             log("无待领取收益该处隐藏");

         }
     }

    @Test(alwaysRun = true)
    public void test003_VoteOrderPopViewTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        vote.orderPopView();
        Assert.assertEquals(vote.tv_my_voted.getText(),"仅展示我的投票");
        Assert.assertTrue(vote.rb_my_vote.getText().contains("我的投票"));
        Assert.assertTrue(vote.rb_apr.getText().contains("预计 APR"));
        Assert.assertTrue(vote.rb_voted_count.getText().contains("得票数"));

    }


    @Test(alwaysRun = true)
    public void test004_VoteSearchSRTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("china");
        Assert.assertEquals(vote.tv_witness_name.getText(),"ChinaTRON");

    }

    @Test(alwaysRun = true)
    public void test005_VoteSRPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("sr-26");
        TimeUnit.SECONDS.sleep(1);
        Double votedNumber = removeSymbolDouble(vote.vote_count.getText());
        vote.enterSRPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(vote.tv_name.getText(),"http://sr-26.com");
        Assert.assertTrue(vote.tv_ranking.getText().contains("NO.2"));
        Assert.assertEquals(vote.tv_address.getText(),"TPffmvjxEcvZefQqS7QYvL1Der3uiguikE");
        Double votedSRNumber = removeSymbolDouble(vote.tv_total_vote.getText());
        Assert.assertEquals(votedNumber,votedSRNumber,0.1);
    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test006_VoteToTronChainSRTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("china");
        vote.enterSRPage();
        if (isElementShotId("btn_vote")){
            vote.enterVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }else if(isElementShotId("btn_voted_update")){
            vote.enterEditVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }
    }

    @Test(alwaysRun = true)
    public void test007_VoteToFirstSRTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("-26");
        vote.enterSRPage();
        if (isElementShotId("btn_vote")){
            vote.enterVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("http://sr-26.com"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }else if(isElementShotId("btn_voted_update")){
            vote.enterEditVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("http://sr-26.com"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }
    }

     @Test(alwaysRun = true)
     public void test008_improveAPRTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         VotePage vote = asset.enterVotePage();
         TimeUnit.SECONDS.sleep(2);
         Assert.assertEquals(vote.tv_to_promote.getText(),"去提升");
         vote.enterFastVote();
         Assert.assertEquals(vote.tv_common_title.getText(),"快速投票");
         Assert.assertEquals(vote.tv_vote_tips.getText(),"平分您的全部投票权给 3 个年化收益最高的候选人");
         Assert.assertEquals(vote.available_vote.getText(),"0/");
     }

    @Test(alwaysRun = true)
    public void test009_gotoResourceTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(vote.tv_to_stake.getText(),"去质押");
        String fromNumber = vote.tv_total_vote_rights.getText();
        vote.enterStake();
        Assert.assertEquals(vote.tv_common_title.getText(),"质押 TRX");
        Assert.assertEquals(vote.tv_stake_amount.getText(),fromNumber + " TRX");
    }

    //构建一个账户 投票权不足使用



}
