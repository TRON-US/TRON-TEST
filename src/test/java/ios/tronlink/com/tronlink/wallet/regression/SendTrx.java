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

    public SendTrxPage enterToSendTrxPage(){
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }



    @Test(description = "input Privatekey to Receiving address",alwaysRun = true)
    public void tsst001_inputPrivatekey() throws Exception {
        Helper.guaranteeMainChain(DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(1),"324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"收款地址格式不正确"));
    }



    @Test(description = "input error address to Receiving address",alwaysRun = true)
    public void tsst002_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(1),"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"收款地址格式不正确"));
    }



    @Test(description = "input not active Receiving address",alwaysRun = true)
    public void tsst003_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(1),"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"地址未激活"));

    }



    @Parameters({"address"})
    @Test(description = "input Receiving address same as send address",alwaysRun = true)
    public void tsst004_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(1),address);
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"发送地址与接收地址不能相同"));
    }



    @Test(description = "input Null Receiving address",alwaysRun = true)
    public void tsst005_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.testfieldArray.get(2),"1");
        Assert.assertFalse(transfer.findsend_btn().isEnabled()); //send btn can click
    }



    @Test(description = "input max send number",alwaysRun = true)
    public void tsst006_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(transfer.findsend_btn().isEnabled());
    }



    @Test(description = "input mix send number",alwaysRun = true)
    public void tsst007_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"格式错误"));
    }



    @Test(description = "input too Much TRX send number",alwaysRun = true)
    public void tsst008_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"余额不足"));
    }


    @Test(description = "password error",alwaysRun = true)
    public void tsst009_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.testfieldArray.get(2).sendKeys("1");
        Helper.tapWhitePlace(transfer.driver);
        transfer.send_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        transfer.broadcastButtonClick();
        WebElement element = transfer.driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND name == '完成'");
        Assert.assertTrue(element.isDisplayed());
    }



    @Test(description = "Receiving address trim",alwaysRun = true)
    public void tsst010_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(1).sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"收款地址格式不正确"));

    }



    @Test(description = "Receiving Minimum Trx",alwaysRun = true)
    public void tsst011_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.testfieldArray.get(2).sendKeys("0.000001");
        Helper.tapWhitePlace(transfer.driver);
        transfer.send_btn.click();
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
    }



    @Test(description = "SendTrx success test",alwaysRun = true)
    public void tsst012_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.testfieldArray.get(2).sendKeys("1");
        Helper.tapWhitePlace(transfer.driver);
        transfer.send_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("Test0001");
        transfer.broadcastButtonClick();

        TrxPage tokenpage = new TrxPage(transfer.driver);
        double trcBefore = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        transfer.back_bt.click();//返回到首页资产页
        AssetPage assetpage = new AssetPage(DRIVER);
        tokenpage = assetpage.enterTrxPage();
        double trcafter = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        Assert.assertTrue(trcafter + 1 == trcBefore);
    }



    @Test(description = "Receiving Minimum Extra Trx",alwaysRun = true)
    public void tsst013_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.testfieldArray.get(2).sendKeys("0.0000001");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"精确到0.000001"));

    }


}
