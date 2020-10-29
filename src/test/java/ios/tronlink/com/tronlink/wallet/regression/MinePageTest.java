package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * 我的页面功能测试
 */
public class MinePageTest extends BaseTest {


    @Test(description = "test Me into about us", alwaysRun = true)
    public void test001_enteraboutus() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AboutUsPage aboutusPage = minePage.enterAboutUsPage();
        Assert.assertTrue(aboutusPage.title.isDisplayed());

    }

    @Test(description = "test Me into wallet", alwaysRun = true)
    public void test002_enterwallet() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage walletPage = minePage.enterMyPursePage();
        Assert.assertEquals(walletPage.title.getText(), "钱包管理");

    }

//
//    @Test(description = "test Me into FriendInvitationPage", alwaysRun = true)
////    public void test003_enterInvetus() throws Exception {
////        AssetPage assetPage = new AssetPage(DRIVER);
////        MinePage minePage = assetPage.enterMinePage();
////        FriendInvitationPage friendPage = minePage.enterFriendInvitationPage();
////        TimeUnit.SECONDS.sleep(15);
////        Assert.assertTrue(Helper.contentTexts(friendPage.textArray,"好友邀请"));
////
////    }

    @Test(description = "test Me into AnnouncementPage", alwaysRun = true)

    public void test004_enterannoun() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AnnouncementPage announPage = minePage.enterAnnouncementPage();
        TimeUnit.SECONDS.sleep(15);
        Assert.assertTrue(Helper.contentTexts(announPage.textArray,"公告"));

    }

    @Test(description = "test Me into HelpPage", alwaysRun = true)

    public void test005_enterHelpPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        HelpPage helpPage = minePage.enterHelpPage();
        TimeUnit.SECONDS.sleep(15);
        Assert.assertTrue(helpPage.helperPhone.isDisplayed());

    }


    @Test(groups = {"P0"},description = "test Me into GroupPage", alwaysRun = true)
    public void test006_enterGroupPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        waiteTime();
        minePage.aboutUs_btn.click();
        waiteTime();
        GroupPage groupPage = minePage.enterGroupPage();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(groupPage.groupInto_title.getText(), "加入社群");

    }



    @Test(description = "test Me into SettingPage", alwaysRun = true)
    public void test007_enterSettingPage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        SettingPage setPage = minePage.enterSettingPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(setPage.title.getText(), "设置");

    }





    @Test(description = "test Me into TransactionRecordPage", alwaysRun = true)
    public void test008_enterrecoder() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        TimeUnit.SECONDS.sleep(2);
        TransactionRecordPage recordPage = minePage.enterTransactionRecordPage();
//        Assert.assertTrue(recordPage.icNav_Icon.isDisplayed());
    }

    //
    @Test(description = "test TronLending enery rant", alwaysRun = true)
    public void test009_onLendingEneryEant() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        EnergyRentPage rentPage = assetPage.entereneryRantage();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(Helper.contentTexts(rentPage.textArray,"能量租赁")||Helper.contentTexts(rentPage.textArray,"TronLending"));


    }
}
