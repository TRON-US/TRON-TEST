package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.VoteConfirmPage;
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
            DRIVER.activateApp("com.tronlink.wallet");
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
        asset = frozenAndThawingPage.forzenSuccessEnterAssetPage("10");
        return asset;
    }



    @Test(description = "enter a number that great than the number of votes available", alwaysRun = true)
    public void test003_vote01() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.unusualVoteOperate();
        Assert.assertTrue(vote.getHits());
    }


    @Test(description = "Enter a vote of 0,prompt 'vote number null'", alwaysRun = true)
    public void test004_vote02() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.et_input.sendKeys("0");
        vote.vote_btn.click();
        Assert.assertTrue(vote.getTostInfo());
    }


    @Test(description = "The number of votes entered is empty,prompt 'vote number null'", alwaysRun = true)
    public void test005_vote03() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.vote_btn.click();
        Assert.assertTrue(vote.getTostInfo());
    }


    @Test(description = "Gets the address of the second candidate", alwaysRun = true)
    public void test006_searchVoteInfo() throws Exception {
        AssetPage asset = forzenTrx();
        VotePage vote = asset.enterVotePage();
        VoteConfirmPage voteConfirmPage = vote.setrVotePremise();
        voteConfirmPage.voteOperate();
        vote.checkTheSecondInfoOfVoted();
        //String address = vote.voted_address.get(1).getText();
        //vote.checkTheSecondInfoOfVoted01();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(vote.getExist());

    }


}
