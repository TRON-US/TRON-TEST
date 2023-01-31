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

import java.util.concurrent.TimeUnit;


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
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
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
    public void test001_friendInvitationTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        FriendInvitationPage friendInvitation = minePage.enterFriendInvitationPage();
        Assert.assertTrue(friendInvitation.friendInvitation_title.getText().contains("好友邀请"));
    }


//    @Test(description = "Announcement Test", alwaysRun = true)
//    public void test002_AnnouncementTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage minePage = asset.enterMinePage();
//        AnnouncementPage announcement = minePage.enterAnnouncementPage();
//        Assert.assertTrue(announcement.tv_web_title.getText().contains("公告中心 – 帮助中心"));
//    }

     @Test(alwaysRun = true)
     public void test003_publicWalletManageTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         MinePage minePage = asset.enterMinePage();
         Assert.assertTrue( isElementTextExist("公开账户管理"));
         minePage.wallet_manager.click();
         Assert.assertTrue(minePage.nav_title.getText().contains("钱包详情"));
     }

    @Test(alwaysRun = true)
    public void test004_shieldWalletManageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        Assert.assertTrue(minePage.findByShotId("tv_shield_wallet").getText().contains("匿名账户管理"));
        minePage.findByShotId("tv_shield_wallet").click();
        Assert.assertTrue(minePage.findByShotId("content").getText().contains("当前无匿名账户，是否新建？"));
        Assert.assertTrue(isElementShotId("btn_cancel"));
        Assert.assertTrue(isElementShotId("btn_confirm"));
        minePage.findByShotId("btn_cancel").click();
        Assert.assertFalse(isElementTextExist("创建"));
    }

     @Test(alwaysRun = true)
     public void test005_addressbookTests() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         MinePage minePage = asset.enterMinePage();
         Assert.assertTrue(isElementTextExist("地址本"));
         minePage.findElementByText("地址本").click();
         Assert.assertTrue(minePage.nav_title.getText().contains("地址本"));

     }

      @Test(alwaysRun = true)
      public void test006_advanceTest()  throws Exception{
          AssetPage asset = new AssetPage(DRIVER);
          MinePage minePage = asset.enterMinePage();
          Assert.assertTrue(isElementTextExist("高级功能"));
          minePage.findElementByText("高级功能").click();
          Assert.assertTrue(minePage.nav_title.getText().contains("高级功能"));
      }


    @Test(alwaysRun = true)
    public void test008_helpingTest()  throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        Helper.swipScreen(minePage.driver);
        Assert.assertTrue(isElementTextExist("帮助中心"));
        minePage.findElementByText("帮助中心").click();
        Assert.assertTrue(minePage.nav_title.getText().contains("帮助中心"));
    }

     @Test(alwaysRun = true)
     public void test009_bellTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         MinePage minePage = asset.enterMinePage();
         findByShotId("rl_bell").click();
         Assert.assertTrue(minePage.nav_title.getText().contains("消息中心"));

     }

    @Test(description = " test010_minePageNavTest" , alwaysRun = true)
    public void test010_minePageNavTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterMinePage();
        Assert.assertTrue(isElementShotId("ic_my_wallet"));
        Assert.assertTrue(isElementTextExist("我的钱包"));
    }

}
