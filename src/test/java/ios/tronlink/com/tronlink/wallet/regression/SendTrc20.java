package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SendTrc20 extends BaseTest {
    String successNumber;
    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }

    @Test(description = "guarantee Chain in MainChain",alwaysRun = true)
    public void test000_GuaranteeChainName() throws Exception {
        Assert.assertTrue( Helper.guaranteeMainChain(DRIVER));
    }
    @Test(description = "SendTrc20 success test", alwaysRun = true)
    public void test001_sendTrc20Success() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        String count = (random(10,10));
        log(count);
        successNumber = count;
        TrxPage tokenpage = transfer.sendTrx20WithNumber(successNumber);
        double trc20Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        transfer.back_bt.click();//返回到首页资产页
        waiteTime();
        AssetPage assetpage = new AssetPage(DRIVER);
        tokenpage = assetpage.enterTrx20Page();
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
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray, "格式错误"));
    }


    @Test(description = "input too Much trc20 send number", alwaysRun = true)
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        Assert.assertTrue(Helper.contentTexts(transfer.alltextArray, "余额不足"));
    }
    @Test(description = "ssendaddressChanged test", alwaysRun = true)
    public void test005_sendaddressChanged() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.testfieldArray.get(0).sendKeys(" ");
        Helper.tapWhitePlace(transfer.driver);
        Assert.assertTrue(transfer.transferErrorLabel.getText().contains("地址格式不正确"));
    }


    @Test(description = "Check OutNumberInRecord Trx20",alwaysRun = true)
    public void test006_CheckOutNumberInRecordTrx20() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page = asset.enterTrx20Page();
        String findString = "-" + successNumber;
        Assert.assertTrue(page.enterNumberRecordPage(findString));

    }

}



