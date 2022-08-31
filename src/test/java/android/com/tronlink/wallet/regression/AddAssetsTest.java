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

    @Test(description = "Enter Asset Page Test",alwaysRun = true)
    public void test001_EnterAddAssetPageTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
        Assert.assertTrue(page.nav_title.getText().contains("我的全部资产"));
        Assert.assertTrue(page.right_title.getText().contains("自定义通证"));
        Assert.assertTrue(page.hidasset.getText().contains("隐藏小额资产"));
        Assert.assertTrue(page.right_title.getText().contains("自定义通证"));
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
        TimeUnit.SECONDS.sleep(5);
        Helper.swipScreenLitte(asset.driver);
        AndroidTouchAction act = new AndroidTouchAction(DRIVER);
        WebElement el = asset.findElementByText("AXE");
        act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(el))).perform();
        Assert.assertTrue(asset.title.getText().contains("确认将AXE移出首页吗？"));
        asset.confirm.click();
        Assert.assertFalse(asset.isElementExist("AXE"));
    }


}
