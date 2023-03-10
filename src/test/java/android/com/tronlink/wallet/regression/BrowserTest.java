package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DAPP_SearchPage;
import android.com.wallet.pages.DiscoverPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


/**
 * Discover page function test
 */
public class BrowserTest extends Base {

    String[] dappNameList = {"TRONSCAN","TRONLENDING","SunSwap","Bankroll","zkWrapper","JUST","SUN"};

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}

    }

    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(groups = {"P0"},description = "DebugModel", alwaysRun = true)
    public void test001_DebugModelOpenTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openDebugModel();
        page.iv_menu.click();
        Assert.assertEquals(page.tv_browser_debug.getText(),"退出调试");
    }

    @Test( alwaysRun = true)
    public void test002_BrowserMenuTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        Assert.assertTrue(isElementTextExist("常用"));
        Assert.assertTrue(isElementTextExist("收藏"));
        Assert.assertTrue(isElementTextExist("推荐应用"));
        Assert.assertEquals(page.tv_web_title.getText(),"浏览器");
        Assert.assertEquals(page.tv_search.getText(),"搜索名称或输入网址");
        Assert.assertEquals(page.tv_browser_tab.getText(),"1");
        page.iv_menu.click();
        Assert.assertTrue(isElementTextExist("新标签页"));
        Assert.assertTrue(isElementTextExist("刷新"));
        Assert.assertTrue(isElementTextExist("浏览记录"));
        Assert.assertTrue(isElementTextExist("收藏夹"));
        Assert.assertTrue(isElementTextExist("切换钱包"));
        Assert.assertTrue(isElementTextExist("连接管理"));
        Assert.assertTrue(isElementTextExist("关闭当前标签页"));
    }

    @Test( alwaysRun = true)
    public void test003_MoreCommonTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.inputSearch("sunswap.com");
        page.visitTheWeb();
        page.gotoBrowserMainPage();
        TimeUnit.SECONDS.sleep(2);
        page.normalUsed.click();
        Assert.assertTrue(page.sdv_image.size()>=1);
    }

    @Test( alwaysRun = true)
    public void test004_BrowserNetTabFuncTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openNewTab();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(page.tv_browser_tab.getText(),"2");
        page.openTabs();
        Assert.assertTrue(page.iv_tab_snapshot.size()>1);
    }

    @Test(groups = {"P0"}, alwaysRun = true)
    public void test005_BrowserHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.inputSearch("bt.io");
        page.visitTheWeb();
        page.openHistory();
        Assert.assertTrue(isElementTextExist("浏览记录"));
        Assert.assertTrue(isElementTextExist("多标签页"));
        Assert.assertTrue(isElementTextExist("收藏"));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(page.tv_subtitle.getText().contains("https://bt.io/?utm_source=tronlink"));
        page.historyBackToWeb();
        page.gotoBrowserMainPage();
        page.openHistory();
        Assert.assertTrue(isElementTextExist("浏览记录"));
    }

    @Test(groups = {"P0"}, alwaysRun = true)
    public void test006_FavoritesTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.inputSearch("baidu.com");
        page.visitTheWeb();
        page.openFavorites();
        Assert.assertEquals(page.tv_no_data.getText(),"还没有收藏任何页面");
        page.favoritesBackToWeb();
        page.addFavorite();
        page.openFavorites();
        Assert.assertTrue(page.dapp_title.getText().contains("百度一下"));
    }

    @Test( alwaysRun = true)
    public void test007_SwitchWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openSwitchWallet();
        Assert.assertEquals(page.tv_title.getText(),"切换钱包");
        Assert.assertEquals(page.et_search.getText(),"搜索您要查找的钱包名称或地址");
        Assert.assertEquals(page.tv_wallet_name.getText(),"Auto-test");
    }

    @Test( alwaysRun = true)
    public void test008_connectManageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openConnectManage();
        Assert.assertEquals(page.tv_common_title.getText(),"DApp连接管理");
        Assert.assertEquals(page.tv_text_hint.getText(),"允许访问Auto-test账户地址的DApps");
        Assert.assertEquals(page.bt_cancel.getText(),"取消连接");
    }


}
