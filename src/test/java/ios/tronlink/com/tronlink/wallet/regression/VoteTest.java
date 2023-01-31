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
        TimeUnit.SECONDS.sleep(3);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        return vote;
    }

    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_enterVotePageTest() throws Exception {
        enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("剩余投票权"));
        Assert.assertTrue(isElementExist("超级代表"));
        Assert.assertTrue(isElementExist("多重签名投票"));
        Assert.assertTrue(isElementExist("voteRewardHome  introduce"));
    }

    @Test(alwaysRun = true)
    public void test002_VotePageAmountTest() throws Exception {
        VotePage page = enterVotePage();
        Double total = sepRightNumberTextToDouble( page.totalVoteAmountLabel.getText(),"总投票权");
        Double voted = sepRightNumberTextToDouble( page.usedVoteAmountLabel.getText(),"已投票");
        Double av = Double.parseDouble(removeSymbolString(page.availableAmountLabel.getText().trim()) );
        Assert.assertEquals(voted+av,total,0.0001);

    }

    @Test(alwaysRun = true)
    public void test003_VotePopViewTest() throws Exception {
        VotePage page = enterVotePage();
        page.enterIntroduce();
        Assert.assertTrue(isElementExist("投票说明"));
        Assert.assertTrue(isElementExist("我知道了"));
        page.know.click();
        page.enterSortPobView();
        Assert.assertTrue(isElementExist("排序"));
        Assert.assertTrue(isElementExist("我的投票（高到低）"));
        Assert.assertTrue(isElementExist("预计 APR（高到低）"));
        Assert.assertTrue(isElementExist("得票数（高到低）"));
        page.TapAnyWhere(200,200);
        Assert.assertFalse(isElementExist("排序"));
    }

    @Test(alwaysRun = true,description = "搜索功能查看")
    public void test004_VoteSearchSRTest() throws Exception {
        VotePage page = enterVotePage();
        page.sliderToSearch();
        page.enterSearch("china");
        Assert.assertEquals(page.nameLabel.getText(),"ChinaTRON");

    }

    @Test(alwaysRun = true)
    public void test005_VoteSRPageTest() throws Exception {
        VotePage page = enterVotePage();
        page.sliderToSearch();
        page.enterSearch("sr-26");
        Double votedNumber = removeSymbolDouble(page.voteAmountLabel.getText());
        page.enterFirstSRPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(page.nameLabel.getText(),"http://sr-26.com");
        Assert.assertEquals(page.addressLabel.getText(),"TPffmvjxEcvZefQqS7QYvL1Der3uiguikE");
        Assert.assertEquals(removeSymbolDouble(page.voteCountLabel.getText()),votedNumber,0.1);
    }

    @Test(groups = {"P0"},alwaysRun = true,description = "投票给tronChina 成功")
    public void test006_VoteToTronChinaSRTest() throws Exception {
        VotePage page = enterVotePage();
        TimeUnit.SECONDS.sleep(3);
        page.sliderToSearch();
        page.enterSearch("china");
        page.enterFirstSRPage();
        TimeUnit.SECONDS.sleep(1);
        if (page.isVoteButton()){
            page.enterVoteStep1ToConfirm();
        }else if(page.isModifyButton()){
            page.enterEditVoteStep1ToConfirm();
        }
        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
        Assert.assertTrue(isElementExist("投票"));
        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
        page.enterVoteStep2Password();
        Assert.assertTrue(isElementExist("投票成功"));
        Assert.assertTrue(isElementExist("完成"));

    }


    @Test(groups = {"P0"},alwaysRun = true,description = "测试取消投票+剩余1票的提示页")
    public void test007_CancelVoteToTronChinaTest() throws Exception {
        VotePage page = enterVotePage();
        TimeUnit.SECONDS.sleep(3);
        page.sliderToSearch();
        page.enterSearch("china");
        page.enterFirstSRPage();
        TimeUnit.SECONDS.sleep(1);
        if (page.isCancelButton()){
            if (page.votedLabel.getText().equalsIgnoreCase("1")){
                page.enterCancelVoteStep1ToConfirm();
                Assert.assertTrue(isElementExist("因波场网络投票数量最少为 1，将为您保留当前超级代表的一票；若您希望全部取消，可解锁全部质押的 TRX。"));
            }else {
                page.enterCancelVoteStep1ToConfirm();
                Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
                Assert.assertTrue(isElementExist("取消投票"));
                Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
                page.enterVoteStep2Password();
                Assert.assertTrue(isElementExist("取消投票成功"));
            }

        }else if(page.isVoteButton()){
            page.enterVoteStep1ToConfirm();
            page.enterVoteStep2Password();
            Assert.assertTrue(isElementExist("投票成功"));
        }
    }

    @Test(alwaysRun = true)
    public void test008_goToMultiSignIntroTest() throws Exception {
        VotePage page = enterVotePage();
        page.enterMulti();
        page.openTheTips();
        Assert.assertTrue(isElementExist("您拥有控制权限的多签账户"));
        TimeUnit.SECONDS.sleep(1);
        page.closeTheTips();
        page.enterWebPageMultiSignIntro();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(isWebView());

    }

    @Test(alwaysRun = true)
    public void test009_gotoResourceTest() throws Exception {
        VotePage page = enterVotePage();
        Double total = sepRightNumberTextToDouble( page.totalVoteAmountLabel.getText(),"总投票权");
        page.enterStake();
        Double stake = sepMiddleNumberTextToDouble( page.stakedLabel.getText(),"已质押:","TRX");
        Assert.assertEquals(stake,total,0.1);
    }



//;投票success 单sr；修改单sr；取消单sr，取消的票数计算；查看sr说明页；交易确认页与前面页的正确性对比；多签功能nav的验证；多签说明的跳转；




}
