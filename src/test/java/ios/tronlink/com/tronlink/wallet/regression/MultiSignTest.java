package ios.tronlink.com.tronlink.wallet.regression;

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

public class MultiSignTest extends Base {

    @Parameters({"ownerPrivateKey", "udid"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String ownerPrivateKey, String udid) throws Exception {
        System.out.println("pk: " + ownerPrivateKey + " udid: " + udid);
//        DRIVER.closeApp();
//        log("开始移除app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid); //00008020-000D04D62132002E ideviceinstaller -U com.tronlink.hdwallet -u
//        log("开始安装app");
//        AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
//        log("开始导入ownerPrivatekey");
        DRIVER.closeApp();
        DRIVER.launchApp();
        new Helper().importFirstWallet(Helper.importType.normal,ownerPrivateKey,DRIVER);

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
    @Parameters({"udid"})
    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        try {
            DRIVER.closeApp();
//            System.out.println("开始移除app");
//            AppiumTestCase.cmdReturn("ideviceinstaller -U com.tronlink.hdwallet -u " + udid);
//            System.out.println("开始安装app");
//            AppiumTestCase.cmdReturn("ideviceinstaller -i Tronlink.ipa -u " + udid);
            DRIVER.quit();
        } catch (Exception e) {
        }

    }

    public MultiSignManagerPage enterMultiSignManagerPage() throws Exception {

        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        MyPursePage pursePage = minePage.enterMyPursePage();
        MultiSignManagerPage managerPage = pursePage.enterMultiSignManagerPageNew();
        try {
            if (managerPage.instructionBtn.isDisplayed()) {
                System.out.println("\n1 times success 成功进入MultiSignMange");
                return managerPage;
            } else {
                System.out.println("\n1 times fails 进入MultiSignMange");
                System.out.println("\n2 times Try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if (managerPage.instructionBtn.isDisplayed()) {
                    System.out.println("\n2 times success 进入MultiSignMange");
                }
                return managerPage;
            }
        } catch (Exception e) {
            System.out.println("\n1 times fails 进入MultiSignMange Exception");
            System.out.println("\n2 times Try 进入MultiSignMange");
            managerPage = pursePage.enterMultiSignManagerPage();
            if (managerPage.instructionBtn.isDisplayed()) {
                System.out.println("\n2 times success  进入MultiSignMange");
            } else {
                System.out.println("\n last try 进入MultiSignMange");
                managerPage = pursePage.enterMultiSignManagerPageNew();
                if (managerPage.instructionBtn.isDisplayed()) {
                    System.out.println("\n last times success 进入MultiSignMange");
                }
            }
            return managerPage;
        }

    }

    @Parameters({"multiSignAddress"})
    @Test(description = "valued sign address is right",alwaysRun = true)
    public void test001_ValueSignAddressIsRight(String multiSignAddress) throws Exception{
        System.out.println("test_001ValueSignAddressIsRight:");
        MultiSignManagerPage managerPage = enterMultiSignManagerPage();
        Assert.assertTrue(managerPage.ownerAllkeys().contains(multiSignAddress));
    }


    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"},description = "add sign account", alwaysRun = true)
    public void test002_addSignAccountSuccess(String multiSignPrivateKey) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();
        waiteTime();
        DRIVER.findElementByName("私钥").click();
        waiteTime();
        ImportPrivateKeyPage importPrivateKey = new ImportPrivateKeyPage(DRIVER);
        PrivateKeySetNamePage setName = importPrivateKey.enterPrivateKeySetNamePage(multiSignPrivateKey);
        PrivateKeySetPwdPage setPwd = setName.enterPrivateKeySetPwdPage("Signed");
        PrivateKeySetPwdAgainPage setPwdAgain = setPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        waiteTime();
        setPwdAgain.pwd_input.sendKeys("Test0001");
        Helper.tapWhitePlace(DRIVER);
        setPwdAgain.getComplish_btn().click();
        TimeUnit.SECONDS.sleep(8);
        String trxtext = assetPage.getTrxCount();
        log("value:" + trxtext);
        Assert.assertTrue(Double.parseDouble(trxtext) < 100);

    }

    @Test(description = "change account", alwaysRun = true)
    public void test003_swipChangeAccountSuccess() throws Exception {
            AssetPage assetPage = new AssetPage(DRIVER);
            waiteTime();
            String oldName = assetPage.walletNameBtn.getText();
            log("\nfrom Wallet: " + oldName );
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            log("\nfrom Wallet: " + oldName + "  to Wallet " + assetPage.walletNameBtn.getText());
            Assert.assertFalse(assetPage.walletNameBtn.getText().contains(oldName));
    }

