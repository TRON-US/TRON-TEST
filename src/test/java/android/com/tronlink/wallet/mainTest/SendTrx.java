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
        TimeUnit.SECONDS.sleep(6);
        transfer.btn_done.click();
        Double afterValue =  Double.valueOf(prettyString(asset.assets_count.getText()));
        System.out.println("afterSendBalance-----"+afterValue);
        Assert.assertEquals( (sendAmount + afterValue),beforeValue,0.5);

    }

//    @Parameters({"address"})
//    @Test(alwaysRun = true)
//    public void test003_redDotTest(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage transfer = asset.enterSendTrxPage();
//        Double sendAmount = getAnAmount();
//        transfer.sendTrx(Double.toString(sendAmount));
//        TimeUnit.SECONDS.sleep(5);
//        transfer.btn_done.click();
//
//        MinePage page = asset.enterMinePage();
//        Assert.assertTrue(isElementShotId("tv_bell"));
//        page.tv_bell.click();
//        TimeUnit.SECONDS.sleep(1);
//        if (page.firstAddress.getText().contains(address)){
//            Assert.assertTrue(page.firstTitle.getText().contains("收款成功"));
//            Assert.assertTrue(page.secondTitle.getText().contains("转账成功"));
//
//        }else {
//            Assert.assertTrue(page.firstTitle.getText().contains("转账成功"));
//            Assert.assertTrue(page.secondTitle.getText().contains("收款成功"));
//        }
//        Assert.assertTrue(page.firstContent.getText().contains(sendAmount.toString()));
//        Assert.assertTrue(page.secondContent.getText().contains(sendAmount.toString()));
//        DRIVER.navigate().back();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertFalse(isElementShotId("tv_bell"));
//    }

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

    @Test(enabled = true, alwaysRun = true)
    public void test007_enterFrozenSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        page.enterDepositPage();
        Assert.assertTrue(isElementTextExist("质押"));
        Assert.assertTrue(isElementTextExist("解锁"));
    }

    @Test(enabled = true, alwaysRun = true)
    public void test008_multiSignPathTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        Assert.assertTrue(transfer.tv_multi_sign.getText().contains("多重签名转账"));
        transfer.tv_multi_sign.click();
        Assert.assertTrue(transfer.tv_account.getText().contains("控制账户列表"));
    }

    @Parameters({"address"})
    @Test(enabled = true, alwaysRun = true)
    public void test009_transferAddressTextField(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(address);
        Assert.assertTrue(findByShotId("error_view").getText().contains("接收账户与转出账户相同，请确认"));

    }

    @Test(enabled = true, alwaysRun = true)
    public void test010_transferAddressContractTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(contract);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此为合约地址，请确认您要向此合约地址转账，避免造成资产损失!"));
    }

    @Test(enabled = true, alwaysRun = true)
    public void test011_transferAddressBlackTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(nileBlack);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此账户用于销毁代币，请确认您要向此账户转账！"));
    }

    @Parameters({"udid"})
    @Test(enabled = true, alwaysRun = true)
    public void test012_transferAddressFormatTest(String udid) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        keyboardSogou(udid);
        TimeUnit.SECONDS.sleep(2);
        transfer.receiveAddress_text.click();
        transfer.receiveAddress_text.sendKeys("s6HiKvTM9SPx");
        ScreenShot("keyboard");
        TimeUnit.SECONDS.sleep(3);
        DRIVER.hideKeyboard();
        TimeUnit.SECONDS.sleep(2);
        keyboardUnicode(udid);
        ScreenShot("keyboard2");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(findByShotId("error_view").getText().contains("地址格式不正确，请检查"));
    }


    @Parameters({"udid"})
    @Test(enabled = true, alwaysRun = true)
    public void test013_transferTherePartTest(String udid) throws Exception {
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

    @Test(enabled = true, alwaysRun = true)
    public void test014_resourceCoastTipShowTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        Double sendAmount = getAnAmount();
        transfer.SendTRXToConfirmView(Double.toString(sendAmount));
        Assert.assertEquals(transfer.tv_resource_consume_left.getText(),"交易所需资源");
        transfer.showResTips();
        Assert.assertEquals(transfer.tv_left.getText(),"交易所需资源 = 带宽消耗 + 能量消耗");

    }



}
