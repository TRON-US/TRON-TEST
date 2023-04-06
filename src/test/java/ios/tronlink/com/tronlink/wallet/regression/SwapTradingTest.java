package ios.tronlink.com.tronlink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SwapPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TransactionRecordPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;

/**
 * setting function test
 */
public class SwapTradingTest extends BaseTest {

    @Test(alwaysRun = true)
    public void test001_SwapPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage trxpage = asset.enterTrxPage();
        String avNumber =  removeSymbolNoDot(trxpage.leftAmountLabel.getText());
        trxpage.navBack();
        asset.enterSwapPage();
        Assert.assertTrue(isElementExist("闪兑"));
        Assert.assertTrue(isElementExist("查看币种信息"));
        Assert.assertTrue(isElementExist("交易记录"));
        Assert.assertTrue(isElementExist(avNumber));

    }

     @Test(groups = {"P0"},alwaysRun = true)
     public void test002_SwapTrxToUsdtTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         SwapPage page = asset.enterSwapPage();
         TimeUnit.SECONDS.sleep(3);
         Double sendValue = getAnAmount();
         page.inputField1.sendKeys(String.valueOf(sendValue));
         closeKeyBoard();
         TimeUnit.SECONDS.sleep(2);
         System.out.println(page.inputField2.getText());
         page.swapButton.click();
         TimeUnit.SECONDS.sleep(8);
         Assert.assertTrue(page.titleLabel.getText().contains("确认交易"));
         page.inputPassword();
         Assert.assertTrue(isElementExist(String.valueOf(sendValue)));
         Assert.assertTrue(isElementExist("TRX"));
         Assert.assertTrue(isElementExist("USDT"));
         TimeUnit.SECONDS.sleep(2);
         Assert.assertTrue(isElementExist("兑换成功"));

     }

      @Test(groups = {"P0"},alwaysRun = true)
      public void test003_MinePageHistoryTest() throws Exception {
          AssetPage asset = new AssetPage(DRIVER);
          MinePage page = asset.enterMinePage();
          TransactionRecordPage pageC = page.enterTransactionRecordPage();
          TimeUnit.SECONDS.sleep(3);
          Assert.assertTrue(isXpathElementExist("(//XCUIElementTypeStaticText[@name=\"触发智能合约\"])[1]"));
      }
    @Test(alwaysRun = true)
    public void test004_FinancialAPYTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page = asset.enterFinancialPage();
        Assert.assertTrue(isElementExist("理财推荐"));
        float defaultTRX = page.getTrxButtonY();
        page.switchToAPY();
        float nowTRX = page.getTrxButtonY();
        System.out.printf("defaultTRX is %f, nowTRX is %f", defaultTRX, nowTRX);
        Assert.assertTrue(defaultTRX != nowTRX);
    }

    @Test(alwaysRun = true)
    public void test005_PendingEarningsToBeClaimed() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page = asset.enterFinancialPage();
        page.enterClmPage();
        Assert.assertTrue(isElementExist("待领取收益"));
        Assert.assertTrue(isElementExist("待领取总收益"));
        Assert.assertTrue(isElementExist("JustLend DAO"));
        Assert.assertTrue(isElementExist("TRON"));
        Assert.assertTrue(isElementExist("BTTC"));
    }

    @Test(alwaysRun = true)
    public void test006_MyFinancialTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page = asset.enterFinancialPage();
        page.enterMyFinancial();
        Assert.assertTrue(isElementExist("理财列表"));
        int count = page.trxButtons.size();
        page.showByTypeItem();
        int count2 = page.trxButtons.size();
        Assert.assertTrue(count > count2);
    }

    @Test(alwaysRun = true)
    public void test007_switchWalletTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page = asset.enterFinancialPage();
        page.enterSwitchPage();
        Assert.assertTrue(isElementExist("切换钱包"));
        Assert.assertTrue(isElementExist("钱包汇总"));
        Assert.assertTrue(isElementExist("选择后可查看单一钱包理财数据"));
    }


}
