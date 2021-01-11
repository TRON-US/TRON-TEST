package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.TransactionRecordPage;
import android.com.wallet.pages.VoteConfirmPage;
import android.com.wallet.pages.VotePage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * vote function test
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
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    public AssetPage forzenTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndThawingPage = asset.enterFrozenAndUnfreezePage();
        //FrozenAndUnfreezePage frozenAndThawingPage =new FrozenAndUnfreezePage(DRIVER);
        asset = frozenAndThawingPage.forzenSuccessEnterAssetPage("2");
        return asset;
    }



//    //3.5版本由于需要添加取消全部投票的临时功能，暂时屏蔽了投票确认页面
//    @Test(groups = {"P0"},enabled = true,description = "Gets the address of the second candidate", alwaysRun = true)
//    public void test001_searchVoteInfo() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        VoteConfirmPage voteConfirmPage = vote.setrVotePremise();
//        voteConfirmPage.voteOperate();
//        vote.checkTheSecondInfoOfVoted();
//        //String address = vote.voted_address.get(1).getText();
//        //vote.checkTheSecondInfoOfVoted01();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertTrue(vote.getExist());
//    }
//
//
//
//    @Test(enabled = true,description = "test001_voteFirstPageShowTest", alwaysRun = true)
//    public void test001_voteFirstPageShowTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        log(vote.toString());
////        Assert.assertTrue(vote.getHits());
//    }
//
//
//    @Test(enabled = true,description = "Enter a vote of 0,prompt 'vote number null'", alwaysRun = true)
//    public void test003_vote02() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        vote.reset_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        vote.et_input.sendKeys("0");
//        vote.vote_btn.click();
//        Assert.assertTrue(vote.getTostInfo());
//    }
//
//
//    @Test(enabled = true,description = "The number of votes entered is empty,prompt 'vote number null'", alwaysRun = true)
//    public void test004_vote03() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        vote.reset_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        vote.vote_btn.click();
//        Assert.assertTrue(vote.getTostInfo());
//    }
//
//    @Test(enabled = true, description = "Vote transaction record test", alwaysRun = true)
//    public void test005_transactionRecord() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
//        String transactionType = transaction.transactionTypeList.get(0).getText();
//        String voteInfo = transaction.voteDetailList.get(0).getText();
//        System.out.println(transactionType);
//        System.out.println(voteInfo);
//        Assert.assertTrue(transactionType.equals("投票") || transactionType.equals("Vote"));
//        Assert.assertTrue(voteInfo.contains("总票数") || voteInfo.contains("Total votes"));
//    }
//
//
//    @Test(enabled = true,description = "Try to cancel all vote, vote for first sr", alwaysRun = true)
//    public void test006_cancelAllVote() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        vote.reset_btn.click();
//        TimeUnit.SECONDS.sleep(1);
//        vote.vote_btn.click();
//        Boolean isIntoPasswordPage = false;
//        try {
//            vote.password_input.isDisplayed();
//            isIntoPasswordPage = true;
//        } catch (Exception e) {
//
//        }
//        Assert.assertTrue(!isIntoPasswordPage);
//
//    }





}
