package android.com.tronlink.wallet.committeeProposal;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.PortUnreachableException;
import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;

public class CommitteeTest extends Base {

    @Parameters({"witnessKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String witnessKey) throws Exception {
        new Helper().getSign(witnessKey, DRIVER);
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }



    @Test(enabled = true,description = "Enter Proposal Detail success test", alwaysRun = true)
    public void test001_enterProposalDetails() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.proposalName_text.isDisplayed());
    }

    @Test(enabled = true,description = "Create proposal page reset btn test", alwaysRun = true)
    public void test002_createProposalReset() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        createProposePage.proValue_ipt.get(1).clear();
        createProposePage.reset_btn.click();
        Assert.assertTrue(createProposePage.proValue_ipt.get(1).getText().equals("9999"));
    }



    @Test(enabled = true,description = "Create proposal success", alwaysRun = true)
    public void test003_createCommitteeProposalSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(committeeProposalPage.title_btn.isDisplayed());
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.numAllVotes_btn.isDisplayed());
        Assert.assertTrue(proposalDetailsPage.proposalsState_btn.isDisplayed());
        Assert.assertTrue(proposalDetailsPage.numValidVotes_btn.isDisplayed());
        Assert.assertTrue(proposalDetailsPage.createTime_text.isDisplayed());
        Assert.assertTrue(proposalDetailsPage.endTime_text.isDisplayed());
        Helper.swipScreenLitte(proposalDetailsPage.driver);
        Assert.assertTrue(proposalDetailsPage.noApprovers_text.isDisplayed());
    }

    /////////////////.................................................../////////////////////////////
    @Test(groups = {"P0"},enabled = true,description = "Approve proposal", alwaysRun = true)
    public void test004_approveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        TimeUnit.SECONDS.sleep(3);
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        MyProposalsPage.agree_btn.click();
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Assert.assertTrue(MyProposalsPage.approveNum_text.getText()!="0");
    }

    @Test(groups = {"P0"},enabled = true,description = "Disapprove proposal", alwaysRun = true)
    public void test005_disApproveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        MyProposalsPage.agree_btn.click();
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.agree_btn.click();
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        String number = MyProposalsPage.approveNum_text.getText();
        Assert.assertTrue(number.equals("0") || number == "0");
    }

    //撤销
    @Test(groups = {"P0"},enabled = true,description = "Cancel proposal", alwaysRun = true)
    public void test006_cancelProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        ProposalDetailsPage proposalDetailsPage = MyProposalsPage.enterpProposalDetailsPage();
        proposalDetailsPage.proposalCancle_btn.click();
        proposalDetailsPage.confirm_btn.click();
        proposalDetailsPage.send_btn.click();
        proposalDetailsPage.pw_input.sendKeys("Test0001");
        proposalDetailsPage.send_btn.click();
        TimeUnit.SECONDS.sleep(10);
        String proposalStatus = proposalDetailsPage.proposals_state_btn.getText();
        Assert.assertTrue(proposalStatus.equals("已取消"));
    }

    //提议撤销时间 关键字
    @Test(enabled = true,description = "Cancel proposal", alwaysRun = true)
    public void test007_cancelProposalDetailsStatus() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.proposalsState_btn.isDisplayed());
        Assert.assertTrue(proposalDetailsPage.createTime_text.isDisplayed());
    }

    //我发起的提议
    @Test(enabled = true,description = "my proposals ", alwaysRun = true)
    public void test008_myProposals() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Assert.assertTrue(committeeProposalPage.myProposals_text.getText() != "0");
        Assert.assertTrue(committeeProposalPage.myApproved_text.getText() != "0");
    }

    //另一个入口发起提议，按钮可以点击就可以
    @Test(enabled = true,description = "Create Proposals Entrance", alwaysRun = true)
    public void test009_createProposalsEntrance() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage myProposals = committeeProposalPage.enterMyProposals();
        Assert.assertTrue(myProposals.createProposal_btn.isEnabled());
    }









}
