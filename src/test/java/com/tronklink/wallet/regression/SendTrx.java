package com.tronklink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.SendTrxPage;
import wallet.pages.SendTrxSuccessPage;

/**
 * 转帐功能测试
 */
public class SendTrx extends Base {

//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }


    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }



    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }



    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }



    public SendTrxPage enterToSendTrxPage(){
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }



    @Test(description = "input Privatekey to Receiving address")
    public void tsst001_inputPrivatekey() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text,"324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
    }



    @Test(description = "input error address to Receiving address")
    public void tsst002_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text,"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
    }



    @Test(description = "input not active Receiving address")
    public void tsst003_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text,"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        String hits = transfer.note_text.getText();
        Assert.assertTrue(hits.contains("地址未激活") || hits.contains("Address not activated"));
    }



    @Parameters({"address"})
    @Test(description = "input Receiving address same as send address")
    public void tsst004_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text,address);
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("发送地址与接收地址不能相同") || hits.contains("cannot be the same"));
    }



    @Test(description = "input Null Receiving address")
    public void tsst005_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.tranferCount_text,"1");
        Assert.assertFalse(transfer.send_btn.isEnabled()); //send btn can click
    }



    @Test(description = "input max send number")
    public void tsst006_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }



    @Test(description = "input mix send number")
    public void tsst007_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }



    @Test(description = "input too Much TRX send number")
    public void tsst008_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }



    @Test(description = "password error")
    public void tsst009_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("1");
        transfer.send_btn.click();
        transfer.transferNow_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isEnabled());
    }



    @Test(description = "Receiving address trim")
    public void tsst0010_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("1");
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }



    @Test(description = "SendTrx success test")
    public void tsst0011_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        int trxValue = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        SendTrxPage transfer = asset.enterSendTrxPage();
//        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
//        transfer.tranferCount_text.sendKeys("1");
//        transfer.swip();
//        transfer.send_btn.click();
//        transfer.transferNow_btn.click();
//        transfer.InputPasswordConfim_btn.sendKeys("Test0001");
//        SendTrxSuccessPage stsp = transfer.enterSendTrxSuccessPage();
//        String trxValueNewest = stsp.trxCount.getText();
//        System.out.println(trxValue+"-----"+trxValueNewest);
//        int NewestTrx = Integer.valueOf(removeSymbol(trxValueNewest));
        SendTrxSuccessPage stsp = transfer.normalSendTrx();
        asset = stsp.enterSendTrxPage();
        int trxValueNewest = Integer.valueOf(removeSymbol(asset.getTrxCount()));
        Assert.assertEquals(trxValue-1,trxValueNewest);
    }






}
