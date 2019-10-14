package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.VoteConfirmPage;
import wallet.pages.VotePage;

import org.testng.annotations.*;
/**
 * 投票功能测试
 */
public class VoteTest extends Base {


    @Parameters({"privateKey"})
    @BeforeMethod()
    public void setUpBefore(String privateKey) throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign(privateKey);
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



    @Test(description = "vote test")
    public void test001_vote() {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        VoteConfirmPage voteConfirmPage = vote.enterVoteConfirmPage();
        voteConfirmPage.voteOperate();
        String count = vote.et_input.getText();
        Assert.assertEquals(count,"1");
    }



}
