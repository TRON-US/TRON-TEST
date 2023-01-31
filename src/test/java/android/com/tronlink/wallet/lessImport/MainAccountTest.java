package android.com.tronlink.wallet.lessImport;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainAccountTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
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


    @Test(enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test001_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(6);
        transfer.btn_done.click();
        Double afterValue =  Double.valueOf(prettyString(asset.assets_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);
        Assert.assertEquals( (sendAmount + afterValue),beforeValue,0.5);

    }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test002_redDotTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double sendAmount = getAnAmount();
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(5);
        transfer.btn_done.click();
        MinePage page = asset.enterMinePage();
        Assert.assertTrue(isElementShotId("tv_bell"));
        page.tv_bell.click();
        TimeUnit.SECONDS.sleep(1);
        if (page.firstAddress.getText().contains(address)){
            Assert.assertTrue(page.firstTitle.getText().contains("收款成功"));

        }else {
            Assert.assertTrue(page.firstTitle.getText().contains("转账成功"));
        }
        Assert.assertTrue(page.firstContent.getText().contains(sendAmount.toString()));
        DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("tv_bell"));
    }

    @Test(enabled = true, alwaysRun = true)
    public void test003_sendTrxDetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(5);
        log("send: " +sendAmount );
        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRX 转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
                log(detailAmount.toString());
                Assert.assertEquals(detailAmount,sendAmount,0.4);
                Helper.swipScreenLitte(DRIVER);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2023"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

    @Test(enabled = true, alwaysRun = true)
    public void test004_availableAmountInTransfer() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double avValue =  Double.parseDouble(removeSymbolString(page.tv_balance.getText()));
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.normalSendStepOne();
        Double stepOneValue =  Double.parseDouble(removeSymbolString(transfer.balance_text.getText()));
        System.out.println(avValue);
        System.out.println(stepOneValue);
        Assert.assertEquals(avValue,stepOneValue);
    }

    @Test(enabled = true, alwaysRun = true)
    public void test005_trxFreezeValueAndUsedValueTotalTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double TotalValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        Double avValue =  Double.parseDouble(removeSymbolString(page.tv_balance.getText()));
        Double freezeValue =  Double.parseDouble(removeSymbolString(page.tv_freeze_amout.getText()));
        System.out.println("TotalValue:" + TotalValue);System.out.println("avValue:" + avValue);System.out.println("freezeValue:" + freezeValue);
        Assert.assertEquals(TotalValue,avValue + freezeValue,0.000001);
    }
    //
    @Test(enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test006_sendTrc10Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage pageToken =  asset.enterTrx10Page();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        SendTrxPage transfer =  pageToken.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        sentAmountRecoder = sendAmount;
        transfer.sendTrcTokenWithCurrent(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(3);
        transfer.btn_done.click();
        asset.enterTrx10Page();
        Double afterValue =  Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);        System.out.println("beforeSendBalance-----"+ beforeValue);        System.out.println("sendTrxAmount-----"+ sendAmount);
        Assert.assertEquals(sendAmount + afterValue,beforeValue,0.000001);
    }


    @Test(enabled = true, alwaysRun = true)
    public void test007_sendTrc10DetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer =  asset.enterSendTrc10Page();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);

        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRC10 通证转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"tronlink_token");
                Assert.assertEquals(detailAmount,sendAmount);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2023"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }

//

    public Double sentAmountRecoder;

    public SendTrxPage enterToSendTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }

    @Test(description = "Send trx success test", alwaysRun = true)
    public void test008_sendTrc20Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        sentAmountRecoder = sendAmount;
        System.out.println("sendAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        transfer.btn_done.click();
        asset.enterTrx20Page();
        Double afterValue =  Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);
        Assert.assertEquals(sendAmount + afterValue,beforeValue,0.000001);
    }

    @Test(alwaysRun = true)
    public void test009_redDotTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage page = asset.enterMinePage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(isElementShotId("tv_bell"));
        page.tv_bell.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(page.firstContent.getText().contains(sentAmountRecoder.toString()));
        DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertFalse(isElementShotId("tv_bell"));
    }

    @Test(enabled = true, alwaysRun = true)
    public void test010_sendTrc20DetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx20Page();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRC20 通证转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
                System.out.println("detailAmount-----"+ detailAmount);
                Assert.assertEquals(detailAmount,sendAmount);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2023"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
