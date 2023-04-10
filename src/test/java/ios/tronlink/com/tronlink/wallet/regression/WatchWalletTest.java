package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;

import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WatchWalletTest extends BaseTest {

  

    @Test(groups = {"P0"},description = "watch wallet add WatchWallet", alwaysRun = true)
    public void test001_addWatchWallet() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.importWatchWallet();
        Assert.assertTrue(assetPage.getWalletName().contains("WatchWallet"));
    }


    @Test(groups = {"P0"},description = "watch wallet sendTrx QRCode", alwaysRun = true)
    public void test002_homePageInfoTest() throws Exception {
        WatchWalletHelpPage helpPage = new WatchWalletHelpPage(DRIVER);
        Assert.assertTrue(isElementExist("观察钱包仅支持查看资产，如您持有当前钱包对应的离线冷钱包，可进行配对以开放更多功能权限。前往配对 >"));
        Assert.assertTrue(isElementExist("home function watch icon close"));
        helpPage.closeWatchTips();
        Assert.assertFalse(isElementExist("home function watch icon close"));
    }

    @Test(description = "test003_walletManageInfoTest", alwaysRun = true)
    public void test003_walletManageInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterMyPursePage();
        Assert.assertTrue(isElementExist("配对离线冷钱包"));
        Assert.assertTrue(isElementExist("配对离线冷钱包，可开放更多功能权限"));
        asset.pairColdWallet();
        Assert.assertTrue(isElementExist("配对冷钱包"));
        Assert.assertTrue(isElementExist("① 观察钱包目前仅支持查看钱包资产及连接至 DApp，无法发起相关操作。\n" +
                "\n" +
                "② 当您和相应地址的离线冷钱包配对后，当前钱包不仅支持查看、管理钱包资产及连接至 DApp；同时通过和离线冷钱包的交互，支持通过 App 发起相关操作，如转账、质押、解锁、投票、账户权限管理、多重签名及 DApp 签名等。"));
        asset.enterPairColdWalletPage();
        Assert.assertTrue(isElementExist("请使用冷钱包扫描二维码"));
    }

    @Test(description = "test004_watchWalletStopDoThingTest", alwaysRun = true)
    public void test004_watchWalletStopDoThingTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AdvanceFuncPage advanceFuncPage = minePage.enterAdvancePage();
        CommitteePage committee = advanceFuncPage.enterCommitteePage();
        committee.CreatProposalPage();
        committee.change0proposalStep1("0.2");
        Assert.assertTrue(isElementExist("生成交易二维码"));
        committee.enterQRCodePage();
        Assert.assertTrue(isElementExist("提示"));
        Assert.assertTrue(isElementExist("当前钱包为观察钱包，仅支持查看资产，无法进行其他操作\n" +
                "\n" +
                "如您持有当前钱包的离线冷钱包，可进行配对开放更多功能权限。"));
        Assert.assertTrue(isElementExist("前往配对 >"));
        committee.goPair();
        Assert.assertTrue(isElementExist("配对冷钱包"));
        Assert.assertTrue(isElementExist("① 观察钱包目前仅支持查看钱包资产及连接至 DApp，无法发起相关操作。\n" +
                "\n" +
                "② 当您和相应地址的离线冷钱包配对后，当前钱包不仅支持查看、管理钱包资产及连接至 DApp；同时通过和离线冷钱包的交互，支持通过 App 发起相关操作，如转账、质押、解锁、投票、账户权限管理、多重签名及 DApp 签名等。"));
        committee.enterPairColdWalletPage();
        Assert.assertTrue(isElementExist("请使用冷钱包扫描二维码"));
    }


}
