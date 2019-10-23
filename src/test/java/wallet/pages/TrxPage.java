package wallet.pages;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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




    public TransferInPage enterTransferInPage() throws Exception {
        transferIn_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new TransferInPage(driver);
    }






}
