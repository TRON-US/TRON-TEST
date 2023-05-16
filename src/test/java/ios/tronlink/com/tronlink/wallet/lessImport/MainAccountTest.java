package ios.tronlink.com.tronlink.wallet.lessImport;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainAccountTest extends BaseTest {

    @Test(groups = {"P1"},alwaysRun = true)
    public void Test001_addressBookTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine =  assetPage.enterMinePage();
        mine.enterAddressBook();
        Assert.assertTrue(isElementExist("暂无数据"));
        mine.enterBookAdd();
        Assert.assertTrue(isElementExist("添加地址"));
        Assert.assertTrue(isElementExist("nameField"));
        Assert.assertTrue(isElementExist("addressField"));
        Assert.assertTrue(isElementExist("remarkField"));
        Assert.assertTrue(isElementExist("addressClearBtn"));
    }


    @Test(groups = {"P1"},description = "import a address into notebook",alwaysRun = true)
    public void Test002_ImportAddressIsBooktest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.importSuccess(addressBookAddress);
        Assert.assertEquals(page.addressLabel.getText(),addressBookAddress);
    }

    @Test(groups = {"P1"},description = "modify address book address",alwaysRun = true)
    public void Test003_ModifyAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAddressBook();
        page.modifyNoteAddress("TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW");
        Assert.assertEquals(page.addressLabel.getText(),"TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW");
    }

    @Test(groups = {"P1"},description = "delete address book address",alwaysRun = true)
    public void Test004_DeleteAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAddressBook();
        page.deleteNoteAddress();
        Assert.assertTrue(isElementExist("暂无数据"));
    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test005_TRXProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TRON\""));
        Assert.assertTrue(isElementPredicateExist("label == \"https://tron.network/\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"6\""));
        Assert.assertTrue(isElementPredicateExist("label == \"Official Token of TRON Protocol\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2018/06/25 09:51:09\""));

    }
    @Test(groups = {"P1"},alwaysRun = true)
    public void test006_TRC10ProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx10Page();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"通证 ID\""));
        Assert.assertTrue(isElementPredicateExist("label == \"1000002\""));
        Assert.assertTrue(isElementPredicateExist("label == \"http://nileex.io/\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"nileex_TestCoin\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2019/11/27 15:11:00\""));

    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test007_TRC20ProjectInfoTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        page.enterTokenProjectDetail();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementPredicateExist("label == \"查看详细数据\""));
        Assert.assertTrue(isElementPredicateExist("label == \"Tronix(TRX)\""));
