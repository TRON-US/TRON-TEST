package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SendTrc20 extends BaseTest {
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

    @Test(description = "SendTrc20 success test", alwaysRun = true)
    public void test001_sendTrc20Success() throws Exception {


        AssetPage assetpage = new AssetPage(DRIVER);
        TrxPage tokenpage = assetpage.enterTrx20Page();

        double trc20Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        SendTrxPage transfer = tokenpage.enterTransferPage();
        String count = random(10, 10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        transfer.sendTrx20WithNumber(successNumber);
        TimeUnit.SECONDS.sleep(5);
        double trc20after = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println(count   + "   " + trc20Before  + " " + trc20after);
        Assert.assertTrue(trc20after +  Integer.parseInt(removeSymbol(count))  <= trc20Before);
    }



    @Test(description = "input max send number", alwaysRun = true)
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("max");
        transfer.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
    }


    @Test(description = "input mix send number", alwaysRun = true)
    public void test003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("mix");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("格式错误"));
    }


    @Test(description = "input too Much trc20 send number", alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("余额不足"));
    }
    @Test(description = "ssendaddressChanged test", alwaysRun = true)
    public void test005_sendaddressChanged() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(0).sendKeys(" ");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(transfer.transferErrorLabel.getText().contains("账户不正确"));
    }


    @Test(description = "password error",alwaysRun = true)
    public void test006_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        Helper.tapWhitePlace(transfer.driver);
        waiteTime();
        transfer.token_btn.click();
        waiteTime();
        transfer.getTrc20Token().click();
        waiteTime();
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

    @Test(description = "Check OutNumberInRecord Trx20",alwaysRun = true)
    public void test007_CheckOutNumberInRecordTrx20() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterNumberRecordPage(findString));

    }
    @Test(enabled = true, description = "TRC10 transfer history record test", alwaysRun = true)
    public void test008_transactionRecordInMePage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        Assert.assertTrue( Helper.isElementExist(transaction.driver,"触发智能合约"));
    }

    //使用一个没有足够冻结带宽的账户,点击转账会出现激活消耗的0.1trx
    @Parameters({ "udid"})
    @Test(enabled = true,description = "test008_inputNotEnoughBandWidthSendMaxNumberUNActive")
    public void test009_inputNotEnoughBandWidthSendMaxNumberUNActive(String udid) throws Exception {
        DRIVER.resetApp();
        new Helper().importFirstWallet(Helper.importType.normal,TRXandTRC10InNileprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        Float allNumber =   sepRightNumberTextToFloat(transfer.sendMaxCoinWithType("20"),"可转账数量");
        Float number =  sepLeftNumberTextToFloat(transfer.real_money.getText(),"TRX");
        Assert.assertEquals(sepLeftNumberTextToString(transfer.fee_text.getText(),"TRX"),"0");
        Assert.assertEquals(allNumber,number);
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"手续费"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"实际到账金额"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"转出账户"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"接收账户"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"消耗资源"));
        Assert.assertTrue(transfer.sendImmediatelyEnable());

    }

    //max 未激活的显示
    @Parameters({ "udid"})
    @Test(enabled = true, description = "test009_inputHaveBandWidthSendMaxNumberToUNActive")
    public void test010_inputHaveBandWidthSendMaxNumberToUNActive(String udid) throws Exception {
        DRIVER.resetApp();
        new Helper().importFirstWallet(Helper.importType.normal,haveBandwidthprivateKey,DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        Float allNumber = sepRightNumberTextToFloat(transfer.sendMaxCoinWithType("20"), "可转账数量");
        Float number = sepLeftNumberTextToFloat(transfer.real_money.getText(), "TRX");
        Assert.assertTrue(sepLeftNumberTextToFloat(transfer.fee_text.getText(), "TRX") == 0);
        Assert.assertEquals(allNumber, number);
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"手续费"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"消耗资源"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"实际到账金额"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"转出账户"));
        Assert.assertTrue(Helper.isElementExist(transfer.driver,"接收账户"));
        Assert.assertTrue(transfer.sendImmediatelyEnable());
    }
}



