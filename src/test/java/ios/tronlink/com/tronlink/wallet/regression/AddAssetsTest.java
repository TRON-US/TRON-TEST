package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AddAssertPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SearchAssertPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class AddAssetsTest extends BaseTest {





//    @Test(description = "test add assert",alwaysRun = true)
//    public void test002_addAsset() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert =  asset.enterAddAssertPage();
//        SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
//        searchAssert.addAssert_input.sendKeys("1000029");
//        searchAssert.openAssert();
//        TimeUnit.SECONDS.sleep(5);
//        addAssert = searchAssert.enterAddAssertPage();
//        addAssert.mainPageAssetManage_tab.click();
//        Assert.assertTrue(addAssert.myNewAddAsset_btn.isDisplayed());
//    }
//
//
//    @Test(description = "test remove asset",alwaysRun = true)
//    public void test003_removeAsset(){
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert =  asset.enterAddAssertPage();
//        addAssert.mainPageAssetManage_tab.click();
//        addAssert.removeAsset();
//        Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
//    }

    @Test(description = "test add assert",alwaysRun = true)
    public void test002_addAsset() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        AddAssertPage addAssertPage = assetPage.enterAddAssertPage();
        SearchAssertPage searchAssertPage =addAssertPage.enterSearchAssertPage();
        searchAssertPage.addAssert_input.sendKeys("1000027");
        searchAssertPage.keyboard_btn.click();
        searchAssertPage.turnAsset_btn.click();
        addAssertPage = searchAssertPage.enterAddAssertPage();
        assetPage = addAssertPage.enterAssetPage();

        //TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(assetPage.myNewAddAsset_text.getText().contains("testAssetIssue_1567077083240"));
    }



    @Test(description = "test remove asset",alwaysRun = true)
    public void test003_removeAsset() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        AddAssertPage addAssertPage =  assetPage.enterAddAssertPage();
        addAssertPage.mainPageAssetManage_tab.click();
        addAssertPage.hotAssetManage_tab.click();
        addAssertPage.mainPageAssetManage_tab.click();
        addAssertPage.hotAssetManage_tab.click();
        addAssertPage.mainPageAssetManage_tab.click();
        addAssertPage.hotAssetManage_tab.click();
        addAssertPage.mainPageAssetManage_tab.click();
        //TimeUnit.SECONDS.sleep(10);
        //String pageSource = DRIVER.getPageSource();
        //System.out.println(pageSource);
        System.out.println("12121212312321---"+addAssertPage.assertSwitch_btn.size());
        Assert.assertTrue(addAssertPage.assertSwitch_btn.size() >= 3);



        //addAssertPage.removeAsset();
        //Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
    }

}
