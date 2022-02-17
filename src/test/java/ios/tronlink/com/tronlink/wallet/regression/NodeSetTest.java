package ios.tronlink.com.tronlink.wallet.regression;

import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NodeSetTest extends BaseTest {

    public NodeSetHelperPage enterNodeSettingPaging() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        nodeSetPage.enterSettingPageMainChain();
        TimeUnit.SECONDS.sleep(1);
        log("已经点击过:MainChain");
        return new NodeSetHelperPage(DRIVER);
    }
    public NodeSetHelperPage enterNodeSettingPagingDAppChain() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        NodeSetPage nodeSetPage = setting.enterNodeSetPage();
        nodeSetPage.enterSettingPageDAppChain();
        TimeUnit.SECONDS.sleep(1);
        log("已经点击过:DAppChain");
        return new NodeSetHelperPage(DRIVER);
    }

    @Test(description = "Test enter add note", alwaysRun = true)
    public void test01_enterAddnoteTest() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.enterNote());
    }

    //    添加自定义节点
    @Test(groups = {"P0"},description = "Test  add note", alwaysRun = true)
    public void test02_AddnoteSuccess() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.addNote());
    }

    @Test(description = "Test  add note  IP wrong", alwaysRun = true)
    public void test03_AddnoteIPwrong() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.ipwrong());
    }
    @Test(description = "Test  add note  port wrong", alwaysRun = true)
    public void test04_AddnotePortwrong() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.portwrong());
    }

    @Test(groups = {"P0"},description = "Test  note  delete", alwaysRun = true)
    public void test05_noteDeleteSuccess() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.deleteNode());
    }
    @Test(groups = {"P0"},description = "TestDAppChain  add note", alwaysRun = true)
    public void test06_AddnoteSuccessDAppChain() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPagingDAppChain();
        Assert.assertTrue(page.addNote());
    }
//    @Test(description = "TestDAppChain  add note  IP wrong", alwaysRun = true)
//    public void test07_AddnoteIPwrongDAppChain() throws Exception {
//        NodeSetHelperPage page = enterNodeSettingPagingDAppChain();
//        Assert.assertTrue(page.ipwrong());
//    }
//    @Test(description = "TestDAppChain  add note  port wrong", alwaysRun = true)
//    public void test08_AddnotePortwrongDAppChain() throws Exception {
//        NodeSetHelperPage page = enterNodeSettingPagingDAppChain();
//        Assert.assertTrue(page.portwrong());
//    }
//
//    @Test(groups = {"P0"},description = "Test  note  delete", alwaysRun = true)
//    public void test09_noteDeleteSuccessDAppChain() throws Exception {
//        NodeSetHelperPage page = enterNodeSettingPagingDAppChain();
//        Assert.assertTrue(page.deleteNode());
//    }




}
