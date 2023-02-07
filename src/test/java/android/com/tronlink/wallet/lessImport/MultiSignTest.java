package android.com.tronlink.wallet.lessImport;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.SendTrxPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MultiSignTest extends Base {

    @Parameters({"multiSignPrivateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String multiSignPrivateKey) throws Exception {
        new Helper().getSign(multiSignPrivateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {

        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test001_sendTRXUseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(multiSignAddress);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.goToSecondPage();
        Double sendAmount = getAnAmount();
        SendTrx.tranferCount_text.sendKeys(String.valueOf(sendAmount));
        SendTrx.add_note.click();
        waiteTime();
        SendTrx.et_note.sendKeys("I'm multiSign notes");
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        SendTrx.confirm_btn().click();
        TimeUnit.SECONDS.sleep(8);
        SendTrx.rl_bottom_next.click();
        SendTrx.InputPasswordConfim_btn.sendKeys("Test0001");
        SendTrx.bt_send.click();
        TimeUnit.SECONDS.sleep(12);
        Assert.assertTrue(SendTrx.tv_trans_type.getText().contains("TRX 转账"));
        Double amount = sepLeftNumberTextToDouble(SendTrx.tv_trans_content.getText(),"TRX");
        Assert.assertEquals(amount,sendAmount);
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test002_sendTrc10UseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(multiSignAddress);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.goToSecondPage();
        SendTrx.selectCoinType("10");
        Double sendAmount = getAnAmount();
        SendTrx.tranferCount_text.sendKeys(String.valueOf(sendAmount));
        SendTrx.add_note.click();
        waiteTime();
        SendTrx.et_note.sendKeys("I'm multiSign notes");
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        SendTrx.confirm_btn().click();
        TimeUnit.SECONDS.sleep(8);
        SendTrx.rl_bottom_next.click();
        SendTrx.InputPasswordConfim_btn.sendKeys("Test0001");
        SendTrx.bt_send.click();
        TimeUnit.SECONDS.sleep(12);
        Assert.assertTrue(SendTrx.tv_trans_type.getText().contains("TRC10 通证转账"));
        Double amount = sepLeftNumberTextToDouble(SendTrx.tv_trans_content.getText(),"(tronlink_token)");
        Assert.assertEquals(amount,sendAmount);
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test003_sendTrc20UseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(multiSignAddress);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.goToSecondPage();
        SendTrx.selectCoinType("20");
        Double sendAmount = getAnAmount();
        SendTrx.tranferCount_text.sendKeys(String.valueOf(sendAmount));
        SendTrx.add_note.click();
        waiteTime();
        SendTrx.et_note.sendKeys("I'm multiSign notes");
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        SendTrx.confirm_btn().click();
        TimeUnit.SECONDS.sleep(8);
        SendTrx.rl_bottom_next.click();
        SendTrx.InputPasswordConfim_btn.sendKeys("Test0001");
        SendTrx.bt_send.click();
        TimeUnit.SECONDS.sleep(12);
        Assert.assertTrue(SendTrx.tv_trans_type.getText().contains("触发智能合约"));
    }

    @Parameters({"ownerAddress"})
    @Test(alwaysRun = true)
    public void test004_FrozenEnergySuccess(String ownerAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterMultiSign();
        frozen.inputMultiAddress(ownerAddress);
        frozen.gotoMultiPageTwo();
        frozen.stakeEnergyWithAmount("1");
        frozen.stakeWithThisAddress();
        frozen.stakeConfirm();
        frozen.multiSignOptionSign();
        frozen.inputPopViewPassword("Test0001");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(frozen.tv_trans_content.getText().contains("1"));
        Assert.assertTrue(frozen.tv_trans_type.getText().contains("质押资产"));
        Assert.assertTrue(frozen.resource_type.getText().contains("能量"));

    }

    @Parameters({"ownerAddress"})
    @Test(alwaysRun = true)
    public void test005_FrozenBandWidthSuccess(String ownerAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.enterMultiSign();
        frozen.inputMultiAddress(ownerAddress);
        frozen.gotoMultiPageTwo();
        frozen.stakeEnergyWithAmount("1");
        frozen.stakeWithThisAddress();
        frozen.stakeConfirm();
        frozen.multiSignOptionSign();
        frozen.inputPopViewPassword("Test0001");
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(frozen.tv_trans_content.getText().contains("1"));
        Assert.assertTrue(frozen.tv_trans_type.getText().contains("质押资产"));
        Assert.assertTrue(frozen.resource_type.getText().contains("能量"));
    }

}
