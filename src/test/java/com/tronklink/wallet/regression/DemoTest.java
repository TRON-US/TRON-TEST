package com.tronklink.wallet.regression;

import wallet.pages.AssetPage;
import wallet.pages.SendTrxPage;
import wallet.pages.VotePage;
import static org.junit.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;


public class DemoTest extends Base {

    @BeforeMethod()
    public void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign();
    }

    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        assertEquals(true,transfer.transferTtile_btn.isDisplayed());
    }


    @Test //测试进入投票页
    public void test02_checkPopular() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        assertEquals(true,vote.voteTitle_btn.isDisplayed());
    }


    @Test //测试进入投票页
    public void test03_checkPopular() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        //Assert.assertEquals(vote.voteTitle_btn.getText(),"投票");
        assertEquals(true,vote.voteTitle_btn.isDisplayed());
    }





}
