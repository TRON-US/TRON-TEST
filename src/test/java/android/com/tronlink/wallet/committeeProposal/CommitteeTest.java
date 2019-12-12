package android.com.tronlink.wallet.committeeProposal;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.CommitteeProposalPage;
import android.com.wallet.pages.CreateProposePage;
import android.com.wallet.pages.MinePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.PortUnreachableException;
import java.util.concurrent.TimeUnit;

public class CommitteeTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }



    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        //TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe
        //b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647
        new Helper().getSign("b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647", DRIVER);
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
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }



//    public CommitteeProposalPage enterCommitteeProposalPage(){
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        return mine.enterCommitteeProposalPage();
//    }



    @Test(description = "page can load before data", alwaysRun = true,enabled = false)
    public void test001_enterCommittee() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        //return mine.enterCommitteeProposalPage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(committeeProposalPage.allVotes_text.isDisplayed());
    }



    @Test(description = "page can load before data", alwaysRun = true,enabled = false)
    public void test002_search() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        committeeProposalPage.searchResult();
        Assert.assertTrue(committeeProposalPage.data_url_text.get(1).isDisplayed());
    }



    @Test(description = "create proposal", alwaysRun = true,enabled = false)
    public void test003_createCommitteeProposalSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(committeeProposalPage.title_btn.isDisplayed());
    }





    //赞成

    //取消赞成


    //撤销











}
