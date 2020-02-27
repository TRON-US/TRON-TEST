package android.com.tronlink.wallet.shieldTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MyPursePage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrzPage;
import android.com.wallet.pages.TrxPage;
import android.com.wallet.pages.TrzPage;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 新建匿名交易钱包测试
 * */
public class ShieldSynTest extends Base {

    static String receiverPublicAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.address");
    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");
    static String shieldFee = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldTransactionFee");
    Random rand = new Random();
    float shiled2PublicSendAmount;
    float shiled2ShieldSendAmount;
    float beforeBalance;
    float afterBalance;

    @Parameters({"shieldSK"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String shieldSK) throws Exception {
        new Helper().getShieldSign(shieldSK, DRIVER);
    }

    @Test(enabled = true,description = "Shield wallet syn data test", alwaysRun = true)
    public void test001ShieldWalletSynDFataTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Long startTime = System.currentTimeMillis();
        asset.waitShieldDataSynFinished();
        Long endTime = System.currentTimeMillis();
        System.out.println("finished syn cost time:" + (endTime - startTime));
        Assert.assertTrue(Float.valueOf(removeSymbol(asset.trz_balance.getText())) > 500);
    }

    @Test(enabled = true,description = "Shield account transfer shield coin to public account test", alwaysRun = true)
    public void test002ShieldToPublicTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        System.out.println("beforeBalance:" + transfer.shieldBalance_text.getText());
        beforeBalance = Float.valueOf(transfer.shieldBalance_text.getText());
        shiled2PublicSendAmount = rand.nextFloat() + 1;
        transfer.sendTrz(receiverPublicAddress,Float.toString(shiled2PublicSendAmount));
    }

    @Test(enabled = true,description = "Shield account transfer shield coin to shield account test", alwaysRun = true)
    public void test003ShieldToShieldTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        shiled2ShieldSendAmount = rand.nextFloat() + 1;
        transfer.sendTrz(receiverShieldAddress,Float.toString(shiled2ShieldSendAmount));
    }



    @Test(enabled = true,description = "Shield account transfer shield coin to public account test", alwaysRun = true)
    public void test004ShieldToPublicTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        transfer.sendKey(transfer.receiveAddress_text, "324a2052e491e9");
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
    }

    @Parameters({"shieldAddress"})
    @Test(enabled = true,description = "input Receiving address same as send address", alwaysRun = true)
    public void test005_inputReceivingAddressSameAsSend(String shieldAddress) throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        transfer.sendKey(transfer.receiveAddress_text, shieldAddress);
        String hits = transfer.formatErrorHits_text.getText();
        Assert.assertTrue(hits.equals("转出地址与接收地址不能相同") || hits.equals("发送地址与接收地址不能相同") || hits.contains("cannot be the same"));
    }

    @Test(enabled = true,description = "Shield to shield transaction record test")
    public void test006_ShieldToShieldSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrzPage trz = asset.enterTrzPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries < 5) {
            tries++;
            try {
                AssetPage arret = trz.enterAssetPage();
                trz = arret.enterTrzPage();
                trz.tranfer_tab.get(1).click();
                System.out.println(trz.transactionRecordAmount_text.get(1).getText());
                String shieldToShieldRecordAmount = trz.transactionRecordAmount_text.get(1).getText().split(" ")[1];
                System.out.println("shieldToShieldRecordAmount = " + shieldToShieldRecordAmount);
                System.out.println("shiled2ShieldSendAmount = " + shiled2ShieldSendAmount);
                if (Float.toString(shiled2ShieldSendAmount).substring(0,5).equals(shieldToShieldRecordAmount.substring(0,5))) {
                    exist = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);



    }

    @Test(enabled = true,description = "Shield account transfer shield coin to public account test", alwaysRun = true)
    public void test007ShieldBalanceTest() throws Exception {
        SendTrzPage transfer = enterToSendTrzPage();
        System.out.println("beforeBalance:" + transfer.shieldBalance_text.getText());
        afterBalance = Float.valueOf(transfer.shieldBalance_text.getText());

        System.out.println("beforeBalance:" + beforeBalance);
        System.out.println("afterBalance:" + afterBalance);
        System.out.println("shiled2ShieldSendAmount:" + shiled2ShieldSendAmount);
        System.out.println("shiled2PublicSendAmount:" + shiled2PublicSendAmount);
        System.out.println("shieldFee:" + shieldFee);
        Assert.assertTrue(beforeBalance - afterBalance >= 2 * Long.valueOf(shieldFee)
            + shiled2ShieldSendAmount + shiled2PublicSendAmount - 1);
        Assert.assertTrue(beforeBalance - afterBalance <= 2 * Long.valueOf(shieldFee)
            + shiled2ShieldSendAmount + shiled2PublicSendAmount + 1);
    }




    public SendTrzPage enterToSendTrzPage() {
        AssetPage asset = new AssetPage(DRIVER);
        asset.waitShieldDataSynFinished();
        SendTrzPage transfer = asset.enterSendTrzPage();
        return transfer;
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    public String removeSymbol(String arg){
        String value = arg;
        if (arg.contains(",")){
            value = arg.replace(",","");
        }
        if (arg.contains(".")){
            String[] intValue = value.split("\\.");
            value = intValue[0];
        }
        return value;
    }


}
