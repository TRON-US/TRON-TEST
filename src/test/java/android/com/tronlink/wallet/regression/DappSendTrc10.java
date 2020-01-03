package android.com.tronlink.wallet.regression;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DappSendTrc10 extends Base {
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

    @Test(description = "SendTrc10 success test", alwaysRun = true)
    public void tsst001_sendTrc10Success() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        ///////////////////////////////////////////
        MinePage mine = asset.enterMinePage();
        SettingPage settingPage = mine.enterSettingPage();
        NodeSetPage nodeSetPage = settingPage.enterNodeSetPage();
        settingPage = nodeSetPage.enterSettingPageChoiseDappChain();
        mine = settingPage.enterMinePage();
        asset = mine.enterAssetPage();
        ////////////////////////////////////////////
        SendTrxPage transfer = asset.enterSendTrxPage();
        double trc10Before = transfer.getTrc10Amount();
        String trc10SendAmount = "1";
        SendTrxSuccessPage stsp = transfer.normalSendTrc10(trc10SendAmount);
        TimeUnit.SECONDS.sleep(3);
        transfer = asset.enterSendTrxPage();
        double trc10After = transfer.getTrc10Amount();
        System.out.println(trc10After);
        Assert.assertEquals(trc10Before, trc10After + Double.valueOf(trc10SendAmount));
    }

    @Test(description = "input max send number", alwaysRun = true)
    public void tsst002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(description = "input mix send number", alwaysRun = true)
    public void tsst003_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(description = "input too Much TRX send number", alwaysRun = true)
    public void tsst004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc10("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }

}
