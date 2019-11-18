package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class DappSendTrc20 extends BaseTest {


  public SendTrxPage enterToSendTrxPage(){
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    return transfer;
  }

  @Test(description = "SendTrc20 success test",alwaysRun = true)
  public void tsst001_sendTrc20Success() throws Exception {
    changeDappchain();
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    double trc20Before = transfer.getTrc20Amount();
    String trc20SendAmount = "1";
    SendTrxSuccessPage stsp = transfer.normalSendTrc20(trc20SendAmount);
    TimeUnit.SECONDS.sleep(3);
    transfer = asset.enterSendTrxPage();
    double trc20After = transfer.getTrc20Amount();
    System.out.println(trc20After);
    Assert.assertEquals(trc20Before,trc20After + Double.valueOf(trc20SendAmount));
  }

  @Test(description = "input max send number",alwaysRun = true)
  public void tsst002_inputMaxSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc20("max");
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "input mix send number",alwaysRun = true)
  public void tsst003_inputMixSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc20("mix");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
  }



  @Test(description = "input too Much trc20 send number",alwaysRun = true)
  public void tsst004_inputTooMuchSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc20("tooMuch");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
  }


}
