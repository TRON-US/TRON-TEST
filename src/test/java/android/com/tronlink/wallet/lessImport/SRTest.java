package android.com.tronlink.wallet.lessImport;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SRTest extends Base {

    @Parameters({"witnessKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String witnessKey) throws Exception {
        DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        new Helper().getSign(witnessKey, DRIVER);
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

//    @Test(enabled = true,description = "Create proposal success", alwaysRun = true)
//    public void test001_createCommitteeProposalSuccess() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
//        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
//        committeeProposalPage = createProposePage.createProposal();
//        Assert.assertTrue(committeeProposalPage.title_btn.isDisplayed());
//        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
//        Assert.assertTrue(proposalDetailsPage.numAllVotes_btn.isDisplayed());
//        Assert.assertTrue(proposalDetailsPage.proposalsState_btn.isDisplayed());
//        Assert.assertTrue(proposalDetailsPage.numValidVotes_btn.isDisplayed());
//        Assert.assertTrue(proposalDetailsPage.createTime_text.isDisplayed());
//        Assert.assertTrue(proposalDetailsPage.endTime_text.isDisplayed());
//        Helper.swipScreenLitte(proposalDetailsPage.driver);
//        Assert.assertTrue(proposalDetailsPage.noApprovers_text.isDisplayed());
//    }
//
//    @Test(enabled = true,description = "Approve proposal", alwaysRun = true)
//    public void test002_approveProposal() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
//        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
//        MyProposalsPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(3);
//        while (!MyProposalsPage.isShotIDExist("btn_confirm")){
//            TimeUnit.SECONDS.sleep(1);
//            log("++");
//        }
//        MyProposalsPage.confirm_btn().click();
//        TimeUnit.SECONDS.sleep(1);
//        MyProposalsPage.pw_input.sendKeys("Test0001");
//        MyProposalsPage.send_btn.click();
//        while (!MyProposalsPage.isShotIDExist("tv_make_proposal")){
//            TimeUnit.SECONDS.sleep(1);
//            log("++");
//        }
////        TimeUnit.SECONDS.sleep(15);
//        Assert.assertTrue(MyProposalsPage.approveNum_text.getText()!="0");
//    }
//
//    //撤销
//    @Test(enabled = true,description = "Cancel proposal", alwaysRun = true)
//    public void test003_cancelProposal() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
//        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
//        ProposalDetailsPage proposalDetailsPage = MyProposalsPage.enterpProposalDetailsPage();
//        proposalDetailsPage.proposalCancle_btn.click();
//        while (!MyProposalsPage.isShotIDExist("btn_confirm")){
//            TimeUnit.SECONDS.sleep(1);
//            log("++");
//        }
//        proposalDetailsPage.button_confirm.click();
//        TimeUnit.SECONDS.sleep(1);
//        proposalDetailsPage.pw_input.sendKeys("Test0001");
//        proposalDetailsPage.send_btn.click();
//        while (!MyProposalsPage.isShotIDExist("tv_make_proposal")){
//            TimeUnit.SECONDS.sleep(1);
//            log("++");
//        }
//        String proposalStatus = proposalDetailsPage.proposals_state_btn.getText();
//        Assert.assertTrue(proposalStatus.equals("已取消"));
//    }

}
