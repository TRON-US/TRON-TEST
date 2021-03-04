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


    @Test(description = "Add Asset Page Tip Test",alwaysRun = true)
    public void test001_AddAssetPageTipTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
        Assert.assertTrue(page.tipview.getText().contains("当有新的资产时，可进入查看"));
        page.close.click();
        Assert.assertTrue(page.et_search.getText().contains("输入通证名称/通证 ID/智能合约地址"));
        Assert.assertTrue(page.iv_search_icon.isEnabled());
        Assert.assertTrue(page.firstTab.getText().contains("首页资产管理"));
        Assert.assertTrue(page.secondTab.getText().contains("我的全部资产"));
        Assert.assertTrue(page.sort_type.getText().contains("按名称"));
        Assert.assertTrue(page.tv_common_title.getText().contains("资产"));
        Assert.assertTrue(page.accountnumbers.size() > 1);

    }


}
