package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;

public class TrxPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public TrxPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlinkpro.wallet:id/rl_send")
    public WebElement assets_btn;


//TODO: not locationed
    @FindBy(id = "com.tronlinkpro.wallet:id/ll_deposit")
    public WebElement transferIn_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_freeze_amout")
    public WebElement freezeCount_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public WebElement trxTotal_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_left")
    public WebElement back_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_tab_title")
    public List<WebElement> tranfer_tab;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public List<WebElement> tranferIncount_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/ll_transfer2")
    public WebElement tranfer10_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement balance_text;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    public WebElement totalBalance;
  //@FindBy(id = "com.tronlinkpro.wallet:id/send")
  //public WebElement send_btn;

  @FindBy(id = "com.tronlinkpro.wallet:id/ll_transfer2")
  public WebElement send_btn;



    public TransferPage enterTransferInPage() throws Exception {
        transferIn_btn.click();
        return new TransferPage(driver);
    }

    public TransferPage enterSendPage() throws Exception {
      send_btn.click();
      return new TransferPage(driver);
    }




    public AssetPage enterAssetPage() throws Exception {
        back_btn.click();
        return new AssetPage(driver);
    }


    public boolean getTrxVale(){
        return true;
    }



    public SendTrxPage enterSendTrc10Page() {
        tranfer10_btn.click();
        return new SendTrxPage(driver);
    }



}
