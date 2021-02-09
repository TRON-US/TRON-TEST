package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.SearchAssertPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * add asset test
 */
public class AddAssetsTest extends Base {


//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

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


//    @Test(description = "test add assert", alwaysRun = true)
//    public void test002_addAsset() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert = asset.enterAddAssertPage();
//        SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
//        searchAssert.addAssert_input.sendKeys("1000029");
//        //searchAssert.openAssert();
//        TimeUnit.SECONDS.sleep(3);
//        //addAssert = searchAssert.enterAddAssertPage();
//        //addAssert.mainPageAssetManage_tab.get(1).click();
//        //Assert.assertTrue(addAssert.myNewAddAsset_btn.isDisplayed());
//        Assert.assertTrue(searchAssert.search_btn.isDisplayed());
//    }
//
//
//    @Test(description = "test remove asset", alwaysRun = true)
//    public void test003_removeAsset() {
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert = asset.enterAddAssertPage();
//        addAssert.mainPageAssetManage_tab.get(1).click();
//        //addAssert.removeAsset();
//        //Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
//        Assert.assertTrue(addAssert.mainPageAssetManage_tab.get(1).isDisplayed());
//    }


}
