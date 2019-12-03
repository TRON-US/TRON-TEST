package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class VoteTest extends BaseTest {

    //enter TRXPage
    public boolean guareeteanMainChain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.fastFindMainChain(asset.textArray)){
            return true;
        }else{
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseMainChain();
            mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            TrxPage trx = asset.enterTrxPage();
            TransferPage transferIn = trx.enterTransferInPage();
            String chain = transferIn.chain_text.getText();
            return chain.contains("DAppChain");
        }
    }
    public VotePage enterVotePage() throws InterruptedException {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(1);
        return vote;
    }
    @Test(description = "Guarantee Main Chain in VoteTest Test",alwaysRun = true)
    public void test000_guaranteemainchainTest() throws Exception {
        Assert.assertTrue(guareeteanMainChain());
    }

    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test001_checkPopularSearch() throws Exception {
        VotePage vote = enterVotePage();
        Assert.assertTrue(Helper.isElementExist(vote.driver,"投票"));
    }
    @Test(description = "Test into VotePage'", alwaysRun = true)
    public void test002_checkPopularSearch() throws Exception {
        VotePage vote = enterVotePage();
        Assert.assertTrue(vote.VoteDetail());
    }

//    @Test(description = "enter a number that is right in confirm", alwaysRun = true)
//    public void test002_vote01() throws Exception {
//        VotePage vote = enterVotePage();
//        Assert.assertTrue(vote.VoteTextRightTest());
//    }
//    @Test(description = "vote Success", alwaysRun = true)
//    public void test003_vote01() throws Exception {
//        VotePage vote = enterVotePage();
//        Assert.assertTrue(vote.VoteRightTest().length()>0);
//    }

}
