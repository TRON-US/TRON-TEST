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
        setting.enterNodePage();
        TimeUnit.SECONDS.sleep(1);
        log("已经点击过:MainChain");
        return new NodeSetHelperPage(DRIVER);
    }



    @Test(groups = {"P0"},description = "Test  add note", alwaysRun = true)
    public void test001_AddNodeSuccess() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.addNote());
    }

    @Test(description = "Test  add note  IP wrong", alwaysRun = true)
    public void test002_AddNodeIPWrong() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertEquals(page.ipwrong(),"  请输入正确的 IP 地址");
    }
    @Test(description = "Test  add note  port wrong", alwaysRun = true)
    public void test003_AddNodePortWrong() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertEquals(page.portwrong(),"  请输入正确的端口格式");
    }

    @Test(groups = {"P0"},description = "Test  note  delete", alwaysRun = true)
    public void test004_noteDeleteSuccess() throws Exception {
        NodeSetHelperPage page = enterNodeSettingPaging();
        Assert.assertTrue(page.deleteNode());
    }




}
