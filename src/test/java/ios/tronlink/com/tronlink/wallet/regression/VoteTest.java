package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class VoteTest extends BaseTest {

    public VotePage enterVotePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(4);
        return vote;
    }

    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_checkintoVotePage() throws Exception {
        VotePage vote = enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(Helper.isElementExist(vote.driver,"投票"));
    }
    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test002_checkPopularSearch() throws Exception {
        VotePage vote = enterVotePage();
        Assert.assertTrue(vote.VoteDetail());
    }

    @Test(description = "Test  a number that great than the number of votes available", alwaysRun = true)
    public void test003_voteareatValue() throws Exception {
        VotePage vote = enterVotePage();
        log(vote.avilabelAmount.getText());
        int ava = Integer.parseInt(vote.avilabelAmount.getText().replace(",","")) ;
        vote.inputVoteNumber(ava+10);
        Assert.assertTrue(vote.inInVotePage());
    }
    @Test(description = "Test reset action is effect ", alwaysRun = true)
    public void test004_TestResetctionisEffect() throws Exception {
        VotePage vote = enterVotePage();
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(2);
        String tempstr = vote.voteField.getText();
        log(tempstr);
        Assert.assertEquals(tempstr,"0");
    }

    @Test(description = "Test vote success ", alwaysRun = true)
    public void test005_TestvoteSuccess() throws Exception {
        VotePage vote = enterVotePage();
        int before = Integer.parseInt(vote.avilabelAmount.getText().replace(",","")) ;
        vote.inputVoteNumber(1);
        vote.voteSecondStep();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(vote.myvoteNumberLabel.getText(),"1");
    }

    @Test(description = "Gets the address is right", alwaysRun = true)
    public void test006_TestvoteAddressIsright() throws Exception {
        VotePage vote = enterVotePage();
        String firstPageStr = vote.addressfirst.getText();
        vote.inputVoteNumber(1);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(vote.driver.findElementById("addressValueLabel").getText(),firstPageStr);
    }

    @Test(description = "Gets the address is right", alwaysRun = true)
    public void test007_TestvoteNumberIsright() throws Exception {
        VotePage vote = enterVotePage();
        vote.inputVoteNumber(2);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(vote.driver.findElementById("numberLabel").getText(),"2");
    }

    @Test(description = "vote Second Page clear", alwaysRun = true)
    public void test008_TestSecondPageclear() throws Exception {
        VotePage vote = enterVotePage();
        vote.inputVoteNumber(2);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(vote.seccondPageClear());
    }

    @Test(description = "get reward vote ", alwaysRun = true)
    public void test009_TestgetRewardvote() throws Exception {
        VotePage vote = enterVotePage();
        if (vote.votingRewardNumberLabel.getText().contains("0 TRX")){
            Assert.assertTrue(true);
            log("no ward");
            return;
        }else {

            SimpleDateFormat min = new SimpleDateFormat();// 格式化时间
            min.applyPattern("dd");// a为am/pm的标记
            Date date = new Date();// 获取当前时间
            System.out.println( "日期数:" + min.format(date));
            if(Integer.parseInt( min.format(date))%2 == 1)
            {
                log("no need ward");
                Assert.assertTrue(true);
                return;
            }else {
                log("after 24h get reward");
                vote.getReward();
                Assert.assertEquals(vote.votingRewardNumberLabel.getText(),"0 TRX");
            }

        }
    }
}
