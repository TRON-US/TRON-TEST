package android.com.tronlink.wallet.dappChain;

import android.com.utils.Helper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;

public class DappAddAssetsTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        setToDAppChain();
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


//    @Test(description = "Dapp chain add assert", alwaysRun = true)
//    public void test002_addAsset() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        AddAssertPage addAssert = asset.enterAddAssertPage();
//        SearchAssertPage searchAssert = addAssert.enterSearchAssertPage();
//        searchAssert.addAssert_input.sendKeys("1000029");
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(searchAssert.search_btn.isDisplayed());
//    }
//
//
//    @Test(description = "Dapp chain remove asset", alwaysRun = true)
//    public void test003_removeAsset() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TimeUnit.SECONDS.sleep(3);
//        AddAssertPage addAssert = asset.enterAddAssertPage();
//        addAssert.mainPageAssetManage_tab.get(1).click();
//        Assert.assertTrue(addAssert.mainPageAssetManage_tab.get(1).isDisplayed());
//    }

}
