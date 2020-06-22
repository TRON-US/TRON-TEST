package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 转帐功能测试
 */
public class SendTrx extends BaseTest {
    String successNumber;
    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


    @Test(groups = {"P0"},description = "SendTrx success test",alwaysRun = true)
    public void test001_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        String count = random(10,10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        TrxPage tokenpage = transfer.sendTrxWithNumber(successNumber);
        double trcBefore = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        transfer.back_bt.click();//返回到首页资产页
        waiteTime();
        tokenpage = asset.enterTrxPage();
        double trcafter = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println(count   + "   " + trcBefore  + " " + trcafter);
        Assert.assertTrue(trcafter + Integer.parseInt(removeSymbol(count)) <= trcBefore);
    }



    @Test(description = "input error address to Receiving address",alwaysRun = true)
    public void test002_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.sendKey(transfer.testfieldArray.get(1),"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.reciptErrorLabel.getText().contains("地址格式不正确"));

    }



    @Test(description = "input not active Receiving address",alwaysRun = true)
    public void test003_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.sendKey(transfer.testfieldArray.get(1),"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.reciptErrorLabel.getText().contains("地址未激活"));


    }



    @Parameters({"address"})
    @Test(groups = {"P0"},description = "input Receiving address same as send address",alwaysRun = true)
    public void test004_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(1),address);
        Helper.tapWhitePlace(transfer.driver);
//        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"地址不能相同"));
        Assert.assertTrue(transfer.reciptErrorLabel.getText().contains("地址不能相同"));

    }


    @Test(description = "input Null Receiving address",alwaysRun = true)
    public void test005_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.sendKey(transfer.testfieldArray.get(2),"1");
        TimeUnit.SECONDS.sleep(2);
        Assert.assertFalse(transfer.findsend_btn().isEnabled()); //send btn can click
    }



    @Test(description = "input max send number",alwaysRun = true)
    public void test006_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.findsend_btn().isEnabled());
    }



    @Test(description = "input mix send number",alwaysRun = true)
    public void test007_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("格式错误"));
    }



    @Test(description = "input too Much TRX send number",alwaysRun = true)
    public void test008_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("余额不足"));
    }


    @Test(description = "password error",alwaysRun = true)
    public void test009_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(2);
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



    @Test(description = "Receiving address trim",alwaysRun = true)
    public void test010_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.reciptErrorLabel.getText().contains("地址格式不正确"));

    }



    @Test(description = "Receiving Minimum Trx",alwaysRun = true)
    public void test011_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(2).sendKeys("0.000001");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        transfer.transferNow_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
    }

    @Test(description = "input Privatekey to Receiving address",alwaysRun = true)
    public void test012_inputPrivatekey() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.sendKey(transfer.testfieldArray.get(1),"324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.reciptErrorLabel.getText().contains("地址格式不正确"));
    }


    @Test(description = "Receiving Minimum Extra Trx",alwaysRun = true)
    public void test013_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        TimeUnit.SECONDS.sleep(2);
        transfer.testfieldArray.get(2).sendKeys("0.0000001");
        Helper.tapWhitePlace(transfer.driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(transfer.amountErrorLabel.getText().contains("0.000001"));

    }


    @Test(groups = {"P0"},description = "Check OutNumberInRecord Trx",alwaysRun = true)
    public void test014_CheckOutNumberInRecordTrx() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrxPage();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterNumberRecordPage(findString));

    }

}
