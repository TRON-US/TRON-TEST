package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 关于我们功能测试
 */
public class AboutUsTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
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
    @Test(description = "About Us Page Element Test",alwaysRun = true)
    public void test001_AboutUsPageElementTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterAboutUsPage();
        Assert.assertTrue(mine.nav_title.getText().contains("关于我们"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"波宝"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"版本日志"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"版本更新"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"用户协议"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"加入社群"));
    }

    @Test(description = "SecendPageEnterTest",alwaysRun = true)
    public void test002_SecendPageEnterTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterAboutUsPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(mine.nav_title.getText().contains("关于我们"));
        mine.enterLogPage();
        TimeUnit.SECONDS.sleep(6);
        Assert.assertTrue(Helper.isElementExist(mine.driver,"4.5.1(2022.01.25)"));
        Assert.assertTrue(mine.nav_title.getText().contains("版本日志"));
        mine.backBtn.click();
        mine.enterUpdatePage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(Helper.isElementExist(mine.driver,"波宝"));
        mine.enterUserAgreementPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(mine.userAgreementTitle.getText().contains("用户协议"));
        mine.userAgreementBackBtn.click();
        TimeUnit.SECONDS.sleep(1);
        mine.enterjoinCommunityPage();
        Assert.assertTrue(Helper.isElementExist(mine.driver,"Telegram 电报群"));
        Assert.assertTrue(mine.enTelegramID.getText().contains("https://t.me/TronLink"));
        Assert.assertTrue(Helper.isElementExist(mine.driver,"Twitter"));
        Assert.assertTrue(mine.tv_twitter.getText().contains("https://twitter.com/TronLinkWallet"));

    }

}
