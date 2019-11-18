package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SendTrc10 extends BaseTest {

  public SendTrxPage enterToSendTrxPage(){
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    return transfer;
  }

  @Test(description = "SendTrc10 success test",alwaysRun = true)
  public void tsst001_sendTrc10Success() throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    Double trc10Before = transfer.getTrc10Amount();
    String trc10SendAmount = "1";
    SendTrxSuccessPage stsp = transfer.normalSendTrc10(trc10SendAmount);
    TimeUnit.SECONDS.sleep(3);
    transfer = asset.enterSendTrxPage();
    double trc10After = transfer.getTrc10Amount();
    System.out.println(trc10After);
    Assert.assertEquals(trc10Before,trc10After + Double.valueOf(trc10SendAmount));
  }

  @Test(description = "input max send number",alwaysRun = true)
  public void tsst002_inputMaxSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("max");
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "input mix send number",alwaysRun = true)
  public void tsst003_inputMixSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("mix");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
  }



  @Test(description = "input too Much trc10 send number",alwaysRun = true)
  public void tsst004_inputTooMuchSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("tooMuch");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
  }


}
