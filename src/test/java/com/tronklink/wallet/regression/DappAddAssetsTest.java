package com.tronklink.wallet.regression;

import common.utils.Helper;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;
import wallet.pages.AddAssertPage;
import wallet.pages.AssetPage;
import wallet.pages.MinePage;
import wallet.pages.NodeSetPage;
import wallet.pages.SearchAssertPage;
import wallet.pages.SettingPage;

public class DappAddAssetsTest extends Base {
  @Parameters({"privateKey"})
  @BeforeClass(alwaysRun = true)
  public void setUpBefore(String privateKey) throws Exception {
    new Helper().getSign(privateKey,DRIVER);
    new Helper().changeDappchain();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(){
    DRIVER.closeApp();
    DRIVER.activateApp("com.tronlink.wallet");
  }

  @AfterClass
  public void tearDownAfterClass() {
    DRIVER.quit();
  }



  @Test(description = "test add assert",alwaysRun = true)
  public void test002_addAsset() throws Exception {
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
