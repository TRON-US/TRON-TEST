package android.com.tronlink.wallet.mainTest;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DetailPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NFTPage;
import android.com.wallet.pages.ReceiptPage;
import android.com.wallet.pages.SendTrxPage;
import android.com.wallet.pages.SendTrxSuccessPage;
import android.com.wallet.pages.TransactionDetailInfomaitonPage;
import android.com.wallet.pages.TrxPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SendNFT extends Base {


    private String RPrivateKey = "0e44254c18f98c2998db2fa0e6d26e948ff27b4b5890b0306c7ab4553e109e24";
    private String RAddress = "TNzUJXEY45iuQBhmhDk5VucU9HS4CZhyKe";
    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e){

        }
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(alwaysRun = true)
    public void test001_NFTMainPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        Assert.assertEquals(page.tv_name.getText(),"HC7TTTT");
        Assert.assertEquals(page.tv_contract_address.getText(),"TCC5w3wvEqJwEq2wH5VZQPWjRvxwXQet2K");
        Assert.assertEquals(page.tv_common_right2.getText(),"交易记录");
        Assert.assertEquals(page.tv_contract_address_title.getText(),"合约地址");
    }

    @Test(alwaysRun = true)
    public void test002_NFTHistoryPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        page.enterHistory();
        Assert.assertEquals(page.nav_title.getText(),"交易记录");
        Assert.assertTrue(page.inners.size() > 1);
        Assert.assertTrue(page.address_title.getText().contains("接收") || page.address_title.getText().contains("发送"));
        Assert.assertTrue(page.time.getText().contains("2023"));
    }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test003_NFTMainToReceiveTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        ReceiptPage receiptPage = page.enterReceive();
        Assert.assertEquals(receiptPage.tv_address.getText(), address);
        Assert.assertTrue(receiptPage.isElementExist("com.tronlinkpro.wallet:id/qr"));
        Assert.assertTrue(receiptPage.pagetitle.getText().contains("扫描二维码向我付款"));
        Assert.assertTrue(receiptPage.wallettitle.getText().contains("Auto-test"));
        receiptPage.copy_btn.click();
        Assert.assertTrue(assertToast("已复制"));
    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test004_sendNFTTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        SendTrxPage sendPage = page.enterSendPage();
        SendTrxSuccessPage successPage = sendPage.sendNFT(RAddress);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertEquals(successPage.tv_result.getText(),"交易已上链");
        Assert.assertEquals(successPage.btn_done.getText(),"完成");
        Assert.assertEquals(successPage.btn_transaction_info.getText(),"查看交易详情");
        DetailPage detail = successPage.enterDetailPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(detail.tv_title.getText(), "发送");
        Assert.assertEquals(detail.name.getText(), "HC7T(HC7TTTT)");
        Assert.assertEquals(detail.tv_contract_type.getText(), "TRC721 通证转账");
        Assert.assertEquals(detail.tv_vdd.getText(), "查看详细数据");
        Assert.assertTrue(detail.iv_share.isEnabled());

    }

    @Test(groups = {"P0"},alwaysRun = true)
    public void test005_AddWalletTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        new Helper().AddMoreWalletWithPrivateKey(RPrivateKey,DRIVER);
        Assert.assertTrue(asset.tv_walletname.getText().contains("导入"));
    }

    @Parameters({"address"})
    @Test(groups = {"P0"},alwaysRun = true)
    public void test006_SendBackNFTTest(String address) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        NFTPage page = asset.enterNFTPage();
        SendTrxPage sendPage = page.enterSendPage();
        SendTrxSuccessPage successPage = sendPage.sendNFT(address);
        TimeUnit.SECONDS.sleep(8);
        Assert.assertEquals(successPage.tv_result.getText(),"交易已上链");
        Assert.assertEquals(successPage.btn_done.getText(),"完成");
        Assert.assertEquals(successPage.btn_transaction_info.getText(),"查看交易详情");
        DetailPage detail = successPage.enterDetailPage();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(detail.tv_title.getText(), "发送");
        Assert.assertEquals(detail.name.getText(), "HC7T(HC7TTTT)");
        Assert.assertEquals(detail.tv_contract_type.getText(), "TRC721 通证转账");
        Assert.assertEquals(detail.tv_vdd.getText(), "查看详细数据");
        Assert.assertTrue(detail.iv_share.isEnabled());
    }


}
