package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.AppiumTestCase;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
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


public class YCommitteeTest extends Base {

    //    String privateKey = "2f5d032f395573491cb1e0684d684105ad5b5ff56db3f45f277e7928e791472a";

    @Parameters({"witnessKey", "udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String witnessKey, String udid) throws Exception {
        System.out.println("pk: " + witnessKey + " udid: " + udid);
//        DRIVER.closeApp();
//        log("开始移除app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
//        log("开始安装app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
//        log("开始导入ownerPrivatekey");
//        DRIVER.closeApp();
//        DRIVER.launchApp();
        new Helper().getSign(witnessKey, DRIVER);
    }
    @Parameters({"bundleId"})
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
        try {
            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();
            screenshotAction(name);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
        } catch (Exception e) {
        }

    }
    @Parameters({"bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String bundleId) throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", bundleId);
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

//    @Parameters({"udid"})
//    @AfterClass(alwaysRun = true)
//    public void tearDownAfterClass(String udid) {
//        try {
//            DRIVER.closeApp();
//            System.out.println("开始移除app");
//            AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
//            System.out.println("开始安装app");
//            AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
//            DRIVER.quit();
//        } catch (Exception e) {
//        }
//
//    }



    public CommitteePage enterCommitteePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return committeePage;
    }


    @Test(description = "send proposals", alwaysRun = true)
    public void test_001SendProposals() throws Exception {

        CommitteePage   committeePage = enterCommitteePage();
        TimeUnit.SECONDS.sleep(8);
        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(9);
        String count = String.format("%.0f", Math.random() * 100000);
        System.out.println(count);
        log("开始执行时间");
        committeePage.change1proposal(count);
        WebElement wl = committeePage.findFirstproposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, count));
    }



    @Test(description = "cheack state proposal", alwaysRun = true)
    public void test_002cheackProposalState() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String states = committeePage.getStateofproposal();
        System.out.println(states);
        Assert.assertTrue(states.contains("投票中"));
    }

    @Test(description = "be delete My first Proposal", alwaysRun = true)
    public void test_003cancalagreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.deleteAction();
        String states = committeePage.getStateofproposal();
        Assert.assertTrue(states.contains("已取消"));
    }
    @Test(description = "secendnewProposal",alwaysRun = true)
    public void test_004makeecendNewProposal() throws  Exception{

        log("second 开始执行时间");
        String count = String.format("%.0f", Math.random() * 100000);
        System.out.println(count);
        CommitteePage committeePage = enterCommitteePage();
        TimeUnit.SECONDS.sleep(6 );
        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(9);
        committeePage.change0proposal("0.123456");
        WebElement wl = committeePage.findFirstproposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, "0.123456"));
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

    @Test(description = "be delete three My Proposal", alwaysRun = true)
    public void test_007deleteagreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.deleteAction();
        String states = committeePage.getStateofproposal();
        log("second 结束执行时间");
        Assert.assertTrue(states.contains("已取消"));

    }

    @Test(description = "newProposal",alwaysRun = true)
    public void test_008makeNewProposal() throws  Exception{

        log("three 开始执行时间");
        CommitteePage committeePage = enterCommitteePage();
        TimeUnit.SECONDS.sleep(6);
        committeePage.Setuppropos.click();
        TimeUnit.SECONDS.sleep(9);
        committeePage.change2proposal("0.2");
        WebElement wl = committeePage.findFirstproposalWl();
        log("进入new我发起的投票并获取到第一个元素");
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray, "0.2"));
    }

    @Test(description = "be agreed Proposal", alwaysRun = true)
    public void test_009agreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.agreeAction();
        Assert.assertTrue(committeePage.getagreedStateofproposal());
    }

    //有没有攥成着
    @Test(description = "be dis agreed Proposal", alwaysRun = true)
    public void test_010disagreedProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        committeePage.disagreeAction();
        Assert.assertTrue(committeePage.getdisagreedStateofproposal());
        log("dis agreed时间");
    }

    //1个状态
    @Test(description = "be disagreed value Proposal", alwaysRun = true)
    public void test_011disagreedValueProposal() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        log("three 结束执行时间");
        Assert.assertTrue(committeePage.findvoteafterNumbers() == 0);
    }


    @Parameters({"witnessUrl"})
    @Test(description = "cheack proposals name", alwaysRun = true)
    public void test_012cheackProposalName(String witnessUrl) throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        String names = committeePage.getNameofproposal();
        System.out.println(names);
        Assert.assertTrue(names.contains(witnessUrl));
    }

    @Test(description = "cheack time order proposal", alwaysRun = true)
    public void test_013cheackProposalTime() throws Exception {
        CommitteePage committeePage = enterCommitteePage();
        boolean states = committeePage.cheacktimeorderofproposal();
        System.out.println(states);
        Assert.assertTrue(states);

    }


}
