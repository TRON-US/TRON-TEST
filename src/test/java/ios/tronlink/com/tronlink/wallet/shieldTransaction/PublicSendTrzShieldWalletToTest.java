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

public class PublicSendTrzShieldWalletToTest extends Base {

    static String receiverPublicAddress = "TQ9gXwoLcLi6W5gV5RjRykVuLVYTDY54nK";
    static String receiverShieldAddress ="ztron1zj4x9k4t28qk53w5qug45ds93dfcz2xkww4j2l8j2rgl0fav0mkp9lhe2gsgyffwjvmevrryeqd";
    float PublicToShieldSendAmount;
    float PublicToPublicSendAmount;
    float beforeBalance;
    float afterBalance;

    @Parameters({"publicShieldSK", "udid"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String publicShieldSK,String udid) throws Exception {
        log("我是BaseTest类的Before");
        System.out.println("publicPk: " + publicShieldSK + " udid: " + udid);
        DRIVER.closeApp();
        log("开始移除app");
        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
        log("开始安装app");
        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        new Helper().importFirstWallet(Helper.importType.normal,publicShieldSK, DRIVER);
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

    @Test(groups = {"P0"},description = "PublicAddressSend Trz To ShieldWallet Success", alwaysRun = true)
    public void test001_PublicAddressSendTrzToShieldWalletSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        log("beforeBalance TRZ: " + trzPage.trxTotal_text.getText());
        beforeBalance = Float.valueOf(trzPage.trxTotal_text.getText());
        PublicToShieldSendAmount = getAnAmount();
        SendTrxPage transfer =trzPage.enterTransferPage();
        transfer.sendTrzWithNumber(Float.toString(PublicToShieldSendAmount),receiverShieldAddress);
    }

    @Test(description = "Public trz shield transfer balance decrease check", alwaysRun = true)
    public void test002_PublicTrzBalanceReduceAfterSendCoinToShield() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        log("afterBalance TRZ: " + trzPage.trxTotal_text.getText());
        afterBalance = Float.valueOf(trzPage.trxTotal_text.getText());
        Assert.assertTrue(afterBalance < beforeBalance);
    }

    @Test(description = "Public trz transfer history record test", alwaysRun = true)
    public void test003_trzTransactionhieldRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        String findString = "-" + PublicToShieldSendAmount;
        Assert.assertTrue(trzPage.enterNumberRecordPage(findString));
    }
    @Parameters({"publicShieldAddress"})
    @Test(groups = {"P0"},enabled = true, description = "Public send trz transaction detail info test", alwaysRun = true)
    public void test004_PublicSendTrzTransactionDetailInfo(String publicShieldAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        String findString = "-" + PublicToShieldSendAmount;
        TransactionRecordPage recordPage = trzPage.enterTransactionRecordPage(findString);
        waiteTime();
        Assert.assertTrue(recordPage.isElementExist("10 TRZ"));
        Assert.assertTrue(recordPage.isElementExist("匿名地址"));
        Assert.assertTrue(recordPage.isElementExist(publicShieldAddress));

    }


    @Test(groups = {"P0"},description = "PublicAddressSend Trz To PublicWallet Success", alwaysRun = true)
    public void test005_PublicAddressSendTrzToPublicWalletSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        log("beforeBalance TRZ: " + trzPage.trxTotal_text.getText());
        beforeBalance = Float.valueOf(trzPage.trxTotal_text.getText());
        PublicToPublicSendAmount = getAnAmount();
        SendTrxPage transfer =trzPage.enterTransferPage();
        transfer.sendTrzWithNumber(Float.toString(PublicToPublicSendAmount),receiverPublicAddress);

    }
    @Test(description = "Public trz Public transfer balance decrease check", alwaysRun = true)
    public void test006_PublicTrzBalanceReduceAfterSendCoinToPublic() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        log("afterBalance TRZ: " + trzPage.trxTotal_text.getText());
        afterBalance = Float.valueOf(trzPage.trxTotal_text.getText());
        Assert.assertTrue(afterBalance < beforeBalance);
    }
    @Test(description = "Public trz transfer history record test", alwaysRun = true)
    public void test007_trzTransactionPublicRecord() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        String findString = "-" + PublicToPublicSendAmount;
        Assert.assertTrue(trzPage.enterNumberRecordPage(findString));
    }

    @Parameters({"publicShieldAddress"})
    @Test(groups = {"P0"},enabled = true, description = "Trz receive transaction detail info test", alwaysRun = true)
    public void test008_trzReceiveTransactionDetailInfo(String publicShieldAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trzPage = asset.enterPublicTrzPage();
        String findString = "-" + PublicToPublicSendAmount;
        TransactionRecordPage recordPage = trzPage.enterTransactionRecordPage(findString);
        waiteTime();
        Assert.assertTrue(recordPage.isElementExist("0 TRX"));
        Assert.assertTrue(recordPage.isElementExist(receiverPublicAddress));
        Assert.assertTrue(recordPage.isElementExist(publicShieldAddress));
    }


}
