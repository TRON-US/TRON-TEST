package ios.tronlink.com.tronlink.wallet.regression;


import android.com.utils.Configuration;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SendTrc10 extends BaseTest {
    String successNumber;
    static String TRXandTRC10InNileprivateKey = android.com.utils.Configuration.getByPath("testng.conf")
            .getString("HaveTRXandTRC10InNile.privateKey1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");
    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }
    @Test(groups = {"P0"},alwaysRun = true)
    public void test001_sendTrxTest() throws Exception {
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

    @Test(groups = {"P0"},alwaysRun = true)
    public void test002_sendTrxHistoryTest() throws Exception {
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


    @Test(description = "input max send number",alwaysRun = true)
    public void test003_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("max");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.findSend_btn().isEnabled());
    }


    @Test(description = "input too Much TRX send number",alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("tooMuch");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("转账数量不可大于可用数量。"));
    }

    @Test(description = "Receiving Minimum Extra Trx",alwaysRun = true)
    public void test005_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("min");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("转账数量需大于 0"));
    }



}