    @Test(description = "swip account address is change", alwaysRun = true)
    public void test004_swipAccountAddressChange() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        String oldName = assetPage.walletNameBtn.getText();
        MyPursePage myPursePage = assetPage.enterMyPursePage();
        myPursePage.swipWalletTochangeNext();
        log("\nfrom Wallet: " + oldName + "  to Wallet " + assetPage.walletNameBtn.getText());
        Assert.assertFalse(assetPage.walletNameBtn.getText().contains(oldName));
    }

    @Test(description = "make account address to Signed", alwaysRun = true)
    public void test005_makeAccountToSigned() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        String oldName = assetPage.walletNameBtn.getText();
        if (oldName.contains("Signed")){
            Assert.assertTrue(true);
        }else {
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Signed"));
        }
    }

    @Test(groups = {"P0"},description = "send trx overstep one’s authority Test", alwaysRun = true)
    public void test006_sendTrxOptions() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage = assetPage.enterSendTrxPage();
        Assert.assertTrue(sendTrxPage.overstepAuthority("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp"));
    }
    @Parameters({"ownerAddress"})
    @Test(description = "send mutisign addr change UI Test", alwaysRun = true)
    public void test007_sendmutisignAddrChangeUISuccess(String ownerAddress) throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage = assetPage.enterSendTrxPage();
        Assert.assertTrue(sendTrxPage.multiSignUIChanged(ownerAddress));

    }
    @Parameters({"ownerAddress"})
    @Test(description = "send trx sign success use owner authority Test", alwaysRun = true)
    public void test008_sendtrxSignSuccessUseOwnerAuthoritySuccess(String ownerAddress) throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage = assetPage.enterSendTrxPage();
        Assert.assertTrue(sendTrxPage.multiSignOwnerSend(ownerAddress));

    }
    @Parameters({"ownerAddress"})
    @Test(groups = {"P0"},description = "send trx sign success use active authority Test", alwaysRun = true)
    public void test009_sendSignSuccessUseOwnerActiveSuccess(String ownerAddress) throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage sendTrxPage = assetPage.enterSendTrxPage();
        Assert.assertTrue(sendTrxPage.multiSignActiveSend(ownerAddress));
    }

    @Test(groups = {"P0"},description = "make account address to Owner", alwaysRun = true)
    public void test010_makeAccountToOwner() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        String oldName = assetPage.walletNameBtn.getText();
        if (oldName.contains("Auto_test")){
            Assert.assertTrue(true);
        }else {
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
        }
    }

    @Test(description = "show multiSign Tips Test", alwaysRun = true)
    public void test011_showMultiSignTipsTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        Assert.assertTrue(assetPage.isMultiSignViewShow());
    }

    @Test(description = " multiSign Title Test", alwaysRun = true)
    public void test012_multiSignTitleTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        Assert.assertTrue(multiSignRecodPage.typeLabels.get(0).getText().contains("TRX 转账"));

    }

    @Test(description = " multiSign Deal numbers Test", alwaysRun = true)
    public void test013_multiSignDealNumbersTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        Assert.assertTrue(multiSignRecodPage.getwaitingCellsCount() >= 2);
    }

    @Test(description = " multiSign Wrong Password Test", alwaysRun = true)
    public void test014_multiSignWrongPasswordTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        Assert.assertTrue(multiSignRecodPage.signWrongPass());

    }

    @Test(groups = {"P0"},description = " multiSign sign Owner success Test", alwaysRun = true)
    public void test015_multiSignSuccessTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        int beforeNumber = multiSignRecodPage.getwaitingCellsCount();
        log("beforeNumber:"+ beforeNumber);
        multiSignRecodPage.signSuccess();
        int afterNumber = multiSignRecodPage.getwaitingCellsCount();
        log("afterNumber:"+ afterNumber);
        Assert.assertTrue(beforeNumber > afterNumber);
    }


    @Test(groups = {"P0"},description = "make account address to Signed", alwaysRun = true)
    public void test017_makeAccountToSigned() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        String oldName = assetPage.walletNameBtn.getText();
        if (oldName.contains("Signed")){
            Assert.assertTrue(true);
        }else {
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Signed"));
        }
    }

    @Test(description = "frozenPage have MultiSigned", alwaysRun = true)
    public void test018_frozenPagehaveMultiSignedTest() {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndThawingPage();
        Assert.assertTrue(frozenAndUnfreezePage.multiSignBtnIsShow());
    }

    @Parameters({"ownerAddress"})
    @Test(groups = {"P0"},description = "Add multiSignatureFeeCheck Test", alwaysRun = true)
    public void test019_multiSignatureFeeCheck(String ownerAddress) throws Exception {
        MultiSignManagerPage multiSignManagerPage = enterMultiSignManagerPage();
        multiSignManagerPage.addActiveBeforeConfirm(ownerAddress);
        Assert.assertTrue(multiSignManagerPage.detailLabel.getText().contains("101"));
    }

    @Parameters({"ownerAddress"})
    @Test(groups = {"P0"},description = "frozenPage setup MultiSigned thing", alwaysRun = true)
    public void test020_frozenPagehaveMultiSignedTest(String ownerAddress) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndThawingPage();
        MultiSignRecodPage recodPage = frozenAndUnfreezePage.FrozenMutiSignWith(ownerAddress);
        Assert.assertTrue(recodPage.isHaveMultiSingTrans());
    }

    @Test(groups = {"P0"},description = "make account address to Owner", alwaysRun = true)
    public void test021_makeAccountToOwner() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        String oldName = assetPage.walletNameBtn.getText();
        if (oldName.contains("Auto_test")) {
            Assert.assertTrue(true);
        } else {
            MyPursePage myPursePage = assetPage.enterMyPursePage();
            myPursePage.swipWalletTochangeNext();
            Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Auto_test"));
        }
    }

    @Test(groups = {"P0"},description = " multiSign  is Frozen Test", alwaysRun = true)
    public void test022_multiSignTitleTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        Assert.assertTrue(multiSignRecodPage.getListString(multiSignRecodPage.typeLabels).contains("冻结资产"));
    }

    @Test(description = " multiSign sign Frozen  Test", alwaysRun = true)
    public void test023_multiSignFrozenSuccessTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.goBackAndSeeMultiTips();
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        int beforeNumber = multiSignRecodPage.getwaitingCellsCount();
        log("beforeNumber:"+ beforeNumber);
        multiSignRecodPage.signSuccess();
        int afterNumber = multiSignRecodPage.getwaitingCellsCount();
        log("afterNumber:"+ afterNumber);
        Assert.assertTrue(beforeNumber > afterNumber);
    }


}
