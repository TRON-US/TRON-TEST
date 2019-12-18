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



    public CommitteeProposalPage enterCommitteeProposalPage(){
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterCommitteeProposalPage();
    }



    @Test(description = "page can load before data", alwaysRun = true)
    public void test001_enterCommittee() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        //return mine.enterCommitteeProposalPage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(committeeProposalPage.allVotes_text.isDisplayed());
    }



    @Test(description = "search", alwaysRun = true)
    public void test002_search() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        committeeProposalPage.searchResult();
        Assert.assertTrue(committeeProposalPage.data_url_text.get(1).isDisplayed());
    }



    @Test(description = "Enter Proposal Detail success test", alwaysRun = true)
    public void test003_enterProposalDetails() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        //CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        //committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(proposalDetailsPage.proposalName_text.isDisplayed());
    }






    @Test(description = "create Proposal page reset", alwaysRun = true)
    public void test004_createProposalReset() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        //拿取这个值，然后对比？？？？？？？？？？？？？？？？？？？？
        createProposePage.proValue_ipt.get(1).clear();
        createProposePage.reset_btn.click();
        Assert.assertTrue(createProposePage.proValue_ipt.get(1).getText().equals("9999"));
    }





    @Test(description = "create proposal success", alwaysRun = true)
    public void test005_createCommitteeProposalSuccess() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        //改至少两个地
        committeeProposalPage = createProposePage.createProposal();
        Assert.assertTrue(committeeProposalPage.title_btn.isDisplayed());
    }



    //............................
    //add 详情页显示  投票中！！！！！！
    @Test(description = "display Proposal Details Is Voteing", alwaysRun = true)
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
    @Test(description = "display Proposal Details count of Vote", alwaysRun = true)
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
    @Test(description = "display Proposal Details Valid Vote", alwaysRun = true)
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
    @Test(description = "display Proposal Details Create Time", alwaysRun = true)
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
    @Test(description = "display Proposal End Time", alwaysRun = true)
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
    @Test(description = "display Proposal End Time", alwaysRun = true)
    public void test011_ProposalDetails() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.noApprovers_text.isDisplayed());
    }



    //赞成，然后看详情有赞成者
    @Test(description = "have approve People", alwaysRun = true)
    public void test012_haveApprovePeople() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        committeeProposalPage.agree_btn.click();
        TimeUnit.SECONDS.sleep(2);
        committeeProposalPage.pw_input.sendKeys("Test0001");
        committeeProposalPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        Assert.assertTrue(proposalDetailsPage.pproversPeo_text.isDisplayed());
    }




    @Test(description = "approve proposal", alwaysRun = true)
    public void test013_approveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        committeeProposalPage.agree_btn.click();
        TimeUnit.SECONDS.sleep(2);
        committeeProposalPage.pw_input.sendKeys("Test0001");
        committeeProposalPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(committeeProposalPage.approveNum_text.getText()!="0");
    }


    //赞成后详情页在有赞成者



    @Test(description = "disapprove proposal", alwaysRun = true)
    public void test014_disApproveProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
        committeeProposalPage.agree_btn.click();
        TimeUnit.SECONDS.sleep(2);
        committeeProposalPage.pw_input.sendKeys("Test0001");
        committeeProposalPage.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        committeeProposalPage.agree_btn.click();
        TimeUnit.SECONDS.sleep(2);
        committeeProposalPage.pw_input.sendKeys("Test0001");
        committeeProposalPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        String number = committeeProposalPage.approveNum_text.getText();
        System.out.println("number=" + number);
        Assert.assertTrue(number.equals("0"));
    }

    //无赞成者


    //撤销
    @Test(description = "cancel proposal", alwaysRun = true)
    public void test015_cancelProposal() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        CommitteeProposalPage committeeProposalPage = mine.enterCommitteeProposalPage();
        CreateProposePage createProposePage = committeeProposalPage.enterCreateProposePage();
        committeeProposalPage = createProposePage.createProposal();
//        committeeProposalPage.agree_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        committeeProposalPage.pw_input.sendKeys("Test0001");
//        committeeProposalPage.send_btn.click();
        TimeUnit.SECONDS.sleep(3);
        ProposalDetailsPage proposalDetailsPage = committeeProposalPage.enterpProposalDetailsPage();
        proposalDetailsPage.proposalCancle_btn.click();
        TimeUnit.SECONDS.sleep(1);
        proposalDetailsPage.confirm_btn.click();
        TimeUnit.SECONDS.sleep(2);
        proposalDetailsPage.pw_input.sendKeys("Test0001");
        proposalDetailsPage.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        String proposalStatus = committeeProposalPage.proposals_state_btn.getText();
        Assert.assertTrue(proposalStatus.equals("已取消"));
    }


    //提议撤销时间 关键字


    //进入我发起的提议


    //我赞成的提议


    //另一个入口发起提议，按钮可以点击就可以









}
