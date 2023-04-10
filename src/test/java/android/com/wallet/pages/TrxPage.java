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

    @FindBy(id = "com.tronlinkpro.wallet:id/rl_price_trx")
    public WebElement rl_price_trx;

    public void enterDepositPage(){
        rl_price_trx.click();
        if (isElementExist("我知道了")){
            findElementByText("我知道了").click();
        }
    }
    @FindBy(id = "com.tronlinkpro.wallet:id/ll_action")
    public WebElement ll_action;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_qr")
    public WebElement iv_qr;

    public DetailPage enterProjectInfoPage() throws Exception{
        iv_qr.click();
        TimeUnit.SECONDS.sleep(2);
        return new DetailPage(driver);
    }
    @FindBy(id = "com.tronlinkpro.wallet:id/tv_trx_amount")
    public WebElement trx_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_freeze_amout")
    public WebElement tv_freeze_amout;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_freeze_amout")
    public WebElement freezeCount_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public WebElement trxTotal_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public WebElement tv_count;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_common_left")
    public WebElement back_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_tab_title")
    public List<WebElement> tranfer_tab;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_count")
    public List<WebElement> tranferIncount_text;



    @FindBy(id = "com.tronlinkpro.wallet:id/ll_transfer2")
    public WebElement tranfer10_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_balance")
    public WebElement tv_balance;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    public WebElement totalBalance;
  //@FindBy(id = "com.tronlinkpro.wallet:id/send")
  //public WebElement send_btn;

  @FindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
  public WebElement send_btn;

    @FindBy(xpath = "//*[@resource-id=\"com.tronlinkpro.wallet:id/ll_transfer2\"]/android.widget.ImageView[1]")
    public WebElement trxSendBottomBtn ;

    public WebElement transferIn_btn(){
        return  findElementByText("转链");
    }

    public TransferPage enterTransferInPage() throws Exception {
        transferIn_btn.click();
        return new TransferPage(driver);
    }

    public TransferPage enterSendPage() throws Exception {
      send_btn.click();
      return new TransferPage(driver);
    }

    public SendTrxPage trxSendTrxPage() {
        trxSendBottomBtn.click();
        return new SendTrxPage(driver);
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
