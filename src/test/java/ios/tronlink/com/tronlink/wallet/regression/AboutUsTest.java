package ios.tronlink.com.tronlink.wallet.regression;


import android.com.utils.Configuration;
import android.com.wallet.pages.AddressBookPage;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 关于我们功能测试
 */
public class AboutUsTest extends BaseTest {




    @Test(description = "test AboutUs test",alwaysRun = true)
    public void test001_aboutUs() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"关于我们"));

    }

    @Test(description = "test version test",alwaysRun = true)
    public void test002_versionTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"v4.2.0"));
        minePage.enterAboutUsPage();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"版本日志"));
        minePage.versionNote_btn.click();
        TimeUnit.SECONDS.sleep(3);
        waiteTime();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"v4.2.0(Build 1)"));
    }

    @Test(description = "test versionUpdate test",alwaysRun = true)
    public void test003_versionUpdateTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"版本更新"));
    }

    @Test(description = "test enterGroup test" ,alwaysRun = true)
    public void test004_enterGroupTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        minePage.enterAboutUsPage();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"加入社群"));
        minePage.intoGroup_btn.click();
        TimeUnit.SECONDS.sleep(5);
        waiteTime();
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"Twitter"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"微信"));
        Assert.assertTrue(Helper.isElementExist(assetPage.driver,"Telegram电报群"));

    }


}
