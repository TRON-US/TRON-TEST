package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.*;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;


import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;


/**
 * vote function test
 */
public class VoteTest extends BaseTest {

    public AssetPage forzenTrx() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndThawingPage =  asset.enterFrozenAndThawingPage();
        asset = frozenAndThawingPage.forzenSuccessEnterAssetPage("10");
        return asset;
    }


    //because vote need Freeze trx
    @Test(enabled = false)
    public void test001_freezeEnergy() throws Exception {
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



    @Test(description = "enter a number that great than the number of votes available",alwaysRun = true)
    public void test003_vote01() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.unusualVoteOperate();
        Assert.assertTrue(vote.getHits());
    }



    @Test(description = "Enter a vote of 0,prompt 'vote number null'",alwaysRun = true)
    public void test004_vote02() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.et_input.sendKeys("0");
        vote.vote_btn.click();
        Assert.assertTrue(vote.getTostInfo());
    }



    @Test(description = "The number of votes entered is empty,prompt 'vote number null'",alwaysRun = true)
    public void test005_vote03() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        vote.reset_btn.click();
        TimeUnit.SECONDS.sleep(1);
        vote.vote_btn.click();
        Assert.assertTrue(vote.getTostInfo());
    }




    @Test(description = "Gets the address of the second candidate",alwaysRun = true)
    public void test006_searchVoteInfo() throws Exception {
        AssetPage asset = forzenTrx();
        VotePage vote = asset.enterVotePage();
        VoteConfirmPage voteConfirmPage = vote.setrVotePremise();
        voteConfirmPage.voteOperate();
        vote.checkTheSecondInfoOfVoted();
        String address = vote.voted_address.get(1).getText();
        vote.checkTheSecondInfoOfVoted01();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(address.equals(vote.voted_address.get(0).getText()));

    }


}
