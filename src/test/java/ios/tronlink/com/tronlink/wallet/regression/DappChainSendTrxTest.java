package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxSuccessPage;
import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class DappChainSendTrxTest extends BaseTest {


  public SendTrxPage enterToSendTrxPage(){
    AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = asset.enterSendTrxPage();
    return transfer;
  }



  @Test(description = "input Privatekey to Receiving address",alwaysRun = true)
  public void tsst001_inputPrivatekey() throws Exception {
    changeDappchain();
    //AssetPage asset = new AssetPage(DRIVER);
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendKey(transfer.receiveAddress_text,"324a2052e491e99026442d81df4d2777292840c1b3949e20696c49096c6bacb0");
    String hits = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
  }



  @Test(description = "input error address to Receiving address",alwaysRun = true)
  public void tsst002_inputErrorAddress() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendKey(transfer.receiveAddress_text,"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8a");
    String hits = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(hits.equals("格式错误") || hits.equals("Wrong format"));
  }



  @Test(description = "input not active Receiving address",alwaysRun = true)
  public void tsst003_inputNotActiveAddress() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendKey(transfer.receiveAddress_text,"TFjmzQrQrkUWbu2Qs5NWXjj1F4D3m8aJvu");
    String hits = transfer.note_text.getText();
    Assert.assertTrue(hits.contains("地址未激活") || hits.contains("Address not activated"));
  }



  @Parameters({"address"})
  @Test(description = "input Receiving address same as send address",alwaysRun = true)
  public void tsst004_inputReceivingAddressSameAsSend(String address) throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendKey(transfer.receiveAddress_text,address);
    String hits = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(hits.equals("发送地址与接收地址不能相同") || hits.contains("cannot be the same"));
  }



  @Test(description = "input Null Receiving address",alwaysRun = true)
  public void tsst005_inputNullReceivingAddress() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendKey(transfer.tranferCount_text,"1");
    Assert.assertFalse(transfer.send_btn.isEnabled()); //send btn can click
  }



  @Test(description = "input max send number",alwaysRun = true)
  public void tsst006_inputMaxSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrx("max");
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "input mix send number",alwaysRun = true)
  public void tsst007_inputMixSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrx("mix");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("转账金额需大于0") || centent.contains("greater than 0"));
  }



  @Test(description = "input too Much TRX send number",alwaysRun = true)
  public void tsst008_inputTooMuchSendNumber() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.sendAllTrx("tooMuch");
    String centent = transfer.formatErrorHits_text.getText();
    Assert.assertTrue(centent.equals("余额不足") || centent.equals("insufficient balance"));
  }



  @Test(description = "password error",alwaysRun = true)
  public void tsst009_passwordError() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.receiveAddress_text.sendKeys("TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp");
    transfer.tranferCount_text.sendKeys("1");
    transfer.send_btn.click();
    transfer.transferNow_btn.click();
    transfer.InputPasswordConfim_btn.sendKeys("forget_password");
    Assert.assertTrue(transfer.InputPasswordConfim_btn.isEnabled());
  }



  @Test(description = "Receiving address trim",alwaysRun = true)
  public void tsst010_receivingAddressTrim() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
    transfer.tranferCount_text.sendKeys("1");
    transfer.send_btn.click();
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "Receiving Minimum Trx",alwaysRun = true)
  public void tsst011_sendMinimumTrx() throws Exception {
    SendTrxPage transfer = enterToSendTrxPage();
    transfer.receiveAddress_text.sendKeys("  " + "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp" + "  ");
    transfer.tranferCount_text.sendKeys("0.000001");
    transfer.send_btn.click();
    Assert.assertTrue(transfer.transferNow_btn.isDisplayed());
  }



  @Test(description = "SendTrx success test",alwaysRun = true)
  public void tsst012_sendTrxSuccess() throws Exception {
    AssetPage asset = new AssetPage(DRIVER);
    int trxValue = Integer.valueOf(removeSymbol(asset.getTrxCount()));
    SendTrxPage transfer = asset.enterSendTrxPage();
    SendTrxSuccessPage stsp = transfer.normalSendTrx();
    asset = stsp.enterSendTrxPage();
    int trxValueNewest = Integer.valueOf(removeSymbol(asset.getTrxCount()));
    Assert.assertEquals(trxValue-1,trxValueNewest);
  }

}
