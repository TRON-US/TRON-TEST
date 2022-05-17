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






    @Test(enabled = true, description = "input max send number", alwaysRun = true)
    public void test005_inputMaxSendNumber() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.next_btn.click();
        Assert.assertTrue(!transfer.send_btn.isEnabled());
        transfer.tvMax_btn.click();
        Assert.assertTrue(transfer.send_btn.isEnabled());
    }

    @Parameters({"privateKey"})
    @Test(enabled = true, description = "input min send number", alwaysRun = true)
    public void test006_inputMixSendNumber(String privateKey) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.sendAllTrx("min");
        Assert.assertTrue(isElementTextExist("   转账金额需大于 0"));
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
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("1");
        transfer.send_btn.click();
        transfer.confirm_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        transfer.send_btn.click();
        Assert.assertTrue(transfer.formatErrorHits_text.getText().contains("密码错误"));
    }

    @Test(enabled = true, description = "Receiving address trim", alwaysRun = true)
    public void test009_receivingAddressTrim() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        Assert.assertTrue(transfer.next_btn.isEnabled());
    }





    @Parameters({"address"})
    @Test(enabled = true, description = "test013_confirmInfoShowTest", alwaysRun = true)
    public void test013_confirmInfoShowTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrx10Page();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.next_btn.click();
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        String content = transfer.bandwidth_text.getText();
        content = content.replace("≈","");
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 200);
        log("------\n" + findByShotId("tv_info_subtitle").getText() + "\n--------");
        Assert.assertTrue(findByShotId("tv_info_subtitle").getText().contains("0.000001") && findByShotId("tv_info_subtitle").getText().contains("tronlink_token"));
        Assert.assertTrue(findByShotId("tv_wallet_name").getText().contains("Auto-test"));
        Assert.assertTrue(findByShotId("transfer_out_address_title").getText().contains("转出账户"));
        Assert.assertTrue(findByShotId("transfer_out_name").getText().contains("当前账户"));
        Assert.assertTrue(findByShotId("receiving_address_title").getText().contains("接收账户"));
        Assert.assertTrue(findByShotId("tv_resource_consume_left").getText().contains("消耗资源"));
        Assert.assertTrue(findByShotId("transfer_out_address").getText().contains(address));
        Assert.assertTrue(findByShotId("tv_chain_name").getText().contains("Mainnet"));
    }




    @Test(enabled = true)
    public void test016_NotFreezeBandWidthSendMaxNumberToUNActive() throws Exception {
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
        TimeUnit.SECONDS.sleep(2);
        String content = transfer.fee_text.getText();
        Assert.assertTrue(content.contains("1.1") && content.contains("TRX"));

    }


    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
    public void test017_inputHaveBandWidthSendMaxNumberToUNActive() throws Exception {
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
        TimeUnit.SECONDS.sleep(2);
        String content = transfer.fee_text.getText();
        Assert.assertTrue(content.contains("≈1 TRX"));

    }

}
