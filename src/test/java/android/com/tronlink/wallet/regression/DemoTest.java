package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.VotePage;

import org.testng.annotations.Parameters;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.testng.annotations.*;

public class DemoTest extends Base {
//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

//    @BeforeClass(alwaysRun = true)
//    public void setUpBefore(String privateKey) throws Exception {
//        Helper.getSign(privateKey,DRIVER);
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(){
//        DRIVER.closeApp();
//        DRIVER.activateApp("com.tronlink.wallet");
//    }

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(alwaysRun = true) //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        assertEquals(true, transfer.transferTtile_btn.isDisplayed());
    }


    @Test(alwaysRun = true) //测试进入投票页
    public void test02_checkPopular() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        assertEquals(true, vote.voteTitle_btn.isDisplayed());
    }


    @Test(alwaysRun = true) //测试进入投票页
    public void test03_checkPopular() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        //Assert.assertEquals(vote.voteTitle_btn.getText(),"投票");
        assertEquals(true, vote.voteTitle_btn.isDisplayed());
    }


}
