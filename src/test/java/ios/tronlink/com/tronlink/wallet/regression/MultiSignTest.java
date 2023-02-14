package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
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

public class MultiSignTest extends BaseTest {



    @Parameters({"ownerPrivateKey","bundleId"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String ownerPrivateKey,String bundleId) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        restartApp(bundleId);
        TimeUnit.SECONDS.sleep(4);
        log("TestClass Import ---Start");
        importFirstWallet(importType.normal,ownerPrivateKey);
        log("TestClass Import ---Success");
    }


    @Parameters({"multiSignPrivateKey"})
    @Test(groups = {"P0"},description = "add sign account", alwaysRun = true)
    public void test001_addSignAccountSuccess(String multiSignPrivateKey) throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        new Helper().importMoreWallet(Helper.importType.normal,multiSignPrivateKey,"Signed","Test0001",assetPage.driver);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(assetPage.walletNameBtn.getText().contains("Signed"));
    }

    @Test(description = "send trx overstep one’s authority Test", alwaysRun = true)
    public void test002_sendTrxOptions() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        SendTrxPage page = assetPage.enterSendTrxPage();
        page.overstepAuthority("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
        Assert.assertTrue(isElementExist(" 您没有此账户的转账权限，无法转账。"));
        Assert.assertFalse(page.nextBtn.isEnabled());
    }

    @Parameters({"ownerAddress","multiSignAddress"})
    @Test(groups = {"P0"},alwaysRun = true)
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
        Assert.assertTrue(page.typeLabel.getText().contains("触发智能合约"));
    }

//    @Parameters({"ownerAddress","multiSignAddress"})
//    @Test(groups = {"P0"},alwaysRun = true)
//    public void test006_frozenEnergyUseMultiSign(String ownerAddress,String multiSignAddress) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        FrozenAndUnfreezePage page = asset.enterFrozenAndThawingPage();
//        page.multiSignView();
//        page.multiSignFirst();
//        page.inputFrozenCount("1");
//        page.getFreeze_btn().click();
//        page.agreeClick();
//        page.confirmDeposit().click();
//        TimeUnit.SECONDS.sleep(3);
//        Assert.assertTrue(isElementExist("多签交易"));
//        Assert.assertTrue(isElementExist("1 TRX"));
//        Assert.assertTrue(isElementExist("质押"));
//        Assert.assertTrue(isElementExist("1 票"));
//        page.confirm_btn().click();
//        Assert.assertTrue(isElementExist("多重签名设置"));
//        Assert.assertTrue(isElementExist("(≤24H)"));
//        Assert.assertTrue(isElementExist(ownerAddress));
//        Assert.assertTrue(isElementExist(multiSignAddress));
//        page.confirm_btn().click();
//        page.frozenInputPassword();
//        Assert.assertTrue(page.typeLabel.getText().contains("质押资产"));
//
//    }

    @Parameters({"ownerAddress"})
    @Test(groups = {"P0"},alwaysRun = true)
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
        String setString = "投票,触发智能合约,TRC10 通证转账,TRX 转账";
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
                TimeUnit.SECONDS.sleep(6);
                try {
                    page.confirm_btn().click();
                }catch (Exception e){
                }
                page.passwordInputFinish();
            }
            Assert.assertTrue(!isElementExist("signBtn"));
        }


    }

//    @Test(alwaysRun = true)
//    public void test014_signMultiFrozenByOther() throws Exception {
//        AssetPage assetPage = new AssetPage(DRIVER);
//        MultiSignRecodPage page = assetPage.enterMultiSignRecordView();
//        if (page.typeLabel.getText().contains("质押资产")){
//            List<WebElement> Secure = (List<WebElement>) DRIVER.findElementsByName("typeLabel");
//            Integer sizeNumber = Secure.size();
//            System.out.println(Secure.size());
//            if (sizeNumber > 0){
//                page.signBtn.click();
//                TimeUnit.SECONDS.sleep(8);
//                try {
//                    page.confirmButton.click();
//                }catch (Exception e){
//                }
//                page.passwordInputFinish();
//            }
//            Assert.assertTrue(!isElementExist("signBtn"));
//        }
//
//    }

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
                TimeUnit.SECONDS.sleep(8);
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
                TimeUnit.SECONDS.sleep(1);
                page.signBtn.click();
                TimeUnit.SECONDS.sleep(8);
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
                TimeUnit.SECONDS.sleep(8);
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
