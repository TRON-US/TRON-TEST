package android.com.tronlink.wallet.mainTest;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendTrc10 extends Base {
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
    float sendTrxAmount;
    float beforeSendBalance;
    float afterSendBalance;

    public Double sentAmountRecoder;


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
    @Test(groups = {"P0"},enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test001_sendTrxSuccess() throws Exception {
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

    @Test(alwaysRun = true)
    public void test002_redDotTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage page = asset.enterMinePage();
        Assert.assertTrue(isElementShotId("tv_bell"));
        page.tv_bell.click();
        Assert.assertTrue(page.firstContent.getText().contains(sentAmountRecoder.toString())&&page.firstContent.getText().contains(""));
        DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertFalse(isElementShotId("tv_bell"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test003_sendTrxDetailSuccess() throws Exception {
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



    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test004_availableAmountInTransfer() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage pageToken =  asset.enterTrx10Page();
        Double avValue =  Double.parseDouble(removeSymbolString(pageToken.tv_count.getText()));
        SendTrxPage transfer = pageToken.trxSendTrxPage();
        transfer.normalSendStepOne();
        Double stepOneValue =  Double.parseDouble(removeSymbolString(transfer.balance_text.getText()));
        System.out.println(avValue);
        System.out.println(stepOneValue);
        Assert.assertEquals(avValue,stepOneValue);
    }


    @Test(enabled = true, description = "input max send number", alwaysRun = true)
    public void test005_inputMaxSendNumber() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.next_btn.click();
        Assert.assertTrue(!transfer.send_btn.isEnabled());
        transfer.tvMax_btn.click();
        Assert.assertTrue(transfer.send_btn.isEnabled());
    }

    @Parameters({"privateKey"})
    @Test(enabled = true, description = "input min send number", alwaysRun = true)
    public void test006_inputMinSendNumber(String privateKey) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.sendAllTrx("min");
        Assert.assertTrue(isElementTextExist("   转账数量需大于 0"));
    }


    @Test(enabled = true, description = "input too Much TRX send number", alwaysRun = true)
    public void test007_inputTooMuchSendNumber() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        Assert.assertTrue(isElementTextExist("   转账数量不可大于可用数量"));
    }

    @Test(enabled = true, description = "password error", alwaysRun = true)
    public void test008_passwordError() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("1");
        TimeUnit.SECONDS.sleep(2);
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        transfer.confirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        transfer.send_btn.click();
        Assert.assertTrue(transfer.formatErrorHits_text.getText().contains("密码错误"));
    }

    @Test(enabled = true, description = "Receiving address trim", alwaysRun = true)
    public void test009_receivingAddressTrim() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq" + "  ");
        Assert.assertTrue(transfer.next_btn.isEnabled());
    }


    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test010_transferTherePart() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer =  asset.enterSendTrc10Page();
        Assert.assertTrue(isElementShotId("tv_address"));
        transfer.findElementByText("地址本").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.net_error.getText().contains("暂无其他地址"));
        transfer.findElementByText("我的账户").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.net_error.getText().contains("暂无其他账户"));
    }

    @Parameters({"address"})
    @Test(enabled = true, description = "test013_confirmInfoShowTest", alwaysRun = true)
    public void test011_confirmInfoShowTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.send_btn.click();
        log("");
        TimeUnit.SECONDS.sleep(3);
        if (isElementShotId("tv_consume_resource")){
            String content = transfer.bandwidth_text.getText();
            log(content);
            if (content.length()<2){
                TimeUnit.SECONDS.sleep(5);
                content = transfer.bandwidth_text.getText();
                log(content);
            }
            String number = StringUtils.substringBeforeLast(content,"带宽");
            Assert.assertTrue(Integer.parseInt(number.trim()) > 200);
            Assert.assertTrue(findByShotId("tv_resource_consume_left").getText().contains("交易所需资源"));
        }else {
            String content = transfer.fee_text.getText();
            content = content.replace("≈","");
            String number = StringUtils.substringBeforeLast(content,"TRX");
            Assert.assertTrue(Double.parseDouble(number.trim()) > 0 && Double.parseDouble(number.trim()) < 0.5);
        }

        log("------\n" + findByShotId("tv_info_subtitle").getText() + "\n--------");
        Assert.assertTrue(findByShotId("tv_info_subtitle").getText().contains("0.000001") && findByShotId("tv_info_subtitle").getText().contains("tronlink_token"));
        Assert.assertTrue(findByShotId("tv_wallet_name").getText().contains("Auto-test"));
        Assert.assertTrue(findByShotId("transfer_out_address_title").getText().contains("转出账户"));
        Assert.assertTrue(findByShotId("transfer_out_name").getText().contains("当前账户"));
        Assert.assertTrue(findByShotId("receiving_address_title").getText().contains("接收账户"));
        Assert.assertTrue(findByShotId("transfer_out_address").getText().contains(address));
        Assert.assertTrue(findByShotId("tv_chain_name").getText().contains("Mainnet"));
    }


    @Test(enabled = true)
    public void test012_NotFreezeBandWidthSendMaxNumberToUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(notFreezenBandWidthAddressPrivateKey,DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer =  page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(unActiveAddress);
        findByShotId("search_result_view").click();
        Assert.assertTrue(isElementTextExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        String content = transfer.fee_text.getText();
        Assert.assertTrue(content.contains("1.1") && content.contains("TRX"));

    }


    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
    public void test013_inputHaveBandWidthSendMaxNumberToUNActive() throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(haveBandwidthprivateKey,DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer =  page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(unActiveAddress);
        findByShotId("search_result_view").click();
        Assert.assertTrue(isElementTextExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）。"));
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        String content = transfer.fee_text.getText();
        Assert.assertTrue(content.contains("1 TRX"));

    }

}
