package android.com.tronlink.wallet.manualTest;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DAPP_BrowerPage;
import android.com.wallet.pages.DAPP_SearchPage;
import android.com.wallet.pages.DAPP_SearchResultPage;
import android.com.wallet.pages.DiscoverPage;

import android.com.wallet.pages.MarketPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TrxPage;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * Discover page function test
 */
public class DiscoverTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(description = "Nile main chain discover page test", alwaysRun = true)
    public void test001_discoverTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();

        //尼罗河测试点
        String noteContent = discover.nile_discover_note.getText();
        System.out.println(noteContent);
        Assert.assertTrue(noteContent.contains("尼罗河"));
        Assert.assertTrue(noteContent.contains("DApp"));
        Assert.assertTrue(noteContent.contains("暂不支持"));


        //主网测试点
/*        Assert.assertTrue(discover.dapp_title.getText().contains("DAPP"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        discover.search_btn.click();
        Assert.assertTrue(discover.search_icon_in_searchPage_btn.isEnabled());*/

    }

    @Test(enabled = false,description = "Nile dapp chain discover page test",alwaysRun = true)
    public void test002_dappDiscoverTest() throws Exception {
        AssetPage asset = enterDappAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();
        try {
            TimeUnit.SECONDS.sleep(60);
            // if page display AD , cloese the AD
/*            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }*/
        } catch (Exception e){}


        //尼罗河测试点
        Assert.assertTrue(discover.dapp_title.getText().contains("DAPP"));

    }

    @Test(enabled = true,description = "Online main chain discover page test",alwaysRun = true)
    public void test003_onlineMainNetDiscoverTest() throws Exception {
        AssetPage asset = enterOnlineAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();

        //主网测试点
        Assert.assertTrue(discover.dapp_title.getText().contains("DAPP"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        discover.search_btn.click();
        Assert.assertTrue(discover.search_icon_in_searchPage_btn.isEnabled());


    }

    @Test(enabled = true,description = "Online dapp chain discover page test",alwaysRun = true)
    public void test004_onlineDappNetDiscoverTest() throws Exception {
        AssetPage asset = enterDappAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();

        //主网测试点
        Assert.assertTrue(discover.dapp_title.getText().contains("DAPP"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        discover.search_btn.click();
        Assert.assertTrue(discover.search_icon_in_searchPage_btn.isEnabled());


    }



    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter dapp page
    public AssetPage enterDappAssetPage() throws Exception {
        SettingPage set = enterSettingPage();

        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset;
    }


    //enter online page
    public AssetPage enterOnlineAssetPage() throws Exception {
        SettingPage set = enterSettingPage();
        set.version_btn.click();
        set.online_version_icon.click();

        try {
            TimeUnit.SECONDS.sleep(10);

        } catch (Exception e){}

        AssetPage asset = new AssetPage(DRIVER);
        return asset;
    }




}
