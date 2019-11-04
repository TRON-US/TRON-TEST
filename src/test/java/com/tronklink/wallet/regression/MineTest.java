package com.tronklink.wallet.regression;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.utils.Helper;
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
        new Helper().getSign(privateKey,DRIVER);
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


    @Test(description = "Friend invitation Test",alwaysRun = true)
    public void test001_friendInvitation() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        FriendInvitationPage friendInvitation = minePage.enterFriendInvitationPage();
        Assert.assertTrue(friendInvitation.friendInvitation_title.isDisplayed());
    }


    @Test(description = "Announcement Test",alwaysRun = true)
    public void test002_bulletin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AnnouncementPage announcement = minePage.enterAnnouncementPage();
        Assert.assertTrue(announcement.announcementPage_title.isDisplayed());
    }






}
