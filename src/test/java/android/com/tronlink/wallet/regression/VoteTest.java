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



    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test001_VoteMainPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(Double.parseDouble(prettyString(vote.tv_canuse_trx.getText()))>0);
        Assert.assertTrue(vote.nav_title.getText().contains("投票"));
        Assert.assertTrue(vote.tv_common_right2.getText().contains("多重签名"));
    }

     @Test(alwaysRun = true)
     public void test002_BatchVoteTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         VotePage vote = asset.enterVotePage();
         vote.enterBatchPage();
         Assert.assertTrue(vote.title.getText().contains("批量投票"));
         Assert.assertTrue(vote.tv_cancel_all_vote.getText().contains("取消全部投票"));
     }


    @Test(alwaysRun = true)
    public void test003_GetMoreVotePTTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.enterBatchPage();
        vote.enterGetMoreTP();
        if (isElementShotId("tv_ok")){
            Assert.assertTrue(vote.tv_content.getText().contains("更多投票权"));
            findByShotId("tv_ok").click();
        }
        Assert.assertTrue(vote.nav_title.getText().contains("质押"));
    }

    @Test(alwaysRun = true)
    public void test004_GetMoreVotePTFromMainPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.getVotes();
        if (isElementShotId("tv_ok")){
            Assert.assertTrue(vote.tv_content.getText().contains("更多投票权"));
            findByShotId("tv_ok").click();
        }
        Assert.assertTrue(vote.nav_title.getText().contains("质押"));
    }

     @Test(alwaysRun = true)
     public void test005_receiveIncome() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         VotePage vote = asset.enterVotePage();
         vote.calmReward();
         TimeUnit.SECONDS.sleep(8);
         Assert.assertTrue(vote.tv_info_title.getText().contains("领取收益"));
     }

      @Test(alwaysRun = true)
      public void test006_SearchFuncTest() throws Exception {
          AssetPage asset = new AssetPage(DRIVER);
          VotePage vote = asset.enterVotePage();
          vote.openSearch();
          Assert.assertTrue(vote.et_search.getText().contains("搜索超级代表"));
          vote.searchString("git.ico");
          Assert.assertTrue(vote.firstSR.getText().contains("https://git.ico"));
      }

       @Test(alwaysRun = true)
       public void test007_ChangeTabVote() throws Exception {
           AssetPage asset = new AssetPage(DRIVER);
           VotePage vote = asset.enterVotePage();
           TimeUnit.SECONDS.sleep(2);
           vote.findElementByText("我的投票").click();
           TimeUnit.SECONDS.sleep(1);
           Helper.swipScreen(DRIVER);
           Helper.swipScreen(DRIVER);
           Assert.assertTrue(vote.no_more.getText().contains("没有更多了"));
       }

        @Test(alwaysRun = true)
        public void test008_goToSRPage() throws Exception {
            AssetPage asset = new AssetPage(DRIVER);
            VotePage vote = asset.enterVotePage();
            vote.enterFirstSR();
            Assert.assertTrue(vote.tv_sr_number.getText().contains("NO.1"));
            Assert.assertTrue(vote.tv_voting_onsiderations.getText().contains("查看投票注意事项"));
        }

         @Test(alwaysRun = true)
         public void test009_voteTest() throws Exception {
             AssetPage asset = new AssetPage(DRIVER);
             VotePage vote = asset.enterVotePage();
             vote.enterBatchPage();
             String number =prettyString(vote.vote_number_input.getText());
             if (vote.vote_number_input.getText().contains("请输入投票数量")) {
                 number = "1";
             }
             vote.enterVoteNumber(number);
             vote.signActiontoDone();
             TimeUnit.SECONDS.sleep(4);
             vote.findElementByText("我的投票").click();
             Integer newint = Integer.parseInt(number)+1;
             Integer recoderInt = Integer.parseInt(prettyString(vote.tv_my_vote.getText()));
             Assert.assertEquals(newint,recoderInt);
         }


}
