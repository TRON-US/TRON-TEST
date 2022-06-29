package android.com.tronlink.wallet.mainTest;


import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SendTrxPage;

import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransactionRecordPage;
import android.com.wallet.pages.TrxPage;


import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 转帐功能测试
 */
public class SendTrx extends Base {
    static String receiverAddress = Configuration.getByPath("testng.conf")
            .getString("foundationAccount.receiverAddress");
    static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
            .getString("foundationAccount.currentMainNetBlockNum");
    static String notFreezenBandWidthAddressPrivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveTRXandTRC10InNile.privateKey1");
    static String unActiveAddressprivateKey = Configuration.getByPath("testng.conf")
            .getString("unActiveAddressInNile.privateKey1");
    static String unActiveAddress = Configuration.getByPath("testng.conf")
            .getString("unActiveAddressInNile.Address1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");
    Double sendTrxAmount;
    Double beforeSendBalance;
    Double afterSendBalance;

    String contract = "TPjkW6HiKvTM9SPhDdbb9GfCC39ajkLz6c";
    String nileBlack = "TDPJULRzVtzVjnBmZvfaTcTNQ2tsVi6XxQ";

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
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


    @Test(alwaysRun = true)
    public void test000_importAddressToTestNotice() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.importPrivateKey("20ebe9fd7964058941714551820f91afbde28698e5ac8f6f8fd63ed6716f0683");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(asset.tv_walletname.getText().contains("Received"));
    }

    @Test(alwaysRun = true)
    public void test001_switchWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.switchToWallet("Auto-test");
        Assert.assertTrue(asset.tv_walletname.getText().contains("Auto-test"));
    }


    @Test(groups = {"P0"},enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test002_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        transfer.btn_done.click();
        Double afterValue =  Double.valueOf(prettyString(asset.assets_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);
        Assert.assertEquals( (sendAmount + afterValue),beforeValue,0.5);

    }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test003_redDotTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double sendAmount = getAnAmount();
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        transfer.btn_done.click();

        MinePage page = asset.enterMinePage();
        Assert.assertTrue(isElementShotId("tv_bell"));
        page.tv_bell.click();
        TimeUnit.SECONDS.sleep(1);
        if (page.firstAddress.getText().contains(address)){
            Assert.assertTrue(page.firstTitle.getText().contains("收款成功"));
            Assert.assertTrue(page.secondTitle.getText().contains("转账成功"));

        }else {
            Assert.assertTrue(page.firstTitle.getText().contains("转账成功"));
            Assert.assertTrue(page.secondTitle.getText().contains("收款成功"));
        }
        Assert.assertTrue(page.firstContent.getText().contains(sendAmount.toString()));
        Assert.assertTrue(page.secondContent.getText().contains(sendAmount.toString()));
        DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("tv_bell"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test004_sendTrxDetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        log("send: " +sendAmount );
        for (int i = 0; i < 5; i++) {
            if(transfer.btn_transaction_info.isEnabled()){
                TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
                Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRX 转账"));
                Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
                log(detailAmount.toString());
                Assert.assertEquals(detailAmount,sendAmount);
                Assert.assertTrue(detail.transaction_time_text.getText().contains("2022"));
                break;
            }else {
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }


    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test005_availableAmountInTransfer() throws Exception {
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

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test006_trxFreezeValueAndUsedValueTotalTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double TotalValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        Double avValue =  Double.parseDouble(removeSymbolString(page.tv_balance.getText()));
        Double freezeValue =  Double.parseDouble(removeSymbolString(page.tv_freeze_amout.getText()));
        System.out.println("TotalValue:" + TotalValue);System.out.println("avValue:" + avValue);System.out.println("freezeValue:" + freezeValue);
        Assert.assertEquals(TotalValue,avValue + freezeValue,0.000001);
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test007_enterFrozenSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        page.rl_price_trx.click();
        if (isElementShotId("ll_action")){
            page.ll_action.click();
        }
        Assert.assertTrue(page.tv_main_title.getText().contains("网络资源管理"));
        Assert.assertTrue(isElementShotId("progress_energy"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test008_multiSignPathTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        Assert.assertTrue(transfer.tv_multi_sign.getText().contains("多重签名转账"));
        transfer.tv_multi_sign.click();
        Assert.assertTrue(transfer.tv_account.getText().contains("控制账户列表"));
    }

    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test009_transferAddressTextField(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(address);
        Assert.assertTrue(findByShotId("error_view").getText().contains("接收账户与转出账户相同，请确认"));

    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test010_transferAddressContractTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(contract);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此为合约地址，请确认您要向此合约地址转账，避免造成资产损失!"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test011_transferAddressBlackTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(nileBlack);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此账户地址用于销毁代币，请确认您要向此地址转账！"));
    }

    @Parameters({"udid"})
    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test012_transferAddressFormatTest(String udid) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        keyboardSogou(udid);
        transfer.receiveAddress_text.click();
        transfer.receiveAddress_text.sendKeys("TPjkW6HiKvTM9SPxhDdbb9GfCC39ajkLz6c");
        DRIVER.hideKeyboard();
        Assert.assertTrue(findByShotId("error_view").getText().contains("地址格式不正确，请检查"));
        keyboardUnicode(udid);

    }



    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test013_transferTherePartTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        Assert.assertTrue(isElementShotId("tv_address"));
        transfer.findElementByText("地址本").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.net_error.getText().contains("暂无其他地址"));
        transfer.findElementByText("我的账户").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.tv_name.getText().contains("Received"));
    }



