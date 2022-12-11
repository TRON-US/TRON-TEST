package ios.tronlink.com.tronlink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class DappSendTrxTest extends BaseTest {
    String successNumber;


    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset.enterTrxPage();
        }else{
            MinePage mine = asset.enterMinePage();
            mine.enterSettingPage();
            Helper.swipScreenLitter(DRIVER);
            mine.changeNet();
            mine.DAppChainBtn().click();
            mine.confirmBtn.click();
            TimeUnit.SECONDS.sleep(6);
            mine.blackBackBtn.click();
            TimeUnit.SECONDS.sleep(1);
            mine.blackBackBtn.click();
            return asset.enterTrxPage();
        }

    }
    //enter Dapp AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset;
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset;
        }
    }

//    @Test(alwaysRun = true)
//    public void test001_sendTrxAndHistoryTest() throws Exception {
//
//        TrxPage page = enterTrxPage();
//        Double send = getAnAmount();
//        SendTrxPage transfer = page.enterTransferPage();
//        String sendStr = String.valueOf(send);
//        transfer.sendTrxWithNumber(sendStr);
//        TimeUnit.SECONDS.sleep(7);
//        transfer.detail();
//        TimeUnit.SECONDS.sleep(3);
//        String recodeAmount = "-" + sendStr + " TRX";
//        Assert.assertTrue(isElementExist(recodeAmount));
//        Assert.assertTrue(isElementExist("TRX 转账"));
//        Assert.assertTrue(isElementExist("交易成功"));
//        Assert.assertTrue(isElementExist("查看详细数据"));
//        Assert.assertTrue(isElementExist("transaction shareIcon"));
//
//    }
//
//
//    @Test(alwaysRun = true)
//    public void test002_SendPageOneTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage page = asset.enterSendTrxPage();
//        Assert.assertTrue(isElementExist("地址本"));
//        Assert.assertTrue(isElementExist("我的账户"));
//        Assert.assertTrue(isElementExist("最近转账"));
//        Assert.assertTrue(isElementExist("多重签名转账"));
//        Assert.assertTrue(isElementExist("转账 (1/2)"));
//        Assert.assertTrue(isElementExist("transfer address scan"));
//        Assert.assertTrue(isElementExist("接收账户"));
//        Assert.assertTrue(isElementExist("粘贴"));
//        Assert.assertTrue(isElementExist("下一步"));
//        page.addressBook.click();
//        Assert.assertTrue(isElementExist("暂无其他地址"));
//        page.myAccount.click();
//        Assert.assertTrue(isElementExist("暂无其他账户"));
//    }
//
//    @Test(alwaysRun = true)
//    public void test003_SendPageOneAddressErrorTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage page = asset.enterSendTrxPage();
//        page.TextField.sendKeys("notAAddress");
//        closeKeyBoard();
//        Assert.assertTrue(isElementExist("  账户地址格式不正确，请检查"));
//    }
//
//    @Test(alwaysRun = true)
//    public void test004_SendPageOneAddressAddBookTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage page = asset.enterSendTrxPage();
//        page.TextField.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        closeKeyBoard();
//        Assert.assertTrue(isElementExist("加入到地址本"));
//    }
//
//    @Test(alwaysRun = true)
//    public void test005_SendPageOneAddressAddBookFuncTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        SendTrxPage page = asset.enterSendTrxPage();
//        page.TextField.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        closeKeyBoard();
//        page.addBook.click();
//        Assert.assertTrue(isElementExist("加入到地址本"));
//        Assert.assertTrue(isElementExist("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq"));
//        page.addBookName("Book");
//        Assert.assertTrue(page.iosToast("保存成功"));
//    }
//
//    @Test(alwaysRun = true)
//    public void test006_SendPageOneAddressBookAmountTest() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        TimeUnit.SECONDS.sleep(2);
//        String avNumber = page.leftAmountLabel.getText();
//        SendTrxPage transfer = page.enterTransferPage();
//        TimeUnit.SECONDS.sleep(2);
//        transfer.addressBook.click();
//        transfer.firstCell.click();
//        transfer.goToSecondPage();
//        Assert.assertTrue(isElementExist("TRX"));
//        Assert.assertTrue( transfer.tokenBalance.getText().contains(avNumber));
//    }
//
//
//    @Test(description = "input not active Receiving address",alwaysRun = true)
//    public void test007_inputNotActiveAddress() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();
//        TimeUnit.SECONDS.sleep(2);
//        transfer.enterSendTextField("TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
//        Assert.assertTrue(isElementExist("账户未激活，将额外消耗部分 TRX 用于激活该账户（不包含在转账数量内）"));
//
//    }
//
//    @Parameters({"address"})
//    @Test(groups = {"P0"},description = "input Receiving address same as send address",alwaysRun = true)
//    public void test008_inputReceivingAddressSameAsSend(String address) throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();        TimeUnit.SECONDS.sleep(2);
//        transfer.enterSendTextField(address);
//        Assert.assertTrue(isElementExist(" 接收账户与转出账户相同，请确认。"));
//    }
//
//
//    @Test(description = "input max send number",alwaysRun = true)
//    public void test009_inputMaxSendNumber() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();
//        transfer.sendAllTrx("max");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(transfer.findSend_btn().isEnabled());
//    }
//
//
//    @Test(description = "input too Much TRX send number",alwaysRun = true)
//    public void test010_inputTooMuchSendNumber() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();
//        transfer.sendAllTrx("tooMuch");
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(isElementExist("转账数量不可大于可用数量。"));
//    }
//
//    @Test(description = "Receiving address trim",alwaysRun = true)
//    public void test011_receivingAddressTrim() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();
//        TimeUnit.SECONDS.sleep(2);
//        transfer.TextField.sendKeys("  " + "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq" + "  ");
//        closeKeyBoard();
//        TimeUnit.SECONDS.sleep(2);
//        Assert.assertTrue(isElementExist("  账户地址格式不正确，请检查"));
//    }
//
//
//    @Test(description = "Receiving Minimum Extra Trx",alwaysRun = true)
//    public void test012_sendMinimumTrx() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        TrxPage page = asset.enterTrxPage();
//        SendTrxPage transfer = page.enterTransferPage();
//        TimeUnit.SECONDS.sleep(2);
//        transfer.TextField.sendKeys("TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq");
//        closeKeyBoard();
//        transfer.goToSecondPage();
//        TimeUnit.SECONDS.sleep(2);
//        transfer.TextField.sendKeys("0.0000001");
//        closeKeyBoard();
//        Assert.assertTrue(isElementExist("转账数量需大于 0"));
//    }


}
