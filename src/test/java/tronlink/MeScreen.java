package tronlink;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MeScreen extends AppiumTestCase {



    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01MeScreen() {
        importWallet(testPrivateKey);

        //Enter to Me screen
         testOperation(  meIconId,"click","Enter me screen");

        //Friend invitation
         testOperation(  meFriendInvitationId,"click","Enter to friend invitation screen");
         testOperation(  common_left,"click","back");
//         testOperation( "swipeUp","");

        //Announcement screen
         testOperation(  meAnnouncementId,"click","Enter to announcement screen");
//         testOperation( "swipeUp","");
      testOperation(  common_left,"click","back");

        //Join Our Communities
         testOperation(  meJoinOurCommunitiesId,"click","Enter to Join Our Communitites screen");
        Assert.assertTrue( isEnabled(  meJoinOurCommunitiesEnglishTelegraphGroupId));
        Assert.assertTrue( isEnabled(  meJoinOurCommunitiesChineseTelegraphGroupId));
        Assert.assertTrue( isEnabled(  meJoinOurCommunitiesTwitterId));
        Assert.assertTrue( isEnabled(  meJoinOurCommunitiesWechatId));
      testOperation(  common_left,"click","back");

        //Help Center
         testOperation(  meHelpCenterId,"click","Enter to help center screen");
      testOperation(  common_left,"click","back");

        //About Us
         testOperation(  meAboutUsId,"click","Enter to about Us screen");
         testOperation(  meAboutUsVersionLogsId,"click","Enter to version logs screen");
      testOperation(  common_left,"click","back");
         testOperation(  meAbountUsVersionVersionUpdateId,"click","Try to update version");
      testOperation(  common_left,"click","back");

    }

    @AfterClass
    public void teardown(){
        driver.resetApp();
    }

}
