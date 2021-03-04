package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DappSendTrc20 extends Base {
    Random rand = new Random();
    float dappChainSendTrc20Amount;
    float beforeBalance;
    float afterBalance;
    static String receiverAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.receiverAddress");
    static String dappNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.dappNetGateWay");
    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");
    static String trc20TokenName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trc20TokenName");


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        setToDAppChain();
    }

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }


    public TrxPage enterTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrx20Page();
    }

    public SendTrxPage enterToSendTrc20Page() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc20Page();
        return transfer;
    }
    

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
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

    @Test(groups = {"P0"},description = "Dapp chain send TRC20 success test", alwaysRun = true)
    public void test001_sendTrc20Success() throws Exception {
        SendTrxPage transfer = enterToSendTrc20Page();
        beforeBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
        dappChainSendTrc20Amount = getAnAmount();
        System.out.println("DAppChain Send TRC20 : " + dappChainSendTrc20Amount);
        transfer.sendTrc20(Float.toString(dappChainSendTrc20Amount));
    }

    @Test(description = "Input max send number")
    public void test002_inputMaxSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("max");
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }

    @Test(description = "Input mix send number")
    public void test003_inputMinSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }

    @Test(description = "Input too Much TRX send number")
    public void test004_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrc20("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }

    @Test(enabled = true, description = "BandWidthShowTest", alwaysRun = true)
    public void test006_BandWidthShowTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.selectTokenType("20");
        waiteTime();
        transfer.tranferCount_text.sendKeys("0.000001");
        waiteTime();
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        String no_bandwidthTips = transfer.no_bandwidth.getText();
        Assert.assertTrue(no_bandwidthTips.contains("20"));
        Assert.assertTrue(no_bandwidthTips.contains("燃烧"));
        Assert.assertTrue(no_bandwidthTips.contains("TRX"));
        Assert.assertTrue(no_bandwidthTips.contains("消耗能量"));
        Assert.assertTrue(no_bandwidthTips.contains("转账需要执行智能合约"));
//TRC 20 通证，可能消耗能量，若能量不足则将燃烧 TRX，请保证您持有一定数量的 TRX，避免转账失败。
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Test(enabled = true,description = "Dapp chain send TRC20 recording")
    public void test007_dappChainSendTrc20Recording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trx = asset.enterTrx20Page();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 2) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx20Page();
                trx.tranfer_tab.get(1).click();
                String tranfercount = trx.tranferIncount_text.get(1).getText().substring(1);
                System.out.println("tranferCount: " + tranfercount);
                System.out.println("dappChainSendTrxAmount: " + dappChainSendTrc20Amount);
                if (tranfercount.contentEquals(String.valueOf(dappChainSendTrc20Amount)))
                {
                    exist = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);
    }

    @Test(enabled = true,description = "Dapp chain send TRC20 transfer balance decrease check")
    public void test008_balanceReduceAfterSendTrc20() throws Exception {
        SendTrxPage transfer = enterToSendTrc20Page();
        Assert.assertTrue(transfer.tvName_text.getText().contains("TRX"));
        afterBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
        Assert.assertTrue(beforeBalance - afterBalance >= 1);
    }


    @Parameters({"address"})
    @Test(enabled = true, description = "Dapp send Trc 20 transaction detail info test", alwaysRun = true)
    public void test009_DappSendTrc20TransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(2);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        //尼罗河测链gateway
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[0]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
        Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(dappChainSendTrc20Amount).substring(0,6));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
        Helper.swipScreenLitte(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String recorders = transactionInfo.resouce_cost.getText();
        String bandwidth = StringUtils.substringBeforeLast(recorders,"带宽");
        String energy = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(recorders,"带宽"),"能量");
        log( " bandwidth : " + bandwidth + " energy: "+ energy);
        Assert.assertTrue(Integer.parseInt(bandwidth.trim()) >= 0);
        Assert.assertTrue(Integer.parseInt(energy.trim()) >= 0);
    }

}