//    @Test(enabled = true, description = "input max send number", alwaysRun = true)
//    public void test005_inputMaxSendNumber() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        transfer.next_btn.click();
//        Assert.assertTrue(!transfer.send_btn.isEnabled());
//        transfer.tvMax_btn.click();
//        Assert.assertTrue(transfer.send_btn.isEnabled());
//    }
//
//    @Parameters({"privateKey"})
//    @Test(enabled = true, description = "input min send number", alwaysRun = true)
//    public void test006_inputMixSendNumber(String privateKey) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.sendAllTrx("min");
//        Assert.assertTrue(isElementTextExist("   转账金额需大于 0"));
//    }
//
//
//    @Test(enabled = true, description = "input too Much TRX send number", alwaysRun = true)
//    public void test007_inputTooMuchSendNumber() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.sendAllTrx("tooMuch");
//        Assert.assertTrue(isElementTextExist("   转账数量不可大于可用数量。"));
//    }
//
//    @Test(enabled = true, description = "password error", alwaysRun = true)
//    public void test008_passwordError() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        transfer.next_btn.click();
//        transfer.tranferCount_text.sendKeys("1");
//        transfer.send_btn.click();
//        transfer.confirm_btn.click();
//        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
//        transfer.send_btn.click();
//        Assert.assertTrue(transfer.formatErrorHits_text.getText().contains("密码错误"));
//    }
//
//    @Test(enabled = true, description = "Receiving address trim", alwaysRun = true)
//    public void test009_receivingAddressTrim() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys("  " + "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq" + "  ");
//        Assert.assertTrue(transfer.next_btn.isEnabled());
//    }
//
//
//    @Test(groups = {"P0"},enabled = true,description = "Trx transfer success recording")
//    public void test010_transferInSuccessRecording() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage trx = asset.enterTrxPage();
//        boolean find = false;
//        for (WebElement item: trx.tranferIncount_text
//             ) {
//            if (item.getText().contains("-" + Double.toString(sendTrxAmount))){
//                find = true;
//                break;
//            }else {
//                find = false;
//            }
//        }
//        Assert.assertTrue(find);
//
//    }
//
//    @Test(groups = {"P0"},enabled = true, description = "Trx transfer history record test", alwaysRun = true)
//    public void test011_MinePageTransactionRecord() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
//        Assert.assertTrue(isElementTextExist("TRX 转账"));
//        String transactionType = transaction.descTextList.get(0).getText();
//        System.out.println(transactionType);
//        Assert.assertTrue( transactionType.contains( Double.toString(sendTrxAmount)));
//    }
//
//
//    @Parameters({"address"})
//    @Test(groups = {"P0"},enabled = true, description = "Trx transaction detail info test", alwaysRun = true)
//    public void test012_trxTransactionDetailInfo(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(0);
//        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
//        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
//        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
//        String detailPageSendAmount = transactionInfo.tv_amount.getText().split(" ")[0];
//        Assert.assertTrue(detailPageSendAmount.contains(Double.toString(sendTrxAmount)));
//        Assert.assertTrue(detailPageSendAmount.contains("-"));
//        Assert.assertTrue(transactionInfo.contractType.getText().contains("TRX 转账"));
//        Helper.swipScreenLitte(asset.driver);
//        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("2022"));
//        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
//                > Long.valueOf(currentMainNetBlockNum));
//        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
//        Assert.assertTrue(transactionInfo.resouce_cost.getText().contains("带宽") && transactionInfo.resouce_cost.getText().contains("能量"));
//        Assert.assertTrue(transactionInfo.send_copy.isDisplayed());
//        Assert.assertTrue(transactionInfo.revice_copy.isDisplayed());
//        Assert.assertTrue(transactionInfo.hash_copy.isDisplayed());
//    }
//
//
//    @Parameters({"address"})
//    @Test(enabled = true, description = "test013_confirmInfoShowTest", alwaysRun = true)
//    public void test013_confirmInfoShowTest(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        transfer.next_btn.click();
//        transfer.tranferCount_text.sendKeys("0.000001");
//        transfer.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String content = transfer.bandwidth_text.getText();
//        content = content.replace("≈","");
//        String number = StringUtils.substringBeforeLast(content,"带宽");
//        Assert.assertTrue(Integer.parseInt(number.trim()) > 200);
//        log("------\n" + findByShotId("tv_info_subtitle").getText() + "\n--------");
//        Assert.assertTrue(findByShotId("tv_info_subtitle").getText().contains("0.000001") && findByShotId("tv_info_subtitle").getText().contains("TRX"));
//        Assert.assertTrue(findByShotId("tv_wallet_name").getText().contains("Auto-test"));
//        Assert.assertTrue(findByShotId("transfer_out_address_title").getText().contains("转出账户"));
//        Assert.assertTrue(findByShotId("transfer_out_name").getText().contains("当前账户"));
//        Assert.assertTrue(findByShotId("receiving_address_title").getText().contains("接收账户"));
//        Assert.assertTrue(findByShotId("tv_resource_consume_left").getText().contains("消耗资源"));
//        Assert.assertTrue(findByShotId("transfer_out_address").getText().contains(address));
//        Assert.assertTrue(findByShotId("tv_chain_name").getText().contains("Mainnet"));
//
//    }
//
//
//
//    @Test(enabled = true)
//    public void test014_uNActiveAddressToSend() throws Exception {
//        DRIVER.resetApp();
//        new Helper().getSign(unActiveAddressprivateKey,DRIVER);
//        AssetPage asset = new AssetPage(DRIVER);
//        asset.enterSendTrxPage();
//        Assert.assertTrue(isElementTextExist("当前账户未激活，无法转账。请转入TRX 激活账户。"));
//        Assert.assertTrue(isElementTextExist("发起多签转账"));
//        Assert.assertTrue(isElementTextExist("确认"));
//    }
//
//
//    @Test(enabled = true)
//    public void test015_uNActiveAddressActionFunctionTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        asset.enterSendTrxPage();
//        asset.findElementByText("发起多签转账").click();
//        asset.findElementByText("多重签名转账").click();
//        Assert.assertTrue(asset.tv_main_title.getText().contains("多重签名转账"));
//    }
//
//    @Test(enabled = true)
//    public void test016_NotFreezeBandWidthSendMaxNumberToUNActive() throws Exception {
//        DRIVER.resetApp();
//        new Helper().getSign(notFreezenBandWidthAddressPrivateKey,DRIVER);
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys(unActiveAddress);
//        findByShotId("search_result_view").click();
//        Assert.assertTrue(isElementTextExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
//        transfer.next_btn.click();
//        transfer.tranferCount_text.sendKeys("0.000001");
//        transfer.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String content = transfer.fee_text.getText();
//        Assert.assertTrue(content.contains("1.1") && content.contains("TRX"));
//
//    }
//
//
//    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
//    public void test017_inputHaveBandWidthSendMaxNumberToUNActive() throws Exception {
//        DRIVER.resetApp();
//        new Helper().getSign(haveBandwidthprivateKey,DRIVER);
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys(unActiveAddress);
//        findByShotId("search_result_view").click();
//        Assert.assertTrue(isElementTextExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
//        transfer.next_btn.click();
//        transfer.tranferCount_text.sendKeys("0.000001");
//        transfer.send_btn.click();
//        TimeUnit.SECONDS.sleep(2);
//        String content = transfer.fee_text.getText();
//        Assert.assertTrue(content.contains("≈1 TRX"));
//
//    }



}
