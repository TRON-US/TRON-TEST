package ios.tronlink.com.tronlink.wallet.lessImport;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AdvanceFuncPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CommitteePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SRTest extends BaseTest {

    @Parameters({"witnessKey", "udid"})
    @BeforeClass(groups = {"P1"},alwaysRun = true)
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

//    @Test(groups = {"P1"},description = "send proposals", alwaysRun = true)
//    public void test_001SendProposals() throws Exception {
//        CommitteePage   committeePage = enterCommitteePage();
//        committeePage.Setuppropos.click();
//        TimeUnit.SECONDS.sleep(5);
//        String count = String.format("%.0f", Math.random() * 100000);
//        System.out.println("委员会提议修改超级代表燃烧TRX值："+count);
//        committeePage.change1proposal(count);
//        WebElement wl = committeePage.findFirstproposalWl();
//        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
//        Assert.assertTrue(Helper.contentTexts(textarray, count));
//    }
//
//    @Test(groups = {"P1"}, description = "be delete My first Proposal", alwaysRun = true)
//    public void test_002cancelAgreedProposal() throws Exception {
//        CommitteePage committeePage = enterCommitteePage();
//        committeePage.deleteAction();
//        Assert.assertTrue(committeePage.isElementExist("已取消"));
//    }



}
