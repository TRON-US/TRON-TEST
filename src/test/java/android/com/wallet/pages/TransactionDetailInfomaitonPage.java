package android.com.wallet.pages;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionDetailInfomaitonPage extends AbstractPage {


    public AndroidDriver<?> driver;


    public TransactionDetailInfomaitonPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "wallet.tronlink.global:id/tv_amount")
    public WebElement title_amount_test;

    @FindBy(id = "wallet.tronlink.global:id/tv_sa")
    public WebElement sendAddress_text;

    @FindBy(id = "wallet.tronlink.global:id/tv_ra")
    public WebElement receiverAddress_text;

    @FindBy(id = "wallet.tronlink.global:id/tv_tn")
    public WebElement txid_hash_test;

    @FindBy(id = "wallet.tronlink.global:id/tv_bn")
    public WebElement block_num_text;

    @FindBy(id = "wallet.tronlink.global:id/tv_time")
    public WebElement transaction_time_text;

    @FindBy(id = "wallet.tronlink.global:id/iv_code")
    public WebElement transaction_QRCode;

    @FindBy(id = "wallet.tronlink.global:id/tv_vdd")
    public WebElement to_tronscan_btn;

    @FindBy(id = "wallet.tronlink.global:id/tv_resource")
    public WebElement resouce_cost;

    @FindBy(id = "wallet.tronlink.global:id/tv_note")
    public WebElement tv_note;

    @FindBy(id = "wallet.tronlink.global:id/fee_amount")
    public WebElement fee_coast;

}
