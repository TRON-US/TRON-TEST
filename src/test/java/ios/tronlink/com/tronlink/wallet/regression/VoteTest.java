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

    @Parameters("address")
    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_checkintoVotePage(String address) throws Exception {
        enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("投票"));
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("Auto_test"));
    }

    @Test( alwaysRun = true)
    public void test002_checkPopularSearchTest() throws Exception {
        VotePage vote = enterVotePage();
        vote.enterSearch();
        Assert.assertEquals(vote.SRinput.getText(),"搜索超级代表");
        vote.SRSearch("sr-26");
        Assert.assertTrue(isElementExist("http://sr-26.com"));
    }

    @Test( alwaysRun = true)
    public void test003_voteSRInfoTest() throws Exception {
        VotePage vote = enterVotePage();
        vote.enterSearch();
        vote.SRSearch("sr-26");
        vote.enterSRPage();
        Assert.assertTrue(isElementExist("http://sr-26.com"));
        Assert.assertTrue(isElementExist("收益分成比例"));
        Assert.assertTrue(isElementExist("得票数"));
        Assert.assertTrue(isElementExist("票数占比"));
        Assert.assertTrue(isElementExist("出块数量"));
        Assert.assertTrue(isElementExist("链接"));
        Assert.assertTrue(isElementExist("查看投票注意事项"));

    }
    @Test( alwaysRun = true)
    public void test004_AvailableTrxText() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        String availableNumber = page.leftAmountLabel.getText();
        page.navBack();
        asset.enterVotePage();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(isElementExist(availableNumber));
    }

     @Test(alwaysRun = true)
     public void test005_multiSignPageTest() throws Exception {
         VotePage vote = enterVotePage();
         vote.enterMulti();
         Assert.assertTrue(isElementExist("修改投票账户"));
         Assert.assertTrue(isElementExist("使用多重签名功能，您可以操作其他账户的投票和领取奖励，这需要该账户赋予您相应权限。"));
         Assert.assertTrue(isElementExist("mitiSinAddressBook"));
     }

    @Test(alwaysRun = true)
    public void test006_multiVotePageTest() throws Exception {
        VotePage vote = enterVotePage();
        vote.enterMultiVote();
        Assert.assertTrue(isElementExist("批量投票"));
        Assert.assertTrue(isElementExist("取消全部投票"));
        Assert.assertTrue(isElementExist("投票权"));
        Assert.assertTrue(isElementExist("batchVote sort N"));
        Assert.assertTrue(isElementExist("batchVote search N"));
        Assert.assertTrue(isElementExist("获取投票权"));
        Assert.assertTrue(isElementExist("投票合计"));

    }

    @Test(alwaysRun = true)
    public void test007_VoteOrderPageTest() throws Exception {
        VotePage vote = enterVotePage();
        vote.enterSortVote();
        Assert.assertTrue(isElementExist("得票数（高到低）"));
        Assert.assertTrue(isElementExist("得票数（低到高）"));
        Assert.assertTrue(isElementExist("预计年化收益（高到低）"));
        Assert.assertTrue(isElementExist("预计年化收益（低到高）"));

    }

}
