package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrxPage extends AbstractPage {

    public IOSDriver<?> driver;


    public TrxPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(name = "com.tronlink.wallet:id/rl_send")
    public WebElement assets_btn;



    @FindBy(name = "com.tronlink.wallet:id/ll_deposit")
    public WebElement transferIn_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_trx_amount")
    public WebElement trx_text;



    @FindBy(name = "com.tronlink.wallet:id/tv_freeze_amout")
    public WebElement freezeCount_text;



    @FindBy(name = "com.tronlink.wallet:id/tv_count")
    public WebElement trxTotal_text;



    @FindBy(name = "com.tronlink.wallet:id/iv_common_left")
    public WebElement back_btn;



    @FindBy(name = "com.tronlink.wallet:id/tv_tab_title")
    public List<WebElement> tranfer_tab;



    @FindBy(name = "com.tronlink.wallet:id/tv_count")
    public List<WebElement> tranferIncount_text;

    @FindBy(name = "balanceLabe")
    public WebElement balanceLabe_text;


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
