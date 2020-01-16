package android.com.tronlink.wallet.committeeProposal;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
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


    @Parameters({"witnessKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String witnessKey) throws Exception {
        //TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe
        //b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647
        //new Helper().getSign("b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647", DRIVER);
        new Helper().getSign(witnessKey, DRIVER);
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



    public CommitteeProposalPage enterCommitteeProposalPage(){
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterCommitteeProposalPage();
    }



    @Test(enabled = true,description = "page can load before data", alwaysRun = true)
    public void test001_enterCommittee() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        //return mine.enterCommitteeProposalPage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Assert.assertTrue(committeeProposalPage.allVotes_text.isDisplayed());
    }



//    @Test(description = "search", alwaysRun = true)
//    public void test002_search() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.searchResult();
//        Assert.assertTrue(committeeProposalPage.data_url_text.get(1).isDisplayed());
//    }



    @Test(enabled = true,description = "Enter Proposal Detail success test", alwaysRun = true)
    public void test003_enterProposalDetails() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        //CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        //committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(proposalDetailsPage.proposalName_text.isDisplayed());
    }






    @Test(enabled = true,description = "create Proposal page reset", alwaysRun = true)
    public void test004_createProposalReset() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        createProposePage.proValue_ipt.get(1).clear();
        createProposePage.reset_btn.click();
        Assert.assertTrue(createProposePage.proValue_ipt.get(1).getText().equals("9999"));
    }





    @Test(enabled = true,description = "create proposal success", alwaysRun = true)
    public void test005_createCommitteeProposalSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(committeeProposalPage.title_btn.isDisplayed());
    }



    //............................
    //add 详情页显示  投票中！！！！！！
    @Test(enabled = true,description = "display Proposal Details Is Voteing", alwaysRun = true)
    public void test006_ProposalDetailsVoteing() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.proposalsState_btn.isDisplayed());
    }



    //............................
    //add 详情页显示  展示总票数
    @Test(enabled = true,description = "display Proposal Details count of Vote", alwaysRun = true)
    public void test007_ProposalDetailsVoteCount() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.numAllVotes_btn.isDisplayed());
    }


    //add 详情页显示  展示有效票数
    @Test(enabled = true,description = "display Proposal Details Valid Vote", alwaysRun = true)
    public void test008_ProposalDetailsValidVote() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.numValidVotes_btn.isDisplayed());
    }



    //add 详情页显示  展示有效票数
    @Test(enabled = true,description = "display Proposal Details Create Time", alwaysRun = true)
    public void test009_ProposalDetailsCreateTime() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.createTime_text.isDisplayed());
    }


    //add 详情页显示  展示结束时间
    @Test(enabled = true,description = "display Proposal End Time", alwaysRun = true)
    public void test010_ProposalDetailsEndTime() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.endTime_text.isDisplayed());
    }



    //add 详情页显示  无赞成者
    @Test(enabled = true,description = "display Proposal End Time", alwaysRun = true)
    public void test011_ProposalDetails() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.noApprovers_text.isDisplayed());
    }


    /////////////////.................................................../////////////////////////////
    //赞成，然后看详情有赞成者
    @Test(enabled = true,description = "have approve People", alwaysRun = true)
    public void test012_haveApprovePeople() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        MyProposalsPage.agree_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        ProposalDetailsPage proposalDetailsPage = MyProposalsPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.pproversPeo_text.isDisplayed());

//        committeeProposalPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.pw_input.sendKeys("Test0001");
//        committeeProposalPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(6);
//        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
//        Assert.assertTrue(proposalDetailsPage.pproversPeo_text.isDisplayed());
    }



    /////////////////.................................................../////////////////////////////
    @Test(enabled = true,description = "approve proposal", alwaysRun = true)
    public void test013_approveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        MyProposalsPage.agree_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Assert.assertTrue(MyProposalsPage.approveNum_text.getText()!="0");

//        committeeProposalPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.pw_input.sendKeys("Test0001");
//        committeeProposalPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(6);
//        Assert.assertTrue(committeeProposalPage.approveNum_text.getText()!="0");
    }




    //赞成后详情页在有赞成者
    /////////////////.................................................../////////////////////////////
    @Test(enabled = true,description = "disapprove proposal", alwaysRun = true)
    public void test014_disApproveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        MyProposalsPage.agree_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.agree_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        MyProposalsPage.pw_input.sendKeys("Test0001");
        MyProposalsPage.send_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        String number = MyProposalsPage.approveNum_text.getText();
        //System.out.println("number=" + number);
        Assert.assertTrue(number.equals("0") || number == "0");
//        committeeProposalPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.pw_input.sendKeys("Test0001");
//        committeeProposalPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(3);
//        committeeProposalPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.pw_input.sendKeys("Test0001");
//        committeeProposalPage.send_btn.click();
//        TimeUnit.SECONDS.sleep(6);
//        String number = committeeProposalPage.approveNum_text.getText();
//        System.out.println("number=" + number);
//        Assert.assertTrue(number.equals("0"));
    }

    //无赞成者


    /////////////////.................................................../////////////////////////////
    //撤销
    @Test(enabled = true,description = "cancel proposal", alwaysRun = true)
    public void test015_cancelProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        MyProposalsPage MyProposalsPage = committeeProposalPage.enterMyProposalsPage();
        ProposalDetailsPage proposalDetailsPage = MyProposalsPage.enterpProposalDetailsPage();
        //ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        proposalDetailsPage.proposalCancle_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        proposalDetailsPage.confirm_btn.click();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        proposalDetailsPage.pw_input.sendKeys("Test0001");
        proposalDetailsPage.send_btn.click();
        //asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
        String proposalStatus = proposalDetailsPage.proposals_state_btn.getText();
        //String proposalStatus = committeeProposalPage.proposals_state_btn.getText();
        Assert.assertTrue(proposalStatus.equals("已取消"));
    }



    //提议撤销时间 关键字
    @Test(enabled = true,description = "cancel proposal", alwaysRun = true)
    public void test016_cancelProposalDetailsStatus() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.proposalsState_btn.isDisplayed());
    }



    @Test(enabled = true,description = "cancel proposal", alwaysRun = true)
    public void test017_cancelProposalDetailsTimes() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.createTime_text.isDisplayed());
    }




    //我发起的提议
    @Test(enabled = true,description = "my proposals ", alwaysRun = true)
    public void test018_myProposals() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(committeeProposalPage.myProposals_text.getText() != "0");
    }




    //我赞成的提议
    @Test(enabled = true,description = "my Approve Proposals", alwaysRun = true)
    public void test019_myApproveProposals() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(committeeProposalPage.myApproved_text.getText() != "0");
    }




    //另一个入口发起提议，按钮可以点击就可以
    @Test(enabled = true,description = "create Proposals Entrance", alwaysRun = true)
    public void test020_createProposalsEntrance() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        asset.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        MyProposalsPage myProposals = committeeProposalPage.enterMyProposals();
        Assert.assertTrue(myProposals.createProposal_btn.isEnabled());
    }









}
