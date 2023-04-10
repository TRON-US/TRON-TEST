package ios.tronlink.com.tronlink.wallet.regression;

import android.com.wallet.pages.DiscoverPage;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.BrowserPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CreateWalletPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BrowserTest extends BaseTest {

    @Test(description = "browserPageTest",alwaysRun = true)
    public void test001_BrowserPageInfoTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        Assert.assertTrue(isElementExist("开始您的探索吧"));
        Assert.assertTrue(isElementExist("浏览器"));
        Assert.assertTrue(isElementExist("1"));
        Assert.assertTrue(isElementExist("dapp topNav more white"));
    }


    @Test(description = "open sun io Test",alwaysRun = true)
    public void test002_BrowserMenuTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.openMenu();
        Assert.assertTrue(isElementExist("新标签页"));
        Assert.assertTrue(isElementExist("刷新"));
        Assert.assertTrue(isElementExist("浏览记录"));
        Assert.assertTrue(isElementExist("收藏夹"));
        Assert.assertTrue(isElementExist("切换钱包"));
        Assert.assertTrue(isElementExist("连接管理"));
        Assert.assertTrue(isElementExist("关闭当前标签页"));

    }

    @Test( alwaysRun = true)
    public void test003_MoreCommonTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.inputSearch("bt.io");
        page.visitTheWeb();
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(isElementExist("Earn Rewards by Staking BTT"));
    }

    @Test(groups = {"P0"}, alwaysRun = true)
    public void test004_BrowserNetTabFuncTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.openNewTab();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("2"));
        page.openTab("2");
        Assert.assertTrue(page.cells.size()>1);

    }


    @Test(groups = {"P0"}, alwaysRun = true)
    public void test005_BrowserHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.backHome();
        page.inputSenSearch("sunswap.com");
        page.visitTheWeb();
        page.backHome();
        page.openHistory();
        Assert.assertTrue(isElementExist("浏览记录"));
        Assert.assertTrue(isElementExist("多标签页"));
        Assert.assertTrue(isElementExist("收藏"));
        Assert.assertTrue(isElementExist("https://sunswap.com/?lang=zh-CN?utm_source=tronlink#/home"));
    }

    @Test(groups = {"P0"}, alwaysRun = true)
    public void test006_FavoritesTest() throws Exception {
        restartApp();
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        try{
            page.backHome();
        }catch (Exception e) {
            e.printStackTrace();
        }
        page.inputSenSearch("baidu.com");
        page.visitTheWWWWeb();
        TimeUnit.SECONDS.sleep(4);
        page.openFavorites();
        Assert.assertTrue(isElementExist("还没有收藏任何页面"));
        page.favoritesBackToWeb();
        page.addFavorite();
        page.openFavorites();
        Assert.assertTrue(isElementExist("百度一下"));
    }

    @Test( alwaysRun = true)
    public void test007_SwitchWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.approveEnterWeb();
        try{
            page.backHome();
        }catch (Exception e) {
            e.printStackTrace();
        }
        page.openSwitchWallet();
        Assert.assertTrue(isElementExist("切换钱包"));
        Assert.assertTrue(isElementExist("Auto_test"));
        page.closeSwitchWallet();
        Assert.assertTrue(isElementExist("常用"));

    }

    @Test( alwaysRun = true)
    public void test008_connectManageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.approveEnterWeb();
        page.openConnectManage();
        Assert.assertTrue(isElementExist("DApp连接管理"));
    }


}
