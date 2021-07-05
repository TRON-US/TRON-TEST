package android.com.tronlink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AnnouncementPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FriendInvitationPage;
import android.com.wallet.pages.MinePage;


/**
 * mine page function test
 */
public class MineTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(description = "Friend invitation Test", alwaysRun = true)
    public void test001_friendInvitation() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        FriendInvitationPage friendInvitation = minePage.enterFriendInvitationPage();
        Assert.assertTrue(friendInvitation.friendInvitation_title.isDisplayed());
    }


    @Test(description = "Announcement Test", alwaysRun = true)
    public void test002_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AnnouncementPage announcement = minePage.enterAnnouncementPage();
        Assert.assertTrue(announcement.announcementPage_title.isDisplayed());
    }


}
