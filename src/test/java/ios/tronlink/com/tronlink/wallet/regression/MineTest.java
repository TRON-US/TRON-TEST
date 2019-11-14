package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AnnouncementPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.FriendInvitationPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;



public class MineTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.launchApp();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "Friend invitation Test",alwaysRun = true)
    public void test001_friendInvitation() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        TimeUnit.SECONDS.sleep(1);
        FriendInvitationPage friendInvitation = minePage.enterFriendInvitationPage();
        Assert.assertTrue(friendInvitation.friendInvitation_title.isDisplayed());
        TimeUnit.SECONDS.sleep(3);

    }

    @Test(description = "Announcement Test",alwaysRun = true)
    public void test002_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        TimeUnit.SECONDS.sleep(1);
        AnnouncementPage announcement = minePage.enterAnnouncementPage();
        TimeUnit.SECONDS.sleep(3);

    }




}