package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrzPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public TrzPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.harmony:id/rl_send")
    public WebElement assets_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/ll_deposit")
    public WebElement transferIn_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_count")
    public List<WebElement> tranferRecordCount_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_freeze_amout")
    public WebElement freezeCount_text;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_count")
    public WebElement trxTotal_text;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_count")
    public WebElement trzTotal_text;



    @FindBy(id = "wallet.tronlink.harmony:id/iv_common_left")
    public WebElement back_btn;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_tab_title")
    public List<WebElement> tranfer_tab;



    @FindBy(id = "wallet.tronlink.harmony:id/tv_count")
    public List<WebElement> transactionRecordAmount_text;



    @FindBy(id = "wallet.tronlink.harmony:id/ll_transfer2")
    public WebElement tranfer10_btn;

    @FindBy(id = "wallet.tronlink.harmony:id/tv_balance")
    public WebElement balance_text;

    //详情按钮
    @FindBy(id = "wallet.tronlink.harmony:id/tv_note_detail")
    public WebElement noteDetail_btn;

    //wallet.tronlink.harmony:id/tv_num
    @FindBy(id = "wallet.tronlink.harmony:id/tv_num")
    public WebElement tokenBalanceInNoteDetail_text;



    public TransferPage enterTransferPage() throws Exception {
        transferIn_btn.click();

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
