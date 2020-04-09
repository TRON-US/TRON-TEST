package ios.tronlink.com.tronlink.wallet.shieldTransaction;

import android.com.utils.AppiumTestCase;
import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ShieldWalletSynCAndSentTRZTest extends Base {

    static String receiverPublicAddress = "TQ9gXwoLcLi6W5gV5RjRykVuLVYTDY54nK";
    static String receiverShieldAddress ="ztron1zj4x9k4t28qk53w5qug45ds93dfcz2xkww4j2l8j2rgl0fav0mkp9lhe2gsgyffwjvmevrryeqd";
    float shiled2PublicSendAmount;
    float shiled2ShieldSendAmount;
    float beforeBalance;
    float afterBalance;

    @Parameters({"shieldSK", "udid"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String shieldSK,String udid) throws Exception {
//        log("我是BaseTest类的Before");
//        System.out.println("pk: " + shieldSK + " udid: " + udid);
//        DRIVER.closeApp();
//        log("开始移除app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
//        log("开始安装app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
//        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        new Helper().importFirstWallet(Helper.importType.shieldWallet,shieldSK, DRIVER);
    }
    @Parameters({"bundleId"})
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
        try {
            String name = this.getClass().getSimpleName() + "." +
                    methed.getName();
            screenshotAction(name);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
        } catch (Exception e) {
        }

    }

    @Parameters({"udid"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        try {
            DRIVER.closeApp();
            System.out.println("开始移除app");
            AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
            System.out.println("开始安装app");
            AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

    @Parameters({"bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String bundleId) throws Exception {
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", bundleId);
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

    @Test(description = "Test ShieldWallet Syn DataTest", alwaysRun = true)
    public void test001_ShieldWalletSyCDataTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Long startTime = System.currentTimeMillis();
        asset.waitShieldDataSynFinished();
        Long endTime = System.currentTimeMillis();
        System.out.println("finished syn cost time:" + (endTime - startTime));
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(Integer.parseInt(removeSymbol(asset.getTrxCount())) > 10);
    }

    @Test(description = "Test TRZDetailValueTest", alwaysRun = true)
    public void test002_ShieldWalletTRZDetailValueTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Integer aint = 0;

        for (int i = 0;i<5;i++){
            TimeUnit.SECONDS.sleep(5);
            String amount = removeSymbol(asset.getTrxCount());
            aint = Integer.parseInt(amount);
            log("amount:" + amount);
            if (aint > 0)
            {  break;}
        }


        TrxPage trz = asset.enterTrxPage();
        String amount = removeSymbol(trz.trxTotal_text.getText());
        aint = Integer.parseInt(amount);
        log("aint:" + amount);

        TrzTokenDetailPage trzDetail = trz.enterTrzDetailPage();
        String detailAmount = removeSymbol(trzDetail.blanceLabel.getText());
        log("detailAmount:" + detailAmount);

        Integer dint = Integer.parseInt(detailAmount);
        Assert.assertTrue(aint.equals(dint));
    }

    @Test(description = "Test TRZDetailHelpDocTest", alwaysRun = true)
    public void test003_ShieldWalletTRZDetailHelpDocTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trz = asset.enterTrxPage();
        TrzTokenDetailPage trzDetail = trz.enterTrzDetailPage();
        trzDetail.instructionBtn.click();
        boolean isExist;
        try{
            waiteTime();
            trzDetail.helpWebview.getLocation();
            isExist = true;
        }catch (Exception e){
            isExist = false;

        }
        Assert.assertTrue(isExist);

     }

    @Test(description = "Test TRZDetailPageTest", alwaysRun = true)
    public void test004_ShieldWalletTRZDetailPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trz = asset.enterTrxPage();
        TrzTokenDetailPage trzDetail = trz.enterTrzProjectPage();
        waiteTime();
        Assert.assertTrue(trzDetail.nameLabel.getText().contains("TRZ"));
        Assert.assertTrue(trzDetail.typeLabel.getText().contains("TRC10 Token"));

    }

    @Test(enabled = true,description = "Shield account transfer shield coin to public account test", alwaysRun = true)
    public void test005_ShieldToPublicTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        System.out.println("beforeBalance:" + transfer.shieldedCurrentBalance.getText());
        beforeBalance = Float.valueOf(transfer.shieldedCurrentBalance.getText());
        shiled2PublicSendAmount = getAnAmount();
        transfer.sendTrzWithNumber(Float.toString(shiled2PublicSendAmount),receiverPublicAddress);
    }

    @Test(enabled = true,description = "Shield account transfer shield coin to shield account test", alwaysRun = true)
    public void test006_ShieldToShieldTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        shiled2ShieldSendAmount = getAnAmount();
        transfer.sendTrzWithNumber(Float.toString(shiled2ShieldSendAmount),receiverShieldAddress);
    }

    @Test(enabled = true,description = "Shield account transfer Wrong format test", alwaysRun = true)
    public void test007_ShieldTransactionExceptionTest() throws Exception {

        SendTrxPage transfer = enterToSendTrxPage();
        transfer.enterGetTextField("324a2052e491e9");
        String hits = transfer.reciptErrorLabel.getText();
        Assert.assertTrue(hits.contains("格式不正确") || hits.contains("Wrong format"));
    }

    @Parameters({"shieldAddress"})
    @Test(enabled = true,description = "input Receiving address same as send address", alwaysRun = true)
    public void test008_inputReceivingAddressSameAsSend(String shieldAddress) throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.enterGetTextField(shieldAddress);
        String hits = transfer.reciptErrorLabel.getText();
        Assert.assertTrue(hits.contains("转出地址与接收地址不能相同") || hits.contains("发送地址与接收地址不能相同") || hits.contains("cannot be the same"));
    }

    @Test(enabled = true,description = "Shield to Address transaction record test")
    public void test009_ShieldToAddressSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trz = asset.enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries < 5) {
            tries++;
            try {
                asset = trz.enterAssetPage();
                trz = asset.enterTrxPage();

                System.out.println("shiled2PublicSendAmount = " + shiled2PublicSendAmount);
                String findString = "-" + Float.toString(shiled2PublicSendAmount);
                if (trz.enterNumberRecordPage(findString)) {
                    exist = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);

    }

    @Test(enabled = true,description = "Shield to shield transaction record test")
    public void test010_ShieldToShieldSuccessRecording() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trz = asset.enterTrxPage();
        int tries = 0;
        Boolean exist = false;
        while (exist == false && tries < 5) {
            tries++;
            try {
                asset = trz.enterAssetPage();
                trz = asset.enterTrxPage();

                System.out.println("shiled2ShieldSendAmount = " + shiled2ShieldSendAmount);
                String findString = "-" + Float.toString(shiled2ShieldSendAmount);
                if (trz.enterNumberRecordPage(findString)) {
                    exist = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Assert.assertTrue(exist);

    }




    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrzPage();
        return transfer;
    }
}
