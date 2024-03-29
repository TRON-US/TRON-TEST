package ios.tronlink.com.tronlink.wallet.regression;


import android.com.utils.Configuration;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.apache.commons.lang3.StringUtils;
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

    @Test(description = "SendTrc10 success test", alwaysRun = true)
    public void test001_sendTrc10Success() throws Exception {

        AssetPage assetpage = new AssetPage(DRIVER);
        TrxPage tokenpage = assetpage.enterTrx10Page();
        double trc10Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));

        SendTrxPage transfer = tokenpage.enterTransferPage();
        String count = random(10, 10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        transfer.sendTrx10WithNumber(successNumber);
        TimeUnit.SECONDS.sleep(5);

        double trc10after = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println("   count:" +count + "   trc10Before:" + trc10Before + " trc10after:" + trc10after);
        Assert.assertTrue(trc10after + Integer.parseInt(removeSymbol(count)) <= trc10Before);
    }


    @Test(description = "input max send number", alwaysRun = true)
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("max");
        transfer.transferNow_btn.click();
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
    }


    @Test(description = "input mix send number", alwaysRun = true)
    public void test003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("mix");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("转账金额需大于 0"));
    }


    @Test(description = "input too Much trc10 send number", alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("tooMuch");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("余额不足"));
    }

    @Test(description = "ssendaddressChanged test", alwaysRun = true)
    public void test005_sendaddressChanged() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(0).sendKeys(" ");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(transfer.transferErrorLabel.getText().contains("钱包地址格式不正确"));

    }


    @Test(description = "password error",alwaysRun = true)
    public void test006_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(transfer.driver);
        waiteTime();
        transfer.selectTokenByName("tronlink_token");
        transfer.testfieldArray.get(2).sendKeys("1");
        TimeUnit.SECONDS.sleep(2);
        Helper.tapWhitePlace(transfer.driver);
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        transfer.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        TimeUnit.SECONDS.sleep(2);
        transfer.broadcastButtonClick();
        TimeUnit.SECONDS.sleep(2);
        WebElement element = transfer.driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '完成'");
        Assert.assertTrue(element.isDisplayed());
    }




    @Test(enabled = true, description = "test015_BandWidthShowTest", alwaysRun = true)
    public void test007_BandWidthShowTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        waiteTime();
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.closeKeyBoard(transfer.driver);
        transfer.selectTokenByName("tronlink_token");
        transfer.testfieldArray.get(2).sendKeys("0.000001");
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Helper.tapWhitePlace(transfer.driver);
        transfer.send_btn.click();
        waiteTime();
        String content = transfer.resourcesLabel.getText();
        String number = StringUtils.substringBeforeLast(content, "带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Parameters({ "address"})
    @Test(description = "Check OutNumberInRecord Trx10", alwaysRun = true)
    public void test008_CheckOutNumberInRecordTrx10(String address) throws Exception {
        log(successNumber);
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx10Page();
        String findString = "-" + successNumber;
        log(findString);
        Assert.assertTrue(page.enterNumberRecordPage(findString));
        Assert.assertTrue(Helper.isElementExist(page.driver,address));
    }



    @Test(enabled = true, description = "TRC10 transfer history record test", alwaysRun = true)
    public void test009_transactionRecordInMePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        log("find eleTitle: " + "转账数量：" + successNumber);
        Assert.assertTrue( Helper.isElementExist(transaction.driver,"转账数量："+successNumber));
        Assert.assertTrue( Helper.isElementExist(transaction.driver,"TRC10 通证转账"));
    }

    //使用一个没有足够冻结带宽的账户,点击转账会出现激活消耗的0.1trx
    @Parameters({ "udid"})
    @Test(enabled = true,description = "test008_inputNotEnoughBandWidthSendMaxNumberUNActive")
    public void test010_inputNotEnoughBandWidthSendMaxNumberUNActive(String udid) throws Exception {
        DRIVER.resetApp();
        new Helper().importFirstWallet(Helper.importType.normal,TRXandTRC10InNileprivateKey,DRIVER);

        SendTrxPage transfer = enterToSendTrxPage();
        String allnumber = removeSymbol(transfer.sendMaxCoinWithType("10"));
        System.out.println("allnumber : " + allnumber);
        String comfirmnumber = removeSymbol(StringUtils.substringBeforeLast(transfer.real_money.getText(),"tronlink_token").trim());
        System.out.println("comfirmnumber : " + comfirmnumber);
        Assert.assertEquals(allnumber, comfirmnumber);

//        Assert.assertEquals(sepLeftNumberTextToString(transfer.fee_text.getText(),"TRX"),"0.1");
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"手续费"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"消耗资源"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"付款账户"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"收款账户"));
        Assert.assertTrue(transfer.sendImmediatelyEnable());

    }

    //max 未激活的显示
    @Parameters({ "udid"})
    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
    public void test011_inputHaveBandWidthSendMaxNumberToUNActive(String udid) throws Exception {
        DRIVER.resetApp();
        new Helper().importFirstWallet(Helper.importType.normal,haveBandwidthprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();

        String allnumber = removeSymbol(transfer.sendMaxCoinWithType("10"));
        System.out.println("allnumber : " + allnumber);
        String comfirmnumber = removeSymbol(StringUtils.substringBeforeLast(transfer.real_money.getText(),"tronlink_token").trim());
        System.out.println("comfirmnumber : " + comfirmnumber);
        Assert.assertEquals(allnumber, comfirmnumber);
        log(transfer.fee_text.getText());
        Assert.assertTrue(sepLeftNumberTextToFloat(transfer.fee_text.getText(), "TRX") == 1);
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"手续费"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"消耗资源"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"付款账户"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"收款账户"));
        Assert.assertTrue(transfer.sendImmediatelyEnable());
    }


}
