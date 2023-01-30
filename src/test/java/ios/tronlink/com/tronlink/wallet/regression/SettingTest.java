package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * setting function test
 */
public class SettingTest extends BaseTest {



    @Test(alwaysRun = true)
    public void test001_SettingPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        Assert.assertTrue(isElementExist("设置"));
        Assert.assertTrue(isElementExist("钱包设置"));
        Assert.assertTrue(isElementExist("语言"));
        Assert.assertTrue(isElementExist("货币单位"));
        Assert.assertTrue(isElementExist("消息通知"));
        Assert.assertTrue(isElementExist("DApp连接管理"));
        Assert.assertTrue(isElementExist("二维码拆分"));
        Assert.assertTrue(isElementExist("在冷钱包交易中，开启此选项可将交易拆分成多个二维码，以满足大数据量的交易签名。"));
        Assert.assertTrue(isElementExist("网络设置"));
        Helper.swipScreenLitter(DRIVER);
        Assert.assertTrue(isElementExist("切换网络"));
        Assert.assertTrue(isElementExist("切换节点"));
        Assert.assertTrue(isElementExist("切换版本"));

    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test002_languageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        Assert.assertTrue(isElementExist("简体中文"));
        mine.changeLanguage("en");
        Assert.assertTrue(isElementExist("Language"));
        Assert.assertTrue(isElementExist("Currency"));
        Assert.assertTrue(isElementExist("Network Settings"));
        mine.changeLanguage("cn");
        Assert.assertTrue(isElementExist("设置"));
        Assert.assertTrue(isElementExist("钱包设置"));
        Assert.assertTrue(isElementExist("语言"));
    }

    @Parameters({"bundleId"})
    @Test(groups = {"P0"},alwaysRun = true)
    public void test003_currencyTest(String bundleId) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Assert.assertTrue(asset.cashLabel.getText().contains("$"));
        Assert.assertTrue(asset.assetsLabel.getText().contains("$"));
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        Assert.assertTrue(isElementExist("USD"));
        mine.changeCurrency("cny");
        Assert.assertTrue(isElementExist("CNY"));
        mine.navBack();
        restartApp(bundleId);
        TimeUnit.SECONDS.sleep(10);
        Assert.assertTrue(asset.assetsLabel.getText().contains("¥"));
        Assert.assertTrue(asset.cashLabel.getText().contains("¥"));
        asset.enterMinePage();
        mine.enterSettingPage();
        Assert.assertTrue(isElementExist("CNY"));
        mine.changeCurrency("usd");
        Assert.assertTrue(isElementExist("USD"));
    }

    @Test(alwaysRun = true)
    public void test004_noticeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        mine.enterNotice();
        Assert.assertTrue(isElementExist("消息通知"));
        Assert.assertTrue(isElementExist("接收动账通知"));
        Assert.assertTrue(isElementExist("系统通知权限"));
        mine.switchBtn.click();
        Assert.assertTrue(mine.titleLabel.getText().contains("关闭后将无法收到动账通知"));
        mine.closeBtn.click();
        Assert.assertTrue(mine.iosToast("已关闭动账通知"));
        TimeUnit.SECONDS.sleep(6);
        mine.switchBtn.click();
        Assert.assertTrue(mine.iosToast("已打开动账通知"));
        mine.sysNoticeBtn.click();
        Assert.assertTrue(isElementExist("TronLink"));
    }

    @Test(alwaysRun = true)
    public void test005_DAppConnectTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        mine.enterDAppManage();
        Assert.assertTrue(isElementExist("暂无连接记录"));
        Assert.assertTrue(isElementExist("DApp连接管理"));
    }

    @Test(alwaysRun = true)
    public void test006_changeNetTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        mine.enterSettingPage();
        Helper.swipScreenLitter(DRIVER);
        Assert.assertTrue(isElementExist("TRON 主网"));
        mine.changeNet();
        Assert.assertTrue(isElementExist("网络节点设置"));
        mine.shastaBtn().click();
        Assert.assertTrue(isElementExist("切换网络"));
        Assert.assertTrue(isElementExist("Shasta 网络主要用于开发及测试，不建议普通用户使用，是否确认切换？"));
        mine.confirmBtn.click();
        Assert.assertTrue(mine.iosToast("已切换到TRON Shasta 测试网"));
        TimeUnit.SECONDS.sleep(6);
//        mine.DAppChainBtn().click();
//        Assert.assertTrue(isElementExist("切换网络"));
//        Assert.assertTrue(isElementExist("切换完成后，你将只能看到 DAppChain 的数据，无法查看 TRON 主网数据，是否确认切换？"));
//        mine.confirmBtn.click();
//        Assert.assertTrue(mine.iosToast("已切换到DAppChain 主网"));
//        TimeUnit.SECONDS.sleep(6);
        mine.mainBtn().click();
        Assert.assertTrue(mine.iosToast("已切换到TRON 主网"));
    }

     @Test(alwaysRun = true)
     public void test007_NodeTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         MinePage mine = asset.enterMinePage();
         mine.enterSettingPage();
         Helper.swipScreenLitter(DRIVER);
         mine.enterNode();
         Assert.assertTrue(isElementExist("节点设置"));
         Assert.assertEquals(mine.label.getText(),"Tips：延迟越小速度越快");
         Assert.assertEquals(mine.centerBtn.getText(),"添加自定义节点");
     }

    @Test(description = "Into Mnemonic Page Test",alwaysRun = true)
    public void test008_IntoMnemonicPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        AdvanceFuncPage setting = mine.enterAdvancePage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        Assert.assertEquals(toolsPage.title.getText(),"助记词转换工具");
    }
    @Test(description = "Mnemonic Exchange Test",alwaysRun = true)
    public void test009_MnemonicExchangeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        AdvanceFuncPage setting = mine.enterAdvancePage();
        MnemonicToolsPage toolsPage = setting.enternemTools_btnPage();
        toolsPage.switchMnems();
        Assert.assertNotNull(toolsPage.assetExpectText);

    }


}
