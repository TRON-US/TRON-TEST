package android.com.tronlink.wallet.regression;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;

import java.rmi.registry.LocateRegistry;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import android.com.utils.Helper;
import android.com.wallet.pages.MinePage;

public class MainNetDeposit20 extends Base {

    Random rand = new Random();
    float depositTrc20Amount;

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        boolean trc20IsExist = false;
        new Helper().getSign(privateKey, DRIVER);
        try {
            AssetPage asset = new AssetPage(DRIVER);
            asset.trx20_btn.get(2).isDisplayed();
            trc20IsExist = true;
        } catch (Exception e ) {
            try {
                DRIVER.closeApp();
                DRIVER.activateApp("com.tronlink.wallet");
            } catch (Exception e1) {
            }
        }

        if (!trc20IsExist) {
            try {
                AssetPage asset = new AssetPage(DRIVER);
                asset.trx20_btn.get(2).isDisplayed();
            } catch (Exception e ) {
                try {
                    DRIVER.closeApp();
                    DRIVER.activateApp("com.tronlink.wallet");
                } catch (Exception e1) {
                }
            }

        }
        AssetPage asset = new AssetPage(DRIVER);
        asset.trx20_btn.get(2).isDisplayed();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }


    //enter TRXPage
    public TrxPage enterTrc20Page() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        return asset.enterTrx20Page();
    }


    @Test(enabled = true,description = "Check TransferIn Hits")
    public void test001_checkTransferInHits() throws Exception {
        TrxPage trx = enterTrc20Page();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("hits");
        Assert.assertTrue(info.equals("转入需要执行智能合约。执行智能合约同时会消耗 Energy。")
            || info.contains("requires the execution of a smart contract"));
    }


    @Test(enabled = true,description = "Check TransferIn Fee")
    public void test002_checkTransferInFee() throws Exception {
        TrxPage trx = enterTrc20Page();
        TransferPage transferIn = trx.enterTransferPage();
        String info = transferIn.getTransferInfo("fee");
        int count = Integer.valueOf(info);
        Assert.assertTrue(50 <= count && count <= 900);
    }

    @Test(enabled = true,description = "Deposit TRC20 Success and checkout available trx", alwaysRun = true)
    public void test003_checkAvailableBalance() throws Exception {
        TrxPage trx = enterTrc20Page();
        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCount = " + trxCount);
        TransferPage transferIn = trx.enterTransferPage();
        depositTrc20Amount = rand.nextFloat() + 1;
        trx = transferIn.enterTrxPageWithTransferSuccess(Float.toString(depositTrc20Amount));
        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
        System.out.println("trxCountNow = " + trxCountNow);
        Assert.assertTrue(trxCount >= trxCountNow);
    }


    @Test(enabled = true,description = "Trc20 deposit success recording")
    public void test004_transferInSuccessRecording() throws Exception {
        TrxPage trx = enterTrc20Page();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries < 10) {
            tries++;
            try {
                AssetPage arret = trx.enterAssetPage();
                trx = arret.enterTrx20Page();
                trx.tranfer_tab.get(3).click();
                System.out.println(trx.tranferIncount_text.get(1).getText());
                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
                System.out.println("tranferInCount = " + tranferInCount);
                System.out.println("depositTrc20Amount = " + depositTrc20Amount);
                if (Float.toString(depositTrc20Amount).substring(0,5).equals(tranferInCount.substring(0,5))) {
                    exist = true;
                    break;
                }
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);
    }

}
































