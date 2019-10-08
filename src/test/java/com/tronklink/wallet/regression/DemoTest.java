package com.tronklink.wallet.regression;

import com.tronlink.wallet.UITest.base.Base;
import com.tronlink.wallet.pages.AssetPage;
import com.tronlink.wallet.pages.TransferPage;
import com.tronlink.wallet.pages.VotePage;
import static org.junit.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DemoTest extends Base {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Base.setUpBeforeClass();
        //Base.getSign();
    }

    @BeforeMethod()
    public static void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        Base.getSign();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransferPage transfer = asset.enterTransferPage();
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
