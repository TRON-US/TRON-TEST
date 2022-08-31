package android.com.tronlink.wallet.multiSignatureTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;

import net.bytebuddy.implementation.bytecode.Throw;

import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AllSignatureSuccTest extends Base {


    static String unActiveAddress = Configuration.getByPath("testng.conf")
            .getString("unActiveAddressInNile.Address1");

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


    @Parameters({"ownerAddress","address"})
    @Test(description = "test001_sendTrxMultiSignActiveFeeCheck ", alwaysRun = true)
    public void test001_sendTrxMultiSignActiveFeeCheck(String ownerAddress,String address) throws Exception {
            AssetPage asset = new AssetPage(DRIVER);
            SendTrxPage SendTrx = asset.enterSendTrxPage();
            SendTrx.inputFormAddress(ownerAddress);
            SendTrx.goToSecondPage();
            TimeUnit.SECONDS.sleep(6);
            SendTrx.receiveAddress_text.sendKeys(address);
            TimeUnit.SECONDS.sleep(6);
            SendTrx.goToSecondPage();
            Random random = new Random();
            float count = random.nextFloat();
            DecimalFormat df = new DecimalFormat( "0.00" );
            String str = df.format(count);
            SendTrx.trxCount = str;
            SendTrx.tranferCount_text.sendKeys(str);
            TimeUnit.SECONDS.sleep(6);
            SendTrx.send_btn.click();
            TimeUnit.SECONDS.sleep(15);
            Assert.assertTrue(SendTrx.fee_text.getText().contains("1"));

    }



    @Parameters({"ownerAddress"})
    @Test(description = "test002_sendTrxMultiSignUnActiveFeeCheck ", alwaysRun = true)
    public void test002_sendTrxMultiSignUnActiveFeeCheck(String ownerAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(unActiveAddress);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.goToSecondPage();
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        Helper.swipScreenLitte(SendTrx.driver);
        SendTrx.tranferCount_text.sendKeys(str);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(12);
        Assert.assertTrue(SendTrx.fee_text.getText().contains("2"));
    }


    @Parameters({"ownerAddress","address"})
    @Test(description = "test006_sendTrc20MultiSignActiveFeeCheck ", alwaysRun = true)
    public void test003_sendTrc20MultiSignActiveFeeCheck(String ownerAddress,String address) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(address);
        TimeUnit.SECONDS.sleep(6);
        SendTrx.goToSecondPage();
        SendTrx.selectCoinType("20");
        Double sendAmount = getAnAmount();
        SendTrx.tranferCount_text.sendKeys(String.valueOf(sendAmount));
        TimeUnit.SECONDS.sleep(6);
        SendTrx.bt_send.click();
        TimeUnit.SECONDS.sleep(8);
        SendTrx.iv_arrow_right.click();
        Assert.assertTrue(SendTrx.tv_right_active_account.getText().contains("1"));

    }

    @Parameters({"ownerAddress"})
    @Test(description = "test005_sendTrc20MultiSignUnActiveFeeCheck ", alwaysRun = true)
    public void test004_sendTrc20MultiSignUnActiveFeeCheck(String ownerAddress) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        SendTrx.goToSecondPage();
        TimeUnit.SECONDS.sleep(6);
        SendTrx.receiveAddress_text.sendKeys(unActiveAddress);
        TimeUnit.SECONDS.sleep(6);
        Assert.assertTrue(SendTrx.error_view.getText().contains("账户未激活"));
        SendTrx.goToSecondPage();
        SendTrx.selectCoinType("20");
        Double sendAmount = getAnAmount();
        SendTrx.tranferCount_text.sendKeys(String.valueOf(sendAmount));
        TimeUnit.SECONDS.sleep(6);
        SendTrx.bt_send.click();
        TimeUnit.SECONDS.sleep(8);
        SendTrx.iv_arrow_right.click();
        Assert.assertTrue(SendTrx.tv_right_active_account.getText().contains("1"));

    }


    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(groups = {"P0"},description = "Invalid Time is exist,ddressIsExists,signNameCheck,WaitSignAddressIsExists", alwaysRun = true)
    public void test005_invalidTimeIsExistsAddressIsExistsWaitSignAddressIsExistsSignNameCheck(String ownerAddress,String multiSignAddress) throws Exception {
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
        TimeUnit.SECONDS.sleep(6);
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(4);
        SendTrx.confirm_btn().click();
        TimeUnit.SECONDS.sleep(8);
        Assert.assertTrue(SendTrx.invalidTime_input.isDisplayed());
        Assert.assertTrue(SendTrx.signAddress_input.get(0).getText().length() == 34);
        Assert.assertTrue(SendTrx.signAddress_input.get(1).isDisplayed());
        Assert.assertTrue(SendTrx.selectSignName_text.isDisplayed());
    }


    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(groups = {"P0"},description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test006_sendTRXUseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
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
    @Test(groups = {"P0"},description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test007_sendTrc10UseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
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
    @Test(groups = {"P0"},description = "test006_sendTRXUseOwnerAccountSuccess", alwaysRun = true)
    public void test008_sendTrc20UseOwnerAccountSuccess(String ownerAddress,String multiSignAddress) throws Exception {
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



    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage MultiSignManager = myPursePage.enterMultiSignManagerPage();
        return MultiSignManager;
    }



}
