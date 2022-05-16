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

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_type_top")
    public WebElement tv_contract_type_top;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_contract_type")
    public WebElement contractType;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_amount")
    public WebElement tv_amount;



    @FindBy(id = "com.tronlinkpro.wallet:id/tv_sa")
    public WebElement sendAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ra")
    public WebElement receiverAddress_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_tn")
    public WebElement txid_hash_test;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_bn")
    public WebElement block_num_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_time")
    public WebElement transaction_time_text;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_code")
    public WebElement transaction_QRCode;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_vdd")
    public WebElement to_tronscan_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_resource")
    public WebElement resouce_cost;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_note")
    public WebElement tv_note;

    @FindBy(id = "com.tronlinkpro.wallet:id/fee_amount")
    public WebElement fee_coast;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_sa_copy")
    public WebElement send_copy;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_ra_copy")
    public WebElement revice_copy;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_hash_copy")
    public WebElement hash_copy ;



}
