package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MultiSignTest extends Base {

    public Integer accountOfList = 0;

    @Parameters({"ownerPrivateKey", "udid"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String ownerPrivateKey, String udid) throws Exception {
        new Helper().importFirstWallet(Helper.importType.normal,ownerPrivateKey,DRIVER);
    }

    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod(Method methed, String bundleId) throws Exception {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            final boolean wasRunningBefore = (Boolean)DRIVER.executeScript("mobile: terminateApp", params);
        } catch (Exception e) {
        }

    }

    @Parameters({"bundleId"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,Method method) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(3);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                DRIVER.executeScript("mobile: terminateApp", params);
                TimeUnit.SECONDS.sleep(3);
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName());

    }

    @Parameters({"udid"})
    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass(String udid) {
        try {
            DRIVER.closeApp();
            DRIVER.quit();
        } catch (Exception e) {
        }

    }


    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"},description = "add sign account", alwaysRun = true)
    public void test001_addSignAccountSuccess(String multiSignPrivateKey) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        new Helper().importMoreWallet(Helper.importType.normal,multiSignPrivateKey,"Signed","Test0001",assetPage.driver);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Signed"));
    }

    @Test(groups = {"P0"},description = "send trx overstep one’s authority Test", alwaysRun = true)
    public void test002_sendTrxOptions() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage page = assetPage.enterSendTrxPage();
        page.overstepAuthority("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        Assert.assertTrue(isElementExist(" 您没有此账户的转账权限，无法转账。"));
        Assert.assertFalse(page.nextBtn.isEnabled());
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(alwaysRun = true)
    public void test003_sendTrxSignUseMultiSignAddress(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage page = assetPage.enterSendTrxPage();
        page.sendTrxMultiSignToConfirm();
        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
        Assert.assertTrue(isElementExist("多签交易"));
        Assert.assertTrue(isElementExist("1 TRX"));
        page.confirmPageButtonClick();
        Assert.assertTrue(isElementExist("多重签名设置"));
        Assert.assertTrue(isElementExist("(≤24H)"));
        Assert.assertTrue(isElementExist(ownerAddress));
        Assert.assertTrue(isElementExist(multiSignAddress));
        page.confirmPageButtonClick();
        page.passwordInputFinish();
        Assert.assertEquals(page.sendAddress.getText(),ownerAddress);
        Assert.assertTrue(page.typeLabel.getText().contains("TRX 转账"));
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(alwaysRun = true)
    public void test004_sendTrc10SignUseMultiSignAddress(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage page = assetPage.enterSendTrxPage();
        page.sendMultiSignStepTwo();
        page.inputTRC10AndSendAmount("0.3");
        Assert.assertEquals(page.topTransactionDescriptionLabel.getText(),"0.3 tronlink_token");
        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
        Assert.assertTrue(isElementExist("多签交易"));
        Assert.assertTrue(isElementExist("1 TRX"));
        page.confirmPageButtonClick();
        Assert.assertTrue(isElementExist("多重签名设置"));
        Assert.assertTrue(isElementExist("(≤24H)"));
        Assert.assertTrue(isElementExist(ownerAddress));
        Assert.assertTrue(isElementExist(multiSignAddress));
        page.confirmPageButtonClick();
        page.passwordInputFinish();
        Assert.assertEquals(page.sendAddress.getText(),ownerAddress);
        Assert.assertTrue(page.typeLabel.getText().contains("TRC10 通证转账"));

    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(alwaysRun = true)
    public void test005_sendTrc20SignUseMultiSignAddress(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage page = assetPage.enterSendTrxPage();
        page.sendMultiSignStepTwo();
        page.inputTRC20AndSendAmount("0.3");
        Assert.assertEquals(page.topTransactionDescriptionLabel.getText(),"0.3 TRX");
        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
        Assert.assertTrue(isElementExist("多签交易"));
        Assert.assertTrue(isElementExist("1 TRX"));
        page.confirmPageButtonClick();
        Assert.assertTrue(isElementExist("多重签名设置"));
        Assert.assertTrue(isElementExist("(≤24H)"));
        Assert.assertTrue(isElementExist(ownerAddress));
        Assert.assertTrue(isElementExist(multiSignAddress));
        page.confirmPageButtonClick();
        page.passwordInputFinish();
        Assert.assertEquals(page.sendAddress.getText(),ownerAddress);
        Assert.assertTrue(page.typeLabel.getText().contains("触发智能合约"));
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(alwaysRun = true)
    public void test006_frozenEnergyUseMultiSign(String ownerAddress,String multiSignAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
        page.multiSignView();
        page.multiSignFirst();
        page.inputFrozenCount("1");
        page.getFreeze_btn().click();
        page.agreeClick();
        page.confirmDeposit().click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(isElementExist("多签交易"));
        Assert.assertTrue(isElementExist("1 TRX"));
        Assert.assertTrue(isElementExist("质押"));
        Assert.assertTrue(isElementExist("1 票"));
        page.confirm_btn().click();
        Assert.assertTrue(isElementExist("多重签名设置"));
        Assert.assertTrue(isElementExist("(≤24H)"));
        Assert.assertTrue(isElementExist(ownerAddress));
        Assert.assertTrue(isElementExist(multiSignAddress));
        page.confirm_btn().click();
        page.frozenInputPassword();
        Assert.assertTrue(page.typeLabel.getText().contains("质押资产"));

    }

    @Parameters({"ownerAddress"})
    @Test(alwaysRun = true)
    public void test007_voteSTtoTronChinaUseMultiSignTest(String ownerAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        VotePage page = asset.enterVotePage();
        TimeUnit.SECONDS.sleep(6);
        page.enterMulti();
        page.enterOwenAddressAndNext(ownerAddress);
        page.sliderToSearch();
        page.enterSearch("china");
        page.enterFirstSRPage();
        TimeUnit.SECONDS.sleep(1);
        if (page.isVoteButton()){
            page.enterVoteStep1ToConfirm();
        }else if(page.isModifyButton()){
            page.enterEditVoteStep1ToConfirm();
        }
        Assert.assertEquals(page.topNetworkLabel.getText(),"Mainnet");
        Assert.assertTrue(isElementExist("投票"));
        Assert.assertEquals(page.topWalletNameLabel.getText(),"Auto_test");
        page.openFeeContent();
        Assert.assertTrue(isElementExist("1 TRX"));
        page.enterMultiSignVoteStep2Password();
        Assert.assertTrue(page.typeLabel.getText().contains("投票"));
    }

    @Test(description = "show multiSign Tips Test", alwaysRun = true)
    public void test008_changeWalletAndShowMultiSignTipsTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.swipWalletTochange("Auto_test");
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(assetPage.isMultiSignViewShow());
        Assert.assertTrue(assetPage.contentLabel.getText().contains("等待您的签名。"));

    }

    @Test(alwaysRun = true)
    public void test009_enterListOfMultiSignFromHome() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MultiSignRecodPage page = asset.enterMultiSignRecordView();
        Assert.assertTrue(isElementExist("多重签名交易"));
        Assert.assertTrue(page.waitingCells.size()>1);
    }

    @Test(alwaysRun = true)
    public void test010_enterListOfMultiSignFromManage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage page = asset.enterMyPursePage();
        Helper.swipScreenLitter(DRIVER);
        page.enterMultiSignRecordView();
        Assert.assertTrue(isElementExist("多重签名交易"));
        Assert.assertTrue(page.waitingCells.size()>1);
    }

    @Test(description = " multiSign Wrong Password Test", alwaysRun = true)
    public void test011_multiSignWrongPasswordTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage multiSignRecodPage = assetPage.enterMultiSignRecordView();
        Assert.assertTrue(multiSignRecodPage.signWrongPass());
    }

    @Test(alwaysRun = true)
    public void test012_multiSignFourTypeTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.enterMultiSignRecordView();
        String setString = "投票,质押资产,触发智能合约,TRC10 通证转账,TRX 转账";
        Set<String> types = new HashSet<>(Arrays.asList(setString.split(",")));
        List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
        System.out.println(Secure.size());
        for (int i = 0 ; i<Secure.size();i++){
            System.out.println(Secure.get(i).getText());
            Assert.assertTrue(types.contains(Secure.get(i).getText()));
        }
    }

    @Test(alwaysRun = true)
    public void test013_signMultiVoteByOther() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
        if (page.typeLabel.getText().contains("投票")){
            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
            Integer sizeNumber = Secure.size();
            System.out.println(Secure.size());
            if (sizeNumber > 0){
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(3);
                try {
                    page.confirm_btn().click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }


    }

    @Test(alwaysRun = true)
    public void test014_signMultiFrozenByOther() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
        if (page.typeLabel.getText().contains("质押资产")){
            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
            Integer sizeNumber = Secure.size();
            System.out.println(Secure.size());
            if (sizeNumber > 0){
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(5);
                try {
                    page.confirmButton.click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }

    }

    @Test(alwaysRun = true)
    public void test015_signMultiTRC20ByOther() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
        if (page.typeLabel.getText().contains("触发智能合约")){
            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
            Integer sizeNumber = Secure.size();
            System.out.println(Secure.size());
            if (sizeNumber > 0){
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(5);
                try {
                    page.confirm_btn().click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }

    }

    @Test(alwaysRun = true)
    public void test016_signMultiTRC10ByOther() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
        if (page.typeLabel.getText().contains("TRC10 通证转账")){
            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
            Integer sizeNumber = Secure.size();
            System.out.println(Secure.size());
            if (sizeNumber > 0){
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(5);
                try {
                    page.confirm_btn().click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }

    }

    @Test(alwaysRun = true)
    public void test017_signMultiTrxByOther() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
        if (page.typeLabel.getText().contains("TRX 转账")){
            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
            Integer sizeNumber = Secure.size();
            System.out.println(Secure.size());
            if (sizeNumber > 0){
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(5);
                try {
                    page.confirm_btn().click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }

    }



}
