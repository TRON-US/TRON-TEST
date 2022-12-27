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




//    @Test(enabled = true, alwaysRun = true)
//    public void test001_availableAmountInTransfer() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        Double avValue =  Double.parseDouble(removeSymbolString(page.tv_balance.getText()));
//        SendTrxPage transfer = page.trxSendTrxPage();
//        transfer.normalSendStepOne();
//        Double stepOneValue =  Double.parseDouble(removeSymbolString(transfer.balance_text.getText()));
//        System.out.println(avValue);
//        System.out.println(stepOneValue);
//        Assert.assertEquals(avValue,stepOneValue);
//    }
//
//
//    @Test(enabled = true, alwaysRun = true)
//    public void test002_enterFrozenSuccess() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        page.rl_price_trx.click();
//        if (isElementShotId("ll_action")){
//            page.ll_action.click();
//        }
//        Assert.assertTrue(page.tv_main_title.getText().contains("网络资源管理"));
//        Assert.assertTrue(isElementShotId("progress_energy"));
//    }
//
//    @Test(enabled = true, alwaysRun = true)
//    public void test003_multiSignPathTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        SendTrxPage transfer = page.trxSendTrxPage();
//        Assert.assertTrue(transfer.tv_multi_sign.getText().contains("多重签名转账"));
//        transfer.tv_multi_sign.click();
//        Assert.assertTrue(transfer.tv_account.getText().contains("控制账户列表"));
//    }
//
//    @Parameters({"address"})
//    @Test(enabled = true, alwaysRun = true)
//    public void test004_transferAddressTextField(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        SendTrxPage transfer = page.trxSendTrxPage();
//        transfer.receiveAddress_text.sendKeys(address);
//        Assert.assertTrue(findByShotId("error_view").getText().contains("接收账户与转出账户相同，请确认"));
//
//    }
//
//    @Test(enabled = true, alwaysRun = true)
//    public void test005_transferAddressContractTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        SendTrxPage transfer = page.trxSendTrxPage();
//        transfer.receiveAddress_text.sendKeys(contract);
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertTrue(findByShotId("error_view").getText().contains("此为合约地址，请确认您要向此合约地址转账，避免造成资产损失!"));
//    }
//
//    @Test(enabled = true, alwaysRun = true)
//    public void test006_transferAddressBlackTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        SendTrxPage transfer = page.trxSendTrxPage();
//        transfer.receiveAddress_text.sendKeys(nileBlack);
//        Assert.assertTrue(findByShotId("error_view").getText().contains("此账户用于销毁代币，请确认您要向此账户转账！"));
//    }
//
//    @Parameters({"udid"})
//   @Test(enabled = true, alwaysRun = true)
//    public void test007_transferAddressFormatTest(String udid) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page =  asset.enterTrxPage();
//        SendTrxPage transfer = page.trxSendTrxPage();
//        keyboardSogou(udid);
//        TimeUnit.SECONDS.sleep(1);
//        transfer.receiveAddress_text.click();
//        transfer.receiveAddress_text.sendKeys("TPjkW6HiKvTM9SPxhDdbb9GfCC39ajkLz6c");
//        TimeUnit.SECONDS.sleep(1);
//        DRIVER.hideKeyboard();
//        TimeUnit.SECONDS.sleep(1);
//        keyboardUnicode(udid);
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(findByShotId("error_view").getText().contains("地址格式不正确，请检查"));
//
//
//    }




}