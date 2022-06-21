package android.com.tronlink.wallet.mainTest;

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
        Assert.assertTrue(mine.tv_version.getText().contains("4.7.0"));
        Assert.assertTrue(isElementTextExist("波宝"));
        Assert.assertTrue(isElementTextExist("用户协议"));
        Assert.assertTrue(isElementTextExist("版本日志"));
        Assert.assertTrue(isElementTextExist("版本更新"));
        Assert.assertTrue(isElementTextExist("官方网站"));
        Assert.assertTrue(isElementTextExist("Telegram"));
        Assert.assertTrue(isElementTextExist("Twitter"));
    }

    @Test(description = "SecendPageEnterTest",alwaysRun = true)
    public void test002_SecendPageEnterTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterAboutUsPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(mine.nav_title.getText().contains("关于我们"));
        mine.enterLogPage();
        TimeUnit.SECONDS.sleep(8);
//        Assert.assertTrue(isElementTextExist("4.7.0(2022.06.02)"));
        Assert.assertTrue(mine.nav_title.getText().contains("版本日志"));
        DRIVER.navigate().back();
        mine.findElementByText("用户协议").click();
        Assert.assertTrue(mine.tv_title.getText().contains("用户协议"));
        Assert.assertTrue(isElementShotId("iv_common_right"));
        DRIVER.navigate().back();
        mine.findElementByText("版本更新").click();
        Assert.assertTrue(assertToast("当前已经是最新版本"));
    }

}
