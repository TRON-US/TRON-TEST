package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class VoteTest extends BaseTest {

    public VotePage enterVotePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        return vote;
    }
    @Test(description = "Guarantee Main Chain in VoteTest Test",alwaysRun = true)
    public void test000_guaranteemainchainTest() throws Exception {
        Assert.assertTrue(Helper.guaranteeMainChain(DRIVER));
    }

    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_checkPopularSearch() throws Exception {
        VotePage vote = enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(Helper.isElementExist(vote.driver,"投票"));
    }
    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test002_checkPopularSearch() throws Exception {
        VotePage vote = enterVotePage();
        Assert.assertTrue(vote.VoteDetail());
    }


}
