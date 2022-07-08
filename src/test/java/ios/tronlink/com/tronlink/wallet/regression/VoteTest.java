package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
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
    public void test001_enterVotePageTest() throws Exception {
        enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("投票及奖励 "));
        Assert.assertTrue(isElementExist("超级代表"));
        Assert.assertTrue(isElementExist("多重签名投票"));
        Assert.assertTrue(isElementExist("resource manager introduce"));

    }



}
