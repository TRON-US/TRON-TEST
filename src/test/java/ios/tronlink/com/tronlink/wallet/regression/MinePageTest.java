package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;


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




    @Test(description = "test Me into FriendInvitationPage", alwaysRun = true)

    public void test003_enterInvetus() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        FriendInvitationPage friendPage = minePage.enterFriendInvitationPage();
        Assert.assertEquals(friendPage.friendInvitation_title.getText(), "好友邀请");

    }





    @Test(description = "test Me into AnnouncementPage", alwaysRun = true)

    public void test004_enterannoun() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        AnnouncementPage announPage = minePage.enterAnnouncementPage();
        Assert.assertEquals(announPage.announcementPage_title.getText(), "公告");

    }

    @Test(description = "test Me into HelpPage", alwaysRun = true)

    public void test005_enterannoun() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        HelpPage helpPage = minePage.enterHelpPage();
        Assert.assertEquals(helpPage.title.getText(), "帮助中心");

    }





    @Test(description = "test Me into GroupPage", alwaysRun = true)
    public void test006_enterannoun() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        GroupPage groupPage = minePage.enterGroupPage();
        Assert.assertEquals(groupPage.groupInto_title.getText(), "加入社群");

    }




    @Test(description = "test Me into SettingPage", alwaysRun = true)
    public void test007_enterseting() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        SettingPage setPage = minePage.enterSettingPage();
        Assert.assertEquals(setPage.title.getText(), "设置");

    }





    @Test(description = "test Me into TransactionRecordPage", alwaysRun = true)
    public void test008_enterrecoder() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        TransactionRecordPage recordPage = minePage.enterTransactionRecordPage();
//        Assert.assertTrue(recordPage.icNav_Icon.isDisplayed());
    }
}
