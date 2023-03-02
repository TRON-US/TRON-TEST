package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AdvanceFuncPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CommitteePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class CommitteeTest extends BaseTest {


    @Parameters({"witnessKey", "udid"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId) throws Exception {

        try{
            TimeUnit.SECONDS.sleep(2);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            DRIVER.executeScript("mobile: terminateApp", params);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.executeScript("mobile: activateApp", params);
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
        }

        log("BaseTest Import ---start");
        importFirstWallet(importType.normal,privateKey);
        log("BaseTest Import ---Success");
    }




    public CommitteePage enterCommitteePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        AdvanceFuncPage minePage = mine.enterAdvancePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return committeePage;
    }


    @Test(groups = {"P0"},description = "send proposals", alwaysRun = true)
    public void test_001SendProposals() throws Exception {

        CommitteePage   committeePage = enterCommitteePage();
        committeePage.CreatProposalPage();
        String count = String.format("%.0f", Math.random() * 100000);
        System.out.println("委员会提议修改超级代表燃烧TRX值："+count);
        committeePage.change1proposal(count);
        WebElement wl = committeePage.findFirstproposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, count));
    }

    @Test(description = "check state proposal", alwaysRun = true)
    public void test_002checkProposalState() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String states = committeePage.getStateofproposal();
        System.out.println(states);
        Assert.assertTrue(isElementExist("投票中"));
    }

    @Test(groups = {"P0"}, description = "be delete My first Proposal", alwaysRun = true)
    public void test_003cancelAgreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.deleteAction();
        Assert.assertTrue(isElementExist("已取消"));
    }

    @Test(description = "secendnewProposal",alwaysRun = true)
    public void test_004makeSecondNewProposal() throws  Exception{
        Double sendcount = getAnAmountZero();
        String count = Double.toString(sendcount);
        count = Helper.getPrettyNumber(count);
        System.out.println(count);
        CommitteePage committeePage = enterCommitteePage();
        committeePage.CreatProposalPage();
        committeePage.change0proposal(count);
        WebElement wl = committeePage.findFirstproposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, count));
    }
    @Test(description = "be agreed Proposal", alwaysRun = true)
    public void test_005agreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.agreeAction();
        Assert.assertTrue(committeePage.getagreedStateofproposal());
    }

    //1个数值
    @Test(description = "be agreed value Proposal", alwaysRun = true)
    public void test_006agreedValueProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        Assert.assertTrue(committeePage.findvoteNumbers() > 0);
    }


    @Test(description = "newProposal",alwaysRun = true)
    public void test_007makeNewProposal_8agreed_9disagreed() throws  Exception{
        log("three 开始执行时间");
        CommitteePage committeePage = enterCommitteePage();
        committeePage.CreatProposalPage();
        committeePage.change2proposal("0.2");
        Assert.assertEquals(committeePage.descLabel.getText(),"提议修改创建账户费用为 0.2 TRX");
        committeePage.agreeAction();
        TimeUnit.SECONDS.sleep(1);
        committeePage.enterMyAgreedProposal();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(committeePage.totalVoteValue.getText(),"1");
        committeePage.navBack();
        boolean have = committeePage.disagreeAction();
        TimeUnit.SECONDS.sleep(1);
        if (have){
            Assert.assertEquals(committeePage.totalVoteValue.getText(),"0");
        }else {
            Assert.assertTrue(isElementExist("暂无数据") || isElementExist("已失效"));
        }

    }


    @Parameters({"witnessUrl"})
    @Test(description = "cheack proposals name", alwaysRun = true)
    public void test_010checkProposalName(String witnessUrl) throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String names = committeePage.getNameofproposal();
        System.out.println(names);
        Assert.assertTrue(names.contains(witnessUrl));
    }

    @Test(description = "cheack time order proposal", alwaysRun = true)
    public void test_011checkProposalTime() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        boolean states = committeePage.cheacktimeorderofproposal();
        System.out.println(states);
        Assert.assertTrue(states);

    }


}
