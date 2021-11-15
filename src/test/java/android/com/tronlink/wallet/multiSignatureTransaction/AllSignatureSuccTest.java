package android.com.tronlink.wallet.multiSignatureTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AllSignatureSuccTest extends Base {


    public String sourceWallet = "";
    public String fromAccountPrivateKey1 = "dad5b1d416822eb02e79bb818c35411e58b88db85562bcc8e71cac2c1ffa441c";
    //public String fromAccountAddress = "TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS";
    public String signatureAccountPrivateKey2 = "451a602d36e0158b5d642daca47e01ec5abdc96ec67a9f88dbc165c7dbb2a08a";
    public String signatureAccountAddress = "TS9XrumdDFBs5bQkVnhFTexoqwqaxUVG8v";

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
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e) {
            try {
                DRIVER.closeApp();
                DRIVER.activateApp("com.tronlinkpro.wallet");
            } catch (Exception e1) {

            }
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
        TimeUnit.SECONDS.sleep(3);
        SendTrx.receiveAddress_text.sendKeys(address);
        TimeUnit.SECONDS.sleep(3);
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        SendTrx.tranferCount_text.sendKeys(str);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(SendTrx.fee_text.getText().contains("1"));
    }



    @Parameters({"ownerAddress"})
    @Test(description = "test002_sendTrxMultiSignUnActiveFeeCheck ", alwaysRun = true)
    public void test002_sendTrxMultiSignUnActiveFeeCheck(String ownerAddress) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.receiveAddress_text.sendKeys(unActiveAddress);
        TimeUnit.SECONDS.sleep(3);
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        Helper.swipScreenLitte(SendTrx.driver);
        SendTrx.tranferCount_text.sendKeys(str);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(SendTrx.fee_text.getText().contains("2"));

    }


    @Parameters({"ownerAddress","address"})
    @Test(description = "test004_sendTrc20MultiSignActiveFeeCheck ", alwaysRun = true)
    public void test003_sendTrc20MultiSignActiveFeeCheck(String ownerAddress,String address) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress( ownerAddress);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.receiveAddress_text.sendKeys(address);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.selectCoinType("trc20");
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        SendTrx.tranferCount_text.sendKeys(str);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(SendTrx.fee_text.getText().contains("1"));

    }

    @Parameters({"ownerAddress"})
    @Test(description = "test005_sendTrc20MultiSignUnActiveFeeCheck ", alwaysRun = true)
    public void test004_sendTrc20MultiSignUnActiveFeeCheck(String ownerAddress) throws Exception {

        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.receiveAddress_text.sendKeys(unActiveAddress);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.selectCoinType("trc20");
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        SendTrx.tranferCount_text.sendKeys(str);
        Helper.swipScreen(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(SendTrx.note_text.getText().contains("账户未激活"));
        SendTrx.send_btn.click();
        TimeUnit.SECONDS.sleep(1);
        waiteTime();
        Assert.assertTrue(SendTrx.fee_text.getText().contains("1"));
    }


    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(groups = {"P0"},description = "Invalid Time is exist,ddressIsExists,signNameCheck,WaitSignAddressIsExists", alwaysRun = true)
    public void test005_invalidTimeIsExistsAddressIsExistsWaitSignAddressIsExistsSignNameCheck(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        SendTrx.inputFormAddress(ownerAddress);
        TimeUnit.SECONDS.sleep(3);
        SendTrx.receiveAddress_text.sendKeys(multiSignAddress);
        Random random = new Random();
        float count = random.nextFloat();
        DecimalFormat df = new DecimalFormat( "0.00" );
        String str = df.format(count);
        SendTrx.trxCount = str;
        SendTrx.tranferCount_text.sendKeys(str);
        Helper.swipScreen(DRIVER);
        SendTrx.send_btn.click();
        SendTrx.confirm_btn().click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(SendTrx.invalidTime_input.isDisplayed());
        Assert.assertTrue(SendTrx.signAddress_input.get(0).getText().length() == 34);
        Assert.assertTrue(SendTrx.signAddress_input.get(1).isDisplayed());
        Assert.assertTrue(SendTrx.selectSignName_text.isDisplayed());
    }




    @Parameters({"ownerPrivateKey", "multiSignAddress"})
    @Test(groups = {"P0"}, description = "send trx sign success options Test", alwaysRun = true)
    public void test006_sendTrxOptions(String ownerPrivateKey, String multiSignAddress) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(ownerPrivateKey, DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        asset = SendTrx.sendRamonTrxSuccess(multiSignAddress);
    }


    @Parameters({"ownerPrivateKey", "multiSignAddress"})
    @Test(groups = {"P0"}, description = "send trx sign success two times options Test", alwaysRun = true)
    public void test007_sendTrxTwoTimesOptions(String ownerPrivateKey, String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        asset = SendTrx.sendRamonTrxSuccess(multiSignAddress);
    }


    @Test(groups = {"P0"}, description = "test007_multiAlreadySignSendTrxNotesCheck", alwaysRun = true)
    public void test008_multiAlreadySignSendTrxNotesCheck() throws Exception {
        //new Helper().getSign(ownerPrivateKey, DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        TimeUnit.SECONDS.sleep(3);
        multiSignTransactionPage.transactionAlreadySign_text.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(multiSignTransactionPage.tv_note.getText(),"I'm multiSign notes");
        waiteTime();
        multiSignTransactionPage.tv_note_more.click();
        Assert.assertEquals(multiSignTransactionPage.tv_note.getText(),"I'm multiSign notes");
        Assert.assertTrue(multiSignTransactionPage.tv_cancle.getText().contains("我知道了"));

    }

    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"}, description = "test008_multiSignWaitSendTrxNotesCheck", alwaysRun = true)
    public void test009_multiSignWaitSendTrxNotesCheck(String multiSignPrivateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(multiSignPrivateKey, DRIVER);
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(multiSignTransactionPage.tv_note.getText(),"I'm multiSign notes");
        waiteTime();
        multiSignTransactionPage.tv_note_more.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(multiSignTransactionPage.tv_note.getText(),"I'm multiSign notes");
        Assert.assertTrue(multiSignTransactionPage.tv_cancle.getText().contains("我知道了"));

    }

    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"},description = "sign options Test", alwaysRun = true)
    public void test010_signOptions(String multiSignPrivateKey) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        multiSignTransactionPage.sign();
        TimeUnit.SECONDS.sleep(5);
    }



    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"},description = "sign options Test check TRX", alwaysRun = true)
    public void test011_signPageCheckTrx(String multiSignPrivateKey) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPurse = minePage.enterMyPursePage();
        MultiSignTransactionPage multiSignTransactionPage = myPurse.enterMultiSignTransactionPage();
        //multiSignTransactionPage.sign();
        Assert.assertTrue(multiSignTransactionPage.transConten_text.getText() != null);
        Assert.assertTrue(multiSignTransactionPage.transFrom_text.getText().length() == 34);
        Assert.assertTrue(multiSignTransactionPage.transTo_text.getText().length() == 34);
        Assert.assertTrue(multiSignTransactionPage.invaTime_text.isDisplayed());
    }




    @Parameters({"ownerPrivateKey"})
    @Test(groups = {"P0"},description = "test012_modifyMultiSignFeeCheck TR-1066", alwaysRun = true)
    public void test013_modifyMultiSignFeeCheck(String ownerPrivateKey) throws Exception {
        DRIVER.resetApp();
        new Helper().getSign(ownerPrivateKey, DRIVER);
        MultiSignManagerPage multiSignManager = enterMultiSignManagerPage();
        ModifyPermissionPage modifyPermission = multiSignManager.enterModifyPage();
        Helper.swipScreen(modifyPermission.driver);
        modifyPermission.confirm_btn.click();
        String fee = modifyPermission.fee_text.getText();
        Assert.assertTrue(fee.contains("TRX") && fee.contains("101"));


    }

    @Parameters({"multiSignAddress","ownerAddress"})
    @Test(description = "test012_modifyMultiSignFeeCheck TR-1066", alwaysRun = true)
    public void test014_FrozenMultiSignCheck(String multiSignAddress,String ownerAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndUnfreezePage();
        frozen.energy_btn.click();
        Helper.swipScreenLitte(frozen.driver);
        frozen.freezeCount_input.sendKeys("1");
        MultiSignTransactionPage multipage = frozen.frozenMultiSign(); //Freeze operating
        Assert.assertTrue(multipage.isElementExist(multiSignAddress));
        Assert.assertTrue(multipage.tv_invalid_time.getText().contains("24"));
        multipage.broadcaseNow();
        boolean exist = false;
        for (int i = 0 ;i<5;i++){
            log("findTimes:   s" + String.valueOf(i) );
            try{
                Assert.assertTrue(multipage.isElementExist("质押资产"));
                Assert.assertTrue(multipage.isElementExist("质押账户:"));
                Assert.assertTrue(multipage.isElementExist(ownerAddress));
                exist = true;
                break;
            }catch (Exception e)
            {
                Helper.swipScreenLitte(multipage.driver);
            }
        }
        Assert.assertTrue(exist);

    }

//    @Parameters({"multiSignAddress","ownerAddress"})
//    @Test(description = "test014_VoteMultiSignCheck", alwaysRun = true)
//    public void test015_VoteMultiSignCheck(String multiSignAddress,String ownerAddress) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        VotePage vote = asset.enterVotePage();
//        TimeUnit.SECONDS.sleep(1);
//        vote.votefirstSRuseMutiSign();
//        Assert.assertTrue(vote.isElementExist("投票"));
//    }






    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        MyPursePage myPursePage = mine.enterMyPursePage();
        MultiSignManagerPage MultiSignManager = myPursePage.enterMultiSignManagerPage();
        return MultiSignManager;
    }



}
