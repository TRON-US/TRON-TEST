package com.tronklink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.SendTrxPage;
import wallet.pages.SendTrxSuccessPage;

/**
 * 转帐功能测试
 */
public class SendTrx extends Base {

    @BeforeMethod()
    public void setUpBefore() throws Exception{
        DRIVER.closeApp();
        DRIVER.launchApp();
        getSign();
    }

    @AfterClass
    public void tearDownAfterClass() {
        DRIVER.quit();
    }


    @Test(description = "SendTrx success test")
    public void tsst001_sendTrxSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        String trxValue = asset.getTrxCount();
        SendTrxPage transfer = asset.enterSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("1");
        transfer.swip();
        transfer.send_btn.click();
        transfer.transferNow_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("Test0001");
        SendTrxSuccessPage stsp = transfer.enterSendTrxSuccessPage();
        String trxValueNewest = stsp.trxCount.getText();
        //System.out.println(trxValue+"-----"+trxValueNewest);
        Assert.assertEquals(trxValue,trxValueNewest);
    }


}
