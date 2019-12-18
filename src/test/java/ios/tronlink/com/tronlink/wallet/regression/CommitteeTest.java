package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CommitteePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class CommitteeTest extends BaseTest {

    String privateKey = "2f5d032f395573491cb1e0684d684105ad5b5ff56db3f45f277e7928e791472a";
    public String myChangeCount;
    public CommitteePage perparWallet() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.addWallet_btn.click();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("importUsePrivateKey0");
        findWebElement("私钥导入").click();
        DRIVER.findElementByClassName("XCUIElementTypeTextView").sendKeys(privateKey);
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(20);
        DRIVER.findElementByClassName("XCUIElementTypeTextField").sendKeys("Committee");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(5);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("下一步").click();
        TimeUnit.SECONDS.sleep(5);
        DRIVER.findElementByClassName("XCUIElementTypeSecureTextField").sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        findWebElement("完成").click();
        TimeUnit.SECONDS.sleep(10);

        System.out.println("importUsePrivateKey1");
        TimeUnit.SECONDS.sleep(3);
        MinePage minePage = assetPage.enterMinePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return  committeePage;
    }
    public CommitteePage enterCommitteePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        CommitteePage committeePage = minePage.enterCommitteePage();
        return  committeePage;
    }

    @Test(description = "send proposals",alwaysRun = true)
    public void test_001SendProposals() throws Exception{

            CommitteePage committeePage = perparWallet();
//            CommitteePage committeePage = enterCommitteePage();
            TimeUnit.SECONDS.sleep(2);
            committeePage.Setuppropos.click();
            String count = String.format("%.0f",Math.random()*100000);
            myChangeCount = count;
            System.out.println(count);
            committeePage.change1proposal(count);
            WebElement wl = committeePage.findFirstproposalWl();
            List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
            Assert.assertTrue(Helper.contentTexts(textarray,myChangeCount));

    }
//XCUIElementTypeButton

    @Test(description = "be agreed Proposal",alwaysRun = true)
    public void test_002agreedProposal() throws Exception{

        CommitteePage committeePage = enterCommitteePage();
        committeePage.agreeAction();
        WebElement wl = committeePage.findFirstAgreedroposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertTrue(Helper.contentTexts(textarray,myChangeCount));


    }

    @Test(description = "be cancal agreed Proposal",alwaysRun = true)
    public void test_003cancalagreedProposal() throws Exception{

        CommitteePage committeePage = enterCommitteePage();
        committeePage.disagreeAction();
        WebElement wl = committeePage.findFirstAgreedroposalWl();
        List<WebElement> textarray = wl.findElements(By.className("XCUIElementTypeStaticText"));
        Assert.assertFalse(Helper.contentTexts(textarray,myChangeCount));


    }

    @Test(description = "be delete My Proposal",alwaysRun = true)
    public void test_004cancalagreedProposal() throws Exception{
        CommitteePage committeePage = enterCommitteePage();
        committeePage.deleteAction();
        WebElement wl = committeePage.findFirstproposalWl();
        Assert.assertTrue(Helper.containElement(wl,"已取消"));

    }

    @Test(description = "delete Commitee Wallet",alwaysRun = true)
    public void test_005deletecommiteewallet() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        Assert.assertTrue(myPursePage.deletWallet("Test0001"));

    }

    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                el = DRIVER.findElementByName(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        if(el != null){
            return  el;
        }else {
            el = DRIVER.findElementById(element);
            return el;
        }

    }


}
