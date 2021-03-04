package android.com.tronlink.wallet.dappChain;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.pages.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import android.com.wallet.UITest.base.Base;

public class DappSendTrx extends Base {
    Random rand = new Random();
    float dappChainSendTrxAmount;
    float beforeBalance;
    float afterBalance;
    static String receiverAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.receiverAddress");
    static String dappNetGateWay = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.dappNetGateWay");
    static String currentDappNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentDappNetBlockNum");


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //reset DAPP chain trun main chain
        //changeToMainChain();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        setToDAppChain();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    //reset app turn to MainChain
    public void changeToMainChain() {
        try {
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            nodeSet.enterSettingPageChoiseMainChain();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

    }


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    public TrxPage enterTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrxPage();
    }

    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


    @Test(enabled = true,description = "Input Privatekey to Receiving address", alwaysRun = true)
    public void test0001_inputPrivatekey() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("钱包地址格式不正确") || hits.equals("Wrong format"));
    }


    @Test(enabled = true,description = "Input error address to Receiving address", alwaysRun = true)
    public void test0002_inputErrorAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("钱包地址格式不正确") || hits.equals("Wrong format"));
    }


    @Test(enabled = true,description = "Input not active Receiving address", alwaysRun = true)
    public void test0003_inputNotActiveAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, "TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
        String hits = transfer.note_text.getText();
        Assert.assertTrue(hits.contains("账户未激活") || hits.contains("Address not activated"));
    }


    @Parameters({"address"})
    @Test(enabled = true,description = "Input Receiving address same as send address", alwaysRun = true)
    public void test0004_inputReceivingAddressSameAsSend(String address) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.receiveAddress_text, address);
        transfer.sendKey(transfer.tranferCount_text, "1");
        transfer.swipScreenLitte();
        transfer.send_btn.click();
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("转出账户和接收账户不能相同") || hits.equals("发送账户与接收账户不能相同") || hits.contains("cannot be the same"));
    }


    @Test(enabled = true,description = "Input Null Receiving address", alwaysRun = true)
    public void test0005_inputNullReceivingAddress() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendKey(transfer.tranferCount_text, "1");
        Helper.swipScreenLitte(transfer.driver);
        Assert.assertFalse(transfer.send_btn.isEnabled()); //send btn can click
    }



    @Test(enabled = true,description = "Input mix send number", alwaysRun = true)
    public void test0007_inputMixSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("mix");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.contains("转账金额需大于 0") ||centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
    }


    @Test(enabled = true,description = "Input too Much TRX send number", alwaysRun = true)
    public void test0008_inputTooMuchSendNumber() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.sendAllTrx("tooMuch");
        String centent = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
    }


    @Test(enabled = true,description = "Password error", alwaysRun = true)
    public void test0009_passwordError() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("1");
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        transfer.transferNow_btn.click();
        transfer.InputPasswordConfim_btn.sendKeys("forget_password");
        Assert.assertTrue(transfer.InputPasswordConfim_btn.isEnabled());
    }


    @Test(enabled = true,description = "Receiving address trim", alwaysRun = true)
    public void test0010_receivingAddressTrim() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("1");
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }


    @Test(enabled = true,description = "Receiving Minimum Trx", alwaysRun = true)
    public void test0011_sendMinimumTrx() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
        transfer.tranferCount_text.sendKeys("0.000001");
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
    }
    @Test(enabled = true, description = "BandWidthShowTest", alwaysRun = true)
    public void test0012_BandWidthShowTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
        transfer.tranferCount_text.sendKeys("0.000001");
        Helper.swipScreenLitte(transfer.driver);
        transfer.send_btn.click();
        waiteTime();
        String content = transfer.bandwidth_text.getText();
        String number = StringUtils.substringBeforeLast(content,"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) > 0);
    }

    @Test(groups = {"P0"},enabled = true,description = "Dapp chain send trx succesfully", alwaysRun = true)
    public void test0013_dappChainSendTrxSucess() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        beforeBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
        dappChainSendTrxAmount = getAnAmount();
        transfer.sendTrx(Float.toString(dappChainSendTrxAmount));
    }



    @Test(groups = {"P0"},enabled = true,description = "Dapp chain send coin recording")
    public void test0014_dappChainSendTrxRecording() throws Exception {
        TrxPage trx = enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries++ < 5) {
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrxPage();
                trx.tranfer_tab.get(1).click();
                String tranfercount = trx.tranferIncount_text.get(1).getText().substring(1);
                System.out.println("tranferCount: " + tranfercount + "length: " + tranfercount.length() );
                System.out.println("dappChainSendTrxAmount: " + dappChainSendTrxAmount + "length: " + String.valueOf(dappChainSendTrxAmount).length()  );
                System.out.println(tranfercount.contentEquals(String.valueOf(dappChainSendTrxAmount)));
                if (tranfercount.contentEquals(String.valueOf(dappChainSendTrxAmount)))
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

    @Test(enabled = true,description = "Trx transfer balance decrease check")
    public void test0015_balanceReduceAfterSendCoin() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        afterBalance = Float.valueOf(removeSymbol(transfer.balance_text.getText()));
        Assert.assertTrue(beforeBalance - afterBalance >= 1);
    }


    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true, description = "Dapp send Trx transaction detail info test", alwaysRun = true)
    public void test0016_trxTransactionDetailInfo(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(0);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),address);
        //尼罗河测链gateway
        Assert.assertEquals(transactionInfo.receiverAddress_text.getText(),receiverAddress);
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText()) > Long.valueOf(currentDappNetBlockNum));
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[0]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[0];
        Assert.assertEquals(detailPageSendAmount.substring(1,7),String.valueOf(dappChainSendTrxAmount).substring(0,6));
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
        String number = StringUtils.substringBeforeLast(transactionInfo.resouce_cost.getText(),"带宽");
        Assert.assertTrue(Integer.parseInt(number.trim()) >= 0);
    }







}