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


    @FindBy(id = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;



    @FindBy(id = "com.tronlink.wallet:id/ll_deposit")
    public WebElement transferIn_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_freeze_amout")
    public WebElement freezeCount_text;



    @FindBy(id = "com.tronlink.wallet:id/tv_count")
    public WebElement trxTotal_text;



    @FindBy(id = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;



    @FindBy(id = "com.tronlink.wallet:id/tv_tab_title")
    public List<WebElement> tranfer_tab;



    @FindBy(id = "com.tronlink.wallet:id/tv_count")
    public List<WebElement> tranferIncount_text;



    public TransferPage enterTransferPage() throws Exception {
        transferIn_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new TransferPage(driver);
    }




    public AssetPage enterAssetPage() throws Exception {
        back_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new AssetPage(driver);
    }




}
