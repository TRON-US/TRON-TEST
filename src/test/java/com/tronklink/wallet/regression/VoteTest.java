package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.FrozenAndUnfreezePage;
import wallet.pages.VoteConfirmPage;
import wallet.pages.VotePage;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 投票功能测试
 */
public class VoteTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }


    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



    //because vote need Freeze trx
    @Test(enabled = false)
    public void test001_freezeEnergy() {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        int myVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        frozen.freezeCount_input.sendKeys("1");
        frozen.frozenTheEnergy(); //Freeze operating
        asset = frozen.enterAssetPage();
        frozen = asset.enterFrozenAndThawingPage();
        //int currentVotingPower = Integer.valueOf(frozen.votingPower_btn.getText());
        //Assert.assertTrue(myVotingPower + 1 == currentVotingPower);
    }

    @Test(description = "vote test",enabled = false)
    public void test002_vote() {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        VoteConfirmPage voteConfirmPage = vote.enterVoteConfirmPage();
        voteConfirmPage.voteOperate();
        String count = vote.et_input.getText();
        Assert.assertEquals(count,"1");
    }

    @Test(description = "enter a number that great than the number of votes available")
    public void test002_vote01() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        TimeUnit.SECONDS.sleep(2);
        vote.unusualVoteOperate();

        if (vote.reset_btn.getText().equals("Reset")) {
            String hits = asset.english_availableVote_toast.getText();
            Assert.assertTrue(hits.contains("Insufficient number of votes available"));
        }else {
            String hits = asset.availableVote_toast.getText();
            Assert.assertTrue(hits.contains("可用投票数不足"));
        }


    }

    @Test(description = "Enter a vote of 0,prompt 'vote number null'")
    public void test002_vote02() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.et_input.sendKeys("0");
        vote.vote_btn.click();
        if (vote.reset_btn.getText().equals("Reset")) {
            String hits = asset.english_availableVote_toast_null.getText();
            Assert.assertTrue(hits.contains("0 vote"));
        }else {
            String hits = asset.availableVote_toast_null.getText();
            Assert.assertTrue(hits.contains("投票数为空") || hits.contains("0 vote"));
        }

    }

    @Test(description = "The number of votes entered is empty,prompt 'vote number null'")
    public void test002_vote03() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        TimeUnit.SECONDS.sleep(1);
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.vote_btn.click();
        if (vote.reset_btn.getText().equals("Reset")) {
            String hits = asset.english_availableVote_toast_null.getText();
            Assert.assertTrue(hits.contains("0 vote"));
        }else {
            String hits = asset.availableVote_toast_null.getText();
            Assert.assertTrue(hits.contains("投票数为空"));
        }

    }

    @Test(description = "Freeze before testing")
    public void test002_aPremise01() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterFrozenAndThawingPage();

        TimeUnit.SECONDS.sleep(2);
        FrozenAndUnfreezePage frozenAndThawingPage =new FrozenAndUnfreezePage(DRIVER);
        frozenAndThawingPage.inputFrozenCountAndSure("10");
    }

    @Test(description = "the premise of vote ")
    public void test002_aPremise02() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        TimeUnit.SECONDS.sleep(2);
        VoteConfirmPage voteConfirmPage = vote.setrVotePremise();
        voteConfirmPage.voteOperate();
    }

    @Test(description = "Gets the address of the second candidate")
    public void test002_vote04() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        TimeUnit.SECONDS.sleep(1);
        vote.checkTheSecondInfoOfVoted();
        TimeUnit.SECONDS.sleep(3);
        VotePage.Address_Second = vote.voted_address.get(1).getText();
    }

    @Test(description = "Search bar input")
    public void test002_vote05() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();

        TimeUnit.SECONDS.sleep(2);
        vote.checkTheSecondInfoOfVoted01();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(VotePage.getSecondAddress().equals(vote.voted_address.get(0).getText()));
    }


}
