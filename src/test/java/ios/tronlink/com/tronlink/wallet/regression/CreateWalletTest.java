package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.CreateWalletPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.WalletPasswordPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateWalletTest extends BaseTest {


    @Parameters({"privateKey","bundleId"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId) throws Exception {
        try{
            TimeUnit.SECONDS.sleep(2);
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", bundleId);
            DRIVER.executeScript("mobile: terminateApp", params);
            TimeUnit.SECONDS.sleep(2);
            DRIVER.executeScript("mobile: activateApp", params);
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
        }
        log("over write wallet BeforeClass");
    }

    @Test(description = "test001_CreateWalletInfoTest",alwaysRun = true)
    public void test001_CreateWalletInfoTest() throws Exception{
        CreateWalletPage  page = new CreateWalletPage(DRIVER);
        Assert.assertTrue(page.isElementExist("丰富的波场支持"));
        Assert.assertTrue(page.isElementExist("最丰富的"));
        Assert.assertTrue(page.isElementExist("最安全的"));
        Assert.assertTrue(page.isElementExist("最便捷的"));
        Assert.assertTrue(page.isElementExist("全面支持 TRX 及主链代币，各类功能，应有尽有"));
        page.SwitchColdWallet();
        Assert.assertTrue(page.isElementExist("冷钱包模式描述"));
        page.CloseTips();
        Assert.assertFalse(page.isElementExist("冷钱包模式描述"));

    }

    @Test(description = "",alwaysRun = true)
    public void test002_CreateFirstWalletTest() throws Exception {
        CreateWalletPage  page = new CreateWalletPage(DRIVER);
        page.enterCreate();
        page.policyBack();
        page.enterCreate();
        Assert.assertTrue(isElementExist("policy browse"));
        page.findAcceptAndClick();
        Assert.assertTrue(isElementExist("创建钱包"));
        page.inputCreatePassword();
        page.createWalletAction();
        Assert.assertTrue(isElementExist("创建成功"));
        Assert.assertTrue(isElementExist("请备份钱包助记词，在任何情况下，它是您恢复资产的唯一方式。"));
        page.backUpWalletAction();
        Assert.assertTrue(isElementExist("助记词即为私钥，由 12-24 个单词组成，掌握助记词等于掌握账户资产的所有权，请务必保管在安全的地方。"));
        Assert.assertTrue(isElementExist("准备好纸和笔，抄写记录下助记词并离线保存"));
        Assert.assertTrue(isElementExist("备份助记词"));
        page.beginBackUpWalletAction();
        Assert.assertTrue(isElementExist("任何人获取了助记词，意味着获取了资产的所有权，请保证周边环境安全。"));
        page.showWordsAction();
        Assert.assertTrue(isElementExist("请按顺序抄写以下助记词并确保正确。"));
        Assert.assertTrue(isElementExist("获得助记词等于拥有钱包资产所有权，请您在收款或卸载前，务必完成助记词备份。"));
        Assert.assertTrue(isElementExist("TronLink 不会在服务器上保存您的助记词，故一旦丢失，TronLink 也无法帮您找回钱包。"));
        page.navBack();
        page.navBack();
        page.navBack();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(page.walletName.getText(),"钱包");
    }


    @Test(description = "",alwaysRun = true)
    public void test003_FirstWalletNotBackUpActionTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        Assert.assertEquals(asset.contentLabel.getText(),"您的钱包助记词尚未备份，为了防止丢失资产的情况发生，请务必尽快备份助记词。");
        asset.enterReceiptPage();
        Assert.assertTrue(isElementExist("安全提醒"));
        Assert.assertTrue(isElementExist("为了防止因忘记密码、应用卸载、手机丢失等情况导致资产损失，继续操作需要先完成助记词备份"));
        asset.cancelAction();
        asset.enterTransportPage();
        Assert.assertTrue(isElementExist("安全提醒"));
        Assert.assertTrue(isElementExist("为了防止因忘记密码、应用卸载、手机丢失等情况导致资产损失，继续操作需要先完成助记词备份"));
        asset.cancelAction();
        asset.enterFrozenAndThawingPage();
        Assert.assertTrue(isElementExist("当前账户未激活，无法质押。您可进行多重签名质押，或转入 TRX 激活账户。"));
        Assert.assertTrue(isElementExist("发起多签质押"));
        asset.cancelTipsByConfirm();
        asset.enterVotePage();
        Assert.assertTrue(isElementExist("当前账户未激活，无法投票。您可进行多重签名投票，或转入 TRX 激活账户。"));
        Assert.assertTrue(isElementExist("发起多签投票"));
        asset.cancelTipsByConfirm();
        Assert.assertEquals(asset.gotoDetailBtn.getText(),"立即备份");

    }

    @Test(description = "",alwaysRun = true)
    public void test004_associatedWalletNumberTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage purse = asset.enterMyPursePage();
        Assert.assertTrue(isElementExist("关联钱包: 0"));
        purse.showLinkTip();
        Assert.assertTrue(isElementExist("共享同一套助记词的钱包"));
        Helper.tapWhitePlace(DRIVER);
        purse.enterAssociationWalletList();
        Assert.assertTrue(isElementExist("当前钱包"));
        Assert.assertTrue(isElementExist("暂无关联钱包"));
        Assert.assertTrue(isElementExist("Index 0"));
    }





}
