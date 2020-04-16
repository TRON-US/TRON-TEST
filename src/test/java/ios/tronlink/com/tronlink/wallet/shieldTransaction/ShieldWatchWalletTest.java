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

public class ShieldWatchWalletTest extends Base {
    static String receiverPublicAddress = "TQ9gXwoLcLi6W5gV5RjRykVuLVYTDY54nK";
    static String receiverShieldAddress ="ztron1zj4x9k4t28qk53w5qug45ds93dfcz2xkww4j2l8j2rgl0fav0mkp9lhe2gsgyffwjvmevrryeqd";


    @Parameters({"shieldSK", "udid"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String shieldSK,String udid) throws Exception {
        log("我是BaseTest类的Before");
        System.out.println("pk: " + shieldSK + " udid: " + udid);
        DRIVER.closeApp();
        log("开始移除app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        new Helper().importWatchShieldWallet(udid,"0d81ca05d2b0227755e56af3ad463c3297945d84e4588f238b101b46fa598d0a",
                "7642b0158c66379df0573287d9102cfa6c3919f1c9085bdcacebf60fdb6fd641",
                "d56def9cfb699790bc2f0869c898c25e968d91ed691238a67984732528c22dc6","ztron16nq696tkyuag3et5h4tygn4vumsgx7m2955azkfvpcecfy6na6f5n7n2l2svhpknmqp2v4wqne7",DRIVER);
    }
    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
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
    @AfterClass(groups = {"P0"},alwaysRun = true)
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
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
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

    @Test(enabled = true,description = "Shield watch wallet syn data test", alwaysRun = true)
    public void test001_ShieldWalletWalletSynDataTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Long startTime = System.currentTimeMillis();
        asset.waitShieldDataSynFinished();
        Long endTime = System.currentTimeMillis();
        System.out.println("finished syn cost time:" + (endTime - startTime));
        TimeUnit.SECONDS.sleep(10);
        Assert.assertTrue(Integer.parseInt(removeSymbol(asset.getTrxCount())) > 1);

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

    @Test(enabled = true,description = "Shield to shiled QR test", alwaysRun = true)
    public void test003_Shield2ShieldQRTest() throws Exception {
         SendTrxPage transfer = enterToSendTrxPage();
         QRCodePage page = transfer.sendTrzWatchWithNumber("1",receiverShieldAddress);
         TimeUnit.SECONDS.sleep(5);
         if(Helper.isElementExist(page.driver,"请用冷钱包扫描下方二维码")){
                Assert.assertTrue(Helper.isElementExist(page.driver,"签署交易"));
                Assert.assertTrue(Helper.isElementExist(page.driver,"因交易数据过大，需要扫描多张二维码"));
                Assert.assertTrue(Helper.isElementExist(page.driver,"请使用 v3.5.0 及以上版本 TronLink 扫描"));
         }
        for (int i = 0 ; i<10;i++){
            if(Helper.isElementExist(page.driver,"冷钱包已签名"))
                break;
            waiteTime();
            page.nextBtn.click();
        }
        waiteTime();
        page.overBtn.click();
        Assert.assertTrue(Helper.isElementExist(page.driver,"广播交易"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"扫描下一张"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"multiQRCode Scan"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"100%"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"0%"));



    }


    @Test(enabled = true,description = "Shield to public QR test", alwaysRun = true)
    public void test004_Shield2PublicQRTest() throws Exception {
        SendTrxPage transfer = enterToSendTrxPage();
        System.out.println("beforeBalance:" + transfer.shieldedCurrentBalance.getText());
        QRCodePage page = transfer.sendTrzWatchWithNumber("1.1",receiverPublicAddress);

        TimeUnit.SECONDS.sleep(5);
        if(Helper.isElementExist(page.driver,"请用冷钱包扫描下方二维码")){
            Assert.assertTrue(Helper.isElementExist(page.driver,"签署交易"));
            Assert.assertTrue(Helper.isElementExist(page.driver,"因交易数据过大，需要扫描多张二维码"));
            Assert.assertTrue(Helper.isElementExist(page.driver,"请使用 v3.5.0 及以上版本 TronLink 扫描"));
        }
        for (int i = 0 ; i<10;i++){
            if(Helper.isElementExist(page.driver,"冷钱包已签名"))
                break;
            waiteTime();
            page.nextBtn.click();
        }
        waiteTime();
        page.overBtn.click();
        Assert.assertTrue(Helper.isElementExist(page.driver,"广播交易"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"扫描下一张"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"multiQRCode Scan"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"100%"));
        Assert.assertTrue(Helper.isElementExist(page.driver,"0%"));

    }


    public SendTrxPage enterToSendTrxPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrzPage();
        return transfer;
    }





}
