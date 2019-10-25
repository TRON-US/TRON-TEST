package com.tronklink.wallet.regression;

import common.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.SendTrxPage;
import wallet.pages.SendTrxSuccessPage;

public class SendTrc10 extends Base {
  @Parameters({"privateKey"})
  @BeforeClass()
  public void setUpBefore(String privateKey) throws Exception {
    System.out.println("执行setUpBefore");
    new Helper().getSign(privateKey,DRIVER);
  }



  @AfterMethod
  public void afterMethod(){
    DRIVER.closeApp();
    DRIVER.activateApp("com.tronlink.wallet");
  }



  @AfterClass
  public void tearDownAfterClass() {
    DRIVER.quit();
  }



  public SendTrxPage enterToSendTrxPage(){
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    return transfer;
  }

  @Test(description = "input max send number")
  public void tsst001_inputMaxSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("max");
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "input mix send number")
  public void tsst002_inputMixSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("mix");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
  }



  @Test(description = "input too Much TRX send number")
  public void tsst003_inputTooMuchSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrc10("tooMuch");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
  }



  @Test(description = "SendTrx success test")
  public void tsst007_sendTrxSuccess() throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    int trxValue = Integer.valueOf(removeSymbol(asset.getTrxCount()));
    SendTrxPage transfer = asset.enterSendTrxPage();
    SendTrxSuccessPage stsp = transfer.normalSendTrx();
    asset = stsp.enterSendTrxPage();
    int trxValueNewest = Integer.valueOf(removeSymbol(asset.getTrxCount()));
    Assert.assertEquals(trxValue-1,trxValueNewest);
  }
}
