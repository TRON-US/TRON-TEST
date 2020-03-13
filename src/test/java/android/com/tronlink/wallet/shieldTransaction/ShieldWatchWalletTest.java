package android.com.tronlink.wallet.shieldTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddPermissionPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.CommitteeProposalPage;
import android.com.wallet.pages.CreateProposePage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MultiSignManagerPage;
import android.com.wallet.pages.MyPursePage;
import android.com.wallet.pages.QRodeEPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrxSuccessPage;
import android.com.wallet.pages.SendTrzPage;
import android.com.wallet.pages.TransferPage;
import android.com.wallet.pages.TrxPage;
import android.com.wallet.pages.VotePage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShieldWatchWalletTest extends Base {

    static String receiverPublicAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.address");
    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.watchShieldAddress");
    static String shieldFee = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldTransactionFee");
    static String trzName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trzName");
    static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentMainNetBlockNum");

    Random rand = new Random();
    float shiled2PublicSendAmount;
    float shiled2ShieldSendAmount;
    float beforeBalance;
    float afterBalance;


    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        //String nsk,String ak,String ovk,String shieldAddress
        new Helper().getShieldWatchWalletSign("c120f2daf13d564d4a5619e7a38241fc9d2773310a1acdeab1aa624b89680d0b",
            "59d1e9786a617f9dcbc77e640caa3f6c3901a89f79bf778f549b40b1e59a800d",
            "d5a1f56573f882f322d28f90eaa9855e13d72a1a56dc4cb5197f4292c4b7add4",
            "ztron1tgde9pm2eerqgquljyq9qrcu6krxwefje5qfyrnua3z0km4e8xcyjjgw9fhdzwa95583u3c8fxy",
            DRIVER);

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

    @Test(enabled = true,description = "Shield watch wallet syn data test", alwaysRun = true)
    public void test001_ShieldWalletWalletSynDataTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Long startTime = System.currentTimeMillis();
        asset.waitShieldDataSynFinished();
        Long endTime = System.currentTimeMillis();
        System.out.println("finished syn cost time:" + (endTime - startTime));
        Assert.assertTrue(Float.valueOf(removeSymbol(asset.trz_balance.getText())) > 1);
    }

    @Test(enabled = true,description = "Shield to shiled QR test", alwaysRun = true)
    public void test002_Shield2ShieldQRTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        shiled2PublicSendAmount = (float)(0.000001);
        transfer.receiveAddress_text.sendKeys(receiverShieldAddress);
        transfer.tranferAmount_text.sendKeys(Float.toString(shiled2PublicSendAmount));
        transfer.swip();
        transfer.send_btn.click();
        transfer.transferNow_btn.click();

        while (transfer.coldHadScan_next_btn.getText().contains("冷钱包已扫描")) {
            Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
            Assert.assertTrue(transfer.coldHadScan_next_btn.isEnabled());
            transfer.coldHadScan_next_btn.click();
        }


        Assert.assertTrue(transfer.coldHadScan_next_btn.getText().contains("冷钱包已签名"));
        Assert.assertTrue(transfer.coldHadScan_next_btn.isEnabled());
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());




    }


    @Test(enabled = true,description = "Shield to public QR test", alwaysRun = true)
    public void test003_Shield2PublicQRTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        transfer.receiveAddress_text.sendKeys(receiverPublicAddress);
        transfer.tvMax_btn.click();
        transfer.swip();
        transfer.send_btn.click();
        transfer.transferNow_btn.click();

        while (transfer.coldHadScan_next_btn.getText().contains("冷钱包已扫描")) {
            Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());
            Assert.assertTrue(transfer.coldHadScan_next_btn.isEnabled());
            transfer.coldHadScan_next_btn.click();
        }

        Assert.assertTrue(transfer.coldHadScan_next_btn.getText().contains("冷钱包已签名"));
        Assert.assertTrue(transfer.coldHadScan_next_btn.isEnabled());
        Assert.assertTrue(new QRodeEPage(DRIVER).QRcode_text.isDisplayed());



    }



    public SendTrzPage enterToSendTrzPage() {
        AssetPage asset = new AssetPage(DRIVER);
        asset.waitShieldDataSynFinished();
        SendTrzPage transfer = asset.enterSendTrzPage();
        return transfer;
    }





}