//        Assert.assertTrue(isElementPredicateExist("label == \"最新价格\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TCCcBZEdTHmS1NfFtCYfwpjBKeTv515n71\""));
        Assert.assertTrue(isElementPredicateExist("label == \"精度\""));
        Assert.assertTrue(isElementPredicateExist("label == \"TronlinknilheTRC20\""));
        Assert.assertTrue(isElementPredicateExist("label == \"2019-12-04 03:29:01\""));

    }

    @Test(groups = {"P1"}, alwaysRun = true)
    public void test008_BrowserNetTabFuncTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.openNewTab();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementExist("2"));
        page.openTab("2");
        Assert.assertTrue(page.cells.size()>1);

    }


    @Test(groups = {"P1"}, alwaysRun = true)
    public void test009_BrowserHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        BrowserPage page = asset.enterBrowserPage();
        page.backHome();
        page.inputSenSearch("sunswap.com");
        page.visitTheWWWWeb();
        page.backHome();
        page.openHistory();
        Assert.assertTrue(isElementExist("浏览记录"));
        Assert.assertTrue(isElementExist("多标签页"));
        Assert.assertTrue(isElementExist("收藏"));
    }

    @Test(groups = {"P1"}, alwaysRun = true)
    public void test010_FavoritesTest() throws Exception {
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

    @Test(groups = {"P0"},alwaysRun = true)
    public void test011_inputPercentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.enterDepositPage();
        Double total = sepLeftNumberTextToDouble(page.tv_available_amount.getText().replace("可用: ",""),"TRX");
        page.amount_percent_25.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(page.et_amount.getText()),total/4.0,1.0);
        page.amount_percent_50.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(page.et_amount.getText()),total/2.0,1.0);
        page.amount_percent_75.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(page.et_amount.getText()),3*total/4.0,1.0);
        page.amount_percent_100.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(page.et_amount.getText()),total,2.0);

    }

    @Test(groups = {"P1"},description = "Add MultiSignature Test", alwaysRun = true)
    public void test012_multiSignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        multiSignManagerPage.addPermission(signName);
        System.out.println("-------------\nmultiSignManagerPage.havedaddActive() value Is:\n"+multiSignManagerPage.havedaddActive() + "\n----------\n");
        Assert.assertTrue(multiSignManagerPage.havedaddActive());
    }

    @Test(groups = {"P1"},description = "Modify signature Test", alwaysRun = true)
    public void test013_modifySignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.modifyPermission();
        Assert.assertTrue(multiSignManagerPage.havedaddfreezeAssetPower());
    }

    @Test(groups = {"P1"},description = "delete signature Test", alwaysRun = true)
    public void test014_delSignature() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignManagerPage multiSignManagerPage = assetPage.enterMultiSignManagerPage();
        multiSignManagerPage.delSignData();
        Helper.swipRefreshScreen(multiSignManagerPage.driver);
        Assert.assertFalse(multiSignManagerPage.havedaddActive());
    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test015_sendTrxCheckNumberTotalTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        TrxPage page = asset.enterTrxPage();
        String show1 = page.balanceLabel.getText();
        String before1 = removeSymbolNoDot(show1);
        Double send = getAnAmount();
        SendTrxPage transfer = page.enterTransferPage();
        transfer.sendTrxWithNumber(String.valueOf(send));
        TimeUnit.SECONDS.sleep(8);
        transfer.finnish();
        Helper.refreshWalletScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        Helper.refreshWalletScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        page = asset.enterTrxPage();
        String show2 = page.balanceLabel.getText();
        String before2 = removeSymbolNoDot(show2);
        System.out.println("before1:" + before1 + " before2:" + before2 + " send:" + send);
        System.out.println("before:"+Double.parseDouble(before1)  + " cal:" + (Double.parseDouble(before2) + send));
        Assert.assertEquals(Double.parseDouble(before1) ,Double.parseDouble(before2) + send,0.5);
    }


    @Test(groups = {"P1"},alwaysRun = true)
    public void test016_sendTrxAndNumberHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        TrxPage page = asset.enterTrxPage();
        Double send = getAnAmount();
        SendTrxPage transfer = page.enterTransferPage();
        String sendStr = String.valueOf(send);
        transfer.sendTrxWithNumber(sendStr);
        TimeUnit.SECONDS.sleep(7);
        transfer.detail();
        TimeUnit.SECONDS.sleep(3);
        String recodeAmount = "-" + sendStr + " TRX";
        Assert.assertTrue(isElementExist(recodeAmount));
        Assert.assertTrue(isElementExist("TRX 转账"));
        Assert.assertTrue(isElementExist("交易成功"));
        Assert.assertTrue(isElementExist("查看详细数据"));
        Assert.assertTrue(isElementExist("transaction shareIcon"));

    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test017_sendTrxTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double send = getAnAmount();
        Double before = transfer.sendTrc10WithNumber(String.valueOf(send));
        System.out.println(before);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(isElementExist("交易已上链"));
        transfer.finnish();
        asset.enterSendTrxPage();
        transfer.inputReceivedAddress("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.goToSecondPage();
        transfer.selectTrc10nile();
        Double after = Double.parseDouble(removeSymbolNoDot(transfer.tokenBalance.getText())) ;
        System.out.println("before:"+before+ "after:" + after + "send:" + send);
        Assert.assertEquals(before ,after + send,0.000001);

    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test018_sendTrxHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double send = getAnAmount();
        String sendStr = String.valueOf(send);
        Double before = transfer.sendTrc10WithNumber(String.valueOf(send));
        System.out.println(before);
        TimeUnit.SECONDS.sleep(8);
        transfer.detail();
        TimeUnit.SECONDS.sleep(3);
        String recodeAmount = "-" + sendStr + " tronlink_token";
        Assert.assertTrue(isElementExist(recodeAmount));
        Assert.assertTrue(isElementExist("TRC10 通证转账"));
        Assert.assertTrue(isElementExist("交易成功"));
        Assert.assertTrue(isElementExist("查看详细数据"));
        Assert.assertTrue(isElementExist("transaction shareIcon"));
        Assert.assertTrue(isElementExist("查询链接"));
        Assert.assertTrue(isElementExist("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq"));

    }
    @Test(groups = {"P1"},alwaysRun = true)
    public void test019_sendTrxTest() throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double send = getAnAmount();
        Double before = transfer.sendTrc20WithNumber(String.valueOf(send));
        System.out.println(before);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(isElementExist("交易已上链"));
        transfer.finnish();
        asset.enterSendTrxPage();
        transfer.inputReceivedAddress("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.goToSecondPage();
        transfer.selectTrc20nile();
        Double after = Double.parseDouble(removeSymbolNoDot(transfer.tokenBalance.getText())) ;
        System.out.println(after);
        Assert.assertEquals(before ,after + send,0.000001);

    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test020_sendTrxHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double send = getAnAmount();
        String sendStr = String.valueOf(send);
        Double before = transfer.sendTrc20WithNumber(String.valueOf(send));
        System.out.println(before);
        TimeUnit.SECONDS.sleep(8);
        transfer.detail();
        TimeUnit.SECONDS.sleep(3);
        String recodeAmount = "-" + sendStr + " TRX";
        Assert.assertTrue(isElementExist(recodeAmount));
        Assert.assertTrue(isElementExist("TRC20 通证转账"));
        Assert.assertTrue(isElementExist("交易成功"));
        Assert.assertTrue(isElementExist("查看详细数据"));
        Assert.assertTrue(isElementExist("transaction shareIcon"));
        Assert.assertTrue(isElementExist("查询链接"));
        Assert.assertTrue(isElementExist("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq"));

    }
    @Test(groups = {"P1"},alwaysRun = true)
    public void test021_languageTest() throws Exception {
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
    @Test(groups = {"P1"},alwaysRun = true)
    public void test022_currencyTest(String bundleId) throws Exception {
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

//    @Test(groups = {"P1"},alwaysRun = true,description = "投票给tronChina 成功")
//    public void test023_VoteToTronChinaSRTest() throws Exception {
//        VotePage page = enterVotePage();
//        TimeUnit.SECONDS.sleep(3);
//        page.sliderToSearch();
//        page.enterSearch("china");
//        page.enterFirstSRPage();
//        TimeUnit.SECONDS.sleep(1);
//        if (page.isVoteButton()){
//            page.enterVoteStep1ToConfirm();
//        }else if(page.isModifyButton()){
//            page.enterEditVoteStep1ToConfirm();
//        }
//        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
//        Assert.assertTrue(isElementExist("投票"));
//        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
//        page.enterVoteStep2Password();
//        Assert.assertTrue(isElementExist("投票成功"));
//        Assert.assertTrue(isElementExist("完成"));
//
//    }
//
//
//    @Test(groups = {"P1"},alwaysRun = true,description = "测试取消投票+剩余1票的提示页")
//    public void test024_CancelVoteToTronChinaTest() throws Exception {
//        VotePage page = enterVotePage();
//        TimeUnit.SECONDS.sleep(3);
//        page.sliderToSearch();
//        page.enterSearch("china");
//        page.enterFirstSRPage();
//        TimeUnit.SECONDS.sleep(1);
//        if (page.isCancelButton()){
//            if (page.votedLabel.getText().equalsIgnoreCase("1")){
//                page.enterCancelVoteStep1ToConfirm();
//                Assert.assertTrue(isElementExist("因波场网络投票数量最少为 1，将为您保留当前超级代表的一票；若您希望全部取消，可解锁全部质押的 TRX。"));
//            }else {
//                page.enterCancelVoteStep1ToConfirm();
//                Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
//                Assert.assertTrue(isElementExist("取消投票"));
//                Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
//                page.enterVoteStep2Password();
//                Assert.assertTrue(isElementExist("取消投票成功"));
//            }
//
//        }else if(page.isVoteButton()){
//            page.enterVoteStep1ToConfirm();
//            page.enterVoteStep2Password();
//            Assert.assertTrue(isElementExist("投票成功"));
//        }
//    }

    @Test(groups = {"P1"},alwaysRun = true)
    public void test025_MinePageElementTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        Assert.assertTrue(isElementExist("我的钱包"));
//        Assert.assertTrue(isElementExist("message noti"));
        Assert.assertTrue(isElementExist("交易历史"));
        Assert.assertTrue(isElementExist("钱包管理"));
        Assert.assertTrue(isElementExist("地址本"));
        Assert.assertTrue(isElementExist("设置"));
        Assert.assertTrue(isElementExist("好友邀请"));
        Assert.assertTrue(isElementExist("高级功能"));
        Helper.swipScreenLitter(DRIVER);
        Assert.assertTrue(isElementExist("公告"));
        Assert.assertTrue(isElementExist("钱包指南"));
        Assert.assertTrue(isElementExist("关于我们"));
    }

    @Parameters("address")
    @Test(groups = {"P1"},alwaysRun = true)
    public void test026_ReceiptPageTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterReceiptPage();
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("扫描二维码向我付款"));
        Assert.assertTrue(isElementExist("收款"));
        Assert.assertTrue(isElementExist("Auto_test"));
        Assert.assertTrue(isElementExist("仅可向此账户转入波场系通证 (如 TRX 或 TRC10/20/721 通证)，转入其他通证将无法找回"));
        Assert.assertTrue(isElementExist(address));
        Assert.assertTrue(isElementExist("分享"));
        Assert.assertTrue(isElementExist("保存二维码"));

    }


    public  String signName = "MultiSignActive";
    public String addressBookAddress = "TTwaSwvSQur5G54vMSTiu4AEuW4cdhrQzq";

    public NotebookHelpPage enterAdrNoteBook() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        mine.enterAddressBook();
        mine.enterBookAdd();
        return new NotebookHelpPage(DRIVER);
    }

    public NotebookHelpPage enterAddressBook() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        mine.enterAddressBook();
        TimeUnit.SECONDS.sleep(2);
        return new NotebookHelpPage(DRIVER);
    }

    public VotePage enterVotePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        return vote;
    }

}
