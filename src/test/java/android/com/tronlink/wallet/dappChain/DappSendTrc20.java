package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import android.com.utils.Helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        enterTrxPage();
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        } catch (Exception e){}
    }

    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset.enterTrx20Page();
    }


    public SendTrxPage enterToSendTrc20Page() {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrc20Page();
        return transfer;
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

    @Test(groups = {"P0"},description = "Dapp chain send TRC20 success test", alwaysRun = true)
    public void test001_sendTrc20Success() throws Exception {
        SendTrxPage transfer = enterToSendTrc20Page();
        beforeBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        dappChainSendTrc20Amount = getAnAmount();
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

    @Test(description = "Dapp chain send TRC20 and check 20name")
    public void test005_check20Name() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxPage = asset.enterTrx20Page();
        SendTrxPage sendTrxPage = trxPage.enterSendTrc10Page();
        //TransferPage transferPage = trxPage.enterTransferPage();
        Assert.assertTrue(sendTrxPage.tvName_text.getText().contains("TRX"));
    }

    @Test(enabled = true,description = "Dapp chain send TRC20 recording")
    public void test006_dappChainSendTrc20Recording() throws Exception {
        TrxPage trx = enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 2) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx20Page();
                trx.tranfer_tab.get(1).click();
                //todo 转出转入记录中没有最新数据
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("dappChainSendTrc20Amount:" + dappChainSendTrc20Amount);
                System.out.println("tranferInCount:" + tranferInCount);
                if (Float.toString(dappChainSendTrc20Amount).substring(0, 5)
                    .equals(tranferInCount.substring(0, 5))) {
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
    public void test007_balanceReduceAfterSendTrc20() throws Exception {
        SendTrxPage transfer = enterToSendTrc20Page();
        afterBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        Assert.assertTrue(beforeBalance - afterBalance >= 1);
    }


    @Parameters({"address"})
    @Test(enabled = true, description = "Dapp send Trc 20 transaction detail info test", alwaysRun = true)
    public void test008_DappSendTrc20TransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(2);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        //尼罗河测链gateway
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
        Assert.assertEquals(detailPageSendAmount.substring(0,6),String.valueOf(dappChainSendTrc20Amount).substring(0,6));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trc20TokenName));
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    }

}
