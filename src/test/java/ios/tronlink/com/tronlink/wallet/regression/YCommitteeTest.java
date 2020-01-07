package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CommitteePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class YCommitteeTest extends BaseTest {

//    String privateKey = "2f5d032f395573491cb1e0684d684105ad5b5ff56db3f45f277e7928e791472a";
    public String myChangeCount;
    public boolean isimport = false;


    public CommitteePage perparWallet(String privateKey) throws Exception {
        log("导入钱包");
        AssetPage assetPage = new AssetPage(DRIVER);
        if(assetPage.getWalletName().contains("Committee"))
        {
            log("有这个钱包没有进入导入流程：");
            MinePage minePage = assetPage.enterMinePage();
            CommitteePage committeePage = minePage.enterCommitteePage();
            return committeePage;
        }
        log("无这个钱包开始进入导入流程：");
        TimeUnit.SECONDS.sleep(2);
        assetPage.addWallet_btn.click();
        log("进入添加钱包页面");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("importUsePrivateKey0");
        findWebElement("私钥导入").click();
        DRIVER.findElementByClassName("XCUIElementTypeTextView").sendKeys(privateKey);
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(10);
        DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys("Committee");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(3);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(3);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("完成").click();
        TimeUnit.SECONDS.sleep(10);

        System.out.println("importUsePrivateKey1");
        TimeUnit.SECONDS.sleep(3);
        MinePage minePage = assetPage.enterMinePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return committeePage;
    }

    public CommitteePage enterCommitteePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return committeePage;
    }

    @Test(description = "guarantee Chain in MainChain",alwaysRun = true)
    public void test000_GuaranteeChainName() throws Exception {
        Assert.assertTrue( Helper.guaranteeMainChain(DRIVER));
    }

    @Parameters({"witnessKey"})
    @Test(description = "send proposals", alwaysRun = true)
    public void test_001SendProposals(String witnessKey) throws Exception {
        CommitteePage committeePage;
        System.out.println("isimport:" + isimport);
        if (!isimport ) {
            committeePage = perparWallet(witnessKey);
            isimport = true;
        } else {
            committeePage = enterCommitteePage();
        }

        while (true){
            SimpleDateFormat min = new SimpleDateFormat();// 格式化时间
            min.applyPattern("mm");// a为am/pm的标记
            Date date = new Date();// 获取当前时间
            System.out.println( "分钟数:" + min.format(date));
            if(Integer.parseInt( min.format(date))%10 == 0)
            {
                break;
            }
            TimeUnit.SECONDS.sleep(5);
            Helper.swipRefreshScreen(DRIVER);
        }

        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(6);

        log("开始执行时间");
        String count = String.format("%.0f", Math.random() * 100000);
        myChangeCount = count;
        System.out.println(count);
        committeePage.change1proposal(count);
        WebElement wl = committeePage.findFirstproposalWl();
        log("进入我发起的投票并获取到第一个元素");
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, myChangeCount));
    }



    @Test(description = "cheack state proposal", alwaysRun = true)
    public void test_003cheackProposalState() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String states = committeePage.getStateofproposal();
        System.out.println(states);
        Assert.assertTrue(states.contains("投票中"));
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



    //有没有攥成着
    @Test(description = "be cancal agreed Proposal", alwaysRun = true)
    public void test_007disagreedProposal() throws Exception {

        CommitteePage committeePage = enterCommitteePage();
        committeePage.disagreeAction();
        Assert.assertTrue(committeePage.getdisagreedStateofproposal());
        log("dis agreed proposal 时间");

    }

    //1个状态
    @Test(description = "be disagreed value Proposal", alwaysRun = true)
    public void test_008disagreedValueProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        Assert.assertTrue(committeePage.findvoteafterNumbers() == 0);
    }

    @Test(description = "newProposal",alwaysRun = true)
    public void test_009makeNewProposal() throws  Exception{

        log("new proposal 开始执行时间");
        String count = String.format("%.0f", Math.random() * 100000);
        myChangeCount = count;
        System.out.println(count);
        CommitteePage committeePage = enterCommitteePage();
        TimeUnit.SECONDS.sleep(3);
        committeePage.Setuppropos.click();

        committeePage.change1proposal(count);
        WebElement wl = committeePage.findFirstproposalWl();
        log("进入new我发起的投票并获取到第一个元素");
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, myChangeCount));
    }

    @Test(description = "be delete My Proposal", alwaysRun = true)
    public void test_010cancalagreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.deleteAction();
        String states = committeePage.getStateofproposal();
        Assert.assertTrue(states.contains("已取消"));

    }

    @Parameters({"witnessUrl"})
    @Test(description = "cheack proposals name", alwaysRun = true)
    public void test_011cheackProposalName(String witnessUrl) throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String names = committeePage.getNameofproposal();
        System.out.println(names);
        Assert.assertTrue(names.contains(witnessUrl));
    }

    @Test(description = "cheack time order proposal", alwaysRun = true)
    public void test_012cheackProposalTime() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        boolean states = committeePage.cheacktimeorderofproposal();
        System.out.println(states);
        Assert.assertTrue(states);

    }

//状态验证

    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                el = DRIVER.findElementByName(element);
                Element_is_exist = true;
            } catch (NoSuchElementException e) {
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        if (el != null) {
            return el;
        } else {
            el = DRIVER.findElementById(element);
            return el;
        }

    }
//            DRIVER.installApp("Tronlink.ipa");
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {

        try {
            AssetPage.closedADView = false;
            DRIVER.quit();
            DRIVER.removeApp("com.tronlink.hdwallet");
        } catch (Exception e) {
        }

    }

}
