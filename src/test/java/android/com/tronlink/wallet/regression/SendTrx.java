package android.com.tronlink.wallet.regression;


import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrxSuccessPage;

import android.com.wallet.pages.TrxPage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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


    Random rand = new Random();
    float sendTrxAmount;
    int beforeSendBalance;
    int afterSendBalance;



    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    public SendTrxPage enterToSendTrxPage() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


    @Test(description = "input Privatekey to Receiving address", alwaysRun = true)
    public void test001_inputPrivatekey() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
    }


    @Test(description = "input error address to Receiving address", alwaysRun = true)
    public void test002_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
    }


    @Test(description = "input not active Receiving address", alwaysRun = true)
    public void test003_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        String hits = transfer.note_text.getText();
        Assert.assertTrue(hits.contains("地址未激活") || hits.contains("Address not activated"));
    }


    @Parameters({"address"})
    @Test(description = "input Receiving address same as send address", alwaysRun = true)
    public void test004_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, address);
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("发送地址与接收地址不能相同") || hits.contains("cannot be the same"));
    }


    @Test(description = "input Null Receiving address", alwaysRun = true)
    public void test005_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.tranferCount_text, "1");
        Assert.assertFalse(transfer.send_btn.isEnabled()); //send btn can click
    }


    @Test(description = "input max send number", alwaysRun = true)
    public void test006_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(description = "input mix send number", alwaysRun = true)
    public void test007_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(description = "input too Much TRX send number", alwaysRun = true)
    public void test008_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }


    @Test(description = "password error", alwaysRun = true)
    public void test009_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("1");
        transfer.send_btn.click();
        transfer.transferNow_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isEnabled());
    }


    @Test(description = "Receiving address trim", alwaysRun = true)
    public void test010_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("1");
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(description = "Receiving Minimum Trx", alwaysRun = true)
    public void test011_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("0.000001");
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(description = "check trx name", alwaysRun = true)
    public void test012_sendTrxCheckName() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        Assert.assertTrue(transfer.tvName_text.getText().contains("TRX"));
    }

    @Test(enabled = true,description = "Send trx success test", alwaysRun = true)
    public void test013_sendTrxSuccess() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        beforeSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        System.out.println("beforeSendBalance-----"+beforeSendBalance);
        sendTrxAmount = rand.nextFloat() + 1;
        transfer.sendTrx(Float.toString(sendTrxAmount));
    }


    @Test(enabled = true,description = "Trx transfer success recording")
    public void test014_transferInSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trx = asset.enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries < 7) {
            tries++;
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrxPage();
                trx.tranfer_tab.get(1).click();
                System.out.println(trx.tranferIncount_text.get(1).getText());
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("tranferInCount = " + tranferInCount);
                System.out.println("sendTrxAmount = " + sendTrxAmount);
                if (Float.toString(sendTrxAmount).substring(0,5).equals(tranferInCount.substring(0,5))) {
                    exist = true;
                    break;
                }
                TimeUnit.SECONDS.sleep(3);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);


    }


    @Test(enabled = true,description = "Trx transfer balance decrease check")
    public void test015_balanceReduceAfterSendCoin() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        afterSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        System.out.println("beforeSendBalance:" + beforeSendBalance);
        System.out.println("afterSendBalance:" + afterSendBalance);
        Assert.assertTrue(beforeSendBalance - afterSendBalance >= 1);
    }





}
