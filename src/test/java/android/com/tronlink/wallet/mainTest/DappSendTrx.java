package android.com.tronlink.wallet.mainTest;

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

    String contract = "TXkdXbzjoLpxGAD2strP1zwjJzR6osNfD7";
    String nileBlack = "TDPJULRzVtzVjnBmZvfaTcTNQ2tsVi6XxQ";

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
            TimeUnit.SECONDS.sleep(2);
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



    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test002_sendTrxDetailSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double beforeValue = Double.valueOf(prettyString(asset.tv_count.getText()));
        System.out.println("beforeSendBalance-----"+ beforeValue);
        SendTrxPage transfer = page.trxSendTrxPage();
        Double sendAmount = getAnAmount();
        System.out.println("sendTrxAmount-----"+ sendAmount);
        transfer.sendTrx(Double.toString(sendAmount));
        TimeUnit.SECONDS.sleep(2);
        while (transfer.btn_transaction_info.isEnabled()){
            TransactionDetailInfomaitonPage detail = transfer.enterTransationDetailPage();
            Assert.assertTrue(detail.tv_contract_type_top.getText().contains("TRX 转账"));
            Double detailAmount = sepLeftNumberTextToDouble(detail.tv_amount.getText(),"TRX");
            Assert.assertTrue(detailAmount == sendAmount);
            Assert.assertTrue(detail.transaction_time_text.getText().contains("2022"));
        }

    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test003_transferTherePart() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        Assert.assertTrue(isElementShotId("tv_address"));
        transfer.findElementByText("地址本").click();
        Assert.assertTrue(transfer.net_error.getText().contains("暂无其他地址"));
        transfer.findElementByText("我的账户").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(transfer.net_error.getText().contains("暂无其他账户"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test004_availableAmountInTransfer() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        Double avValue =  Double.parseDouble(removeSymbolString(page.tv_balance.getText()));
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.normalSendStepOne();
        Double stepOneValue =  Double.parseDouble(removeSymbolString(transfer.balance_text.getText()));
        System.out.println(avValue);
        System.out.println(stepOneValue);
        Assert.assertEquals(avValue,stepOneValue);
    }


    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test006_enterFrozenSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        page.rl_price_trx.click();
        if (isElementShotId("ll_action")){
            page.ll_action.click();
        }
        Assert.assertTrue(page.tv_main_title.getText().contains("网络资源管理"));
        Assert.assertTrue(isElementShotId("progress_energy"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test007_multiSignPathTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        Assert.assertTrue(transfer.tv_multi_sign.getText().contains("多重签名转账"));
        transfer.tv_multi_sign.click();
        Assert.assertTrue(transfer.tv_account.getText().contains("控制账户列表"));
    }

    @Parameters({"address"})
    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test008_transferAddressTextField(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(address);
        Assert.assertTrue(findByShotId("error_view").getText().contains("接收账户与转出账户相同，请确认"));

    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test009_transferAddressContractTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(contract);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此为合约地址，请确认您要向此合约地址转账，避免造成资产损失!"));
    }

    @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test010_transferAddressBlackTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        transfer.receiveAddress_text.sendKeys(nileBlack);
        Assert.assertTrue(findByShotId("error_view").getText().contains("此账户地址用于销毁代币，请确认您要向此地址转账！"));
    }

    @Parameters({"udid"})
   @Test(groups = {"P0"},enabled = true, alwaysRun = true)
    public void test011_transferAddressFormatTest(String udid) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage page =  asset.enterTrxPage();
        SendTrxPage transfer = page.trxSendTrxPage();
        keyboardSogou(udid);
        TimeUnit.SECONDS.sleep(1);
        transfer.receiveAddress_text.click();
        transfer.receiveAddress_text.sendKeys("TPjkW6HiKvTM9SPxhDdbb9GfCC39ajkLz6c");
        TimeUnit.SECONDS.sleep(1);
        DRIVER.hideKeyboard();
        TimeUnit.SECONDS.sleep(1);
        keyboardUnicode(udid);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(findByShotId("error_view").getText().contains("地址格式不正确，请检查"));


    }




}