package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AddAssertPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SearchAssertPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class AddAssetsTest extends BaseTest {


//    @Test(description = "guarantee Chain in MainChain",alwaysRun = true)
//    public void test000_GuaranteeChainName() throws Exception {
//        Assert.assertTrue( Helper.guaranteeMainChain(DRIVER));
//    }

//    @Test(description = "test add assert",alwaysRun = true)
//    public void test002_addAsset() throws Exception {
//        Helper.switchToReleaseNet(DRIVER);
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//
//        AssetPage assetPage = new AssetPage(DRIVER);
//        AddAssertPage addAssertPage = assetPage.enterAddAssertPage();
//        SearchAssertPage searchAssertPage =addAssertPage.enterSearchAssertPage();
//        searchAssertPage.addAssert_input.sendKeys("888Tron");
//        searchAssertPage.openAssert();
//        addAssertPage = searchAssertPage.enterAddAssertPage();
//        assetPage = addAssertPage.enterAssetPage();
//        Helper.swipScreen(DRIVER);
//        TimeUnit.SECONDS.sleep(2);
//        assertTrue(Helper.isElementExist(assetPage.driver,"888"));
//    }
//
//
//
//    @Test(description = "test remove asset",alwaysRun = true)
//    public void test003_removeAsset() throws Exception {
//        Helper.switchToReleaseNet(DRIVER);
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//
//        AssetPage assetPage = new AssetPage(DRIVER);
//        AddAssertPage addAssertPage =  assetPage.enterAddAssertPage();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        Helper.swipScreen(DRIVER);
//        TimeUnit.SECONDS.sleep(2);
//        addAssertPage.cloeAssert();
//        assetPage =  addAssertPage.enterAssetPage();
//        Helper.swipScreen(DRIVER);
//        TimeUnit.SECONDS.sleep(2);
//        assertFalse(Helper.isElementExist(assetPage.driver,"888"));
//        Helper.switchToDevNet(DRIVER);
//
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//    }



}
