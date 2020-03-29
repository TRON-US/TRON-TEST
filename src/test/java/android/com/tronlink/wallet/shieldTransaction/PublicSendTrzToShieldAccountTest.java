package android.com.tronlink.wallet.shieldTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TransactionRecordPage;
import android.com.wallet.pages.TrxPage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PublicSendTrzToShieldAccountTest extends Base {
    Random rand = new Random();
    float sendTrzAmount;
    int beforeSendBalance;
    int afterSendBalance;
    static String receiverAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.receiverAddress");
    static String currentMainNetBlockNum = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.currentMainNetBlockNum");
    static String trc10TokenName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trc10TokenName");
    static String trzName = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.trzName");

    static String receiverShieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");
    static String shieldFee = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldTransactionFee");



    @Parameters({"witnessKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String witnessKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(witnessKey, DRIVER);
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


    @Test(enabled = true,description = "Public send trz to shield account success test", alwaysRun = true)
    public void test001_PbulicSendTrzToShieldAccountSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.mine_btn.click();
        asset.assetsMain_btn.click();
        SendTrxPage transfer = asset.publicAccountenterSendTrzPage();
        beforeSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        sendTrzAmount = getAnAmount();
        transfer.publicSendTrz(receiverShieldAddress,Float.toString(sendTrzAmount));
    }

    @Test(enabled = true,description = "Public trz transfer success recording")
    public void test002_PublicTrzTransferInSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
      try {
        TimeUnit.SECONDS.sleep(80);
        // if page display AD , cloese the AD
/*            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }*/
      } catch (Exception e){}
        TrxPage trx = asset.publicAccountEnterTrzPage();
        trx.tranfer_tab.get(1).click();
        System.out.println(trx.tranferIncount_text.get(1).getText());
        String tranferInCount = trx.tranferIncount_text.get(1).getText().split(" ")[1];
        Assert.assertTrue(Float.toString(sendTrzAmount).substring(0,5).equals(tranferInCount.substring(0,5)));
    }

    @Test(enabled = true,description = "Public trz transfer balance decrease check")
    public void test003_PublicTrzBalanceReduceAfterSendCoin() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.publicAccountenterSendTrzPage();
        afterSendBalance = Integer.valueOf(removeSymbol(transfer.balance_text.getText().split(" ")[1]));
        System.out.println("beforeSendBalance:" + beforeSendBalance);
        System.out.println("afterSendBalance:" + afterSendBalance);
        Assert.assertTrue(beforeSendBalance - afterSendBalance > Long.valueOf(shieldFee));
    }

    @Test(enabled = true, description = "Public trz transfer history record test", alwaysRun = true)
    public void test004_trzTransactionRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        TransactionRecordPage transaction = mine.enterTransactionRecordPage();
        String transactionType = transaction.transactionTypeList.get(0).getText();
        System.out.println(transactionType);
        Assert.assertTrue(transactionType.contains("匿名交易") || transactionType.contains("Shielded Transaction"));
    }



    @Parameters({"witnessAddress"})
    @Test(enabled = true, description = "Public send trz transaction detail info test", alwaysRun = true)
    public void test005_PublicSendtrzTransactionDetailInfo(String witnessAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.enterTransactionDetailPage(3);
        Assert.assertEquals(transactionInfo.sendAddress_text.getText(),witnessAddress);
        Assert.assertTrue(transactionInfo.receiverAddress_text.getText().contains("匿名地址"));
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trzName));
        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageSendAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
        Assert.assertEquals(detailPageSendAmount.substring(0,6),String.valueOf(sendTrzAmount).substring(0,6));
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
            > Long.valueOf(currentMainNetBlockNum) );
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());

    }


    @Parameters({"witnessAddress"})
    @Test(enabled = true, description = "Trz receive transaction detail info test", alwaysRun = true)
    public void test006_trzReceiveTransactionDetailInfo(String witnessAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TransactionDetailInfomaitonPage transactionInfo = asset.pubilcEnterTrzReceiveTransactionDetailPage();

        System.out.println(transactionInfo.title_amount_test.getText());
        System.out.println(transactionInfo.title_amount_test.getText().split(" ")[1]);
        String detailPageReceiveAmount = transactionInfo.title_amount_test.getText().split(" ")[1];
        String receiveIcon = transactionInfo.title_amount_test.getText().split(" ")[0];
        Assert.assertTrue(receiveIcon.equals("+"));
        Assert.assertTrue(transactionInfo.title_amount_test.getText().contains(trzName));
        Assert.assertEquals(transactionInfo.txid_hash_test.getText().length(),64);
        Assert.assertTrue(transactionInfo.transaction_time_text.getText().contains("202"));
        Assert.assertTrue(Float.valueOf(removeSymbol(detailPageReceiveAmount)) > 0);
        Assert.assertTrue(Long.valueOf(transactionInfo.block_num_text.getText())
            > Long.valueOf(currentMainNetBlockNum));
        Helper.swipScreen(transactionInfo.driver);
        Assert.assertTrue(transactionInfo.transaction_QRCode.isDisplayed());
        Assert.assertTrue(transactionInfo.to_tronscan_btn.isEnabled());
    }







}
