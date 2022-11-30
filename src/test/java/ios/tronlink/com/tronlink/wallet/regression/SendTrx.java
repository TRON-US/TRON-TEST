package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 转帐功能测试
 */
public class SendTrx extends BaseTest {
    String successNumber;

    static String TRXandTRC10InNileprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveTRXandTRC10InNile.privateKey1");
    static String haveBandwidthprivateKey = Configuration.getByPath("testng.conf")
            .getString("HaveBandWidthInNile.privateKey1");


    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


     @Test(alwaysRun = true)
     public void test001_sendTrxCheckNumberTotalTest() throws Exception {
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

    @Test(alwaysRun = true)
    public void test002_sendTrxAndNumberHistoryTest() throws Exception {
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


     @Test(alwaysRun = true)
     public void test003_SendPageOneTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage page = asset.enterSendTrxPage();
        Assert.assertTrue(isElementExist("地址本"));
        Assert.assertTrue(isElementExist("我的账户"));
        Assert.assertTrue(isElementExist("最近转账"));
        Assert.assertTrue(isElementExist("多重签名转账"));
        Assert.assertTrue(isElementExist("转账 (1/2)"));
        Assert.assertTrue(isElementExist("transfer address scan"));
        Assert.assertTrue(isElementExist("接收账户"));
        Assert.assertTrue(isElementExist("粘贴"));
        Assert.assertTrue(isElementExist("下一步"));
        page.addressBook.click();
        Assert.assertTrue(isElementExist("暂无其他地址"));
        page.myAccount.click();
        Assert.assertTrue(isElementExist("暂无其他账户"));
     }

    @Test(alwaysRun = true)
    public void test004_SendPageOneAddressErrorTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage page = asset.enterSendTrxPage();
        page.TextView.sendKeys("notAAddress");
        closeKeyBoard();
        Assert.assertTrue(isElementExist(" 账户地址格式不正确，请检查"));
    }

    @Test(alwaysRun = true)
    public void test005_SendPageOneAddressAddBookTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage page = asset.enterSendTrxPage();
        page.TextView.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        closeKeyBoard();
        Assert.assertTrue(isElementExist("加入到地址本"));
    }

    @Test(alwaysRun = true)
    public void test006_SendPageOneAddressAddBookFuncTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage page = asset.enterSendTrxPage();
        page.TextView.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        closeKeyBoard();
        page.addBook.click();
        Assert.assertTrue(isElementExist("加入到地址本"));
        Assert.assertTrue(isElementExist("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq"));
        page.addBookName("Book");
        Assert.assertTrue(page.iosToast("保存成功"));
    }

    @Test(alwaysRun = true)
    public void test007_SendPageOneAddressBookAmountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        String avNumber = page.leftAmountLabel.getText();
        SendTrxPage transfer = page.enterTransferPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.addressBook.click();
        transfer.firstCell.click();
        transfer.goToSecondPage();
        Assert.assertTrue(isElementExist("TRX"));
        Assert.assertTrue( transfer.tokenBalance.getText().contains(avNumber));
    }


    @Test(description = "input not active Receiving address",alwaysRun = true)
    public void test008_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.enterSendTextField("TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        Assert.assertTrue(isElementExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）"));

    }

    @Parameters({"address"})
    @Test(groups = {"P0"},description = "input Receiving address same as send address",alwaysRun = true)
    public void test009_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.enterSendTextField(address);
        Assert.assertTrue(isElementExist(" 接收账户与转出账户相同，请确认。"));
    }


    @Test(description = "input max send number",alwaysRun = true)
    public void test010_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.findSend_btn().isEnabled());
    }


    @Test(description = "input too Much TRX send number",alwaysRun = true)
    public void test011_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist("转账数量不可大于可用数量。"));
    }

    @Test(description = "Receiving address trim",alwaysRun = true)
    public void test012_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.TextView.sendKeys("  " + "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq" + "  ");
        closeKeyBoard();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(isElementExist(" 账户地址格式不正确，请检查"));
    }


    @Test(description = "Receiving Minimum Extra Trx",alwaysRun = true)
    public void test013_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.TextView.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        closeKeyBoard();
        transfer.goToSecondPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.TextField.sendKeys("0.0000001");
        closeKeyBoard();
        Assert.assertTrue(isElementExist("转账数量需大于 0"));
    }


     @Test(alwaysRun = true)
     public void test014_AmountTotal() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         TrxPage page = asset.enterTrxPage();
         TimeUnit.SECONDS.sleep(2);
         String avNumber = removeSymbolNoDot(page.leftAmountLabel.getText());
         String fzNumber = removeSymbolNoDot(page.rightAmountLabel.getText());
         String totalNumber = removeSymbolNoDot(page.trxTotal_text.getText());
         Assert.assertEquals(Double.parseDouble(fzNumber)+Double.parseDouble(avNumber),Double.parseDouble(totalNumber),0.000001);

     }


}
