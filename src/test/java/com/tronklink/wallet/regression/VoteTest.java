package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.FrozenAndUnfreezePage;
import wallet.pages.VoteConfirmPage;
import wallet.pages.VotePage;

import org.testng.annotations.*;
/**
 * 投票功能测试
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
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }


    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



    //because vote need Freeze trx
    @Test(enabled = false)
    public void test001_freezeEnergy() {
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



}
