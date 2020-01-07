package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SendTrc10 extends BaseTest {

  public SendTrxPage enterToSendTrxPage() throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    return transfer;
  }

  @Test(description = "guarantee Chain in MainChain",alwaysRun = true)
  public void test000_GuaranteeChainName() throws Exception {
    Assert.assertTrue( Helper.guaranteeMainChain(DRIVER));
  }


  @Test(description = "ssendaddressChanged test",alwaysRun = true)
  public void tsst001_sendaddressChanged() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.testfieldArray.get(0).sendKeys(" ");
    Helper.tapWhitePlace(transfer.driver);
    Assert.assertTrue(transfer.transferErrorLabel.getText().contains("地址格式不正确"));

  }

  @Test(description = "input max send number",alwaysRun = true)
  public void tsst002_inputMaxSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("max");
    transfer.transferNow_btn.click();
    Assert.assertTrue(transfer.InputPasswordConfim_btn.isDisplayed());
  }



  @Test(description = "input mix send number",alwaysRun = true)
  public void tsst003_inputMixSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("mix");
    Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"格式错误"));
  }



  @Test(description = "input too Much trc10 send number",alwaysRun = true)
  public void tsst004_inputTooMuchSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("tooMuch");
    Assert.assertTrue(Helper.contentTexts(transfer.alltextArray,"余额不足"));
  }

  @Test(description = "SendTrc10 success test",alwaysRun = true)
  public void tsst005_sendTrc10Success() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.testfieldArray.get(1).sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
    Helper.tapWhitePlace(transfer.driver);
    TimeUnit.SECONDS.sleep(1);
    transfer.token_btn.click();
    transfer.getTrc10Token().click();
    transfer.testfieldArray.get(2).sendKeys("1");
    Helper.tapWhitePlace(transfer.driver);
    TimeUnit.SECONDS.sleep(1);
    transfer.send_btn.click();
    TimeUnit.SECONDS.sleep(1);
    transfer.transferNow_btn.click();
    TimeUnit.SECONDS.sleep(1);
    transfer.InputPasswordConfim_btn.sendKeys("Test0001");
    TimeUnit.SECONDS.sleep(1);
    transfer.broadcastButtonClick();
    TrxPage tokenpage = new TrxPage(transfer.driver);
    double trc10Before = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
    transfer.back_bt.click();//返回到首页资产页
    AssetPage assetpage = new AssetPage(DRIVER);
    tokenpage = assetpage.enterTrx10Page();
    double trc10after = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
    Assert.assertTrue(trc10after + 1 == trc10Before);
  }

}
