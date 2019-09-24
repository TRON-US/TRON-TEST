package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MeScreen {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = TronLink.driverTron;
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01MeScreen() {
        //Enter to Me screen
        TronLink.testOperation(driver,TronLink.meIconId,"click","Enter me screen");

        //Friend invitation
        TronLink.testOperation(driver,TronLink.meFriendInvitationId,"click","Enter to friend invitation screen");
        TronLink.testOperation(driver,"swipeUp","");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Announcement screen
        TronLink.testOperation(driver,TronLink.meAnnouncementId,"click","Enter to announcement screen");
        TronLink.testOperation(driver,"swipeUp","");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Join Our Communities
        TronLink.testOperation(driver,TronLink.meJoinOurCommunitiesId,"click","Enter to Join Our Communitites screen");
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.meJoinOurCommunitiesEnglishTelegraphGroupId));
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.meJoinOurCommunitiesChineseTelegraphGroupId));
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.meJoinOurCommunitiesTwitterId));
        Assert.assertTrue(TronLink.isEnabled(driver,TronLink.meJoinOurCommunitiesWechatId));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Help Center
        TronLink.testOperation(driver,TronLink.meHelpCenterId,"click","Enter to help center screen");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //About Us
        TronLink.testOperation(driver,TronLink.meAboutUsId,"click","Enter to about Us screen");
        TronLink.testOperation(driver,TronLink.meAboutUsVersionLogsId,"click","Enter to version logs screen");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        TronLink.testOperation(driver,TronLink.meAbountUsVersionVersionUpdateId,"click","Try to update version");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));




    }


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