//

    //
    @Test(alwaysRun = true)
    public void test011_VotePageGetRewardTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        if (isElementShotId("tv_profit")){
            Double ward = 0.0;
            if (vote.tv_profit.getText().contains("<0.001")){
                ward = 0.001;
            }else {
                ward = sepLeftNumberTextToDouble(vote.tv_profit.getText(),"TRX");
            }
            if (ward > 0){
                vote.enterGetReword();
                if (isElementShotId("tv_confirm_title")){
                    Assert.assertEquals(vote.tv_confirm_title.getText(),"确认交易");
                    Assert.assertEquals(vote.tv_info_title.getText(),"领取收益");
                    Assert.assertTrue(vote.tv_right.getText().contains("当前账户"));
                    vote.confirmAction();
                    Assert.assertEquals(vote.tv_result.getText(),"领取收益成功");
                }else {
                    log("未到24小时场景");
                }

            }else {
                log("待领取收益数值 0 无法领取");
            }
        }else {
            log("无待领取收益该处隐藏");

        }
    }

    @Test(alwaysRun = true)
    public void test012_VoteToTronChainSRTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        vote.enterSearch("china");
        vote.enterSRPage();
        if (isElementShotId("btn_vote")){
            vote.enterVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }else if(isElementShotId("btn_voted_update")){
            vote.enterEditVoteStep1ToConfirm();
            Assert.assertTrue(vote.tv_vote_sr.getText().contains("ChinaTRON"));
            vote.enterVoteStep2Password();
            Assert.assertEquals(vote.tv_result.getText(),"投票成功");
        }
    }
    //
    @Test(description = "Add Asset Test",alwaysRun = true)
    public void test013_AddAssetTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
        page.addAssert_input.click();
        page.addAssert_input.sendKeys("axeo");
        TimeUnit.SECONDS.sleep(2);
        page.AddButton.click();
        Assert.assertTrue(assertToast("已添加到首页"));
        page.cancelSearch();
        page.navBackAction();
        TimeUnit.SECONDS.sleep(2);
        Helper.swipScreenLitte(asset.driver);
        Assert.assertTrue(asset.isElementExist("AXE"));

    }

    @Test(description = "remove Asset Test",alwaysRun = true)
    public void test014_removeAssetTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(5);
        Helper.swipScreenLitte(asset.driver);
        AndroidTouchAction act = new AndroidTouchAction(DRIVER);
        WebElement el = asset.findElementByText("AXE");
        act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(el))).perform();
        Assert.assertTrue(asset.title.getText().contains("确认将AXE移出首页吗？"));
        asset.confirm.click();
        Assert.assertFalse(asset.isElementExist("AXE"));
    }
    //
    String  addressName;
    String  addressString = "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq";

    @Test(enabled = true,description = "Add address book test", alwaysRun = true)
    public void test015_addAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.addAddressBook_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(addressBookPage.addressBook_title.getText().equals("添加地址")
                || addressBookPage.addressBook_title.getText().equalsIgnoreCase("Add Address"));


        addressBookPage.save_btn.click();
        Assert.assertTrue(addressBookPage.nameError_info.getText().equalsIgnoreCase("Please enter a name")
                || addressBookPage.nameError_info.getText().equals("请输入名称"));

        Random rand = new Random();
        addressName = "Book" + rand.nextInt(10);
        addressBookPage.addName_input.sendKeys(addressName);

        addressBookPage.save_btn.click();
        Assert.assertTrue(addressBookPage.addressError_info.getText().equalsIgnoreCase("Please enter an address")
                || addressBookPage.addressError_info.getText().equals("请输入地址"));

        addressBookPage.addAddress_input.sendKeys(addressString);
        addressBookPage.addNote_input.sendKeys(addressString);
        addressBookPage.save_btn.click();
    }

    @Test(enabled = true,description = "Delete address book test", alwaysRun = true)
    public void test016_DeleteAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.name_display.click();
        addressBookPage.deleteAddress_btn.click();
        Assert.assertTrue(!addressBookPage.addAddress_input.getText().equals(addressString));
        Assert.assertTrue(addressBookPage.scan_btn.isEnabled());

        addressBookPage.deleteBook_btn.click();
        Assert.assertTrue(addressBookPage.deleteConfirm_btn.isEnabled());
        Assert.assertTrue(addressBookPage.deleteCancle_btn.isEnabled());
        addressBookPage.deleteCancle_btn.click();
        Assert.assertTrue(addressBookPage.save_btn.isEnabled());

        addressBookPage.deleteBook_btn.click();
        addressBookPage.deleteConfirm_btn.click();
        Assert.assertTrue(addressBookPage.dataInfo_display.getText().equals("暂无数据")
                || addressBookPage.dataInfo_display.getText().equalsIgnoreCase("No data"));
    }

    //
    @Test(description = "DebugModel", alwaysRun = true)
    public void test017_DebugModelOpenTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openDebugModel();
        page.iv_menu.click();
        Assert.assertEquals(page.tv_browser_debug.getText(),"退出调试");
    }

    @Test( alwaysRun = true)
    public void test018_BrowserHistoryTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openNewTab();
        TimeUnit.SECONDS.sleep(2);
        page.inputSearch("bt.io");
        page.visitTheWeb();
        page.openHistory();
        Assert.assertTrue(isElementTextExist("浏览记录"));
        Assert.assertTrue(isElementTextExist("多标签页"));
        Assert.assertTrue(isElementTextExist("收藏"));
        Assert.assertTrue(page.tv_subtitle.getText().contains("https://bt.io/?utm_source=tronlink"));
        page.historyBackToWeb();
        page.gotoBrowserMainPage();
        page.openHistory();
        Assert.assertTrue(isElementTextExist("浏览记录"));
    }

    @Test( alwaysRun = true)
    public void test019_FavoritesTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openNewTab();
        TimeUnit.SECONDS.sleep(2);
        page.inputSearch("baidu.com");
        page.visitTheWeb();
        page.openFavorites();
        Assert.assertEquals(page.tv_no_data.getText(),"还没有收藏任何页面");
        page.favoritesBackToWeb();
        page.addFavorite();
        page.openFavorites();
        Assert.assertTrue(page.dapp_title.getText().contains("百度一下"));
    }
    //
    @Test(alwaysRun = true)
    public void test020_inputPercentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        Double total = sepLeftNumberTextToDouble(frozen.tv_available_amount.getText(),"TRX");
        frozen.amount_percent_25.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total/4.0,1.0);
        frozen.amount_percent_50.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total/2.0,1.0);
        frozen.amount_percent_75.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),3*total/4.0,1.0);
        frozen.amount_percent_100.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(removeSymbolDouble(frozen.et_amount.getText()),total,2.0);

    }

    //

    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage MultiSignManager = myPursePage.enterMultiSignManagerPage();
        return MultiSignManager;
    }

    @Test(description = "Add MutiSignature Test", alwaysRun = true)
    public void test021_multiSignAddSuccessTest() throws Exception {
        String signName = "AutoTest-" + System.currentTimeMillis();
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.enterMyPursePage();
        MultiSignManagerPage multiSignManager = assetPage.enterActiveChangePage();
        AddPermissionPage add = multiSignManager.enterAddPermissionPage();
        multiSignManager = add.addPermission(signName);
        TimeUnit.SECONDS.sleep(7);
        multiSignManager.swipLeftTimes();
        String currentName = multiSignManager.permissionName_text.getText();
        System.out.println(currentName  + "   " + signName );
        Assert.assertTrue(currentName.contains(signName));
    }
    @Test(description = "delete signature Test", alwaysRun = true)
    public void test022_delSignature() throws Exception {
        MultiSignManagerPage manager = enterMultiSignManagerPage();
        manager.swipLeftTimes();
        String signName = manager.permissionName_text.getText();
        manager.delSign();
        manager.swipLeftTimes();
        String newName =  manager.permissionName_text.getText();
        System.out.println(signName);
        System.out.println(newName);
        Assert.assertNotEquals(signName,newName);
    }

    @Test(enabled = true, description = "Mutisign history record test", alwaysRun = true)
    public void test023_transactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionTypeList.get(0).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.contains("更新账户权限"));
    }
    //
    @Parameters({"address"})
    @Test(description = "check Receipt Address", alwaysRun = true)
    public void test024_ckeckReceiptTrxAddress(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ReceiptPage receiptPage = asset.enterReceiptPage();
        Assert.assertEquals(receiptPage.tv_address.getText(), address);
        Assert.assertTrue(receiptPage.isElementExist("com.tronlinkpro.wallet:id/qr"));
        Assert.assertTrue(receiptPage.pagetitle.getText().contains("扫描二维码向我付款"));
        Assert.assertTrue(receiptPage.wallettitle.getText().contains("Auto-test"));
        receiptPage.copy_btn.click();
        Assert.assertTrue(assertToast("已复制"));
    }
    //
    String beforeLanguage;

    @Test(description = "Switch Language Test")
    public void test025_languagesSwitchTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.languane_btn.click();
        List<WebElement> languageList = setting.language_list;
        List<WebElement> selectList = setting.selected_btn;
        beforeLanguage = languageList.get(0).getText();
        Assert.assertTrue(languageList.get(0).getText().contains("English"));
        Assert.assertTrue(languageList.get(1).getText().contains("简体中文"));
        selectList.get(0).click();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test(description = "Language switch success check")
    public void test026_languagesContentTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.languane_btn.click();
        List<WebElement> languageList = setting.language_list;
        List<WebElement> selectList = setting.selected_btn;
        beforeLanguage = languageList.get(0).getText();
        Assert.assertTrue(languageList.get(0).getText().contains("English"));
        Assert.assertTrue(languageList.get(1).getText().contains("简体中文"));
        selectList.get(1).click();
        TimeUnit.SECONDS.sleep(3);

    }

    @Test(description = "Currency Test")
    public void test027_currencyTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        SettingPage setting = mine.enterSettingPage();
        setting.currency_btn.click();
        List<WebElement> currencyList = setting.currency_list;
        Assert.assertTrue(currencyList.get(0).getText().contains("CNY"));
        Assert.assertTrue(currencyList.get(1).getText().contains("USD"));
    }


    String mainNetCustomIp = "";

    @Test(alwaysRun = true)
    public void test028_ChangeNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.changNetWorkTo("Shasta");
        TimeUnit.SECONDS.sleep(6);
        sett = asset.enterMinePage().enterSettingPage();
        Assert.assertTrue(sett.tv_network_name.getText().contains("TRON Shasta 测试网"));
        sett.changNetWorkTo("MainChain");
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(isElementTextExist("MainChain"));
    }

    @Test(alwaysRun = true)
    public void test029_AddCustomNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.ll_node_root.click();
        AddCustomNodePage addCustomNodePage = sett.enterAddCustomNodePage();
        mainNetCustomIp = createRandomIp();
        log("createdIP: " + mainNetCustomIp);
        addCustomNodePage.nodeIp_input.sendKeys(mainNetCustomIp);
        addCustomNodePage.nodePort_input.sendKeys("50051");
        addCustomNodePage.saveNode();
        TimeUnit.SECONDS.sleep(8);
        boolean find = false;
        for (int i = 0 ; i<sett.node_ips.size();i++){
            String ips = sett.node_ips.get(i).getText();
            System.out.println(ips);
            if (ips.contains(mainNetCustomIp)){
                find = true;
            }
        }
        Assert.assertTrue(find);

    }


    @Test(alwaysRun = true)
    public void test030_DeleteCustomNodeTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SettingPage sett = asset.enterMinePage().enterSettingPage();
        sett.ll_node_root.click();
        AddCustomNodePage editPage =sett.enterEditCustomNodePage();
        editPage.deleteNode();
        Assert.assertTrue(assertToast("删除成功"));
    }
    //
    String oldPassword = "Test0001";
    String newPassword = "Admin1234";
    public WalletPasswordPage walletPasswordPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage myPursePage = asset.enterMyPursePage();
        WalletPasswordPage walletPasswordPage = myPursePage.enterwalletPasswordPage();
        return walletPasswordPage;
    }

    @Test(description = "Input correct password", alwaysRun = true)
    public void test031_ChangePassword() throws Exception {
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword, newPassword, newPassword);
        TimeUnit.SECONDS.sleep(5);
        MyPursePage purse = new MyPursePage(DRIVER);
        walletPasswordPage =  purse.enterwalletPasswordPage();
        walletPasswordPage.changePassword(newPassword, oldPassword, oldPassword);
        Assert.assertTrue(assertToast("钱包密码修改成功"));
    }

    @Test(enabled = true,description = "Withdraw reward transaction QRCode", alwaysRun = true)
    public void test032_WithdrawRewardQRCode() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        VotePage vote = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(2);
        if (isElementShotId("tv_profit")){
            Double ward = 0.0;
            if (vote.tv_profit.getText().contains("<0.001")){
                ward = 0.001;
            }else {
                ward = sepLeftNumberTextToDouble(vote.tv_profit.getText(),"TRX");
            }
            if (ward > 0){
                vote.enterGetReword();
                if (isElementShotId("tv_confirm_title")){
                    TimeUnit.SECONDS.sleep(2);
                    Assert.assertEquals(vote.tv_confirm_title.getText(),"确认交易");
                    Assert.assertEquals(vote.tv_info_title.getText(),"领取收益");
                    Assert.assertTrue(vote.tv_right.getText().contains("当前账户"));

                }else {
                    log("未到24小时场景");
                }

            }else {
                log("待领取收益数值 0 无法领取");
            }
        }else {
            log("无待领取收益该处隐藏");

        }

    }


    private String RPrivateKey = "0e44254c18f98c2998db2fa0e6d26e948ff27b4b5890b0306c7ab4553e109e24";
    private String RAddress = "TNzUJXEY45iuQBhmhDk5VucU9HS4CZhyKe";

    @Test(alwaysRun = true)
    public void test033_sendNFTTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        SendTrxPage sendPage = page.enterSendPage();
        SendTrxSuccessPage successPage = sendPage.sendNFT(RAddress);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertEquals(successPage.tv_result.getText(),"交易已上链");
        Assert.assertEquals(successPage.btn_done.getText(),"完成");
        Assert.assertEquals(successPage.btn_transaction_info.getText(),"查看交易详情");
        DetailPage detail = successPage.enterDetailPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(detail.tv_title.getText(), "发送");
        Assert.assertEquals(detail.name.getText(), "HC7T(HC7TTTT)");
        Assert.assertEquals(detail.tv_contract_type.getText(), "TRC721 通证转账");
        Assert.assertEquals(detail.tv_vdd.getText(), "查看详细数据");
        Assert.assertTrue(detail.iv_share.isEnabled());

    }

    @Test(alwaysRun = true)
    public void test034_AddWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        new Helper().AddMoreWalletWithPrivateKey(RPrivateKey,DRIVER);
        Assert.assertTrue(asset.tv_walletname.getText().contains("导入"));
    }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test035_SendBackNFTTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        SendTrxPage sendPage = page.enterSendPage();
        SendTrxSuccessPage successPage = sendPage.sendNFT(address);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertEquals(successPage.tv_result.getText(),"交易已上链");
        Assert.assertEquals(successPage.btn_done.getText(),"完成");
        Assert.assertEquals(successPage.btn_transaction_info.getText(),"查看交易详情");
        DetailPage detail = successPage.enterDetailPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(detail.tv_title.getText(), "发送");
        Assert.assertEquals(detail.name.getText(), "HC7T(HC7TTTT)");
        Assert.assertEquals(detail.tv_contract_type.getText(), "TRC721 通证转账");
        Assert.assertEquals(detail.tv_vdd.getText(), "查看详细数据");
        Assert.assertTrue(detail.iv_share.isEnabled());
    }

}