//    @AfterClass(alwaysRun = true)
//    public void tearDownAfterClass() {
//        //Base.tearDownAfterClass();
//        try {
//            DRIVER.quit();
//        } catch (Exception e) {
//        }
//    }
//
//
//    @Parameters({"privateKey"})
//    @BeforeClass(alwaysRun = true)
//    public void setUpBefore(String privateKey) throws Exception {
//        new Helper().getSign(privateKey, DRIVER);
//    }
//
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod() {
//        try {
//            DRIVER.closeApp();
//            DRIVER.activateApp("com.tronlink.wallet");
//        }catch (Exception e){}
//    }
//
//
//    //enter SettingPage
//    public SettingPage enterSettingPage() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage mine = asset.enterMinePage();
//        return mine.enterSettingPage();
//    }
//
//
//    //enter TRXPage
//    public TrxPage enterTrxPage() throws Exception {
//        SettingPage set = enterSettingPage();
//        NodeSetPage nodeSet = set.enterNodeSetPage();
//        set = nodeSet.enterSettingPageChoiseMainChain();
//        MinePage mine = set.enterMinePage();
//        AssetPage asset = mine.enterAssetPage();
//        return asset.enterTrx20Page();
//    }
//
//
//    @Test(description = "Change Chain", alwaysRun = true)
//    public void test001_changeChain() throws Exception {
//        SettingPage set = enterSettingPage();
//        String nodeName = set.node_name.getText();
//        System.out.println("chain name is : " + nodeName);
//        NodeSetPage nodeSet = set.enterNodeSetPage();
//        set = nodeSet.enterSettingPageChoiseDappChain();
//        //String currentNodeName = set.node_name.getText();
//        MinePage mine = set.enterMinePage();
//        AssetPage assetPage = mine.enterAssetPage();
//        String currentNodeName = assetPage.currChain_name.getText();
//        System.out.println("change chain to : " + currentNodeName);
//        Assert.assertNotEquals(nodeName, currentNodeName);
//    }
//
//
//    @Test(description = "Check TransferIn Chain Name", alwaysRun = true)
//    public void test002_checkTransferInChainName() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferPage();
//        String chain = transferIn.chain_text.getText();
//        Assert.assertTrue(chain.equals("DAppChain"));
//    }
//
//
//    @Test(description = "Check TransferIn Trc20 Count", alwaysRun = true)
//    public void test003_checkTransferInTrc10() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferPage();
//        String info = transferIn.getTransferInfo("trx");
//        Assert.assertTrue(info.contains("10"));
//    }
//
//
//    @Test(description = "Check TransferIn Hits", alwaysRun = true)
//    public void test004_checkTransferInHits() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferPage();
//        String info = transferIn.getTransferInfo("hits");
//        Assert.assertTrue(info.contains("转入需要执行智能合约") || info.contains("requires the execution of a smart contract"));
//    }
//
//
//    @Test(description = "Check TransferIn Fee", alwaysRun = true)
//    public void test005_checkTransferInFee() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferPage();
//        String info = transferIn.getTransferInfo("fee");
//        int count = Integer.valueOf(info);
//        System.out.println("count = " + count);
//        Assert.assertTrue(50 <= count && count <= 1500);
//    }
//
//
//    @Test(description = "Check Available Balance")
//    public void test006_checkAvailableBalance() throws Exception {
//        SettingPage set = enterSettingPage();
//        NodeSetPage nodeSet = set.enterNodeSetPage();
//        set = nodeSet.enterSettingPageChoiseMainChain();
//        MinePage mine = set.enterMinePage();
//        AssetPage asset = mine.enterAssetPage();
//        int trxCount = Integer.valueOf(removeSymbol(asset.getTrxCount()));
//        TrxPage trx = asset.enterTrx10Page();
//        int frozenCount = Integer.valueOf(removeSymbol(trx.freezeCount_text.getText()));
//        TransferPage transferIn = trx.enterTransferPage();
//        int availableBalance = Integer.valueOf(removeSymbol(transferIn.availableBalance_text.getText().split(" ")[1]));
//        Assert.assertTrue(trxCount == frozenCount + availableBalance);
//    }
//
//
//    @Test(description = "TransferIn Success Checkout Available trc20")
//    public void test007_checkAvailableBalance() throws Exception {
//        TrxPage trx = enterTrxPage();
//        int trxCount = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
//        TransferPage transferIn = trx.enterTransferPage();
//        trx = transferIn.enterTrxPageWithTransferSuccess();
//        int trxCountNow = Integer.valueOf(removeSymbol(trx.trxTotal_text.getText()));
//        Assert.assertTrue(trxCount >= trxCountNow);
//    }
//
//
//    @Test(description = "TransferIn Success Recording", alwaysRun = true)
//    public void test008_transferInSuccessRecording() throws Exception {
//        TrxPage trx = enterTrxPage();
//        TransferPage transferIn = trx.enterTransferPage();
//        String count = random(10, 10);
//        System.out.println("count = " + count);
//        trx = transferIn.enterTrxPageWithTransferSuccess(count);
//        int tries = 0;
//        Boolean exist = false;
//        exist = trx.getTrxVale();
//        while (exist == false && tries < 5) {
//            tries++;
//            try {
//                AssetPage arret = trx.enterAssetPage();
//                trx = arret.enterTrx20Page();
//                trx.tranfer_tab.get(3).click();
//                TimeUnit.SECONDS.sleep(3);
//                exist = trx.getTrxVale();
//                String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
//                System.out.println("tranferInCount = " + tranferInCount);
//                if (count.equals(tranferInCount)) {
//                    exist = true;
//                    break;
//                }
//            } catch (Exception e) {
//            }
//        }
//        Assert.assertTrue(exist);
//    }

