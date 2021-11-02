package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AddressBookPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SearchAssertPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

/**
 * add asset test
 */
public class AddAssetsTest extends Base {


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

    @Test(description = "Add Asset Page Test",alwaysRun = true)
    public void test001_AddAssetPageTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
        Assert.assertTrue(page.isTextExist("我的全部资产"));
        Assert.assertTrue(page.isTextExist("TRX"));
        page.findElementByText("收藏品").click();
        Assert.assertTrue(page.isTextExist("TNFT"));

    }
    @Test(description = "Add Asset Test",alwaysRun = true)
    public void test002_AddAssetTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
        page.addAssert_input.click();
        page.addAssert_input.sendKeys("axeo");
        page.AddButton.click();
        Assert.assertTrue(assertToast("已添加到首页"));
    }

    @Test(description = "remove Asset Test",alwaysRun = true)
    public void test003_removeAssetTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AndroidTouchAction act = new AndroidTouchAction(DRIVER);
        WebElement el = asset.findElementByText("AXE");
        act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(el))).perform();
        Assert.assertTrue(asset.isTextExist("确认将AXE移出首页吗？"));
        asset.findElementByText("移出").click();
    }


}
