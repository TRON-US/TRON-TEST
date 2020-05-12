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


    @FindBy(id = "com.tronlink.wallet:id/tv_amount")
    public WebElement title_amount_test;

    @FindBy(id = "com.tronlink.wallet:id/tv_sa")
    public WebElement sendAddress_text;

    @FindBy(id = "com.tronlink.wallet:id/tv_ra")
    public WebElement receiverAddress_text;

    @FindBy(id = "com.tronlink.wallet:id/tv_tn")
    public WebElement txid_hash_test;

    @FindBy(id = "com.tronlink.wallet:id/tv_bn")
    public WebElement block_num_text;

    @FindBy(id = "com.tronlink.wallet:id/tv_time")
    public WebElement transaction_time_text;

    @FindBy(id = "com.tronlink.wallet:id/iv_code")
    public WebElement transaction_QRCode;

    @FindBy(id = "com.tronlink.wallet:id/tv_vdd")
    public WebElement to_tronscan_btn;

    @FindBy(id = "com.tronlink.wallet:id/tv_resource")
    public WebElement resouce_cost;



}
