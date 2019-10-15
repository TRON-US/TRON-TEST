package com.tronklink.wallet.regression;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;
import wallet.pages.AnnouncementPage;
import wallet.pages.AssetPage;
import wallet.pages.FriendInvitationPage;
import wallet.pages.MinePage;


/**
 * mine page function test
 */
public class MineTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        getSign(privateKey);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "Friend invitation Test")
    public void test001_friendInvitation() {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        FriendInvitationPage friendInvitation = minePage.enterFriendInvitationPage();
        Assert.assertTrue(friendInvitation.friendInvitation_title.isDisplayed());
    }


    @Test(description = "Announcement Test")
    public void test002_bulletin() {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AnnouncementPage announcement = minePage.enterAnnouncementPage();
        Assert.assertTrue(announcement.announcementPage_title.isDisplayed());
    }






}
