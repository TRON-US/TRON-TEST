package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AddAssertPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SearchAssertPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class DappAddAssetsTest extends BaseTest {


  @Test(description = "test add assert",alwaysRun = true)
  public void test002_addAsset() throws Exception {
    changeDappchain();
    AssetPage asset = new AssetPage(DRIVER);
    AddAssertPage addAssert =  asset.enterAddAssertPage();
    SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
    searchAssert.addAssert_input.sendKeys("1000029");
    searchAssert.openAssert();
    TimeUnit.SECONDS.sleep(5);
    addAssert = searchAssert.enterAddAssertPage();
    addAssert.mainPageAssetManage_tab.get(1).click();
    Assert.assertTrue(addAssert.myNewAddAsset_btn.isDisplayed());
  }


  @Test(description = "test remove asset",alwaysRun = true)
  public void test003_removeAsset(){
    AssetPage asset = new AssetPage(DRIVER);
    AddAssertPage addAssert =  asset.enterAddAssertPage();
    addAssert.mainPageAssetManage_tab.get(1).click();
    addAssert.removeAsset();
    Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
  }


}
